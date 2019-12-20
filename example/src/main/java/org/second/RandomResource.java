package org.second;

import java.util.Random;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/random")
public class RandomResource {

    private static Random random = new Random();

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Integer randomInt() {
        return random.nextInt(101);
    }
}