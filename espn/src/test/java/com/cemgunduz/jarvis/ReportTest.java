package com.cemgunduz.jarvis;

import com.cemgunduz.jarvis.nba.BasketballPlayer;
import com.cemgunduz.jarvis.nba.League;
import com.cemgunduz.jarvis.nba.calculators.PreSeasonReportCompiler;
import com.cemgunduz.jarvis.nba.calculators.player.PlayerReport;
import com.cemgunduz.jarvis.nba.calculators.player.PlayerValueCalculator;
import com.cemgunduz.jarvis.nba.calculators.report.Reporter;
import com.cemgunduz.jarvis.nba.calculators.stat.SimpleStatValueCalculator;
import com.cemgunduz.jarvis.nba.calculators.stat.Stat;
import com.cemgunduz.jarvis.nba.statsheets.Statsheet;
import com.cemgunduz.jarvis.nba.statsheets.scrapers.PlayerStatsheetScraper;
import com.cemgunduz.jarvis.nba.statsheets.scrapers.PreSeasonStatsheetScraper;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by cem on 07/09/16.
 */
public class ReportTest {

    @Test
    public void reportTest()
    {
        PreSeasonReportCompiler preSeasonReportCompiler = new PreSeasonReportCompiler();
        List<PlayerReport> playerReports = preSeasonReportCompiler.compile();

        Reporter reporter = new Reporter();
        String reportAsString = reporter.compileReport(playerReports);

        System.out.println(reportAsString);
    }
}
