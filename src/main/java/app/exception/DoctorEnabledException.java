package app.exception;

/**
 * User: IMPERIVM
 * Date: 25.12.2016
 * Date: 13:56
 */
public class DoctorEnabledException extends RuntimeException {
    public DoctorEnabledException() {
    }

    public DoctorEnabledException(String message) {
        super(message);
    }

    public DoctorEnabledException(String message, Throwable cause) {
        super(message, cause);
    }

    public DoctorEnabledException(Throwable cause) {
        super(cause);
    }

    public DoctorEnabledException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
