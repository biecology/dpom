package com.huagu.vcoin.main.service.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huagu.vcoin.main.dao.FquestionDAO;
import com.huagu.vcoin.main.model.Fquestion;

@Service
public class QuestionService {
    @Autowired
    private FquestionDAO questionDAO;
    @Autowired
    private HttpServletRequest request;

    public Fquestion findById(int id) {
        return this.questionDAO.findById(id);
    }

    public Fquestion findById_user(int id) {
        Fquestion q = this.questionDAO.findById(id);
        if (q.getFuser() != null) {
            q.getFuser().getFnickName();
        }
        return q;
    }

    public void saveObj(Fquestion obj) {
        this.questionDAO.save(obj);
    }

    public void deleteObj(int id) {
        Fquestion obj = this.questionDAO.findById(id);
        this.questionDAO.delete(obj);
    }

    public void updateObj(Fquestion obj) {
        this.questionDAO.attachDirty(obj);
    }

    public List<Fquestion> findByProperty(String name, Object value) {
        return this.questionDAO.findByProperty(name, value);
    }

    public List<Fquestion> findAll() {
        return this.questionDAO.findAll();
    }

    public List<Fquestion> list(int firstResult, int maxResults, String filter, boolean isFY) {
        List<Fquestion> all = this.questionDAO.list(firstResult, maxResults, filter, isFY);
        for (Fquestion fquestion : all) {
            if (fquestion.getFuser() != null) {
                fquestion.getFuser().getFemail();
            }
            if (fquestion.getFadmin() != null) {
                fquestion.getFadmin().getFname();
            }
        }
        return all;
    }
}