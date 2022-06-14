package by.bsu.soroka.ea.neko.service.exception;

public class ServiceException extends Exception {
    private static final long serialVersionUID = -5476104233135309142L;

    /**
     * Construct exception
     * @param e exception message.
     */
    public ServiceException(String e){
        super(e);
    }
}
