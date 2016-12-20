package com.github.sasd97.controllers;

import com.github.sasd97.errors.BasicError;
import com.github.sasd97.models.response.ErrorResponseModel;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import static com.github.sasd97.constants.MethodConstants.Error.ERROR;

/**
 * Created by Alexadner Dadukin on 11/30/2016.
 */

@RestController
@ControllerAdvice
@RequestMapping(ERROR)
public class ErrorController implements org.springframework.boot.autoconfigure.web.ErrorController {

    @ExceptionHandler(BasicError.class)
    public ErrorResponseModel basicError(HttpServletRequest req, Exception ex) {
        BasicError error = ((BasicError) ex);
        return new ErrorResponseModel(error.getCode(), error.getDescription(), req.getRequestURL().toString());
    }

    @ExceptionHandler(Exception.class)
    public ErrorResponseModel error(HttpServletRequest req) {
        return new ErrorResponseModel(0, "Unknown error", req.getRequestURL().toString());
    }

    @Override
    public String getErrorPath() {
        return ERROR;
    }
}
