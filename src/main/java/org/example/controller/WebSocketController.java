package org.example.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.util.BeautyLog;
import org.example.websocket.Handler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.user.SimpUser;
import org.springframework.messaging.simp.user.SimpUserRegistry;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.socket.WebSocketSession;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import static org.example.websocket.Handler.activeConnections;


@RestController
@CrossOrigin(origins = {"http://192.168.1.5:4200","http://127.0.0.1:4200","*","http://localhost:4200"})
public class WebSocketController {

    private List<String> onlineUsers = new ArrayList<String>();
    private List<Object> onlineUsersObject = new ArrayList<Object>();
    private ObjectMapper objectMapper;
    @GetMapping("/users")
    public List<String> s(){
        BeautyLog.println( activeConnections.values());
        BeautyLog.println(activeConnections.keySet());

//        BeautyLog.println(onlineUsersObject.get(0));

        onlineUsers = Handler.customers;

        return onlineUsers;
    }



}
