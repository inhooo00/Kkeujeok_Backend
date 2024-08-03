package shop.kkeujeok.kkeujeokbackend.challenge.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.kkeujeok.kkeujeokbackend.auth.exception.EmailNotFoundException;
import shop.kkeujeok.kkeujeokbackend.challenge.api.dto.reqeust.ChallengeSaveReqDto;
import shop.kkeujeok.kkeujeokbackend.challenge.api.dto.response.ChallengeInfoResDto;
import shop.kkeujeok.kkeujeokbackend.challenge.domain.Challenge;
import shop.kkeujeok.kkeujeokbackend.challenge.domain.repository.ChallengeRepository;
import shop.kkeujeok.kkeujeokbackend.member.domain.Member;
import shop.kkeujeok.kkeujeokbackend.member.domain.repository.MemberRepository;

@Service
@RequiredArgsConstructor
public class ChallengeService {
    private final ChallengeRepository challengeRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public ChallengeInfoResDto create(String email, ChallengeSaveReqDto challengeSaveReqDto) {
        Member member = memberRepository.findByEmail(email).orElseThrow(EmailNotFoundException::new);
        Challenge challenge = challengeSaveReqDto.toEntity(member);
        challenge.validateCycleDetails(challengeSaveReqDto.cycle());
        challengeRepository.save(challenge);

        return ChallengeInfoResDto.of(challenge, member);
    }
}
