package works.keyka.usermngserver.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import works.keyka.usermngserver.controller.model.InputedUserParam;
import works.keyka.usermngserver.domain.UserData;
import works.keyka.usermngserver.repository.LogWriter;
import works.keyka.usermngserver.service.ServiceResult;




//↓SpringBootのControllerであることを示すアノテーション
@RestController
@RequestMapping("/UserController")
public class UserController {
	
	@GetMapping("/sayTom")
	public String getUser() {
		return "tom";
	}
	
	@PostMapping("/add")
	public String addUser(@RequestBody InputedUserParam body){
		final String addUserName = body.getName();
		final String addUserEmail = body.getEmail();
		
		System.out.println(addUserName);
		
		UserData newUserData = new UserData(addUserName,addUserEmail,true);
		
	    // SpringらしくServiceをDIする
	    ServiceResult result = addUserService.addUserExecute(newUserData);
		LogWriter.logWrite(result,getServletContext());
		//request.setAttribute("resultMessage", result.getResultMessage());
		//うん？ここからどう送ればいいんだ？
		
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