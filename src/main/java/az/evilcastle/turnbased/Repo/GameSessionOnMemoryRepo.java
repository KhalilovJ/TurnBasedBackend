package az.evilcastle.turnbased.Repo;

import az.evilcastle.turnbased.entities.RequestMessage;
import az.evilcastle.turnbased.entities.redis.GameSession;
import org.springframework.util.ConcurrentReferenceHashMap;
import org.springframework.web.socket.WebSocketSession;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentMap;

public class GameSessionOnMemoryRepo {

    private static final ConcurrentMap<Long, GameSession> gameSessions = new ConcurrentReferenceHashMap<>();
    private static final ConcurrentMap<String, GameSession> players = new ConcurrentReferenceHashMap<>();

    public void addGameSession(WebSocketSession webSocketSession, RequestMessage requestMessage) {

        long id = requestMessage.getId();

        gameSessions.putIfAbsent(id, GameSession.builder().id(id).socketSessions(new ArrayList<>()).build());
        addPlayer(webSocketSession, requestMessage);
    }

    public void addPlayer(WebSocketSession webSocketSession, RequestMessage requestMessage) {

        GameSession gameSession = gameSessions.get(requestMessage.getId());

        gameSession.getSocketSessions().add(webSocketSession.getId());
        players.putIfAbsent(webSocketSession.getId(), gameSession);
    }

    public ConcurrentMap<Long, GameSession> getAllActiveGameSessions() {
        return gameSessions;
    }

    public ConcurrentMap<String, GameSession> getAllActivePlayers() {
        return players;
    }

    public GameSession getGameSession(Long id) {
        return gameSessions.get(id);
    }

    public void removePlayer(WebSocketSession webSocketSession) {
        String playerId = webSocketSession.getId();
        long gameSessionId = players.get(playerId).getId();

        List<String> playerList = gameSessions.get(gameSessionId).getSocketSessions();

        playerList.remove(playerId);
        players.remove(playerId);

        if (playerList.isEmpty()) {
            removeGameSession(gameSessionId);
        }
    }

    public void removeGameSession(long id) {
        gameSessions.remove(id);
    }
}
