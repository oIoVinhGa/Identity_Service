package com.example.NotificationService.Controller;

import com.example.NotificationService.Entity.MailInfo;
import com.example.NotificationService.Service.MailService;
import com.example.NotificationService.Utils.EmailSubjectEnum;
import com.example.NotificationService.Utils.TypeMailEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mailinfo")
public class MailInfoController {
    @Autowired
    private MailService mailService;

    // POST to create a new MailInfo
    @PostMapping
    public boolean createMailInfo(@RequestBody MailInfo mailInfo) {
        mailInfo.setSubject(EmailSubjectEnum.OTP);
        mailInfo.setType(TypeMailEnum.OTP);
        return mailService.sendWithTemplate(mailInfo);
    }

    @PostMapping("/welcome")
    public boolean createWelcomeInfo(@RequestBody MailInfo mailInfo) {
        mailInfo.setSubject(EmailSubjectEnum.LINK);
        mailInfo.setType(TypeMailEnum.VERIFY_LINK);
        return mailService.sendWithTemplate(mailInfo);
    }

    // GET all MailInfo objects
    @GetMapping
    public List<MailInfo> getAllMailInfos() {
        return mailService.getAllMailInfos();
    }

    // GET a single MailInfo by ID
    @GetMapping("/{id}")
    public MailInfo getMailInfoById(@PathVariable String id) {
        return mailService.getMailInfoById(id);
    }

    // DELETE a MailInfo by ID
    @DeleteMapping("/{id}")
    public void deleteMailInfo(@PathVariable String id) {
        mailService.deleteMailInfo(id);
    }
}
