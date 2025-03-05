package com.clozingtag.clozingtag.notification.service.impl;

import com.clozingtag.clozingtag.notification.service.configuration.AppConfiguration;
import com.clozingtag.clozingtag.notification.service.dto.NotificationRequest;
import com.clozingtag.clozingtag.notification.service.service.EmailService;
import com.mailjet.client.ClientOptions;
import com.mailjet.client.MailjetClient;
import com.mailjet.client.MailjetRequest;
import com.mailjet.client.MailjetResponse;
import com.mailjet.client.errors.MailjetException;
import com.mailjet.client.resource.Emailv31;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service
@Slf4j
public class EmailServiceImpl implements EmailService {

    private final AppConfiguration appConfiguration;

    public EmailServiceImpl(AppConfiguration appConfiguration) {
        this.appConfiguration = appConfiguration;
    }


    @Override
    public void sendEmail(NotificationRequest request) {
        try {
            sendWithTemplate(request);
        } catch (MailjetException e) {
            log.error(e.getMessage(), e);
        }
    }


    public void sendWithTemplate(NotificationRequest notificationRequest) throws MailjetException {
        ClientOptions options = ClientOptions.builder()
                .apiKey(appConfiguration.getMailjet().getApi().getKey())
                .apiSecretKey(appConfiguration.getMailjet().getApi().getSecret())
                .build();
        MailjetClient client;
        MailjetRequest request;
        MailjetResponse response;
        Map<String, String> templates = appConfiguration.getMailjet().getTemplates();
        client = new MailjetClient(options);
//        try {
            request = new MailjetRequest(Emailv31.resource)
                    .property(Emailv31.MESSAGES, new JSONArray()
                            .put(new JSONObject()
                                    .put(Emailv31.Message.FROM, new JSONObject()
                                            .put("Email", appConfiguration.getMailjet().getFromEmail())
                                            .put("Name", appConfiguration.getMailjet().getFromName()))
                                    .put(Emailv31.Message.TO, new JSONArray()
                                            .put(new JSONObject()
                                                    .put("Email", notificationRequest.getEmail())
                                                    .put("Name", notificationRequest.getName())))
                                    .put(Emailv31.Message.TEMPLATEID, Integer.parseInt(templates.get(notificationRequest.getTemplate())))
                                    .put(Emailv31.Message.TEMPLATELANGUAGE, true)
                                    .put(Emailv31.Message.SUBJECT, notificationRequest.getSubject())
                                    .put(Emailv31.Message.VARIABLES, new JSONObject(notificationRequest.getEmailParams()))
                            )
                    );
//            response = client.post(request);
//            System.out.println(response.getStatus());
//            System.out.println(response.getData());
//        } catch (MailjetException e) {
//            throw new RuntimeException(e);
//        }
    }

}
