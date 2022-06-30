package cinema.model;

import com.fasterxml.jackson.annotation.JsonGetter;

import java.util.UUID;

public class Ticket {
    private UUID id;
    private Seat seat;

    public Ticket() {
    }

    public Ticket(UUID id, Seat seat) {
        this.id = id;
        this.seat = seat;
    }

    @JsonGetter(value = "token")
    public UUID getId() {
        return id;
    }

    @JsonGetter(value = "ticket")
    public Seat getSeat() {
        return seat;
    }
}
