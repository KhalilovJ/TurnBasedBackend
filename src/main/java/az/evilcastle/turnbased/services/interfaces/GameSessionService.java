package az.evilcastle.turnbased.services.interfaces;

import az.evilcastle.turnbased.enums.GameActionType;
import az.evilcastle.turnbased.models.RequestMessage;
import az.evilcastle.turnbased.models.redis.GameSession;
import org.springframework.web.socket.WebSocketSession;

import java.util.concurrent.ConcurrentMap;

public interface GameSessionService {

    void join(WebSocketSession webSocketSession, RequestMessage requestMessage);

    void distributeRequest(WebSocketSession webSocketSession, RequestMessage requestMessage);

    ConcurrentMap<String, GameSession> getAllGameSession();

    GameSession getGameSession(String id);

    void removePlayer(WebSocketSession webSocketSession);

    void removeGameSession(String id);

    ConcurrentMap<String, String> getAllActivePlayers();

    void sendMessageToSession(String sessionId, String message);

    void sendMessageToSocket(WebSocketSession webSocketSession, String message, GameActionType gameActionType);

    void gameMessageReceived(String sessionId, RequestMessage requestMessage);

}
