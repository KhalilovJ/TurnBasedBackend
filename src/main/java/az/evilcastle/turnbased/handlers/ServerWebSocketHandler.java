package az.evilcastle.turnbased.handlers;

import az.evilcastle.turnbased.entities.RequestMessage;
import az.evilcastle.turnbased.entities.redis.GameSession;
import az.evilcastle.turnbased.services.interfaces.GameSessionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.ConcurrentReferenceHashMap;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.SubProtocolCapable;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import org.springframework.web.util.HtmlUtils;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArraySet;

@Component
public class ServerWebSocketHandler extends TextWebSocketHandler implements SubProtocolCapable {

    private static final Logger logger = LoggerFactory.getLogger(ServerWebSocketHandler.class);

    private final Set<WebSocketSession> webSocketSessions = new CopyOnWriteArraySet<>();
    private ConcurrentMap<Long, GameSession> gameSessions = new ConcurrentReferenceHashMap<>();;

    GameSessionService gameSessionService;

    public ServerWebSocketHandler(GameSessionService gameSessionService) {
        this.gameSessionService = gameSessionService;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        logger.info("Server connection opened");
        webSocketSessions.add(session);

        TextMessage message = new TextMessage("one-time message from server");
        logger.info("Server sends: {}", message);
        session.sendMessage(message);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        logger.info("Server connection closed: {}", status);
        webSocketSessions.remove(session);
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String request = message.getPayload();
        logger.info("Server received: {}", request);

        RequestMessage requestMessage = new ObjectMapper().readValue(request, RequestMessage.class);

        gameSessionService.distributeRequest(session, requestMessage);

        gameSessions = gameSessionService.getAllGameSession();

//        String response = String.format("response from server to '%s'", HtmlUtils.htmlEscape(request));
        String response = String.format("response from server to '%s'", HtmlUtils.htmlEscape(requestMessage.toString()));
        logger.info("Server sends: {}", response);
        session.sendMessage(new TextMessage(response));
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) {
        logger.info("Server transport error: {}", exception.getMessage());
    }

    @Override
    public List<String> getSubProtocols() {
        return Collections.singletonList("subprotocol.demo.websocket");
    }

    //    @Scheduled(fixedRate = 10000)
//    void sendPeriodicMessages() throws IOException {
//        for (WebSocketSession session : sessions) {
//            if (session.isOpen()) {
//                String broadcast = "server periodic message " + LocalDateTime.now();
//                logger.info("Server sends: {}", broadcast);
//                session.sendMessage(new TextMessage(broadcast));
//            }
//        }
//    }
}
