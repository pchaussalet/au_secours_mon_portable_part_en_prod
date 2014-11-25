package net.chaussalet.bbl.asmppp.mygreatapp.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import static com.google.common.io.ByteStreams.copy;
import static java.text.MessageFormat.format;
import static javax.ws.rs.core.MediaType.TEXT_HTML;
import static javax.ws.rs.core.Response.Status.NOT_FOUND;
import static javax.ws.rs.core.Response.ok;
import static javax.ws.rs.core.Response.status;

@Path("/")
public class StaticResource {
    @GET
    @Produces(TEXT_HTML)
    public Response index() {
        return ok(getStreamingOutput("/static/index.html")).build();
    }

    @GET
    @Path("/css/{filepath:.+}")
    @Produces("text/css")
    public Response css(@PathParam("filepath") String filepath) {
        return getResponse(filepath, "/static/css/{0}").build();
    }

    @GET
    @Path("/js/{filepath:.+}")
    @Produces("application/javascript")
    public Response js(@PathParam("filepath") String filepath) {
        return getResponse(filepath, "/static/js/{0}").build();
    }

    private Response.ResponseBuilder getResponse(String filepath, String pattern) {
        Response.ResponseBuilder responseBuilder;
        StreamingOutput entity = getStreamingOutput(format(pattern, filepath));
        if (entity != null) {
            responseBuilder = ok(entity);
        } else {
            responseBuilder = status(NOT_FOUND);
        }
        return responseBuilder;
    }

    private StreamingOutput getStreamingOutput(String resourceName) {
        StreamingOutput output = null;
        final InputStream resourceAsStream = getClass().getResourceAsStream(resourceName);
        if (resourceAsStream != null) {
            output = new StreamingOutput() {
                @Override
                public void write(OutputStream outputStream) throws IOException, WebApplicationException {
                    copy(resourceAsStream, outputStream);
                }
            };
        }
        return output;
    }
}
