package de.urr4.winemanager.rest;

import de.urr4.wine.entities.Berry;
import de.urr4.wine.entities.User;
import de.urr4.wine.entities.Wine;
import de.urr4.winemanager.beans.CRUDService;
import de.urr4.winemanager.beans.RelationshipBean;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/wine")
public class WineEndpoint {

	@Inject
	private CRUDService<Wine> wineCRUDService;

	@Inject
    private RelationshipBean relationshipBean;

	@GET
	public List<Wine> getAll() {
		return wineCRUDService.getAll();
	}

	@GET
	@Path("/{id}")
	public Wine getById(@PathParam("id") Long id) {
		return wineCRUDService.getById(id);
	}

	@POST
	public void save(Wine wine) {
		wineCRUDService.save(wine);
	}

	@DELETE
	@Path("/{id}")
	public void delete(@PathParam("id") Long id) {
		Wine wine = wineCRUDService.getById(id);
		wineCRUDService.delete(wine);
	}

	@PUT
	@Path("/{id}/berries/")
	public void addBerryToWine(@PathParam("id") Long wineId, Berry berry){
		Wine wine = wineCRUDService.getById(wineId);
		wine.addBerry(berry);
		wineCRUDService.save(wine);
	}

	@DELETE
	@Path("/{wineId}/berries/{berryId}")
	public void removeBerryFromWine(@PathParam("wineId") Long wineId, @PathParam("berryId") Long berryId){
		Wine wine = wineCRUDService.getById(wineId);
		wine.setBerries(wine.getBerries().stream().filter(n->!n.getId().equals(berryId)).collect(Collectors.toSet()));
		wineCRUDService.save(wine);
	}

	@GET
	@Path("/{wineId}/users")
	public List<User> getUsersByWine(@PathParam("wineId") Long wineId){
	    Wine wine = wineCRUDService.getById(wineId);
        List<User> userByWine = relationshipBean.getUserByWine(wine);
        return userByWine;
    }
}