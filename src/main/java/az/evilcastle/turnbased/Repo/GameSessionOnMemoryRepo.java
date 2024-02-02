package az.evilcastle.turnbased.Repo;

import az.evilcastle.turnbased.entities.RequestMessage;
import az.evilcastle.turnbased.entities.redis.GameSession;
import az.evilcastle.turnbased.enums.GameActionType;
import az.evilcastle.turnbased.enums.GameStatus;
import az.evilcastle.turnbased.services.interfaces.GameSessionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.ConcurrentReferenceHashMap;
import org.springframework.web.socket.WebSocketSession;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ConcurrentMap;
@Component
@Slf4j
public class GameSessionOnMemoryRepo {
    private GameSessionService gameSessionService;
    private static final ConcurrentMap<Long, GameSession> gameSessions = new ConcurrentReferenceHashMap<>(); // TODO change type to UUID
    private static final ConcurrentMap<String, Long> players = new ConcurrentReferenceHashMap<>();

    public void addGameSession(WebSocketSession webSocketSession, RequestMessage requestMessage, GameSessionService gss) {

        if (gameSessionService == null){
            gameSessionService = gss;
        }

        long id = requestMessage.getId();

        gameSessions.putIfAbsent(id, GameSession.builder()
                .id(id)
                .webSocketSessions(new ArrayList<>())
                .webSocketSessionHashMap(new HashMap<>())
                .gameStatus(GameStatus.WAITING)
//                .socketSessions(new ArrayList<>())
                .build());

        addPlayer(webSocketSession, requestMessage);
    }

    public void addPlayer(WebSocketSession webSocketSession, RequestMessage requestMessage) {

        GameSession gameSession = gameSessions.get(requestMessage.getId());

//        gameSession.getWebSocketSessions().add(webSocketSession);
//        gameSession.getSocketSessions().add(webSocketSession.getId());

        if (gameSession.getWebSocketSessions().size()>0){
            gameSession.setGameStatus(GameStatus.STARTED);
            RequestMessage rm = RequestMessage.builder()
                    .type(GameActionType.CONNECTION)
                    .payload("game started")
                    .build();

//            GameSession usersSession = getUsersSession(webSocketSession.getId());

            log.info(rm.toJson());
            gameSessionService.SendMessageToSession(requestMessage.getId(), rm.toJson());
        }

        log.info("Adding player " + webSocketSession.getId() + " " + requestMessage.getId());

        gameSession.addWebSocketSession(webSocketSession, gameSession.getWebSocketSessions().size());

        players.put(webSocketSession.getId(), requestMessage.getId());
    }

    public ConcurrentMap<Long, GameSession> getAllActiveGameSessions() {
        return gameSessions;
    }

    public ConcurrentMap<String, Long> getAllActivePlayers() {
        return players;
    }

    public GameSession getGameSession(Long id) {
        return gameSessions.get(id);
    }

    public void removePlayer(WebSocketSession webSocketSession) {
        String playerId = webSocketSession.getId();
        long gameSessionId = players.get(playerId);

//        TODO Murad, Fix it
//        List<String> playerList = gameSessions.get(gameSessionId).getSocketSessions();

//        playerList.remove(playerId);
//        players.remove(playerId);
//
//        if (playerList.isEmpty()) {
//            removeGameSession(gameSessionId);
//        }
    }

    public void removeGameSession(long id) {
        gameSessions.remove(id);
    }

    public void printAllSessions(Long id){
        log.info(gameSessions.get(id).toString());
    }
    public GameSession getUsersSession(String sessionKey){

        Long sessionId = players.get(sessionKey);
        log.info("session id is " + sessionId);
        log.info(String.valueOf(gameSessions.get(sessionId).getId()));
        return gameSessions.get(sessionId);
    }
}
