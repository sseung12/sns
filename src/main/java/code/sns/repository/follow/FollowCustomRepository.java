package code.sns.repository.follow;

import code.sns.domain.dto.response.FollowResponseDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FollowCustomRepository {

    List<FollowResponseDto> getUnFollowList(Long id);

    List<FollowResponseDto> getBasicList();

    List<FollowResponseDto> getFollowList(Long id);

}
