package com.revature.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "USERS")
@NamedQueries({
	@NamedQuery(name="findUserById", query="from User u where u.userId = :id"),
	@NamedQuery(name="findUserByCred", query="from User u where u.userUsername = :username and u.userPassword = :password")
})
@SequenceGenerator(name="user_seq", sequenceName="user_seq", allocationSize=1)
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="user_seq")
	@Column(name = "user_id")
	private int userId;

	@NonNull
	@Column(name = "user_username", unique = true, nullable = false)
	private String userUsername;
	
	@NonNull
	@Column(name = "user_password", nullable = false)
	private String userPassword;
	
	@NonNull
	@Column(name = "user_firstname", nullable = false)
	private String userFirstname;
	
	@NonNull
	@Column(name = "user_lastname", nullable = false)
	private String userLastname;
	
	@NonNull
	@Column(name = "user_email", unique = true, nullable = false)
	private String userEmail;
	
	@Column(name = "user_hearts")
	private String hearts;
	
	@Column(name = "user_role")
	private int userRole = 1;

}
