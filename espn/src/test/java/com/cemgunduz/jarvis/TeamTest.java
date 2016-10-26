package com.cemgunduz.jarvis;

import com.cemgunduz.jarvis.nba.calculators.TeamReportCompiler;
import com.cemgunduz.jarvis.nba.calculators.report.Reporter;
import com.cemgunduz.jarvis.nba.calculators.team.ReportablePlayerTeam;
import org.junit.Test;

import java.util.List;

/**
 * Created by cem on 26/10/16.
 */
public class TeamTest {

    @Test
    public void notReallyATest() {

        TeamReportCompiler teamReportCompiler = new TeamReportCompiler();
        List<ReportablePlayerTeam> teamReports = teamReportCompiler.compile();

        Reporter reporter = new Reporter();
        String reportAsString = reporter.compileTeamReport(teamReports);

        System.out.println(reportAsString);
    }
}
