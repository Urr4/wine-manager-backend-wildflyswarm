package de.urr4.winemanager.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

@ApplicationScoped
public class LoggerProducer {

    @Produces
    public Logger logger(InjectionPoint injectionPoint){
        Logger logger = LoggerFactory.getLogger(injectionPoint.getClass());
        return logger;
    }
}
