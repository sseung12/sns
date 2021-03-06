package code.sns.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    BAD_REQUEST_PARAM(HttpStatus.BAD_REQUEST, "잘못된 요청입니다."),
    BAD_REQUEST_POST(HttpStatus.BAD_REQUEST, "글 입력값을 다시 확인하세요."),
    UNAUTHORIZED_USER(HttpStatus.UNAUTHORIZED, "로그인이 필요합니다"),
    FORBIDDEN_USER(HttpStatus.FORBIDDEN, "권한이 없는 요청입니다"),
    FORBIDDEN_AUTHORITY(HttpStatus.FORBIDDEN,"해당 포스트에 접근 권한이 업습니다"),
    FORBIDDEN_REQUEST(HttpStatus.FORBIDDEN,"자기 자신에게 알림을 보낼수 없습니다."),
    FILE_CONVERT_ERROR(HttpStatus.BAD_REQUEST,"파일 변환에 실패했습니다."),


    NOT_FOUND_POST(HttpStatus.NOT_FOUND, "해당 글을 찾을 수 없습니다"),
    NOT_FOUND_USER(HttpStatus.NOT_FOUND, "해당 유저를 찾을 수 없습니다"),
    NOT_FOUND_EMAIL(HttpStatus.NOT_FOUND, "해당 이메일을 찾을 수 없습니다"),
    NOT_FOUND_Follow(HttpStatus.NOT_FOUND, "해당 팔로우를 찾을 수 없습니다");

    private final HttpStatus httpStatus;
    private final String detail;

}
