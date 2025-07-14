package works.keyka.usermngserver.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import works.keyka.usermngserver.common.ErrorCode;
import works.keyka.usermngserver.common.exception.DuplicateEmailException;
import works.keyka.usermngserver.common.exception.UnexpectedException;
import works.keyka.usermngserver.common.exception.ValidationException;
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
			throw new DuplicateEmailException ("メールアドレスが重複しています",ErrorCode.DUPLICATE_EMAIL,"add");
		}
		
		try {
//			//insertするデータの作成　
//			//IDはAutoIncrementするのでnull。削除フラグは当然false
//			UserData userData = new UserData(null,addName,addEmail,addPassword,false);
			//引数がごちゃつくので、controllerへ移管
			
			//insert intoしてもらう。
			userRepository.insert(userData);
			
			return new ServiceResult("add",
					true,
					"ユーザーを追加しました"
					,null
					);
		}catch(IllegalArgumentException e){
			throw new ValidationException("入力値が不正です。",ErrorCode.VALIDATION_ERROR,"add");
		}catch(Exception e) {
			throw new UnexpectedException("予期せぬエラーが発生しました。",ErrorCode.UNKNOWN_ERROR,"add");
		}
	}
}