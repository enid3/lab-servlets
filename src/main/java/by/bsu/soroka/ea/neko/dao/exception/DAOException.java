package by.bsu.soroka.ea.neko.dao.exception;

public class DAOException extends Exception {
    /**
     * Construct exception
     * @param e exception.
     */
    public DAOException(Exception e){super(e);}

    /**
     * Construct exception
     * @param message exception message.
     */
    public DAOException(String message){super(message);}
}
