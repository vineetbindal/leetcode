package com.leetcode;

import java.util.*;

class LC355Twitter {
    int seqId = 0;
    HashMap<Integer, LinkedHashSet<Integer>> newsfeedMap;
    HashMap<Integer, ArrayList<Integer>> tweetMap;
    HashMap<Integer, ArrayList<Integer>> followerMap;
    List<Integer> userFreq;

    /** Initialize your data structure here. */
    public LC355Twitter() {
        tweetMap = new HashMap<Integer, ArrayList<Integer>>();
        followerMap = new HashMap<Integer, ArrayList<Integer>>();
        userFreq = new ArrayList<>();
    }
    
    /** Compose a new tweet. */
    public void postTweet(int userId, int tweetId) {
        addTweets(userId,tweetId);
        userFreq.add(userId);
    }
    
    /** Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent. */
    public List<Integer> getNewsFeed(int userId) {
        Map<Integer, Integer> indexMap = new HashMap<>();
        List<Integer> result = new ArrayList<>();
        ArrayList<Integer> followee = followerMap.get(userId);
        // start from end becaouse most recent are at the end
        for (int i = userFreq.size() -1 ; i>= 0; i--) {
            if (userId == userFreq.get(i) || (followee != null && followee.contains(userFreq.get(i)))) {
                // start from end becaouse most recent are at the end
                Integer startIndex = tweetMap.get(userFreq.get(i)).size() - 1;
                //int index = indexMap.getOrDefault(userFreq.get(i), startIndex);
                int index = indexMap.containsKey(userFreq.get(i)) ? indexMap.get(userFreq.get(i)) : startIndex;
                result.add(tweetMap.get(userFreq.get(i)).get(index));
                index--;
                indexMap.put(userFreq.get(i), index);
            }
            if (result.size() == 10) break;
        }
        return result;
    }
    
    /** Follower follows a followee. If the operation is invalid, it should be a no-op. */
    public void follow(int followerId, int followeeId) {
        if (followerId == followeeId) return;
        ArrayList<Integer> set = new ArrayList<>();
        if (followerMap.containsKey(followerId)) {
            set = followerMap.get(followerId);
        }
        set.add(followeeId);
        followerMap.put(followerId, set);
    }
    
    /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
    public void unfollow(int followerId, int followeeId) {
         if (followerId == followeeId) return;
        if (followerMap.containsKey(followerId)) {
            ArrayList<Integer> list = followerMap.get(followerId);
            list.remove(Integer.valueOf(followeeId));
        }
    }
    
    private void addTweets(int userId, int tweetId) {
        ArrayList<Integer> tweets = new ArrayList<>();
        if (tweetMap.containsKey(userId)) {
            tweets = tweetMap.get(userId);
        }
        tweets.add(tweetId);
        tweetMap.put(userId, tweets);
        
    }
}

/**
 * Your Twitter object will be instantiated and called as such:
 * Twitter obj = new Twitter();
 * obj.postTweet(userId,tweetId);
 * List<Integer> param_2 = obj.getNewsFeed(userId);
 * obj.follow(followerId,followeeId);
 * obj.unfollow(followerId,followeeId);
 */
