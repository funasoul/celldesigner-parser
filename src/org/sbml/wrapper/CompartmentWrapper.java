package org.sbml.wrapper;

import org.sbml.sbml.level2.version4.Compartment;

/**
 * @author Kaito Ii
 *
 * Date Created: May 24, 2016
 */

public class CompartmentWrapper extends Compartment {

	ModelWrapper modelWrapper;
	Compartment compartment;
	
	public CompartmentWrapper(Compartment compartment, ModelWrapper modelWrapper){
		this.compartment = compartment;
		this.modelWrapper = modelWrapper;
		this.annotation = compartment.getAnnotation();
		this.constant = compartment.isConstant();
		this.id = compartment.getId();
		this.metaid = compartment.getMetaid();
		this.name = compartment.getName();
		this.notes = compartment.getNotes();
		this.outside = compartment.getOutside();
		this.size = compartment.getSize();
		this.spatialDimensions = compartment.getSpatialDimensions();
		this.units = compartment.getUnits();
	}
	
	public String getName(){
		return annotation.getExtension().getName();
	}
	
	
	public void setName(String value){
		annotation.getExtension().setName(value);;
	}
	
	public boolean isSetName(){
		if(annotation == null || annotation.getExtension().getName() == null)
			return false;
		
		return true;
	}
}
