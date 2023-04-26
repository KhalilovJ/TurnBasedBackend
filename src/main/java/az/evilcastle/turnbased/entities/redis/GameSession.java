package az.evilcastle.turnbased.entities.redis;


import az.evilcastle.turnbased.enums.GameStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.web.socket.WebSocketSession;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GameSession {

    long id;
    GameStatus gameStatus;
    List<WebSocketSession> socketSessions;
}
