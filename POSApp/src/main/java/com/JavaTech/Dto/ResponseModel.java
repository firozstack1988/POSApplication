package com.JavaTech.Dto;

import com.JavaTech.Entity.UserDetail;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor()
@NoArgsConstructor
public class ResponseModel {
 
	private String success;
	private String errorMsg;
	private Object content;
}
