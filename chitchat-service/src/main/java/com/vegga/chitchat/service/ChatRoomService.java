package com.vegga.chitchat.service;

import com.vegga.chitchat.dao.model.ChatRoom;
import com.vegga.chitchat.dao.model.Message;
import com.vegga.chitchat.dao.repository.ChatRoomRepository;
import com.vegga.chitchat.dao.repository.MessageRepository;
import com.vegga.chitchat.domain.ChatRoomDTO;
import com.vegga.chitchat.domain.MessageDTO;
import com.vegga.chitchat.service.converter.MessageServiceConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.user.SimpUserRegistry;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ChatRoomService {

  private final SimpUserRegistry userRegistry;
  private final ChatRoomRepository chatRoomRepository;
  private final MessageRepository messageRepository;
  private final MessageServiceConverter messageServiceConverter;

  public ChatRoomService(
      SimpUserRegistry userRegistry,
      ChatRoomRepository chatRoomRepository,
      MessageRepository messageRepository,
      MessageServiceConverter messageServiceConverter) {
    this.userRegistry = userRegistry;
    this.chatRoomRepository = chatRoomRepository;
    this.messageRepository = messageRepository;
    this.messageServiceConverter = messageServiceConverter;
  }

  public ChatRoomDTO getOrCreateChatRoom(String sourceUrl) {

    Optional<ChatRoom> chatRoom = chatRoomRepository.findBySourceUrl(sourceUrl);

    if (chatRoom.isPresent()) {
      List<Message> messages = messageRepository.findByChatRoomAndRemovedByIsNull(chatRoom.get());

      List<MessageDTO> messageDTOList = messageServiceConverter.convert(messages);

      return ChatRoomDTO.builder()
          .id(chatRoom.get().getId())
          .messages(messageDTOList)
          .onlineUsers(null)
          .build();

    } else {
      ChatRoom chatRoomToSave = new ChatRoom();
      chatRoomToSave.setSourceUrl(sourceUrl);
      chatRoomToSave.setGeneratedUrl(sourceUrl);
      chatRoomRepository.save(chatRoomToSave);

      return ChatRoomDTO.builder().id(chatRoomToSave.getId()).onlineUsers(null).build();
    }
  }
}
