package com.JavaTech.Dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor(staticName="build")
@NoArgsConstructor
public class UserRequest {
	@NotNull(message="Name should not be blank")
	private String userName;
	@Email(message="Invalid email")
	private String email;
	@Pattern(regexp="^\\d{10}$",message="Invalid mobile number")
	private String mobile;
	@NotBlank
	private String nationality;
}
