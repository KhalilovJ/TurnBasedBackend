package az.evilcastle.turnbased.entities;

import az.evilcastle.turnbased.enums.GameActionType;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RequestMessage {

    long id;
    GameActionType type;
    String payload;

    public String toJson(){
        return "{\"id\":\"" + id + "\"," + "\"type\":\"" + type + "\"," + "\"payload\":\"" + payload + "\"}";
    }
}
