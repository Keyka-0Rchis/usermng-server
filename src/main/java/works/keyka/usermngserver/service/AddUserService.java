package works.keyka.usermngserver.service;

import java.util.TreeMap;

import jakarta.servlet.ServletContext;

import works.keyka.usermngserver.domain.UserData;
import works.keyka.usermngserver.repository.IdGenerator;
import works.keyka.usermngserver.repository.UserFileManager;
import works.keyka.usermngserver.repository.UserMap;



public class AddUserService {

	private UserData userData;
	
	public AddUserService(UserData userData) {
		this.userData = userData;
	}
	
	public ServiceResult addUserExecute(ServletContext context) {
		try {
			UserMap userMap = UserFileManager.loadMap(context);
			int newId = IdGenerator.generateNextID(userMap.getGeneratedIdSet());
			UserData newUserData = this.userData;
			userMap.addUser(newId,newUserData);
			TreeMap <Integer, UserData> resultMap = userMap.getInternalMap();
			UserFileManager.saveMap(resultMap,context);
			return new ServiceResult("add" , newId , newUserData.getName() , true , "ユーザーID:"+newId+" ,ユーザー名:"+newUserData.getName()+"を登録しました。",resultMap);
		}catch(Exception e) {
			return new ServiceResult("add" , 0 , "---" , false, "ユーザーの追加に失敗しました。"+ e.getMessage(),null);
		}
	}
}