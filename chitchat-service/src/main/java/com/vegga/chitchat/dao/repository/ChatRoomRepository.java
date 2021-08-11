package com.vegga.chitchat.dao.repository;

import com.vegga.chitchat.dao.model.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {

  Optional<ChatRoom> findBySourceUrl(String sourceUrl);
}
