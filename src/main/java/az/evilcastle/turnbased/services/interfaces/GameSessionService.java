package az.evilcastle.turnbased.services.interfaces;

import az.evilcastle.turnbased.entities.RequestMessage;
import az.evilcastle.turnbased.entities.redis.GameSession;
import org.springframework.web.socket.WebSocketSession;

import java.util.concurrent.ConcurrentMap;

public interface GameSessionService {

    void join(WebSocketSession webSocketSession, RequestMessage requestMessage);

    void distributeRequest(WebSocketSession webSocketSession, RequestMessage requestMessage);

    ConcurrentMap<Long, GameSession> getAllGameSession();

}
