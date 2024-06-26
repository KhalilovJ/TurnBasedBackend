package az.evilcastle.turnbased.controllers;

import az.evilcastle.turnbased.models.redis.GameSession;
import az.evilcastle.turnbased.services.interfaces.GameSessionService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class GameSessionsController {

    private final GameSessionService gameSessionService;

    @GetMapping("/GameSessions")
    List<GameSession> getAllGameSessions() {

        return gameSessionService.getAllGameSession().values().stream().toList();
    }

    @GetMapping("/GameSessions/{id}")
    GameSession getGameSession(@PathVariable String id) {
        return gameSessionService.getGameSession(id);
    }

}
