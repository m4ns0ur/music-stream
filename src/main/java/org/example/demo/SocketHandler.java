package org.example.demo;

import org.springframework.web.socket.BinaryMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author mansour
 */
public class SocketHandler extends AbstractWebSocketHandler {

    @Override
    protected void handleBinaryMessage(WebSocketSession session, BinaryMessage message) throws IOException, URISyntaxException {
        final URL res = getClass().getClassLoader().getResource("demo.mp3");
        final File file = Paths.get(res.toURI()).toFile();
        session.sendMessage(new BinaryMessage(Files.readAllBytes(file.toPath())));
    }

}
