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


    static class Node {
        private Node next;
        private int value;

        public Node(int value) {
            this.value = value;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

    }


    public static void main(String[] argv) {
        Node head = new Node((int) (Math.random() * 100));
        Node h1 = new Node((int) (Math.random() * 100));
        Node h2 = new Node((int) (Math.random() * 100));
        Node h3 = new Node((int) (Math.random() * 100));
        Node h4 = new Node((int) (Math.random() * 100));
        Node h5 = new Node((int) (Math.random() * 100));
        Node h6 = new Node((int) (Math.random() * 100));
        Node h7 = new Node((int) (Math.random() * 100));

        head.next = h1;
        h1.next = h2;
        h2.next = h3;
        h3.next = h4;
        h4.next = h5;
        h5.next = h6;
        h6.next = h7;


        print(head);
        print(bubbleSort(head));
    }


    private static void print(Node node) {
        if (node == null) {
            return;
        }

        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(node.value + "-");
        while (node.next != null) {
            node = node.next;
            stringBuffer.append(node.value + "-");
        }
        System.out.println(stringBuffer.toString());
    }

    private static Node bubbleSort(Node node) {
        if (node == null || node.next == null) {
            return node;
        }

        Node temp = node;//记录头节点,因为每次排序都是从头节点开始两两比较
        Node tail = null;//记录排序最后的节点

        int value = 0;
        while (node.next != tail) {
            while (node.next != tail) {
                if (node.value > node.next.value) {
                    value = node.value;
                    node.value = node.next.value;
                    node.next.value = value;
                }
                node = node.next;
            }
            tail = node;
            node = temp;
        }
        return node;
    }



}
