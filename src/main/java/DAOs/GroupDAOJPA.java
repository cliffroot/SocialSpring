package DAOs;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import models.Group;
import models.Message;
import models.User;

public class GroupDAOJPA implements GroupDAO {

	@PersistenceContext
    private EntityManager em;
	
	@Override
	@Transactional
	public Group createGroup(Group group) {
		em.persist(group);
		return null;
	}

	@Override
	public List<Group> getAllGroups() {
		List<Group> groups = em.createQuery("select g from Group g").getResultList();
		return groups;
	}

	@Override
	@Transactional
	public Group getGrouById(long id) {
		System.out.println("Retrive group: " + id);
		return em.find(Group.class, id);
	}

	@Override
	public ArrayList<Message> getAllMessages(long groupId) {
		//List<Message> messages = em.createQuery("select m from Message )
		return null;
	}

}
