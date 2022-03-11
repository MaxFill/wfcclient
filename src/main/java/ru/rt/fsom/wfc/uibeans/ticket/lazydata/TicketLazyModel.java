package ru.rt.fsom.wfc.uibeans.ticket.lazydata;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import ru.rt.fsom.wfc.data.tickets.TicketData;

/**
 *
 * @author Maksim.Filatov
 */
public class TicketLazyModel extends LazyDataModel<TicketData>{
    private final List<TicketData> datasource;
 
    public TicketLazyModel(List<TicketData> datasource) {
        this.datasource = datasource;
    }
 
    @Override
    public TicketData getRowData(String rowKey) {
        for (TicketData ticket : datasource) {
            if (String.valueOf(ticket.getTicketId()).equals(rowKey)) {
                return ticket;
            }
        }
        return null;
    }
 
    @Override
    public Object getRowKey(TicketData ticket) {
        return ticket.getTicketId();
    }
 
    @Override
    public List<TicketData> load(int first, int pageSize, Map<String, SortMeta> sortMeta, Map<String, FilterMeta> filterMeta) {
        List<TicketData> data = new ArrayList<>();
 
        //filter
        for (TicketData ticket : datasource) {
            boolean match = true;
 
            if (filterMeta != null) {
                for (FilterMeta meta : filterMeta.values()) {
                    try {
                        String filterField = meta.getFilterField();
                        Object filterValue = meta.getFilterValue();
                        String fieldValue = String.valueOf(ticket.getClass().getField(filterField).get(ticket));
 
                        if (filterValue == null || fieldValue.startsWith(filterValue.toString())) {
                            match = true;
                        }
                        else {
                            match = false;
                            break;
                        }
                    }
                    catch (Exception e) {
                        match = false;
                    }
                }
            }
 
            if (match) {
                data.add(ticket);
            }
        }
 
        //sort
        if (sortMeta != null && !sortMeta.isEmpty()) {
            for (SortMeta meta : sortMeta.values()) {
                Collections.sort(data, new TicketLazySorter(meta.getSortField(), meta.getSortOrder()));
            }
        }
 
        //rowCount
        int dataSize = data.size();
        this.setRowCount(dataSize);
 
        //paginate
        if (dataSize > pageSize) {
            try {
                return data.subList(first, first + pageSize);
            }
            catch (IndexOutOfBoundsException e) {
                return data.subList(first, first + (dataSize % pageSize));
            }
        }
        else {
            return data;
        }
    }
}
