package DAOs;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import models.Message;

public class MessageDAOJPA implements MessageDAO {
	
	@PersistenceContext
    private EntityManager em;

	@Override
	@Transactional
	public Message createMessage(Message message) {
		em.persist(message);
		return null;
	}

	@Override
	@Transactional
	public Message editMessage(Message message) {
		return em.merge(message);
	}

	@Transactional
	@Override
	public Message findMessageById(long ID) {
		return em.find(Message.class, ID);
	}

	@Override
	public List<Message> getAllUserMessages(long UserID) {
		// TODO Auto-generated method stub
		return null;
	}	

}
