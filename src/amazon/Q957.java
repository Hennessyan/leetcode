package amazon;

import java.util.HashMap;
import java.util.Map;

// Prison Cells After N Days
public class Q957 {
    // O(min(n, 2^k)) O(2^k) : k - len of cells
    public int[] prisonAfterNDays(int[] cells, int n) {
        Map<Integer, Integer> map = new HashMap<>();
        boolean fastForward = false;

        int stateBit = 0x0;
        for(int cell : cells) {
            stateBit <<= 1;
            stateBit |= cell;
        }

        while(n > 0) {
            if(!fastForward) {
                if(map.containsKey(stateBit)) {
                    fastForward = true;
                    n %= map.get(stateBit) - n;
                } else {
                    map.put(stateBit, n);
                }
            }
            if(n > 0) {
                n--;
                stateBit = nextDay(stateBit);
            }
        }
        int[] res = new int[cells.length];
        for(int i = res.length - 1; i >= 0; i--) {
            res[i] = stateBit & 1;
            stateBit >>= 1;
        }
        return res;
    }

    private int nextDay(int stateBit) {
        stateBit = ~ ((stateBit << 1) ^ (stateBit >> 1));
        return stateBit & 0x7e; // remove first and last bit one
    }
    // O(k) if use below method
    protected int[] nextDay(int[] cells) {
        int[] newCells = new int[cells.length];
        newCells[0] = 0;
        for (int i = 1; i < cells.length - 1; i++)
            newCells[i] = (cells[i - 1] == cells[i + 1]) ? 1 : 0;
        newCells[cells.length - 1] = 0;
        return newCells;
    }
}
