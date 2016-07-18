package com.cemgunduz.jarvis.popularity.impl;

import com.cemgunduz.jarvis.popularity.ItemPopularityChecker;
import com.cemgunduz.jarvis.popularity.PopulerItem;

import java.util.List;

/**
 * Created by cem on 18/07/16.
 */
public class LinearPopularityChecker<T extends PopulerItem> extends ItemPopularityChecker {

    private static Integer CAP = 12;

    public LinearPopularityChecker(List<T> topicsToBeAnalyzed) {
        super(topicsToBeAnalyzed, CAP);
    }

    public LinearPopularityChecker(List<T> topicsToBeAnalyzed, int topicSize) {
        super(topicsToBeAnalyzed, topicSize);
        CAP = topicSize;
    }

    @Override
    public boolean isPopular() {

        for(int i = 0; i < CAP - 1; i++)
        {
            if(getPopulariyRating(i) <= getPopulariyRating(i+1))
                return false;
        }

        return true;
    }

    public static int getMinimumAmountOfItemsNeeded() {
        return CAP;
    }
}
