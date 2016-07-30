/*
 * 
 */
package org.sbml.wrapper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
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
import org.sbml._2001.ns.celldesigner.ModelAnnotationType;
import org.sbml._2001.ns.celldesigner.ModelAnnotationType.Extension;
import org.sbml._2001.ns.celldesigner.ModelDisplay;
import org.sbml._2001.ns.celldesigner.Protein;
import org.sbml._2001.ns.celldesigner.RNA;
import org.sbml._2001.ns.celldesigner.SpeciesAlias;
import org.sbml.jsbml.ext.layout.CompartmentGlyph;
import org.sbml.jsbml.ext.layout.SpeciesGlyph;
import org.sbml.layoutconverter.SBMLUtil;
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
  private Model model;
  
  /** The c wrapper list. */
  private List<CompartmentWrapper> cWrapperList;
  
  /** The r wrapper list. */
  private List<ReactionWrapper> rWrapperList;
  
  /** The s wrapper list. */
  private List<SpeciesWrapper> sWrapperList;
  
  /** The s alias wrapper list. */
  private List<SpeciesAliasWrapper> sAliasWrapperList;
  
  /** The c alias wrapper list. */
  private List<CompartmentAliasWrapper>  cAliasWrapperList;
  
  /** The complex wrapper list. */
  private List<ComplexSpeciesAliasWrapper> complexWrapperList;

  /** The anti sense RNA list. */
  private List<AntisenseRNA> antiSenseRNAList;
  
  /** The block diagram list. */
  private List<BlockDiagram> blockDiagramList;
  
  /** The c alias list. */
  private List<CompartmentAlias> cAliasList;
  
  /** The s alias list. */
  private List<SpeciesAlias> sAliasList;
  
  /** The complex species alias list. */
  private List<ComplexSpeciesAlias> complexSpeciesAliasList;
  
  /** The gene list. */
  private List<Gene> geneList;
  
  /** The group list. */
  private List<Group> groupList;
  
  /** The included species list. */
  private List<org.sbml._2001.ns.celldesigner.Species> includedSpeciesList;
  
  /** The layer list. */
  private List<Layer> layerList;
  
  /** The protein list. */
  private List<Protein> proteinList;
  
  /** The rna list. */
  private List<RNA> rnaList;
  
  /** The width. */
  private double width;
  
  /** The height. */
  private double height;

  /** The model display. */
  private ModelDisplay modelDisplay;
  
  /** The version. */
  private int version;

	/**
	 * Instantiates a new model wrapper.
	 *
	 * @param model the model
	 */
	public ModelWrapper(Model model){
		this.model = model;
		this.id = model.getId();
		this.name = model.getName();
		this.notes = model.getNotes();
		this.annotation = model.getAnnotation();

		if(model.getListOfCompartments() != null)
			this.cWrapperList = createCompartmentWrapperList(model.getListOfCompartments().getCompartment());
		else
			this.cWrapperList = new ArrayList<CompartmentWrapper>();
		
		if(model.getListOfSpecies() != null)
			this.sWrapperList = createSpeciesWrapperList(model.getListOfSpecies().getSpecies());
		else
			this.sWrapperList = new ArrayList<SpeciesWrapper>();

		if(model.getListOfReactions() != null)
			this.rWrapperList = createReactionWrapperList(model.getListOfReactions().getReaction());
		else
			this.rWrapperList = new ArrayList<ReactionWrapper>();
		
		if(annotation != null)
			setAnnotations();
		else
			initAnnotations();
	}

	/**
	 * Sets the annotations.
	 */
	void setAnnotations(){
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

		this.cAliasWrapperList = createCompartmentAliasWrapperList(annotation.getExtension().getListOfCompartmentAliases().getCompartmentAlias());

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
	
	/**
	 * Inits the annotations.
	 */
	void initAnnotations(){
		
		setAnnotation(new ModelAnnotationType());
		model.setAnnotation(annotation);
		annotation.setExtension(new Extension());
		
		setListOfAntisenseRNAs(new ListOfAntisenseRNAs());
		this.antiSenseRNAList = annotation.getExtension().getListOfAntisenseRNAs().getAntisenseRNA();

		setListOfBlockDiagrams(new ListOfBlockDiagrams());
		this.blockDiagramList = annotation.getExtension().getListOfBlockDiagrams().getBlockDiagram();

		setListOfCompartmentAliases(new ListOfCompartmentAliases());
		this.cAliasList = annotation.getExtension().getListOfCompartmentAliases().getCompartmentAlias();

		setListOfGenes(new ListOfGenes());
		this.geneList = annotation.getExtension().getListOfGenes().getGene();

		setListOfGroups(new ListOfGroups());
		this.groupList = annotation.getExtension().getListOfGroups().getGroup();

		setListOfLayers(new ListOfLayers());
		this.layerList = annotation.getExtension().getListOfLayers().getLayer();

		setListOfIncludedSpecies(new ListOfIncludedSpecies());
		this.includedSpeciesList = annotation.getExtension().getListOfIncludedSpecies().getSpecies();

		setListOfProteins(new ListOfProteins());
		this.proteinList = annotation.getExtension().getListOfProteins().getProtein();

		setListOfRNAs(new ListOfRNAs());
		this.rnaList = annotation.getExtension().getListOfRNAs().getRNA();

		setListOfSpeciesAliases(new ListOfSpeciesAliases());
		this.sAliasList = annotation.getExtension().getListOfSpeciesAliases().getSpeciesAlias();
		this.sAliasWrapperList = createSpeciesAliasWrapperList(sAliasList);
		
		setListOfCompartmentAliases(new ListOfCompartmentAliases());
		this.cAliasWrapperList = createCompartmentAliasWrapperList(annotation.getExtension().getListOfCompartmentAliases().getCompartmentAlias());

		setListOfComplexSpeciesAliases(new ListOfComplexSpeciesAliases());
		this.complexSpeciesAliasList = annotation.getExtension().getListOfComplexSpeciesAliases().getComplexSpeciesAlias();
		this.complexWrapperList = createComplexWrapperList(complexSpeciesAliasList);
		
		modelDisplay = new ModelDisplay();
		setModelDisplay(modelDisplay);
		
		model.setListOfFunctionDefinitions(null);	// add later
		model.setListOfRules(null);		// add later
		
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
	 * Sets the h.
	 *
	 * @param h the new h
	 */
	public void setH(double h){
		this.height = h;
		annotation.getExtension().getModelDisplay().setSizeY((short) height);
	}

	/**
	 * Sets the dimension.
	 *
	 * @param width the width
	 * @param height the height
	 */
	public void setDimension(double width, double height) {
		setH(height);
		setW(width);
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
	 * Gets the species alias wrapper by species id.
	 *
	 * @param id the id
	 * @return the species alias wrapper by species id
	 */
	public SpeciesAliasWrapper getSpeciesAliasWrapperBySpeciesId(String id){
		for(SpeciesAliasWrapper saw : sAliasWrapperList)
			if(saw.getSpecies().equals(id))
				return saw;
		
		return null;
	}
	
	/**
	 * Creates the species alias wrapper.
	 *
	 * @param sg the sg
	 * @return the species alias wrapper
	 */
	public SpeciesAliasWrapper createSpeciesAliasWrapper(SpeciesGlyph sg){
		SpeciesAliasWrapper saw =  new SpeciesAliasWrapper(sg, this);
		sAliasWrapperList.add(saw);
		
		return saw;
	}
		
	/**
	 * Creates the complex wrapper list.
	 *
	 * @param complexSpeciesAliasList the complex species alias list
	 * @return List<ComplexSpeciesAliasWrapper>
	 * TODO
	 */
	public List<ComplexSpeciesAliasWrapper> createComplexWrapperList(List<ComplexSpeciesAlias> complexSpeciesAliasList) {
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

//	public ComplexSpeciesAliasWrapper createComplexSpeciesAliasWrapper(){
//		ComplexSpeciesAliasWrapper csaw = new ComplexSpeciesAliasWrapper(complexSpeciesAlias, this);
//		complexWrapperList.add(csaw);
//	}
	
	
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
	 * Creates the compartment alias wrapper.
	 *
	 * @param cg the cg
	 * @return the compartment alias wrapper
	 */
	public CompartmentAliasWrapper createCompartmentAliasWrapper(CompartmentGlyph cg){
		CompartmentAliasWrapper caw = new CompartmentAliasWrapper(cg, this);
		cAliasWrapperList.add(caw);
		return caw;
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
    public  List<org.sbml._2001.ns.celldesigner.Species> getListOfIncludedSpecies() {
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
    public void addIncludedSpecies(org.sbml._2001.ns.celldesigner.Species species){
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
    	//cAliasWrapperList.add(new CompartmentAliasWrapper(compartmentAlias, this));
    }

    /**
     * Removes the compartment alias.
     *
     * @param compartmentAlias void
     * TODO
     */
    public void removeCompartmentAlias(CompartmentAlias compartmentAlias){
    	cAliasList.remove(compartmentAlias);
    	for(CompartmentAliasWrapper caw : cAliasWrapperList)
    		if(caw.getCompartment().equals(compartmentAlias.getCompartment()))
    			cAliasWrapperList.remove(caw);
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
     * Creates the protein.
     *
     * @param species the species
     * @return the protein
     */
    public Protein createProtein(org.sbml.jsbml.Species species){
    	Protein protein = new Protein();
    	protein.setId("pr" + (proteinList.size() + 1));
    	protein.setName(species.getId());
    	protein.setType(SBMLUtil.SBOTermToString(species.getSBOTerm()));
    	addProtein(protein);
    	
    	return protein;
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
     * Creates the gene.
     *
     * @param species the species
     * @return the gene
     */
    public Gene createGene(org.sbml.jsbml.Species species){
    	Gene gene = new Gene();
    	gene.setName(species.getId());
    	gene.setId("gn" + (geneList.size() + 1));
    	gene.setType(SBMLUtil.SBOTermToString(species.getSBOTerm()));
    	addGene(gene);
    	
    	return gene;
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
     * Creates the RNA.
     *
     * @param species the species
     * @return the rna
     */
    public RNA createRNA(org.sbml.jsbml.Species species){
    	RNA rna = new RNA();
    	rna.setName(species.getId());
    	rna.setId("rn" + (rnaList.size() + 1));
    	rna.setType(SBMLUtil.SBOTermToString(species.getSBOTerm()));
    	addRNA(rna);
    	
    	return rna;
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
     * Creates the antisense RNA.
     *
     * @param s the s
     * @return the antisense RNA
     */
    public AntisenseRNA createAntisenseRNA(org.sbml.jsbml.Species s){
    	AntisenseRNA arn = new AntisenseRNA();
    	arn.setName(s.getId());
    	arn.setId("arn" + (antiSenseRNAList.size() + 1));
    	arn.setType(SBMLUtil.SBOTermToString(s.getSBOTerm()));
    	addAntisenseRNA(arn);
    	
    	return arn;
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
					Collections.swap(cawList, outIndex, i);
				}
			}
		}

		return cawList;
	}

	/**
	 * Creates the species object from SBO term.
	 *
	 * @param sg the sg
	 * @param sboterm the sboterm
	 * @return org.sbml._2001.ns.celldesigner.Species
	 * TODO
	 */
//	public org.sbml._2001.ns.celldesigner.Species getSpeciesById(String reference) {
//		List<Species> list = model.getListOfSpecies().getSpecies();
//		
//		return ;
//	}

	public void createSpeciesObjectFromSBOTerm(SpeciesGlyph sg, int sboterm){
		switch (sboterm) {
			case SBMLUtil.intSBOTermForCOMPLEX:

			break;
			
			case SBMLUtil.intSBOTermForANTISENSE_RNA:
				createAntisenseRNA((org.sbml.jsbml.Species) sg.getSpeciesInstance());
			break;
			
			case SBMLUtil.intSBOTermForDEGRADED:
			case SBMLUtil.intSBOTermForDRUG:
			case SBMLUtil.intSBOTermForSIMPLE_MOLECULE:
			case SBMLUtil.intSBOTermForUNKNOWN:
			case SBMLUtil.intSBOTermForION:
			case SBMLUtil.intSBOTermForPHENOTYPE:
				//nothing to do
			break;
			
			case SBMLUtil.intSBOTermForGENE:
				createGene((org.sbml.jsbml.Species) sg.getSpeciesInstance());
			break;
				
			case SBMLUtil.intSBOTermForRNA:
				createRNA((org.sbml.jsbml.Species) sg.getSpeciesInstance());
			break;
			
			case SBMLUtil.intSBOTermForPROTEIN:
			case SBMLUtil.intSBOTermForGENERIC:
			case SBMLUtil.intSBOTermForRECEPTOR:
			case SBMLUtil.intSBOTermForION_CHANNEL:
			case SBMLUtil.intSBOTermForTRUNCATED:
			default:
				createProtein((org.sbml.jsbml.Species) sg.getSpeciesInstance());
				break;
		}
	}

	/**
	 * Gets the protein by species id.
	 *
	 * @param id void
	 * TODO
	 * @return the protein by species id
	 */
	public Protein getProteinBySpeciesId(String id) {
		for(Protein p : proteinList)
			if(p.getName().equals(id))
				return p;
		
		return null;
	}

	/**
	 * Gets the gene by species id.
	 *
	 * @param id void
	 * TODO
	 * @return the gene by species id
	 */
	public Gene getGeneBySpeciesId(String id) {
		for(Gene g : geneList)
			if(g.getName().equals(id))
				return g;
		
		return null;
	}

	/**
	 * Gets the RNA by species id.
	 *
	 * @param id void
	 * TODO
	 * @return the RNA by species id
	 */
	public RNA getRNABySpeciesId(String id) {
		for(RNA rna : rnaList)
			if(rna.getName().equals(id))
				return rna;
		
		return null;
	}

	/**
	 * Gets the antisense RNA by species id.
	 *
	 * @param id void
	 * TODO
	 * @return the antisense RNA by species id
	 */
	public AntisenseRNA getAntisenseRNABySpeciesId(String id) {
		for(AntisenseRNA asrna : antiSenseRNAList)
			if(asrna.getName().equals(id))
				return asrna;
		
		return null;		
	}

	/**
	 * Gets the species alias wrapper by position.
	 *
	 * @param x the x
	 * @param y the y
	 * @return SpeciesAliasWrapper
	 * TODO
	 */
	public SpeciesAliasWrapper getSpeciesAliasWrapperByPosition(double x, double y) {
		for(SpeciesAliasWrapper saw : sAliasWrapperList)
			if((int)saw.getX() == (int) x && (int)saw.getY() == (int) y)
				return saw;
		
		return null;
	}
}
