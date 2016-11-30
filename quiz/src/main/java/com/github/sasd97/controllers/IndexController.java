package com.github.sasd97.controllers;

import com.github.sasd97.errors.NotFoundError;
import com.github.sasd97.models.reponse.ErrorResponseModel;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import static com.github.sasd97.constants.MethodConstants.Base.ERROR;

/**
 * Created by Alexadner Dadukin on 11/30/2016.
 */

@RestController
@ControllerAdvice
@RequestMapping(ERROR)
public class IndexController implements ErrorController {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundError.class)
    public ErrorResponseModel notFoundError(HttpServletRequest req, Exception ex) {
        NotFoundError error = ((NotFoundError) ex);
        return new ErrorResponseModel(error.getCode(), error.getDescription(), req.getRequestURL().toString());
    }

    @ExceptionHandler(Exception.class)
    public ErrorResponseModel error() {
        return new ErrorResponseModel();
    }

    @Override
    public String getErrorPath() {
        return ERROR;
    }
}
