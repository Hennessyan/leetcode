package linkedin.concurrent;

import java.util.Arrays;
import java.util.Collections;

public class H2OMachine {

    Object object;
    String[] molecule;
    int count;
    public H2OMachine() {
        object = new Object();
        molecule = new String[3];
        count = 0;
    }

    public void HydrogenAtom() throws InterruptedException {
        synchronized(object) {
            while(Collections.frequency(Arrays.asList(molecule), "H") == 2) {
                object.wait();
            }
            molecule[count++] = "H";
            if(count == 3) {
                System.out.println(Arrays.toString(molecule));
                count = 0;
                Arrays.fill(molecule, null);
            }
            object.notifyAll();
        }
    }

    public void OxygenAtom() throws InterruptedException {
        synchronized(object) {
            while(Collections.frequency(Arrays.asList(molecule), "O") == 1) {
                object.wait();
            }
            molecule[count++] = "O";
            if(count == 3) {
                System.out.println(Arrays.toString(molecule));
                count = 0;
                Arrays.fill(molecule, null);
            }
            object.notifyAll();
        }
    }
}
