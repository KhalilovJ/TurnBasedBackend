package az.evilcastle.turnbased.models;

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
    String secondary;

    public String toJson(String divider){

        String st =  "{*who*:*" + who + "*," +
                "*action*:*" + action + "*," +
                "*userId*:*" + userId + "*," +
                "*secondary*:*" + secondary + "*," +
                "*where*:*" + where +"*}";

        st = st.replace("*", divider);
        return st;
    }
}
