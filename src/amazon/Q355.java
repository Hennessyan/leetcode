package amazon;

import java.util.*;
// Design Twitter

public class Q355 {

    class Twitter {
        Map<Integer, Set<Integer>> friends;
        Map<Integer, LinkedList<int[]>> tweets;
        int time;
        /** Initialize your data structure here. */
        public Twitter() {
            friends = new HashMap<>();
            tweets = new HashMap<>();
            time = 0;
        }

        /** Compose a new tweet. */
        public void postTweet(int userId, int tweetId) {
            follow(userId, userId);
            tweets.computeIfAbsent(userId, x -> new LinkedList<>()).add(0, new int[]{time++, tweetId});
        }

        /** Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent. */
        public List<Integer> getNewsFeed(int userId) {
            List<Integer> feed = new ArrayList<>();
            PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) ->
                    tweets.get(b[0]).get(b[1])[0] - tweets.get(a[0]).get(a[1])[0]);
            // we don't need L31-L33 because of L21.
            // if(tweets.getOrDefault(userId, new LinkedList<>()).size() > 0) {
            //     pq.offer(new int[]{userId, 0});
            // }
            for(int follow : friends.getOrDefault(userId, new HashSet<>())) {
                if(tweets.getOrDefault(follow, new LinkedList<>()).size() > 0) {
                    pq.offer(new int[]{follow, 0});
                }
            }
            while(!pq.isEmpty()) {
                int[] tmp = pq.poll();
                LinkedList<int[]> ts = tweets.get(tmp[0]);
                feed.add(ts.get(tmp[1])[1]);
                if(++tmp[1] < ts.size()) {
                    pq.offer(tmp);
                }
                if(feed.size() == 10) {
                    break;
                }
            }
            return feed;



        }

        /** Follower follows a followee. If the operation is invalid, it should be a no-op. */
        public void follow(int followerId, int followeeId) {
            friends.computeIfAbsent(followerId, x -> new HashSet<>()).add(followeeId);
        }

        /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
        public void unfollow(int followerId, int followeeId) {
            if(followerId != followeeId && friends.containsKey(followerId)) {
                friends.get(followerId).remove(followeeId);
            }
        }
    }

    class Twitter1 {
        private final int threshold = 10;
        private int timestamp = 0;
        Map<Integer, User> usermap = new HashMap<>();
        class Tweet {
            int tweetId;
            int time;
            Tweet next;
            public Tweet(int tweetId, int timestamp) {
                this.tweetId = tweetId;
                this.time = timestamp;
                this.next = null;
            }
        }

        class User {
            int id;
            Set<User> follows;
            Tweet head;
            public User(int id) {
                this.id = id;
                follows = new HashSet<>();
                follows.add(this);
                this.head = null;
            }
            public void follow(User user) {
                follows.add(user);
            }
            public void unfollow(User user) {
                follows.remove(user);
            }
            public void postTweet(int tweetId, int timestamp) {
                Tweet t = new Tweet(tweetId, timestamp);
                t.next = head;
                head = t;
            }
        }

        /** Initialize your data structure here. */
        public Twitter1() {

        }

        /** Compose a new tweet. */
        public void postTweet(int userId, int tweetId) {
            usermap.putIfAbsent(userId, new User(userId));
            usermap.get(userId).postTweet(tweetId, timestamp++);
        }

        /** Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent. */
        public List<Integer> getNewsFeed(int userId) {
            List<Integer> list = new ArrayList<>();

            if(!usermap.containsKey(userId)) {
                return list;
            }
            PriorityQueue<Tweet> pq = new PriorityQueue<>((t1, t2) -> t2.time - t1.time);
            for(User user : usermap.get(userId).follows) {
                Tweet t = user.head;
                if(t != null) {
                    pq.offer(t);
                }
            }
            while(!pq.isEmpty()) {
                Tweet tmp = pq.poll();
                list.add(tmp.tweetId);
                if(tmp.next != null) {
                    pq.offer(tmp.next);
                }
                if(list.size() == threshold) break;
            }
            return list;
        }

        /** Follower follows a followee. If the operation is invalid, it should be a no-op. */
        public void follow(int followerId, int followeeId) {
            usermap.putIfAbsent(followerId, new User(followerId));
            usermap.putIfAbsent(followeeId, new User(followeeId));
            usermap.get(followerId).follow(usermap.get(followeeId));
        }

        /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
        public void unfollow(int followerId, int followeeId) {
            if(followerId != followeeId && usermap.containsKey(followerId) && usermap.containsKey(followeeId)) {
                usermap.get(followerId).unfollow(usermap.get(followeeId));
            }
        }
    }
}
