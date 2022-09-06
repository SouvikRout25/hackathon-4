package com.jap.ticketing;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TicketsAnalyzer {
    public List<TicketsRecord> readFile(String filename){
        List<TicketsRecord> records = new ArrayList<>();
        try{
            FileReader fileReader = new FileReader(filename);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine();
            while((line = bufferedReader.readLine()) != null){
                String[] values = line.split(",");
                String schedule_no = values[0];
                String route_no = values[1];
                int ticket_from_stop_id = Integer.parseInt(values[2]);
                int ticket_from_stop_seq_no = Integer.parseInt(values[3]);
                int ticket_till_stop_id = Integer.parseInt(values[4]);
                int ticket_till_stop_seq_no = Integer.parseInt(values[5]);
                String ticket_date = values[6];
                String ticket_time = values[7];
                double total_ticket_amount = Double.parseDouble(values[8]);
                double travelling_distance = Double.parseDouble(values[9]);

                records.add(new TicketsRecord(schedule_no,route_no,ticket_from_stop_id,ticket_from_stop_seq_no,ticket_till_stop_id,ticket_till_stop_seq_no,ticket_date,ticket_time,total_ticket_amount,travelling_distance));

            }
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
        return records;
    }
    public List<TicketsRecord> sortDataByDistanceTravelled(List<TicketsRecord> ticketsRecords){
        ticketsRecords.sort(((o1, o2) -> {
            if(o1.getTravelling_distance() == o2.getTravelling_distance()){
                return 0;
            }
            else if(o1.getTravelling_distance() > o2.getTravelling_distance()){
                return -1;
            }
            else{
                return 1;
            }
        }));
        return ticketsRecords;
    }
    public double totalTicketAmount(List<TicketsRecord> ticketsRecords){
        AmountCalculator amountCalculator = ticketsRecords1 -> {
            double sum = 0;
            Iterator<TicketsRecord> iterator = ticketsRecords1.iterator();
            while (iterator.hasNext()){
                TicketsRecord element = iterator.next();
                sum +=  element.getTotal_ticket_amount();
            }
            return sum;
        };
        return amountCalculator.totalTicketAmount(ticketsRecords);
    }
    public static void main(String[] args) {
        TicketsAnalyzer ticketsAnalyzer = new TicketsAnalyzer();
        String filename = "sample.csv";
        List<TicketsRecord> list = ticketsAnalyzer.readFile(filename);
        System.out.println("After storing");
        for (TicketsRecord element : list){
            System.out.println(element);
        }
        System.out.println();
        System.out.println("After sorting");
        List<TicketsRecord> list2 = ticketsAnalyzer.sortDataByDistanceTravelled(list);
        for (TicketsRecord element : list2){
            System.out.println(element);
        }
        System.out.println();
        System.out.println("Total amount = " + ticketsAnalyzer.totalTicketAmount(list));
    }
}
