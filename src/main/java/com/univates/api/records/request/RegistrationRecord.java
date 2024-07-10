/**
 * Filename:    RegistrationRecord.java
 *
 * Description: Implementation of the RegistrationRecord class.
 *
 * Revision:    15 de abr. de 2024
 *
 * Author:      Erik Freire Vergani
 * EMail:       efvergani@hotmail.com.br
 *
 */

package com.univates.api.records.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * @author ev
 */
public record RegistrationRecord( @NotNull @NotBlank String userLogin, @Min(value = 1 ) Integer eventId ){}
