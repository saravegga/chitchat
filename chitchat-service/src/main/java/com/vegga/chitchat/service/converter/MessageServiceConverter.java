package com.vegga.chitchat.service.converter;

import com.vegga.chitchat.dao.model.Message;
import com.vegga.chitchat.domain.MessageDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface MessageServiceConverter {

  @Mapping(target = "userId", source = "user.id")
  @Mapping(target = "userName", source = "user.name")
  List<MessageDTO> convert(List<Message> messages);
}
