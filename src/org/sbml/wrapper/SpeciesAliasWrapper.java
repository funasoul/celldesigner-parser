package org.sbml.wrapper;

import java.util.List;

import org.sbml._2001.ns.celldesigner.CompartmentAlias;
import org.sbml._2001.ns.celldesigner.ComplexSpeciesAlias;
import org.sbml._2001.ns.celldesigner.ListOfSpeciesTag;
import org.sbml._2001.ns.celldesigner.Species;
import org.sbml._2001.ns.celldesigner.SpeciesAlias;
import org.sbml._2001.ns.celldesigner.SpeciesTag;

/**
 * @author Kaito Ii
 *
 * Date Created: May 25, 2016
 */

public class SpeciesAliasWrapper extends SpeciesAlias {

	ModelWrapper modelWrapper;
	Species speciesAliased;
	SpeciesWrapper speciesWrapper;
	CompartmentAlias compartmentAlias;
	ComplexSpeciesAlias complexSpeciesAlias;
	SpeciesAlias speciesAlias;
	
	public SpeciesAliasWrapper(SpeciesAlias speciesAlias, ModelWrapper modelWrapper){
		this.modelWrapper = modelWrapper;
		this.speciesAlias = speciesAlias;
		this.activity = speciesAlias.getActivity();
		this.bounds = speciesAlias.getBounds();
		this.briefView = speciesAlias.getBriefView();
		this.id = speciesAlias.getId();
		this.info = speciesAlias.getInfo();
		this.listOfSpeciesTag = speciesAlias.getListOfSpeciesTag();
		this.structuralState = speciesAlias.getStructuralState();
		this.usualView = speciesAlias.getUsualView();
		this.compartmentAlias = modelWrapper.getCompartmentAliasById(speciesAlias.getCompartmentAlias());
		this.complexSpeciesAlias = modelWrapper.getComplexSpeciesAliasById(speciesAlias.getComplexSpeciesAlias());
		this.speciesWrapper = modelWrapper.getSpeciesWrapperById(speciesAlias.getSpecies());
		this.view = speciesAlias.getView();
		this.species = speciesAlias.getSpecies();
	}
	
	public int getSBOTerm(){
		return 1;
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
	 * List<SpeciesTag>
	 * TODO
	 */
	public List<SpeciesTag> getListOfSpeciesTags() {
        return listOfSpeciesTag.getSpeciesTag();
    }

	/**
	 * 
	 * @param value
	 * void
	 * TODO
	 */
    public void setListOfSpeciesTags(ListOfSpeciesTag value) {
        this.listOfSpeciesTag = value;
    }
    
    /**
     * 
     * @return
     * Species
     * TODO
     */
    public Species getSpeciesAliased() {
        return speciesAliased;
    }

    /**
     * 
     * @param value
     * void
     * TODO
     */
    public void setSpecies(Species value) {
        this.speciesAliased = value;
    }
    
    /**
     * 
     * @return
     * SpeciesWrapper
     * TODO
     */
    public SpeciesWrapper getSpeciesWrapperAliased(){
    	return speciesWrapper;
    }
    
    /**
     * 
     * @return
     * CompartmentAlias
     * TODO
     */
    public CompartmentAlias getCompartmentAliased() {
        return compartmentAlias;
    }

    /**
     * 
     * @param value
     * void
     * TODO
     */
    public void setCompartmentAliased(CompartmentAlias value) {
        this.compartmentAlias = value;
    }

    /**
     * 
     * @return
     * ComplexSpeciesAlias
     * TODO
     */
    public ComplexSpeciesAlias getComplexSpeciesAliased() {
        return complexSpeciesAlias;
    }

    /**
     * 
     * @param value
     * void
     * TODO
     */
    public void setComplexSpeciesAliased(ComplexSpeciesAlias value) {
        this.complexSpeciesAlias = value;
    }

}
