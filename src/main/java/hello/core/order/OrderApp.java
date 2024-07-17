package hello.core.order;

import hello.core.AppConfig;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;

public class OrderApp {
    public static void main(String[] args) {
//        MemberService memberService = new MemberServiceImpl();
//        OrderService orderService = new OrderServiceImpl( memberRepository, discountPolicy );
        AppConfig appConfig = new AppConfig();
        OrderService orderService = appConfig.orderService(); //MemoryMemberService, FixDiscoutPolicy 참조값 받음
        MemberService memberService = appConfig.memberService();

        Long memberId = 1L;
        Member member = new Member( memberId, "memberA", Grade.VIP );
        memberService.join( member );

        Order order = orderService.createOrder( memberId, "itemA", 20000 );
        System.out.println( "order: " + order );
        System.out.println( "order: " + order.calculatePrice() );
    }
}
