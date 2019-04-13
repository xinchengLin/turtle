package beone.service;

import java.util.List;

import beone.entity.User;

public interface UserService {

	boolean addUser(User user);

	List<User> fineAllUser();

	boolean deleteUserById(Long userId);

	User fineByUserName(String userName);


}
