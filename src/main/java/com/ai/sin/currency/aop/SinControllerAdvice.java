package com.ai.sin.currency.aop;

import com.ai.sin.currency.controller.CurrencyRateController;
import com.ai.sin.currency.model.SinCurrencyDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

/**
 * exception handler
 */
@ControllerAdvice(basePackageClasses = CurrencyRateController.class)
public class SinControllerAdvice extends ResponseEntityExceptionHandler {

    private static final Logger LOG = LoggerFactory.getLogger(SinControllerAdvice.class);
    /**
     * Know exception
     * @param request
     * @param ex
     * @return
     */
    @ExceptionHandler(Throwable.class)
    @ResponseBody
    ResponseEntity<?> handleControllerException(HttpServletRequest request, Throwable ex) {
        LOG.error("Ops, Error caused!!!", ex);
        HttpStatus status = getStatus(request);
        SinCurrencyDto sinCurrencyDto = new SinCurrencyDto();
        SinCurrencyDto.Header header = new SinCurrencyDto.Header();
        header.setAuthToken("");
        header.setSuccess(false);
        sinCurrencyDto.setHeader(header);
        SinCurrencyDto.Error error = new SinCurrencyDto.Error();
        error.setErrorCode(status == null ? "500" : status.name());
        error.setErrorMessage(ex.getMessage());
        error.setErrorType(ex.getClass().getName());
        sinCurrencyDto.setError(error);
        return new ResponseEntity<>(sinCurrencyDto, status);
    }

    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.valueOf(statusCode);
    }

}
