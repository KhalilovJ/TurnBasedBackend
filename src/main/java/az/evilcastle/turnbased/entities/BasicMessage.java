package az.evilcastle.turnbased.entities;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class BasicMessage {
    private String content;
    private String name;
    private String msgTime;


}
