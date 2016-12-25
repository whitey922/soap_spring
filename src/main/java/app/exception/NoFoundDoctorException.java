package app.exception;

/**
 * User: IMPERIVM
 * Date: 25.12.2016
 * Date: 13:52
 */
public class NoFoundDoctorException extends RuntimeException {
    public NoFoundDoctorException() {
    }

    public NoFoundDoctorException(String message) {
        super(message);
    }

    public NoFoundDoctorException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoFoundDoctorException(Throwable cause) {
        super(cause);
    }

    public NoFoundDoctorException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
