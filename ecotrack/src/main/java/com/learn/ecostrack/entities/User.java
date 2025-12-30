package com.learn.ecostrack.entities;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;
	@Column(nullable = false,length = 26)
	private String name;
	@Column(nullable = false,unique = true)
	private String email;
	@Column(nullable = false)
	private String password;
	@Column(nullable = false,unique = true)
	private String phoneNo;
	private int age;
	@OneToMany(mappedBy = "user")
	@JsonBackReference
	private List<RecycleRequest> recycleRequests;
	@ManyToOne 
	@JsonManagedReference
	private Role role;
	@OneToMany(mappedBy = "user")
	@JsonBackReference
	private List<Enrollements> enrollements = new ArrayList<Enrollements>();
}
