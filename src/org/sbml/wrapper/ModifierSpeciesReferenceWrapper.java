package org.sbml.wrapper;

import org.sbml._2001.ns.celldesigner.SpeciesReferenceAnnotationType;
import org.sbml.sbml.level2.version4.ModifierSpeciesReference;

/**
 * @author Kaito Ii
 *
 * Date Created: May 24, 2016
 */

public class ModifierSpeciesReferenceWrapper {

	private ModifierSpeciesReference speciesReference;
	private SpeciesReferenceAnnotationType annotation;
	
	public ModifierSpeciesReferenceWrapper(ModifierSpeciesReference s){
		this.speciesReference = s;
		annotation = s.getAnnotation();
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
