package org.sbml.wrapper;

import org.sbml._2001.ns.celldesigner.CompartmentAlias;
import org.sbml.jsbml.Compartment;

/**
 * @author Kaito Ii
 *
 * Date Created: Jun 2, 2016
 */

public class CompartmentAliasWrapper extends CompartmentAlias {
	
	ModelWrapper modelWrapper;
	Compartment compartmentAliased;
	CompartmentWrapper compartmentWrapper;
	CompartmentAlias compartmentAlias;
	
	public CompartmentAliasWrapper(CompartmentAlias compartmentAlias, ModelWrapper modelWrapper){
		this.modelWrapper = modelWrapper;
		this.id = compartmentAlias.getId();
		this.compartmentAlias = compartmentAlias;
		this.clazz = compartmentAlias.getClazz();
		this.bounds = compartmentAlias.getBounds();
		this.point = compartmentAlias.getPoint();
		this.namePoint = compartmentAlias.getNamePoint();
		this.info = compartmentAlias.getInfo();
		this.compartment = compartmentAlias.getCompartment();
	}
	
	/**
	 * 
	 * @return
	 * double
	 * TODO
	 */
	public double getH(){
		return bounds.getH().doubleValue();
	}

	/**
	 * 
	 * @return
	 * double
	 * TODO
	 */
	public double getW(){
		return bounds.getW().doubleValue();
	}

	
	/**
	 * 
	 * @return
	 * double
	 * TODO
	 */
	public double getX(){
		return bounds.getX().doubleValue();
	}
	
	/**
	 * 
	 * @return
	 * double
	 * TODO
	 */
	public double getY(){
		return bounds.getY().doubleValue();
	}

    /**
     * 
     * @return
     * Species
     * TODO
     */
    public Compartment getCompartmentAliased() {
        return compartmentAliased;
    }
	
    /**
     * 
     * @param value
     * void
     * TODO
     */
    public void setCompartmentAliased(Compartment value){
    	this.compartmentAliased = value;
    }
    
    /**
     * 
     * @return
     * CompartmentWrapper
     * TODO
     */
    public CompartmentWrapper getCompartmentWrapperAliased(){
    	return compartmentWrapper;
    }
}
