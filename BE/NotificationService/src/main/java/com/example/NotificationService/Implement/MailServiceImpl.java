package com.example.NotificationService.Implement;

import com.example.NotificationService.Entity.MailInfo;

import com.example.NotificationService.Repository.MailInfoRepository;
import com.example.NotificationService.Service.MailService;
import com.example.NotificationService.Utils.EmailSubjectEnum;
import com.example.NotificationService.Utils.TypeMailEnum;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
public class MailServiceImpl implements MailService {

    @Autowired
    MailInfoRepository mailInfoRepository;

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public MailInfo saveMailInfo(MailInfo mailInfo) {
        return mailInfoRepository.save(mailInfo);
    }

    @Override
    public List<MailInfo> getAllMailInfos() {
        return mailInfoRepository.findAll();
    }

    @Override
    public MailInfo getMailInfoById(String id) {
        return mailInfoRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteMailInfo(String id) {
        mailInfoRepository.deleteById(id);
    }

    @Override
    public Boolean sendWithTemplate(MailInfo mailInfo) {
        try {
            String htmlContent = loadHtmlTemplate(mailInfo.getType().getTemplate());
            switch (mailInfo.getType()) {
                case OTP -> {
                    htmlContent = htmlContent.replaceAll("##otp##", mailInfo.getBody());
                    sendHtmlEmail(mailInfo.getEmail(), mailInfo.getSubject().toString(), htmlContent);

                }
                case VERIFY_LINK -> {
                    htmlContent = htmlContent.replaceAll("##verify_link##", mailInfo.getBody());
                    sendHtmlEmail(mailInfo.getEmail(), mailInfo.getSubject().toString(), htmlContent);
                }
            }
            mailInfoRepository.save(mailInfo);
        } catch (MessagingException | UnsupportedEncodingException e) {
            throw new RuntimeException("Mail Error", e);
        }
        return true;
    }



    private void sendHtmlEmail(String toMail, String subject, String htmlBody) throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
        helper.setFrom("noreply@thirdteam.com", "Third Team");
        helper.setTo(toMail);
        helper.setSubject(subject);
        helper.setText(htmlBody, true);
        javaMailSender.send(message);
    }

    private String loadHtmlTemplate(String templateName) {
        try (InputStream inputStream = new ClassPathResource(templateName).getInputStream()) {
            return new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException( e.getMessage());
        }
    }
}
