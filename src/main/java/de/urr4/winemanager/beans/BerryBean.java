package de.urr4.winemanager.beans;

import de.urr4.wine.entities.Berry;
import de.urr4.winemanager.exceptions.WineManagerException;
import de.urr4.winemanager.exceptions.WineManagerExceptionType;
import org.neo4j.ogm.session.Session;
import org.slf4j.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Stateless
public class BerryBean implements CRUDService<Berry>{

    @Inject
    private Session session;

    @Inject
    private Logger logger;

    @Override
    public List<Berry> getAll() {
        logger.debug("Getting all Berries");
        List<Berry> berries = session.loadAll(Berry.class).stream().collect(toList());
        return berries;
    }

    @Override
    public Berry getById(Long id) {
        logger.debug("Getting Berry by Id "+id);
        Berry berry = session.load(Berry.class, id);
        if(berry == null){
            String message = "Couldn't load Berry "+id;
            logger.error(message);
            throw new WineManagerException(message, WineManagerExceptionType.ENTITY_NOT_FOUND);
        }
        return berry;
    }

    @Override
    public void save(Berry berry) {
        logger.debug("Saving Berry "+berry.getName());
        session.save(berry);
    }

    @Override
    public void delete(Berry berry) {
        logger.debug("Deleting Berry "+berry.getName());
        session.delete(berry);
    }
}
