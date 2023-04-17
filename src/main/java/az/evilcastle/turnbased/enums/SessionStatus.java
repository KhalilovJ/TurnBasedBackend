package az.evilcastle.turnbased.enums;

public enum SessionStatus {
    WAITING(0),
    STARTED(1);

    private int statusCode;

    SessionStatus(int statusCode) {
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
