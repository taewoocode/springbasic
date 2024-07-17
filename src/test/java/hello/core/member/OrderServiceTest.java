package hello.core.member;

import hello.core.AppConfig;
import hello.core.order.Order;
import hello.core.order.OrderService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OrderServiceTest {

//    MemberService memberService = new MemberServiceImpl();
//    OrderService orderService = new OrderServiceImpl( memberRepository, discountPolicy );

    MemberService memberService;
    OrderService orderService;

    @BeforeEach
    public void beforeEach() {
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
        orderService = appConfig.orderService();
    }

    @Test
    void createOrder() {
        Long memberId = 1L; //AutoBoxing되어 객체로 넘긴다. L은 LongType임을 명시한다.
        Member member = new Member( memberId, "memberA", Grade.VIP );
        memberService.join( member );
        Order order = orderService.createOrder( memberId, "itemA", 10000 );
        Assertions.assertThat( order.getDiscountPrice() ).isEqualTo( 1000 );

    }
}
