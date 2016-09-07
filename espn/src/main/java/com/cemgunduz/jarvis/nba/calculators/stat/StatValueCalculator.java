package com.cemgunduz.jarvis.nba.calculators.stat;

import com.cemgunduz.jarvis.nba.statsheets.Statsheet;

import java.util.List;

/**
 * Created by cem on 06/09/16.
 */
public interface StatValueCalculator {

    List<StatValue> calculateGlobalStatValue(List<Statsheet> statsheets);
}
