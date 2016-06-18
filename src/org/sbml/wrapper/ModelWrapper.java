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
import org.sbml.sbml.level2.version4.Model;
import org.sbml.sbml.level2.version4.Reaction;
import org.sbml.sbml.level2.version4.Species;

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
	
	List<AntisenseRNA> antiSenseRNAList;
	List<BlockDiagram> blockDiagramList;
	List<CompartmentAlias> cAliasList;
	List<SpeciesAlias> sAliasList;
	List<ComplexSpeciesAlias> complexSpeciesAliasList;
	List<Gene> geneList;
	List<Group> groupList;
	List<Species> includedSpeciesList;
	List<Layer> layerList;
	List<Protein> proteinList;
	List<RNA> rnaList;
	double width;
	double height;
	
	ModelDisplay modelDisplay;
	int version;
	
	public ModelWrapper(Model model){
		this.model = model;
		this.annotation = model.getAnnotation();
		this.id = model.getId();
		this.name = model.getName();
		this.notes = model.getNotes();
		
		if(annotation.getExtension().getListOfAntisenseRNAs() != null)
			this.antiSenseRNAList = annotation.getExtension().getListOfAntisenseRNAs().getAntisenseRNA();
		
		if(annotation.getExtension().getListOfBlockDiagrams() != null)
			this.blockDiagramList = annotation.getExtension().getListOfBlockDiagrams().getBlockDiagram();
		
		if(annotation.getExtension().getListOfCompartmentAliases() != null)
			this.cAliasList = annotation.getExtension().getListOfCompartmentAliases().getCompartmentAlias();
		
		if(annotation.getExtension().getListOfComplexSpeciesAliases() != null)
			this.complexSpeciesAliasList = annotation.getExtension().getListOfComplexSpeciesAliases().getComplexSpeciesAlias();
		
		if(annotation.getExtension().getListOfGenes() != null)
			this.geneList = annotation.getExtension().getListOfGenes().getGene();
		
		if(annotation.getExtension().getListOfGroups() != null)
			this.groupList = annotation.getExtension().getListOfGroups().getGroup();

		if(annotation.getExtension().getListOfLayers() != null)
			this.layerList = annotation.getExtension().getListOfLayers().getLayer();

//		if(annotation.getExtension().getListOfIncludedSpecies()!= null)
//			this.includedSpeciesList = createIncludedSpeciesList(annotation.getExtension().getListOfIncludedSpecies().getSpecies());
	
		if(annotation.getExtension().getListOfProteins() != null)
			this.proteinList = annotation.getExtension().getListOfProteins().getProtein();
		
		if(annotation.getExtension().getListOfRNAs() != null)
			this.rnaList = annotation.getExtension().getListOfRNAs().getRNA();
		
		if(annotation.getExtension().getListOfSpeciesAliases() != null)
			this.sAliasList = annotation.getExtension().getListOfSpeciesAliases().getSpeciesAlias();
		
		this.cWrapperList = createCompartmentWrapperList(model.getListOfCompartments().getCompartment());
		this.sWrapperList = createSpeciesWrapperList(model.getListOfSpecies().getSpecies());
		this.rWrapperList = createReactionWrapperList(model.getListOfReactions().getReaction());
		this.sAliasWrapperList = createSpeciesAliasWrapperList(sAliasList);
		this.cAliasWrapperList = createCompartmentAliasWrapperList(annotation.getExtension().getListOfCompartmentAliases().getCompartmentAlias());
	
		modelDisplay = annotation.getExtension().getModelDisplay();
		version = annotation.getExtension().getModelVersion().intValue();
		
		this.width = modelDisplay.getSizeX();
		this.height = modelDisplay.getSizeY();
		
	
	}
//
//	/**
//	 * @param species
//	 * @return
//	 * List<Species>
//	 * TODO
//	 */
//	private List<Species> createIncludedSpeciesList(List<org.sbml._2001.ns.celldesigner.Species> speciesList) {
//		List<Species> includedSpeciesList = new ArrayList<Species>(speciesList.size());
//		
//		for(org.sbml._2001.ns.celldesigner.Species s : speciesList){
//			Species species = new Species();
//			
//			//includedSpeciesList
//		}
//		
//		return includedSpeciesList;
//	}

	/**
	 * 
	 * @return
	 * double
	 * TODO
	 */
	public double getW(){
		return width;
	}
	
	/**
	 * 
	 * @param x
	 * void
	 * TODO
	 */
	public void setW(double w){
		this.width = w;
		annotation.getExtension().getModelDisplay().setSizeX((short) width);
	}
	
	/**
	 * 
	 * @return
	 * double
	 * TODO
	 */
	public double getH(){
		return height;
	}
	
	/**
	 * 
	 * @param y
	 * void
	 * TODO
	 */
	public void setSizeY(double h){
		this.height = h;
		annotation.getExtension().getModelDisplay().setSizeY((short) h);
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

	/**
	 * 
	 * @return
	 * List<SpeciesWrapper>
	 * TODO
	 */
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
		for(ComplexSpeciesAlias csa : complexSpeciesAliasList)
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
	public int getModelVersion() {
        return version;
    }

	/**
	 * 
	 * @param version
	 * void
	 * TODO
	 */
    public void setModelVersion(int version) {
    	annotation.getExtension().setModelVersion(new BigDecimal(version));
    	this.version = version;
    }

    /**
     * 
     * @return
     * ModelDisplay
     * TODO
     */
    public ModelDisplay getModelDisplay() {
        return modelDisplay;
    }

    /**
     * 
     * @param display
     * void
     * TODO
     */
    public void setModelDisplay(ModelDisplay display) {
    	annotation.getExtension().setModelDisplay(display);
    	this.modelDisplay = display;
    }

    /**
     * 
     * @return
     * ListOfIncludedSpecies
     * TODO
     */
    public  List<Species> getListOfIncludedSpecies() {
        return includedSpeciesList;
    }

    /**
     * 
     * @param value
     * void
     * TODO
     */
    public void setListOfIncludedSpecies(ListOfIncludedSpecies value) {
    	//includedSpeciesList = value.getSpecies();
    	annotation.getExtension().setListOfIncludedSpecies(value);
    }

    /**
     * 
     * @param species
     * void
     * TODO
     */
    public void addIncludedSpecies(Species species){
    	includedSpeciesList.add(species);
    }
    
    /**
     * 
     * @param species
     * void
     * TODO
     */
    public void removeIncludedSpecies(Species species){
    	includedSpeciesList.remove(species);
    }
    
    /**
     * 
     * @return
     * ListOfCompartmentAliases
     * TODO
     */
    public List<CompartmentAlias> getListOfCompartmentAliases() {
        return cAliasList;
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
    	cAliasList.add(compartmentAlias);
    }
    
    /**
     * 
     * @param compartmentAlias
     * void
     * TODO
     */
    public void removeCompartmentAlias(CompartmentAlias compartmentAlias){
    	cAliasList.remove(compartmentAlias);
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
        return complexSpeciesAliasList;
    }

    /**
     * 
     * @param value
     * void
     * TODO
     */
    public void setListOfComplexSpeciesAliases(ListOfComplexSpeciesAliases value) {
    	complexSpeciesAliasList = value.getComplexSpeciesAlias();
    	annotation.getExtension().setListOfComplexSpeciesAliases(value);
    }

    /**
     * 
     * @param complexSpeciesAlias
     * void
     * TODO
     */
    public void addComplexSpeciesAlias(ComplexSpeciesAlias complexSpeciesAlias) {
    	complexSpeciesAliasList.add(complexSpeciesAlias);
    }
    
    /**
     * 
     * @param complexSpeciesAlias
     * void
     * TODO
     */
    public void removeComplexSpeciesAlias(ComplexSpeciesAlias complexSpeciesAlias) {
    	complexSpeciesAliasList.remove(complexSpeciesAlias);
    }
    
    /**
     * 
     * @return
     * ListOfSpeciesAliases
     * TODO
     */
    public List<SpeciesAlias> getListOfSpeciesAliases() {
        return sAliasList;
    }

    /**
     * 
     * @param value
     * void
     * TODO
     */
    public void setListOfSpeciesAliases(ListOfSpeciesAliases value) {
    	sAliasList = value.getSpeciesAlias();
    	annotation.getExtension().setListOfSpeciesAliases(value);
    	sAliasWrapperList = createSpeciesAliasWrapperList(sAliasList);
    }

    /**
     * 
     * @param speciesAlias
     * void
     * TODO
     */
    public void addSpeciesAlias(SpeciesAlias speciesAlias){
    	sAliasList.add(speciesAlias);
    }
    
    /**
     * 
     * @param speciesAlias
     * void
     * TODO
     */
    public void removeSpeciesAlias(SpeciesAlias speciesAlias){
    	 sAliasList.remove(speciesAlias);
    }
    
    /**
     * 
     * @return
     * ListOfGroups
     * TODO
     */
    public List<Group> getListOfGroups() {
        return groupList;
    }

    /**
     * 
     * @param value
     * void
     * TODO
     */
    public void setListOfGroups(ListOfGroups value) {
    	annotation.getExtension().setListOfGroups(value);
    	groupList = value.getGroup();
    }

    /**
     * 
     * @param group
     * void
     * TODO
     */
    public void addGroup(Group group){
    	groupList.add(group);
    }
    
    /**
     * 
     * @param group
     * void
     * TODO
     */
    public void removeGroup(Group group){
    	groupList.remove(group);
    }
    
    
    /**
     * 
     * @return
     * ListOfProteins
     * TODO
     */
    public List<Protein> getListOfProteins() {
        return proteinList;
    }

    /**
     * 
     * @param value
     * void
     * TODO
     */
    public void setListOfProteins(ListOfProteins value) {
    	annotation.getExtension().setListOfProteins(value);
    	proteinList = value.getProtein();
    }

    /**
     * 
     * @param protein
     * void
     * TODO
     */
    public void addProtein(Protein protein){
    	proteinList.add(protein);
    }

    /**
     * 
     * @param protein
     * void
     * TODO
     */
    public void removeProtein(Protein protein){
    	proteinList.remove(protein);
    }
    

    /**
     * 
     * @return
     * ListOfGenes
     * TODO
     */
    public List<Gene> getListOfGenes() {
        return geneList;
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
    	geneList.add(gene);
    }
    
    /**
     * 
     * @param gene
     * void
     * TODO
     */
    public void removeGene(Gene gene){
    	geneList.remove(gene);
    }
    
    /**
     * 
     * @return
     * ListOfRNAs
     * TODO
     */
    public List<RNA> getListOfRNAs() {
        return rnaList;
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
    	rnaList.add(rna);
    }
    
    /**
     * 
     * @param rna
     * void
     * TODO
     */
    public void removeRNA(RNA rna){
    	rnaList.remove(rna);
    }
    
    /**
     * 
     * @return
     * ListOfAntisenseRNAs
     * TODO
     */
    public List<AntisenseRNA> getListOfAntisenseRNAs() {
        return antiSenseRNAList;
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
     * @param antisenseRNA
     * void
     * TODO
     */
    public void addAntisenseRNA(AntisenseRNA antisenseRNA){
    	antiSenseRNAList.add(antisenseRNA);
    }
    
    /**
     * 
     * @param antisenseRNA
     * void
     * TODO
     */
    public void removeAntisenseRNA(AntisenseRNA antisenseRNA){
    	antiSenseRNAList.remove(antisenseRNA);
    }
        
    /**
     * 
     * @return
     * ListOfLayers
     * TODO
     */
    public List<Layer> getListOfLayers() {
        return layerList;
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
    	layerList.add(layer);
    }
    
    /**
     * 
     * @param layer
     * void
     * TODO
     */
    public void removeLayer(Layer layer){
    	layerList.remove(layer);
    }
    
    
    /**
     * 
     * @return
     * ListOfBlockDiagrams
     * TODO
     */
	public List<BlockDiagram> getListOfBlockDiagrams() {
        return blockDiagramList;
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
    	blockDiagramList.add(blockDiagram);
    }
    
    /**
     * 
     * @param blockDiagram
     * void
     * TODO
     */
    public void removeBlockDiagram(BlockDiagram blockDiagram){
    	blockDiagramList.remove(blockDiagram);
    }
    
	/**
	 * 
	 * @param cawList
	 * @return
	 * List<CompartmentAliasWrapper>
	 * TODO
	 */
	public static List<CompartmentAliasWrapper> reorderCompartmentAccordingToPosition(List<CompartmentAliasWrapper> cawList){
		for(int i = 1; i < cawList.size(); i++){
			CompartmentAliasWrapper c = cawList.get(i);
			if(c.getCompartmentWrapperAliased().isSetOutside()){
				CompartmentWrapper cw = c.getCompartmentWrapperAliased().getOutsideInstance();
				CompartmentAliasWrapper outside = cw.getAliasWrapper();
				int outIndex = cawList.indexOf(outside);
				if(outIndex > i){
					cawList.remove(c);
					cawList.add(outIndex , c);
				}
			}
		}
		
		return cawList;
	}
}
