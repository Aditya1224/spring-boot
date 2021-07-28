package com.rest.webservices.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.omg.CORBA.UNKNOWN;
import org.springframework.stereotype.Component;

/*Dao service. One of the important things is we would want this to be managed by spring.
 *  So how do I do that? By putting at component on it. So @Component
 *  This is something which would be talking to the database. Actually, I could have put at repository on it as well because it talks to the database. 
 *  here we are using a simple array list to store the list of users.
 */
@Component
public class UserDaoService {

	private static List<User> userList = new ArrayList<User>();
	private static int userCount = 6;

	static {
		userList.add(new User(1, "Ram", new Date()));
		userList.add(new User(2, "Shayam", new Date()));
		userList.add(new User(3, "Kishna", new Date()));
		userList.add(new User(4, "Shiva", new Date()));
		userList.add(new User(5, "Ganesha", new Date()));
	}

	public List<User> findAll() {
		return userList;
	}

	public User saveUser(User user) {
		if (!Optional.ofNullable(user.getId()).isPresent()) {
			user.setId(userCount++);
		}
		userList.add(user);
		return user;
	}

	public User findOne(int id) {
		return userList.stream().filter(user -> user.getId() == id).findAny().orElse(null);
	}

	public User deleteById(int id) {
		return userList.stream().filter(user -> user.getId() == id)
				.findFirst()
				.orElseThrow(() -> new UserNotFoundException("id-"+id));
	}
}
