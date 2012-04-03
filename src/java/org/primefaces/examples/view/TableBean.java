package org.primefaces.examples.view;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.ServletContext;

import entities.Documents;
import javax.faces.bean.SessionScoped;
import javax.inject.Named;

@Named(value = "tableBean")
@SessionScoped
public class TableBean implements Serializable {
	
	private final static String[] colors;
	
	private final static String[] manufacturers;
	
	static {
		colors = new String[10];
		colors[0] = "Black";
		colors[1] = "White";
		colors[2] = "Green";
		colors[3] = "Red";
		colors[4] = "Blue";
		colors[5] = "Orange";
		colors[6] = "Silver";
		colors[7] = "Yellow";
		colors[8] = "Brown";
		colors[9] = "Maroon";
		
		manufacturers = new String[10];
		manufacturers[0] = "Mercedes";
		manufacturers[1] = "BMW";
		manufacturers[2] = "Volvo";
		manufacturers[3] = "Audi";
		manufacturers[4] = "Renault";
		manufacturers[5] = "Opel";
		manufacturers[6] = "Volkswagen";
		manufacturers[7] = "Chrysler";
		manufacturers[8] = "Ferrari";
		manufacturers[9] = "Ford";
	}

	private List<Documents> cars;
	
	private Documents selectedCar;

	public TableBean() {
		cars = new ArrayList<Documents>();
		
		populateRandomCars(cars, 50);
	}
	
	public Documents getSelectedCar() {
		return selectedCar;
	}

	public void setSelectedCar(Documents selectedCar) {
		this.selectedCar = selectedCar;
	}

	private void populateRandomCars(List<Documents> list, int size) {
		for(int i = 0 ; i < size ; i++)
			list.add(new Documents());
	}

	public List<Documents> getCars() {
		return cars;
	}
}
		