/**
 * Filename:    RegistrationService.java
 *
 * Description: Implementation of the RegistrationService class.
 *
 * Revision:    15 de abr. de 2024
 *
 * Author:      Erik Freire Vergani
 * EMail:       efvergani@hotmail.com.br
 *
 */

package com.univates.api.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.univates.api.enums.RegistrationState;
import com.univates.api.exception.NotFoundException;
import com.univates.api.exception.UserRegisterException;
import com.univates.api.models.Event;
import com.univates.api.models.Registration;
import com.univates.api.models.User;
import com.univates.api.records.request.RegistrationRecord;
import com.univates.api.records.response.RegistrationResponseRecord;
import com.univates.api.repositories.RegistrationRepository;

/**
 * @author ev
 */
@Service
public class RegistrationService
{
    @Autowired
    private RegistrationRepository registrationRepository;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private EventService eventService;
    
    @Autowired
    private EmailService emailService;
    
    public List<RegistrationResponseRecord> getRegistrations( User user )
    {
        List<RegistrationResponseRecord> records = new ArrayList <RegistrationResponseRecord>();
        
        registrationRepository.findByUser( user ).forEach( r -> records.add( new RegistrationResponseRecord( r ) ) );
        
       return records;
    }
    
    public Optional <Registration> getRegistration( Integer eventId, Integer userId )
    {
        return registrationRepository.findByEventAndUser( eventService.getEvent( eventId ), userService.getUser( userId ) );
    }
    
    public ResponseEntity<RegistrationResponseRecord> addRegistration( RegistrationRecord rr )
    {
        User user = userService.getUserByLogin( rr.userLogin() );
        Event event = eventService.getEvent( rr.eventId() );
        
        if ( user == null)
        {
            throw new NotFoundException( "Usuário não encontrado" );
        }
        
        if ( event == null )
        {
            throw new NotFoundException( "Evento não encontrado" );
        }
        
        if ( !registrationRepository.findByEventAndUser( event, user ).isEmpty() )
        {
            throw new UserRegisterException( "Usuário já encontra-se registrado" );
        }
        
        Registration registration = new Registration();
        
        registration.setUser( user );
        registration.setEvent( event );
        
        registrationRepository.save( registration );
        emailService.sendEventRegister( user, event, registration.getRegisterDate() );
        
        return ResponseEntity.status( HttpStatus.CREATED ).body( new RegistrationResponseRecord( registration ) );
    }


    public ResponseEntity<RegistrationResponseRecord> cancelRegistration( Integer eventId, Integer userId )
    {
        Optional <Registration> r = getRegistration( eventId, userId );
        
        if ( r.isEmpty() )
        {
            throw new NotFoundException( "Inscrição não encontrada" );
        }
        
        Registration registration = r.get();
        
        if ( registration.getState() == RegistrationState.CANCELED )
        {
            throw new UserRegisterException( "Inscrição do usuário já está cancelada" );
        }
        
        registration.setState( RegistrationState.CANCELED );
        
        emailService.sendCancelationEmail( registration.getUser(), registration.getEvent() );
        registrationRepository.save( registration );
        
        return ResponseEntity.status( HttpStatus.OK ).body( new RegistrationResponseRecord( registration ) );
    }

    public ResponseEntity<RegistrationResponseRecord> checkIn( Integer eventId, Integer userId )
    {
        Optional <Registration> r =  getRegistration( eventId, userId );
        
        if ( r.isEmpty() )
        {
            throw new NotFoundException( "Inscrição não encontrada" );
        }
        
        Registration registration = r.get();
        
        if ( registration.getState() == RegistrationState.CHECK_IN_DONE )
        {
            throw new UserRegisterException( "Check-in já realizado" );
        }
        
        registration.setState( RegistrationState.CHECK_IN_DONE );
        
        emailService.sendCheckinConfirmation( registration.getUser(), registration.getEvent() );
        registrationRepository.save( registration );
        
        return ResponseEntity.status( HttpStatus.OK ).body( new RegistrationResponseRecord( registration ) );
    }
}
