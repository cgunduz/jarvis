package com.cemgunduz.jarvis.reader;

import com.cemgunduz.jarvis.publish.Publishable;

import java.util.List;

/**
 * Created by cem on 12/07/16.
 */
public interface Reader {

    List<Publishable> read();
    PublishMethod getPublishMethod();
}
