package de.urr4.winemanager.exceptions;

public class WineManagerException extends RuntimeException {

    private WineManagerExceptionType type;

    public WineManagerException(String message, WineManagerExceptionType type) {
        super(message);
        this.type = type;
    }

    public WineManagerExceptionType getType() {
        return type;
    }

    public void setType(WineManagerExceptionType type) {
        this.type = type;
    }
}
