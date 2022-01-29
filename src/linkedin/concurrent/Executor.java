package linkedin.concurrent;

interface Callback {
    public void done();
}

class Demon {

    public static void main(String[] args) throws Exception {
        Executor executor = new Executor();
        executor.asynchronousExecution(() -> {
            System.out.println("I am done");
        });

        System.out.println("main thread exiting...");
    }
}
public class Executor {

    public void asynchronousExecution(Callback callback) throws Exception {
        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException ie) {
            }
            callback.done();
        });
        thread.start();
    }
}
class SyncExecutor extends Executor {

    /*
    Follow-up Question: Is the method asynchronousExecution() thread-safe?

    The way we have constructed the logic, all the variables in the overridden method will be created on
    the thread-stack for each thread therefore the method is threadsafe and multiple threads can execute it in parallel.
     */
    @Override
    public void asynchronousExecution(Callback callback) throws Exception {
        Object signal = new Object();

        // When a lambda expression uses an assigned local variable from its enclosing space
        // there’s a crucial restriction. A lambda expression may only use a local variable whose
        // value doesn’t change. That restriction is referred to as “variable capture” which is described as;
        // lambda expression capture values, not variables.

        // We can’t declare it final because isDone gets assigned to after initialization
        // hence we use array for final rather than boolean isDone directly.
        final boolean[] IS_DONE = new boolean[1];
        Callback cb = new Callback() {
          @Override
          public void done() {
              callback.done();
              synchronized(signal) {
                  signal.notify();
                  IS_DONE[0] = true;
              }
          }
        };
        super.asynchronousExecution(cb);
        synchronized(signal) {
            while (!IS_DONE[0]) {
                signal.wait();
            }
        }
    }
}