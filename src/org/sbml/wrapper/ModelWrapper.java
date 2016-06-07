package org.sbml.wrapper;

import java.math.BigDecimal;
import java.util.ArrayList;
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
import org.sbml._2001.ns.celldesigner.SpeciesAlias;
import org.sbml.sbml.level2.version4.Compartment;
import org.sbml.sbml.level2.version4.Event;
import org.sbml.sbml.level2.version4.FunctionDefinition;
import org.sbml.sbml.level2.version4.Model;
import org.sbml.sbml.level2.version4.Parameter;
import org.sbml.sbml.level2.version4.Reaction;
import org.sbml.sbml.level2.version4.Rule;
import org.sbml.sbml.level2.version4.Species;
import org.sbml.sbml.level2.version4.UnitDefinition;

/**
 * @author Kaito Ii
 *
 * Date Created: May 24, 2016
 * 
 */

public class ModelWrapper extends Model {	
	
	Model model;
	List<CompartmentWrapper> cWrapperList;
	List<ReactionWrapper> rWrapperList;
	List<SpeciesWrapper> sWrapperList;
	List<SpeciesAliasWrapper> sAliasWrapperList;
	List<CompartmentAliasWrapper>  cAliasWrapperList;
	
	List<Event> eventList;
	List<FunctionDefinition> functionDefinitionList;
	List<Parameter> parameterList;
	List<Rule> ruleList;
	List<UnitDefinition> unitDefinitionList;
	
	List<AntisenseRNA> antiSenseRNAList;
	List<BlockDiagram> blockDiagramList;
	List<CompartmentAlias> cAliasList;
	List<ComplexSpeciesAlias> complexSAliasList;
	List<Gene> geneList;
	List<Species> includedSpeciesList;
	List<Layer> layerList;
	List<Protein> proteinList;
	List<RNA> rnaList;
	
	public ModelWrapper(Model model){
		this.model = model;
		this.annotation = model.getAnnotation();
		this.id = model.getId();
//		this.eventList = model.getListOfEvents().getEvent();
//		this.functionDefinitionList = model.getListOfFunctionDefinitions().getFunctionDefinition();
//		this.parameterList = model.getListOfParameters().getParameter();
//		this.ruleList = model.getListOfRules().getAlgebraicRuleOrAssignmentRuleOrRateRule();
//		this.unitDefinitionList = model.getListOfUnitDefinitions().getUnitDefinition();
//		this.metaid = model.getMetaid();
//		this.name = model.getName();
//		this.notes = model.getNotes();
		
		this.setListOfAntisenseRNAs(annotation.getExtension().getListOfAntisenseRNAs());
		this.setListOfBlockDiagrams(annotation.getExtension().getListOfBlockDiagrams());
		this.setListOfCompartmentAliases(annotation.getExtension().getListOfCompartmentAliases());
		this.setListOfComplexSpeciesAliases(annotation.getExtension().getListOfComplexSpeciesAliases());
		this.setListOfGenes(annotation.getExtension().getListOfGenes());
		this.setListOfGroups(annotation.getExtension().getListOfGroups());
		this.setListOfIncludedSpecies(annotation.getExtension().getListOfIncludedSpecies());
		this.setListOfLayers(annotation.getExtension().getListOfLayers());
		this.setListOfProteins(annotation.getExtension().getListOfProteins());
		this.setListOfRNAs(annotation.getExtension().getListOfRNAs());
		this.setModelDisplay(annotation.getExtension().getModelDisplay());
		this.setModelVersion(annotation.getExtension().getModelVersion());
		this.cWrapperList = createCompartmentWrapperList(model.getListOfCompartments().getCompartment());
		this.sWrapperList = createSpeciesWrapperList(model.getListOfSpecies().getSpecies());
		this.rWrapperList = createReactionWrapperList(model.getListOfReactions().getReaction());
		this.sAliasWrapperList = createSpeciesAliasWrapperList(annotation.getExtension().getListOfSpeciesAliases().getSpeciesAlias());
		this.cAliasWrapperList = createCompartmentAliasWrapperList(annotation.getExtension().getListOfCompartmentAliases().getCompartmentAlias());
	}

	/**
	 * 
	 * @return
	 * short
	 * TODO
	 */
	public short getSizeX(){
		return annotation.getExtension().getModelDisplay().getSizeX();
	}
	
	/**
	 * 
	 * @return
	 * short
	 * TODO
	 */
	public short getSizeY(){
		return annotation.getExtension().getModelDisplay().getSizeY();
	}
	
	/**
	 * 
	 * @param cList
	 * @return
	 * List<CompartmentWrapper>
	 * TODO
	 */
	public List<CompartmentAliasWrapper> createCompartmentAliasWrapperList(List<CompartmentAlias> caList){
		List<CompartmentAliasWrapper> cawList = new ArrayList<CompartmentAliasWrapper>(caList.size());
		for(CompartmentAlias ca : caList)
			cawList.add(new CompartmentAliasWrapper(ca, this));
		
		return cawList;
	}
	
	/**
	 * 
	 * @return
	 * List<CompartmentWrapper>
	 * TODO
	 */
	public List<CompartmentAliasWrapper> getListOfCompartmentAliasWrapper(){
		return cAliasWrapperList;
	}
	
	/**
	 * 
	 * @param cList
	 * @return
	 * List<CompartmentWrapper>
	 * TODO
	 */
	public List<CompartmentWrapper> createCompartmentWrapperList(List<Compartment> cList){
		List<CompartmentWrapper> cwList = new ArrayList<CompartmentWrapper>(cList.size());
		for(Compartment c : cList)
			cwList.add(new CompartmentWrapper(c, this));
		
		return cwList;
	}
	
	/**
	 * 
	 * @return
	 * List<CompartmentWrapper>
	 * TODO
	 */
	public List<CompartmentWrapper> getListOfCompartmentWrapper(){
		return cWrapperList;
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 * CompartmentWrapper
	 * TODO
	 */
	public CompartmentWrapper getCompartmentWrapperById(String id){
		for(CompartmentWrapper cw : cWrapperList)
			if(cw.getId().equals(id))
				return cw;
		
		return null;
	}
	
	/**
	 * 
	 * @param rList
	 * @return
	 * List<ReactionWrapper>
	 * TODO
	 */
	public List<ReactionWrapper> createReactionWrapperList(List<Reaction> rList){
		List<ReactionWrapper> rwList = new ArrayList<ReactionWrapper>(rList.size());
		for(Reaction r : rList)
			rwList.add(new ReactionWrapper(r, this));
		
		return rwList;
	}

	/**
	 * 
	 * @return
	 * List<ReactionWrapper>
	 * TODO
	 */
	public List<ReactionWrapper> getListOfReactionWrapper(){
		return rWrapperList;
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 * ReactionWrapper
	 * TODO
	 */
	public ReactionWrapper getReactionWrapperById(String id){
		for(ReactionWrapper rw : rWrapperList)
			if(rw.getId().equals(id))
				return rw;
		
		return null;
	}
	
	/**
	 * 
	 * @param sList
	 * @return
	 * List<SpeciesWrapper>
	 * TODO
	 */
	public List<SpeciesWrapper> createSpeciesWrapperList(List<Species> sList){
		List<SpeciesWrapper> swList = new ArrayList<SpeciesWrapper>(sList.size());
		for(Species s : sList)
			swList.add(new SpeciesWrapper(s, this));
		
		return swList;
	}

	public List<SpeciesWrapper> getListOfSpeciesWrapper(){
		return sWrapperList;
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 * SpeciesWrapper
	 * TODO
	 */
	public SpeciesWrapper getSpeciesWrapperById(String id){
		for(SpeciesWrapper sw : sWrapperList)
			if(sw.getId().equals(id))
				return sw;
		
		return null;
	}
	
	
	/**
	 * 
	 * @param saList
	 * @return
	 * List<SpeciesAliasWrapper>
	 * TODO
	 */
	public List<SpeciesAliasWrapper> createSpeciesAliasWrapperList(List<SpeciesAlias> saList){
		List<SpeciesAliasWrapper> sawList = new ArrayList<SpeciesAliasWrapper>(saList.size());
		for(SpeciesAlias sa : saList)
			sawList.add(new SpeciesAliasWrapper(sa, this));
		
		return sawList;
	}
	
	/**
	 * 
	 * @return
	 * List<SpeciesAliasWrapper>
	 * TODO
	 */
	public List<SpeciesAliasWrapper> getListOfSpeciesAliasWrapper(){
		return sAliasWrapperList;
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 * SpeciesAliasWrapper
	 * TODO
	 */
	public SpeciesAliasWrapper getSpeciesAliasWrapperById(String id){
		for(SpeciesAliasWrapper saw : sAliasWrapperList)
			if(saw.getId().equals(id))
				return saw;
		
		return null;
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 * CompartmentAlias
	 * TODO
	 */
	public CompartmentAlias getCompartmentAliasById(String id){
		for(CompartmentAlias ca : cAliasList)
			if(ca.getId().equals(id))
				return ca;
		
		return null;
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 * ComplexSpeciesAlias
	 * TODO
	 */
	public ComplexSpeciesAlias getComplexSpeciesAliasById(String id){
		for(ComplexSpeciesAlias csa : complexSAliasList)
			if(csa.getId().equals(id))
				return csa;
		
		return null;
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

//    /**
//     * 
//     * @return
//     * ListOfIncludedSpecies
//     * TODO
//     */
//    public  List<Species> getListOfIncludedSpecies() {
//        return annotation.getExtension().getListOfIncludedSpecies().getSpecies();
//    }

    /**
     * 
     * @param value
     * void
     * TODO
     */
    public void setListOfIncludedSpecies(ListOfIncludedSpecies value) {
//    	includedSpeciesList = value.getSpecies();
    	annotation.getExtension().setListOfIncludedSpecies(value);
    }

//    /**
//     * 
//     * @param species
//     * void
//     * TODO
//     */
//    public void addIncludedSpecies(Species species){
//    	annotation.getExtension().getListOfIncludedSpecies().getSpecies().add(species);
//    }
//    
//    /**
//     * 
//     * @param species
//     * void
//     * TODO
//     */
//    public void removeIncludedSpecies(Species species){
//    	annotation.getExtension().getListOfIncludedSpecies().getSpecies().remove(species);
//    }
//    
    /**
     * 
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
    	cAliasList = value.getCompartmentAlias();
    	annotation.getExtension().setListOfCompartmentAliases(value);
    }

    /**
     * 
     * @param compartmentAlias
     * void
     * TODO
     */
    public void addCompartmentAlias(CompartmentAlias compartmentAlias){
    	annotation.getExtension().getListOfCompartmentAliases().getCompartmentAlias().add(compartmentAlias);
    }
    
    /**
     * 
     * @param compartmentAlias
     * void
     * TODO
     */
    public void removeCompartmentAlias(CompartmentAlias compartmentAlias){
    	annotation.getExtension().getListOfCompartmentAliases().getCompartmentAlias().remove(compartmentAlias);
    }    
    
	/**
	 * @return
	 * CompartmentAliasWrapper
	 * TODO
	 */
	public CompartmentAliasWrapper getCompartmentAliasWrapperByCompartmentId(String id) {
		for(CompartmentAliasWrapper caw: cAliasWrapperList){
			if(caw.getCompartment().equals(id))
				return caw;		
		}
		
		return null;
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
    	complexSAliasList = value.getComplexSpeciesAlias();
    	annotation.getExtension().setListOfComplexSpeciesAliases(value);
    }

    /**
     * 
     * @param value
     * void
     * TODO
     */
    public void addComplexSpeciesAlias(ComplexSpeciesAlias complexSpeciesAlias) {
    	annotation.getExtension().getListOfComplexSpeciesAliases().getComplexSpeciesAlias().add(complexSpeciesAlias);
    }
    
    /**
     * 
     * @param value
     * void
     * TODO
     */
    public void removeComplexSpeciesAlias(ComplexSpeciesAlias complexSpeciesAlias) {
    	annotation.getExtension().getListOfComplexSpeciesAliases().getComplexSpeciesAlias().remove(complexSpeciesAlias);
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
     * 
     * @param value
     * void
     * TODO
     */
    public void setListOfSpeciesAliases(ListOfSpeciesAliases value) {
    	sAliasWrapperList = createSpeciesAliasWrapperList(value.getSpeciesAlias());
    	annotation.getExtension().setListOfSpeciesAliases(value);	
    }

    /**
     * 
     * @param speciesAlias
     * void
     * TODO
     */
    public void addSpeciesAlias(SpeciesAlias speciesAlias){
    	 annotation.getExtension().getListOfSpeciesAliases().getSpeciesAlias().add(speciesAlias);
    }
    
    /**
     * 
     * @param speciesAlias
     * void
     * TODO
     */
    public void removeSpeciesAlias(SpeciesAlias speciesAlias){
    	 annotation.getExtension().getListOfSpeciesAliases().getSpeciesAlias().remove(speciesAlias);
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
     * @param group
     * void
     * TODO
     */
    public void addGroup(Group group){
    	annotation.getExtension().getListOfGroups().getGroup().add(group);
    }
    
    /**
     * 
     * @param group
     * void
     * TODO
     */
    public void removeGroup(Group group){
    	annotation.getExtension().getListOfGroups().getGroup().remove(group);
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
     * @param protein
     * void
     * TODO
     */
    public void addProtein(Protein protein){
    	annotation.getExtension().getListOfProteins().getProtein().add(protein);
    }

    /**
     * 
     * @param protein
     * void
     * TODO
     */
    public void removeProtein(Protein protein){
    	annotation.getExtension().getListOfProteins().getProtein().remove(protein);
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
     * @param gene
     * void
     * TODO
     */
    public void addGene(Gene gene){
    	annotation.getExtension().getListOfGenes().getGene().add(gene);
    }
    
    /**
     * 
     * @param gene
     * void
     * TODO
     */
    public void removeGene(Gene gene){
    	annotation.getExtension().getListOfGenes().getGene().remove(gene);
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
     * @param rna
     * void
     * TODO
     */
    public void addRNA(RNA rna){
    	annotation.getExtension().getListOfRNAs().getRNA().add(rna);
    }
    
    /**
     * 
     * @param rna
     * void
     * TODO
     */
    public void removeRNA(RNA rna){
    	annotation.getExtension().getListOfRNAs().getRNA().remove(rna);
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
    private void setListOfAntisenseRNAs(ListOfAntisenseRNAs value) {
    	annotation.getExtension().setListOfAntisenseRNAs(value);
    }

    /**
     * 
     * @param antisenseRNA
     * void
     * TODO
     */
    public void addAntisenseRNA(AntisenseRNA antisenseRNA){
    	annotation.getExtension().getListOfAntisenseRNAs().getAntisenseRNA().add(antisenseRNA);
    }
    
    /**
     * 
     * @param antisenseRNA
     * void
     * TODO
     */
    public void removeAntisenseRNA(AntisenseRNA antisenseRNA){
    	annotation.getExtension().getListOfAntisenseRNAs().getAntisenseRNA().remove(antisenseRNA);
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
     * @param layer
     * void
     * TODO
     */
    public void addLayer(Layer layer){
    	 annotation.getExtension().getListOfLayers().getLayer().add(layer);
    }
    
    /**
     * 
     * @param layer
     * void
     * TODO
     */
    public void removeLayer(Layer layer){
    	 annotation.getExtension().getListOfLayers().getLayer().remove(layer);
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

    /**
     * 
     * @param blockDiagram
     * void
     * TODO
     */
    public void addBlockDiagram(BlockDiagram blockDiagram){
    	annotation.getExtension().getListOfBlockDiagrams().getBlockDiagram().add(blockDiagram);
    }
    
    /**
     * 
     * @param blockDiagram
     * void
     * TODO
     */
    public void removeBlockDiagram(BlockDiagram blockDiagram){
    	annotation.getExtension().getListOfBlockDiagrams().getBlockDiagram().remove(blockDiagram);
    }
}
