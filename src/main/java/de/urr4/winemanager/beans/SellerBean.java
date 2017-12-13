package de.urr4.winemanager.beans;

import de.urr4.wine.entities.Seller;
import de.urr4.winemanager.exceptions.WineManagerException;
import de.urr4.winemanager.exceptions.WineManagerExceptionType;
import org.neo4j.ogm.session.Session;
import org.slf4j.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Stateless
public class SellerBean implements CRUDService<Seller> {

    @Inject
    private Session session;

    @Inject
    private Logger logger;

    @Override
    public List<Seller> getAll() {
        logger.debug("Getting all Seller");
        List<Seller> sellers = session.loadAll(Seller.class).stream().collect(toList());
        return sellers;
    }

    @Override
    public Seller getById(Long id) {
        logger.debug("Getting Seller by Id "+id);
        Seller seller = session.load(Seller.class, id);
        if(seller == null){
            String message = "Couldn't load Seller "+id;
            logger.error(message);
            throw new WineManagerException(message, WineManagerExceptionType.ENTITY_NOT_FOUND);
        }
        return seller;
    }

    @Override
    public void save(Seller seller) {
        logger.debug("Saving Seller "+seller.getName());
        session.save(seller);
    }

    @Override
    public void delete(Seller seller) {
        logger.debug("Deleting Seller "+seller.getName());
        session.delete(seller);
    }
}
