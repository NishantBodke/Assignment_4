package application;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

public class AirlineController implements Initializable {

	@FXML
	private RadioButton RadioAirline;

	@FXML
	private ToggleGroup searchOption;

	@FXML
	private RadioButton RadioAirlineNumber;

	@FXML
	private RadioButton RadioDepartureAirport;

	@FXML
	private RadioButton RadioArrivalAirport;

	@FXML
	private ChoiceBox<String> choiceBoxFieldAirline;

	@FXML
	private ChoiceBox<String> choiceBoxFieldnum;

	@FXML
	private ChoiceBox<String> choiceBoxFieldDeparture;

	@FXML
	private ChoiceBox<String> choiceBoxFieldArrival;

	@FXML
	private Button SearchBtn;

	@FXML
	private Button CloseBtn;

	@FXML
	private ListView<String> listView;

	private ArrayList<Airline> resultSet = new ArrayList();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		GetFromFile();
		//to add header to the list view
		listView.getItems().add("Airline \t Airline No \t Departure \t Arrival");
		
	}

	@FXML
	void OnClose(ActionEvent event) {
		//closing the window.
		Platform.exit();
	}

	@FXML
	void OnSearch(ActionEvent event) {

		RadioButton selectedRadioButton = (RadioButton) searchOption.getSelectedToggle();
		String toggleGroupValue = selectedRadioButton.getId();

		switch (toggleGroupValue) { // This will enable the choice box only when the corresponding radio button is
									// clicked.
		case "RadioAirline":
			LookupByAirlineName();
			break;
		case "RadioAirlineNumber":
			LookupByAirlineNumber();
			break;
		case "RadioDepartureAirport":
			LookupByAirlineDeparture();
			break;
		case "RadioArrivalAirport":
			LookupByAirlineArrival();
			break;
		}
	}

	private void LookupByAirlineArrival() {
		// to display the items in the listView.
		listView.getItems().clear();
		listView.getItems().add("Airline \t Airline No \t Departure \t Arrival");
		String match = choiceBoxFieldArrival.getSelectionModel().getSelectedItem();
		for (Airline item : resultSet) {
			if (item.getArrivalAirport().equals(match)) {
				listView.getItems().add(item.toString());
			}
		}
	}

	private void LookupByAirlineDeparture() {
		// to display the items in the listView.
		listView.getItems().clear();
		listView.getItems().add("Airline \t Airline No \t Departure \t Arrival");
		String match = choiceBoxFieldDeparture.getSelectionModel().getSelectedItem();
		for (Airline item : resultSet) {
			if (item.getDepartureAirport().equals(match)) {
				listView.getItems().add(item.toString());
			}
		}

	}

	private void LookupByAirlineNumber() {
		// to display the items in the listView.
		listView.getItems().clear();
		listView.getItems().add("Airline \t Airline No \t Departure \t Arrival");
		String match = choiceBoxFieldnum.getSelectionModel().getSelectedItem();
		for (Airline item : resultSet) {
			if (item.getAirlineNumber().equals(match)) {
				listView.getItems().add(item.toString());
			}
		}

	}

	private void LookupByAirlineName() {
		// to display the items in the listView.
		listView.getItems().clear();
		listView.getItems().add("Airline \t Airline No \t Departure \t Arrival");
		String match = choiceBoxFieldAirline.getSelectionModel().getSelectedItem();
		for (Airline item : resultSet) {
			if (item.getAirlineName().equals(match)) {
				listView.getItems().add(item.toString());
			}
		}

	}

	@FXML
	void ClickedRadioBtn(ActionEvent event) {

		RadioButton selectedRadioButton = (RadioButton) searchOption.getSelectedToggle();
		String toggleGroupValue = selectedRadioButton.getId();

		// Disabling the radio buttons.
		choiceBoxFieldAirline.setDisable(true);
		choiceBoxFieldnum.setDisable(true);
		choiceBoxFieldDeparture.setDisable(true);
		choiceBoxFieldArrival.setDisable(true);

		switch (toggleGroupValue) { // This will enable the choice box only when the corresponding radio button is
									// clicked.
		case "RadioAirline":
			choiceBoxFieldAirline.setDisable(false);
			GetCBFieldAirlineOptions();
			break;
		case "RadioAirlineNumber":
			choiceBoxFieldnum.setDisable(false);
			GetCBFieldNum();
			break;
		case "RadioDepartureAirport":
			choiceBoxFieldDeparture.setDisable(false);
			GetCBFieldDeparture();
			break;
		case "RadioArrivalAirport":
			choiceBoxFieldArrival.setDisable(false);
			GetCBFieldArrival();
			break;
		}

	}

	private void GetCBFieldArrival() {
		// populating the options with unique Arrival airport.
		ArrayList<String> myArrayList = new ArrayList();
		choiceBoxFieldArrival.getItems().clear();
		choiceBoxFieldArrival.setValue("Arrival Airport");
		

		for (Airline item : resultSet) {
			if (myArrayList.contains(item.getArrivalAirport().toString())) {
				continue;
			} else {
				myArrayList.add(item.getArrivalAirport());
				choiceBoxFieldArrival.getItems().add(item.getArrivalAirport().toString());
			}
		}
	}

	private void GetCBFieldDeparture() {

		// populating the options with unique departure airport.
		ArrayList<String> myArrayList = new ArrayList();
		choiceBoxFieldDeparture.getItems().clear();
		choiceBoxFieldDeparture.setValue("Departure Airport");

		for (Airline item : resultSet) {
			if (myArrayList.contains(item.getDepartureAirport().toString())) {
				continue;
			} else {
				myArrayList.add(item.getDepartureAirport());
				choiceBoxFieldDeparture.getItems().add(item.getDepartureAirport().toString());
			}
		}
	}

	private void GetCBFieldNum() {
		// populating the options with unique Airline Number.
		ArrayList<String> myArrayList = new ArrayList();
		choiceBoxFieldnum.getItems().clear();
		choiceBoxFieldnum.setValue("Airline Number");

		for (Airline item : resultSet) {
			if (myArrayList.contains(item.getAirlineNumber().toString())) {
				continue;
			}

			else {
				myArrayList.add(item.getAirlineNumber());
				choiceBoxFieldnum.getItems().add(item.getAirlineNumber().toString());
			}
		}
	}

	private void GetCBFieldAirlineOptions() {
		// populating the options with unique Airline Names.

		ArrayList<String> myArrayList = new ArrayList();
		choiceBoxFieldAirline.getItems().clear();
		choiceBoxFieldAirline.setValue("Airline Name");


		for (Airline item : resultSet) {
			if (myArrayList.contains(item.getAirlineName().toString())) {
				continue;
			} else {
				myArrayList.add(item.getAirlineName());
				choiceBoxFieldAirline.getItems().add(item.getAirlineName().toString());
			}

		}

	}

	// Creating another method to get the data from the file.

	public void GetFromFile()

	{
		//Reading data from file.
		File file = new File("Flight.txt");
		Scanner sc;
		try {
			sc = new Scanner(file);
			while (sc.hasNextLine()) {
				String[] airline = sc.nextLine().split(",");
				resultSet.add(new Airline(airline[0], airline[1], airline[2], airline[3]));
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
