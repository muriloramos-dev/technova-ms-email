package com.mullen.domain;

import io.quarkus.mongodb.panache.PanacheMongoEntityBase;
import io.quarkus.mongodb.panache.common.MongoEntity;
import org.bson.codecs.pojo.annotations.BsonId;

import java.time.LocalDateTime;

@MongoEntity(database = "technova-ms-email", collection = "emails")
public class Email extends PanacheMongoEntityBase {
    @BsonId
    public String id;

    public String emailType;

    public String to;

    public String subject;

    public String message;

    public String sentFrom;

    public String code;

    public LocalDateTime sentAt;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmailType() {
        return emailType;
    }

    public void setEmailType(String emailType) {
        this.emailType = emailType;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSentFrom() {
        return sentFrom;
    }

    public void setSentFrom(String sentFrom) {
        this.sentFrom = sentFrom;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDateTime getSentAt() {
        return sentAt;
    }

    public void setSentAt(LocalDateTime sentAt) {
        this.sentAt = sentAt;
    }

    public Email() {
    }

    public Email(String id, String emailType, String to, String subject, String message, String sentFrom, String code, LocalDateTime sentAt) {
        this.id = id;
        this.emailType = emailType;
        this.to = to;
        this.subject = subject;
        this.message = message;
        this.sentFrom = sentFrom;
        this.code = code;
        this.sentAt = sentAt;
    }
}
