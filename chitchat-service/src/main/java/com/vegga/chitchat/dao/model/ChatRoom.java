package com.vegga.chitchat.dao.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table
@Data
public class ChatRoom {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String sourceUrl;
  private String generatedUrl;
  private LocalDateTime creationDate;
}
