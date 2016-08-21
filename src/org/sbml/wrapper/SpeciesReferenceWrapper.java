/*******************************************************************************
 * Copyright 2016 Kaito Ii
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
/*
 * 
 */
package org.sbml.wrapper;

import org.sbml._2001.ns.celldesigner.SpeciesReferenceAnnotationType;
import org.sbml._2001.ns.celldesigner.SpeciesReferenceAnnotationType.Extension;
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
		this.metaid = sRef.getMetaid();
		this.notes = sRef.getNotes();
		this.species = sRef.getSpecies();
		this.stoichiometry = sRef.getStoichiometry();
		this.stoichiometryMath = sRef.getStoichiometryMath();
		this.annotation = sRef.getAnnotation();

		
		if(annotation != null)
			setAnnotations();
		else
			initAnnotations();
	}
	
	/**
	 * Sets the annotations.
	 */
	void setAnnotations(){
		this.alias = annotation.getExtension().getAlias();
	}
	
	/**
	 * Inits the annotations.
	 */
	void initAnnotations(){	
		annotation = new SpeciesReferenceAnnotationType();
		sRef.setAnnotation(annotation);
		annotation.setExtension(new Extension());
		
		this.alias = new String();
		annotation.getExtension().setAlias(alias);
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

	/**
	 * @return the modelWrapper
	 */
	public ModelWrapper getModelWrapper() {
		return modelWrapper;
	}

}
