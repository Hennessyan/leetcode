package linkedin.concurrent;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class DeferredCallbackExecutor {

    PriorityQueue<CallBack> q = new PriorityQueue<CallBack>(new Comparator<CallBack>() {

        public int compare(CallBack o1, CallBack o2) {
            return (int) (o1.executeAt - o2.executeAt);
        }
    });
    ReentrantLock lock;
    Condition newCallbackArrived;

    public DeferredCallbackExecutor() {
        lock = new ReentrantLock();
        newCallbackArrived = lock.newCondition();
    }
    // Run by the Executor Thread
    public void start() throws InterruptedException {
        long sleepFor = 0;

        while(true) {
            lock.lock();
            while(q.isEmpty()) {
                newCallbackArrived.await();
            }
            while(q.size() > 0) {
                sleepFor = findSleepDuration();
                if(sleepFor <= 0) break;
                newCallbackArrived.await(sleepFor, TimeUnit.MILLISECONDS);
            }
            CallBack cb = q.poll();
            System.out.println(cb.message);

            lock.unlock();
        }
    }

    private long findSleepDuration() {
        long currentTime = System.currentTimeMillis();
        return q.peek().executeAt - currentTime;
    }
    // Called by Consumer Threads to register callback
    public void registerCallback(CallBack callBack) {
        lock.lock();
        q.offer(callBack);
        newCallbackArrived.signal();
        lock.unlock();
    }

    /**
     * Represents the class which holds the callback. For simplicity instead of
     * executing a method, we print a message.
     */
    static class CallBack {

        long executeAt;
        String message;

        public CallBack(long executeAfter, String message) {
            this.executeAt = System.currentTimeMillis() + executeAfter * 1000;
            this.message = message;
        }
    }
}
