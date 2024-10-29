package net.joshuabrandes.spring_vthreads.delay;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@RestController
@ResponseBody
@RequiredArgsConstructor
class EndpointController {

    final ApplicationEventPublisher eventPublisher;

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

    @PostMapping(path = "/sort", consumes = "application/json")
    public ResponseEntity<List<Integer>> sort(@RequestBody List<Integer> numbers) {
        eventPublisher.publishEvent("Sorting numbers...");

        return ResponseEntity.ok()
                .body(SortingService.sort(numbers, true));
    }
}

class SortingService {

    private SortingService() {
    }

    public static List<Integer> sort(List<Integer> numbers, boolean delay) {
        var sorted = numbers.stream()
                .sorted()
                .toList();

        if (delay) {
            // randomize list and sort again
            for (int i = 0; i < 500; i++) {
                Collections.shuffle(sorted);
                sorted = sorted.stream()
                        .sorted()
                        .toList();
            }
        }

        return sorted;
    }
}
