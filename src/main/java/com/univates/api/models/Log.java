/**
 * Filename:    Log.java
 *
 * Description: Implementation of the Log class.
 *
 * Revision:    22 de abr. de 2024
 *
 * Author:      Erik Freire Vergani
 * EMail:       efvergani@hotmail.com.br
 *
 */

package com.univates.api.models;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * @author ev
 */
@Entity
@Table( name = "logs" )
public class Log
{
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    private Integer id;
    
    @ManyToOne
    @JoinColumn( name = "user_id" )
    private User user;
    
    private String url;
    private String method;
    private LocalDateTime dtAccess = LocalDateTime.now();
    
    public Log( User user, String url, String method )
    {
        this.user = user;
        this.url = url;
        this.method = method;
    }

    public String getMethod()
    {
        return method;
    }
    
    public void setMethod( String method )
    {
        this.method = method;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId( Integer id )
    {
        this.id = id;
    }

    public User getUser()
    {
        return user;
    }
    
    public String getUrl()
    {
        return url;
    }
    
    public LocalDateTime getDtAccess()
    {
        return dtAccess;
    }
    
    public void setUser( User user )
    {
        this.user = user;
    }
    
    public void setUrl( String url )
    {
        this.url = url;
    }
    
    public void setDtAccess( LocalDateTime dtAccess )
    {
        this.dtAccess = dtAccess;
    }
}
