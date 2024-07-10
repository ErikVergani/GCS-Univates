/**
 * Filename:    Event.java
 *
 * Description: Implementation of the Event class.
 *
 * Revision:    14 de abr. de 2024
 *
 * Author:      Erik Freire Vergani
 * EMail:       efvergani@hotmail.com.br
 *
 */

package com.univates.api.models;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

/**
 * @author ev
 */
@Entity
@Table( name = "events" )
public class Event
{
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Integer id;
    
    private String name;
    private LocalDateTime date;
    
    @OneToMany( mappedBy = "event" )
    private List <Registration> registrations;

    
    public Integer getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public LocalDateTime getDate()
    {
        return date;
    }

    public List <Registration> getRegistrations()
    {
        return registrations;
    }

    public void setId( Integer id )
    {
        this.id = id;
    }

    public void setName( String name )
    {
        this.name = name;
    }

    public void setDate( LocalDateTime date )
    {
        this.date = date;
    }

    public void setRegistrations( List <Registration> registrations )
    {
        this.registrations = registrations;
    }
}
