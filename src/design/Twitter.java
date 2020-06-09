package design;

//import com.google.common.base.MoreObjects;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/* 355. Design Twitter
 * https://leetcode.com/problems/design-twitter/
 * 
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */
public class Twitter {
  //  private static Logger log = LoggerFactory.getLogger(Twitter.class);
    
    HashMap<User, Set<User>> follow = new HashMap<>();    
    HashMap<User, List<Tweet>> tweet = new HashMap<>();
    long timeStamp = 0;
    
    //HashMap<Integer,List> followers = new HashMap<Integer,List>();
   // HashMap<Integer,List> tweet = new HashMap<Integer,List>();
    
    public static class User {
        public final long id;
        public final String username;

        public User(long id, String username) {
            this.id = id;
            this.username = username;
        }

        public long getId() { return id; }

		@Override
		public String toString() {
			return "User [id=" + id + ", username=" + username + "]";
		}
    }

    public static class Tweet {
        public final long id;
        public final String message;
        public long timestamp;
        public Tweet(long id, String message, long timestamp) {
            this.id = id;
            this.message = message;
            this.timestamp = timestamp;
            
        }
        public long getId() { return id;}
        public String getMessage () { return message; }
		public long getTimestamp() { return timestamp; }
		
		@Override
		public String toString() {
			return "Tweet [id=" + id + ", message=" + message + ", timestamp=" + timestamp + "]";
		}
    }
    
    public static class TweetComparator implements Comparator<Object>{
    	public int compare(Object o1, Object o2) {
    		Tweet t1 = (Tweet)o1;
    		Tweet t2 = (Tweet)o2;
    		return (int) (t2.timestamp- t1.timestamp);
    	}
    }

    /**
     * Marks follower as following leader so that all messages posted by leader show up in followers news feed.
     *
     * @param follower following User
     * @param leader followed User
     */
    public void follow(User follower, User leader) {
      //  throw new UnsupportedOperationException();
       Set<User> users = follow.getOrDefault(leader, new HashSet<User>());
       users.add(follower);
       follow.put(leader, users);
   
       // user B follower -> user A leader
        // user C follower -> user A leader
        // A <-> B, C 
     
    }
            
    /**
     * Marks follower as no longer following leader.
     *
     * @param follower following User
     * @param leader followed User
     */
    public void unfollow(User follower, User leader) {
       // throw new UnsupportedOperationException();
       Set<User> users = follow.getOrDefault(leader, new HashSet<User>());              
       users.remove(follower);
       follow.put(leader, users);
    }

    /**
     * Posts a tweet to user's timeline, causing it to appear in their follower's news feed.
     *
     * @param user User to post tweet from
     * @param message the message to post
     */
    public void postTweet(User user, String message) {
       // throw new UnsupportedOperationException();
       List<Tweet> t = tweet.getOrDefault(user, new ArrayList<Tweet>());
       t.add(new Tweet(user.getId(), message, timeStamp++));
       tweet.put(user, t);
    }

    /**
     * Get the ten most recent tweets for user in chronological order.
     *
     * @param user User to retrieve tweets for
     * @return a list of tweets
     */
    public List<Tweet> getNewsFeed(User user) {
    	 //  throw new UnsupportedOperationException();
        List<Tweet> feeds = new ArrayList<>();

       List<Tweet> temp = new ArrayList<Tweet>();
       
        if(tweet.containsKey(user)) 
        	temp.addAll(tweet.get(user));
        
        if(follow.containsKey(user)) {
            Set<User> follower = follow.get(user);
            for(User i: follower) {
                if(tweet.containsKey(i)) temp.addAll(tweet.get(i));
            }
        }
        Collections.sort(temp,new TweetComparator());
        for(int i = 0; i < 10 && i < temp.size(); i++) {
            feeds.add(temp.get(i));
        }
      
        return feeds;
    }
    
    public static void main (String[] agrs) {
    	Twitter obj = new Twitter();
    	User u1 = new User(1, "kathy");
    	obj.postTweet(u1 , "hello");
    	obj.postTweet(u1 , "Monday");
    	
    	List<Tweet> list = obj.getNewsFeed(u1); // hello
    	System.out.println("Post of user1:" + list);
    	
    	//user 1 follow leader 1
    	User l1 = new User(2, "Lisa");
    	obj.follow(u1, l1);
    	//leader 1 post a new tweet
    	obj.postTweet(l1 , "New message 1");
    	
    	System.out.println("Feeds of u1: " + obj.getNewsFeed(u1)) ; 
    	System.out.println("Feeds of l1:  " + obj.getNewsFeed(l1)) ; // 
    	
    	obj.unfollow(u1,l1);
    	List<Tweet> list2 = obj.getNewsFeed(u1); //
    	System.out.println("Feeds after unfollow" + list2);
    	
    }
  
}