package org.sbml.wrapper;

import java.math.BigDecimal;

import org.sbml._2001.ns.celldesigner.ListOfAntisenseRNAs;
import org.sbml._2001.ns.celldesigner.ListOfBlockDiagrams;
import org.sbml._2001.ns.celldesigner.ListOfCompartmentAliases;
import org.sbml._2001.ns.celldesigner.ListOfComplexSpeciesAliases;
import org.sbml._2001.ns.celldesigner.ListOfGenes;
import org.sbml._2001.ns.celldesigner.ListOfGroups;
import org.sbml._2001.ns.celldesigner.ListOfIncludedSpecies;
import org.sbml._2001.ns.celldesigner.ListOfLayers;
import org.sbml._2001.ns.celldesigner.ListOfProteins;
import org.sbml._2001.ns.celldesigner.ListOfRNAs;
import org.sbml._2001.ns.celldesigner.ListOfSpeciesAliases;
import org.sbml._2001.ns.celldesigner.ModelAnnotationType;
import org.sbml._2001.ns.celldesigner.ModelDisplay;
import org.sbml.sbml.level2.version4.Model;

/**
 * @author Kaito Ii
 *
 * Date Created: May 24, 2016
 */

public class ModelWrapper extends Model {

	private Model model;
	private ModelAnnotationType annotation;
	
	public ModelWrapper(Model m){
		this.model = m;
		this.annotation = m.getAnnotation();
	}
	
	/**
	 * 
	 * @return
	 * BigDecimal
	 * TODO
	 */
	public BigDecimal getModelVersion() {
        return annotation.getExtension().getModelVersion();
    }

	/**
	 * 
	 * @param value
	 * void
	 * TODO
	 */
    public void setModelVersion(BigDecimal value) {
    	annotation.getExtension().setModelVersion(value);
    }

    /**
     * 
     * @return
     * ModelDisplay
     * TODO
     */
    public ModelDisplay getModelDisplay() {
        return annotation.getExtension().getModelDisplay();
    }

    /**
     * 
     * @param value
     * void
     * TODO
     */
    public void setModelDisplay(ModelDisplay value) {
    	annotation.getExtension().setModelDisplay(value);
    }

    /**
     * 
     * @return
     * ListOfIncludedSpecies
     * TODO
     */
    public ListOfIncludedSpecies getListOfIncludedSpecies() {
        return annotation.getExtension().getListOfIncludedSpecies();
    }

    /**
     * 
     * @param value
     * void
     * TODO
     */
    public void setListOfIncludedSpecies(ListOfIncludedSpecies value) {
    	annotation.getExtension().setListOfIncludedSpecies(value);
    }

    /**
     * 
     * @return
     * ListOfCompartmentAliases
     * TODO
     */
    public ListOfCompartmentAliases getListOfCompartmentAliases() {
        return annotation.getExtension().getListOfCompartmentAliases();
    }

    /**
     * 
     * @param value
     * void
     * TODO
     */
    public void setListOfCompartmentAliases(ListOfCompartmentAliases value) {
    	annotation.getExtension().setListOfCompartmentAliases(value);
    }

    /**
     * 
     * @return
     * ListOfComplexSpeciesAliases
     * TODO
     */
    public ListOfComplexSpeciesAliases getListOfComplexSpeciesAliases() {
        return annotation.getExtension().getListOfComplexSpeciesAliases();
    }

    /**
     * 
     * @param value
     * void
     * TODO
     */
    public void setListOfComplexSpeciesAliases(ListOfComplexSpeciesAliases value) {
    	annotation.getExtension().setListOfComplexSpeciesAliases(value);
    }

    /**
     * 
     * @return
     * ListOfSpeciesAliases
     * TODO
     */
    public ListOfSpeciesAliases getListOfSpeciesAliases() {
        return annotation.getExtension().getListOfSpeciesAliases();
    }

    /**
     * Sets the value of the listOfSpeciesAliases property.
     * 
     * @param value
     *     allowed object is
     *     {@link ListOfSpeciesAliases }
     *     
     */
    public void setListOfSpeciesAliases(ListOfSpeciesAliases value) {
    	annotation.getExtension().setListOfSpeciesAliases(value);
    }

    /**
     * 
     * @return
     * ListOfGroups
     * TODO
     */
    public ListOfGroups getListOfGroups() {
        return annotation.getExtension().getListOfGroups();
    }

    /**
     * 
     * @param value
     * void
     * TODO
     */
    public void setListOfGroups(ListOfGroups value) {
    	annotation.getExtension().setListOfGroups(value);
    }

    /**
     * 
     * @return
     * ListOfProteins
     * TODO
     */
    public ListOfProteins getListOfProteins() {
        return annotation.getExtension().getListOfProteins();
    }

    /**
     * 
     * @param value
     * void
     * TODO
     */
    public void setListOfProteins(ListOfProteins value) {
    	annotation.getExtension().setListOfProteins(value);
    }

    /**
     * 
     * @return
     * ListOfGenes
     * TODO
     */
    public ListOfGenes getListOfGenes() {
        return annotation.getExtension().getListOfGenes();
    }

    /**
     * 
     * @param value
     * void
     * TODO
     */
    public void setListOfGenes(ListOfGenes value) {
    	annotation.getExtension().setListOfGenes(value);
    }

    /**
     * 
     * @return
     * ListOfRNAs
     * TODO
     */
    public ListOfRNAs getListOfRNAs() {
        return annotation.getExtension().getListOfRNAs();
    }

    /**
     * 
     * @param value
     * void
     * TODO
     */
    public void setListOfRNAs(ListOfRNAs value) {
    	annotation.getExtension().setListOfRNAs(value);
    }

    /**
     * 
     * @return
     * ListOfAntisenseRNAs
     * TODO
     */
    public ListOfAntisenseRNAs getListOfAntisenseRNAs() {
        return annotation.getExtension().getListOfAntisenseRNAs();
    }

    /**
     * 
     * @param value
     * void
     * TODO
     */
    public void setListOfAntisenseRNAs(ListOfAntisenseRNAs value) {
    	annotation.getExtension().setListOfAntisenseRNAs(value);
    }

    /**
     * 
     * @return
     * ListOfLayers
     * TODO
     */
    public ListOfLayers getListOfLayers() {
        return annotation.getExtension().getListOfLayers();
    }

    /**
     * 
     * @param value
     * void
     * TODO
     */
    public void setListOfLayers(ListOfLayers value) {
    	annotation.getExtension().setListOfLayers(value);
    }

    /**
     * 
     * @return
     * ListOfBlockDiagrams
     * TODO
     */
	public ListOfBlockDiagrams getListOfBlockDiagrams() {
        return annotation.getExtension().getListOfBlockDiagrams();
    }

    /**
     * 
     * @param value
     * void
     * TODO
     */
    public void setListOfBlockDiagrams(ListOfBlockDiagrams value) {
    	annotation.getExtension().setListOfBlockDiagrams(value);
    }

}
