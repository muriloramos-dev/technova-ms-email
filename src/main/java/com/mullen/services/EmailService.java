package com.mullen.services;

import com.mullen.domain.Email;
import com.mullen.interfaces.EmailInterface;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Produces;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.eclipse.microprofile.reactive.messaging.Incoming;
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
    public void sendRegistrationConfirmationEmail(byte[] payload) {
        String raw = new String(payload, StandardCharsets.UTF_8);
        System.out.println("Received email for confirmation: " + raw);
        Email emailEntity = new Email();
        emailEntity.setEmailType("registration-confirmation");
        emailEntity.setTo(raw);
        emailEntity.setMessage("Thank you for registering with us! Please confirm your email address. With the code down below.");
        emailEntity.setSubject("Registration Confirmation");
        emailEntity.setSentAt(LocalDateTime.now());
        emailEntity.setCode(String.valueOf(Math.random() * 100000));
        emailInterface.sendRegistrationConfirmationEmail(emailEntity);
        Email.persist(emailEntity);
    }

}
