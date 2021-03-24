package Test;

public class MyRunnable implements Runnable{
    public static Add add = new Add();
    @Override
    public void run() {
        add.addCnt();
        System.out.println("RUNNING");
    }

    public static void main(String[] args) {
        MyRunnable myRunnable = new MyRunnable();
        Thread thread = new Thread(myRunnable);
        thread.start();
        System.out.println(add.getCnt());
    }
}
