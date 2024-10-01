package com.example.NotificationService.Repository;

import com.example.NotificationService.Entity.MailInfo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface MailInfoRepository extends MongoRepository<MailInfo, String> {
    @Query("{ 'from': ?0, 'subject': { $regex: ?1, $options: 'i' } }")
    List<MailInfo> findByFromAndSubjectLike(String from, String subject);
}
