package SecondRoll.demo.exception;

import SecondRoll.demo.payload.response.ErrorMessage;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler {

    //Handles specific exceptions.
    @ExceptionHandler(value = {ServiceException.class})
    public ResponseEntity<Object> handleSpecificException(ServiceException ex, WebRequest request) {

        String errorMessageDetails = ex.getLocalizedMessage();

        if(errorMessageDetails == null) errorMessageDetails = ex.toString();

        ErrorMessage errorMessage = new ErrorMessage(new Date(), errorMessageDetails);

        return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}