package com.cemgunduz.jarvis;

import com.cemgunduz.jarvis.nba.League;
import com.cemgunduz.jarvis.nba.calculators.SeasonReportCompiler;

import com.cemgunduz.jarvis.nba.calculators.player.PlayerReport;
import com.cemgunduz.jarvis.nba.calculators.report.Reporter;
import org.junit.Test;

import java.util.List;

/**
 * Created by cem on 26/10/16.
 */
public class SeasonReportTest {

    @Test
    public void notReallyATest() {

        SeasonReportCompiler seasonReportCompiler = new SeasonReportCompiler(League.SCUM_LEAGUE);
        List<PlayerReport> playerReports = seasonReportCompiler.compile();

        Reporter reporter = new Reporter();
        String reportAsString = reporter.compileReport(playerReports);

        System.out.println(reportAsString);
    }
}
