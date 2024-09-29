package hello.core.lifecycle;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class NetworkClient  {

    private String url;

    public NetworkClient() {
        System.out.println( "생성자를 호출합니다." + url );
    }

    public void setUrl(String url) {
        this.url = url;
    }

    //서비스를 시작합니다.
    public void connect() {
        System.out.println( "connect: " + url );
    }

    public void call(String message) {
        System.out.println( "call: " + url + "message = " + message );
    }

    //서비스 종료시 호출
    public void disconnect() {
        System.out.println( "close: " + url );
    }

    @PostConstruct
    public void init(){
        System.out.println("NetworkClinet.init");
        connect();
        call( "초기화 연결 메시지" );
    }

    @PreDestroy
    public void close() {
        System.out.println("NetworkClinet.close");
        disconnect();

    }
}
