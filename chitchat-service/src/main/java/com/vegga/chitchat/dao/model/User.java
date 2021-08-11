package com.vegga.chitchat.dao.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table
@Data
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  @ManyToOne
  @JoinColumn(name = "typeId")
  private UserType type;

  @ManyToOne
  @JoinColumn(name = "statusId")
  private UserStatus status;

  private LocalDateTime statusDate;

  @ManyToOne(fetch = FetchType.LAZY)
  private User changedBy;
}
