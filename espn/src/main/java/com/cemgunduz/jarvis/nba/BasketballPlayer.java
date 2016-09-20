package com.cemgunduz.jarvis.nba;

import com.cemgunduz.jarvis.nba.statsheets.Statsheet;
import com.cemgunduz.jarvis.nba.statsheets.StatsheetType;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by cem on 05/09/16.
 */
public class BasketballPlayer {

    private String name;
    private Position position;

    private Map<StatsheetType, Statsheet> statsheetMap = new HashMap<>();

    public BasketballPlayer() {

        for(StatsheetType statsheetType : StatsheetType.values())
        {
            statsheetMap.put(statsheetType, new Statsheet());
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Statsheet getSheet(StatsheetType statsheetType)
    {
        return statsheetMap.get(statsheetType);
    }

    public void setSheet(Statsheet statsheet, StatsheetType statsheetType)
    {
        statsheetMap.put(statsheetType, statsheet);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BasketballPlayer that = (BasketballPlayer) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return position == that.position;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (position != null ? position.hashCode() : 0);
        return result;
    }
}
