package az.evilcastle.turnbased.enums;

public enum GameStatus {
    WAITING(0),
    STARTED(1);

    private int statusCode;

    GameStatus(int statusCode) {
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
