package com.vegga.chitchat.dao.model;

import lombok.Data;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class UserType {

  @Id private Long id;
  private String name;
  private String description;
}
