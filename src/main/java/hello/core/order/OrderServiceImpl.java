package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService {

    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPirce) {
        Member member = memberRepository.findById( memberId ); //조회
        int discountPrice = discountPolicy.discount( member, itemPirce ); //정책에 회원을 넘김
        return new Order( memberId, itemName, itemPirce, discountPrice ); //수행된 로직들에 대한 객체 return
    }
}
