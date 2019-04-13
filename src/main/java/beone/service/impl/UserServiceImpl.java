package beone.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import beone.dao.UserDao;
import beone.entity.User;
import beone.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;

	@Override
	public User fineByUserName(String userName) {
		
		return userDao.findByUserName(userName);
	}

	@Override
	public boolean addUser(User user) {
		User save = userDao.save(user);
		if(save!=null)
			return true;
		return false;
	}

	@Override
	public List<User> fineAllUser() {
		List<User> userList = userDao.findAll();
		return userList;
	}

	@Override
	public boolean deleteUserById(Long userId) {
		try{
			userDao.deleteById(userId);
			return true;
		}catch (Exception e) {
			return false;
		}
	}



}
