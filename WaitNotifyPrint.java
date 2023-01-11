package interview;

public class WaitNotifyPrint {

    public static void main(String[] args) {

        final Object lock = new Object();

        Thread t1 = new Thread(
                ()  ->{
                    synchronized(lock){
                        for(int i = 1; i < 27; i++){
                            System.out.println(Thread.currentThread().getName() + " print " + i);
                            try {
                                lock.notifyAll();
                                lock.wait();
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        }
                        lock.notifyAll();
                    }
                }
        ,"Thread 1");


        Thread t2 = new Thread(
                () -> {
                    synchronized (lock){
                        for (int i = 0; i < 26; i++) {
                            System.out.println(Thread.currentThread().getName() + " print " + (char)('a'+i));
                            try {
                                lock.notifyAll();
                                lock.wait();
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        }
                       lock.notifyAll();
                    }
                }
        , "Thread 2");


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
