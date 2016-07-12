package com.cemgunduz.jarvis.reader.eksisozluk.persistence;

import com.cemgunduz.jarvis.publish.Publishable;
import com.cemgunduz.jarvis.publish.news.News;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * Created by cem on 12/07/16.
 */

@Document(collection = "EksiTopic")
public class EksiTopic {

    @Id
    String id;
    
    private String topic;
    private String link;
    private Integer entriesToday;
    
    private Date creationDate = new Date();
    private Boolean notified = false;

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Integer getEntriesToday() {
        return entriesToday;
    }

    public void setEntriesToday(Integer entriesToday) {
        this.entriesToday = entriesToday;
    }

    public String getId() {
        return id;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public Boolean getNotified() {
        return notified;
    }

    public void setNotified(Boolean notified) {
        this.notified = notified;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EksiTopic eksiTopic = (EksiTopic) o;

        if (id != null ? !id.equals(eksiTopic.id) : eksiTopic.id != null) return false;
        if (topic != null ? !topic.equals(eksiTopic.topic) : eksiTopic.topic != null) return false;
        return creationDate != null ? creationDate.equals(eksiTopic.creationDate) : eksiTopic.creationDate == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (topic != null ? topic.hashCode() : 0);
        result = 31 * result + (creationDate != null ? creationDate.hashCode() : 0);
        return result;
    }

    public Publishable toPublishable(Publishable.Severity severity)
    {
        String description = "Olaylar olaylar ! - ".concat(String.valueOf(entriesToday));
        
        Publishable publishable = new News(
                Publishable.Type.EKSISOZLUK,
                topic,
                link,
                description,
                null,
                severity
        );
        
        return publishable;
    }
}
