package az.evilcastle.turnbased.enums;

public enum BatchStatus {
    WAITING(0),
    STARTED(1);

    private int statusCode;

    BatchStatus(int statusCode) {
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
