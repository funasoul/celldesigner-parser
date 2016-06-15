package org.sbml.wrapper;

import java.util.List;

import org.sbml._2001.ns.celldesigner.Catalyzed;
import org.sbml._2001.ns.celldesigner.ListOfCatalyzedReactions;
import org.sbml._2001.ns.celldesigner.SpeciesIdentity;
import org.sbml.sbml.level2.version4.Species;

/**
 * @author Kaito Ii
 *
 * Date Created: May 24, 2016
 */

public class SpeciesWrapper extends Species{
	
	ModelWrapper modelWrapper;
	Species species;
	CompartmentWrapper compartmentWrapper;
	String positionToCompartment;
	String complexSpecies; // TODO
	SpeciesIdentity speciesIdentity;
	List<Catalyzed> catalyzedReactions;
	
	public SpeciesWrapper(Species species, ModelWrapper modelWrapper){
		this.modelWrapper = modelWrapper;
		this.species = species;
		this.boundaryCondition = species.isBoundaryCondition();
		this.charge = species.getCharge();
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
		
		this.compartmentWrapper = modelWrapper.getCompartmentWrapperById(species.getCompartment());
		this.annotation = species.getAnnotation();
		this.positionToCompartment = annotation.getExtension().getPositionToCompartment();
		this.complexSpecies = annotation.getExtension().getComplexSpecies();
		this.speciesIdentity = annotation.getExtension().getSpeciesIdentity();

		if(annotation.getExtension().getListOfCatalyzedReactions() != null)
			this.catalyzedReactions = annotation.getExtension().getListOfCatalyzedReactions().getCatalyzed();
	}
	
	/**
	 * 	
	 * @return
	 * String
	 * TODO
	 */
	public String getPositionToCompartment(){	
		return positionToCompartment;
	}
	
	/**
	 * 
	 * @param position
	 * void
	 * TODO
	 */
	public void setPositionToCompartment(String position){	
		annotation.getExtension().setPositionToCompartment(position);
		positionToCompartment = position;
	}
	
	/**
	 * 
	 * @return
	 * boolean
	 * TODO
	 */
	public boolean isSetPositionToCompatment(){
		if(positionToCompartment == null)
			return false;
		else
			return true;
	}
	
	/**
	 * 
	 * @return
	 * String
	 * TODO
	 */
	public String getComplexSpecies(){
		return complexSpecies;
	}
	
	/**
	 * 
	 * @param complexSpecies
	 * void
	 * TODO
	 */
	public void setComplexSpecies(String complexSpecies){
		annotation.getExtension().setComplexSpecies(complexSpecies);
		this.complexSpecies = complexSpecies;
	}
	
	/**
	 * 
	 * @return
	 * boolean
	 * TODO
	 */
	public boolean isSetComplexSpecies(){
		if(complexSpecies == null)
			return false;
		else
			return true;
	}
	
	/**
	 * 
	 * @return
	 * SpeciesIdentity
	 * TODO
	 */
	public SpeciesIdentity getSpeciesIdentity(){
		return speciesIdentity;
	}
	
	/**
	 * 
	 * @param speciesIdentity
	 * void
	 * TODO
	 */
	public void setSpeciesIdentity(SpeciesIdentity speciesIdentity){
		annotation.getExtension().setSpeciesIdentity(speciesIdentity);
		this.speciesIdentity = speciesIdentity;
	}
	
	/**
	 * 
	 * @return
	 * boolean
	 * TODO
	 */
	public boolean isSetSpeciesIdentity(){
		if(speciesIdentity == null)
			return false;
		else
			return true;
	}
	
	/**
	 * 
	 * @return
	 * ListOfCatalyzedReactions
	 * TODO
	 */
	public List<Catalyzed> getListOfCatalyzedReactions() {
		return catalyzedReactions;
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
	 * 
	 * void
	 * TODO
	 */
	public boolean isSetListOfCatalyzedReactions(){
		if(catalyzedReactions == null)
			return false;
		else
			return true;
	}
	
	/**
	 * 
	 * @param catalyzed
	 * void
	 * TODO
	 */
	public void addCatalyzedReaction(Catalyzed catalyzed){
		catalyzedReactions.add(catalyzed);
	}
	
	/**
	 * 
	 * @param catalyzed
	 * void
	 * TODO
	 */
	public void removeCatalyzedReaction(Catalyzed catalyzed){
		catalyzedReactions.remove(catalyzed);
	}
	
	/**
	 * 
	 * @return
	 * Catalyzed
	 * TODO
	 */
	public Catalyzed createCatalyzedReaction(){
		Catalyzed catalyzed = new Catalyzed();
		catalyzedReactions.add(catalyzed);
		
		return catalyzed;
	}
	
	/**
	 * 
	 * @return
	 * Catalyzed
	 * TODO
	 */
	public Catalyzed createCatalyzedReaction(String reaction){
		Catalyzed catalyzed = createCatalyzedReaction();
		catalyzed.setReaction(reaction);
		
		return catalyzed;
	}
	
	//TODO species identity handling
}
