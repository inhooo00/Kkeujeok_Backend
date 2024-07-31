package shop.kkeujeok.kkeujeokbackend.member.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import shop.kkeujeok.kkeujeokbackend.global.annotation.CurrentUserEmail;
import shop.kkeujeok.kkeujeokbackend.global.template.RspTemplate;
import shop.kkeujeok.kkeujeokbackend.member.application.MemberService;
import shop.kkeujeok.kkeujeokbackend.member.friend.api.dto.reqeust.FriendReqDto;
import shop.kkeujeok.kkeujeokbackend.member.friend.api.dto.response.FriendResDto;

@RestController
@RequestMapping("/api/members")
public class MemberController {
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/")
    public RspTemplate<FriendResDto> sendFriendshipRequest(@CurrentUserEmail String fromEmail, @RequestBody FriendReqDto friendReqDto) {
        return new RspTemplate<>(HttpStatus.OK, "친구 요청 전송", memberService.requestFriend(fromEmail, friendReqDto));
    }
}
