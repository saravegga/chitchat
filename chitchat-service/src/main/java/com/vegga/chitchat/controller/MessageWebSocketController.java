package com.vegga.chitchat.controller;

import com.vegga.chitchat.domain.MessageDTO;
import com.vegga.chitchat.service.ChatRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.HtmlUtils;

@RestController
public class MessageWebSocketController {

  @Autowired private ChatRoomService chatRoomService;

  @MessageMapping("/message")
  //@MessageMapping("/chat/{sourceUrl}")
  @SendTo("/topic/messages")
  //@SendTo("/topic/{sourceUrl}")
  public MessageDTO chatting(/*@DestinationVariable String sourceUrl,*/ MessageDTO message) {

    MessageDTO chatMessageDTO = new MessageDTO();
    chatMessageDTO.setContent("Hello, " + message.getContent() + "!");

    //call db to save msg
    //front needs to send user
    //front needs to firstly call endpoint to get/save chatroom
    //this endpoint will receive the sourceUrl param

    //front needs to look like a chatroom :)

    return chatMessageDTO;
  }
}
