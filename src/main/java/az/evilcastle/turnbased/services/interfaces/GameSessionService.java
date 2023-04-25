package az.evilcastle.turnbased.services.interfaces;

import az.evilcastle.turnbased.entities.BasicMessage;
import az.evilcastle.turnbased.entities.RequestMessage;
import org.springframework.web.socket.WebSocketSession;

public interface GameSessionService {

    void join(WebSocketSession webSocketSession, RequestMessage requestMessage);

    void distributeRequest(WebSocketSession webSocketSession, RequestMessage requestMessage);

}
