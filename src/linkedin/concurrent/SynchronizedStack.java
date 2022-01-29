package linkedin.concurrent;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/*
There are several reasons to avoid locks which we described in detail in the Atomics section.
The crux is that locking is a heavyweight synchronization mechanism that incurs significant overhead
 */
public class SynchronizedStack<T> {

    StackNode<T> top;

    public synchronized void push(T item) {
        if(top == null) {
            top = new StackNode(item);
        } else {
            StackNode<T> tmp = top;
            top = new StackNode(item);
            top.setNext(tmp);
        }
    }
    public synchronized T pop() {
        if(top == null) return null;
        StackNode<T> tmp = top;
        top = tmp.getNext();
        return tmp.getItem();
    }
}

class StackNode<T> {
    private StackNode<T> next;
    private T item;
    public StackNode(T item) {
        this.item = item;
    }
    public void setNext(StackNode<T> node) {
        next = node;
    }
    public StackNode<T> getNext() {
        return this.next;
    }
    public void setItem(T item) {
        this.item = item;
    }
    public T getItem() {
        return this.item;
    }
}

class SimulatedCompareAndSwap<T> {

    private T value;

    // constructor to initialize the value
    public SimulatedCompareAndSwap(T initValue) {
        value = initValue;
    }

    synchronized T getValue() {
        return value;
    }

    synchronized T compareAndSwap(T expectedValue, T newValue) {

        if (value == null) {
            if (expectedValue == null) {
                value = newValue;
            }
            return null;
        }

        if (value.equals(expectedValue)) {
            value = newValue;
            return expectedValue;
        }

        // return the current value
        return value;
    }

    // This method uses the compareAndSwap() method to indicate if the CAS
    // instruction completed successfully or not.
    synchronized boolean compareAndSet(T expectedValue, T newValue) {
        T returnVal = compareAndSwap(expectedValue, newValue);

        if (returnVal == null && expectedValue == null) return true;
        else if (returnVal == null && expectedValue != null) return false;
        else {
            return returnVal.equals(expectedValue);
        }
    }
}

class CASBasedStack<T> {

    private SimulatedCompareAndSwap<StackNode<T>> simulatedCAS;

    public CASBasedStack() {
        simulatedCAS = new SimulatedCompareAndSwap<>(null);

    }

    public void push(T item) {

        StackNode<T> oldHead;
        StackNode<T> newHead;

        do {
            // retrieve the current value of top
            oldHead = simulatedCAS.getValue();
            // create a new StackNode for the passed-in item.
            newHead = new StackNode<>(item);
            // Adjust the pointer
            newHead.setNext(oldHead);
        } while (!simulatedCAS.compareAndSet(oldHead, newHead));
        // attempt to atomically check and update
    }

    public T pop() {

        StackNode<T> returnValue;
        StackNode<T> newHead;

        do {
            // get the current top of the stack
            returnValue = simulatedCAS.getValue();
            // if the top is null then simply return null
            if (returnValue == null) return null;
            // compute the new top of stack
            newHead = returnValue.getNext();
        } while (!simulatedCAS.compareAndSet(returnValue, newHead));
        // attempt to update the new top of stack

        return returnValue.getItem();
    }
}

class NonblockingStack<T> {
    private AtomicInteger count = new AtomicInteger(0);
    private AtomicReference<StackNode<T>> top = new AtomicReference();
    public int size() {
        return count.get();
    }
    public void push(T item) {
        StackNode<T> new_node, old_node;
        do {
            new_node = new StackNode(item);
            old_node = top.get();
            new_node.setNext(old_node);
        }while(!top.compareAndSet(old_node, new_node));
        count.incrementAndGet();
    }
    public T get() {
        StackNode<T> return_node, new_top;
        do {
            return_node = top.get();
            if(return_node == null) return null;
            new_top = return_node.getNext();
        }while(!top.compareAndSet(return_node, new_top));
        count.decrementAndGet();
        return return_node.getItem();
    }
}