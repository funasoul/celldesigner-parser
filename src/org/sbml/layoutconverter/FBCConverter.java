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
package org.sbml.layoutconverter;

import java.util.List;

import org.sbml._2001.ns.celldesigner.Gene;
import org.sbml.jsbml.ListOf;
import org.sbml.jsbml.Model;
import org.sbml.jsbml.Reaction;
import org.sbml.jsbml.Species;
import org.sbml.jsbml.ext.fbc.FBCConstants;
import org.sbml.jsbml.ext.fbc.FBCModelPlugin;
import org.sbml.jsbml.ext.fbc.FBCReactionPlugin;
import org.sbml.jsbml.ext.fbc.FBCSpeciesPlugin;
import org.sbml.jsbml.ext.fbc.GeneProduct;
import org.sbml.wrapper.ModelWrapper;
import org.sbml.wrapper.SpeciesWrapper;

// TODO: Auto-generated Javadoc
/**
 * The Class FBCConverter.
 *
 * @author Kaito Ii
 * 
 * Date Created: Aug 6, 2016
 */

public class FBCConverter {
	
	/** The model. */
	private Model model;
	
	/** The m wrapper. */
	private ModelWrapper mWrapper;
	
	/** The fbc plugin. */
	private FBCModelPlugin fbcPlugin;
	
	/**
	 * Instantiates a new FBC converter.
	 *
	 * @param model the model
	 * @param mWrapper the m wrapper
	 */
	public FBCConverter(Model model, ModelWrapper mWrapper) {
		this.model = model;
		this.mWrapper = mWrapper;
	}
	
	/**
	 * Convert.
	 */
	public void convert(){
		convertModel();
		convertSpecies();
		convertReactions();
	}

	/**
	 * Convert model.
	 */
	public void convertModel(){
		fbcPlugin = (FBCModelPlugin) model.getPlugin(FBCConstants.shortLabel);
		fbcPlugin.setStrict(false);
	}
	
	/**
	 * Convert species.
	 */
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
				geneProduct.setLabel(gene.getType() + "_" + gene.getId());
				geneProduct.setAssociatedSpecies(sw.getId());
			}
		}
		
	}

	
	/**
	 * Convert reactions.
	 */
	public void convertReactions(){
		ListOf<Reaction> lor = model.getListOfReactions();
		for(Reaction reaction : lor){
			FBCReactionPlugin reactionPlugin = (FBCReactionPlugin) reaction.getPlugin(FBCConstants.shortLabel);
		}
	}
	
}
