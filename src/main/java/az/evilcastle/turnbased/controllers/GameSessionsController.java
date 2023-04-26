package az.evilcastle.turnbased.controllers;

import az.evilcastle.turnbased.entities.redis.GameSession;
import az.evilcastle.turnbased.services.interfaces.GameSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.ConcurrentMap;

@RestController
public class GameSessionsController {

    private final GameSessionService gameSessionService;

    public GameSessionsController(GameSessionService gameSessionService) {
        this.gameSessionService = gameSessionService;
    }

    @GetMapping("/GameSessions")
    List<GameSession> getAllGameSessions() {

        return gameSessionService.getAllGameSession().values().stream().toList();
    }

    @GetMapping("/GameSessions/{id}")
    GameSession getGameSession(@PathVariable String id) {
        return gameSessionService.getGameSession(Long.parseLong(id));
    }
}
