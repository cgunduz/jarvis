package com.cemgunduz.jarvis.reader.reddit.worldnews;

import com.cemgunduz.jarvis.popularity.PopulerItem;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by cem on 18/07/16.
 */
@Document(collection = "RedditWorldNewsTopic")
public class RedditWorldNewsTopic implements PopulerItem {

    @Id
    private String id;

    private String redditId;
    private String redditAuthor;
    private Long timestamp;
    private String url;

    private Integer upvotes;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRedditId() {
        return redditId;
    }

    public void setRedditId(String redditId) {
        this.redditId = redditId;
    }

    public String getRedditAuthor() {
        return redditAuthor;
    }

    public void setRedditAuthor(String redditAuthor) {
        this.redditAuthor = redditAuthor;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getUpvotes() {
        return upvotes;
    }

    public void setUpvotes(Integer upvotes) {
        this.upvotes = upvotes;
    }

    @Override
    public int getPopularity() {
        return getUpvotes();
    }
}
