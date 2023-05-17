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

    Integer userId;
    @JsonProperty("who")
    String who;
    @JsonProperty("action")
    String action;
    @JsonProperty("where")
    String where;

    public String toJson(String divider){

        String st =  "{*who*:*" + who + "*," +
                "*action*:*" + action + "*," +
                "*userId*:*" + userId + "*," +
                "*where*:*" + where +"*}";

        st = st.replace("*", divider);
        return st;
    }
}
