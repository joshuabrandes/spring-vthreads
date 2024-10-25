package net.joshuabrandes.spring_vthreads.delay;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@ResponseBody
class DelayController {

    @PostMapping("/delay")
    public ResponseEntity<String> delay() {
        var url = "https://httpstat.us/200?sleep=5000";

        return ResponseEntity.ok()
                .body(new RestTemplate()
                        .getForEntity(url, String.class)
                        .getBody());
    }
}
