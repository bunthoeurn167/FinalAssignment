package coding;
import javax.persistence.*;
@Entity
@Table(name = "FlightDepArr")
public class flightDepArr {
	@Id
	@Column(name = "id")
	private int id;
	
	@Column(name = "code")
	private String code;
	
	@Column(name = "flightnumber")
	private int flightNumber;
	
	@Column(name= "status")
	private String status;
	
	@Column(name = "type")
	private String type;
	
	@Column(name = "departurecode")
	private String depCode;
	
	@Column(name = "departuregate")
	private String depGate;
	
	@Column(name = "departureday")
	private String depDay;
	
	@Column(name = "departuretime")
	private String depTime;
	
	@Column(name = "arrivalcode")
	private String arrCode;
	
	@Column(name = "arrivalgate")
	private String arrGate;
	
	@Column(name = "arrivalday")
	private String arrDay;
	
	@Column(name = "arrivaltime")
	private String arrTime;
	
	public flightDepArr()
	{
		
	}
	
	public flightDepArr(String code, int flight, String s, String t, String dc, String dg , String dd, String dt, String ac, String ag, String ad, String at)
	{
		this.code = code;
		flightNumber = flight;
		status = s;
		type = t;
		depCode = dc;
		depGate = dg;
		depDay = dd;
		depTime = dt;
		arrCode = ac;
		arrGate = ag;
		arrDay = ad;
		arrTime = at;
		
	}
	public String[] toStringFlight()
	{
		String output[] = {code+flightNumber, flightNumber +"", status, type, depCode, depGate, depDay, depTime, arrCode, arrGate, arrDay, arrTime};
		return output;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getFlightNumber() {
		return flightNumber;
	}

	public void setFlightNumber(int flightNumber) {
		this.flightNumber = flightNumber;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDepCode() {
		return depCode;
	}

	public void setDepCode(String depCode) {
		this.depCode = depCode;
	}

	public String getDepGate() {
		return depGate;
	}

	public void setDepGate(String depGate) {
		this.depGate = depGate;
	}

	public String getDepDay() {
		return depDay;
	}

	public void setDepDay(String depDay) {
		this.depDay = depDay;
	}

	public String getDepTime() {
		return depTime;
	}

	public void setDepTime(String depTime) {
		this.depTime = depTime;
	}

	public String getArrCode() {
		return arrCode;
	}

	public void setArrCode(String arrCode) {
		this.arrCode = arrCode;
	}

	public String getArrGate() {
		return arrGate;
	}

	public void setArrGate(String arrGate) {
		this.arrGate = arrGate;
	}

	public String getArrDay() {
		return arrDay;
	}

	public void setArrDay(String arrDay) {
		this.arrDay = arrDay;
	}

	public String getArrTime() {
		return arrTime;
	}

	public void setArrTime(String arrTime) {
		this.arrTime = arrTime;
	}
}
