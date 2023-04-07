package az.evilcastle.turnbased.controllers;

import az.evilcastle.turnbased.entities.BasicMessage;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.NestedExceptionUtils;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import java.time.LocalDateTime;

@Controller
@Log4j2
class BasicWebSocketController {

    @MessageExceptionHandler
    @SendTo("/topic/errors")
    public String handleException(IllegalArgumentException e) {
        var message = ("an exception occurred! " + NestedExceptionUtils.getMostSpecificCause(e));
        System.out.println(message);
        return message;
    }

    @MessageMapping("/chat")
    @SendTo("/topic/greetings")
    public BasicMessage greet(BasicMessage request) throws Exception {
//        Assert.isTrue(Character.isUpperCase(request.getName().charAt(0)), () -> "the name must start with a capital letter!");
//        Thread.sleep(1_000);
        log.info("request: " + request);
        return BasicMessage.builder()
                .content(request.getContent())
                .name(request.getName())
                .msgTime(LocalDateTime.now().toString())
                    .build();
    }


}
