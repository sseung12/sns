package code.sns.domain.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDate;
import java.time.Period;

@Getter
@Setter
@ToString
public class UserBirthDto implements Serializable {

    private Long userId;

    private String username;

    private String nickname;

    private String profile_img;

    private LocalDate birth;

    private Integer dDay;

    @QueryProjection
    public UserBirthDto(Long userId,String nickname,  String username, String profile_img,LocalDate birth) {
        this.nickname = nickname;
        this.userId = userId;
        this.username = username;
        this.profile_img = profile_img;
        this.birth = birth;


        this.dDay =Period.between(changeBirth(birth),LocalDate.now()).getDays();
    }

    private LocalDate changeBirth(LocalDate birth) {
        LocalDate now = LocalDate.now();
        LocalDate change = LocalDate.of(now.getYear(),birth.getMonth(),birth.getDayOfMonth());
        return change;
    }
}
