package com.huagu.vcoin.main.service.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huagu.vcoin.main.dao.FentrustlogDAO;

@Service
public class EntrustlogService {
    @Autowired
    private FentrustlogDAO entrustlogDAO;
    @Autowired
    private HttpServletRequest request;

    public List list(int firstResult, int maxResults, String filter, boolean isFY) {
        List all = this.entrustlogDAO.list(firstResult, maxResults, filter, isFY);
        return all;
    }

    public Double getTotalTradeAmt() {
        return this.entrustlogDAO.getTotalTradeAmt();
    }

}