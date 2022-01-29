package linkedin.concurrent;

import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

// https://silhding.github.io/2021/03/13/How-to-design-a-delayed-scheduler-in-Java/
public class DelayScheduler {
    private final DelayQueue<DelayTask> queue;

    public DelayScheduler() {
        this.queue = new DelayQueue<>();
        this.startExecute();
    }
    public Future schedule(Runnable task, long delayTime) {
        DelayTask newTask = new DelayTask(task, delayTime);
        this.queue.offer(newTask);
        return newTask;
    }

    private void startExecute() {
        Runnable execute = () -> {
            while(true) {
                try {
                    DelayTask task = this.queue.take();
                    task.run();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        new Thread(execute, "Executor").start();
    }
}
class DelayTask extends FutureTask implements Delayed {

    private final long startTime;
    private final Runnable task;

    public DelayTask(Runnable task, long delayTime) {
        super(task, null);  // null is the return value for the runnable tasks
        this.startTime = System.currentTimeMillis() + delayTime;
        this.task = task;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        long diff = this.startTime - System.currentTimeMillis();
        return unit.convert(diff, TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        return Long.compare(this.getDelay(TimeUnit.MILLISECONDS), ((DelayTask) o).getDelay(TimeUnit.MILLISECONDS));
    }
}

class DelayedTask extends FutureTask implements Delayed {

    private final long startTime;
    private final Runnable task;
    public DelayedTask(Runnable task, long delay) {
        super(task, null);
        this.startTime = delay + System.nanoTime();
        this.task = task;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        long diff = this.startTime - System.nanoTime();
        return unit.convert(diff, TimeUnit.NANOSECONDS);
    }
    @Override
    public int compareTo(Delayed that) {
        return Long.compare(this.getDelay(TimeUnit.NANOSECONDS), ((DelayedTask) that).getDelay(TimeUnit.NANOSECONDS));
    }
}
class DelayedTaskScheduler {
    private final PriorityBlockingQueue<DelayedTask> queue;
    private final ReentrantLock lock;
    private final Condition condition;
    public DelayedTaskScheduler() {
        queue = new PriorityBlockingQueue<>();
        lock = new ReentrantLock();
        condition = lock.newCondition();
    }

    public void put(DelayedTask task) {
        lock.lock();
        try {
            queue.offer(task);
            if(queue.peek() == task) {
                this.condition.signal();
            }
        } finally {
            lock.unlock();
        }
    }
    public DelayedTask take() throws InterruptedException {
        lock.lock();
        try {
            while(true) {
                DelayedTask peekTask = this.queue.peek();
                if(peekTask == null) {
                    condition.await();
                } else {
                    long delay = peekTask.getDelay(TimeUnit.NANOSECONDS);
                    if(delay <= 0) return queue.poll();
                    condition.awaitNanos(delay);
                }
            }
        } finally {
            lock.unlock();
        }
    }
}