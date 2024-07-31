package shop.kkeujeok.kkeujeokbackend.member.friend.domain.repository;

import shop.kkeujeok.kkeujeokbackend.member.friend.domain.FriendStatus;

public interface FriendRepositoryCustom {
    void updateFriendStatusByEmails(String userEmail, String friendEmail, FriendStatus status);
}
