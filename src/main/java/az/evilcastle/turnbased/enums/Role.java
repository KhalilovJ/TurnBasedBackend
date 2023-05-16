package az.evilcastle.turnbased.enums;

public enum Role {
    ADMIN(0),
    PLAYER(1);

    private int statusCode;

    Role(int statusCode) {
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
