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
import java.util.List;

import org.sbml._2001.ns.celldesigner.Bounds;
import org.sbml._2001.ns.celldesigner.BriefView;
import org.sbml._2001.ns.celldesigner.CompartmentAlias;
import org.sbml._2001.ns.celldesigner.ComplexSpeciesAlias;
import org.sbml._2001.ns.celldesigner.Info;
import org.sbml._2001.ns.celldesigner.ListOfSpeciesTag;
import org.sbml._2001.ns.celldesigner.Species;
import org.sbml._2001.ns.celldesigner.SpeciesAlias;
import org.sbml._2001.ns.celldesigner.SpeciesTag;
import org.sbml._2001.ns.celldesigner.StructuralStateAngle;
import org.sbml._2001.ns.celldesigner.UsualView;
import org.sbml._2001.ns.celldesigner.View;
import org.sbml.jsbml.ext.layout.SpeciesGlyph;

// TODO: Auto-generated Javadoc
/**
 * The Class SpeciesAliasWrapper.
 *
 * @author Kaito Ii
 * 
 * Date Created: May 25, 2016
 */

public class SpeciesAliasWrapper extends SpeciesAlias {

  /** The model wrapper. */
  private ModelWrapper modelWrapper;
  
  /** The species aliased. */
  private Species speciesAliased;
  
  /** The species wrapper. */
  private SpeciesWrapper speciesWrapper;
  
  /** The compartment alias. */
  private CompartmentAlias compartmentAlias;
	
  /** The compartment alias wrapper. */
  private CompartmentAliasWrapper compartmentAliasWrapper;
  
  /** The complex species alias. */
  private ComplexSpeciesAlias complexSpeciesAlias;
  
  /** The species alias. */
  private SpeciesAlias speciesAlias;
  
  /** The species tags. */
  private List<SpeciesTag> speciesTags;
  
  /** The h. */
  private double H;
  
  /** The w. */
  private double W;
  
  /** The x. */
  private double X;
  
  /** The y. */
  private double Y;

	/**
	 * Instantiates a new species alias wrapper.
	 *
	 * @param speciesAlias the species alias
	 * @param modelWrapper the model wrapper
	 */
	public SpeciesAliasWrapper(SpeciesAlias speciesAlias, ModelWrapper modelWrapper){
		this.modelWrapper = modelWrapper;
		this.speciesAlias = speciesAlias;
		this.activity = speciesAlias.getActivity();
		this.bounds = speciesAlias.getBounds();
		this.briefView = speciesAlias.getBriefView();
		this.id = speciesAlias.getId();
		this.info = speciesAlias.getInfo();
		this.structuralState = speciesAlias.getStructuralState();
		this.usualView = speciesAlias.getUsualView();
		this.compartmentAlias = modelWrapper.getCompartmentAliasById(speciesAlias.getCompartmentAlias());
		if(speciesAlias.getComplexSpeciesAlias() != null)
			this.complexSpeciesAlias = modelWrapper.getComplexSpeciesAliasById(speciesAlias.getComplexSpeciesAlias());
		this.speciesWrapper = modelWrapper.getSpeciesWrapperById(speciesAlias.getSpecies());
		this.view = speciesAlias.getView();
		this.species = speciesAlias.getSpecies();

		if(compartmentAlias != null)
			this.compartmentAliasWrapper = modelWrapper.getCompartmentAliasWrapperByCompartmentId(compartmentAlias.getId());

		this.listOfSpeciesTag = speciesAlias.getListOfSpeciesTag();
		if(listOfSpeciesTag != null)
			this.speciesTags = listOfSpeciesTag.getSpeciesTag();

		this.H = bounds.getH().doubleValue();
		this.W = bounds.getW().doubleValue();
		this.X = bounds.getX().doubleValue();
		this.Y = bounds.getY().doubleValue();
	}

	/**
	 * Instantiates a new species alias wrapper.
	 *
	 * @param sg the sg
	 * @param modelWrapper the model wrapper
	 */
	public SpeciesAliasWrapper(SpeciesGlyph sg, ModelWrapper modelWrapper) {
		this.speciesAlias = new SpeciesAlias();
		this.modelWrapper = modelWrapper;
		this.species = sg.getReference();
		speciesAlias.setSpecies(species);
		modelWrapper.getListOfSpeciesAliases().add(speciesAlias);
		this.activity = "inactive";
		speciesAlias.setActivity(activity);
		this.speciesWrapper = modelWrapper.getSpeciesWrapperById(speciesAlias.getSpecies());
		this.bounds = new Bounds();
		speciesAlias.setBounds(bounds);
		this.setH(sg.getBoundingBox().getDimensions().getHeight());
		this.setW(sg.getBoundingBox().getDimensions().getWidth());
		this.setX(sg.getBoundingBox().getPosition().getX());
		this.setY(sg.getBoundingBox().getPosition().getY());
		
		this.info = new Info();
		speciesAlias.setInfo(info);
		setListOfSpeciesTag(new ListOfSpeciesTag());
		this.view = new View();
		view.setState("usual");
		speciesAlias.setView(view);
		this.briefView = new BriefView();
		speciesAlias.setBriefView(briefView);
		this.usualView = new UsualView();
		speciesAlias.setUsualView(usualView);
		this.structuralState = new StructuralStateAngle();
		speciesAlias.setStructuralState(structuralState);
	}

	/* (non-Javadoc)
	 * @see org.sbml._2001.ns.celldesigner.SpeciesAlias#setId(java.lang.String)
	 */
	public void setId(String id){
		this.id = id;
		speciesAlias.setId(id);
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
	public void setH(double h){
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
	public void setW(double w){
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
	public void setX(double x){
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
	public void setY(double y){
		bounds.setY(new BigDecimal(y));
		this.Y = y;
	}

	/**
	 * Gets the list of species tags.
	 *
	 * @return List<SpeciesTag>
	 * TODO
	 */
	public List<SpeciesTag> getListOfSpeciesTags() {
        return speciesTags;
    }

	/**
	 * Sets the list of species tags.
	 *
	 * @param value void
	 * TODO
	 */
    public void setListOfSpeciesTags(ListOfSpeciesTag value) {
        this.listOfSpeciesTag = value;
    }

    /**
     * Gets the species aliased.
     *
     * @return Species
     * TODO
     */
    public Species getSpeciesAliased() {
        return speciesAliased;
    }

    /**
     * Sets the species.
     *
     * @param value void
     * TODO
     */
    public void setSpecies(Species value) {
        this.speciesAliased = value;
    }

    /**
     * Gets the species wrapper aliased.
     *
     * @return SpeciesWrapper
     * TODO
     */
    public SpeciesWrapper getSpeciesWrapperAliased(){
    	return speciesWrapper;
    }

    /**
     * Sets the species wrapper aliased.
     *
     * @param speciesWrapper void
     * TODO
     */
    public void setSpeciesWrapperAliased(SpeciesWrapper speciesWrapper){
    	this.speciesWrapper = speciesWrapper;
    }

    /**
     * Gets the compartment alias aliased.
     *
     * @return CompartmentAlias
     * TODO
     */
    public CompartmentAlias getCompartmentAliasAliased() {
        return compartmentAlias;
    }

    /**
     * Sets the compartment alias aliased.
     *
     * @param value void
     * TODO
     */
    public void setCompartmentAliasAliased(CompartmentAlias value) {
        this.compartmentAlias = value;
    }


    /**
     * Gets the compartment alias wrapper.
     *
     * @return CompartmentAlias
     * TODO
     */
    public CompartmentAliasWrapper getCompartmentAliasWrapper() {
        return compartmentAliasWrapper;
    }

    /**
     * Sets the compartment alias wrapper.
     *
     * @param value void
     * TODO
     */
    public void setCompartmentAliasWrapper(CompartmentAliasWrapper value) {
        this.compartmentAliasWrapper = value;
    }
    
    /**
     * Sets the compartment alias wrapper.
     *
     * @param id the new compartment alias wrapper
     */
    public void setCompartmentAliasWrapper(String id){
    	this.compartmentAliasWrapper = modelWrapper.getCompartmentAliasWrapperByCompartmentId(id);
    }
    
    /* (non-Javadoc)
     * @see org.sbml._2001.ns.celldesigner.SpeciesAlias#setCompartmentAlias(java.lang.String)
     */
    public void setCompartmentAlias(String id){
    	this.compartmentAlias = modelWrapper.getCompartmentAliasById(id);
		speciesAlias.setCompartmentAlias(id);
    }
    
    /**
     * Gets the complex species aliased.
     *
     * @return ComplexSpeciesAlias
     * TODO
     */
    public ComplexSpeciesAlias getComplexSpeciesAliased() {
        return complexSpeciesAlias;
    }

    /**
     * Sets the complex species aliased.
     *
     * @param value void
     * TODO
     */
    public void setComplexSpeciesAliased(ComplexSpeciesAlias value) {
        this.complexSpeciesAlias = value;
        setComplexSpeciesAlias(value.getId());
    }

    /**
     * Checks if is complex species.
     *
     * @return true, if is complex species
     */
    public boolean isComplexSpecies(){
    	return isSetComplexSpeciesAlias();
    }
    
    /**
     * Checks if is sets the complex species alias.
     *
     * @return boolean
     * TODO
     */
    public boolean isSetComplexSpeciesAlias(){
    	System.out.println(complexSpeciesAlias);
    	if(complexSpeciesAlias == null)
    		return false;
    	else
    		return true;
    }
}
