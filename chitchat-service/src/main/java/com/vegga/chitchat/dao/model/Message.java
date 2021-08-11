package com.vegga.chitchat.dao.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table
@Data
public class Message {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String content;

  private LocalDateTime sendDate;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "chatRoomId")
  private ChatRoom chatRoom;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "userId")
  private User user;

  @ManyToOne(fetch = FetchType.LAZY)
  private User removedBy;
}
