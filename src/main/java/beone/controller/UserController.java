package beone.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import beone.entity.User;
import beone.service.UserService;
import beone.utils.MD5Util;
import beone.utils.ResultVOUtil;
import beone.vo.ResultVO;

@Controller
@EnableAutoConfiguration
public class UserController {

	@Autowired
	private UserService userServiceImpl;
	
	@RequestMapping(value = "/user/add")
	@ResponseBody
	public ResultVO<Object> addUser(String userName,String password){
		
		User exist = userServiceImpl.fineByUserName(userName);
		if(exist!=null){
			return ResultVOUtil.fail("用户名已存在", null);
		}
		
		 User user = new User(userName,password,new Date());
		 System.out.println(new Date());
		 //获取当前时间作为注册时间
		 user.setPassword(MD5Util.getMd5(user.getPassword(),user.getUserName()));
		boolean isRegisterSuccess = userServiceImpl.addUser(user); 
		if(isRegisterSuccess)
			return ResultVOUtil.success("用户添加成功", user);
		else
			return ResultVOUtil.fail("用户添加失败", null);
	}
	
	
	/**
	 * 登录方法
	 * @param userName
	 * @param password
	 * @return
	 */
	@RequestMapping(value = "/user/login",method = RequestMethod.POST)
	@ResponseBody
	public ResultVO<Object> login(String userName,String password){
		System.out.println(userName+password);
		
		User user = userServiceImpl.fineByUserName(userName);
		if(user==null) {
			return ResultVOUtil.fail("用户名不存在", null);
		}
		if(user.getPassword().equals(MD5Util.getMd5(password,userName)))
			return ResultVOUtil.success("登陆成功", user);
		else
			return ResultVOUtil.fail("用户名或密码错误", null);
	}
	
	/**
	 * 查询用户列表
	 * @return
	 */
	@RequestMapping(value = "/user/list",method = RequestMethod.GET)
	@ResponseBody
	public ResultVO<Object> userList(){
		List<User> userList = userServiceImpl.fineAllUser();
		if(userList.isEmpty())
			return ResultVOUtil.success("用户列表为空",userList);
		else
			return ResultVOUtil.success("查询成功", userList);
		
	}
	
	/**
	 * 删除用户
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/user/delete",method = RequestMethod.GET)
	@ResponseBody
	public ResultVO<Object> delete(Long userId){
	
		boolean isDel = userServiceImpl.deleteUserById(userId);
		if(isDel)
			return ResultVOUtil.success("删除成功",null);
		else
			return ResultVOUtil.fail("删除失败","用户不存在");
	}
	
	
	

}
