package az.evilcastle.turnbased.controllers;

import az.evilcastle.turnbased.entities.BasicMessage;
import az.evilcastle.turnbased.handlers.ServerWebSocketHandler;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class ConnectionController {

    @MessageMapping("/start")
    @SendTo("/topic/hello")
    public String handleMessage(@Payload String mess) {

        System.out.println("IN handleMessage");
        return "handleMessage: " + mess;
    }
}
