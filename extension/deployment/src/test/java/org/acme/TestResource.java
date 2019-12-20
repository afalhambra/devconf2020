package org.acme;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/test")
public class TestResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String ping() {
        return "ping";
    }

    @GET
    @Path("/limit")
    @Produces(MediaType.TEXT_PLAIN)
    public String limitExceeded() {
        try {
            Thread.sleep(200l);
        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        }
        return "ping";
    }

}
