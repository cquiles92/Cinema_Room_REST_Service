package cinema.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class OutOfBoundsException extends RuntimeException {
    public OutOfBoundsException() {
        super("The number of a row or a column is out of bounds!");
    }
}
