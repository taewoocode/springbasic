package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements MemberService{

    //인터페이스만 있으면 nullpointException이 생기기 때문에 구현객체 인스턴스를 생성해줘야 한다.
    private final MemberRepository memberRepository;

    //MemberRepository에 들어갈 것을 생성자를 통해서 결정한다 -> 생성자 주입
    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save( member );
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById( memberId );
    }

    //test
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
