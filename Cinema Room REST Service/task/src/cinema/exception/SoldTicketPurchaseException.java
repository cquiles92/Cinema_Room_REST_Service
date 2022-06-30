package cinema.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class SoldTicketPurchaseException extends RuntimeException{
    public SoldTicketPurchaseException() {
        super("The ticket has been already purchased!");
    }
}
