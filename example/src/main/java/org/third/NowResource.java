package org.third;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("date")
public class NowResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String date() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }
}