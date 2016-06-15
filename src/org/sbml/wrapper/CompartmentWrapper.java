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
		this.constant = compartment.isConstant();
		this.id = compartment.getId();
		this.metaid = compartment.getMetaid();
		this.name = compartment.getName();	 // TODO same as annotation.getExtension().getName()?
		this.notes = compartment.getNotes();
		this.outside = compartment.getOutside();
		this.size = compartment.getSize();
		this.spatialDimensions = compartment.getSpatialDimensions();
		this.units = compartment.getUnits();

		this.annotation = compartment.getAnnotation();
	}
	
	public String getName(){
		return name;
	}
	
	
	public void setName(String value){
		annotation.getExtension().setName(value);;
	}
	
	public boolean isSetName(){
		if(annotation == null || annotation.getExtension().getName() == null)
			return false;
		
		return true;
	}
	
	/**
	 * 
	 * @return
	 * boolean
	 * TODO
	 */
	public boolean isSetOutside(){
		if(outside == null)
			return false;
		
		return true;
	}
	
	/**
	 * 
	 * @return
	 * CompartmentWrapper
	 * TODO
	 */
	public CompartmentWrapper getOutsideInstance(){
		return modelWrapper.getCompartmentWrapperById(outside);
	}

	/**
	 * @return
	 * CompartmentAliasWrapper
	 * TODO
	 */
	public CompartmentAliasWrapper getAliasWrapper() {
		return modelWrapper.getCompartmentAliasWrapperByCompartmentId(id);
	}
}
