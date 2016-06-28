/*
 * 
 */
package org.sbml.wrapper;

import org.sbml._2001.ns.celldesigner.CompartmentAnnotationType;
import org.sbml._2001.ns.celldesigner.CompartmentAnnotationType.Extension;
import org.sbml.sbml.level2.version4.Compartment;

// TODO: Auto-generated Javadoc
/**
 * The Class CompartmentWrapper.
 *
 * @author Kaito Ii
 * 
 * Date Created: May 24, 2016
 */

public class CompartmentWrapper extends Compartment {

	/** The model wrapper. */
	private	ModelWrapper modelWrapper;
  	
	/** The compartment. */
	private Compartment compartment;

	/**
	 * Instantiates a new compartment wrapper.
	 *
	 * @param compartment the compartment
	 * @param modelWrapper the model wrapper
	 */
	public CompartmentWrapper(Compartment compartment, ModelWrapper modelWrapper){
		this.modelWrapper = modelWrapper;
		this.compartment = compartment;
		
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
		
		if(annotation == null)
			initAnnotations();
	}

	/**
	 * Inits the annotations.
	 */
	public void initAnnotations(){
		this.annotation = new CompartmentAnnotationType();
		compartment.setAnnotation(annotation);
		annotation.setExtension(new Extension());
		annotation.getExtension().setName(compartment.getId());
	}
	
	/* (non-Javadoc)
	 * @see org.sbml.sbml.level2.version4.OriginalCompartment#getName()
	 */
	public String getName(){
		return name;
	}

	/* (non-Javadoc)
	 * @see org.sbml.sbml.level2.version4.OriginalCompartment#setName(java.lang.String)
	 */
	public void setName(String value){
		annotation.getExtension().setName(value);;
	}

	/**
	 * Checks if is sets the name.
	 *
	 * @return true, if is sets the name
	 */
	public boolean isSetName(){
		if(annotation == null || annotation.getExtension().getName() == null)
			return false;

		return true;
	}

	/**
	 * Checks if is sets the outside.
	 *
	 * @return boolean
	 * TODO
	 */
	public boolean isSetOutside(){
		if(outside == null)
			return false;

		return true;
	}

	/**
	 * Gets the outside instance.
	 *
	 * @return CompartmentWrapper
	 * TODO
	 */
	public CompartmentWrapper getOutsideInstance(){
		return modelWrapper.getCompartmentWrapperById(outside);
	}

	/**
	 * Gets the alias wrapper.
	 *
	 * @return CompartmentAliasWrapper
	 * TODO
	 */
	public CompartmentAliasWrapper getAliasWrapper() {
		return modelWrapper.getCompartmentAliasWrapperByCompartmentId(id);
	}
}
