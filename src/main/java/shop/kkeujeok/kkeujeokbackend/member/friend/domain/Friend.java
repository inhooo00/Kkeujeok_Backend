package shop.kkeujeok.kkeujeokbackend.member.friend.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import shop.kkeujeok.kkeujeokbackend.global.entity.BaseEntity;
import shop.kkeujeok.kkeujeokbackend.global.entity.Status;
import shop.kkeujeok.kkeujeokbackend.member.domain.Member;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Friend extends BaseEntity {

    @Enumerated(value = EnumType.STRING)
    private Status status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private String userEmail;

    private String friendEmail;

    @Enumerated(value = EnumType.STRING)
    private FriendStatus friendStatus;

    private boolean isFrom;

    @Builder
    private Friend(Member member, String userEmail, String friendEmail, FriendStatus friendStatus, boolean isFrom) {
        this.status = Status.A;
        this.member = member;
        this.userEmail = userEmail;
        this.friendEmail = friendEmail;
        this.friendStatus = friendStatus;
        this.isFrom = isFrom;
    }

    public static Friend of(Member member, String userEmail, String friendEmail, boolean isFrom) {
        return Friend.builder()
                .member(member)
                .userEmail(userEmail)
                .friendEmail(friendEmail)
                .friendStatus(FriendStatus.WAITING)
                .isFrom(isFrom)
                .build();
    }
}
