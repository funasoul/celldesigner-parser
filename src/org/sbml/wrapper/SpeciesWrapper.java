/*
 * 
 */
package org.sbml.wrapper;

import java.util.List;

import org.sbml._2001.ns.celldesigner.Catalyzed;
import org.sbml._2001.ns.celldesigner.ListOfCatalyzedReactions;
import org.sbml._2001.ns.celldesigner.SpeciesAnnotationType;
import org.sbml._2001.ns.celldesigner.SpeciesAnnotationType.Extension;
import org.sbml._2001.ns.celldesigner.SpeciesIdentity;
import org.sbml.sbml.level2.version4.Species;

// TODO: Auto-generated Javadoc
/**
 * The Class SpeciesWrapper.
 *
 * @author Kaito Ii
 * 
 * Date Created: May 24, 2016
 */

public class SpeciesWrapper extends Species{

  /** The model wrapper. */
  private	ModelWrapper modelWrapper;
  
  /** The species. */
  private	Species species;
  
  /** The compartment wrapper. */
  private	CompartmentWrapper compartmentWrapper;
  
  /** The position to compartment. */
  private	String positionToCompartment;
  
  /** The complex species. */
  private	String complexSpecies; // TODO
  
  /** The species identity. */
  private	SpeciesIdentity speciesIdentity;
  
  /** The catalyzed reactions. */
  private	List<Catalyzed> catalyzedReactions;

	/**
	 * Instantiates a new species wrapper.
	 *
	 * @param species the species
	 * @param modelWrapper the model wrapper
	 */
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
		if(annotation != null)
			setAnnotations();
		else
			initAnnotations();
		
	}

	/**
	 * Sets the annotations.
	 */
	void setAnnotations(){
		this.positionToCompartment = annotation.getExtension().getPositionToCompartment();
		this.complexSpecies = annotation.getExtension().getComplexSpecies();
		this.speciesIdentity = annotation.getExtension().getSpeciesIdentity();

		if(annotation.getExtension().getListOfCatalyzedReactions() != null)
			this.catalyzedReactions = annotation.getExtension().getListOfCatalyzedReactions().getCatalyzed();

	}
	
	/**
	 * Inits the annotations.
	 */
	void initAnnotations(){
		this.annotation = new SpeciesAnnotationType();
		species.setAnnotation(annotation);
		annotation.setExtension(new Extension());
		
		setPositionToCompartment(new String());
		
		setComplexSpecies(new String());
		
		setSpeciesIdentity(new SpeciesIdentity());
		
		setListOfCatalyzedReactions(new ListOfCatalyzedReactions());
		this.catalyzedReactions = annotation.getExtension().getListOfCatalyzedReactions().getCatalyzed();
	
	
	}
	
	/**
	 * Gets the position to compartment.
	 *
	 * @return String
	 * TODO
	 */
	public String getPositionToCompartment(){
		return positionToCompartment;
	}

	/**
	 * Sets the position to compartment.
	 *
	 * @param position void
	 * TODO
	 */
	public void setPositionToCompartment(String position){
		annotation.getExtension().setPositionToCompartment(position);
		positionToCompartment = position;
	}

	/**
	 * Checks if is sets the position to compatment.
	 *
	 * @return boolean
	 * TODO
	 */
	public boolean isSetPositionToCompatment(){
		if(positionToCompartment == null)
			return false;
		else
			return true;
	}

	/**
	 * Gets the complex species.
	 *
	 * @return String
	 * TODO
	 */
	public String getComplexSpecies(){
		return complexSpecies;
	}

	/**
	 * Sets the complex species.
	 *
	 * @param complexSpecies void
	 * TODO
	 */
	public void setComplexSpecies(String complexSpecies){
		annotation.getExtension().setComplexSpecies(complexSpecies);
		this.complexSpecies = complexSpecies;
	}

	/**
	 * Checks if is sets the complex species.
	 *
	 * @return boolean
	 * TODO
	 */
	public boolean isSetComplexSpecies(){
		if(complexSpecies == null)
			return false;
		else
			return true;
	}

	/**
	 * Gets the species identity.
	 *
	 * @return SpeciesIdentity
	 * TODO
	 */
	public SpeciesIdentity getSpeciesIdentity(){
		return speciesIdentity;
	}

	/**
	 * Sets the species identity.
	 *
	 * @param speciesIdentity void
	 * TODO
	 */
	public void setSpeciesIdentity(SpeciesIdentity speciesIdentity){
		annotation.getExtension().setSpeciesIdentity(speciesIdentity);
		this.speciesIdentity = speciesIdentity;
	}

	/**
	 * Checks if is sets the species identity.
	 *
	 * @return boolean
	 * TODO
	 */
	public boolean isSetSpeciesIdentity(){
		if(speciesIdentity == null)
			return false;
		else
			return true;
	}

	/**
	 * Gets the list of catalyzed reactions.
	 *
	 * @return ListOfCatalyzedReactions
	 * TODO
	 */
	public List<Catalyzed> getListOfCatalyzedReactions() {
		return catalyzedReactions;
	}

	/**
	 * Sets the list of catalyzed reactions.
	 *
	 * @param value void
	 * TODO
	 */
	public void setListOfCatalyzedReactions(ListOfCatalyzedReactions value) {
		annotation.getExtension().setListOfCatalyzedReactions(value);
	}

	/**
	 * void
	 * TODO.
	 *
	 * @return true, if is sets the list of catalyzed reactions
	 */
	public boolean isSetListOfCatalyzedReactions(){
		if(catalyzedReactions == null)
			return false;
		else
			return true;
	}

	/**
	 * Adds the catalyzed reaction.
	 *
	 * @param catalyzed void
	 * TODO
	 */
	public void addCatalyzedReaction(Catalyzed catalyzed){
		catalyzedReactions.add(catalyzed);
	}

	/**
	 * Removes the catalyzed reaction.
	 *
	 * @param catalyzed void
	 * TODO
	 */
	public void removeCatalyzedReaction(Catalyzed catalyzed){
		catalyzedReactions.remove(catalyzed);
	}

	/**
	 * Creates the catalyzed reaction.
	 *
	 * @return Catalyzed
	 * TODO
	 */
	public Catalyzed createCatalyzedReaction(){
		Catalyzed catalyzed = new Catalyzed();
		catalyzedReactions.add(catalyzed);

		return catalyzed;
	}

	/**
	 * Creates the catalyzed reaction.
	 *
	 * @param reaction the reaction
	 * @return Catalyzed
	 * TODO
	 */
	public Catalyzed createCatalyzedReaction(String reaction){
		Catalyzed catalyzed = createCatalyzedReaction();
		catalyzed.setReaction(reaction);

		return catalyzed;
	}

	//TODO species identity handling
}
