package org.sbml.wrapper;

import java.math.BigDecimal;
import java.util.List;

import org.sbml._2001.ns.celldesigner.CompartmentAlias;
import org.sbml._2001.ns.celldesigner.ComplexSpeciesAlias;
import org.sbml._2001.ns.celldesigner.ListOfSpeciesTag;
import org.sbml._2001.ns.celldesigner.SpeciesAlias;
import org.sbml._2001.ns.celldesigner.SpeciesTag;

/**
 * @author Kaito Ii
 *
 * Date Created: Jun 20, 2016
 */

public class ComplexSpeciesAliasWrapper extends ComplexSpeciesAlias {
	
	private ComplexSpeciesAlias complexSpeciesAlias;
	private ModelWrapper modelWrapper;
	private double H;
	private double W;
	private double X;
	private double Y;
	private SpeciesWrapper speciesWrapper;
	private CompartmentAliasWrapper compartmentAliasWrapper;
	private List<SpeciesTag> speciesTags;
	
	public ComplexSpeciesAliasWrapper(ComplexSpeciesAlias complexSpeciesAlias, ModelWrapper modelWrapper){
		this.modelWrapper = modelWrapper;
		this.complexSpeciesAlias = complexSpeciesAlias;
		this.activity = complexSpeciesAlias.getActivity();
		this.bounds = complexSpeciesAlias.getBounds();
		this.briefView = complexSpeciesAlias.getBriefView();
		this.id = complexSpeciesAlias.getId();
		this.info = complexSpeciesAlias.getInfo();
		this.structuralState = complexSpeciesAlias.getStructuralState();
		this.usualView = complexSpeciesAlias.getUsualView();
		this.compartmentAlias = complexSpeciesAlias.getCompartmentAlias();
		this.compartmentAliasWrapper = modelWrapper.getCompartmentAliasWrapperByCompartmentId(complexSpeciesAlias.getCompartmentAlias());
		this.speciesWrapper = modelWrapper.getSpeciesWrapperById(complexSpeciesAlias.getSpecies());
		this.view = complexSpeciesAlias.getView();
		this.species = complexSpeciesAlias.getSpecies();
		
		this.listOfSpeciesTag = complexSpeciesAlias.getListOfSpeciesTag();
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
     * ComplexSpeciesAlias
     * TODO
     */
    public ComplexSpeciesAlias getComplexSpeciesAliased() {
        return complexSpeciesAlias;
    }
	
}
