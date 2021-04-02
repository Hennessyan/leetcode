package google;
// Guess the Word
public class Q843 {

    /**
     * 两个单词0匹配的可能性是(25/26)^6=80%,也就是说有80%的可能性当前candidate跟secret0匹配.
     * 这时,和candidate不是0匹配的单词就会作为新的候选者.所以每次我们选择跟当前list中0匹配最少的
     * 单词作为候选者,这样可以最大概率的排除不符合要求的单词.
     * 当新的一轮时,我们根据返回的匹配度,来寻找list中和之前candidate匹配度相同的作为新的list,这个
     * 新的list中我们再寻找和其他0匹配最少的单词作为新的candidate.
     * 每次都选0匹配是因为它最大概率上排除最多的不符合要求的单词.
     */
	/* O(n^2) O(n)
	public void findSecretWord(String[] wordlist, Master master) {
        List<String> list = Arrays.asList(wordlist);
        for(int i = 0; i < 10; i++) {
            Map<String, Integer> zeroMatch = new HashMap<>();
            for(String s : list) {
                zeroMatch.putIfAbsent(s, 0);
                for(String s1 : list) {
                    if(match(s, s1) == 0) {
                        zeroMatch.put(s, zeroMatch.get(s) + 1);
                    }
                }
            }
            String candidate = "";
            int maxMatch = 100;
            for(String key : zeroMatch.keySet()) {
                if(zeroMatch.get(key) < maxMatch) {
                    candidate = key;
                    maxMatch = zeroMatch.get(key);
                }
            }
            int matchRes = master.guess(candidate);
            List<String> tmp = new ArrayList<>();
            for(String s : list) {
                if(match(s, candidate) == matchRes) {
                    tmp.add(s);
                }
            }
            list = tmp;
        }
    }
    private int match(String s1, String s2) {
        int num = 0;
        for(int i = 0; i < s1.length(); i++) {
            if(s1.charAt(i) == s2.charAt(i)) {
                num++;
            }
        }
        return num;
    }
    */
}
