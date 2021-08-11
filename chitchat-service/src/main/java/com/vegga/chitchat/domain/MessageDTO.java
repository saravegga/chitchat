package com.vegga.chitchat.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MessageDTO {

  private String content;
  private String userId;
  private String userName;
  private LocalDateTime sendDate;
}
