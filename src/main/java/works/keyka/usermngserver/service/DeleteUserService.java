package works.keyka.usermngserver.service;

import java.util.Optional;
import java.util.TreeMap;

import jakarta.servlet.ServletContext;

import works.keyka.usermngserver.domain.UserData;
import works.keyka.usermngserver.repository.UserFileManager;
import works.keyka.usermngserver.repository.UserMap;



public class DeleteUserService {
	
	private Integer deleteId;
	
	public DeleteUserService(Integer deleteId) {
		this.deleteId = deleteId;
	}
	
	public ServiceResult deleteUserExecute(ServletContext context) {
		try {
			UserMap userMap = UserFileManager.loadMap(context);
			int deleteId = this.deleteId; //ここ不要
			Optional<UserData> userOpt = Optional.ofNullable(userMap.getInternalMap().get(deleteId));
			String deleteName = userOpt.map(UserData::getName).orElse("該当なし");
			TreeMap <Integer, UserData> resultMap = userMap.getInternalMap();
	        if (deleteName.equals("該当なし")) {
	            return new ServiceResult("delete", deleteId, "---", false, "該当するユーザーが見つかりません。",null);
	        }
			userMap.deleteUser(deleteId);
			UserFileManager.saveMap(resultMap,context);
			return new ServiceResult("delete",deleteId,deleteName,true,"ID:"+deleteId+" ,ユーザー名:"+deleteName+"の削除が完了しました",resultMap);
		}catch(Exception e) {
			return new ServiceResult("delete",0,"---",false,"エラーが発生しました。" + e.getMessage(),null);
		}
	}
}