package de.urr4.winemanager.beans;

import de.urr4.wine.entities.Berry;
import de.urr4.wine.entities.User;
import de.urr4.wine.entities.Wine;
import org.neo4j.ogm.session.Session;
import org.slf4j.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Stateless
public class RelationshipBean {

    @Inject
    private Session session;

    @Inject
    private Logger logger;

    public List<User> getUserByWine(Wine wine){
        logger.debug("Getting Users liking Wine "+wine.getName());
        List<User> users = new ArrayList<>();
        String cql = "MATCH (u:User)-[:LIKES]->(w:Wine) WHERE id(w)={wineId} RETURN u";
        Iterable<User> foundUsers = session.query(User.class, cql, Collections.singletonMap("wineId", wine.getId()));
        foundUsers.forEach(n->users.add(n));
        return users;
    }

    public List<Wine> getWineByBerry(Berry berry){
        logger.debug("Getting Wines with Berry "+berry.getName());
        List<Wine> wines = new ArrayList<>();
        String cql = "MATCH (w:Wine)-[MADE_FROM]->(b:Berry) WHERE id(b)={berryId} RETURN w";
        Iterable<Wine> foundWine = session.query(Wine.class, cql, Collections.singletonMap("berryId", berry.getId()));
        foundWine.forEach(n->wines.add(n));
        return wines;
    }
}
