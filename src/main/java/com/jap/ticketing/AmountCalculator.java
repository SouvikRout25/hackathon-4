package com.jap.ticketing;

import java.util.List;

public interface AmountCalculator {
    double totalTicketAmount(List<TicketsRecord> ticketsRecords);
}
