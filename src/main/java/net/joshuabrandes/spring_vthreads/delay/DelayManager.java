package net.joshuabrandes.spring_vthreads.delay;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@RestController
@ResponseBody
class DelayController {

    final WebClient webClient = WebClient.create();

    @PostMapping("/delay")
    public ResponseEntity<String> delay() {
        var url = "https://httpstat.us/200?sleep=5000";

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
