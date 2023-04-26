package az.evilcastle.turnbased.Repo;

import az.evilcastle.turnbased.entities.RequestMessage;
import az.evilcastle.turnbased.entities.redis.GameSession;
import org.springframework.util.ConcurrentReferenceHashMap;
import org.springframework.web.socket.WebSocketSession;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentMap;

public class GameSessionOnMemoryRepo {

    private final ConcurrentMap<Long, GameSession> gameSessions = new ConcurrentReferenceHashMap<>();

    public void addGameSession(WebSocketSession webSocketSession, RequestMessage requestMessage) {

        long id = requestMessage.getId();

        gameSessions.putIfAbsent(id, GameSession.builder().id(id).socketSessions(new ArrayList<>()).build());
        addPlayer(webSocketSession, requestMessage);
    }

    public void addPlayer(WebSocketSession webSocketSession, RequestMessage requestMessage) {

        GameSession gameSession = gameSessions.get(requestMessage.getId());

        gameSession.getSocketSessions().add(webSocketSession);
    }

    public ConcurrentMap<Long, GameSession> getAllActiveGameSessions() {
        return gameSessions;
    }
}
