package com.server.service;

import com.alibaba.fastjson.JSONObject;
import com.dao.entity.JobDetail;
import com.dao.entity.TaskInfo;
import com.dao.mapper.JobDetailMapper;
import com.dao.mapper.StockUserMapper;
import com.server.constant.CommonConstant;
import com.util.redis.IJedisService;
import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
@Transactional
public class JobDetailService {
    @Autowired
    private JobDetailMapper jobDetailMapper;
    @Autowired
    private TaskService taskService;
    @Autowired
    private Scheduler scheduler;
    @Autowired
    private IJedisService jedisService;
    @Autowired
    private StockUserMapper stockUserMapper;
    public int insertJobDetail(JobDetail jobDetail){
        String job_group = jobDetail.getJobType()+"_"+System.currentTimeMillis();
        TaskInfo taskInfo = new TaskInfo();
        taskInfo.setJobGroup(job_group);
        taskInfo.setJobName("com.rest.config.quartz");
        taskInfo.setCronExpression(jobDetail.getCron());
        taskInfo.setJobDescription(jobDetail.getJobName());
        jobDetail.setJobClassName("com.rest.config.quartz");
        jobDetail.setJobGroup(job_group);
        Map<String,Object> parmp = buildMap(jobDetail);;
        if(parmp==null){
            return 0;
        }
        int jobRs = taskService.addJob(taskInfo,parmp);
        if(jobRs==0){
            return 0;
        }
        if(!"on".equals(jobDetail.getStatus())){
            taskService.pause(taskInfo.getJobName(),taskInfo.getJobName());
            jobDetail.setStatus("off");
        }
        return jobDetailMapper.insert(jobDetail);
    }
    public List<JobDetail> getAllJob(){
        List<JobDetail> rs = jobDetailMapper.getAllJob();
        try {
            List<String> groupJobList = scheduler.getJobGroupNames();
            for (JobDetail jDetail:rs) {
                for (String groupJob : groupJobList) {
                    if(groupJob.equals(jDetail.getJobGroup())){
                        for (JobKey jobKey : scheduler.getJobKeys(GroupMatcher.<JobKey>groupEquals(groupJob))) {
                            List<? extends Trigger> triggers = scheduler.getTriggersOfJob(jobKey);
                            for (Trigger trigger : triggers) {
                                Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
                                org.quartz.JobDetail jobDetail = scheduler.getJobDetail(jobKey);

                                String cronExpression = "", createTime = "";

                                if (trigger instanceof CronTrigger) {
                                    CronTrigger cronTrigger = (CronTrigger) trigger;
                                    cronExpression = cronTrigger.getCronExpression();
                                    createTime = cronTrigger.getDescription();
                                }
                                TaskInfo info = new TaskInfo();
                                info.setJobName(jobKey.getName());
                                info.setJobGroup(jobKey.getGroup());
                                info.setJobDescription(jobDetail.getDescription());
                                info.setJobStatus(triggerState.name());
                                info.setCronExpression(cronExpression);
                                info.setCreateTime(createTime);
                                jDetail.setTaskInfo(info);
                            }
                        }
                    }

                }

        }
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public int updateJobStatus(int fid,String status){
        JobDetail jobDetail = jobDetailMapper.selectByPrimaryKey(fid);
        if(jobDetail == null){
            return 0;
        }
        int rs =0;
        if("on".equals(status)){
            rs = taskService.resume(jobDetail.getJobClassName(),jobDetail.getJobGroup());
        }else{
            rs = taskService.pause(jobDetail.getJobClassName(),jobDetail.getJobGroup());
        }
        jobDetail.setStatus(status);
        return jobDetailMapper.updateByPrimaryKeySelective(jobDetail);
    }

    public JobDetail getJobDetailById(int id){
        return jobDetailMapper.selectByPrimaryKey(id);
    }

    public int doUpdateJob(JobDetail jobDetail){
        TaskInfo taskInfo = new TaskInfo();
        taskInfo.setJobGroup(jobDetail.getJobGroup());
        taskInfo.setJobName("com.rest.config.quartz");
        taskInfo.setCronExpression(jobDetail.getCron());
        taskInfo.setJobDescription(jobDetail.getJobName());
        Map<String,Object> parmp = buildMap(jobDetail);
        if(parmp==null){
            return 0;
        }
        taskService.delete(taskInfo.getJobName(),taskInfo.getJobGroup());
        int jobRs = taskService.addJob(taskInfo,parmp);
        if(jobRs==0){
            return 0;
        }
        if(!"on".equals(jobDetail.getStatus())){
            taskService.pause(taskInfo.getJobName(),taskInfo.getJobGroup());
            jobDetail.setStatus("off");
        }

        return jobDetailMapper.updateByPrimaryKeySelective(jobDetail);
    }

    private Map<String,Object> buildMap(JobDetail jobDetail){
        Map<String,Object> parmp = new HashMap<>();

        parmp.put("uid",jobDetail.getUid().toString());
        if("wash".equals(jobDetail.getJobType())){
            parmp.put("symbol",jobDetail.getSymbol().toString());
            parmp.put("min",jobDetail.getPriceMin().toString());
            parmp.put("max",jobDetail.getPriceMax().toString());

        }else if("wave".equals(jobDetail.getJobType())){
            parmp.put("symbol",jobDetail.getSymbol().toString());
            parmp.put("ktype",jobDetail.getkType().toString());
            if(jobDetail.getTargetPrice()!=null){
                parmp.put("targetPrice",jobDetail.getTargetPrice().toString());
            }
            if(jobDetail.getWaveMin()!=null){
                parmp.put("waveMin",jobDetail.getWaveMin().toString());
            }
            if(jobDetail.getWaveMax()!=null){
                parmp.put("waveMax",jobDetail.getWaveMax().toString());
            }
            if(jobDetail.getTimeType()!=null){
                parmp.put("timeType",jobDetail.getTimeType().toString());
            }
        }else if("balance".equals(jobDetail.getJobType())){
            parmp.put("balanceType",jobDetail.getBalanceType().toString());
        }else{
            return null;
        }
        return parmp;
    }

    public int deleteJobStatus(int fid) {
        JobDetail jobDetail = jobDetailMapper.selectByPrimaryKey(fid);

        if(jobDetail == null){
            return 0;
        }
        taskService.delete(jobDetail.getJobClassName(),jobDetail.getJobGroup());
        return jobDetailMapper.deleteByPrimaryKey(fid);
    }

    public String userRelationship(){

        String userShip = jedisService.get(CommonConstant.USER_SHIP);
        if(userShip==null){
            List<Map> userShipList = stockUserMapper.getUserShip();
            if(userShipList!=null){
                userShip = JSONObject.toJSONString(userShipList);
                jedisService.set(CommonConstant.USER_SHIP,userShip,60*24);
            }
            return userShip;
        }
        return userShip;
    }
}
