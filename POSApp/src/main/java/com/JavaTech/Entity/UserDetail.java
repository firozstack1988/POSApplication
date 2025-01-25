package com.JavaTech.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor(staticName="build")
@NoArgsConstructor
@Table()
public class UserDetail {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String userName;
	private String email;
	private String mobile;
	private String nationality;
}
