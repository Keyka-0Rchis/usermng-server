package works.keyka.usermngserver.advice;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import works.keyka.usermngserver.common.exception.DuplicateEmailException;
import works.keyka.usermngserver.common.exception.UnexpectedException;
import works.keyka.usermngserver.common.exception.ValidationException;
import works.keyka.usermngserver.service.ServiceResult;

@RestControllerAdvice
public class GlobalExceptionHandler {
    // 独自のバリデーションエラー
    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ServiceResult> handleValidation(ValidationException ex) {
        ServiceResult result = new ServiceResult(
            ex.getOperation(),
            false,
            ex.getMessage(),
            ex.getErrorCode()
        );
        return ResponseEntity.status(ex.getStatus()).body(result);
    }

    // メール重複
    @ExceptionHandler(DuplicateEmailException.class)
    public ResponseEntity<ServiceResult> handleDuplicate(DuplicateEmailException ex) {
        ServiceResult result = new ServiceResult(
            ex.getOperation(),
            false,
            ex.getMessage(),
            ex.getErrorCode()
        );
        return ResponseEntity.status(ex.getStatus()).body(result);
    }

    // 予期せぬ例外（どれにも当てはまらない）
    //合わせてoperationをうけとるためには自作しないといけない。Exceptionでいいのではという話もあるけど、統一感があるほうがいいんだろうか。
    @ExceptionHandler(UnexpectedException.class)
    public ResponseEntity<ServiceResult> handleGeneric(UnexpectedException ex) {
        ServiceResult result = new ServiceResult(
            ex.getOperation(),
            false,
            ex.getMessage(),
            ex.getErrorCode()
        );
        return ResponseEntity.status(ex.getStatus()).body(result);
    }
}
