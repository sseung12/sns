package code.sns.domain.dto.response;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PostResponseLoginDto {
    private Long user_id;
    private Long post_id;
    private String profile_img;
    private String username;
    private String nickname;
    private String context;
    private String storeFilename;
    private LocalDate createdAt;
    private Integer postLikeCount;
    private Integer commentsCount;


    @QueryProjection
    public PostResponseLoginDto(Long user_id, Long post_id, String profile_img, String username, String nickname, String context, String storeFilename, LocalDate createdAt, Integer postLikeCount, Integer commentsCount){
        this.user_id = user_id;
        this.post_id = post_id;
        this.profile_img = profile_img;
        this.username = username;
        this.nickname = nickname;
        this.context = context;
        this.storeFilename = storeFilename;
        this.createdAt = createdAt;
        this.postLikeCount = postLikeCount;
        this.commentsCount = commentsCount;

    }
}
