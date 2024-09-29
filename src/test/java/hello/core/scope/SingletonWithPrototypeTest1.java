package hello.core.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;

public class SingletonWithPrototypeTest1 {

    @Test
    void prototypeFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext( PrototypeBean.class );

        PrototypeBean prototypeBean1 = ac.getBean( PrototypeBean.class );
        prototypeBean1.addCount();
        assertThat( prototypeBean1.getCount() ).isEqualTo( 1 );

        PrototypeBean prototypeBean2 = ac.getBean( PrototypeBean.class );
        prototypeBean2.addCount();
        assertThat( prototypeBean2.getCount() ).isEqualTo( 1 );
    }

    @Test
    void singletonClientUserPrototype() {
        //select Bean
        AnnotationConfigApplicationContext ac = new
                AnnotationConfigApplicationContext( PrototypeBean.class, ClientBean.class );

        ClientBean clientBean1 = ac.getBean( ClientBean.class );
        int count1 = clientBean1.logic();
        assertThat( count1 ).isEqualTo( 1 );

        ClientBean clientBean2 = ac.getBean( ClientBean.class );
        int count2 = clientBean2.logic();
        assertThat( count2 ).isEqualTo( 2 );

    }

    @Scope("singleton") //default

    static class ClientBean {

        private final PrototypeBean prototypeBean; //DI

        //@Autowired 는 생략가능
        private ClientBean(PrototypeBean prototypeBean) {
            this.prototypeBean = prototypeBean;
        }

        private int logic() {
            prototypeBean.addCount();
            return prototypeBean.getCount();
        }
    }

    @Scope("prototype")
    static class PrototypeBean {

        private int count = 0;

        public void addCount() {
            count++;
        }

        //조회
        public int getCount() {
            return count;
        }

        @PostConstruct
        public void init() {
            System.out.println( "PrototypeBean.init " + this );
        }

        @PreDestroy
        public void destroy() {
            System.out.println( "prototypeBean.destroy" );
        }
    }
}
