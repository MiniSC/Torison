import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class MyReechantLock implements Lock {

    boolean isLocked = false;
    Thread locakedBy = null;
    int lockedCount = 0;

    @Override
    public synchronized void lock() {

    }

    @Override
    public synchronized void lockInterruptibly() throws InterruptedException {
           Thread thread = Thread.currentThread();
           while (isLocked&&locakedBy!=thread){
               wait();
           }
           isLocked = true;
           lockedCount ++;
           locakedBy = thread;
    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public synchronized void unlock() {

        if (Thread.currentThread() == this.locakedBy){
            lockedCount--;
            if (lockedCount == 0){
                isLocked = false;
                notify();
            }
        }
    }

    @Override
    public Condition newCondition() {
        return null;
    }
}
