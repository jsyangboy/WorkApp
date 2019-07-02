package demo.yqy.org.workapp.bean;

public class Test {

    private Test() {
    }

    private volatile static Test test;

    public static Test getInstance() {
        if (test == null) {
            synchronized (Test.class) {
                if (test == null) {
                    test = new Test();
                }
            }
        }
        return test;
    }
}
