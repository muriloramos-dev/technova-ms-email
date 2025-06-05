package com.mullen.interfaces;

import com.mullen.domain.Email;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Produces;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient(configKey = "email-api")
@Consumes("application/json")
@Produces("application/json")
public interface EmailInterface {

    @POST
    void sendEmail(Email email);
}
