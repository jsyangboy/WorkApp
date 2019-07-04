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

    private static int[] arr = new int[6];

    public static void main(String[] argv) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 100);
        }

        prin(arr);
        //quickSort(arr, 0, arr.length - 1);
        System.out.println("");
        prin(arr);
    }

    private static void prin(int arr[]) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }

}
