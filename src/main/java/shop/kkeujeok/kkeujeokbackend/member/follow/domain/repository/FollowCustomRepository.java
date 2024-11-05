package shop.kkeujeok.kkeujeokbackend.member.follow.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import shop.kkeujeok.kkeujeokbackend.member.domain.Member;
import shop.kkeujeok.kkeujeokbackend.member.follow.api.dto.response.FollowInfoResDto;
import shop.kkeujeok.kkeujeokbackend.member.follow.api.dto.response.RecommendedFollowInfoResDto;

public interface FollowCustomRepository {

    boolean existsByFromMemberAndToMember(Member fromMember, Member toMember);

    void acceptFollowingRequest(Long followId);

    Page<FollowInfoResDto> findFollowList(Long memberId, Pageable pageable);

    Page<RecommendedFollowInfoResDto> findRecommendedFollowList(Long memberId, Pageable pageable);
}