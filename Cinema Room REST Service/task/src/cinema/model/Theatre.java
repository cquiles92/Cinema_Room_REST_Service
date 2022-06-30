package cinema.model;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;

public class Theatre {
    private final int totalRows;
    private final int totalColumns;
    @JsonIgnore
    private final Map<Location, Seat> seatMap;
    @JsonIgnore
    private final Map<UUID, Seat> soldMap;
    @JsonIgnore
    private final StatisticsData statisticsData;

    public Theatre(int totalRows, int totalColumns) {
        this.totalRows = totalRows;
        this.totalColumns = totalColumns;
        seatMap = initializeMap();
        soldMap = new ConcurrentHashMap<>();
        statisticsData = new StatisticsData();
    }

    @JsonGetter(value = "total_rows")
    public int getTotalRows() {
        return totalRows;
    }

    @JsonGetter(value = "total_columns")
    public int getTotalColumns() {
        return totalColumns;
    }

    @JsonGetter(value = "available_seats")
    public List<Seat> getAvailable_seats() {
        return new ArrayList<>(seatMap.values());
    }

    @JsonIgnore
    private Map<Location, Seat> initializeMap() {
        Map<Location, Seat> locationSeatMap = new ConcurrentSkipListMap<>();
        for (int i = 0; i < totalRows; i++) {
            for (int j = 0; j < totalColumns; j++) {
                Location location = new Location(i + 1, j + 1);
                Seat seat = new Seat(i + 1, j + 1);
                locationSeatMap.put(location, seat);
            }
        }
        return locationSeatMap;
    }

    @JsonIgnore
    public Ticket purchaseSeat(int row, int column) {
        Location request = new Location(row, column);
        if (seatMap.containsKey(request)) {
            Seat purchasedSeat = seatMap.get(request);
            UUID id = UUID.randomUUID();
            seatMap.remove(request);
            soldMap.put(id, purchasedSeat);
            return new Ticket(id, purchasedSeat);
        }
        return null;
    }

    @JsonIgnore
    public Seat refundSeat(UUID id) {
        if (soldMap.containsKey(id)) {
            Seat seat = soldMap.get(id);
            Location location = new Location(seat.getRow(), seat.getColumn());
            soldMap.remove(id);
            seatMap.put(location, seat);
            return seat;
        }
        return null;
    }

    @JsonIgnore
    public StatisticsData getStatisticsData() {
        return this.statisticsData;
    }

    private static class Location implements Comparable<Location> {
        private final int row;
        private final int column;

        Location(int row, int column) {
            this.row = row;
            this.column = column;
        }

        @Override
        public int compareTo(Location location) {
            if (this.row == location.row) {
                return Integer.compare(this.column, location.column);
            } else {
                return Integer.compare(this.row, location.row);
            }
        }
    }

    public class StatisticsData {
        @JsonGetter(value = "current_income")
        public int getCurrentIncome() {
            return soldMap.values().stream().mapToInt(Seat::getPrice).sum();
        }

        @JsonGetter(value = "number_of_available_seats")
        public int totalAvailableSeats() {
            return seatMap.size();
        }

        @JsonGetter(value = "number_of_purchased_tickets")
        public int totalSoldSeats() {
            return soldMap.size();
        }
    }
}
