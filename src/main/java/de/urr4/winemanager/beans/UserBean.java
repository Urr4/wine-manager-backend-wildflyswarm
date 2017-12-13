package de.urr4.winemanager.beans;

import de.urr4.wine.entities.User;
import de.urr4.winemanager.exceptions.WineManagerException;
import de.urr4.winemanager.exceptions.WineManagerExceptionType;
import org.neo4j.ogm.session.Session;
import org.slf4j.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Stateless
public class UserBean implements CRUDService<User>{

    @Inject
    private Session session;

    @Inject
    private Logger logger;

    @Override
    public List<User> getAll() {
        logger.debug("Getting all User");
        List<User> users = session.loadAll(User.class).stream().collect(toList());
        return users;
    }

    @Override
    public User getById(Long id) {
        logger.debug("Getting User by Id "+id);
        User user = session.load(User.class, id);
        if(user == null){
            String message = "Couldn't load User "+id;
            logger.error(message);
            throw new WineManagerException(message, WineManagerExceptionType.ENTITY_NOT_FOUND);
        }
        return user;
    }

    @Override
    public void save(User user) {
        logger.debug("Saving User "+user.getName());
        session.save(user);
    }

    @Override
    public void delete(User user) {
        logger.debug("Deleting User "+user.getName());
        session.delete(user);
    }
}
