package az.evilcastle.turnbased.services;

import az.evilcastle.turnbased.Repo.GameSessionOnMemoryRepo;
import az.evilcastle.turnbased.entities.RequestMessage;
import az.evilcastle.turnbased.entities.redis.GameSession;
import az.evilcastle.turnbased.services.interfaces.GameSessionService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.concurrent.ConcurrentMap;

@Service
public class GameSessionServiceImpl implements GameSessionService {

    private final GameSessionOnMemoryRepo gameSessionOnMemoryRepo = new GameSessionOnMemoryRepo();

    @PostConstruct
    private void init(){
        gameSessionOnMemoryRepo.setGameSessionService(this);
    }

    @Override
    public void join(WebSocketSession webSocketSession, RequestMessage requestMessage) {

        gameSessionOnMemoryRepo.addGameSession(webSocketSession, requestMessage, this);
    }

    @Override
    public void distributeRequest(WebSocketSession webSocketSession, RequestMessage requestMessage) {


        switch (requestMessage.getType()) {
            case "CONNECTION":
                join(webSocketSession, requestMessage);
                break;
            case "GAMEACTION":

                break;
            default:
                //TODO exception handler for WRONG REQUEST TYPE
                System.out.println("WRONG REQUEST TYPE");
        }
    }

    @Override
    public ConcurrentMap<Long, GameSession> getAllGameSession() {
        return gameSessionOnMemoryRepo.getAllActiveGameSessions();
    }

    @Override
    public GameSession getGameSession(Long id) {
        return gameSessionOnMemoryRepo.getGameSession(id);
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
}
