package org.sbml.layoutconverter;

import static org.sbml.jsbml.util.ModelBuilder.buildUnit;

import java.awt.Dimension;
import java.io.IOException;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.xml.stream.XMLStreamException;

import org.sbml._2001.ns.celldesigner.Gene;
import org.sbml.jsbml.CVTerm;
import org.sbml.jsbml.CVTerm.Qualifier;
import org.sbml.jsbml.Compartment;
import org.sbml.jsbml.JSBML;
import org.sbml.jsbml.ListOf;
import org.sbml.jsbml.Model;
import org.sbml.jsbml.Parameter;
import org.sbml.jsbml.Reaction;
import org.sbml.jsbml.SBMLDocument;
import org.sbml.jsbml.SBMLException;
import org.sbml.jsbml.SBMLReader;
import org.sbml.jsbml.SBMLWriter;
import org.sbml.jsbml.Species;
import org.sbml.jsbml.Unit;
import org.sbml.jsbml.UnitDefinition;
import org.sbml.jsbml.ext.fbc.And;
import org.sbml.jsbml.ext.fbc.Association;
import org.sbml.jsbml.ext.fbc.FBCConstants;
import org.sbml.jsbml.ext.fbc.FBCModelPlugin;
import org.sbml.jsbml.ext.fbc.FBCReactionPlugin;
import org.sbml.jsbml.ext.fbc.FBCSpeciesPlugin;
import org.sbml.jsbml.ext.fbc.GeneProduct;
import org.sbml.jsbml.ext.fbc.GeneProductAssociation;
import org.sbml.jsbml.ext.fbc.GeneProductRef;
import org.sbml.jsbml.ext.fbc.Or;
import org.sbml.jsbml.util.ModelBuilder;
import org.sbml.wrapper.ModelWrapper;
import org.sbml.wrapper.SpeciesWrapper;

/**
 * @author Kaito Ii
 *
 * Date Created: Aug 6, 2016
 */

public class FBCConverter {
	
	private Model model;
	
	private ModelWrapper mWrapper;
	
	private FBCModelPlugin fbcPlugin;
	
	public FBCConverter(Model model, ModelWrapper mWrapper) {
		this.model = model;
		this.mWrapper = mWrapper;
		
		model.getSBMLDocument().setPackageRequired("fbc", false);
	}
	
	public void convert(){
		convertModel();
		convertSpecies();
		convertReactions();
	}

	public void convertModel(){
		fbcPlugin = (FBCModelPlugin) model.getPlugin(FBCConstants.shortLabel);
		fbcPlugin.setStrict(false);
	}
	
	public void convertSpecies(){
		List<SpeciesWrapper> swList = mWrapper.getListOfSpeciesWrapper();

		for(SpeciesWrapper sw : swList){
			if(sw.getCharge() != null){
				Species s = model.getSpecies(sw.getId());
				FBCSpeciesPlugin speciesPlugin = (FBCSpeciesPlugin) s.getPlugin(FBCConstants.shortLabel);
				speciesPlugin.setCharge(sw.getCharge().intValue());
			}
	
			if(sw.getClazz().equals("GENE")){
				Gene gene = mWrapper.getGeneBySpeciesId(sw.getId());
				GeneProduct geneProduct = fbcPlugin.createGeneProduct(gene.getId());
				//geneProduct.setLabel(label);
				geneProduct.setAssociatedSpecies(sw.getId());
			}
		}
		
	}

	
	public void convertReactions(){
		ListOf<Reaction> lor = model.getListOfReactions();
		for(Reaction reaction : lor){
			FBCReactionPlugin reactionPlugin = (FBCReactionPlugin) reaction.getPlugin(FBCConstants.shortLabel);
		}
	}
	
}
