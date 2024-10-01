package com.example.NotificationService.Service;

import com.example.NotificationService.Entity.MailInfo;
import com.example.NotificationService.Utils.EmailSubjectEnum;
import com.example.NotificationService.Utils.TypeMailEnum;


import java.util.List;

public interface MailService {

    MailInfo saveMailInfo(MailInfo mailInfo);

    List<MailInfo> getAllMailInfos();

    MailInfo getMailInfoById(String id);

    void deleteMailInfo(String id);

    Boolean sendWithTemplate(MailInfo mail);

}
