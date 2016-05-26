package org.sbml.wrapper;

import org.sbml.sbml.level2.version4.SimpleSpeciesReference;
import org.sbml.sbml.level2.version4.SpeciesReference;

/**
 * @author Kaito Ii
 *
 * Date Created: May 24, 2016
 */

public class SpeciesReferenceWrapper extends SpeciesReference{
	
	ModelWrapper modelWrapper;
	SpeciesReference sRef;
	SimpleSpeciesReference species;
	
	public SpeciesReferenceWrapper(SpeciesReference sRef, ModelWrapper modelWrapper){
		this.sRef = sRef;
		this.modelWrapper = modelWrapper;
		this.annotation = sRef.getAnnotation();
		this.metaid = sRef.getMetaid();
		this.notes = sRef.getNotes();
		//this.species = sRef.getSpecies();
		this.stoichiometry = sRef.getStoichiometry();
		this.stoichiometryMath = sRef.getStoichiometryMath();
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

}
