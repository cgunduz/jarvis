package com.cemgunduz.jarvis.publish.news;

import com.cemgunduz.jarvis.publish.Publishable;

import java.util.Map;

/**
 * Created by cem on 04/07/16.
 */
public class News implements Publishable {

    private String type;
    private String title;
    private String link;
    private String description;
    private Map<String, Object> infoMap;
    private Severity severity;

    @Override
    public String getType() {
        return type;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getLink() {
        return link;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public Map<String, Object> getInfoMap() {
        return infoMap;
    }

    @Override
    public Severity getSeverity() {
        return severity;
    }

    @Override
    public Boolean isReportable() {
        return severity.equals(Severity.HIGH);
    }

    @Override
    public Boolean isRecordable() {
        return severity.equals(Severity.HIGH) ||
                severity.equals(Severity.MEDIUM);
    }

    public News(String type, String title, String link, String description,
                Map<String, Object> infoMap, Severity severity) {
        this.type = type;
        this.title = title;
        this.link = link;
        this.description = description;
        this.infoMap = infoMap;
        this.severity = severity;
    }
}
