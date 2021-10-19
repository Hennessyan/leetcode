package amazon;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

// Unique Email Addresses
public class Q929 {
    // O(nm) O(nm)
    // if we don't care about the combination, use set and local name + domain name.
    // https://leetcode.com/problems/unique-email-addresses/solution/
    public int numUniqueEmails(String[] emails) {
        if(emails == null || emails.length == 0) return 0;
        int ans = 0;
        Map<String, Set<String>> map = new HashMap<>();
        for(String email : emails) {
            String[] names = email.split("\\@");
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < names[0].length(); i++) {
                char c = names[0].charAt(i);
                if(c == '.') continue;
                else if(c == '+') break;
                else sb.append(c);
            }
            if(map.computeIfAbsent(sb.toString(), x -> new HashSet<>()).add(names[1])) {
                ans++;
            }
        }
        return ans;
    }
}
