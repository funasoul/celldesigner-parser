package org.sbml.wrapper;

import java.math.BigDecimal;

import org.sbml._2001.ns.celldesigner.CompartmentAlias;
import org.sbml.jsbml.Compartment;

// TODO: Auto-generated Javadoc
/**
 * The Class CompartmentAliasWrapper.
 *
 * @author Kaito Ii
 * 
 * Date Created: Jun 2, 2016
 */

public class CompartmentAliasWrapper extends CompartmentAlias {

	/** The model wrapper. */
	private ModelWrapper modelWrapper;
	
	/** The compartment aliased. */
	private	Compartment compartmentAliased;
	
	/** The compartment wrapper. */
	private	CompartmentWrapper compartmentWrapper;
	
	/** The compartment alias. */
	private	CompartmentAlias compartmentAlias;
  	
	  /** The h. */
	  private	double H;
  	
	  /** The w. */
	  private	double W;
  	
	  /** The x. */
	  private	double X;
  	
	  /** The y. */
	  private	double Y;
  	
	  /** The name X. */
	  private	double nameX;
  	
	  /** The name Y. */
	  private	double nameY;

	/**
	 * Instantiates a new compartment alias wrapper.
	 *
	 * @param compartmentAlias the compartment alias
	 * @param modelWrapper the model wrapper
	 */
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

		this.nameX = compartmentAlias.getNamePoint().getX().doubleValue();
		this.nameY = compartmentAlias.getNamePoint().getY().doubleValue();

	}

	/**
	 * Gets the name X.
	 *
	 * @return double
	 * TODO
	 */
	public double getNameX(){
		return nameX;
	}

	/**
	 * Sets the name X.
	 *
	 * @param value void
	 * TODO
	 */
	public void setNameX(double value){
		nameX = value;
		compartmentAlias.getNamePoint().setX(new BigDecimal(value));
	}

	/**
	 * Gets the name Y.
	 *
	 * @return double
	 * TODO
	 */
	public double getNameY(){
		return nameY;
	}

	/**
	 * Sets the name Y.
	 *
	 * @param value void
	 * TODO
	 */
	public void setNameY(double value){
		nameY = value;
		compartmentAlias.getNamePoint().setY(new BigDecimal(value));
	}

	/**
	 * Gets the h.
	 *
	 * @return double
	 * TODO
	 */
	public double getH(){
		return H;
	}

	/**
	 * Sets the h.
	 *
	 * @param h void
	 * TODO
	 */
	public void setH(Double h){
		bounds.setH(new BigDecimal(h));
		this.H = h;
	}

	/**
	 * Gets the w.
	 *
	 * @return double
	 * TODO
	 */
	public double getW(){
		return W;
	}

	/**
	 * Sets the w.
	 *
	 * @param w void
	 * TODO
	 */
	public void setW(Double w){
		bounds.setW(new BigDecimal(w));
		this.W = w;
	}

	/**
	 * Gets the x.
	 *
	 * @return double
	 * TODO
	 */
	public double getX(){
		return X;
	}

	/**
	 * Sets the x.
	 *
	 * @param x void
	 * TODO
	 */
	public void setX(Double x){
		bounds.setX(new BigDecimal(x));
		this.X = x;
	}

	/**
	 * Gets the y.
	 *
	 * @return double
	 * TODO
	 */
	public double getY(){
		return Y;
	}

	/**
	 * Sets the y.
	 *
	 * @param y void
	 * TODO
	 */
	public void setY(Double y){
		bounds.setY(new BigDecimal(y));
		this.Y = y;
	}

    /**
     * Gets the compartment aliased.
     *
     * @return Species
     * TODO
     */
    public Compartment getCompartmentAliased() {
        return compartmentAliased;
    }

    /**
     * Sets the compartment aliased.
     *
     * @param compartment void
     * TODO
     */
    public void setCompartmentAliased(Compartment compartment){
    	this.compartmentAliased = compartment;
    }

    /**
     * Gets the compartment wrapper aliased.
     *
     * @return CompartmentWrapper
     * TODO
     */
    public CompartmentWrapper getCompartmentWrapperAliased(){
    	return compartmentWrapper;
    }
}
