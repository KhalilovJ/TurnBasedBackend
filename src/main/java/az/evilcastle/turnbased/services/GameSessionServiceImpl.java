package az.evilcastle.turnbased.services;

import az.evilcastle.turnbased.Repo.GameSessionOnMemoryRepo;
import az.evilcastle.turnbased.models.MoveEntity;
import az.evilcastle.turnbased.models.RequestMessage;
import az.evilcastle.turnbased.models.redis.GameSession;
import az.evilcastle.turnbased.enums.GameActionType;
import az.evilcastle.turnbased.services.interfaces.GameSessionService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.concurrent.ConcurrentMap;

@Service
@AllArgsConstructor
@Log4j2
public class GameSessionServiceImpl implements GameSessionService {

    private final GameSessionOnMemoryRepo gameSessionOnMemoryRepo;
    private final
    ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void join(WebSocketSession webSocketSession, RequestMessage requestMessage) {
        gameSessionOnMemoryRepo.addGameSession(webSocketSession, requestMessage, this);
    }

    @Override
    public void distributeRequest(WebSocketSession webSocketSession, RequestMessage requestMessage) {

        switch (requestMessage.getType()) {
            case CREATE -> log.info("CREATE");
            case CONNECTION -> join(webSocketSession, requestMessage);
            case GAMEACTION -> gameMessageReceived(webSocketSession.getId(), requestMessage);
            default ->
                //TODO exception handler for WRONG REQUEST TYPE
                    log.info("WRONG REQUEST TYPE");
        }
    }

    @Override
    public ConcurrentMap<Long, GameSession> getAllGameSession() {
        return gameSessionOnMemoryRepo.getAllActiveGameSessions();
    }

    @Override
    public GameSession getGameSession(Long id) {
        GameSession gs = gameSessionOnMemoryRepo.getGameSession(id);
        log.info(id + "game session test: " + gs);
        gameSessionOnMemoryRepo.printAllSessions(id);
//        return gs;
        return null;
    }

    @Override
    public void removePlayer(WebSocketSession webSocketSession) {
        gameSessionOnMemoryRepo.removePlayer(webSocketSession);
    }

    @Override
    public void removeGameSession(long id) {
        gameSessionOnMemoryRepo.removeGameSession(id);
    }

    @Override
    public ConcurrentMap<String, Long> getAllActivePlayers() {
        return gameSessionOnMemoryRepo.getAllActivePlayers();
    }

    @Override
    public void SendMessageToSession(Long sessionId, String message) {
        GameSession gs = gameSessionOnMemoryRepo.getGameSession(sessionId);
        gs.getWebSocketSessions().forEach(s-> {
            try {
                s.sendMessage(new TextMessage(message));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Override
    public void gameMessageReceived(String userId, RequestMessage requestMessage){
        MoveEntity moveEntity;

        GameSession gameSession = gameSessionOnMemoryRepo.getUsersSession(userId);

        try {

            moveEntity = objectMapper.readValue(requestMessage.getPayload(), MoveEntity.class);
            moveEntity.setUserId(gameSession.getUserInGameId(userId));

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        String msg = moveEntity.toJson("*");
        log.info("Message " + msg);
        SendMessageToSession(gameSession.getId(), RequestMessage.builder().type(GameActionType.GAMEACTION).payload(msg).build().toJson());

//        System.out.println(moveEntity + " sessionId: " +  userId);
    }

}
