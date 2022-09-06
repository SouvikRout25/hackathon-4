package com.jap.ticketing;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

import static org.junit.Assert.*;

public class TicketsRecordAnalyzerTest {
    TicketsRecord ticketsRecord;
    TicketsAnalyzer ticketsAnalyzer;

    String filename = "sample.csv";
    String filename2 = "simple.csv";
    @Before
    public void setUp() throws Exception {
        ticketsAnalyzer = new TicketsAnalyzer();
        ticketsRecord = new TicketsRecord("KIAS-12/5","KIAS-12UP",9387,1,11359,39,"01/09/2018","02:02:58",281,49.3);
    }

    @After
    public void tearDown() throws Exception {
        ticketsAnalyzer = null;
        ticketsRecord = null;
    }

    @Test
    public void readFile() {
        List<TicketsRecord> actual = ticketsAnalyzer.readFile(filename);
        assertEquals("Ticket Records objects not returned correctly",49,actual.size());
    }

    @Test
    public void sortDataByDistanceTravelled() {
        List<TicketsRecord> actual = ticketsAnalyzer.readFile(filename);
        assertEquals(49.5,ticketsAnalyzer.sortDataByDistanceTravelled(actual).get(0).getTravelling_distance(),0);
    }

    @Test
    public void totalTicketAmount() {
        List<TicketsRecord> actual = ticketsAnalyzer.readFile(filename);
        assertEquals(10348,ticketsAnalyzer.totalTicketAmount(actual),0);
    }
}
