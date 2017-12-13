package de.urr4.winemanager.beans;

import de.urr4.wine.entities.Wine;
import de.urr4.winemanager.exceptions.WineManagerException;
import de.urr4.winemanager.exceptions.WineManagerExceptionType;
import org.neo4j.ogm.session.Session;
import org.slf4j.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Stateless
public class WineBean implements CRUDService<Wine> {

    @Inject
    private Session session;

    @Inject
    private Logger logger;

    @Override
    public List<Wine> getAll() {
        logger.debug("Getting all Wine");
        List<Wine> wines = session.loadAll(Wine.class).stream().collect(toList());
        return wines;
    }

    @Override
    public Wine getById(Long id) {
        logger.debug("Getting Wine by Id "+id);
        Wine wine = session.load(Wine.class, id);
        if(wine == null){
            String message = "Couldn't load Wine "+id;
            logger.error(message);
            throw new WineManagerException(message, WineManagerExceptionType.ENTITY_NOT_FOUND);
        }
        return wine;
    }

    @Override
    public void save(Wine wine) {
        logger.debug("Saving Wine "+wine.getName());
        session.save(wine);
    }

    @Override
    public void delete(Wine wine) {
        logger.debug("Deleting Wine "+ wine.getName());
        session.delete(wine);
    }
}
