package hello.core.member;

import hello.core.AppConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {

    public static void main(String[] args) {

//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService();
//        MemberService memberService = new MemberServiceImpl();

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext( AppConfig.class );
        MemberService memberService = applicationContext.getBean( "memberService", MemberService.class );//method이름, Type
        Member member = new Member( 1L, "member", Grade.VIP );
        memberService.join( member );

        Member findMember = memberService.findMember( 1L );
        System.out.println( "new member = " + member.getName() );
        System.out.println( "findMember = " + findMember.getName() );
    }
}
