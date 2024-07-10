/**
 * Filename:    User.java
 *
 * Description: Implementation of the user class.
 *
 * Revision:    14 de abr. de 2024
 *
 * Author:      Erik Freire Vergani
 * EMail:       efvergani@hotmail.com.br
 *
 */

package com.univates.api.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
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
@Table( name = "users" )
public class User
{
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Integer id;
    
    @Column( unique = true )
    private String login;
    private String password;
    private String name;
    private String email;
    
    @OneToMany( mappedBy = "user" )
    private List <Registration> registrations;
    
    @OneToMany( mappedBy = "user" )
    @JsonIgnore
    private List<Log> log;

    public List <Log> getLog()
    {
        return log;
    }

    public void setLog( List <Log> log )
    {
        this.log = log;
    }

    public Integer getId()
    {
        return id;
    }

    public String getLogin()
    {
        return login;
    }

    public String getPassword()
    {
        return password;
    }

    public String getName()
    {
        return name;
    }

    public String getEmail()
    {
        return email;
    }

    public List <Registration> getRegistrations()
    {
        return registrations;
    }

    public void setId( Integer id )
    {
        this.id = id;
    }

    public void setLogin( String login )
    {
        this.login = login;
    }

    public void setPassword( String password )
    {
        this.password = password;
    }

    public void setName( String name )
    {
        this.name = name;
    }

    public void setEmail( String email )
    {
        this.email = email;
    }

    public void setRegistrations( List <Registration> registrations )
    {
        this.registrations = registrations;
    }
}
