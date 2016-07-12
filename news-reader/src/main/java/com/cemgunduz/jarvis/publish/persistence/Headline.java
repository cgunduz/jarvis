package com.cemgunduz.jarvis.publish.persistence;

import com.cemgunduz.jarvis.publish.Publishable;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by cem on 04/07/16.
 */
@Document(collection = "Headline")
public class Headline {

    @Id
    private String id;

    private String title;
    private String source;
    private String link;

    private Boolean delivered = true;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Boolean getDelivered() {
        return delivered;
    }

    public void setDelivered(Boolean delivered) {
        this.delivered = delivered;
    }

    public static Headline toHeadline(Publishable publishable)
    {
        Headline headline = new Headline();

        headline.setLink(
                publishable.getLink()
        );
        headline.setSource(
                publishable.getType().name()
        );
        headline.setTitle(
                publishable.getTitle()
        );

        return headline;
    }
}
