package works.keyka.usermngserver.service;

import org.springframework.stereotype.Service;

import works.keyka.usermngserver.domain.UserData;


@Service
public class AddUserService {

	private String addName;
	private String addEmail;
	private String addPassword;
	
	
	public AddUserService(String addName,String addEmail,String addPassword) {
		this.addName = addName;
		this.addEmail = addEmail;
		this.addPassword = addPassword;
	}
	
	public ServiceResult addUserExecute(String addName,String addEmail,String addPassword) {
		try {
			//insertするデータの作成　
			//IDはAutoIncrementするのでnull。削除フラグは当然false
			UserData userData = new UserData(null,addName,addEmail,addPassword,false);
			//insert intoしてもらう。
			UserRepository.insert(userData);
			
//			UserMap userMap = UserFileManager.loadMap(context);
//			int newId = IdGenerator.generateNextID(userMap.getGeneratedIdSet());
//			UserData newUserData = this.userData;
//			userMap.addUser(newId,newUserData);
//			TreeMap <Integer, UserData> resultMap = userMap.getInternalMap();
//			UserFileManager.saveMap(resultMap,context);
//			return new ServiceResult("add" , newId , newUserData.getName() , true , "ユーザーID:"+newId+" ,ユーザー名:"+newUserData.getName()+"を登録しました。",resultMap);
		}catch(Exception e) {
			return new ServiceResult("add" , 0 , "---" , false, "ユーザーの追加に失敗しました。"+ e.getMessage(),null);
		}
	}
}