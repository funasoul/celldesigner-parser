package org.sbml.wrapper;

import java.math.BigDecimal;

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
	double H;
	double W;
	double X;
	double Y;
	
	public CompartmentAliasWrapper(CompartmentAlias compartmentAlias, ModelWrapper modelWrapper){
		this.modelWrapper = modelWrapper;
		this.compartmentAlias = compartmentAlias;
		this.id = compartmentAlias.getId();
		this.clazz = compartmentAlias.getClazz();
		this.bounds = compartmentAlias.getBounds();
		this.point = compartmentAlias.getPoint();
		this.namePoint = compartmentAlias.getNamePoint();
		this.info = compartmentAlias.getInfo();
		this.compartment = compartmentAlias.getCompartment();
		this.compartmentWrapper = modelWrapper.getCompartmentWrapperById(compartment);
		
		this.H = bounds.getH().doubleValue();
		this.W = bounds.getW().doubleValue();
		this.X = bounds.getX().doubleValue();
		this.Y = bounds.getY().doubleValue();
		
	}
	
	/**
	 * 
	 * @return
	 * double
	 * TODO
	 */
	public double getH(){
		return H;
	}

	/**
	 * 
	 * @param h
	 * void
	 * TODO
	 */
	public void setH(Double h){
		bounds.setH(new BigDecimal(h));
		this.H = h;
	}
	
	/**
	 * 
	 * @return
	 * double
	 * TODO
	 */
	public double getW(){
		return W;
	}

	/**
	 * 
	 * @param w
	 * void
	 * TODO
	 */
	public void setW(Double w){
		bounds.setW(new BigDecimal(w));
		this.W = w;
	}
	
	/**
	 * 
	 * @return
	 * double
	 * TODO
	 */
	public double getX(){
		return X;
	}
	
	/**
	 * 
	 * @param x
	 * void
	 * TODO
	 */
	public void setX(Double x){
		bounds.setX(new BigDecimal(x));
		this.X = x;
	}
	
	/**
	 * 
	 * @return
	 * double
	 * TODO
	 */
	public double getY(){
		return Y;
	}

	/**
	 * 
	 * @param y
	 * void
	 * TODO
	 */
	public void setY(Double y){
		bounds.setY(new BigDecimal(y));
		this.Y = y;
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
     * @param compartment
     * void
     * TODO
     */
    public void setCompartmentAliased(Compartment compartment){
    	this.compartmentAliased = compartment;
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
