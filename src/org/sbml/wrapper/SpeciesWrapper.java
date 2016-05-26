package org.sbml.wrapper;

import java.util.List;

import org.sbml._2001.ns.celldesigner.Catalyzed;
import org.sbml._2001.ns.celldesigner.ListOfCatalyzedReactions;
import org.sbml._2001.ns.celldesigner.SpeciesIdentity;
import org.sbml.sbml.level2.version4.Compartment;
import org.sbml.sbml.level2.version4.Species;

/**
 * @author Kaito Ii
 *
 * Date Created: May 24, 2016
 */

public class SpeciesWrapper extends Species{
	
	ModelWrapper modelWrapper;
	Species species;
	Compartment compartment;
	
	public SpeciesWrapper(Species species, ModelWrapper modelWrapper){
		this.modelWrapper = modelWrapper;
		this.species = species;
		this.annotation = species.getAnnotation();
		this.boundaryCondition = species.isBoundaryCondition();
		this.charge = species.getCharge();
		//this.compartment = species.getCompartment();
		this.constant = species.isConstant();
		this.hasOnlySubstanceUnits = species.isHasOnlySubstanceUnits();
		this.id = species.getId();
		this.initialAmount = species.getInitialAmount();
		this.initialConcentration = species.getInitialConcentration();
		this.metaid = species.getMetaid();
		this.name = species.getName();
		this.notes = species.getNotes();
		this.spatialSizeUnits = species.getSpatialSizeUnits();
		this.substanceUnits = species.getSubstanceUnits();
	}
	
	/**
	 * 	
	 * @return
	 * String
	 * TODO
	 */
	public String getPositionToCompartment(){	
		return annotation.getExtension().getPositionToCompartment();
	}
	
	/**
	 * 
	 * @param value
	 * void
	 * TODO
	 */
	public void setPositionToCompartment(String value){	
		annotation.getExtension().setPositionToCompartment(value);
	}
	
	/**
	 * 
	 * @return
	 * String
	 * TODO
	 */
	public String getComplexSpecies(){
		return annotation.getExtension().getComplexSpecies();
	}
	
	/**
	 * 
	 * @param value
	 * void
	 * TODO
	 */
	public void setComplexSpecies(String value){
		annotation.getExtension().setComplexSpecies(value);
	}
	
	/**
	 * 
	 * @return
	 * SpeciesIdentity
	 * TODO
	 */
	public SpeciesIdentity getSpeciesIdentity(){
		return annotation.getExtension().getSpeciesIdentity();
	}
	
	/**
	 * 
	 * @param value
	 * void
	 * TODO
	 */
	public void setSpeciesIdentity(SpeciesIdentity value){
		annotation.getExtension().setSpeciesIdentity(value);
	}
	
	/**
	 * 
	 * @return
	 * ListOfCatalyzedReactions
	 * TODO
	 */
	public List<Catalyzed> getListOfCatalyzedReactions() {
		return annotation.getExtension().getListOfCatalyzedReactions().getCatalyzed();
	}

	/**
	 * 
	 * @param value
	 * void
	 * TODO
	 */
	public void setListOfCatalyzedReactions(ListOfCatalyzedReactions value) {
		annotation.getExtension().setListOfCatalyzedReactions(value);
	}
	
	/**
	 * 
	 * @param catalyzed
	 * void
	 * TODO
	 */
	public void addCatalyzedReaction(Catalyzed catalyzed){
		annotation.getExtension().getListOfCatalyzedReactions().getCatalyzed().add(catalyzed);
	}
	
	/**
	 * 
	 * @param catalyzed
	 * void
	 * TODO
	 */
	public void removeCatalyzedReaction(Catalyzed catalyzed){
		annotation.getExtension().getListOfCatalyzedReactions().getCatalyzed().remove(catalyzed);
	}
}
