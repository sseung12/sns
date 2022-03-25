package code.sns.controller;


import code.sns.auth.PrincipalDetail;
import code.sns.domain.dto.response.CommentResponseDto;
import code.sns.domain.dto.response.FollowResponseDto;
import code.sns.domain.dto.response.PostResponseDto;
import code.sns.domain.dto.response.UserProfileDto;
import code.sns.exception.CustomException;
import code.sns.exception.ErrorCode;
import code.sns.repository.user.UserRepository;
import code.sns.service.FollowService;
import code.sns.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class indexController {

    private final PostService postService;
    private final FollowService followService;
    private final UserRepository userRepository;

    @GetMapping("/")
    public String index(Model model,Authentication authentication) {

        List<FollowResponseDto> followList;
        List<CommentResponseDto> comments = null;

        if(authentication==null){
                followList = followService.getBasicList();
                model.addAttribute("posts",postService.getPosts());

        }else{
            Long userId = authCheck(authentication);
            followList =followService.getFollowList(userId);
            model.addAttribute("posts",postService.getFollowPost(userId,Pageable.ofSize (5)));

        }

        model.addAttribute ("comments",comments);
        model.addAttribute("followList",followList);
        return "index";
    }

    @GetMapping("/profile")
    public String loginProfile(Authentication authentication,Model model) {
        Long userId = authCheck(authentication);
        
        Pageable pageable = Pageable.ofSize(3);
        profileModeling (userId,pageable , model);
        return "profile";
    }



    @GetMapping("/profile/{userId}")
    public String profile(@PathVariable("userId")Long userId,Model model) {

        userRepository.findById(userId).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_USER));
        Pageable pageable=Pageable.ofSize(3);
        profileModeling (userId, pageable, model);

        return "profile";
    }

    private Long authCheck(Authentication authentication) {

        if (authentication == null) {
            throw new CustomException(ErrorCode.FORBIDDEN_USER);
        }
        PrincipalDetail principal = (PrincipalDetail) authentication.getPrincipal();
        return principal.getId();
    }
    private void profileModeling(Long userId, Pageable pageable, Model model) {
        UserProfileDto profile = userRepository.getProfile (userId);
        List<String> toFollowImg = userRepository.getToFollowImg (userId);
        List<String> fromFollowImg = userRepository.getFromFollowImg (userId);
        List<PostResponseDto> myPosts = postService.getPostsLogin (userId, pageable);
        List<PostResponseDto> postsLiked= postService.getPostsLiked(userId,pageable);

        model.addAttribute ("user", profile);
        model.addAttribute ("toFollow", toFollowImg);
        model.addAttribute ("fromFollow", fromFollowImg);
        model.addAttribute ("myPosts", myPosts);
        model.addAttribute ("postsLiked", postsLiked);
    }
}
