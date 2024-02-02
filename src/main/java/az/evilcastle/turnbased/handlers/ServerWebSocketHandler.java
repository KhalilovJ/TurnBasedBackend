package az.evilcastle.turnbased.handlers;

import az.evilcastle.turnbased.models.RequestMessage;
import az.evilcastle.turnbased.services.interfaces.GameSessionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.SubProtocolCapable;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import org.springframework.web.util.HtmlUtils;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

@Component
@Slf4j
public class ServerWebSocketHandler extends TextWebSocketHandler implements SubProtocolCapable {

    private final Set<WebSocketSession> webSocketSessions = new CopyOnWriteArraySet<>();
    private final ObjectMapper objectMapper = new ObjectMapper();

    GameSessionService gameSessionService;

    public ServerWebSocketHandler(GameSessionService gameSessionService) {
        this.gameSessionService = gameSessionService;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        log.info("Server connection opened");
        webSocketSessions.add(session);

        TextMessage message = new TextMessage("one-time message from server");
        log.info("Server sends: {}", message);
        session.sendMessage(message);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        log.info("Server connection closed: {}", status);

        gameSessionService.removePlayer(session);
        webSocketSessions.remove(session);
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String request = message.getPayload();
        log.info("Server received: {}", request);

        RequestMessage requestMessage = objectMapper.readValue(request, RequestMessage.class);

        gameSessionService.distributeRequest(session, requestMessage);

//        String response = String.format("response from server to '%s'", HtmlUtils.htmlEscape(request));
        String response = String.format("response from server to '%s'", HtmlUtils.htmlEscape(requestMessage.toString()));
        log.info("Server sends: {}", response);
        session.sendMessage(new TextMessage(response));
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) {
        log.error("Server transport error: {}", exception.getMessage());
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
