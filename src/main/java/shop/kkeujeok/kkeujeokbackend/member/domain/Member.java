package shop.kkeujeok.kkeujeokbackend.member.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import shop.kkeujeok.kkeujeokbackend.global.entity.BaseEntity;
import shop.kkeujeok.kkeujeokbackend.global.entity.Status;
import shop.kkeujeok.kkeujeokbackend.member.friend.domain.Friend;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Member extends BaseEntity {

    @Enumerated(EnumType.STRING)
    private Status status;

    private boolean firstLogin;

    @Enumerated(EnumType.STRING)
    private Role role;

    private String email;

    private String name;

    private String picture;

    @Enumerated(value = EnumType.STRING)
    private SocialType socialType;

    private String nickname;

    @OneToMany(mappedBy = "member")
    private List<Friend> friendList = new ArrayList<>();

    @Builder
    private Member(Status status, Role role, String email, String name, String picture, SocialType socialType, boolean firstLogin, String nickname) {
        this.status = status;
        this.role = role;
        this.email = email;
        this.name = name;
        this.picture = picture;
        this.socialType = socialType;
        this.firstLogin = firstLogin;
        this.nickname = nickname;
    }
}
