package com.codetutr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.RestTemplate;

import suman.appdirect.model.event.Event;
import suman.appdirect.model.result.Error;
import suman.appdirect.model.result.EventResult;
import suman.appdirect.model.result.EventResultException;

import com.codetutr.domain.Person;
import com.codetutr.service.PersonService;

@Controller
@RequestMapping("api")
public class PersonController {

	PersonService personService;

	@Autowired
	public PersonController(PersonService personService) {
		this.personService = personService;
	}

	@RequestMapping("person/random")
	@ResponseBody
	public Person randomPerson() {
		return personService.getRandom();
	}

	@RequestMapping("person/{id}")
	@ResponseBody
	public Person getById(@PathVariable Long id) {
		return personService.getById(id);
	}

	/*
	 * same as above method, but is mapped to /api/person?id= rather than
	 * /api/person/{id}
	 */
	@RequestMapping(value = "person", params = "id")
	@ResponseBody
	public Person getByIdFromParam(@RequestParam("id") Long id) {
		return personService.getById(id);
	}

	/**
	 * Saves new person. Spring automatically binds the name and age parameters
	 * in the request to the person argument
	 * 
	 * @param person
	 * @return String indicating success or failure of save
	 */
	@RequestMapping(value = "person", method = RequestMethod.POST)
	@ResponseBody
	public String savePerson(Person person) {
		personService.save(person);
		return "Saved person: " + person.toString();
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	// fired by AppDirect when a user buys our application from AppDirect.
	// --------------------------------------------------------------------------------------------------------------------------------
	@RequestMapping(value = "/subscriptionCreate", params = "eventUrl", method = { RequestMethod.GET }, produces = "application/xml")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public EventResult subscriptionCreate(@RequestParam final String eventUrl) {
		// persist company/user pair
		// saveCompanyUser(company, user);
		RestTemplate restTemplate = new RestTemplate();
		Event event = restTemplate.getForEntity(eventUrl, Event.class)
				.getBody();
		Person person = new Person();
		person.setName(event.getPayload().getCompany().getName());
		person.setEditionCode(event.getPayload().getOrder().getEditionCode());
		System.out.println("Event Fired" + eventUrl);
		personService.save(person);
		return EventResult.success(person.getName());
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	// fired by AppDirect when a user upgrades/downgrades/modifies an existing
	// subscription.
	// --------------------------------------------------------------------------------------------------------------------------------
	@RequestMapping(value = "/subscriptionChange", params = "eventUrl", method = { RequestMethod.GET }, produces = "application/xml")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public EventResult subscriptionChange(@RequestParam final String eventUrl) {
		RestTemplate restTemplate = new RestTemplate();
		Event event = restTemplate.getForEntity(eventUrl, Event.class)
				.getBody();
		Person person = new Person();
		person.setName(event.getPayload().getCompany().getName());
		person.setEditionCode(event.getPayload().getOrder().getEditionCode());
		System.out.println("Event Fired" + eventUrl);
		personService.update(person);
		return EventResult.success(person.getName());
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	// fired by AppDirect when a user cancels his subscription to our
	// application.
	// --------------------------------------------------------------------------------------------------------------------------------
	@RequestMapping(value = "/subscriptionCancel", params = "eventUrl", method = { RequestMethod.GET }, produces = "application/xml")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public EventResult subscriptionCancel(@RequestParam final String eventUrl) {
		RestTemplate restTemplate = new RestTemplate();
		Event event = restTemplate.getForEntity(eventUrl, Event.class)
				.getBody();
		Person person = new Person();
		person.setName(event.getPayload().getCompany().getName());
		person.setEditionCode(event.getPayload().getOrder().getEditionCode());
		System.out.println("Event Fired" + eventUrl);
		personService.delete(person);
		return EventResult.success(person.getName());
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	// fired by AppDirect when a subscription goes overdue or delinquent.
	// --------------------------------------------------------------------------------------------------------------------------------
	@RequestMapping(value = "/subscriptionStatus", params = "eventUrl", method = { RequestMethod.GET }, produces = "application/xml")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public EventResult subscriptionStatus(@RequestParam final String eventUrl) {
		return EventResult.success("success");
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	// fired by AppDirect when a user assigns a user to our application.
	// --------------------------------------------------------------------------------------------------------------------------------
	@RequestMapping(value = "/userAssign", params = "eventUrl", method = { RequestMethod.GET }, produces = "application/xml")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public EventResult userAssign(@RequestParam final String eventUrl) {
		return EventResult.success("success");
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	// fired by AppDirect when a user unassigns a user from our application.
	// --------------------------------------------------------------------------------------------------------------------------------
	@RequestMapping(value = "/userUnassign", params = "eventUrl", method = { RequestMethod.GET }, produces = "application/xml")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public EventResult userUnassign(@RequestParam final String eventUrl) {
		return EventResult.success("success");
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	// exception handling for 'planned' exceptions. Always returned using
	// properly formatted XML and HTTP 200.
	// --------------------------------------------------------------------------------------------------------------------------------
	@ResponseStatus(HttpStatus.OK)
	@ExceptionHandler(EventResultException.class)
	@ResponseBody
	public EventResult eventResultExceptionHandler(
			EventResultException exception) {
		return EventResult.error(exception);
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	// exception handling for all other 'unplanned' exceptions. Always returned
	// using properly formatted XML and HTTP 200.
	// --------------------------------------------------------------------------------------------------------------------------------
	@ResponseStatus(HttpStatus.OK)
	@ExceptionHandler(/* all other exception types */)
	@ResponseBody
	public EventResult unhandledExceptionHandler(Exception exception) {
		return EventResult
				.errorLogged(
						Error.UNKNOWN_ERROR,
						"An unexpected error occurred. If the problem persists, please contact a system administrator.",
						exception);
	}
}
