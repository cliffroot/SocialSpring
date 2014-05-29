package controllers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import models.Message;
import models.User;
import models.Group;

import org.apache.tomcat.util.http.fileupload.FileItemIterator;
import org.apache.tomcat.util.http.fileupload.FileItemStream;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import DAOs.GroupDAO;
import DAOs.MessageDAO;
import DAOs.UserDAO;


@Controller
public class RestUserController {
	
	@Autowired
    private UserDAO userDAO;
	@Autowired 
	private GroupDAO groupDAO;
	@Autowired 
	private MessageDAO messageDAO;
	
	@Autowired
	private HttpServletRequest context;


	@RequestMapping(method = RequestMethod.POST, value = "/welcome")
	String getUser(@RequestParam String name, @RequestParam String password) {//
		User user = userDAO.validUserCredentials(name, password);
		if (user !=  null) {
			System.out.println(user);
			context.getSession().setAttribute("user", user);
			return "Welcome";
		}
		else {
			return "Signin";
		}
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/register")
	String register () {
		return "register";
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/login")
	String login () {	
		User user = (User)context.getSession().getAttribute("user");
		if (user == null) {
			return "Signin";
		}
		else {
			return getUser(user.getName(), user.getPassword());
		}
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/signup") 
	String signup () {
		context.getSession().setAttribute("user", userDAO.createUser(parseEnctype(context)));
		return "Welcome";
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/account")
	String account () {
		return "Account";
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/logout") 
	String logout () {
		context.getSession().removeAttribute("user");
		return "Signin";
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/changephoto")
	String changePhoto () {
		String newPhoto = parsePhoto(context);
		User user = ((User) context.getSession().getAttribute("user"));
		user.setPhotoUrl(newPhoto);
		userDAO.updateUser(user);
		return "Account";
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/changepassword")
	String changePassword (@RequestParam String oldPassword, @RequestParam String newPassword) {
		User user = ((User) context.getSession().getAttribute("user"));
		if (user.getPassword().equals(oldPassword))
		if (user != null) {
			System.out.println("Set new password: " + newPassword);
			user.setPassword(newPassword);
			userDAO.updateUser(user);
		}
		return "Account";
	}
	
	@RequestMapping(method = RequestMethod.GET, value ="/groups") 
	String getAllGroups () {
		List<Group> groups = groupDAO.getAllGroups();
		context.setAttribute("groups", groups);
		return "Groups";
	}
	
	@RequestMapping(method = RequestMethod.POST, value ="/creategroup")
	String createGroup (@RequestParam String name, @RequestParam String description) {
		Group group = new Group(name, description);
		groupDAO.createGroup(group);
		List<Group> groups = groupDAO.getAllGroups();
		context.setAttribute("groups", groups);
		return "Groups";
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/showgroup") 
	String showGroup (@RequestParam long id) {
		Group group = groupDAO.getGrouById(id);
		System.out.println("Group to display: " + group);
		context.setAttribute("group", group);
		context.setAttribute("messages", removeDuplicates(group.getMessages()));
		context.setAttribute("users", group.getUsers());
		System.out.println("Messages: " + removeDuplicates(group.getMessages()));
		System.out.println("Users: " + group.getUsers());
		User user = ((User) context.getSession().getAttribute("user"));
		System.out.println("I'm in the group – " + group.getUsers().contains(user));
		context.setAttribute("subscribed", group.getUsers().contains(user));
		return "ShowGroup";
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/addmessage") 
	String addMessage (@RequestParam long groupId, @RequestParam String message) {
		User user = ((User) context.getSession().getAttribute("user"));
		System.out.println("User: " + user + " has written " + message);
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("HH-mm-ss dd/MM/yyyy");
		String time = sdf.format(calendar.getTime());
		Group group = groupDAO.getGrouById(groupId);
		Message m = new Message(user,message,time,group);
		messageDAO.createMessage(m);
		return showGroup(groupId);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/subscribe") 
	String subscribe (@RequestParam long groupId) {
		User user = ((User) context.getSession().getAttribute("user"));
		userDAO.subscribe(user.getID(), groupId);
		return showGroup(groupId);
	}
	
	@RequestMapping(method = RequestMethod.POST, value ="/unsubscribe") 
	String unsubscribe (@RequestParam long groupId) {
		User user = ((User) context.getSession().getAttribute("user"));
		userDAO.unsubscribe(user.getID(), groupId);
		return showGroup(groupId);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/showuser") 
	String showUser (@RequestParam long userId) {
		User user = userDAO.findUserById(userId);
		context.setAttribute("messages", removeDuplicates(user.getMessages()));
		context.setAttribute("displayUser", user);
		return "User";
	}
	
	protected User parseEnctype(HttpServletRequest request) {
		String name     = null;
		String password = null;
		String surname  = null;
		String photo    = null;
		ServletFileUpload fileUpload = new ServletFileUpload();
		int i = 0;
		try {
		    FileItemIterator items = fileUpload.getItemIterator(request);
		    while (items.hasNext()) {
		    	FileItemStream item = items.next();
		    	if (item.isFormField()) {
		    		InputStream is = item.openStream();
		    		byte [] buffer = new byte[is.available()];
		    		is.read(buffer);
		    		if (i == 0) {
		    			name = new String(buffer);
		    			i++;
		    		}
		    		else if (i == 2) {
		    			password = new String (buffer);
		    			i++;
		    		}
		    		else {
		    			surname = new String(buffer);
		    			i++;
		    		}
		    	}
		    	else {
			    	System.out.println(item.getName());
			    	System.out.println(item.openStream());
			    	photo = item.getName();
			    	System.out.println("Photo: " + photo);
			    	InputStream is = item.openStream();
			    	byte [] buffer = new byte [1024];
			    	if (!(new File(pathToDownload).exists()))
			    			new File(pathToDownload).mkdirs();
			    	OutputStream os = new FileOutputStream(new File(pathToDownload + item.getName()));
			    	int read = 0;
			    	while ((read = is.read(buffer)) != -1) {
			            os.write(buffer, 0, read);
			    	}
			    	os.close();
		    	}
		    }
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		User user = new User(name, surname, password, photo);
		System.out.println(user);
		return user;
	}
	
	final String pathToDownload = "/Users/cliffroot/Documents/workspace/Social/WebContent/images/";
	protected String parsePhoto(HttpServletRequest request) {
			String photo = null;
			ServletFileUpload fileUpload = new ServletFileUpload();
			try {
			    FileItemIterator items = fileUpload.getItemIterator(request);
			    while (items.hasNext()) {
			    	FileItemStream item = items.next();
			    	System.out.println("ITEM UPLOAD: "  + item.getName());
			    	System.out.println(item.openStream());
			    	photo = item.getName();
			    	InputStream is = item.openStream();
			    	byte [] buffer = new byte [1024];
			    	OutputStream os = new FileOutputStream(new File(pathToDownload + item.getName()));
			    	int read = 0;
			    	while ((read = is.read(buffer)) != -1) {
			            os.write(buffer, 0, read);
			    	}
			    	os.close();
			    }
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			return photo;
	}
	
	protected List<Message> removeDuplicates (List<Message> list) {
		Set setItems = new LinkedHashSet(list);
		list.clear();
		list.addAll(setItems);
		return list;
	}
	

}
