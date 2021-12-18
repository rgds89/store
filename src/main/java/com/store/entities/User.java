package com.store.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.*;

import javax.persistence.*;


@Data
@Entity
@Table(name = "TB_USER")
public class User implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String email;
	private String phone;
	private String password;

	@OneToMany(mappedBy = "client")
	private List<Order> orders = new ArrayList<>();

	public User(Long id, String name, String email, String phone, String password) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.password = password;
	}
}
