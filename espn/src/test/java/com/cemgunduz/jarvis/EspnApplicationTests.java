package com.cemgunduz.jarvis;

import com.cemgunduz.jarvis.nba.statsheets.scrapers.PlayerStatsheetScraper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EspnApplicationTests {

	@Autowired
	@Qualifier("PreSeasonStatsheetScraper")
	PlayerStatsheetScraper playerStatsheetScraper;

	@Test
	public void contextLoads() {

		playerStatsheetScraper.scrapePlayerSheets();
	}

}
