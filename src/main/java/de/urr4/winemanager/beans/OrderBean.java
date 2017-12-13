package de.urr4.winemanager.beans;

import de.urr4.wine.entities.Order;
import de.urr4.winemanager.exceptions.WineManagerException;
import de.urr4.winemanager.exceptions.WineManagerExceptionType;
import org.neo4j.ogm.session.Session;
import org.slf4j.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Stateless
public class OrderBean implements CRUDService<Order> {

    @Inject
    private Session session;

    @Inject
    private Logger logger;

    @Override
    public List<Order> getAll() {
        logger.debug("Getting all Orders");
        List<Order> orders = session.loadAll(Order.class).stream().collect(toList());
        return orders;
    }

    @Override
    public Order getById(Long id) {
        logger.debug("Getting Order by Id "+id);
        Order order = session.load(Order.class, id);
        if(order == null){
            String message = "Couldn't load Order "+id;
            logger.error(message);
            throw new WineManagerException(message, WineManagerExceptionType.ENTITY_NOT_FOUND);
        }
        return order;
    }

    @Override
    public void save(Order order) {
        logger.debug("Saving Order for Seller "+order.getSeller().getName());
        session.save(order);
    }

    @Override
    public void delete(Order order) {
        logger.debug("Deleting Order for Seller "+order.getSeller().getName());
        session.delete(order);
    }
}
