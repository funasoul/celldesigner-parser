/*******************************************************************************
 * Copyright 2016 Kaito Ii
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
/*
 * 
 */
package org.sbml.wrapper;

import java.math.BigDecimal;

import org.sbml._2001.ns.celldesigner.Bounds;
import org.sbml._2001.ns.celldesigner.CompartmentAlias;
import org.sbml._2001.ns.celldesigner.DoubleLine;
import org.sbml._2001.ns.celldesigner.Info;
import org.sbml._2001.ns.celldesigner.Paint;
import org.sbml._2001.ns.celldesigner.Point;
import org.sbml.jsbml.Compartment;
import org.sbml.jsbml.ext.layout.CompartmentGlyph;

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
  	
	/** The Constant DEFAULT_COMPARTMENT_WIDTH. */
	public static final double DEFAULT_COMPARTMENT_WIDTH = 12d;
	
	/** The Constant DEFAULT_COMPARTMENT_INNER_WIDTH. */
	public static final double DEFAULT_COMPARTMENT_INNER_WIDTH = 1d;
	
	/** The Constant DEFAULT_COMPARTMENT_OUTER_WIDTH. */
	public static final double DEFAULT_COMPARTMENT_OUTER_WIDTH = 2d;
	
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
	 * Instantiates a new compartment alias wrapper.
	 *
	 * @param cg the cg
	 * @param modelWrapper the model wrapper
	 */
	public CompartmentAliasWrapper(CompartmentGlyph cg, ModelWrapper modelWrapper){
		this.compartmentAlias = new CompartmentAlias();
		modelWrapper.addCompartmentAlias(compartmentAlias);
		compartmentAliased = (Compartment) cg.getCompartmentInstance();
		this.id = compartmentAliased.getId() + "alias";
		compartmentAlias.setId(id);
		this.compartment = compartmentAliased.getId();
		compartmentAlias.setCompartment(compartmentAliased.getId());
		bounds = new Bounds();
		setH(cg.getBoundingBox().getDimensions().getHeight());
		setW(cg.getBoundingBox().getDimensions().getWidth());
		setX(cg.getBoundingBox().getPosition().getX());
		setY(cg.getBoundingBox().getPosition().getY());
		compartmentAlias.setBounds(bounds);
		this.clazz = "SQUARE";
		compartmentAlias.setClazz(clazz);
		doubleLine = new DoubleLine();
		doubleLine.setInnerWidth(new BigDecimal(DEFAULT_COMPARTMENT_INNER_WIDTH));
		doubleLine.setOuterWidth(new BigDecimal(DEFAULT_COMPARTMENT_OUTER_WIDTH));
		doubleLine.setThickness(new BigDecimal(DEFAULT_COMPARTMENT_WIDTH));
		compartmentAlias.setDoubleLine(doubleLine);
		info = new Info();
		info.setAngle(new BigDecimal(0d));
		info.setState("empty");
		compartmentAlias.setInfo(info);
		namePoint = new Point();
		compartmentAlias.setNamePoint(namePoint);
		paint = new Paint();
		paint.setColor("ffcccc00");
		paint.setScheme("Color");
		compartmentAlias.setPaint(paint);
		point = new Point(); 
		compartmentAlias.setPoint(new Point());
		
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
