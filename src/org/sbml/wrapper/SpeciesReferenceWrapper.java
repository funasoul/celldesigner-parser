package org.sbml.wrapper;

import org.sbml.sbml.level2.version4.SpeciesReference;

/**
 * @author Kaito Ii
 *
 * Date Created: May 24, 2016
 */

public class SpeciesReferenceWrapper extends SpeciesReference{
	
	ModelWrapper modelWrapper;
	SpeciesReference sRef;
	String alias;
	
	public SpeciesReferenceWrapper(SpeciesReference sRef, ModelWrapper modelWrapper){
		this.sRef = sRef;
		this.modelWrapper = modelWrapper;
		this.annotation = sRef.getAnnotation();
		this.metaid = sRef.getMetaid();
		this.notes = sRef.getNotes();
		this.species = sRef.getSpecies();
		this.stoichiometry = sRef.getStoichiometry();
		this.stoichiometryMath = sRef.getStoichiometryMath();
		
		this.alias = annotation.getExtension().getAlias();
	}
	
	/**
	 * 
	 * @return
	 * String
	 * TODO
	 */
	public String getAlias() {
        return alias;
    }
	
	/**
	 * 
	 * @param alias
	 * void
	 * TODO
	 */
	public void setAlias(String alias) {
        annotation.getExtension().setAlias(alias);
        this.alias = alias;
    }

	/**
	 * 
	 * @return
	 * SpeciesAliasWrapper
	 * TODO
	 */
	public SpeciesAliasWrapper getAliased(){
		return modelWrapper.getSpeciesAliasWrapperById(getAlias());
	}
	
}
