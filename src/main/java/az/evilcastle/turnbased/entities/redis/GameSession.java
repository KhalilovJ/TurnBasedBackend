package az.evilcastle.turnbased.entities.redis;


import az.evilcastle.turnbased.enums.GameStatus;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.socket.WebSocketSession;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GameSession {

    long id;
    GameStatus gameStatus;
    List<WebSocketSession> socketSessions;
}
