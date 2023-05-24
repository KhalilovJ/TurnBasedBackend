package az.evilcastle.turnbased.config;

import az.evilcastle.turnbased.Repo.GameSessionOnMemoryRepo;
import az.evilcastle.turnbased.handlers.ServerWebSocketHandler;
import az.evilcastle.turnbased.services.GameSessionServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.*;

@Configuration
@EnableWebSocket
public class ServerWebSocketConfig implements WebSocketConfigurer {

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(webSocketHandler(), "/rubicon");
    }

    @Bean
    public WebSocketHandler webSocketHandler() {
        return new ServerWebSocketHandler(new GameSessionServiceImpl(new GameSessionOnMemoryRepo()));
    }
}