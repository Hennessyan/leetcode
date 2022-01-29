package linkedin.concurrent;

// A singleton pattern allows only a single object/instance of a class to ever exist during an application run.
// 1. Declaring the constructor of a class private
// 2. The second trick is to create a public static method usually named getInstance() to return the only instance
public class SupermanNaiveButCorrect {

    private static SupermanNaiveButCorrect superman = new SupermanNaiveButCorrect();

    private SupermanNaiveButCorrect() {}

    public static SupermanNaiveButCorrect getInstance() {
        return superman;
    }

    // Object method
    public void fly() {
        System.out.println("I am Superman & I can fly !");
    }
}
// needs lazily initialize
//class SupermanNaiveButCorrect {
//
//    private static SupermanWithFlaws superman;
//    private SupermanWithFlaws() {}
//
//    // This will fail with multiple threads
//    public static SupermanWithFlaws getInstance() {
//        if (superman == null) {
//            // A thread can be context switched at this point and
//            // superman will evaluate to null for any other threads
//            // testing the if condition. Now multiple threads will
//            // fall into this if clause till the superman object is
//            // assigned a value. All these threads will initialize the
//            // superman object when it should have been initialized
//            // only one.
//            superman = new SupermanWithFlaws();
//        }
//        return superman;
//    }
//}

class SupermanCorrectButSlow {

    private static SupermanCorrectButSlow superman;

    private SupermanCorrectButSlow() {}

    public static SupermanCorrectButSlow getInstance() {
        // synchronized(this) is not correct here as it's static method.
        // use either below way or add synchronized to method()
        synchronized(SupermanCorrectButSlow.class) {
            if (superman == null) {
                superman = new SupermanCorrectButSlow();
            }
        }
        return superman;
    }

    // Object method
    public void fly() {
        System.out.println("I am Superman & I can fly !");
    }
}
/*
Above code is expensive. Can we synchronize only when initializing the singleton instance and not at other times?
The answer is yes and leads us to an implementation known as double checked locking pattern.
*/
class SupermanSlightlyBetter {

    private static volatile SupermanSlightlyBetter superman;

    private SupermanSlightlyBetter() {}

    public static SupermanSlightlyBetter getInstance() {
        if(superman == null) {
            synchronized(SupermanSlightlyBetter.class) {
                if(superman == null) {
                    superman = new SupermanSlightlyBetter();
                }
            }
        }
        return superman;
    }
}
// still a problem: The memory model defines what state a thread may
// see when it reads a memory location modified by other threads =>
// The happens-before semantics of volatile guarantee that the faulty scenario of threads A and B never happens
// https://www.educative.io/courses/java-multithreading-for-senior-engineering-interviews/3jYKmrVAPGQ