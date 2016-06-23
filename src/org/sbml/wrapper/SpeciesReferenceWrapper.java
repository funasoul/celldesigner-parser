/*
 * 
 */
package org.sbml.wrapper;

import org.sbml.sbml.level2.version4.SpeciesReference;

// TODO: Auto-generated Javadoc
/**
 * The Class SpeciesReferenceWrapper.
 *
 * @author Kaito Ii
 * 
 * Date Created: May 24, 2016
 */

public class SpeciesReferenceWrapper extends SpeciesReference{

  /** The model wrapper. */
  private	ModelWrapper modelWrapper;
  
  /** The s ref. */
  private	SpeciesReference sRef;
  
  /** The alias. */
  private	String alias;

	/**
	 * Instantiates a new species reference wrapper.
	 *
	 * @param sRef the s ref
	 * @param modelWrapper the model wrapper
	 */
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
	 * Gets the alias.
	 *
	 * @return String
	 * TODO
	 */
	public String getAlias() {
        return alias;
    }

	/**
	 * Sets the alias.
	 *
	 * @param alias void
	 * TODO
	 */
	public void setAlias(String alias) {
        annotation.getExtension().setAlias(alias);
        this.alias = alias;
    }

	/**
	 * Gets the aliased.
	 *
	 * @return SpeciesAliasWrapper
	 * TODO
	 */
	public SpeciesAliasWrapper getAliased(){
		return modelWrapper.getSpeciesAliasWrapperById(getAlias());
	}

}
