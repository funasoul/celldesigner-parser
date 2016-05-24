package org.sbml.wrapper;

import java.math.BigDecimal;
import java.util.List;

import org.sbml._2001.ns.celldesigner.AntisenseRNA;
import org.sbml._2001.ns.celldesigner.BlockDiagram;
import org.sbml._2001.ns.celldesigner.CompartmentAlias;
import org.sbml._2001.ns.celldesigner.Gene;
import org.sbml._2001.ns.celldesigner.Group;
import org.sbml._2001.ns.celldesigner.Layer;
import org.sbml._2001.ns.celldesigner.ListOfAntisenseRNAs;
import org.sbml._2001.ns.celldesigner.ListOfBlockDiagrams;
import org.sbml._2001.ns.celldesigner.ListOfCompartmentAliases;
import org.sbml._2001.ns.celldesigner.ListOfComplexSpeciesAliases;
import org.sbml._2001.ns.celldesigner.ListOfComplexSpeciesAliases.ComplexSpeciesAlias;
import org.sbml._2001.ns.celldesigner.ListOfGenes;
import org.sbml._2001.ns.celldesigner.ListOfGroups;
import org.sbml._2001.ns.celldesigner.ListOfIncludedSpecies;
import org.sbml._2001.ns.celldesigner.ListOfLayers;
import org.sbml._2001.ns.celldesigner.ListOfProteins;
import org.sbml._2001.ns.celldesigner.ListOfRNAs;
import org.sbml._2001.ns.celldesigner.ListOfSpeciesAliases;
import org.sbml._2001.ns.celldesigner.ModelDisplay;
import org.sbml._2001.ns.celldesigner.Protein;
import org.sbml._2001.ns.celldesigner.RNA;
import org.sbml._2001.ns.celldesigner.Species;
import org.sbml._2001.ns.celldesigner.SpeciesAlias;
import org.sbml.sbml.level2.version4.Model;

/**
 * @author Kaito Ii
 *
 * Date Created: May 24, 2016
 * 
 */

public class ModelWrapper extends Model {

	
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
    public  List<Species> getListOfIncludedSpecies() {
        return annotation.getExtension().getListOfIncludedSpecies().getSpecies();
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

    public void addIncludedSpecies(Species value){
    	annotation.getExtension().getListOfIncludedSpecies().getSpecies().add(value);
    }
    
    /**
     * 
     * @param <CompartmentAliases>
     * @return
     * ListOfCompartmentAliases
     * TODO
     */
    public List<CompartmentAlias> getListOfCompartmentAliases() {
        return annotation.getExtension().getListOfCompartmentAliases().getCompartmentAlias();
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
    public List<ComplexSpeciesAlias> getListOfComplexSpeciesAliases() {
        return annotation.getExtension().getListOfComplexSpeciesAliases().getComplexSpeciesAlias();
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
    public List<SpeciesAlias> getListOfSpeciesAliases() {
        return annotation.getExtension().getListOfSpeciesAliases().getSpeciesAlias();
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
    public List<Group> getListOfGroups() {
        return annotation.getExtension().getListOfGroups().getGroup();
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
    public List<Protein> getListOfProteins() {
        return annotation.getExtension().getListOfProteins().getProtein();
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
    public List<Gene> getListOfGenes() {
        return annotation.getExtension().getListOfGenes().getGene();
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
    public List<RNA> getListOfRNAs() {
        return annotation.getExtension().getListOfRNAs().getRNA();
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
    public List<AntisenseRNA> getListOfAntisenseRNAs() {
        return annotation.getExtension().getListOfAntisenseRNAs().getAntisenseRNA();
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
    public List<Layer> getListOfLayers() {
        return annotation.getExtension().getListOfLayers().getLayer();
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
	public List<BlockDiagram> getListOfBlockDiagrams() {
        return annotation.getExtension().getListOfBlockDiagrams().getBlockDiagram();
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
