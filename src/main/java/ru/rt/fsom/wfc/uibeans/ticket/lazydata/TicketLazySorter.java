package ru.rt.fsom.wfc.uibeans.ticket.lazydata;

import java.util.Comparator;
import org.primefaces.model.SortOrder;
import ru.rt.fsom.wfc.data.tickets.TicketData;

/**
 *
 * @author Maksim.Filatov
 */
public class TicketLazySorter implements Comparator<TicketData>{
    private final String sortField;     
    private final SortOrder sortOrder;
     
    public TicketLazySorter(String sortField, SortOrder sortOrder) {
        this.sortField = sortField;
        this.sortOrder = sortOrder;
    }
 
    @Override
    public int compare(TicketData ticket1, TicketData ticket2) {
        try {
            Object value1 = TicketData.class.getField(this.sortField).get(ticket1);
            Object value2 = TicketData.class.getField(this.sortField).get(ticket2);
 
            int value = ((Comparable)value1).compareTo(value2);
             
            return SortOrder.ASCENDING.equals(sortOrder) ? value : -1 * value;
        }
        catch(Exception e) {
            throw new RuntimeException();
        }
    }
}
