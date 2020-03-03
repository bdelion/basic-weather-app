package fr.fifiz.training.app.java.owm;

/**
 * Classe exception technique.
 * 
 * @author bertrand
 */
public class TechnicalException extends RuntimeException {
    private static final long serialVersionUID = 8297783346453966573L;

    public TechnicalException(String message) {
        super(message);
    }

    public TechnicalException(String message, Throwable cause) {
        super(message, cause);
    }

}
