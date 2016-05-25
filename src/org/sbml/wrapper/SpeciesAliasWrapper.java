package org.sbml.wrapper;

import java.util.List;

import org.sbml._2001.ns.celldesigner.CompartmentAlias;
import org.sbml._2001.ns.celldesigner.ComplexSpeciesAlias;
import org.sbml._2001.ns.celldesigner.ListOfSpeciesTag;
import org.sbml._2001.ns.celldesigner.Species;
import org.sbml._2001.ns.celldesigner.SpeciesAlias;
import org.sbml._2001.ns.celldesigner.SpeciesTag;

/**
 * @author Kaito Ii
 *
 * Date Created: May 25, 2016
 */

public class SpeciesAliasWrapper extends SpeciesAlias {

	Species species;
	CompartmentAlias compartmentAlias;
	ComplexSpeciesAlias complexSpeciesAlias;
	
	/**
	 * 
	 * @return
	 * List<SpeciesTag>
	 * TODO
	 */
	public List<SpeciesTag> getListOfSpeciesTags() {
        return listOfSpeciesTag.getSpeciesTag();
    }

	/**
	 * 
	 * @param value
	 * void
	 * TODO
	 */
    public void setListOfSpeciesTags(ListOfSpeciesTag value) {
        this.listOfSpeciesTag = value;
    }
    
    /**
     * 
     * @return
     * Species
     * TODO
     */
    public Species getSpeciesAliased() {
        return species;
    }

    /**
     * 
     * @param value
     * void
     * TODO
     */
    public void setSpecies(Species value) {
        this.species = value;
    }
    
    /**
     * 
     * @return
     * CompartmentAlias
     * TODO
     */
    public CompartmentAlias getCompartmentAliased() {
        return compartmentAlias;
    }

    /**
     * 
     * @param value
     * void
     * TODO
     */
    public void setCompartmentAliased(CompartmentAlias value) {
        this.compartmentAlias = value;
    }

    /**
     * 
     * @return
     * ComplexSpeciesAlias
     * TODO
     */
    public ComplexSpeciesAlias getComplexSpeciesAliased() {
        return complexSpeciesAlias;
    }

    /**
     * 
     * @param value
     * void
     * TODO
     */
    public void setComplexSpeciesAliased(ComplexSpeciesAlias value) {
        this.complexSpeciesAlias = value;
    }

}
