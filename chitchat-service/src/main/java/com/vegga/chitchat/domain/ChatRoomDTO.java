package com.vegga.chitchat.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChatRoomDTO {

  private Long id;
  private List<UserDTO> onlineUsers;
  private List<MessageDTO> messages;
}
