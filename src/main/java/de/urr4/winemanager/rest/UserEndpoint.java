package de.urr4.winemanager.rest;

import de.urr4.wine.entities.User;
import de.urr4.wine.entities.Wine;
import de.urr4.winemanager.beans.CRUDService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/users")
public class UserEndpoint {

    @Inject
    private CRUDService<User> userCRUDService;

    @GET
    public List<User> getAll() {
        return userCRUDService.getAll();
    }

    @GET
    @Path("/{id}")
    public User getById(@PathParam("id") Long id) {
        return userCRUDService.getById(id);
    }

    @POST
    public void save(User user) {
        userCRUDService.save(user);
    }

    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") Long id) {
        User user = userCRUDService.getById(id);
        userCRUDService.delete(user);
    }

    @PUT
    @Path("/{id}/wines/")
    public void addWineToUser(@PathParam("id") Long userId, Wine wine){
        User user = userCRUDService.getById(userId);
        user.addLikedWine(wine);
        userCRUDService.save(user);
    }

    @DELETE
    @Path("/{userId}/wines/{wineId}")
    public void removeWineFromUser(@PathParam("userId") Long userId, @PathParam("wineId") Long wineId){
        User user = userCRUDService.getById(userId);
        user.setLikedWines(user.getLikedWines().stream().filter(n -> !n.getId().equals(wineId)).collect(Collectors.toList()));
        userCRUDService.save(user);
    }
}

