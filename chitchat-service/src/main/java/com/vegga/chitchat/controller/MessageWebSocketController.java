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

  @MessageMapping("/chat/abc")
  //@MessageMapping("/chat/{sourceUrl}")
  @SendTo("/topic/abc")
  //@SendTo("/topic/{sourceUrl}")
  public MessageDTO greeting(/*@DestinationVariable String sourceUrl,*/ MessageDTO messageDto)
      throws Exception {

    //Thread.sleep(1000); // simulated delay

    MessageDTO chatMessageDTO = new MessageDTO();
    chatMessageDTO.setContent("Hello, " + HtmlUtils.htmlEscape(messageDto.getUserName()) + "!");

    return chatMessageDTO;
  }
}
