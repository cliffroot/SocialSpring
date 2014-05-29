package models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class User {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
	private long ID;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "surname")
	private String surname;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "photoUrl")
	private String photoUrl;
	
	@OneToMany(mappedBy="user", fetch = FetchType.EAGER)
	private List <Message> messages;
	
	public User(String name, String surname, String password, String photoUrl) {
		this.name = name;
		this.surname = surname; 
		this.password = password;
		this.photoUrl = photoUrl;
	}
	
	public User () {
		
	}
	
	public String getPhotoUrl() {
		return photoUrl;
	}

	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}
	
	

	public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}

	public String getPassword () {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public long getID() {
		return ID;
	}

	public void setID(long iD) {
		ID = iD;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	public String toString () {
		return "User: " + name + " " + surname + " " + password +  " " + photoUrl;
	}
	
	@Override
	public boolean equals(Object o){
		System.out.println("Overriden Equals for user called!");
		if(o instanceof User){
			User toCompare = (User) o;
			return new Long(this.ID).equals(toCompare.getID());
		  }
		  return false;
	}
	
	@Override
	public int hashCode() {
	    return new Long(ID).hashCode();
	}
}
