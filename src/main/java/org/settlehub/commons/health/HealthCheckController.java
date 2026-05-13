package org.settlehub.commons.health;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.springframework.beans.factory.annotation.Value;

@RestController
@RequestMapping("/health")
public class HealthCheckController {

    @Value("${spring.application.name:settlehub-service}")
    private String serviceName;

    @GetMapping(value = {"", "/"})
    public ResponseEntity<?> checkHealth(
        @RequestParam(value = "json", required = false, defaultValue = "false") boolean isJson
    ) {
        if (isJson) {
            return ResponseEntity.ok(Map.of(
                "status", "UP",
                "service", serviceName
            ));
        } else {
            String response = 
                "status: \tUP\n" +
                "service:\t"+serviceName+"\n";

            return ResponseEntity.ok(response);
        }
    }

    @GetMapping(value = "/heartbeat", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter streamHeartbeat() {
        SseEmitter emitter = new SseEmitter(Long.MAX_VALUE);
        ExecutorService executor = Executors.newSingleThreadExecutor();

        executor.execute(() -> {
            try {
                while (true) {
                    emitter.send(SseEmitter.event()
                            .name("ping")
                            .data(serviceName + " is ALIVE, latency: " + System.currentTimeMillis() + " ms"));
                    
                    Thread.sleep(5000);
                }
            } catch (IOException | InterruptedException ex) {
                emitter.completeWithError(ex);
            } finally {
                executor.shutdown();
            }
        });

        return emitter;
    }

}
