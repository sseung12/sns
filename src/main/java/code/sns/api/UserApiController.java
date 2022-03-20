package code.sns.api;


import code.sns.domain.User;
import code.sns.domain.dto.request.UserRequestDto;
import code.sns.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
public class UserApiController {


    private final UserService userService;

    @PostMapping("/api/login")
    public ResponseEntity login(@RequestBody Map<String, Object> map) {
        log.info("login 실행");
        return ResponseEntity.status(HttpStatus.OK).body("로그인");
    }


    @PostMapping("/join")
    public ResponseEntity join(@RequestBody @Valid UserRequestDto requestDto) {

        userService.join(requestDto);

        return ResponseEntity.status(HttpStatus.OK).body("회원가입완료");
    }

    @PutMapping("/api/user/{id}")
    public ResponseEntity updateUser(@RequestBody UserRequestDto requestDto) {

        User user = userService.updateUser(requestDto);
        return ResponseEntity.status(HttpStatus.OK).body("user");
    }

    @GetMapping("/user/{id}")
    public ResponseEntity findById(@PathVariable("id") Long id) {

        User findUser = userService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(findUser);
    }


}
