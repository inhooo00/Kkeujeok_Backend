package shop.kkeujeok.kkeujeokbackend.member.friend.api.dto.response;

import lombok.Builder;
import shop.kkeujeok.kkeujeokbackend.member.domain.Member;
import shop.kkeujeok.kkeujeokbackend.member.friend.domain.FriendStatus;

@Builder
public record FriendResDto(
        String userEmail,
        String friendEmail,
        FriendStatus friendStatus,
        boolean isFrom
){
    public static FriendResDto of(Member fromMember, Member toMember) {
        return FriendResDto.builder()
                .userEmail(fromMember.getEmail())
                .friendEmail(toMember.getEmail())
                .friendStatus(FriendStatus.WAITING)
                .isFrom(true)
                .build();
    }
}
