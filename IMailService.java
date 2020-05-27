package com.server.service;

/**
 * @Description:
 * @Version:
 */
public interface IMailService {
    /**
     * @param to
     * @param subject
     * @param content
     */
    int sendMail(String to,String subject,String content);
}
