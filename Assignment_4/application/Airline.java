package application;

public class Airline {

	private String AirlineName;
	private String AirlineNumber;
	private String DepartureAirport;
	private String ArrivalAirport;
	
	//Constructor
	public Airline(String airlineName, String airlineNumber, String departureAirport, String arrivalAirport) {
	
		AirlineName = airlineName;
		AirlineNumber = airlineNumber;
		DepartureAirport = departureAirport;
		ArrivalAirport = arrivalAirport;
	}

	public String getAirlineName() {
		return AirlineName;
	}

	public String getAirlineNumber() {
		return AirlineNumber;
	}

	public String getDepartureAirport() {
		return DepartureAirport;
	}

	public String getArrivalAirport() {
		return ArrivalAirport;
	}

	@Override
	public String toString() {
		return  AirlineName  + "   " +  AirlineNumber + "\t\t\t" + DepartureAirport + "\t\t" + ArrivalAirport;
	}
	

	
}
