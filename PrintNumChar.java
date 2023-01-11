package interview;

public class PrintNumChar {
    // shared lock
    private static Object lock = new Object();

    //
    private static volatile boolean flag = true;


    public static void main(String[] args) {

        Thread t1 = new Thread(
                () -> {
                    for (int i = 1; i < 27;) {
                        synchronized (lock){
                            if(flag){
                                System.out.println(Thread.currentThread().getName() +" print " +i);
                                flag = false;
                                i++; 
                            }

                        }

                    }
                }
        );

        Thread t2 = new Thread(
                () -> {
                    for (int i = 0; i < 26;) {
                        synchronized (lock){
                            if(!flag){
                                System.out.println(Thread.currentThread().getName() +" print " + (char)('a' + i));
                                flag = true;
                                i++;
                            }

                        }

                    }
                }
        );

        t1.setName("t1");
        t2.setName("t2");

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }


}
