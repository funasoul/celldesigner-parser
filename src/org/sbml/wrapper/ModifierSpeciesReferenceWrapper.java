package org.sbml.wrapper;

import org.sbml.sbml.level2.version4.ModifierSpeciesReference;

/**
 * @author Kaito Ii
 *
 * Date Created: May 24, 2016
 */

public class ModifierSpeciesReferenceWrapper extends ModifierSpeciesReference{
	
	ModifierSpeciesReference sRef;
	ModelWrapper modelWrapper;
	SpeciesWrapper speciesWrapper;
	
	/**
	 * 
	 * @param sRef
	 * @param modelWrapper
	 */
	public ModifierSpeciesReferenceWrapper(ModifierSpeciesReference sRef, ModelWrapper modelWrapper){
		 this.sRef =  sRef;
		 this.modelWrapper = modelWrapper;
		 this.annotation = sRef.getAnnotation();
		 this.metaid = sRef.getMetaid();
		 this.notes = sRef.getNotes();
		 this.species = sRef.getSpecies();
		 this.speciesWrapper = modelWrapper.getSpeciesWrapperById(sRef.getSpecies());
	}
	
	/**
	 * 
	 * @return
	 * String
	 * TODO
	 */
	public String getAlias() {
        return annotation.getExtension().getAlias();
    }
	
	/**
	 * 
	 * @param value
	 * void
	 * TODO
	 */
	public void setAlias(String value) {
        annotation.getExtension().setAlias(value);
    }
	
	/**
	 * 
	 * @return
	 * SpeciesWrapper
	 * TODO
	 */
	public SpeciesWrapper getAliased(){
		return speciesWrapper;
	}
}
