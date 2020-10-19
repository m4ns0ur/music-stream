package org.example.demo;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author mansour
 */
@RestController
public class DemoController {

    @GetMapping("/music")
    public ResponseEntity<StreamingResponseBody> music() throws URISyntaxException {
        final URL res = getClass().getClassLoader().getResource("demo.mp3");
        final File file = Paths.get(res.toURI()).toFile();
        final StreamingResponseBody responseBody = os -> Files.copy(file.toPath(), os);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=demo.mp3")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(responseBody);
    }

}
