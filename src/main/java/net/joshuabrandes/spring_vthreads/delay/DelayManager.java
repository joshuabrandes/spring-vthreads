package net.joshuabrandes.spring_vthreads.delay;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@ResponseBody
@RequiredArgsConstructor
class DelayController {

    private final ApplicationEventPublisher eventPublisher;

    @PostMapping("/delay")
    public ResponseEntity<String> delay() {
        var url = "https://httpstat.us/200?sleep=5000";

        eventPublisher.publishEvent("Delaying for 5 seconds...");

        return ResponseEntity.ok()
                .body(new RestTemplate()
                        .getForEntity(url, String.class)
                        .getBody());

        /*
        return ResponseEntity.ok()
                .body(webClient.get()
                        .uri(url)
                        .header("Accept", "application/json")
                        .retrieve()
                        .bodyToMono(String.class)
                        .block());
         */
    }
}
