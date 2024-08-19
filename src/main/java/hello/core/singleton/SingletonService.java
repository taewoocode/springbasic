package hello.core.singleton;

public class SingletonService {

    private static final SingletonService instance = new SingletonService();

    public static SingletonService getInstance() {
        return instance;
    }

    //private는 자기자신을 호출한다.
    private SingletonService() {
    }

    public void logic() {
        System.out.println( "싱글톤 객체로직을 호출" );
    }
}
