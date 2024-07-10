/**
 * Filename:    UnauthorizedExceptionHandler.java
 *
 * Description: Implementation of the UnauthorizedExceptionHandler class.
 *
 * Revision:    21 de abr. de 2024
 *
 * Author:      Erik Freire Vergani
 * EMail:       efvergani@hotmail.com.br
 *
 */

package com.univates.api.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.univates.api.exception.UnauthorizedException;
import com.univates.api.records.response.ErrorResponseRecord;

/**
 * @author ev
 */
@ControllerAdvice
@RestControllerAdvice
public class UnauthorizedExceptionHandler
{
    @ExceptionHandler( UnauthorizedException.class )
    @ResponseBody
    @ResponseStatus( HttpStatus.UNAUTHORIZED )
    public ErrorResponseRecord handler( UnauthorizedException e )
    {
        return new ErrorResponseRecord( e.getMessage() );
    }
}
