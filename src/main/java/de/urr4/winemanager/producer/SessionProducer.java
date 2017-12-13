package de.urr4.winemanager.producer;

import org.neo4j.ogm.config.Configuration;
import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

@ApplicationScoped
public class SessionProducer {

    @Produces
    public Configuration configuration(){
        Configuration configuration = new Configuration.Builder()
                .uri("bolt://localhost")
                .credentials("neo4j", "admin")
                .build();
        return configuration;
    }

    @Produces
    public SessionFactory sessionFactory(){
        SessionFactory sessionFactory = new SessionFactory(configuration(), "de.urr4.wine.entities");
        return sessionFactory;
    }

    @Produces
    public Session session(){
        return sessionFactory().openSession();
    }
}
