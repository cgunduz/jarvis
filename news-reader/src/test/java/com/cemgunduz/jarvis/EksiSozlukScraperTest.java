package com.cemgunduz.jarvis;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cemgunduz.jarvis.reader.eksisozluk.*;

/**
 * Created by cem on 12/07/16.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = NewsReaderApplication.class)
@Ignore
public class EksiSozlukScraperTest {

    @Test
    public void eksiScraperTest()
    {
        EksisozlukTopicReader eksisozlukTopicReader = new EksisozlukTopicReader();
        eksisozlukTopicReader.read();
    }

}
