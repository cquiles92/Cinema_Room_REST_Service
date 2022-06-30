package cinema.service;

import cinema.exception.InvalidTokenException;
import cinema.exception.OutOfBoundsException;
import cinema.exception.SoldTicketPurchaseException;
import cinema.exception.UnauthorizedAccessException;

import cinema.model.SeatRequest;
import cinema.model.Seat;
import cinema.model.Theatre;
import cinema.model.Ticket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;

@Service
public class TheatreService {
    @Autowired
    private final Theatre theatre;

    public TheatreService(Theatre theatre) {
        this.theatre = theatre;
    }

    public Theatre getTheatreService() {
        return theatre;
    }

    public ResponseEntity<?> purchaseSeatService(SeatRequest order) {
        if (order.getRow() > theatre.getTotalRows() || order.getRow() <= 0 ||
            order.getColumn() > theatre.getTotalColumns() || order.getColumn() <= 0) {
            throw new OutOfBoundsException();
        }

        Ticket receipt = theatre.purchaseSeat(order.getRow(), order.getColumn());
        if (receipt != null) {
            return new ResponseEntity<>(receipt, HttpStatus.OK);
        }
        throw new SoldTicketPurchaseException();
    }

    public ResponseEntity<?> returnSeatService(Map<String, UUID> token) {
        Seat returnedSeat = theatre.refundSeat(token.get("token"));
        if (returnedSeat != null) {
            return new ResponseEntity<>(Map.of("returned_ticket", returnedSeat), HttpStatus.OK);
        }
        throw new InvalidTokenException();
    }

    public ResponseEntity<?> returnTheatreStatisticsService(String password) {
        if (password != null && !password.isEmpty() && !password.isBlank()) {
            return new ResponseEntity<>(theatre.getStatisticsData(), HttpStatus.OK);
        }
        throw new UnauthorizedAccessException();
    }
}
