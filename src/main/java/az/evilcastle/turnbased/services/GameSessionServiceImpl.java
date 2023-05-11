package az.evilcastle.turnbased.services;

import az.evilcastle.turnbased.Repo.GameSessionOnMemoryRepo;
import az.evilcastle.turnbased.entities.RequestMessage;
import az.evilcastle.turnbased.entities.redis.GameSession;
import az.evilcastle.turnbased.services.interfaces.GameSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketSession;

import java.util.concurrent.ConcurrentMap;

@Service
public class GameSessionServiceImpl implements GameSessionService {

    private final GameSessionOnMemoryRepo gameSessionOnMemoryRepo = new GameSessionOnMemoryRepo();

    @Override
    public void join(WebSocketSession webSocketSession, RequestMessage requestMessage) {

        gameSessionOnMemoryRepo.addGameSession(webSocketSession, requestMessage);
    }

    @Override
    public void distributeRequest(WebSocketSession webSocketSession, RequestMessage requestMessage) {

        switch (requestMessage.getType()) {
            case "join":
                join(webSocketSession, requestMessage);
                break;
            case "move":

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
    public ConcurrentMap<String, GameSession> getAllActivePlayers() {
        return gameSessionOnMemoryRepo.getAllActivePlayers();
    }
}
