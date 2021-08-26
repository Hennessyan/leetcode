package amazon;

import java.util.*;

// Analyze User Website Visit Pattern
public class Q1152 {
    // O(nlgn) O(C(n,3) * len of max website)
    public List<String> mostVisitedPattern(String[] username, int[] timestamp, String[] website) {
        Map<String, List<Integer>> visitMap = new HashMap<>();
        int n = username.length;
        for(int i = 0; i < n; i++) {
            visitMap.computeIfAbsent(username[i], x -> new ArrayList<>()).add(i);
        }

        Map<String, Integer> sequenceMap = new HashMap<>();
        for(List<Integer> list : visitMap.values()) {
            if(list.size() < 3) {
                continue;
            }
            Collections.sort(list, (l1, l2) -> timestamp[l1] - timestamp[l2]);
            Set<String> seen = new HashSet<>();
            for(String seq : getSequence(list, website)) {
                if(seen.add(seq)) {
                    sequenceMap.put(seq, sequenceMap.getOrDefault(seq, 0) + 1);
                }
            }
        }
        int max = 0;
        String seq = "";
        for(String key : sequenceMap.keySet()) {
            int fre = sequenceMap.get(key);
            if(fre > max) {
                max = fre;
                seq = key;
            } else if(fre == max && seq.compareTo(key) > 0) {
                seq = key;
            }
        }
        return Arrays.asList(seq.split("\\."));
    }
    private List<String> getSequence(List<Integer> list, String[] website) {
        List<String> sequences = new ArrayList<>();
        int size = list.size();
        for(int i = 0; i < size - 2; i++) {
            for(int j = i + 1; j < size - 1; j++) {
                for(int k = j + 1; k < size; k++) {
                    sequences.add(website[list.get(i)]+"."+website[list.get(j)]+"."+website[list.get(k)]);
                }
            }
        }
        return sequences;
    }
    // method2
    public List<String> mostVisitedPattern1(String[] username, int[] timestamp, String[] website) {
        //To avoid array re-size
        List<Record> records = new ArrayList<>(username.length);
        //One traverse for all arrays to create list of records
        for (int i = 0; i < username.length; i++) {
            records.add(new Record(username[i], timestamp[i], website[i]));
        }
        //Sort should be done in N*log(N)
        Collections.sort(records);

        //HashMap will provide amortized O(1) access
        Map<VisitPattern, Integer> m = new HashMap<>();
        String name = records.get(0).username;
        int start = 0;
        VisitPattern maxPattern = null;
        //Might be redundant but access like this one would be guaranted O(1)
        int maxVisits = 0;
        //Another loop with O(N)
        for (int i = 1; i < records.size(); i++){
            String nextName = records.get(i).username;
            if (!name.equals(nextName) || i == records.size() - 1) {
                //Special trick in case if loop is at the end and last user still should be processed
                if (name.equals(nextName) && i == records.size() - 1) {
                    i++;
                }
                //Another set to keep unique combinations for particular user
                Set<VisitPattern> userSet = new HashSet<>();
                for (int a = start; a < i - 2; a++) {
                    for (int b = a + 1; b < i - 1; b++) {
                        for (int c = b + 1; c < i; c++) {
                            VisitPattern vp = new VisitPattern(
                                    records.get(a).website,
                                    records.get(b).website,
                                    records.get(c).website
                            );
                            //Add only new combination
                            if (!userSet.contains(vp)) {
                                userSet.add(vp);
                                int visits = m.compute(vp, (key, val) -> {
                                    if (val == null) {
                                        return 1;
                                    } else {
                                        return val + 1;
                                    }
                                });
                                //Both conditions should result in change of maxPattern. Constant access to maxVisits will save a bit of time
                                if ((visits > maxVisits) || (visits == maxVisits
                                        && vp.compareTo(maxPattern) < 0)) {
                                    maxPattern = vp;
                                    maxVisits = visits;
                                }
                            }
                        }
                    }
                }
                //Slide to next user
                start = i;
                name = nextName;
            }
        }
        //Return maxPattern as list
        return Arrays.asList(maxPattern.a, maxPattern.b, maxPattern.c);
    }

    private class Record implements Comparable<Record> {
        String username;
        int timestamp;
        String website;

        Record(String username, int timestamp, String website) {
            this.username = username;
            this.timestamp = timestamp;
            this.website = website;
        }

        public int compareTo(Record other) {
            int result = this.username.compareTo(other.username);
            if (result == 0) {
                result = Integer.compare(this.timestamp, other.timestamp);
                if (result == 0) {
                    result = this.website.compareTo(other.website);
                }
            }
            return result;
        }

        public String toString() {
            return "{" + this.username + "," + this.timestamp + "," + this.website + "}";
        }
    }

    private class VisitPattern implements Comparable<VisitPattern> {
        String a;
        String b;
        String c;

        VisitPattern (String a, String b, String c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }

        public int compareTo(VisitPattern other) {
            int result = this.a.compareTo(other.a);
            if (result == 0) {
                result = this.b.compareTo(other.b);
                if (result == 0) {
                    result = this.c.compareTo(other.c);
                }
            }
            return result;
        }

        public int hashCode() {
            int result = 31 * a.hashCode();
            result += 31 * result + b.hashCode();
            result += 31 * result + c.hashCode();
            return result;
        }

        public boolean equals(Object o) {
            if (o instanceof VisitPattern) {
                return this.compareTo((VisitPattern) o) == 0;
            } else {
                return false;
            }
        }

        public String toString() {
            return "{" + this.a + "," + this.b + "," + this.c + "}";
        }
    }
}
