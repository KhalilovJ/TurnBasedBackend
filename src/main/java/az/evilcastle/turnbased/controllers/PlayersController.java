package az.evilcastle.turnbased.controllers;


import az.evilcastle.turnbased.services.interfaces.GameSessionService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class PlayersController {

    private final GameSessionService gameSessionService;

    @GetMapping("/activePlayers")
    List<String> getAllActivePlayers() {
        return gameSessionService.getAllActivePlayers().keySet().stream().toList();
    }
}
