package demo.yqy.org.workapp.bean;

public class Test{
    public static void main(String arg[]){
        System.out.print(jumpFloor(10));
    }

    private static int jumpFloor(int number) {
        if(number<=1){
            return 1;
        }
        return jumpFloor(number-1) + jumpFloor(number -2);
    }
}

