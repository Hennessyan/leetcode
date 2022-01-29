package amazon;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

// Maximum XOR of Two Numbers in an Array
public class Q421 {
    //  O(n) O(n)
    public int findMaximumXOR(int[] nums) {
        int max = nums[0];
        for(int num : nums) {
            max = Math.max(num, max);
        }
        int L = Integer.toBinaryString(max).length();
        Set<Integer> prefix = new HashSet<>();
        int maxXOR = 0, curXOR = 0;
        for(int i = L - 1; i >= 0; i--) {
            maxXOR <<= 1;
            curXOR = maxXOR | 1;
            prefix.clear();
            for(int num : nums) {
                prefix.add(num >> i);
            }
            for(int p : prefix) {
                if(prefix.contains(p ^ curXOR)) {
                    maxXOR = curXOR;
                    break;
                }
            }
        }
        return maxXOR;
    }
    // todo : review this solution
    public int findMaximumXOR1(int[] nums) {
        // Compute length L of max number in a binary representation
        int maxNum = nums[0];
        for(int num : nums) maxNum = Math.max(maxNum, num);
        int L = (Integer.toBinaryString(maxNum)).length();

        // zero left-padding to ensure L bits for each number
        int n = nums.length, bitmask = 1 << L;
        String [] strNums = new String[n];
        for(int i = 0; i < n; ++i) {
            strNums[i] = Integer.toBinaryString(bitmask | nums[i]).substring(1);
        }

        TrieNode trie = new TrieNode();
        int maxXor = 0;
        for (String num : strNums) {
            TrieNode node = trie, xorNode = trie;
            int currXor = 0;
            for (Character bit : num.toCharArray()) {
                // insert new number in trie
                if (node.children.containsKey(bit)) {
                    node = node.children.get(bit);
                } else {
                    TrieNode newNode = new TrieNode();
                    node.children.put(bit, newNode);
                    node = newNode;
                }

                // compute max xor of that new number
                // with all previously inserted
                Character toggledBit = bit == '1' ? '0' : '1';
                if (xorNode.children.containsKey(toggledBit)) {
                    currXor = (currXor << 1) | 1;
                    xorNode = xorNode.children.get(toggledBit);
                } else {
                    currXor = currXor << 1;
                    xorNode = xorNode.children.get(bit);
                }
            }
            maxXor = Math.max(maxXor, currXor);
        }

        return maxXor;
    }

    class TrieNode {
        HashMap<Character, TrieNode> children = new HashMap<Character, TrieNode>();
        public TrieNode() {}
    }
}
