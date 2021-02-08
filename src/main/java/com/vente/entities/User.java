package com.vente.entities;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Table(name="user")
@Data
public class User {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="user_id")
	private Long id;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	@ManyToMany(fetch=FetchType.EAGER , cascade=CascadeType.ALL)
    @JoinTable(
    		name="users_roles",
    		joinColumns=@JoinColumn(
    				name="user_id" , referencedColumnName="user_id"),
    		inverseJoinColumns=@JoinColumn(
            		name="role_id" , referencedColumnName="role_id"))
            
	private Collection<Role> roles;
	public User(String firstName, String lastName, String email, String password, Collection<Role> roles) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.roles = roles;
	}
	

}
