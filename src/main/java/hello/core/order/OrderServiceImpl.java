package hello.core.order;

import com.sun.source.tree.UsesTree;
import hello.core.annotation.MainDiscountPolicy;
import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.security.KeyStore;

@Component
public class OrderServiceImpl implements OrderService {

    private final MemberRepository memberRepository; //회원 저장소 역할
    private final DiscountPolicy discountPolicy;

    public OrderServiceImpl(MemberRepository memberRepository, @MainDiscountPolicy DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }
    //수정자 주입
//    @Autowired
//    public void setMemberRepository(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }
//
//    @Autowired
//    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
//        this.discountPolicy = discountPolicy;
//    }
    //생성자 주입

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPirce) {
        Member member = memberRepository.findById( memberId ); //조회
        int discountPrice = discountPolicy.discount( member, itemPirce ); //정책에 회원을 넘김
        return new Order( memberId, itemName, itemPirce, discountPrice ); //수행된 로직들에 대한 객체 return
    }
}
