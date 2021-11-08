package amazon;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

// Encode and Decode TinyURL
public class Q535 {

    String base62 = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    Map<String, String> map = new HashMap<>();
    Random random = new Random();
    // Encodes a URL to a shortened URL.
    public String encode(String longUrl) {
        String key = "";
        while(map.containsKey(key)) {
            key = getRandom();
        }
        map.put(key, longUrl);
        return "http://tinyurl.com/" + key;
    }

    // Decodes a shortened URL to its original URL.
    public String decode(String shortUrl) {
        StringBuilder sb = new StringBuilder();
        int i = shortUrl.length() - 1;
        while(shortUrl.charAt(i) != '/') {
            sb.append(shortUrl.charAt(i--));
        }
        return map.get(sb.reverse().toString());
    }
    private String getRandom() {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < 6; i++) {
            int index = random.nextInt(62);
            sb.append(base62.charAt(index));
        }
        return sb.toString();
    }
}
