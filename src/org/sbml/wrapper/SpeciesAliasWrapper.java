package org.sbml.wrapper;

import java.math.BigDecimal;
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
	List<SpeciesTag> speciesTags;
	double H;
	double W;
	double X;
	double Y;
	
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
		this.complexSpeciesAlias = modelWrapper.getComplexSpeciesAliasById(speciesAlias.getComplexSpeciesAlias());
		this.speciesWrapper = modelWrapper.getSpeciesWrapperById(speciesAlias.getSpecies());
		this.view = speciesAlias.getView();
		this.species = speciesAlias.getSpecies();
	
		this.listOfSpeciesTag = speciesAlias.getListOfSpeciesTag();
		
		if(listOfSpeciesTag != null)
			this.speciesTags = listOfSpeciesTag.getSpeciesTag();
		
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
	public void setH(double h){
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
	public void setW(double w){
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
	public void setX(double x){
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
	public void setY(double y){
		bounds.setY(new BigDecimal(y));
		this.Y = y;
	}
	
	
	/**
	 * 
	 * @return
	 * List<SpeciesTag>
	 * TODO
	 */
	public List<SpeciesTag> getListOfSpeciesTags() {
        return speciesTags;
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
