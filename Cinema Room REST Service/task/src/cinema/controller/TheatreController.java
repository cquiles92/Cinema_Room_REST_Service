package cinema.controller;

import cinema.exception.InvalidTokenException;
import cinema.exception.OutOfBoundsException;
import cinema.exception.SoldTicketPurchaseException;
import cinema.exception.UnauthorizedAccessException;
import cinema.model.SeatRequest;
import cinema.model.Theatre;
import cinema.service.TheatreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;


@RestController
public class TheatreController {
    private final TheatreService theatreService;

    @Autowired
    public TheatreController(TheatreService theatreService) {
        this.theatreService = theatreService;
    }

    @GetMapping("/seats")
    public Theatre getTheatre() {
        return theatreService.getTheatreService();
    }

    @ResponseBody
    @PostMapping("/purchase")
    public ResponseEntity<?> purchaseSeat(@RequestBody SeatRequest order) {
        return theatreService.purchaseSeatService(order);
    }

    @ResponseBody
    @PostMapping("/return")
    public ResponseEntity<?> returnSeat(@RequestBody Map<String, UUID> token) {
        return theatreService.returnSeatService(token);
    }

    @ResponseBody
    @PostMapping("/stats")
    public ResponseEntity<?> returnTheatreStatistics(@RequestParam(value = "password", required = false) String password) {
        return theatreService.returnTheatreStatisticsService(password);
    }

    @ExceptionHandler(UnauthorizedAccessException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    Map<String, String> errorHandlerAuth(Exception e) {
        return Map.of("error", e.getMessage());
    }

    @ExceptionHandler({InvalidTokenException.class, OutOfBoundsException.class, SoldTicketPurchaseException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    Map<String, String> errorHandler(Exception e) {
        return Map.of("error", e.getMessage());
    }
}
