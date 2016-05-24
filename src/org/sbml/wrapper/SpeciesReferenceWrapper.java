package org.sbml.wrapper;

import org.sbml.sbml.level2.version4.SpeciesReference;

/**
 * @author Kaito Ii
 *
 * Date Created: May 24, 2016
 */

public class SpeciesReferenceWrapper extends SpeciesReference{
	
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
