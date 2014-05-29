package DAOs;

import java.util.ArrayList;
import java.util.List;

import models.Group;
import models.Message;

public interface GroupDAO {

	public Group createGroup (Group group);
	
	public List<Group> getAllGroups ();
	
	public Group getGrouById(long id);
	
	public ArrayList<Message> getAllMessages (long groupId);
	
}
