package az.evilcastle.turnbased.entities;

import az.evilcastle.turnbased.enums.MessageType;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RequestMessage {

    long id;
    String type;
    String payload;

    public String toJson(){
        return "{\"id\":\"" + id + "\"," + "\"type\":\"" + type + "\"," + "\"payload\":\"" + payload + "\"}";
    }
}
