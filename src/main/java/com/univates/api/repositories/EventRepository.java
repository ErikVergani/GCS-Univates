/**
 * Filename:    EventRepository.java
 *
 * Description: Implementation of the EventRepository class.
 *
 * Revision:    14 de abr. de 2024
 *
 * Author:      Erik Freire Vergani
 * EMail:       efvergani@hotmail.com.br
 *
 */

package com.univates.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.univates.api.models.Event;

import java.time.LocalDateTime;
import java.util.List;
import com.univates.api.models.Registration;


/**
 * @author ev
 */
@Repository
public interface EventRepository extends JpaRepository <Event, Integer>
{
    @Query( "SELECT e FROM Event e WHERE ?1 < e.date" )
    List <Event> findByDataBefore( LocalDateTime date );
    
    default List <Event> findByDataBeforeTomorrow()
    {
        LocalDateTime date = LocalDateTime.now().plusDays( 2 );
        return findByDataBefore( date );
    }
    
    List<Event> findByRegistrationsIn(List<Registration> registrations);
}
