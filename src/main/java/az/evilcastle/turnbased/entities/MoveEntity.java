package az.evilcastle.turnbased.entities;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MoveEntity {

    String who;
    String action;
    String where;
}
