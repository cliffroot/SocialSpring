package DAOs;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import models.User;

public class UserDAOJPA implements UserDAO {
	
	@PersistenceContext
    private EntityManager em;

	
	@Override
	@Transactional
	public User createUser(User user) {
		em.persist(user);
		return user;
	}

	@Override
	public User findUserById(long ID) {
		return em.find(User.class, ID);
	}

	@Override
	@Transactional
	public User updateUser(User user) {
		return em.merge(user);
	}

	@Override
	public void deleteUser(long ID) {
		em.remove(findUserById(ID));
	}

	@Override
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User validUserCredentials(String name, String password) {
		try {
			return (User) em.createQuery(
				    "SELECT u FROM User u WHERE u.name LIKE :name AND u.password LIKE :password")
				    .setParameter("name", name).setParameter("password", password).getSingleResult();
		} catch (Exception ex) {
			return null;
		}
	}
	
	@Override
	@Transactional
	public void subscribe(long userId, long groupId) {
		em.createNativeQuery("INSERT INTO UserInCommunity(userId, groupId) values(?,?)").setParameter(1, userId).setParameter(2, groupId).executeUpdate();
		
	}

	@Override
	@Transactional
	public void unsubscribe(long userId, long groupId) {
		em.createNativeQuery("DELETE FROM UserInCommunity where userId = ? and groupId = ?").setParameter(1, userId).setParameter(2, groupId).executeUpdate();
	}

}
