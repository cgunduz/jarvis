package com.cemgunduz.jarvis.popularity.impl;

import com.cemgunduz.jarvis.popularity.PopulerItem;
import com.cemgunduz.jarvis.reader.eksisozluk.persistence.EksiTopic;
import com.cemgunduz.jarvis.popularity.ItemPopularityChecker;

import java.util.List;
import java.util.Set;

/**
 * Created by cem on 18/07/16.
 */
public class ExponentialPopularityChecker<T extends PopulerItem> extends ItemPopularityChecker {

    private static Integer CAP = 4;

    public ExponentialPopularityChecker(List<T> topicsToBeAnalyzed) {
        super(topicsToBeAnalyzed, CAP);
    }

    public ExponentialPopularityChecker(List<T> topicsToBeAnalyzed, int topicSize) {
        super(topicsToBeAnalyzed, topicSize);
        CAP = topicSize;
    }

    @Override
    public boolean isPopular() {

        for(int i = 0; i < CAP - 1; i++)
        {
            if(getPopulariyRating(i) < 2 * getPopulariyRating(i+1))
                return false;
        }

        return true;
    }

    public static int getMinimumAmountOfItemsNeeded() {
        return CAP;
    }
}
