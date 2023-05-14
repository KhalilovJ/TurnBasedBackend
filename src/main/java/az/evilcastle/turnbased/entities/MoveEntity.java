package az.evilcastle.turnbased.entities;

import lombok.Data;

@Data
public class MoveEntity {

    String who;
    String action;
    String where;
}
