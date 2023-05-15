package az.evilcastle.turnbased.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MoveEntity {

    @JsonProperty("who")
    String who;
    @JsonProperty("action")
    String action;
    @JsonProperty("where")
    String where;
}
