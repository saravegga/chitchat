package com.vegga.chitchat.dao.repository;

import com.vegga.chitchat.dao.model.ChatRoom;
import com.vegga.chitchat.dao.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

  List<Message> findByChatRoomAndRemovedByIsNull(ChatRoom chatRoom);
}
