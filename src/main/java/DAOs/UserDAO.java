package DAOs;

import java.util.List;
import models.User;

public interface UserDAO {

	public User createUser (User user);
	
	public User findUserById (long ID);
	
	public User updateUser (User user);
	
	public void deleteUser (long ID);
	
	public List<User> getAllUsers ();
	
	public void subscribe (long userId, long groupId);
	public void unsubscribe (long userId, long groupId);
	
	public User validUserCredentials (String name, String password);
}
