package net.chaussalet.bbl.asmppp.mygreatapp.resources;

import com.google.inject.Inject;
import com.google.inject.name.Named;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.Response.ok;

@Path("/status")
public class StatusResource {
    private String version;

    @Inject
    public StatusResource(@Named("app.version") String version) {
        this.version = version;
    }

    @GET
    @Path("/version")
    public Response version() {
        return ok(version).build();
    }
}
