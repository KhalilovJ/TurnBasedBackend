package az.evilcastle.turnbased.entities.redis;


import az.evilcastle.turnbased.enums.GameStatus;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.web.socket.WebSocketSession;

import java.util.List;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GameSession {

    long id;
    GameStatus gameStatus;
    List<String> socketSessions;
    List<WebSocketSession> webSocketSessions;
}
