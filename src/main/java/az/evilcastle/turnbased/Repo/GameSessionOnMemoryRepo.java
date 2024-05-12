package az.evilcastle.turnbased.Repo;

import az.evilcastle.turnbased.models.RequestMessage;
import az.evilcastle.turnbased.models.redis.GameSession;
import az.evilcastle.turnbased.enums.GameActionType;
import az.evilcastle.turnbased.enums.GameStatus;
import az.evilcastle.turnbased.services.interfaces.GameSessionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.ConcurrentReferenceHashMap;
import org.springframework.web.socket.WebSocketSession;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.ConcurrentMap;

@Component
@Slf4j
public class GameSessionOnMemoryRepo {

    private GameSessionService gameSessionService;
    private static final ConcurrentMap<String, GameSession> gameSessions = new ConcurrentReferenceHashMap<>(); // TODO change type to UUID
    private static final ConcurrentMap<String, String> players = new ConcurrentReferenceHashMap<>();

    public void addGameSession(WebSocketSession webSocketSession, RequestMessage requestMessage, GameSessionService gss) {

        if (gameSessionService == null) {
            gameSessionService = gss;
        }

        String id =requestMessage.getPlayerInGameId();

        gameSessions.putIfAbsent(UUID.randomUUID().toString(), GameSession.builder()
                .id(id)
                .webSocketSessions(new ArrayList<>())
                .webSocketSessionHashMap(new HashMap<>())
                .gameStatus(GameStatus.WAITING)
//                .socketSessions(new ArrayList<>())
                .build());

        addPlayer(webSocketSession, requestMessage);
    }

    public void addPlayer(WebSocketSession webSocketSession, RequestMessage requestMessage) {

        GameSession gameSession = gameSessions.get(requestMessage.getPlayerInGameId());

//        gameSession.getWebSocketSessions().add(webSocketSession);
//        gameSession.getSocketSessions().add(webSocketSession.getId());

        if (!gameSession.getWebSocketSessions().isEmpty()) {
            gameSession.setGameStatus(GameStatus.STARTED);
            RequestMessage requestMessageCreated = RequestMessage.builder()
                    .type(GameActionType.CONNECTION)
                    .payload("game started")
                    .build();

//            GameSession usersSession = getUsersSession(webSocketSession.getId());

            log.info(requestMessageCreated.toJson());
            gameSessionService.sendMessageToSession(requestMessage.getPlayerInGameId(), requestMessageCreated.toJson());
        }

        log.info("Adding player " + webSocketSession.getId() + " " + requestMessage.getPlayerInGameId());

        gameSession.addWebSocketSession(webSocketSession, gameSession.getWebSocketSessions().size());

        players.put(webSocketSession.getId(), requestMessage.getPlayerInGameId());
    }

    public ConcurrentMap<String, GameSession> getAllActiveGameSessions() {
        return gameSessions;
    }

    public ConcurrentMap<String, String> getAllActivePlayers() {
        return players;
    }

    public GameSession getGameSession(String id) {
        return gameSessions.get(id);
    }

    public void removePlayer(WebSocketSession webSocketSession) {
        String playerId = webSocketSession.getId();
        String gameSessionId = players.get(playerId);

        var sessions = gameSessions.get(gameSessionId).getWebSocketSessions();

        sessions.remove(webSocketSession);
        players.remove(playerId);

        if (sessions.isEmpty()) removeGameSession(gameSessionId);
    }

    public void removeGameSession(String id) {
        gameSessions.remove(id);
    }

    public void printAllSessions(String id) {
        log.info(gameSessions.get(id).toString());
    }

    public GameSession getUsersSession(String sessionKey) {

        String sessionId = players.get(sessionKey);
        log.info("session id is " + sessionId);
        log.info(String.valueOf(gameSessions.get(sessionId).getId()));
        return gameSessions.get(sessionId);
    }
}
