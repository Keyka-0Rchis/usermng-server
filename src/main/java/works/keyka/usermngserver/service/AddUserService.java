package works.keyka.usermngserver.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import works.keyka.usermngserver.common.ErrorCode;
import works.keyka.usermngserver.domain.UserData;
import works.keyka.usermngserver.repository.UserRepository;


@Service
@RequiredArgsConstructor	//勝手にコンストラクタだと！？
public class AddUserService {

	//フィールドを用意すると勝手にインスタンス化される。
	private final UserRepository userRepository;
	
	public ServiceResult addUserExecute(UserData userData) {
		//メールアドレスの重複チェック
		if (userRepository.isExistEmail(userData.getEmail())) {
			return new ServiceResult(
					"add" ,
					false,
					"既に登録されているメールアドレスです",
					ErrorCode.DUPLICATE_EMAIL
					);
		}
		
		try {
//			//insertするデータの作成　
//			//IDはAutoIncrementするのでnull。削除フラグは当然false
//			UserData userData = new UserData(null,addName,addEmail,addPassword,false);
			//引数がご茶つくので、controllerへ移管
			
			//insert intoしてもらう。
			userRepository.insert(userData);
			
			return new ServiceResult("add",
					true,
					"ユーザーを追加しました"
					,null
					);
		}catch(IllegalArgumentException e){
			return new ServiceResult(
					"add" ,
					false,
					"入力値が不正です。"+ e.getMessage(),
					ErrorCode.VALIDATION_ERROR
					);
		}catch(Exception e) {
			return new ServiceResult(
					"add" ,
					false,
					"予期しないエラーが発生しました。"+ e.getMessage(),
					ErrorCode.UNKNOWN_ERROR
					);
		}
	}
}