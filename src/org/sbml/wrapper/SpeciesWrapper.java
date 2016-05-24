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
	
	public void addCatalyzedReaction(Catalyzed catalyzed){
		annotation.getExtension().getListOfCatalyzedReactions().getCatalyzed().add(catalyzed);
	}
	
	public void removeCatalyzedReaction(Catalyzed catalyzed){
		annotation.getExtension().getListOfCatalyzedReactions().getCatalyzed().remove(catalyzed);
	}
}
