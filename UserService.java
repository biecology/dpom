package com.huagu.vcoin.main.service.admin;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huagu.vcoin.main.controller.admin.ExceptionLog;
import com.huagu.vcoin.main.dao.FintrolinfoDAO;
import com.huagu.vcoin.main.dao.FmessageDAO;
import com.huagu.vcoin.main.dao.FscoreDAO;
import com.huagu.vcoin.main.dao.FsystemargsDAO;
import com.huagu.vcoin.main.dao.FuserDAO;
import com.huagu.vcoin.main.dao.FvirtualwalletDAO;
import com.huagu.vcoin.main.model.Fintrolinfo;
import com.huagu.vcoin.main.model.Fmessage;
import com.huagu.vcoin.main.model.Fscore;
import com.huagu.vcoin.main.model.Fuser;
import com.huagu.vcoin.main.model.Fvirtualwallet;

@Service
public class UserService {
    @Autowired
    private FuserDAO fuserDAO;
    @Autowired
    private FvirtualwalletDAO fvirtualwalletDAO;
    @Autowired
    private FintrolinfoDAO fintrolinfoDAO;
    @Autowired
    private FscoreDAO fscoreDAO;
    @Autowired
    private FsystemargsDAO fsystemargsDAO;
    @Autowired
    private FmessageDAO messageDAO;

    public Fuser findById(int id) {
        Fuser fuser = this.fuserDAO.findById(id);
        if (fuser.getFscore() != null) {
            fuser.getFscore().getFlevel();
        }
        if (fuser.getfIntroUser_id() != null) {
            fuser.getfIntroUser_id().getFlastLoginTime();
        }
        return fuser;
    }

    public void saveObj(Fuser obj) {
        this.fuserDAO.save(obj);
    }

    public void deleteObj(int id) {
        Fuser obj = this.fuserDAO.findById(id);
        this.fuserDAO.delete(obj);
    }

    public void updateObj(Fuser obj) {
        this.fuserDAO.attachDirty(obj);
    }

    public void updateObj(Fuser obj, Fmessage message) {
        try {
            this.fuserDAO.attachDirty(obj);
            this.messageDAO.save(message);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    public void updateObj(Fuser obj, Fscore fscore, Fuser fintrolUser, Fvirtualwallet fvirtualwallet,
            Fintrolinfo introlinfo) {
        try {
            this.fuserDAO.attachDirty(obj);
            if (fscore != null) {
                this.fscoreDAO.attachDirty(fscore);
            }
            if (fintrolUser != null) {
                this.fuserDAO.attachDirty(fintrolUser);
            }
            if (fvirtualwallet != null) {
                this.fvirtualwalletDAO.attachDirty(fvirtualwallet);
            }
            if (introlinfo != null) {
                this.fintrolinfoDAO.save(introlinfo);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    public void updateObj(Fuser obj, Fintrolinfo introlinfo, Fvirtualwallet fvirtualwallet) {
        try {
            this.fuserDAO.attachDirty(obj);
            if (introlinfo != null) {
                this.fintrolinfoDAO.save(introlinfo);
            }

            if (fvirtualwallet != null) {
                this.fvirtualwalletDAO.attachDirty(fvirtualwallet);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    public List<Fuser> findByProperty(String name, Object value) {
        return this.fuserDAO.findByProperty(name, value);
    }

    public List<Fuser> findAll() {
        return this.fuserDAO.findAll();
    }

    public List<Fuser> list(int firstResult, int maxResults, String filter, boolean isFY) {
        List<Fuser> all = this.fuserDAO.list(firstResult, maxResults, filter, isFY);
        for (Fuser fuser : all) {
            try {
                if (fuser.getFscore() != null) {
                    fuser.getFscore().getFlevel();
                }
                if (fuser.getfIntroUser_id() != null) {
                    fuser.getfIntroUser_id().getFnickName();
                }
            } catch (Exception e) {
                ExceptionLog.add("/ssadmin/userList",
                        String.format("fuserDAO.list(%s, %s, %s, %s)", firstResult, maxResults, filter, isFY),
                        String.format("请检查用户ID为:%s的信息", fuser.getFid()), e.getMessage());
            }
        }
        return all;
    }

    public List<Fuser> simpleList(int firstResult, int maxResults, String filter, boolean isFY) {
        List<Fuser> all = this.fuserDAO.list(firstResult, maxResults, filter, isFY);
        return all;
    }

    public List findByDate(String propertyName, Date value) {
        return this.fuserDAO.findByDate(propertyName, value);
    }

    public List getUserGroup(String filter) {
        return this.fuserDAO.getUserGroup(filter);
    }

    public List<Fuser> listUserForAudit(int firstResult, int maxResults, String filter, boolean isFY) {
        return this.fuserDAO.listUserForAudit(firstResult, maxResults, filter, isFY);
    }

    public List getUser(int type) {
        return this.fuserDAO.getUser(type);
    }

    public void updateUser(Fvirtualwallet fvirtualwallet, Fscore fscore) {
        try {
            this.fvirtualwalletDAO.attachDirty(fvirtualwallet);
            this.fscoreDAO.attachDirty(fscore);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }

    }

    public void updateUser1(Fvirtualwallet fvirtualwallet, Fscore fscore, Fintrolinfo introlInfo) {
        try {
            this.fvirtualwalletDAO.attachDirty(fvirtualwallet);
            this.fscoreDAO.attachDirty(fscore);
            if (introlInfo != null) {
                this.fintrolinfoDAO.save(introlInfo);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
}