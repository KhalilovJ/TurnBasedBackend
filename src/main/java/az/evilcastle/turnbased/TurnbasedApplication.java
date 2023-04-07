package az.evilcastle.turnbased;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TurnbasedApplication {
    public static void main(String[] args) {
        SpringApplication.run(TurnbasedApplication.class, args);
    }

}

