package az.evilcastle.turnbased.entities;

import az.evilcastle.turnbased.enums.ActionType;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RequestMessage {

    long id;
    ActionType type;
    String payload;

    public String toJson(){
        return "{\"id\":\"" + id + "\"," + "\"type\":\"" + type + "\"," + "\"payload\":\"" + payload + "\"}";
    }
}
