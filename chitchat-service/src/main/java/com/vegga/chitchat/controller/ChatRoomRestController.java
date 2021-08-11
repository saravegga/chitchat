package com.vegga.chitchat.controller;

import com.vegga.chitchat.domain.ChatRoomDTO;
import com.vegga.chitchat.service.ChatRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatRoomRestController {

  @Autowired private ChatRoomService chatRoomService;

  @GetMapping("/chatroom/{sourceUrl}")
  public ChatRoomDTO getOrCreateChatRoom(@PathVariable String sourceUrl) {
    return chatRoomService.getOrCreateChatRoom(sourceUrl);
  }
}
