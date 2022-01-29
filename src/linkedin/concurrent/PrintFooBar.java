package linkedin.concurrent;

public class PrintFooBar {

    private int n;
    private int flag = 0;

    public PrintFooBar(int n) {
        this.n = n;
    }

    public void foo() throws InterruptedException {
        for(int i = 0; i < n; i++) {
            synchronized(this) {
                while(flag == 1) {
                    this.wait();
                }
                System.out.println("Foo");
                this.notifyAll();
                flag = 1;
            }
        }
    }

    public void bar() throws InterruptedException {
        for(int i = 0; i < n; i++) {
            synchronized(this) {
                while(flag == 0) {
                    this.wait();
                }
                System.out.println("Bar");
                this.notifyAll();
                flag = 0;
            }
        }
    }
}
