package com.JavaTech;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.JavaTech.Entity.UserDetail;
import com.JavaTech.Repository.UserDetailRepo;
import com.JavaTech.Service.UserDetailService;

@SuppressWarnings("deprecation")
@SpringBootTest
class PosAppApplicationTests {
	
	@Autowired
	private UserDetailService userDetailService;
	
	@MockBean
	private UserDetailRepo userDetailRepo;

	@Test
	void contextLoads() {
	}
	
	@Test
	public void findAllUser() {
		UserDetail user1=UserDetail.build(null, "abc", "12", "abc@gmail.com", "01737080787", "BD", "ADMIN");
		UserDetail user2=UserDetail.build(null, "xyz", "12", "xyz@gmail.com", "01737102030", "BD", "USER");		
		Mockito.when(userDetailRepo.findAll()).thenReturn(Stream.of(user1,user2).collect(Collectors.toList()));
		assertEquals(2,userDetailService.findUserList().size());
	}

}
