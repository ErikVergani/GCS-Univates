/**
 * Filename:    EventService.java
 *
 * Description: Implementation of the EventService class.
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

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.univates.api.exception.NotFoundException;
import com.univates.api.models.Event;
import com.univates.api.models.Registration;
import com.univates.api.models.User;
import com.univates.api.records.request.EventRecord;
import com.univates.api.records.response.EventResponseRecord;
import com.univates.api.records.response.EventUsersResponseRecord;
import com.univates.api.repositories.EventRepository;
import com.univates.api.repositories.RegistrationRepository;

/**
 * @author ev
 */
@Service
public class EventService
{
    @Autowired
    private EventRepository eventRepository;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private RegistrationRepository registrationRepository;
    
    /**
     * getAll
     * 
     * @return List <Event>
     */
    public ResponseEntity<List<EventResponseRecord>> getAll()
    {
        List<Event> events = eventRepository.findByDataBeforeTomorrow();
        List<EventResponseRecord> eventsResponse = new ArrayList <EventResponseRecord>();
        
        for ( Event e : events )
        {
            eventsResponse.add( new EventResponseRecord( e ) );
        }
        
        return ResponseEntity.status( HttpStatus.OK ).body( eventsResponse );
    }
    
    /**
     * getEvent
     *
     * @param id Integer
     * @return Event
     */
    public Event getEvent( Integer id )
    {
        return eventRepository.findById( id ).orElseThrow( () -> new NotFoundException( "Evento não encontrado" ) );
    }
    
    /**
     * saveEvent
     * 
     * @param er EventRecord
     * @return ResponseEntity<EventResponseRecord>
     */
    public ResponseEntity <EventResponseRecord> saveEvent( EventRecord er )
    {
        Event event = new Event();
        BeanUtils.copyProperties( er, event );
        
        eventRepository.save( event );
        
        return ResponseEntity.status( HttpStatus.CREATED ).body( new EventResponseRecord( event ) );
    }
    
    /**
     * saveEvent
     * 
     * @param id Integer
     * @param er EventRecord
     * @return ResponseEntity<EventResponseRecord>
     */
    public ResponseEntity<EventResponseRecord> saveEvent( Integer id, EventRecord er )
    {
        Optional <Event> ev = eventRepository.findById( id );
        
        if ( ev.isEmpty() )
        {
            throw new NotFoundException( "Evento não encontrado" );
        }
        
        Event event = ev.get();
        BeanUtils.copyProperties( er, event );
        eventRepository.save( event );
        
        return ResponseEntity.status( HttpStatus.OK ).body( new EventResponseRecord( event ) );
    }

    /**
     * getUserEvents
     *
     */
    public ResponseEntity <List<EventResponseRecord>> getUserEvents( Integer id )
    {
        User user = userService.getUser( id );
        
        if (user == null )
        {
            throw new NotFoundException( "Evento não encontrado" );
        }
        
        List <Registration> registrations =  registrationRepository.findByUser( user );

        List<EventResponseRecord> er = new ArrayList <EventResponseRecord>();
        
        eventRepository.findByRegistrationsIn( registrations ).forEach( e -> er.add( new EventResponseRecord( e ) ) );
        
        return ResponseEntity.status( HttpStatus.OK ).body( er );
    }

    /**
     * getEventusers
     *
     */
    public ResponseEntity <EventUsersResponseRecord> getEventusers( Integer id )
    {
        Optional<Event> op = eventRepository.findById( id );
        
        if ( op.isEmpty() )
        {
            throw new NotFoundException( "Evento não encontrado" );
        }
        
        Event e = op.get();
        
        List<String> usersInfo = new ArrayList <String>();
        e.getRegistrations().forEach( r -> usersInfo.add( new String ( r.getUser().getId() + ":" + r.getUser().getName() ) ) );
        
        return ResponseEntity.status( HttpStatus.OK ).body( new EventUsersResponseRecord( e, usersInfo ) );
    }
}
