package com.mullen.interfaces;

import com.mullen.domain.Email;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient(configKey = "email-api")
@Consumes("application/json")
@Produces("application/json")
public interface EmailInterface {

    @POST
    @Path("/webhook/confirm/registration")
    void sendRegistrationConfirmationEmail(Email email);
}
