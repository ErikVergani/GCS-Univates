/**
 * Filename:    RegistrationResponseRecord.java
 *
 * Description: Implementation of the RegistrationResponseRecord class.
 *
 * Revision:    21 de abr. de 2024
 *
 * Author:      Erik Freire Vergani
 * EMail:       efvergani@hotmail.com.br
 *
 */

package com.univates.api.records.response;

import java.time.format.DateTimeFormatter;

import com.univates.api.models.Registration;

/**
 * @author ev
 */
public record RegistrationResponseRecord( String userName, String eventName, Integer eventId, String status, String registrationDate )
{
    public RegistrationResponseRecord( Registration r )
    {
        this( r.getUser().getName(), r.getEvent().getName(), r.getId(), r.getState().toString(), r.getRegisterDate().format( DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm") ) );
    }
    
}
