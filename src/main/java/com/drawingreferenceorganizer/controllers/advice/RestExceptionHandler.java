package com.drawingreferenceorganizer.controllers.advice;

import com.drawingreferenceorganizer.exceptions.ReferenceNotFoundException;
import com.drawingreferenceorganizer.exceptions.SubjectNotFoundException;
import com.drawingreferenceorganizer.exceptions.UserIdMismatchException;
import com.drawingreferenceorganizer.exceptions.UserNotFoundException;
import com.drawingreferenceorganizer.models.ErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(SubjectNotFoundException.class)
    public ResponseEntity<ErrorDetails> subjectNotFoundHandler() {
        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setMessage("Subject not found.");
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(errorDetails);
    }

    @ExceptionHandler(ReferenceNotFoundException.class)
    public ResponseEntity<ErrorDetails> referenceNotFoundHandler() {
        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setMessage("Reference not found.");
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(errorDetails);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorDetails> userNotFoundHandler() {
        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setMessage("User not found.");
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(errorDetails);
    }

    @ExceptionHandler(UserIdMismatchException.class)
    public ResponseEntity<ErrorDetails> userIdMismatchHandler() {
        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setMessage("Resource is owned by another user.");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(errorDetails);
    }
}
