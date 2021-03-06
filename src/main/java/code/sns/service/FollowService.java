package code.sns.service;

import code.sns.config.stomp.NoticeManger;
import code.sns.config.stomp.NoticeMessage;
import code.sns.domain.Follow;
import code.sns.domain.User;
import code.sns.domain.dto.response.FollowResponseDto;
import code.sns.exception.CustomException;
import code.sns.exception.ErrorCode;
import code.sns.repository.follow.FollowRepository;
import code.sns.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class FollowService {

    private final FollowRepository followRepository;
    private final UserRepository  userRepository;
    private final NoticeManger noticeManger;

    public void follow(Long to_id, Long id) {
        User toUser = userRepository.getById(to_id);
        User fromUser = userRepository.getById(id);

        if (checkFollow(toUser,fromUser)){
            Follow follow = followRepository.findByToFollowAndFromFollow(toUser, fromUser).get();
            followRepository.delete(follow);
            return;
        }
        followRepository.save(new Follow(toUser,fromUser));
        noticeManger.sendNotice(to_id, NoticeMessage.createMessage(fromUser.getUsername(),fromUser.getProfile_img(), NoticeMessage.Type.follow));
    }

    private boolean checkFollow(User to_id, User id) {
        return followRepository.existsByToFollowAndFromFollow(to_id, id);
    }

    public List<FollowResponseDto> getUnFollowList(Long userId) {

        List<FollowResponseDto> list = followRepository.getUnFollowList(userId, PageRequest.of(0,5));

        return list;
    }
    public List<FollowResponseDto> getFollowList(Long id) {

        return followRepository.getFollowList(id);
    }

    public List<FollowResponseDto> getBasicList() {
        return followRepository.getBasicList();
    }

    public boolean isFollow(Long userId, Long loginId) {
        User toUser =  userRepository.findById(userId).orElseThrow(()-> new CustomException(ErrorCode.NOT_FOUND_USER));
        User fromUser =  userRepository.findById(loginId).orElseThrow(()-> new CustomException(ErrorCode.NOT_FOUND_USER));

        return followRepository.existsByToFollowAndFromFollow(toUser,fromUser);
    }
}
