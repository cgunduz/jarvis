package com.cemgunduz.jarvis.nba.statsheets.scrapers;

import com.cemgunduz.jarvis.nba.calculators.player.Status;
import org.jsoup.nodes.Element;
import org.omg.CORBA.DoubleHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cem on 25/10/16.
 */
public class PlayerHtmlAnalyzer {

    private Element playerHtml;

    public PlayerHtmlAnalyzer(Element playerHtml) {
        this.playerHtml = playerHtml;
    }

    public String getPlayerName()
    {
        String playerName =
                playerHtml.getElementsByTag("td").get(0).getElementsByAttribute("href").text();
        return cleanLastEmptySpace(playerName);
    }

    public String getTeamName()
    {
        String teamName = playerHtml.getElementsByTag("td").get(2).text();
        return teamName;
    }

    public String getNbaTeamName()
    {
        String teamName = playerHtml.getElementsByTag("td").get(0).text()
                .replace(getPlayerName(), "").replace("*","").substring(2,5).replace(" ","");

        return cleanLastEmptySpace(teamName);
    }

    public Status getStatus()
    {
        String statusText = playerHtml.getElementsByTag("td").get(0).text();
        if(statusText.contains("*")) return Status.O;
        if(statusText.contains("DTD")) return Status.DTD;

        return Status.AVAILABLE;
    }

    public Double getMinutes()
    {
        return getAttribute(8);
    }

    public Double getThreePointers()
    {
        return getAttribute(13);
    }

    public Double getRebounds()
    {
        return getAttribute(14);
    }

    public Double getAsists()
    {
        return getAttribute(15);
    }

    public Double getSteals()
    {
        return getAttribute(16);
    }

    public Double getBlocks()
    {
        return getAttribute(17);
    }

    public Double getTurnovers()
    {
        return getAttribute(18);
    }

    public Double getPoints()
    {
        return getAttribute(19);
    }

    public Double getFgMissed()
    {
        return getFgAttempted() - getFgScored();
    }

    public Double getFgAttempted()
    {
        String fgLine = playerHtml.getElementsByTag("td").get(9).text();
        return getRightSideOfSlash(fgLine);
    }

    public Double getFgScored()
    {
        String fgLine = playerHtml.getElementsByTag("td").get(9).text();
        return getLeftSideOfSlash(fgLine);
    }

    public Double getFtMissed()
    {
        return getFtAttempted() - getFtScored();
    }

    public Double getFtAttempted()
    {
        String ftLine = playerHtml.getElementsByTag("td").get(11).text();
        return getRightSideOfSlash(ftLine);
    }

    public Double getFtScored()
    {
        String ftLine = playerHtml.getElementsByTag("td").get(11).text();
        return getLeftSideOfSlash(ftLine);
    }

    private Double getAttribute(int no)
    {
        String attrHtml = playerHtml.getElementsByTag("td").get(no).text();
        return attrHtml == null || attrHtml.isEmpty() || attrHtml.equals("--")
                ? 0.0 : Double.valueOf(attrHtml);
    }

    private String cleanLastEmptySpace(String s)
    {
        return (int)s.charAt(s.length()-1) == 160 ||
                (int)s.charAt(s.length()-1) == 32  ? s.substring(0, s.length()-1) : s;
    }

    private Double getLeftSideOfSlash(String s)
    {
        if(s==null || s.isEmpty()) return 0.0;

        int index = s.indexOf("/");
        if(index == 0) return 0.0;

        String valAsString = s.substring(0, index);
        return valAsString.equals("--") ? 0.0 : Double.valueOf(valAsString);
    }

    private Double getRightSideOfSlash(String s)
    {
        if(s==null || s.isEmpty()) return 0.0;

        int index = s.indexOf("/");
        if(index == 0) return 0.0;

        String valAsString = s.substring(index+1);
        return valAsString.equals("--") ? 0.0 : Double.valueOf(valAsString);
    }
}
