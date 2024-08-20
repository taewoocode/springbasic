package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService {

    private final MemberRepository memberRepository; //회원 저장소 역할
    private final DiscountPolicy discountPolicy; //인터페이스만 의존하고 있다.-> 하지만 문제가 있다. -> NullPointException

    //생성자 주입
    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPirce) {
        Member member = memberRepository.findById( memberId ); //조회
        int discountPrice = discountPolicy.discount( member, itemPirce ); //정책에 회원을 넘김
        return new Order( memberId, itemName, itemPirce, discountPrice ); //수행된 로직들에 대한 객체 return
    }

    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
