package net.joshuabrandes.spring_vthreads.logger;

import lombok.extern.slf4j.Slf4j;
import org.springframework.modulith.events.ApplicationModuleListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class LogHub {

    int count = 0;

    @ApplicationModuleListener
    public void log(String message) {
        log.info("Message: {} (log count: {})", message, count++);
    }
}
