package de.urr4.winemanager.rest;

import de.urr4.wine.entities.Order;
import de.urr4.winemanager.beans.CRUDService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@ApplicationScoped
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/orders")
public class OrderEndpoint{

    @Inject
    private CRUDService<Order> orderCRUDService;

    @GET
    public List<Order> getAll() {
        return orderCRUDService.getAll();
    }

    @GET
    @Path("/{id}")
    public Order getById(@PathParam("id") Long id) {
        return orderCRUDService.getById(id);
    }

    @POST
    public void save(Order order) {
        orderCRUDService.save(order);
    }

    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") Long id) {
        Order order = orderCRUDService.getById(id);
        orderCRUDService.delete(order);
    }
}
