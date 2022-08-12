package ucll.web2.web2_redo.domain.model;

public class DomainException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public DomainException() {
        super();
    }

    public DomainException(String message) {
        super(message);
    }

    public DomainException(String message, Throwable exception) {
        super(message, exception);
    }

}