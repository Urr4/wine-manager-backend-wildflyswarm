package de.urr4.winemanager.rest;

import de.urr4.wine.entities.Berry;
import de.urr4.wine.entities.Wine;
import de.urr4.winemanager.beans.CRUDService;
import de.urr4.winemanager.beans.RelationshipBean;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@ApplicationScoped
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/berries")
public class BerryEndpoint{

    @Inject
    private CRUDService<Berry> berryCRUDService;

    @Inject
    private RelationshipBean relationshipBean;

    @GET
    public List<Berry> getAll() {
        return berryCRUDService.getAll();
    }

    @GET
    @Path("/{id}")
    public Berry getById(@PathParam("id") Long id) {
        return berryCRUDService.getById(id);
    }

    @POST
    public void save(Berry berry) {
        berryCRUDService.save(berry);
    }

    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") Long id) {
        Berry berry = berryCRUDService.getById(id);
        berryCRUDService.delete(berry);
    }

    @GET
    @Path("/{id}/wines/")
    public List<Wine> getWinesByBerry(@PathParam("id") Long berryId){
        Berry berry = berryCRUDService.getById(berryId);
        List<Wine> wineByBerry = relationshipBean.getWineByBerry(berry);
        return wineByBerry;
    }
}
