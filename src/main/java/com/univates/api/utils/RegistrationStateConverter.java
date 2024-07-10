/**
 * Filename:    RegistrationStateConverter.java
 *
 * Description: Implementation of the RegistrationStateConverter class.
 *
 * Revision:    14 de abr. de 2024
 *
 * Author:      Erik Freire Vergani
 * EMail:       efvergani@hotmail.com.br
 *
 */

package com.univates.api.utils;

import com.univates.api.enums.RegistrationState;

import jakarta.persistence.AttributeConverter;

/**
 * @author ev
 */
public class RegistrationStateConverter implements AttributeConverter <RegistrationState, String>
{
    
    @Override
    public String convertToDatabaseColumn( RegistrationState attribute )
    {
        return ( attribute == null ) ? null : attribute.name();
    }
    
    @Override
    public RegistrationState convertToEntityAttribute( String dbData )
    {
        return ( dbData == null ) ? null : RegistrationState.valueOf( dbData );
    }
    
}
