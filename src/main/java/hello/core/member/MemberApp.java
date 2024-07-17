package hello.core.member;

import hello.core.AppConfig;

public class MemberApp {

    public static void main(String[] args) {

        AppConfig appConfig = new AppConfig();
        MemberService memberService = appConfig.memberService();
//        MemberService memberService = new MemberServiceImpl();
        Member member = new Member( 1L, "member", Grade.VIP );
        memberService.join( member );

        Member findMember = memberService.findMember( 1L );
        System.out.println( "new member = " + member.getName() );
        System.out.println( "findMember = " + findMember.getName() );
    }
}
