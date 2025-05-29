package com.mullen.services;

import com.mullen.domain.Email;
import com.mullen.domain.EmailType;
import com.mullen.interfaces.EmailInterface;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Produces;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

@Consumes("application/json")
@Produces("application/json")
@ApplicationScoped
public class EmailService {

    @Inject
    @RestClient
    EmailInterface emailInterface;

    @Incoming("email")
    @Outgoing("code")
    @Transactional
    public String sendRegistrationConfirmationEmail(byte[] payload) {
        String raw = new String(payload, StandardCharsets.UTF_8);
        System.out.println("Received email for confirmation: " + raw);
        Email emailEntity = new Email();
        emailEntity.setEmailType(EmailType.CONFIRMATION);
        emailEntity.setTo(raw);
        emailEntity.setSubject("Registration Confirmation");
        emailEntity.setSentAt(LocalDateTime.now());
        emailEntity.setCode(String.valueOf(Math.round(Math.random() * 100000)));
        emailInterface.sendRegistrationConfirmationEmail(emailEntity);
        Email.persist(emailEntity);
        return emailEntity.getCode();
    }
}
