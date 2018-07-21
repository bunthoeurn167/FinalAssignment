package coding;

import javax.persistence.Column;
import javax.persistence.*;
import javax.persistence.Id;

@Entity
@Table(name = "AirlineAircraft")

public class Airline_Aircraft {
	@Id
	@Column(name = "id")
	private int id;
	
	@Column(name = "airlinename")
	private String airlineName;
	
	@Column(name = "airlinecode")
	private String airlineCode;
	
	@Column(name = "model")
	private String model;
	
	@Column(name = "firstclasscapacity")
	private int firstClassCapacity;
	
	@Column(name = "businessclasscapacity")
	private int businessClassCapacity;
	
	@Column(name = "economyclasscapacity")
	private int economyClassCapacity;
	
	public Airline_Aircraft()
	{
		
	}
	
	public Airline_Aircraft(String name, String code,String model, int fC, int bC, int eC)
	{
		airlineName = name;
		airlineCode = code;
		this.model= model;
		firstClassCapacity = fC;
		businessClassCapacity = bC;
		economyClassCapacity = eC;
	}
	
	public String[] toStringAirline_Aircraft()
	{
		String[] output = {airlineName , airlineCode, model, firstClassCapacity+"", businessClassCapacity+"", economyClassCapacity+""};
		return output;
	}
	
	public String getAirlineCode()
	{
		return airlineCode;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAirlineName() {
		return airlineName;
	}

	public void setAirlineName(String airlineName) {
		this.airlineName = airlineName;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getFirstClassCapacity() {
		return firstClassCapacity;
	}

	public void setFirstClassCapacity(int firstClassCapacity) {
		this.firstClassCapacity = firstClassCapacity;
	}

	public int getBusinessClassCapacity() {
		return businessClassCapacity;
	}

	public void setBusinessClassCapacity(int businessClassCapacity) {
		this.businessClassCapacity = businessClassCapacity;
	}

	public int getEconomyClassCapacity() {
		return economyClassCapacity;
	}

	public void setEconomyClassCapacity(int economyClassCapacity) {
		this.economyClassCapacity = economyClassCapacity;
	}

	public void setAirlineCode(String airlineCode) {
		this.airlineCode = airlineCode;
	}
	
	
}
