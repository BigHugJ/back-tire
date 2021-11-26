package com.chatting;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

//@SpringBootTest dd
class ChattingApplicationTests {

	@Test
	void contextLoads() {
		List<User> currentOnlineUser = new ArrayList<User>();
		User user2 = new User();
		user2.setName("bunny");
		currentOnlineUser.add(user2);
		user2 = new User();
		user2.setName("tiger");
		currentOnlineUser.add(user2);
		
		User user = new User();
		user.setName("wolf");
		StringBuilder currentUserNames= new StringBuilder();
		currentOnlineUser.stream().forEach(u->currentUserNames.append(u.getName()+","));;
		currentOnlineUser.add(user);
		if (currentUserNames.length() > 1) {
			currentUserNames.deleteCharAt(currentUserNames.length()-1);
		}

		System.out.println(currentUserNames.toString());
	}

	@Test
	void arrayTest () {
		 List<User> uList = new ArrayList<User>();
		 
			User u1 = new User();
			u1.setId(1);
			u1.setName("hello");
			uList.add(u1);
			
			u1 = new User();
			u1.setId(2);
			u1.setName("hello2");
			uList.add(u1);
			
			u1 = new User();
			u1.setId(55);
			u1.setName("hello3");
			uList.add( u1);
			
			uList.stream().forEach(u->System.out.println(u.getId()));
			
			User u12 = new User();
			u12.setId(55);
			u12.setName("hello3");
			
			Iterator<User> iter = uList.iterator();
			while(iter.hasNext()) {
				User u = (User) iter.next();
				if (u.getName().equals(u12.getName())) {
					uList.remove(u);
					break;
				}
			}
			uList.stream().forEach(u->System.out.println(u.getId()));

	}
}
