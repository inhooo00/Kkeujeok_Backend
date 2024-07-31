package shop.kkeujeok.kkeujeokbackend.member.friend.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.kkeujeok.kkeujeokbackend.member.friend.domain.Friend;

public interface FriendRepository extends JpaRepository<Friend, Long> {
}
