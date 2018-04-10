package io.sattler.resources;

import com.codahale.metrics.annotation.ExceptionMetered;
import com.codahale.metrics.annotation.Timed;
import io.dropwizard.jersey.params.NonEmptyStringParam;
import io.sattler.api.PermissionResponse;
import io.sattler.db.UserPermission;
import io.sattler.db.UserPermissionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.UUID;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class AuthorizationResource {

    private final UserPermissionDAO userPermissionDAO;

    private final static Logger log = LoggerFactory.getLogger(AuthorizationResource.class);

    public AuthorizationResource(UserPermissionDAO userPermissionDAO) {
        this.userPermissionDAO = userPermissionDAO;
    }

    @GET
    @Timed
    @ExceptionMetered
    @Path("/permission/{user_id}/{company_id}")
    public Response fetchPermissionFromUserById(@Context HttpHeaders httpHeaders,
                                                @PathParam("user_id") NonEmptyStringParam userId,
                                                @PathParam("company_id") NonEmptyStringParam companyId) {

        String requestId = httpHeaders.getHeaderString("x-transactionid");
        if(requestId == null) {
            requestId = UUID.randomUUID().toString();
        }

        log.info(requestId + ": got new request to fetch permission from user " + userId + " and company " + companyId);

        UserPermission userPermission = userPermissionDAO.getPermissionFromUsersCompany(companyId.get().get(),
                userId.get().get());

        if(userPermission == null) {
            log.info(requestId + ": abort because requested company is not allowed to access");
            PermissionResponse response = new PermissionResponse("ERROR", requestId,
                    "abort because of missing user permission");
            return Response.status(404).entity(response).build();
        }


        log.info(requestId + ": successfully fetched user permission");

        PermissionResponse response = new PermissionResponse("OK" ,requestId,
                "", userPermission);

        return Response.status(200).entity(response).build();


    }
}
