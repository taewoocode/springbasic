package hello.core.member;

public class MemberServiceImpl implements MemberService{

    //인터페이스만 있으면 nullpointException이 생기기 때문에 구현객체 인스턴스를 생성해줘야 한다.
    private final MemberRepository memberRepository = new MemoryMemberRepository();

    @Override
    public void join(Member member) {
        memberRepository.save( member );
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById( memberId );
    }
}
