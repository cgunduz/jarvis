package com.cemgunduz.jarvis.publish;

import java.util.Map;

/**
 * Created by cem on 04/07/16.
 */
public interface Publishable {

    Type getType();
    String getTitle();
    String getLink();
    String getDescription();
    Map<String, Object> getInfoMap();

    enum Severity
    {
        LOW, MEDIUM, HIGH
    }

    enum Type
    {
        EKSISOZLUK, ANONYMOUS
    }

    Severity getSeverity();
    Boolean isReportable();
    Boolean isRecordable();
}
