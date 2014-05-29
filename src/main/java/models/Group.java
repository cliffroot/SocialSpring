package models;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

@Table(name="Community")
@Entity
public class Group {
	@Column(name="name")
	private String name;
	@Column(name="description")
	String description;
	
	@ManyToMany (fetch = FetchType.EAGER)
	@JoinTable(name="UserInCommunity", joinColumns =  @JoinColumn(name="groupId", referencedColumnName="id"),
								inverseJoinColumns = @JoinColumn(name="userId", referencedColumnName="id"))  
	private Set<User> users;
	
	@OneToMany(mappedBy="group", fetch = FetchType.EAGER)
	private List<Message> messages;
	
	@Column(name="id")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Group (String name, String description) {
		this.name = name;
		this.description = description;
	}
	
	public Group () {
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public String toString () {
		return id + " " + name + " " + description;
	}
	
}
