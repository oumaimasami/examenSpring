package com.vente.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name="role")
public class Role {
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  @Column(name="role_id")
  private Long id;
  public Role(String name) {
	super();
	this.name = name;
}
private String name;
}
