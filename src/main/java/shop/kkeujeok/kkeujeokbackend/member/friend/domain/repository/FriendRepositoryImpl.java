package shop.kkeujeok.kkeujeokbackend.member.friend.domain.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import shop.kkeujeok.kkeujeokbackend.member.friend.domain.FriendStatus;
import shop.kkeujeok.kkeujeokbackend.member.friend.domain.QFriend;

import static com.querydsl.core.types.dsl.Expressions.anyOf;

@Repository
@Transactional(readOnly = true)
public class FriendRepositoryImpl implements FriendRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public FriendRepositoryImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    @Override
    public void updateFriendStatusByEmails(String userEmail, String friendEmail, FriendStatus status) {
        QFriend friend = QFriend.friend;

        queryFactory.update(friend)
                .where(anyOf(
                        friend.userEmail.eq(userEmail).and(friend.friendEmail.eq(friendEmail)),
                        friend.userEmail.eq(friendEmail).and(friend.friendEmail.eq(userEmail))
                ))
                .set(friend.friendStatus, status)
                .execute();
    }
}

