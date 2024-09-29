package hello.core.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycleTest {

    @Test
    public void lifeCycleTest() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext( LifeCycleConfig.class );
        NetworkClient client = ac.getBean( NetworkClient.class );
        ac.close();
    }

    /**
     *  빈은 애플리케이션에서 재사용 가능한 객체로서 스프링 컨테이너에 의해 관리된다.
     *  getBean() 메서드는 스프링 컨테이너에서 등록된 빈을 가져오는 역할
     */

    @Configuration
    static class LifeCycleConfig{
        @Bean
        public NetworkClient networkClient() {
            NetworkClient networkClient = new NetworkClient();
            networkClient.setUrl( "http://hello-spring-dev" );
            return networkClient;
        }
    }
}
