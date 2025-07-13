package works.keyka.usermngserver.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import works.keyka.usermngserver.controller.model.InputedUserParam;
import works.keyka.usermngserver.domain.UserData;
import works.keyka.usermngserver.service.AddUserService;
import works.keyka.usermngserver.service.ServiceResult;




//↓SpringBootのControllerであることを示すアノテーション
@RestController
@RequestMapping("/UserController")
public class UserController {
	
	private AddUserService addUserService;

	@GetMapping("/sayTom")
	public String getUser() {
		return "tom";
	}
	
	@PostMapping("/add")
	public String addUser(@RequestBody InputedUserParam body){
		final String addUserName = body.getName();
		final String addUserEmail = body.getEmail();
		final String addUserPassword = body.getPassword();
		
		System.out.println(addUserName);
		
		UserData newUserData = new UserData(null,addUserName,addUserEmail,addUserPassword, true);
		
	    // SpringらしくServiceをDIする
	    ServiceResult result = addUserService.addUserExecute(newUserData);
		
	    // メッセージをJSON形式で返す
	    String jsonResponse = "{\"message\": \"" + result.getResultMessage() + "\"}";
	    	return jsonResponse;
	}
	
	@PostMapping("/view")
	public void viewUser(){
		
	}
	
	@PostMapping("/delete")
	public void deleteUser(){
		
	}
}