package linkedin.VO;

import java.util.*;

// Find And Replace in String
public class Q833 {

    public String findReplaceString(String s, int[] indices, String[] sources, String[] targets) {
        StringBuilder sb = new StringBuilder();

        int n = s.length(), k = indices.length, i = 0;
        Integer[] sorted = new Integer[k];
        for(int j = 0; j < k; j++) {
            sorted[j] = j;
        }
        Arrays.sort(sorted, (a, b) -> indices[a] - indices[b]);
        for(int j = 0; i < n && j < k; i++) {
            if(i != indices[sorted[j]]) {
                sb.append(s.charAt(i));
            } else {
                if(match(s, i, sources[sorted[j]])) {
                    sb.append(targets[sorted[j]]);
                    i = i + sources[sorted[j]].length() - 1;
                } else {
                    sb.append(s.charAt(i));
                }
                j++;
            }
        }
        while(i < n) {
            sb.append(s.charAt(i++));
        }
        return sb.toString();
    }
    private boolean match(String s, int i, String source) {
        int m = s.length(), n = source.length();
        if(i + n > m) return false;
        int j = 0;
        while(j < n && s.charAt(i + j) == source.charAt(j)) {
            j++;
        }
        return j == n;
    }

    public String findReplaceString2(String s, int[] indices, String[] sources, String[] targets) {
        Map<Integer,Integer> map = new HashMap();
        for(int i = 0; i < indices.length;i++){
            String str1 = s.substring(indices[i], indices[i] + sources[i].length());
            String str2 = sources[i];
            if(str1.equals(str2)){
                map.put(indices[i], i);
            }
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < s.length();){
            if(map.containsKey(i)){
                int idx = map.get(i);
                sb.append(targets[idx]);
                i += sources[idx].length();
            }else{
                sb.append(s.charAt(i++));
            }
        }
        return sb.toString();
    }

    public String findReplaceString1(String S, int[] indexes, String[] sources, String[] targets) {
        List<int[]> sorted = new ArrayList<>();
        for (int i = 0 ; i < indexes.length; i++) sorted.add(new int[]{indexes[i], i});
        Collections.sort(sorted, Comparator.comparing(i -> -i[0]));
        for (int[] ind: sorted) {
            int i = ind[0], j = ind[1];
            String s = sources[j], t = targets[j];
            if (S.substring(i, i + s.length()).equals(s)) S = S.substring(0, i) + t + S.substring(i + s.length());
        }
        return S;
    }
}
