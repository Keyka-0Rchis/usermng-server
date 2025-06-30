package works.keyka.usermngserver.repository;

import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

import works.keyka.usermngserver.domain.UserData;


public class UserMap {
	private TreeMap <Integer, UserData> userMap;
	
	public UserMap() {
		userMap = new TreeMap<>();
	}
	
	//ユーザー追加メソッド
	public void addUser(Integer newID,UserData userdata) {
		userMap.put(newID, userdata);
	}
	
	//ユーザー削除メソッド
	public void deleteUser(Integer deleteID) {
		if(deleteID == null) {
			System.out.println("ユーザーIDが不正です。");
			return;
		}
		//Promiseチェーンみたいに、問題ないものだけ通す。
		//ifネストとはお別れ
		//nullチェックとかは、パリデーションっていうんだって。
		
		UserData userData = userMap.get(deleteID);
		
	    if (userData == null) {
	        System.out.println("該当するデータがありません。");
	        return;
	    }
		
		if(!userData.isExist()) {
			System.out.println("すでに削除済みのユーザーです。");
			return;
		}
		
		userData.setExist(false);;
		System.out.println("ユーザーが削除されました。");

	}
	
	//ユーザー閲覧メソッド
	public TreeMap<Integer,UserData> viewUser() {
		if (userMap.isEmpty()) {
		    return new TreeMap<>(); 
		    // 空のmapを返すことで、このメソッドの外で判断させる。
		    // isEmptyで調べてもらう感じ。要するに素通りだね。
		}
		TreeMap<Integer,UserData> existUserMap = userMap.entrySet()						//entryの組にする
			   .stream()												//stream開始
			   .filter(entry -> entry.getValue().isExist() == true)		//存在フラグtrueのみ取り出し
			   .collect(Collectors.toMap(entry -> entry.getKey(), 		//第一引数はkeyを配置
					   					  entry -> entry.getValue(),	//第二引数は値を配置
					   					  (e1,e2)->e2,					//第三引数は同じkeyの場合、後先どっち優先かを決める。
					   					  () -> new TreeMap<>()));		//第四引数でmapの型を決める。
		return existUserMap;
		
//		TreeMap<Integer, UserData> existUserMap = userMap.entrySet()
//			    .stream()
//			    .filter(entry -> entry.getValue().isExist())
//			    .collect(Collectors.toMap(
//			        Map.Entry::getKey,
//			        Map.Entry::getValue,
//			        (e1, e2) -> e2,
//			        TreeMap::new
//			    ));//なるほど、こういう書き方もあるのか
				//collectの使い方むずいね。実際に動くのはtoMapっていうイメージなんだろうね。
		
	}
	
	
	//saveMapにTreeMapを受け渡すためのゲッター
	public TreeMap<Integer,UserData> getInternalMap(){
		return this.userMap;
	}
	
	//IdGeneratorにkeyのsetを受け渡すためのゲッター
	public Set<Integer> getGeneratedIdSet(){
		Set<Integer> generatedIdSet = this.userMap.keySet();
		return generatedIdSet;
	}
}