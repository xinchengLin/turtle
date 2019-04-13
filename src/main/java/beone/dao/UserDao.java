package beone.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import beone.entity.User;

@Repository
public interface UserDao extends JpaRepository<User,Long>{

	 @Query(value = "select u.* from user u where u.user_name=?1", nativeQuery = true)
	User findByUserName(String userName);
	 

}
