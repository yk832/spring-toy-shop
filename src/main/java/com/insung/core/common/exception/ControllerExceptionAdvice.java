package com.insung.core.common.exception;

import com.insung.core.common.dto.ErrorResultDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;

@Slf4j
@RestControllerAdvice
public class ControllerExceptionAdvice {

    @ExceptionHandler
    public ErrorResultDto sqlExHandler(SQLException e){
      log.error("[exceptionHandler] sql ex", e);
        return new ErrorResultDto("500", e.getMessage());
    }


    @ExceptionHandler
    public ErrorResultDto globalExHandler(Exception e) {
        log.error("globalExHandler Err");
        return new ErrorResultDto("BAD", e.getMessage());
    }
}
