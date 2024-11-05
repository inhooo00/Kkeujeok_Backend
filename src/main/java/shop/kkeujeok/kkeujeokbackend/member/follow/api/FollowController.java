package shop.kkeujeok.kkeujeokbackend.member.follow.api;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import shop.kkeujeok.kkeujeokbackend.global.annotation.CurrentUserEmail;
import shop.kkeujeok.kkeujeokbackend.global.template.RspTemplate;
import shop.kkeujeok.kkeujeokbackend.member.follow.api.dto.request.FollowAcceptReqDto;
import shop.kkeujeok.kkeujeokbackend.member.follow.api.dto.request.FollowReqDto;
import shop.kkeujeok.kkeujeokbackend.member.follow.api.dto.response.FollowAcceptResDto;
import shop.kkeujeok.kkeujeokbackend.member.follow.api.dto.response.FollowInfoListDto;
import shop.kkeujeok.kkeujeokbackend.member.follow.api.dto.response.FollowResDto;
import shop.kkeujeok.kkeujeokbackend.member.follow.api.dto.response.RecommendedFollowInfoListDto;
import shop.kkeujeok.kkeujeokbackend.member.follow.application.FollowService;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/member/follow")
public class FollowController {

    private final FollowService followService;

    @PostMapping
    public RspTemplate<FollowResDto> save(@CurrentUserEmail String email,
                                          @RequestBody FollowReqDto followReqDto) {
        return new RspTemplate<>(HttpStatus.OK,
                "친구 추가 요청",
                followService.save(email, followReqDto));
    }

    @PostMapping("/accept/{followId}")
    public RspTemplate<Void> accept(@PathVariable Long followId) {
        followService.accept(followId);

        return new RspTemplate<>(HttpStatus.OK,
                "친구 추가 수락", null);
    }

    @GetMapping
    public RspTemplate<FollowInfoListDto> findFollowList(@CurrentUserEmail String email,
                                                         @RequestParam(name = "page", defaultValue = "0") int page,
                                                         @RequestParam(name = "size", defaultValue = "10") int size) {
        return new RspTemplate<>(HttpStatus.OK,
                "내 친구 목록 조회",
                followService.findFollowList(email, PageRequest.of(page, size)));
    }

    @GetMapping("/recommended")
    public RspTemplate<RecommendedFollowInfoListDto> findRecommendedFollowList(@CurrentUserEmail String email,
                                                                               @RequestParam(name = "page", defaultValue = "0") int page,
                                                                               @RequestParam(name = "size", defaultValue = "10") int size) {
        return new RspTemplate<>(HttpStatus.OK,
                "추천 친구 목록 조회",
                followService.findRecommendedFollowList(email, PageRequest.of(page, size)));
    }
}
