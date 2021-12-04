package com.cg.hims.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cg.hims.entities.UserTable;
import com.cg.hims.exceptions.UserNotFoundException;
import com.cg.hims.service.IUserServiceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


/*Author:Jeeta Makhal
 * Version: 1.0
 * Date:15/05/2021
 * Description: This is User Management Controller
 */

@RestController    //it will handlle both http protocal and method controller
@RequestMapping("/Authenticate")  //
@CrossOrigin(origins="http://localhost:4200") //it will change from postman to anguler
public class UserManagementController {
	@Autowired  //create  will be create object for us
	HttpSession session;
	@Autowired
	IUserServiceImpl userImpl;
	
	static final Logger LOGGER = LoggerFactory.getLogger(UserManagementController.class);
	
	/* Method: Authenticate
	 * Description: It allows to authenticate the user.
	 * @RequestMapping: It is used to map HTTP requests to handler methods of MVC and REST controllers.
	 * @RestController: It is used to create RESTful web services using MVC.
	 * @Autowired: It enables to inject object dependency implicitly.
    * Created By-Jeeta Makhal
    * Created Date - 15-05-2021 
    */
	
	
	@GetMapping("/Check") //featching data
	public String check() {
		return "ok";
	}
	
	/* Method: Check Operation
	 * Description: It allows to check the controller.
	 * @GetMapping: It is used to handle the HTTP GET requests matched with given URI expression.
	
    * Created By-Jeeta Makhal
    * Created Date - 15-05-2021 
    */
	
	@PostMapping("/addUser") //adding a data
	public UserTable addUser(@RequestBody UserTable user) {
		return userImpl.addNewUser(user);
	}
	
	/* Method: Add User
	 * Description: It allows to add new user.
	 * @RequestBody: It is used to bind HTTP request body with a domain object in method parameter or return type.
	 * @PostMapping: It is used to handle the HTTP POST requests matched with given URI expression.
	
    * Created By-Jeeta Makhal
    * Created Date - 15-05-2021 
    */
	
	
	@PostMapping("/Login")
	public UserTable login(@RequestBody RequestBinder obj) throws UserNotFoundException {
		LOGGER.info(obj.userName+","+obj.password);
		return userImpl.signIn(obj.userName, obj.password);
	}

	
	 /* Method: Login
	 * Description: It allows user to login.
	 * @RequestParam: extract query, parameters, form parameters, and even files from the request.
	 * @PostMapping: It is used to handle the HTTP POST requests matched with given URI expression.
	 * UserNotFoundException:When user fails to sign in/ log in user.
      
     * Created By-Jeeta Makhal
     * Created Date - 15-05-2021
     */
	 
	
	
	@GetMapping("/Logout")
	public ResponseEntity<String> logout() {
		session.invalidate();
		return new ResponseEntity<String>("logged out",HttpStatus.OK);
	}
	
	/* Method: Logout
	 * Description: It allows user to logout.
	 * @RequestBody: It is used to bind HTTP request body with a domain object in method parameter or return type.
	 * @GetMapping: It is used to handle the HTTP GET requests matched with given URI expression.
	
    * Created By-Jeeta Makhal
    * Created Date - 15-05-2021 
    */
}

class RequestBinder{
	String userName;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	String password;
	
}