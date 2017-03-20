package com.cisc181.core;

public class PersonException extends Exception {
	private Person p;
	
	public PersonException(){
		super();
	}
	
	public PersonException(String message){
		super(message);
	}
	
	public PersonException(Person p){
		super(p.getLastName());
		this.p = p;
	}
	
	public PersonException(Person p, String message){
		super(p.getLastName());
		this.p = p;
		System.out.println(this + message);
	}
	
	public Person P(){
		return p;
	}
}