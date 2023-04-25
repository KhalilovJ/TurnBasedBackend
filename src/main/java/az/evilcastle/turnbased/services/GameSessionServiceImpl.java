package az.evilcastle.turnbased.services;

import az.evilcastle.turnbased.entities.BasicMessage;
import az.evilcastle.turnbased.entities.RequestMessage;
import az.evilcastle.turnbased.services.interfaces.GameSessionService;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketSession;

@Service
public class GameSessionServiceImpl implements GameSessionService {

    @Override
    public void join(WebSocketSession webSocketSession, RequestMessage requestMessage) {

        System.out.println("IN join |  webSocketSession: " + webSocketSession + " requestMessage: "+ requestMessage);
    }

    @Override
    public void distributeRequest(WebSocketSession webSocketSession, RequestMessage requestMessage) {

        switch (requestMessage.getType()) {
            case "join":
                join(webSocketSession, requestMessage);
                break;
            default:
                //TODO exception handler for WRONG REQUEST TYPE
                System.out.println("WRONG REQUEST TYPE");
        }
    }
}
