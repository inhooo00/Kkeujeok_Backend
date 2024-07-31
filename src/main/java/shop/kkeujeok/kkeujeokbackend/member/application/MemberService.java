package shop.kkeujeok.kkeujeokbackend.member.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.kkeujeok.kkeujeokbackend.auth.exception.EmailNotFoundException;
import shop.kkeujeok.kkeujeokbackend.member.domain.Member;
import shop.kkeujeok.kkeujeokbackend.member.domain.repository.MemberRepository;
import shop.kkeujeok.kkeujeokbackend.member.friend.api.dto.reqeust.FriendReqDto;
import shop.kkeujeok.kkeujeokbackend.member.friend.api.dto.response.FriendResDto;
import shop.kkeujeok.kkeujeokbackend.member.friend.domain.Friend;
import shop.kkeujeok.kkeujeokbackend.member.friend.domain.repository.FriendRepository;

@Service
@Transactional(readOnly = true)
public class MemberService {
    private final MemberRepository memberRepository;
    private final FriendRepository friendRepository;

    public MemberService(MemberRepository memberRepository, FriendRepository friendRepository) {
        this.memberRepository = memberRepository;
        this.friendRepository = friendRepository;
    }

    // 친구 추가 요청
    @Transactional
    public FriendResDto requestFriend(String fromEmail, FriendReqDto friendReqDto) {
        Member fromMember = memberRepository.findByEmail(fromEmail).orElseThrow(EmailNotFoundException::new);
        Member toMember = memberRepository.findByEmail(friendReqDto.email()).orElseThrow(EmailNotFoundException::new);

        Friend friendFrom = Friend.of(fromMember, fromEmail, friendReqDto.email(), true);
        Friend friendTo = Friend.of(toMember, friendReqDto.email(), fromEmail, false);

        friendRepository.save(friendTo);
        friendRepository.save(friendFrom);

        return FriendResDto.of(fromMember, toMember);
    }

    // 친구 추가 요청 수락

    // 친구 추가 요청 거절

    // 친구 추가 요청 리스트 조회

    // 친구 리스트 조회

    // 친구 삭제
}
