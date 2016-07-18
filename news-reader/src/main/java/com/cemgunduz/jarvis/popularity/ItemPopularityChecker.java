package com.cemgunduz.jarvis.popularity;

import com.cemgunduz.jarvis.reader.eksisozluk.persistence.EksiTopic;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * Created by cem on 18/07/16.
 */
public abstract class ItemPopularityChecker<T extends PopulerItem> implements PopularityChecker {

    private List<T> topicsToBeAnalyzed;

    public ItemPopularityChecker(List<T> topicsToBeAnalyzed, int topicSize) {
        this.topicsToBeAnalyzed = topicsToBeAnalyzed.subList(0, topicSize);
    }

    public Integer getPopulariyRating(int no)
    {
        return topicsToBeAnalyzed.get(no).getPopularity();
    }
}
