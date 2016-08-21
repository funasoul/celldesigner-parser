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
package org.sbml.extconverter;

import java.util.List;

import org.sbml._2001.ns.celldesigner.Modification;
import org.sbml.jsbml.Model;
import org.sbml.jsbml.Reaction;
import org.sbml.jsbml.Species;
import org.sbml.jsbml.ext.fbc.And;
import org.sbml.jsbml.ext.fbc.FBCConstants;
import org.sbml.jsbml.ext.fbc.FBCModelPlugin;
import org.sbml.jsbml.ext.fbc.FBCReactionPlugin;
import org.sbml.jsbml.ext.fbc.FBCSpeciesPlugin;
import org.sbml.jsbml.ext.fbc.GeneProduct;
import org.sbml.jsbml.ext.fbc.GeneProductAssociation;
import org.sbml.jsbml.ext.fbc.GeneProductRef;
import org.sbml.jsbml.ext.fbc.LogicalOperator;
import org.sbml.jsbml.ext.fbc.Or;
import org.sbml.wrapper.ModelWrapper;
import org.sbml.wrapper.ReactionWrapper;
import org.sbml.wrapper.SpeciesWrapper;

// TODO: Auto-generated Javadoc
/**
 * The Class FBCConverter.
 *
 * @author Kaito Ii
 * 
 * Date Created: Aug 6, 2016
 */

public class FBCConverter extends EXTConverter{
	
	/** The fbc plugin. */
	private FBCModelPlugin fbcPlugin;
	
	/**
	 * Instantiates a new FBC converter.
	 *
	 * @param model the model
	 * @param mWrapper the m wrapper
	 */
	public FBCConverter(Model model, ModelWrapper mWrapper) {
		super(model, mWrapper);
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
		}
		
	}
	
	/**
	 * Convert reactions.
	 */
	public void convertReactions(){
		List<ReactionWrapper> rwList = mWrapper.getListOfReactionWrapper();
		for(ReactionWrapper rw : rwList){
			List<Modification> mList = rw.getListOfModification();
			if(mList == null) 
				continue;
			
			for(Modification modification : mList){
				if(modification.getType().contains("BOOLEAN_LOGIC_GATE")){
					Reaction reaction = model.getReaction(rw.getId());
					FBCReactionPlugin reactionPlugin = (FBCReactionPlugin) reaction.getPlugin(FBCConstants.shortLabel);
					GeneProductAssociation  gpa;
					if(reactionPlugin.isSetGeneProductAssociation())
						gpa = reactionPlugin.getGeneProductAssociation();
					else
						gpa = reactionPlugin.createGeneProductAssociation("ga_" + rw.getId());
					
					String modifiers = modification.getModifiers();
					String[] modifierList = modifiers.split(",");
					for(String m : modifierList){
						if(fbcPlugin.getListOfGeneProducts().get("gene_" + m) == null){
							GeneProduct geneProduct = fbcPlugin.createGeneProduct("gene_" + m);
							geneProduct.setLabel(m);
							geneProduct.setAssociatedSpecies(m);
						}
					}
					
					LogicalOperator lo;
					if(modification.getType().contains("AND")){
						lo = setAllProductRef(new And(), modifierList, rw.getId());
					} else {
						lo = setAllProductRef(new Or(), modifierList, rw.getId());
					}
					gpa.setAssociation(lo);		
				}
			}
		}
	}
	
	/**
	 * Sets the all product ref.
	 *
	 * @param lo the lo
	 * @param modifiers the modifiers
	 * @return the logical operator
	 */
	public LogicalOperator setAllProductRef(LogicalOperator lo, String[] modifiers, String reactionId){
		for(String modifier : modifiers){
				GeneProductRef gpr = lo.createGeneProductRef("GeneProductRef_" + reactionId + "_" + modifier);
				gpr.setGeneProduct("gene_" + modifier);
		}
		
		return lo;
	}	
}
