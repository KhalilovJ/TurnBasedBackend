package az.evilcastle.turnbased.models.redis;

import az.evilcastle.turnbased.enums.GameStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashMap;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GameSession {

    String id;
    GameStatus gameStatus;
//    List<String> socketSessions;
    @JsonIgnore
    List<WebSocketSession> webSocketSessions;
    @JsonIgnore
    HashMap<String, Integer> webSocketSessionHashMap;

    public void addWebSocketSession(WebSocketSession session, Integer id){
        webSocketSessions.add(session);
        webSocketSessionHashMap.put(session.getId(), id);
    }

    public Integer getUserInGameId(String session){
        return webSocketSessionHashMap.get(session);
    }

}
