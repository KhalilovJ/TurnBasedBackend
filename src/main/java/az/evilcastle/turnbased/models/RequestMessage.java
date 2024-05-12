package az.evilcastle.turnbased.models;

import az.evilcastle.turnbased.enums.GameActionType;
import az.evilcastle.turnbased.mappers.GameActionTypeDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestMessage {

    String playerInGameId;
    @JsonDeserialize(using = GameActionTypeDeserializer.class)
    GameActionType type;
    String payload;

    public String toJson(){
        return "{\"id\":\"" + playerInGameId + "\"," + "\"type\":\"" + type + "\"," + "\"payload\":\"" + payload + "\"}";
    }
}
