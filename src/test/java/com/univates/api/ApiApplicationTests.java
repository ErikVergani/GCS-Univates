package com.univates.api;

import com.univates.api.repositories.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@TestMethodOrder( MethodOrderer.OrderAnnotation.class )
@AutoConfigureMockMvc
class ApiApplicationTests 
{
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	UserRepository userRepository;

	/* USERS TEST */
	@Test
	@Order( 1 )
	public void testGetAllUsers() throws  Exception
	{
		mockMvc.perform( get("/api/users") )
			   .andExpect( status().isOk() )
			   .andExpect( jsonPath("$", hasSize( greaterThan( 0 ) ) ) );
	}
	
	@Test
	@Order( 2 )
	public void testAddUser() throws  Exception
	{
		String newItem = "{\"login\": \"ev\", \"password\": \"ev\", \"name\": \"Erik Vergani\", \"email\": \"test@gmail.com\"}";

		mockMvc.perform( post( "/api/users" )
						.headers( getDefaultHeaders( false ) )
			    		.contentType( MediaType.APPLICATION_JSON )
			   			.content( newItem )
						)
			   .andExpect( status().isCreated() )
			   .andExpect( jsonPath( "$.name" ).value( "Erik Vergani" ) );
	}
	
	@Test
	@Order( 3 )
	public void testIsUnavailableLogin() throws Exception
	{
		String newItem = "{\"login\": \"ev\", \"password\": \"ev\", \"name\": \"Erik Vergani\", \"email\": \"test@gmail.com\"}";

		mockMvc.perform( post("/api/users")
						.headers( getDefaultHeaders( false ) )
			   			.contentType( MediaType.APPLICATION_JSON )
			   			.content( newItem ) 
						)
			   .andExpect( status().is4xxClientError() )
			   .andExpect( jsonPath( "$.message" ).value( "Login indisponível" ) );
	}
	
	@Test
	@Order( 4 )
	public void testUpdateUser() throws Exception
	{
		String newItem = "{\"login\": \"jb\", \"password\": \"jucabala123\", \"name\": \"Juca Bala\", \"email\": \"bala@gmail.com\"}";

		mockMvc.perform( put("/api/users/2")
						.headers( getDefaultHeaders( true ) )
					    .contentType( MediaType.APPLICATION_JSON )
					    .content( newItem )
						)
			   .andExpect( status().isOk() )
			   .andExpect( jsonPath("$.name" ).value( "Juca Bala" ) );
	}
	
	
	/* EVENTS TEST*/
	@Test
	@Order( 5 )
	public void testAddEvent() throws  Exception
	{
		String newItem = "{\"name\": \"GCS-AOVIVO\", \"date\": \"2024-12-31T20:00:00\"}";
		
		mockMvc.perform( post("/api/events")
						.headers( getDefaultHeaders(true ) )
						.contentType( MediaType.APPLICATION_JSON )
						.content( newItem )
				)
				.andExpect( status().isCreated() )
				.andExpect( jsonPath("$.eventName").value( "GCS-AOVIVO" ) );
	}
	
	@Test
	@Order( 6 )
	public void testGetAllEvents() throws  Exception
	{
		mockMvc.perform( get("/api/events")
						.headers( getDefaultHeaders( false ) )
				)
				.andExpect( status().isOk() )
				.andExpect( jsonPath("$", hasSize( greaterThan( 0 ) ) ) );
	}
	
	@Test
	@Order( 7 )
	public void testUpdateEventName() throws  Exception
	{
		String newItem = "{\"name\": \"GCS-Updated\", \"date\": \"2024-12-10T20:00:00\"}";
		
		mockMvc.perform( put("/api/events/1")
						.headers( getDefaultHeaders( true ) )
						.contentType( MediaType.APPLICATION_JSON )
						.content( newItem )
				)
				.andExpect( status().isOk() )
				.andExpect( jsonPath("$.eventName").value( "GCS-Updated" ) );
	}
	
	@Test
	@Order( 9 )
	public void testGetEventParticipants() throws  Exception
	{
		mockMvc.perform( get("/api/events/1")
						.headers( getDefaultHeaders( true ) )
				)
				.andExpect( status().isOk() )
				.andExpect( jsonPath("$", aMapWithSize( greaterThan( 0 ) ) ) );
	}
	
	/* REGISTER TEST */
	@Test
	@Order( 8 )
	public void testRegisterUser() throws  Exception
	{
		String newItem = "{\"userLogin\": \"jb\", \"eventId\": \"1\"}";
		
		mockMvc.perform( post("/api/registration")
						.headers( getDefaultHeaders( true ) )
						.contentType( MediaType.APPLICATION_JSON )
						.content( newItem )
				)
				.andExpect( status().isCreated() )
				.andExpect( jsonPath("$.status").value( "NEW" ) );
	}
	
	@Test
	@Order( 10 )
	public void testCheckinParticipant() throws  Exception
	{
		mockMvc.perform( post("/api/registration/checkin?eventId=1&userId=2")
						.headers( getDefaultHeaders( true ) )
				)
				.andExpect( status().isOk() )
				.andExpect( jsonPath("$.status" ).value( "CHECK_IN_DONE" ) );
	}
	
	@Test
	@Order( 11 )
	public void testDobleChekin() throws  Exception
	{
		mockMvc.perform( post("/api/registration/checkin?eventId=1&userId=2")
						.headers( getDefaultHeaders( true ) )
				)
				.andExpect( status().is4xxClientError() )
				.andExpect( jsonPath("$.message").value( "Check-in já realizado" ) );
	}
	
	@Test
	@Order( 12 )
	public void testCancelParticipantParticipation() throws  Exception
	{
		mockMvc.perform( post("/api/registration/cancel?eventId=1&userId=2")
						.headers( getDefaultHeaders( true ) )
				)
				.andExpect( status().isOk() )
				.andExpect( jsonPath("$.status" ).value( "CANCELED" ) );
	}
	
	@Test
	@Order( 13 )
	public void testGetUserRegistrations() throws  Exception
	{
		mockMvc.perform( get("/api/registration/2")
						.headers( getDefaultHeaders( true ) )
				)
				.andExpect( status().isOk() )
				.andExpect( jsonPath("$", hasSize( greaterThan( 0 ) ) ) );
	}
	
	@Test
	@Order( 14 )
	public void testUserNotFound() throws  Exception
	{
		mockMvc.perform( get("/api/registration/6")
						.headers( getDefaultHeaders( true ) )
				)
				.andExpect( status().is4xxClientError() )
				.andExpect( jsonPath("$.message" ).value( "Usuário não encontrado" ) );
	}
	
	@Test
	@Order( 15 )
	public void testEventNotFound() throws  Exception
	{
		mockMvc.perform( post("/api/registration/cancel?eventId=2&userId=2")
						.headers( getDefaultHeaders( true ) )
				)
				.andExpect( status().is4xxClientError() )
				.andExpect( jsonPath("$.message" ).value( "Evento não encontrado" ) );
	}
	
	@Test
	@Order( 16 )
	public void testParticipantAlreadyRegistered() throws  Exception
	{
		String newItem = "{\"userLogin\": \"jb\", \"eventId\": \"1\"}";
		
		mockMvc.perform( post("/api/registration")
						.headers( getDefaultHeaders( true) )
						.contentType( MediaType.APPLICATION_JSON )
						.content( newItem )
				)
				.andExpect( status().is4xxClientError() )
				.andExpect( jsonPath("$.message").value( "Usuário já encontra-se registrado" ) );
	}
	
	@Test
	@Order( 17 )
	public void testCancelInvalidRegister() throws  Exception
	{
		mockMvc.perform( post("/api/registration/cancel?eventId=1&userId=1")
						.headers( getDefaultHeaders( true ) )
				)
				.andExpect( status().is4xxClientError() )
				.andExpect( jsonPath("$.message").value( "Inscrição não encontrada" ) );
	}
	
	@Test
	@Order(18)
	public void testDoubleCancel() throws  Exception
	{
		mockMvc.perform( post("/api/registration/cancel?eventId=1&userId=2")
						.headers( getDefaultHeaders( true ) )
				)
				.andExpect( status().is4xxClientError() )
				.andExpect( jsonPath("$.message").value( "Inscrição do usuário já está cancelada" ) );
	}
	
	@Test
	@Order(19)
	public void testAddInvalidUser() throws  Exception
	{
		String newItem = "{\"login\": \"\", \"password\": \"\", \"name\": \"Erik Vergani\", \"email\": \"test@gmail.com\"}";

		mockMvc.perform(post("/api/users")
						.headers( getDefaultHeaders( false ) )
			   .contentType(MediaType.APPLICATION_JSON)
			   .content(newItem))
			   .andExpect(status().is4xxClientError());
	}
	
	@Test
	@Order(20)
	public void testDontShowUnreachableEvents() throws  Exception
	{
		String newItem = "{\"name\": \"GCS-DontShow\", \"date\": \"2024-01-10T20:00:00\"}";
		
		mockMvc.perform( post("/api/events")
						.headers( getDefaultHeaders( true ) )
						.contentType(MediaType.APPLICATION_JSON)
						.content(newItem )
				)
				.andExpect( status().isCreated() );
		
		mockMvc.perform( get("/api/events")
						.headers( getDefaultHeaders( false ) )
				)
				.andExpect( status().isOk() )
				.andExpect( jsonPath("$", hasSize( equalTo( 1 ) ) ) );
	}
	
	@Test
	@Order(21)
	public void testGetUsersFromDb() throws  Exception
	{
		Assertions.assertEquals( 2, userRepository.findAll().size() );
	}
	
	private HttpHeaders getDefaultHeaders( boolean needUser ) 
	{
        HttpHeaders headers = new HttpHeaders();
        headers.add("login", "api");
        headers.add("pass", "api123");
		
		if ( needUser )
		{
			headers.add( "user", "null" );
		}
		
        return headers;
    }
}
