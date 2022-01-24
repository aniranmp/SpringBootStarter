package org.example.websocket;
import org.example.Model.Message;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.example.Model.Users;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@Component
public class Handler extends TextWebSocketHandler {

    private Logger logger = LoggerFactory.getLogger(Users.class);
    public static HashMap<String, WebSocketSession> activeConnections = new HashMap<>();
    public static List<String> customers = new ArrayList<>();
    private ObjectMapper objectMapper;
    public Handler(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }


    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        logger.info("new Connection");
        logger.info(session.getUri().getPath());
        activeConnections.put(session.getUri().getPath(), session);
        logger.info(activeConnections.size() + "");
        customers.add(session.getUri().getPath());
        super.afterConnectionEstablished(session);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        logger.info("new Message");
        logger.info(message.toString());
        logger.info(message.getPayload());

        Message messageObject = objectMapper.readValue(message.getPayload(), Message.class);

        for (String sessionKey : activeConnections.keySet()) {
//            activeConnections.get(sessionKey).sendMessage(message);
//            activeConnections.get(sessionKey).sendMessage(new TextMessage(message.getPayload()));

            Message messageObjectSend = new Message();
            messageObjectSend.setUserName(messageObject.getUserName());
            messageObjectSend.setMessage(messageObject.getMessage());
//            activeConnections.get(sessionKey).sendMessage(new TextMessage(message.getPayload()));

            String messageObjectSendJson = objectMapper.writeValueAsString(messageObjectSend);
            activeConnections.get(sessionKey).sendMessage(new TextMessage(messageObjectSendJson));
        }
        super.handleTextMessage(session, message);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        logger.info("close Connection");
        activeConnections.remove(session.getUri().getPath());
        customers.remove(session.getUri().getPath());
        super.afterConnectionClosed(session, status);
    }


}