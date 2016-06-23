/*
 * 
 */
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

// TODO: Auto-generated Javadoc
/**
 * The Class ModelWrapper.
 *
 * @author Kaito Ii
 * 
 * Date Created: May 24, 2016
 */

public class ModelWrapper extends Model {

  /** The model. */
  private	Model model;
  
  /** The c wrapper list. */
  private	List<CompartmentWrapper> cWrapperList;
  
  /** The r wrapper list. */
  private	List<ReactionWrapper> rWrapperList;
  
  /** The s wrapper list. */
  private	List<SpeciesWrapper> sWrapperList;
  
  /** The s alias wrapper list. */
  private	List<SpeciesAliasWrapper> sAliasWrapperList;
  
  /** The c alias wrapper list. */
  private	List<CompartmentAliasWrapper>  cAliasWrapperList;
  
  /** The complex wrapper list. */
  private	List<ComplexSpeciesAliasWrapper> complexWrapperList;

  /** The anti sense RNA list. */
  private	List<AntisenseRNA> antiSenseRNAList;
  
  /** The block diagram list. */
  private	List<BlockDiagram> blockDiagramList;
  
  /** The c alias list. */
  private	List<CompartmentAlias> cAliasList;
  
  /** The s alias list. */
  private	List<SpeciesAlias> sAliasList;
  
  /** The complex species alias list. */
  private	List<ComplexSpeciesAlias> complexSpeciesAliasList;
  
  /** The gene list. */
  private	List<Gene> geneList;
  
  /** The group list. */
  private	List<Group> groupList;
  
  /** The included species list. */
  private	List<Species> includedSpeciesList;
  
  /** The layer list. */
  private	List<Layer> layerList;
  
  /** The protein list. */
  private	List<Protein> proteinList;
  
  /** The rna list. */
  private	List<RNA> rnaList;
  
  /** The width. */
  private	double width;
  
  /** The height. */
  private	double height;

  /** The model display. */
  private	ModelDisplay modelDisplay;
  
  /** The version. */
  private	int version;

	/**
	 * Instantiates a new model wrapper.
	 *
	 * @param model the model
	 */
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
		this.cAliasWrapperList = createCompartmentAliasWrapperList(annotation.getExtension().getListOfCompartmentAliases().getCompartmentAlias());
		this.sWrapperList = createSpeciesWrapperList(model.getListOfSpecies().getSpecies());
		this.rWrapperList = createReactionWrapperList(model.getListOfReactions().getReaction());
		if(annotation.getExtension().getListOfComplexSpeciesAliases() != null) {
			this.complexSpeciesAliasList = annotation.getExtension().getListOfComplexSpeciesAliases().getComplexSpeciesAlias();
			this.complexWrapperList = createComplexWrapperList(complexSpeciesAliasList);
		}

		this.sAliasWrapperList = createSpeciesAliasWrapperList(sAliasList);


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
 * Gets the w.
 *
 * @return double
 * TODO
 */
	public double getW(){
		return width;
	}

	/**
	 * Sets the w.
	 *
	 * @param w the new w
	 */
	public void setW(double w){
		this.width = w;
		annotation.getExtension().getModelDisplay().setSizeX((short) width);
	}

	/**
	 * Gets the h.
	 *
	 * @return double
	 * TODO
	 */
	public double getH(){
		return height;
	}

	/**
	 * Sets the size Y.
	 *
	 * @param h the new size Y
	 */
	public void setSizeY(double h){
		this.height = h;
		annotation.getExtension().getModelDisplay().setSizeY((short) h);
	}


	/**
	 * Creates the compartment alias wrapper list.
	 *
	 * @param caList the ca list
	 * @return List<CompartmentWrapper>
	 * TODO
	 */
	public List<CompartmentAliasWrapper> createCompartmentAliasWrapperList(List<CompartmentAlias> caList){
		List<CompartmentAliasWrapper> cawList = new ArrayList<CompartmentAliasWrapper>(caList.size());
		for(CompartmentAlias ca : caList)
			cawList.add(new CompartmentAliasWrapper(ca, this));

		return cawList;
	}

	/**
	 * Gets the list of compartment alias wrapper.
	 *
	 * @return List<CompartmentWrapper>
	 * TODO
	 */
	public List<CompartmentAliasWrapper> getListOfCompartmentAliasWrapper(){
		return cAliasWrapperList;
	}

	/**
	 * Creates the compartment wrapper list.
	 *
	 * @param cList the c list
	 * @return List<CompartmentWrapper>
	 * TODO
	 */
	public List<CompartmentWrapper> createCompartmentWrapperList(List<Compartment> cList){
		List<CompartmentWrapper> cwList = new ArrayList<CompartmentWrapper>(cList.size());
		for(Compartment c : cList)
			cwList.add(new CompartmentWrapper(c, this));

		return cwList;
	}

	/**
	 * Gets the list of compartment wrapper.
	 *
	 * @return List<CompartmentWrapper>
	 * TODO
	 */
	public List<CompartmentWrapper> getListOfCompartmentWrapper(){
		return cWrapperList;
	}

	/**
	 * Gets the compartment wrapper by id.
	 *
	 * @param id the id
	 * @return CompartmentWrapper
	 * TODO
	 */
	public CompartmentWrapper getCompartmentWrapperById(String id){
		for(CompartmentWrapper cw : cWrapperList)
			if(cw.getId().equals(id))
				return cw;

		return null;
	}

	/**
	 * Creates the reaction wrapper list.
	 *
	 * @param rList the r list
	 * @return List<ReactionWrapper>
	 * TODO
	 */
	public List<ReactionWrapper> createReactionWrapperList(List<Reaction> rList){
		List<ReactionWrapper> rwList = new ArrayList<ReactionWrapper>(rList.size());
		for(Reaction r : rList)
			rwList.add(new ReactionWrapper(r, this));

		return rwList;
	}

	/**
	 * Gets the list of reaction wrapper.
	 *
	 * @return List<ReactionWrapper>
	 * TODO
	 */
	public List<ReactionWrapper> getListOfReactionWrapper(){
		return rWrapperList;
	}

	/**
	 * Gets the reaction wrapper by id.
	 *
	 * @param id the id
	 * @return ReactionWrapper
	 * TODO
	 */
	public ReactionWrapper getReactionWrapperById(String id){
		for(ReactionWrapper rw : rWrapperList)
			if(rw.getId().equals(id))
				return rw;

		return null;
	}

	/**
	 * Creates the species wrapper list.
	 *
	 * @param sList the s list
	 * @return List<SpeciesWrapper>
	 * TODO
	 */
	public List<SpeciesWrapper> createSpeciesWrapperList(List<Species> sList){
		List<SpeciesWrapper> swList = new ArrayList<SpeciesWrapper>(sList.size());
		for(Species s : sList)
			swList.add(new SpeciesWrapper(s, this));

		return swList;
	}

	/**
	 * Gets the list of species wrapper.
	 *
	 * @return List<SpeciesWrapper>
	 * TODO
	 */
	public List<SpeciesWrapper> getListOfSpeciesWrapper(){
		return sWrapperList;
	}

	/**
	 * Gets the species wrapper by id.
	 *
	 * @param id the id
	 * @return SpeciesWrapper
	 * TODO
	 */
	public SpeciesWrapper getSpeciesWrapperById(String id){
		for(SpeciesWrapper sw : sWrapperList)
			if(sw.getId().equals(id))
				return sw;

		return null;
	}


	/**
	 * Creates the species alias wrapper list.
	 *
	 * @param saList the sa list
	 * @return List<SpeciesAliasWrapper>
	 * TODO
	 */
	public List<SpeciesAliasWrapper> createSpeciesAliasWrapperList(List<SpeciesAlias> saList){
		List<SpeciesAliasWrapper> sawList = new ArrayList<SpeciesAliasWrapper>(saList.size());
		for(SpeciesAlias sa : saList)
			sawList.add(new SpeciesAliasWrapper(sa, this));

		return sawList;
	}

	/**
	 * Gets the list of species alias wrapper.
	 *
	 * @return List<SpeciesAliasWrapper>
	 * TODO
	 */
	public List<SpeciesAliasWrapper> getListOfSpeciesAliasWrapper(){
		return sAliasWrapperList;
	}

	/**
	 * Gets the species alias wrapper by id.
	 *
	 * @param id the id
	 * @return SpeciesAliasWrapper
	 * TODO
	 */
	public SpeciesAliasWrapper getSpeciesAliasWrapperById(String id){
		for(SpeciesAliasWrapper saw : sAliasWrapperList)
			if(saw.getId().equals(id))
				return saw;

		return null;
	}

	/**
	 * Creates the complex wrapper list.
	 *
	 * @param complexSpeciesAliasList the complex species alias list
	 * @return List<ComplexSpeciesAliasWrapper>
	 * TODO
	 */
	private List<ComplexSpeciesAliasWrapper> createComplexWrapperList(List<ComplexSpeciesAlias> complexSpeciesAliasList) {
		List<ComplexSpeciesAliasWrapper> csawList = new ArrayList<ComplexSpeciesAliasWrapper>(complexSpeciesAliasList.size());

		for(ComplexSpeciesAlias csa : complexSpeciesAliasList)
			csawList.add(new ComplexSpeciesAliasWrapper(csa, this));

		return csawList;
	}

	/**
	 * Gets the list of complex species alias wrapper.
	 *
	 * @return List<ComplexSpeciesAliasWrapper>
	 * TODO
	 */
	public List<ComplexSpeciesAliasWrapper> getListOfComplexSpeciesAliasWrapper(){
		return complexWrapperList;
	}

	/**
	 * Gets the complex alias wrapper by id.
	 *
	 * @param id the id
	 * @return ComplexSpeciesAliasWrapper
	 * TODO
	 */
	public ComplexSpeciesAliasWrapper getComplexAliasWrapperById(String id){
		for(ComplexSpeciesAliasWrapper csaw: complexWrapperList)
			if(csaw.getId().equals(id))
				return csaw;

		return null;
	}

	/**
	 * Gets the compartment alias by id.
	 *
	 * @param id the id
	 * @return CompartmentAlias
	 * TODO
	 */
	public CompartmentAlias getCompartmentAliasById(String id){
		for(CompartmentAlias ca : cAliasList)
			if(ca.getId().equals(id))
				return ca;

		return null;
	}

	/**
	 * Gets the complex species alias by id.
	 *
	 * @param id the id
	 * @return ComplexSpeciesAlias
	 * TODO
	 */
	public ComplexSpeciesAlias getComplexSpeciesAliasById(String id){
		for(ComplexSpeciesAlias csa : complexSpeciesAliasList)
			if(csa.getId().equals(id))
				return csa;

		return null;
	}

	/**
	 * Gets the model version.
	 *
	 * @return BigDecimal
	 * TODO
	 */
	public int getModelVersion() {
        return version;
    }

	/**
	 * Sets the model version.
	 *
	 * @param version void
	 * TODO
	 */
    public void setModelVersion(int version) {
    	annotation.getExtension().setModelVersion(new BigDecimal(version));
    	this.version = version;
    }

    /**
     * Gets the model display.
     *
     * @return ModelDisplay
     * TODO
     */
    public ModelDisplay getModelDisplay() {
        return modelDisplay;
    }

    /**
     * Sets the model display.
     *
     * @param display void
     * TODO
     */
    public void setModelDisplay(ModelDisplay display) {
    	annotation.getExtension().setModelDisplay(display);
    	this.modelDisplay = display;
    }

    /**
     * Gets the list of included species.
     *
     * @return ListOfIncludedSpecies
     * TODO
     */
    public  List<Species> getListOfIncludedSpecies() {
        return includedSpeciesList;
    }

    /**
     * Sets the list of included species.
     *
     * @param value void
     * TODO
     */
    public void setListOfIncludedSpecies(ListOfIncludedSpecies value) {
    	//includedSpeciesList = value.getSpecies();
    	annotation.getExtension().setListOfIncludedSpecies(value);
    }

    /**
     * Adds the included species.
     *
     * @param species void
     * TODO
     */
    public void addIncludedSpecies(Species species){
    	includedSpeciesList.add(species);
    }

    /**
     * Removes the included species.
     *
     * @param species void
     * TODO
     */
    public void removeIncludedSpecies(Species species){
    	includedSpeciesList.remove(species);
    }

    /**
     * Gets the list of compartment aliases.
     *
     * @return ListOfCompartmentAliases
     * TODO
     */
    public List<CompartmentAlias> getListOfCompartmentAliases() {
        return cAliasList;
    }

    /**
     * Sets the list of compartment aliases.
     *
     * @param value void
     * TODO
     */
    public void setListOfCompartmentAliases(ListOfCompartmentAliases value) {
    	cAliasList = value.getCompartmentAlias();
    	annotation.getExtension().setListOfCompartmentAliases(value);
    }

    /**
     * Adds the compartment alias.
     *
     * @param compartmentAlias void
     * TODO
     */
    public void addCompartmentAlias(CompartmentAlias compartmentAlias){
    	cAliasList.add(compartmentAlias);
    }

    /**
     * Removes the compartment alias.
     *
     * @param compartmentAlias void
     * TODO
     */
    public void removeCompartmentAlias(CompartmentAlias compartmentAlias){
    	cAliasList.remove(compartmentAlias);
    }

	/**
	 * Gets the compartment alias wrapper by compartment id.
	 *
	 * @param id the id
	 * @return CompartmentAliasWrapper
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
     * Gets the list of complex species aliases.
     *
     * @return ListOfComplexSpeciesAliases
     * TODO
     */
    public List<ComplexSpeciesAlias> getListOfComplexSpeciesAliases() {
        return complexSpeciesAliasList;
    }

    /**
     * Sets the list of complex species aliases.
     *
     * @param value void
     * TODO
     */
    public void setListOfComplexSpeciesAliases(ListOfComplexSpeciesAliases value) {
    	complexSpeciesAliasList = value.getComplexSpeciesAlias();
    	annotation.getExtension().setListOfComplexSpeciesAliases(value);
    }

    /**
     * Adds the complex species alias.
     *
     * @param complexSpeciesAlias void
     * TODO
     */
    public void addComplexSpeciesAlias(ComplexSpeciesAlias complexSpeciesAlias) {
    	complexSpeciesAliasList.add(complexSpeciesAlias);
    }

    /**
     * Removes the complex species alias.
     *
     * @param complexSpeciesAlias void
     * TODO
     */
    public void removeComplexSpeciesAlias(ComplexSpeciesAlias complexSpeciesAlias) {
    	complexSpeciesAliasList.remove(complexSpeciesAlias);
    }

    /**
     * Gets the list of species aliases.
     *
     * @return ListOfSpeciesAliases
     * TODO
     */
    public List<SpeciesAlias> getListOfSpeciesAliases() {
        return sAliasList;
    }

    /**
     * Sets the list of species aliases.
     *
     * @param value void
     * TODO
     */
    public void setListOfSpeciesAliases(ListOfSpeciesAliases value) {
    	sAliasList = value.getSpeciesAlias();
    	annotation.getExtension().setListOfSpeciesAliases(value);
    	sAliasWrapperList = createSpeciesAliasWrapperList(sAliasList);
    }

    /**
     * Adds the species alias.
     *
     * @param speciesAlias void
     * TODO
     */
    public void addSpeciesAlias(SpeciesAlias speciesAlias){
    	sAliasList.add(speciesAlias);
    }

    /**
     * Removes the species alias.
     *
     * @param speciesAlias void
     * TODO
     */
    public void removeSpeciesAlias(SpeciesAlias speciesAlias){
    	 sAliasList.remove(speciesAlias);
    }

    /**
     * Gets the list of groups.
     *
     * @return ListOfGroups
     * TODO
     */
    public List<Group> getListOfGroups() {
        return groupList;
    }

    /**
     * Sets the list of groups.
     *
     * @param value void
     * TODO
     */
    public void setListOfGroups(ListOfGroups value) {
    	annotation.getExtension().setListOfGroups(value);
    	groupList = value.getGroup();
    }

    /**
     * Adds the group.
     *
     * @param group void
     * TODO
     */
    public void addGroup(Group group){
    	groupList.add(group);
    }

    /**
     * Removes the group.
     *
     * @param group void
     * TODO
     */
    public void removeGroup(Group group){
    	groupList.remove(group);
    }


    /**
     * Gets the list of proteins.
     *
     * @return ListOfProteins
     * TODO
     */
    public List<Protein> getListOfProteins() {
        return proteinList;
    }

    /**
     * Sets the list of proteins.
     *
     * @param value void
     * TODO
     */
    public void setListOfProteins(ListOfProteins value) {
    	annotation.getExtension().setListOfProteins(value);
    	proteinList = value.getProtein();
    }

    /**
     * Adds the protein.
     *
     * @param protein void
     * TODO
     */
    public void addProtein(Protein protein){
    	proteinList.add(protein);
    }

    /**
     * Removes the protein.
     *
     * @param protein void
     * TODO
     */
    public void removeProtein(Protein protein){
    	proteinList.remove(protein);
    }


    /**
     * Gets the list of genes.
     *
     * @return ListOfGenes
     * TODO
     */
    public List<Gene> getListOfGenes() {
        return geneList;
    }

    /**
     * Sets the list of genes.
     *
     * @param value void
     * TODO
     */
    public void setListOfGenes(ListOfGenes value) {
    	annotation.getExtension().setListOfGenes(value);
    }

    /**
     * Adds the gene.
     *
     * @param gene void
     * TODO
     */
    public void addGene(Gene gene){
    	geneList.add(gene);
    }

    /**
     * Removes the gene.
     *
     * @param gene void
     * TODO
     */
    public void removeGene(Gene gene){
    	geneList.remove(gene);
    }

    /**
     * Gets the list of RN as.
     *
     * @return ListOfRNAs
     * TODO
     */
    public List<RNA> getListOfRNAs() {
        return rnaList;
    }

    /**
     * Sets the list of RN as.
     *
     * @param value void
     * TODO
     */
    public void setListOfRNAs(ListOfRNAs value) {
    	annotation.getExtension().setListOfRNAs(value);
    }

    /**
     * Adds the RNA.
     *
     * @param rna void
     * TODO
     */
    public void addRNA(RNA rna){
    	rnaList.add(rna);
    }

    /**
     * Removes the RNA.
     *
     * @param rna void
     * TODO
     */
    public void removeRNA(RNA rna){
    	rnaList.remove(rna);
    }

    /**
     * Gets the list of antisense RN as.
     *
     * @return ListOfAntisenseRNAs
     * TODO
     */
    public List<AntisenseRNA> getListOfAntisenseRNAs() {
        return antiSenseRNAList;
    }

    /**
     * Sets the list of antisense RN as.
     *
     * @param value void
     * TODO
     */
    public void setListOfAntisenseRNAs(ListOfAntisenseRNAs value) {
    	annotation.getExtension().setListOfAntisenseRNAs(value);
    }

    /**
     * Adds the antisense RNA.
     *
     * @param antisenseRNA void
     * TODO
     */
    public void addAntisenseRNA(AntisenseRNA antisenseRNA){
    	antiSenseRNAList.add(antisenseRNA);
    }

    /**
     * Removes the antisense RNA.
     *
     * @param antisenseRNA void
     * TODO
     */
    public void removeAntisenseRNA(AntisenseRNA antisenseRNA){
    	antiSenseRNAList.remove(antisenseRNA);
    }

    /**
     * Gets the list of layers.
     *
     * @return ListOfLayers
     * TODO
     */
    public List<Layer> getListOfLayers() {
        return layerList;
    }

    /**
     * Sets the list of layers.
     *
     * @param value void
     * TODO
     */
    public void setListOfLayers(ListOfLayers value) {
    	annotation.getExtension().setListOfLayers(value);
    }

    /**
     * Adds the layer.
     *
     * @param layer void
     * TODO
     */
    public void addLayer(Layer layer){
    	layerList.add(layer);
    }

    /**
     * Removes the layer.
     *
     * @param layer void
     * TODO
     */
    public void removeLayer(Layer layer){
    	layerList.remove(layer);
    }


    /**
     * Gets the list of block diagrams.
     *
     * @return ListOfBlockDiagrams
     * TODO
     */
	public List<BlockDiagram> getListOfBlockDiagrams() {
        return blockDiagramList;
    }

    /**
     * Sets the list of block diagrams.
     *
     * @param value void
     * TODO
     */
    public void setListOfBlockDiagrams(ListOfBlockDiagrams value) {
    	annotation.getExtension().setListOfBlockDiagrams(value);
    }

    /**
     * Adds the block diagram.
     *
     * @param blockDiagram void
     * TODO
     */
    public void addBlockDiagram(BlockDiagram blockDiagram){
    	blockDiagramList.add(blockDiagram);
    }

    /**
     * Removes the block diagram.
     *
     * @param blockDiagram void
     * TODO
     */
    public void removeBlockDiagram(BlockDiagram blockDiagram){
    	blockDiagramList.remove(blockDiagram);
    }

	/**
	 * Reorder compartment according to position.
	 *
	 * @param cawList the caw list
	 * @return List<CompartmentAliasWrapper>
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
