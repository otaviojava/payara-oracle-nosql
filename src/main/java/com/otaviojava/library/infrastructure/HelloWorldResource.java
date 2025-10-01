package com.otaviojava.library.infrastructure;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;

@Path("hello")
public class HelloWorldResource {

    @Inject
    @ConfigProperty(name = "defaultName", defaultValue = "world")
    private String defaultName;

    @GET
    @Operation(summary = "Get a personalized greeting")
    @APIResponses(value = {
        @APIResponse(responseCode = "200", description = "Successful operation"),
        @APIResponse(responseCode = "400", description = "Invalid input")
    })
    public Response hello(@QueryParam("name") @Parameter(name = "name", description = "Name to include in the greeting", required = false, example = "John") String name) {
        if ((name == null) || name.trim().isEmpty()) {
            name = defaultName;
        }
        return Response
                .ok(name)
                .build();
    }

    

}