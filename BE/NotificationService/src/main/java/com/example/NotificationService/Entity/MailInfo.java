package com.example.NotificationService.Entity;

import com.example.NotificationService.Utils.EmailSubjectEnum;
import com.example.NotificationService.Utils.TypeMailEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "mailInfo") // This marks the class as a MongoDB document
public class MailInfo {

    @MongoId  // The unique identifier for the MongoDB document
    private String email;

    EmailSubjectEnum subject;

    TypeMailEnum type;

    String body;

}