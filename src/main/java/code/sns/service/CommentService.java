package code.sns.service;


import code.sns.config.stomp.NoticeManger;
import code.sns.config.stomp.NoticeMessage;
import code.sns.domain.Comment;
import code.sns.domain.Post;
import code.sns.domain.User;
import code.sns.domain.dto.request.CommentRequestDto;
import code.sns.domain.dto.response.CommentResponseDto;
import code.sns.exception.NotFoundObjectException;
import code.sns.repository.comment.CommentRepository;
import code.sns.repository.post.PostRepository;
import code.sns.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final NoticeManger noticeManger;

    public CommentResponseDto createComment(CommentRequestDto requestDto) {

        User user = userRepository.findById(requestDto.getUserId())
                .orElseThrow(()->new NotFoundObjectException("해당 유저가 없습니다."));
        Post post = postRepository.findById(requestDto.getPostId())
                .orElseThrow(()->new NotFoundObjectException("해당 게시물이 없습니다."));

        Comment comment = Comment.builder()
                .context(requestDto.getContext())
                .post(post)
                .user(user)
                .build();

            commentRepository.save(comment);

        Long fromId = post.getUser().getId();
        noticeManger.sendNotice(fromId, NoticeMessage.createMessage(user.getUsername(),user.getProfile_img(), NoticeMessage.Type.comment));

        return commentRepository.getCommentById(comment.getId());
    }

    public List<CommentResponseDto> getCommentById(Long postId, Pageable pageable) {
        List<CommentResponseDto> commentByIdDto = commentRepository.getCommentByIdDto(postId, pageable);
        return commentByIdDto;
    }
}
