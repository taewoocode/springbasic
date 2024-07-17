package hello.core.member;

public interface MemberService {

    //회원가입
    void join(Member member);

    Member findMember(Long memberId);
}
