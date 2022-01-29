package linkedin.concurrent;

// https://www.educative.io/courses/java-multithreading-for-senior-engineering-interviews/xV9mMjj74gE
public class Barrier {
    int count = 0;
    int totalThreads;
    public Barrier(int total) {
        this.totalThreads = total;
    }
    public synchronized void await() throws InterruptedException {
        count++;
        if(count == totalThreads) {
            this.notifyAll();
            count = 0;
        } else {
            this.wait();
        }
    }
}
// 如果我们能够保证不会发生虚假的唤醒，那么前面的代码将会是可靠的。

class Barrier1 {
    int count = 0;
    int totalThreads;
    int released = 0;
    public Barrier1(int total) {
        this.totalThreads = total;
    }
    /*
     should start next round until all threads are passed
     but if t1 and t2 are waiting, t3 called twice, and second time of t3 call await() again before
     t1 and t2 execute L42-43, second t3 will pass the barrier
     or cause deadlock as count < totalThreads if t1 and t2 still in the while loop.
    */
    /*
     If thread t3 attempts to invoke await() immediately after exiting it and is also
     granted the monitor before threads t1 or t2 get a chance to acquire the monitor then
     the count variable will be incremented to 4.

     Another flaw with the above code is, it can cause a deadlock.
     Suppose we wanted the three threads t1, t2, and t3 to congregate at a barrier twice.
     The first invocation was in the order [t1, t2, t3] and the second was in the order [t3, t2, t1].
     If t3 immediately invoked await after the first barrier, it would go past the second barrier without
     stopping while t2 and t1 would become stranded at the second barrier, since count would never equal totalThreads
     */
    public synchronized void await() throws InterruptedException {
        count++;
        if(count == totalThreads) {
            this.notifyAll();
            released = totalThreads;
        } else {
            while(count < totalThreads) {
                this.wait();
            }
        }
        released--;
        if(released == 0) count = 0;
    }
    // last correct version !!!
    // wait() should always be used with a while loop that checks for a condition and if found false should make the thread wait again
    public synchronized void await1() throws InterruptedException {

        while (count == totalThreads)
            wait();

        count++;

        if (count == totalThreads) {
            notifyAll();
            released = totalThreads;
        } else {

            while (count < totalThreads)
                wait();
        }

        released--;
        if (released == 0) {
            count = 0;
            // remember to wakeup any threads
            // waiting on line#64
            notifyAll();
        }
    }
}