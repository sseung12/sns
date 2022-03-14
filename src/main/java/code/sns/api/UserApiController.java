package code.sns.api;


import code.sns.domain.dto.UserRequestDto;
import code.sns.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class UserApiController {


    private final UserService userService;

    @PostMapping("/join")
    public ResponseEntity join(@RequestBody @Valid UserRequestDto requestDto) {

        userService.join(requestDto);


        return ResponseEntity.status(HttpStatus.OK).body("");
    }
}
