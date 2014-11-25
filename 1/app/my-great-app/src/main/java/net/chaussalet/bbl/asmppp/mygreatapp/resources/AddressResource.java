package net.chaussalet.bbl.asmppp.mygreatapp.resources;

import com.google.inject.Inject;
import net.chaussalet.bbl.asmppp.mygreatapp.model.Address;
import net.chaussalet.bbl.asmppp.mygreatapp.repository.AddressRepository;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.util.SortedSet;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.Response.ResponseBuilder;
import static javax.ws.rs.core.Response.noContent;

@Path("/address")
@Produces(APPLICATION_JSON)
public class AddressResource {
    private AddressRepository addressRepository;

    @Inject
    public AddressResource(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @GET
    public Response list() {
        SortedSet<Address> addresses = addressRepository.list();
        ResponseBuilder responseBuilder = Response.ok(addresses);
        return responseBuilder.build();
    }

    @POST
    @Consumes(APPLICATION_JSON)
    public Response add(Address address) {
        addressRepository.save(address);
        return noContent().build();
    }

    @DELETE
    @Path("/{id}")
    public Response del(@PathParam("id") int id) {
        addressRepository.remove(id);
        return noContent().build();
    }
}
