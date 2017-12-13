package de.urr4.winemanager.rest;

import de.urr4.wine.entities.Seller;
import de.urr4.wine.entities.Wine;
import de.urr4.winemanager.beans.CRUDService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

@ApplicationScoped
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/sellers")
public class SellerEndpoint {

    @Inject
    private CRUDService<Seller> sellerCRUDService;

    @GET
    public List<Seller> getAll() {
        return sellerCRUDService.getAll();
    }

    @GET
    @Path("/{id}")
    public Seller getById(@PathParam("id") Long id) {
        return sellerCRUDService.getById(id);
    }

    @POST
    public void save(Seller seller) {
        sellerCRUDService.save(seller);
    }

    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") Long id) {
        Seller seller = sellerCRUDService.getById(id);
        sellerCRUDService.delete(seller);
    }

    @PUT
    @Path("/{sellerId}/wines/")
    public void addWineToSeller(@PathParam("sellerId") Long sellerId, Wine wine){
        Seller seller = sellerCRUDService.getById(sellerId);
        seller.addWine(wine);
        sellerCRUDService.save(seller);
    }

    @DELETE
    @Path("/{sellerId}/wines/{wineId}")
    public void removeWineFromSeller(@PathParam("sellerId") Long sellerId, @PathParam("wineId") Long wineId){
        Seller seller = sellerCRUDService.getById(sellerId);
        seller.setWines(seller.getWines().stream().filter(n -> !n.getId().equals(wineId)).collect(toSet()));
        sellerCRUDService.save(seller);
    }
}
