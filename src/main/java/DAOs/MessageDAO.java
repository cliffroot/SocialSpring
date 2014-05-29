package DAOs;

import java.util.List;

import models.Message;

public interface MessageDAO {

	public Message createMessage (Message message);
	
	public Message editMessage (Message message);
	
	public Message findMessageById (long ID);
	
	public List<Message> getAllUserMessages (long UserID);
}
