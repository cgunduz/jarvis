package com.cemgunduz.jarvis.nba.calculators.report;

import com.cemgunduz.jarvis.nba.calculators.player.PlayerReport;
import com.cemgunduz.jarvis.nba.calculators.stat.Stat;

import java.util.List;

/**
 * Created by cem on 20/09/16.
 */
public class Reporter {

    private StringBuilder stringBuilder;

    public String compileReport(List<PlayerReport> reportables)
    {
        stringBuilder = new StringBuilder();

        int rank = 1;
        for(PlayerReport playerReport : reportables)
        {
            addLine(String.valueOf(rank).concat(". ").concat(playerReport.getPlayerName())
                    .concat(" - ").concat(String.valueOf(playerReport.espnRankings)));
            addLine(String.valueOf(playerReport.getTotalValue()));
            addLine(playerReport.getStatMap().toString());
            try
            {
                addLine("FG : ".concat(String.valueOf(playerReport.getStatMap().get(Stat.FGS) + playerReport.getStatMap().get(Stat.FGM))));
                addLine("FT : ".concat(String.valueOf(playerReport.getStatMap().get(Stat.FTS) + playerReport.getStatMap().get(Stat.FTM))));
            }
            catch (Exception e)
            {
                addLine("FG : 0");
                addLine("FT : 0");
            }

            rank++;
        }

        return stringBuilder.toString();
    }

    public String getReport()
    {
        return stringBuilder == null ? "" : stringBuilder.toString();
    }

    private void addLine(String line)
    {
        stringBuilder.append(line);
        endLine();
    }

    private void endLine()
    {
        stringBuilder.append("\n");
    }
}
