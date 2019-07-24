package hello.value.weather;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


public class Location {

	
	String name;
	String codNaz;
	
	public Location(String name, String codNaz) {
		super();
		this.name = name;
		this.codNaz = codNaz;
	}
	public Location() {
		// TODO Auto-generated constructor stub
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCodNaz() {
		return codNaz;
	}
	public void setCodNaz(String codNaz) {
		this.codNaz = codNaz;
	}
}
