package az.evilcastle.turnbased.controllers;

import jakarta.websocket.*;
import jakarta.websocket.server.ServerEndpoint;

import java.io.IOException;

@ServerEndpoint("/echo")
public class EchoEndpoint {

    @OnOpen
    public void onOpen(Session session) throws IOException {
        session.getBasicRemote().sendText("onOpen");
    }

    @OnMessage
    public String echo(String message) {
        return message + " (from your server)";
    }

    @OnError
    public void onError(Throwable t) {
        t.printStackTrace();
    }

    @OnClose
    public void onClose(Session session) {

    }
}