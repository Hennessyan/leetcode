package linkedin.VO;

import java.util.*;
import java.util.concurrent.*;

// Web Crawler Multithreaded
public class Q1242 {
// https://leetcode.com/problems/web-crawler-multithreaded/discuss/699006/Java-BlockingQueue-%2B-ExecutorService
//    ExecutorService es;
//    public List<String> crawl(String startUrl, HtmlParser htmlParser) {
//        List<String> res = new ArrayList<>();
//        String hostname = getHostName(startUrl);
//        BlockingQueue<String> queue = new LinkedBlockingQueue<>();
//        Deque<Future> tasks = new ArrayDeque<>();
//        Set<String> seen = new HashSet<>();
//        queue.add(startUrl);
//
//        es = Executors.newFixedThreadPool(4, r -> {
//            Thread t = new Thread(r);
//            t.setDaemon(true);
//            return t;
//        });
//
//        while(true) {
//            String url = queue.poll();
//            if(url != null) {
//                if(getHostName(url).equals(hostname) && seen.add(url)) {
//                    res.add(url);
//                    tasks.add(es.submit(() -> {
//                        for(String tmp : htmlParser.getUrls(url)) {
//                            queue.add(tmp);
//                        }
//                    }));
//                }
//            } else {
//                if(!tasks.isEmpty()) {
//                    Future task = tasks.poll();
//                    try {
//                        task.get();
//                    } catch (InterruptedException | ExecutionException e) {
//
//                    }
//                } else break;
//            }
//        }
//        return res;
//    }
//    private String getHostName(String url) {
//        String[] tmp = url.substring(7).split("/");
//        return tmp[0];
//    }
}
