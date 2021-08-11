package com.vegga.chitchat.controller;

import com.vegga.chitchat.domain.MessageDTO;
import com.vegga.chitchat.service.ChatRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.HtmlUtils;

@RestController
public class TestWSController {

  @Autowired private ChatRoomService chatRoomService;

  @MessageMapping("/hello")
  @SendTo("/topic/greetings")
  public MessageDTO greeting(String message) throws Exception {

    Thread.sleep(1000); // simulated delay

    MessageDTO chatMessageDTO = new MessageDTO();
    chatMessageDTO.setContent("Hello, " + message + "!");

    return chatMessageDTO;
  }
}
