package com.jdc.location.entity;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class State {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(nullable = false, unique = true)
	private String name;
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private Type type;
	private String region;
	private String capital;
	private int population;

	public State() {}

	public State(String name, Type type, String region, String capital, int population) {
		super();
		this.name = name;
		this.type = type;
		this.region = region;
		this.capital = capital;
		this.population = population;
	}

	
	public State(int id, String name, Type type, String region, String capital, int population) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.region = region;
		this.capital = capital;
		this.population = population;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getCapital() {
		return capital;
	}

	public void setCapital(String capital) {
		this.capital = capital;
	}

	public int getPopulation() {
		return population;
	}

	public void setPopulation(int population) {
		this.population = population;
	}

	@Override
	public int hashCode() {
		return Objects.hash(capital, id, name, population, region, type);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		State other = (State) obj;
		return Objects.equals(capital, other.capital) && id == other.id && Objects.equals(name, other.name)
				&& population == other.population && Objects.equals(region, other.region) && type == other.type;
	}
}
