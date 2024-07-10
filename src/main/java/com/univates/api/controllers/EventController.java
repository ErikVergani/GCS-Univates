/**
 * Filename:    EventController.java
 *
 * Description: Implementation of the EventController class.
 *
 * Revision:    15 de abr. de 2024
 *
 * Author:      Erik Freire Vergani
 * EMail:       efvergani@hotmail.com.br
 *
 */

package com.univates.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.univates.api.records.request.EventRecord;
import com.univates.api.records.response.EventResponseRecord;
import com.univates.api.records.response.EventUsersResponseRecord;
import com.univates.api.services.EventService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

/**
 * @author ev
 */
@RestController
@RequestMapping( "/api/events" )
public class EventController
{
    @Autowired
    private EventService eventService;
    
    @PostMapping
    @Operation( description = "Adciona um novo evento" )
    @ApiResponses( value = { @ApiResponse ( responseCode = "201", description = "Retorna o novo evento" ),
                             @ApiResponse ( responseCode = "401", description = "Credenciais da API intermediária estão incorretas" ),
                           } )
    public ResponseEntity<EventResponseRecord> addEvent( @RequestHeader String login, @RequestHeader String pass, @RequestHeader String user, @RequestBody @Valid EventRecord er, HttpServletRequest request )
    {
        return eventService.saveEvent( er );
    }
    
    @PutMapping( "/{id}" )
    @Operation( description = "Atualiza as informações de um evento ( Disponível apenas para ADM )" )
    @ApiResponses( value = { @ApiResponse ( responseCode = "200", description = "Retorna o evento atualizado" ),
                             @ApiResponse ( responseCode = "401", description = "Credenciais da API intermediária estão incorretas" ),
                             @ApiResponse ( responseCode = "400", description = "Evento não encontrado" ),
                           } )
    public ResponseEntity<EventResponseRecord> updateEvent(  @RequestHeader String login, @RequestHeader String pass, @RequestHeader String user, @PathVariable( value = "id" ) Integer id, @RequestBody @Valid EventRecord er, HttpServletRequest request )
    {
        return eventService.saveEvent( id, er );
    }
    
    @GetMapping
    @Operation( description = "Retorna os eventos disponíveis ( 2 dias de antecedência )" )
    @ApiResponses( value = { @ApiResponse ( responseCode = "200", description = "Retorna os eventos disponíveis" ),
                             @ApiResponse ( responseCode = "401", description = "Credenciais da API intermediária estão incorretas" ),
                            } )
    public ResponseEntity<List<EventResponseRecord>> getEvents( @RequestHeader String login, @RequestHeader String pass, HttpServletRequest request )
    {
        return eventService.getAll();
    }
    
    // Implementar novamente caso necessário... demanda estava desenvolvida do jeito errado, o método correto é getUserEvents
//    @GetMapping( "/{id}" )
//    @Operation( description = "Retorna os eventos que um usuário está inscrito." )
//    @ApiResponses( value = { @ApiResponse ( responseCode = "200", description = "Retorna os eventos de um usuário" ),
//                             @ApiResponse ( responseCode = "401", description = "Credenciais da API intermediária estão incorretas" ),
//                             @ApiResponse ( responseCode = "400", description = "Evento/Usuário não encontrado" ),
//                           } )
//    public ResponseEntity <List<EventResponseRecord>> getUserEvents(  @RequestHeader String login, @RequestHeader String pass, @RequestHeader String user, @PathVariable( value = "id" ) Integer id, HttpServletRequest request )
//    {
//        return eventService.getUserEvents( id );
//    }
    
    
    @GetMapping( "/{id}" )
    @Operation( description = "Retorna as incricoes que existem em um determinado evento." )
    @ApiResponses( value = { @ApiResponse ( responseCode = "200", description = "Retorna as inscricoes existentes de um evento" ),
                             @ApiResponse ( responseCode = "401", description = "Credenciais da API intermediária estão incorretas" ),
                             @ApiResponse ( responseCode = "400", description = "Evento/Usuário não encontrado" ),
                           } )
    public ResponseEntity <EventUsersResponseRecord> getUserEvents(  @RequestHeader String login, @RequestHeader String pass, @RequestHeader String user, @PathVariable( value = "id" ) Integer id, HttpServletRequest request )
    {
        return eventService.getEventusers( id );
    }
}
