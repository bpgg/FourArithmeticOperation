package exception;

public class IllegalSituationException extends Exception {
    private String mExceptionMessage;

    public IllegalSituationException(String message) {
        super(message);
        mExceptionMessage = message;
    }

    @Override
    public String getMessage() {
        return mExceptionMessage;
    }
}
