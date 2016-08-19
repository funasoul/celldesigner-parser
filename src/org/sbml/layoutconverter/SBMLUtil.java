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

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import javax.swing.tree.TreeNode;
import javax.xml.stream.XMLStreamException;

import org.sbml.jsbml.AbstractMathContainer;
import org.sbml.jsbml.Compartment;
import org.sbml.jsbml.Constraint;
import org.sbml.jsbml.Delay;
import org.sbml.jsbml.Event;
import org.sbml.jsbml.EventAssignment;
import org.sbml.jsbml.FunctionDefinition;
import org.sbml.jsbml.InitialAssignment;
import org.sbml.jsbml.KineticLaw;
import org.sbml.jsbml.ListOf;
import org.sbml.jsbml.Model;
import org.sbml.jsbml.ModifierSpeciesReference;
import org.sbml.jsbml.Parameter;
import org.sbml.jsbml.Priority;
import org.sbml.jsbml.Reaction;
import org.sbml.jsbml.Rule;
import org.sbml.jsbml.SBMLDocument;
import org.sbml.jsbml.SBMLReader;
import org.sbml.jsbml.SBase;
import org.sbml.jsbml.Species;
import org.sbml.jsbml.SpeciesReference;
import org.sbml.jsbml.StoichiometryMath;
import org.sbml.jsbml.Trigger;
import org.sbml.jsbml.ext.fbc.FBCConstants;
import org.sbml.jsbml.ext.layout.LayoutConstants;

// TODO: Auto-generated Javadoc
/**
 * The Class SBMLUtil.
 *
 * @author Kaito Ii
 * 
 * Date Created: Jun 24, 2016
 */

@SuppressWarnings("deprecation")
public class SBMLUtil {

	/** The defaultsbmllevel. */
	public static final int DEFAULT_SBML_LEVEL = 3;
	
	/** The defaultsbmlversion. */
	public static final int DEFAULT_SBML_VERSION = 1;
	
	/** The defaultCellDesignersbmllevel. */
	public static final int DEFAULT_CELLDESIGNER_SBML_LEVEL = 2;
	
	/** The defaultCellDesignersbmlversion. */
	public static final int DEFAULT_CELLDESIGNER_SBML_VERSION = 4;
	
	/** The Constant DEFALUT_CELLDESIGNER_MODEL_VERSION. */
	public static final int DEFALUT_CELLDESIGNER_MODEL_VERSION = 4;
	
	/** The Constant cellDesignerNS. */
	final static String cellDesignerNS = "http://www.sbml.org/2001/ns/celldesigner";
	
    /** The Constant intSBOTermForModel. */
    public static final int intSBOTermForModel = 231;
    
    /** The Constant intSBOTermForFunctionDefinition. */
    public static final int intSBOTermForFunctionDefinition = 64;
    
    /** The Constant intSBOTermForCompartmentType. */
    public static final int intSBOTermForCompartmentType = 240;
    
    /** The Constant intSBOTermForSpeciesType. */
    public static final int intSBOTermForSpeciesType = 240;
    
    /** The Constant intSBOTermForCompartment. */
    public static final int intSBOTermForCompartment = 240;
    
    /** The Constant intSBOTermForSpecies. */
    public static final int intSBOTermForSpecies = 240;
    
    /** The Constant intSBOTermForReaction. */
    public static final int intSBOTermForReaction = 231;
    
    /** The Constant intSBOTermForParameter. */
    public static final int intSBOTermForParameter = 2;
    
    /** The Constant intSBOTermForSpeciesReference. */
    public static final int intSBOTermForSpeciesReference = 3;
    
    /** The Constant intSBOTermForModifierSpeciesReference. */
    public static final int intSBOTermForModifierSpeciesReference = 3;
    
    /** The Constant intSBOTermForKineticLaw. */
    public static final int intSBOTermForKineticLaw = 1;
    
    /** The Constant intSBOTermForInitialAssignment. */
    public static final int intSBOTermForInitialAssignment = 64;
    
    /** The Constant intSBOTermForAlgebraicRule. */
    public static final int intSBOTermForAlgebraicRule = 64;
    
    /** The Constant intSBOTermForAssignmentRule. */
    public static final int intSBOTermForAssignmentRule = 64;
    
    /** The Constant intSBOTermForRateRule. */
    public static final int intSBOTermForRateRule = 64;
    
    /** The Constant intSBOTermForConstraint. */
    public static final int intSBOTermForConstraint = 64;
    
    /** The Constant intSBOTermForEvent. */
    public static final int intSBOTermForEvent = 231;
    
    /** The Constant intSBOTermForTrigger. */
    public static final int intSBOTermForTrigger = 64;
    
    /** The Constant intSBOTermForDelay. */
    public static final int intSBOTermForDelay = 64;
    
    /** The Constant intSBOTermForEventAssignment. */
    public static final int intSBOTermForEventAssignment = 64;
    
    /** The Constant intSBOTermForANTISENSE_RNA. */
    //  Species Aliases
    public static final int intSBOTermForANTISENSE_RNA = 317;
    
    /** The Constant intSBOTermForCOMPLEX. */
    public static final int intSBOTermForCOMPLEX = 253;
    
    /** The Constant intSBOTermForDEGRADED. */
    public static final int intSBOTermForDEGRADED = 291;
    
    /** The Constant intSBOTermForDRUG. */
    public static final int intSBOTermForDRUG = 298;
    
    /** The Constant intSBOTermForGENE. */
    public static final int intSBOTermForGENE = 243;
    
    /** The Constant intSBOTermForGENERIC. */
    public static final int intSBOTermForGENERIC = 252;
    
    /** The Constant intSBOTermForION. */
    public static final int intSBOTermForION = 327;
    
    /** The Constant intSBOTermForPHENOTYPE. */
    public static final int intSBOTermForPHENOTYPE = 358;
    
    /** The Constant intSBOTermForPROTEIN. */
    public static final int intSBOTermForPROTEIN = 297;
    
    /** The Constant intSBOTermForRECEPTOR. */
    public static final int intSBOTermForRECEPTOR = 244;
    
    /** The Constant intSBOTermForRNA. */
    public static final int intSBOTermForRNA = 250;
    
    /** The Constant intSBOTermForION_CHANNEL. */
    public static final int intSBOTermForION_CHANNEL = 242;
    
    /** The Constant intSBOTermForSIMPLE_MOLECULE. */
    public static final int intSBOTermForSIMPLE_MOLECULE = 247;
    
    /** The Constant intSBOTermForTRUNCATED. */
    public static final int intSBOTermForTRUNCATED = 248;
    
    /** The Constant intSBOTermForUNKNOWN. */
    public static final int intSBOTermForUNKNOWN = 285;
    
    /** The Constant intSBOTermForKNOWN_TRANSITION_OMITTED. */
    // Reaction Aliases
    public static final int intSBOTermForKNOWN_TRANSITION_OMITTED = 397;
    
    /** The Constant intSBOTermForSTATE_TRANSITION. */
    public static final int intSBOTermForSTATE_TRANSITION = 176;
    
    /** The Constant intSBOTermForTRANSCRIPTION. */
    public static final int intSBOTermForTRANSCRIPTION = 183;
    
    /** The Constant intSBOTermForTRANSLATION. */
    public static final int intSBOTermForTRANSLATION = 184;
    
    /** The Constant intSBOTermForTRANSPORT. */
    public static final int intSBOTermForTRANSPORT = 185;
    
    /** The Constant intSBOTermForHETERODIMER_ASSOCIATION. */
    public static final int intSBOTermForHETERODIMER_ASSOCIATION = 177; // non-covalent binding
    
    /** The Constant intSBOTermForDISSOCIATION. */
    public static final int intSBOTermForDISSOCIATION = 180;
    
    /** The Constant intSBOTermForTRUNCATION. */
    public static final int intSBOTermForTRUNCATION = 178; // Cleavage
    
    /** The Constant intSBOTermForUNKNOWN_TRANSITION. */
    public static final int intSBOTermForUNKNOWN_TRANSITION = 396;
    
    /** The Constant intSBOTermForReactant. */
    public static final int intSBOTermForReactant = 10;
    
    /** The Constant intSBOTermForProduct. */
    public static final int intSBOTermForProduct = 11;
    
    /** The Constant intSBOTermForModifier. */
    public static final int intSBOTermForModifier = 19;
    
    /** The Constant intSBOTermForCATALYSIS. */
    // Modifier Aliases
    public static final int intSBOTermForCATALYSIS = 13;
    
    /** The Constant intSBOTermForINHIBITION. */
    public static final int intSBOTermForINHIBITION = 20;
    
    /** The Constant intSBOTermForMODULATION. */
    public static final int intSBOTermForMODULATION = 19;
    
    /** The Constant intSBOTermForPHYSICAL_STIMULATION. */
    public static final int intSBOTermForPHYSICAL_STIMULATION = 21;
    
    /** The Constant intSBOTermForTRANSCRIPTIONAL_ACTIVATION. */
    public static final int intSBOTermForTRANSCRIPTIONAL_ACTIVATION = 459;
    
    /** The Constant intSBOTermForTRANSCRIPTIONAL_INHIBITION. */
    public static final int intSBOTermForTRANSCRIPTIONAL_INHIBITION = 20;
    
    /** The Constant intSBOTermForTRANSLATIONAL_ACTIVATION. */
    public static final int intSBOTermForTRANSLATIONAL_ACTIVATION = 459;
    
    /** The Constant intSBOTermForTRANSLATIONAL_INHIBITION. */
    public static final int intSBOTermForTRANSLATIONAL_INHIBITION = 20;
    
    /** The Constant intSBOTermForTRIGGER. */
    public static final int intSBOTermForTRIGGER = 461;
    
    /** The Constant intSBOTermForUNKNOWN_CATALYSIS. */
    public static final int intSBOTermForUNKNOWN_CATALYSIS = 13;
    
    /** The Constant intSBOTermForUNKNOWN_INHIBITION. */
    public static final int intSBOTermForUNKNOWN_INHIBITION = 20;

    /** The Constant intSBOTermForDEFAULT_SPECIES. */
    public static final int intSBOTermForDEFAULT_SPECIES = intSBOTermForPROTEIN;
	
    /** The Constant intSBOTermForDEFAULT_REACTION. */
    public static final int intSBOTermForDEFAULT_REACTION = intSBOTermForSTATE_TRANSITION;
    
	/**
	 * Checks if is sets the cell designer name space.
	 *
	 * @param document the document
	 * @return boolean
	 * TODO
	 */
	public static boolean isSetCellDesignerNameSpace(SBMLDocument document){
		Map<String, String> nsMap = document.getDeclaredNamespaces();
		return nsMap.containsValue(cellDesignerNS);
	}
	
	/**
	 * Checks if is sets the cell designer name space.
	 *
	 * @param file the file
	 * @return true, if is sets the cell designer name space
	 * @throws XMLStreamException the XML stream exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static boolean isSetCellDesignerNameSpace(File file) throws XMLStreamException, IOException{
		return isSetCellDesignerNameSpace(SBMLReader.read(file));
	}	
	
	/**
	 * Checks if is sets the cell designer name space.
	 *
	 * @param document the document
	 * @return boolean
	 * TODO
	 */
	public static boolean isSetLayoutNameSpace(SBMLDocument document){
		Map<String, String> nsMap = document.getDeclaredNamespaces();
		return nsMap.containsValue(LayoutConstants.namespaceURI_L3V1V1);
	}
	
	/**
	 * Adds the extension package layout.
	 *
	 * @param document the document
	 * @return the SBML document
	 */
	public static SBMLDocument addExtensionPackageLayout(SBMLDocument document){
		document.enablePackage(LayoutConstants.getNamespaceURI(SBMLUtil.DEFAULT_SBML_LEVEL, SBMLUtil.DEFAULT_SBML_VERSION), true);
		document.setPackageRequired(LayoutConstants.getNamespaceURI(SBMLUtil.DEFAULT_SBML_LEVEL, SBMLUtil.DEFAULT_SBML_VERSION), true);

		return document;
	}

	/**
	 * Adds the extension package fbc.
	 *
	 * @param document the document
	 * @return the SBML document
	 */
	public static SBMLDocument addExtensionPackageFBC(SBMLDocument document){
		document.enablePackage(FBCConstants.getNamespaceURI(SBMLUtil.DEFAULT_SBML_LEVEL, SBMLUtil.DEFAULT_SBML_VERSION), true);
		document.setPackageRequired(FBCConstants.getNamespaceURI(SBMLUtil.DEFAULT_SBML_LEVEL, SBMLUtil.DEFAULT_SBML_VERSION), false);

		return document;
	}
	
	/**
	 * Adds the extension package fbc.
	 *
	 * @param model the model
	 * @return the SBML document
	 */
	public static Model addExtensionPackageFBC(Model model){
		return addExtensionPackageFBC(model.getSBMLDocument()).getModel();
	}
	
	/**
	 * Checks if is level and version match with CD.
	 *
	 * @param file the file
	 * @return true, if is level and version match with CD
	 * @throws XMLStreamException the XML stream exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static boolean isLevelAndVersionMatchWithCD(File file) throws XMLStreamException, IOException{
		return isLevelAndVersionMatchWithCD(SBMLReader.read(file));
	}
	
	/**
	 * Checks if is level and version match with CD.
	 *
	 * @param document the document
	 * @return true, if is level and version match with CD
	 */
	public static boolean isLevelAndVersionMatchWithCD(SBMLDocument document){
		return (document.getLevel() == DEFAULT_CELLDESIGNER_SBML_LEVEL) && (document.getVersion() == DEFAULT_CELLDESIGNER_SBML_VERSION);
	}
	
	/**
	 * Checks if is sets the SBO term.
	 *
	 * @param document the document
	 * @return boolean
	 * TODO
	 */
	public static boolean isSetSBOTerm(SBMLDocument document){
		boolean species = checkSBOTermFromList(document.getModel().getListOfSpecies());
		boolean compartment = checkSBOTermFromList(document.getModel().getListOfCompartments());
		boolean reaction = checkSBOTermFromList(document.getModel().getListOfReactions());
		ListOf<Reaction> lor = document.getModel().getListOfReactions();
		boolean reactant = true;
		boolean product = true;
		boolean modifier = true;
		
		for(Reaction r: lor){
			if(!checkSBOTermFromList(r.getListOfReactants()))
				reactant = false;
			if(!checkSBOTermFromList(r.getListOfProducts()))
				product = false;
			if(!checkSBOTermFromList(r.getListOfModifiers()))
				modifier = false;
		}
		
		return species && compartment && reaction && reactant && product && modifier;
	}
	
	/**
	 * Check SBO term from list.
	 *
	 * @param list the list
	 * @return boolean
	 * TODO
	 */
	public static boolean checkSBOTermFromList(ListOf<?> list){
		@SuppressWarnings("unchecked")
		Iterator<SBase> iterator = (Iterator<SBase>) list.iterator();
		
		while(iterator.hasNext())
			 if(!((SBase) iterator.next()).isSetSBOTerm())
				return false;
		
		return true;
	}

	/**
	 * Adds the default SBO term.
	 *
	 * @param document the document
	 * @return the SBML document
	 */
	public static SBMLDocument addDefaultSBOTerm(SBMLDocument document){
		TreeNode node = document.getRoot();
		addDefaultSBOTerm(node);
		
		return document;
	}
	
	/**
	 * Adds the default SBO term.
	 *
	 * @param node the node
	 */
	public static void addDefaultSBOTerm(TreeNode node){
		for(int i = 0; i < node.getChildCount(); i++)
			addDefaultSBOTerm(node.getChildAt(i));
		
		if(!(node instanceof SBase) )
			return ;
		
		SBase sbase = (SBase) node;
		if(sbase.isSetSBOTerm())
			return;
		
		if(sbase instanceof Model){
			sbase.setSBOTerm(intSBOTermForModel);
		} else if(sbase instanceof Species){
			sbase.setSBOTerm(intSBOTermForGENERIC);
		} else if(sbase instanceof Compartment){
			sbase.setSBOTerm(intSBOTermForCompartment);
		}else if(sbase instanceof Parameter){
			sbase.setSBOTerm(intSBOTermForParameter);
		}else if(sbase instanceof Rule){
			sbase.setSBOTerm(intSBOTermForRateRule);
		}else if(sbase instanceof Compartment){
			sbase.setSBOTerm(intSBOTermForCompartment);
		}else if(sbase instanceof Reaction){
			sbase.setSBOTerm(intSBOTermForReaction);
		}else if(sbase instanceof SpeciesReference){
			Reaction r = (Reaction) ((SpeciesReference) sbase).getParentSBMLObject().getParentSBMLObject();
			if(r.getListOfReactants().contains(sbase))
				sbase.setSBOTerm(intSBOTermForReactant);
			else 
				sbase.setSBOTerm(intSBOTermForProduct);
		}else if(sbase instanceof ModifierSpeciesReference){
			sbase.setSBOTerm(intSBOTermForModifierSpeciesReference);
		}else if(sbase instanceof KineticLaw){
			sbase.setSBOTerm(intSBOTermForKineticLaw);
		}
	}
	
	/**
	 * Creates the output file name.
	 *
	 * @param file the file
	 * @return the string
	 */
	public static String createOutputFileName(File file) {
		String path = file.getPath();
		return path.substring(0, path.indexOf(".")) + "_converted.xml";		
	}

	/**
	 * SBO term to string.
	 *
	 * @param sboTerm the sbo term
	 * @return String
	 * TODO
	 */
	public static String SBOTermToString(int sboTerm) {
		switch (sboTerm){
		case intSBOTermForANTISENSE_RNA :
			return "ANTISENSE_RNA";
		case intSBOTermForCOMPLEX :
			return "COMPLEX";
		case intSBOTermForDEGRADED :
			return "DEGRADED";
		case intSBOTermForDRUG :
			return "DRUG";
		case intSBOTermForGENE :
			return "GENE";
		case intSBOTermForGENERIC :
			return "GENERIC";
		case intSBOTermForION :
			return "ION";
		case intSBOTermForPHENOTYPE :
			return "PHENOTYPE";
		case intSBOTermForPROTEIN :
			return "PROTEIN";
		case intSBOTermForRECEPTOR :
			return "RECEPTOR";
		case intSBOTermForRNA :
			return "RNA";
		case intSBOTermForION_CHANNEL :
			return "ION_CHANNEL";
		case intSBOTermForSIMPLE_MOLECULE :
			return "SIMPLE_MOLECULE";
		case intSBOTermForTRUNCATED :
			return "TRUNCATED";
		case intSBOTermForUNKNOWN :
			return "UNKNOWN";
		case intSBOTermForKNOWN_TRANSITION_OMITTED :
			return "KNOWN_TRANSITION_OMITTED";
		case intSBOTermForSTATE_TRANSITION :
			return "STATE_TRANSITION";
		case intSBOTermForTRANSCRIPTION :
			return "TRANSCRIPTION";
		case intSBOTermForTRANSLATION :
			return "TRANSLATION";
		case intSBOTermForTRANSPORT :
			return "TRANSPORT";
		case intSBOTermForHETERODIMER_ASSOCIATION :
			return "HETERODIMER_ASSOCIATION";
		case intSBOTermForDISSOCIATION :
			return "DISSOCIATION";
		case intSBOTermForTRUNCATION :
			return "TRUNCATION";
		case intSBOTermForUNKNOWN_TRANSITION :
			return "UNKNOWN_TRANSITION";
		case intSBOTermForModifier : // = intSBOTermForMODULATION
			return "Modifier";
		case intSBOTermForCATALYSIS : // = intSBOTermForUNKNOWN_CATALYSIS
			return "CATALYSIS";
		case intSBOTermForINHIBITION : // = intSBOTermForTRANSLATIONAL_INHIBITION = intSBOTermForTRANSCRIPTIONAL_INHIBITION = intSBOTermForUNKNOWN_INHIBITION
			return "INHIBITION";
		case intSBOTermForPHYSICAL_STIMULATION :
			return "PHYSICAL_STIMULATION";
		case intSBOTermForTRANSCRIPTIONAL_ACTIVATION : //  = intSBOTermForTRANSLATIONAL_ACTIVATION
			return "TRANSCRIPTIONAL_ACTIVATION";
		case intSBOTermForTRIGGER :
			return "TRIGGER";

			default:
				return "";
		}
	}
	
	/**
	 * SBO term to CD species.
	 *
	 * @param sboTerm the sbo term
	 * @return the string
	 */
	public static String SBOTermToCDSpecies(int sboTerm) {
		switch (sboTerm){
		case intSBOTermForANTISENSE_RNA :
			return "ANTISENSE_RNA";
		case intSBOTermForCOMPLEX :
			return "COMPLEX";
		case intSBOTermForDEGRADED :
			return "DEGRADED";
		case intSBOTermForDRUG :
			return "DRUG";
		case intSBOTermForGENE :
			return "GENE";	
		case intSBOTermForION :
			return "ION";
		case intSBOTermForPHENOTYPE :
			return "PHENOTYPE";
		case intSBOTermForRNA :
			return "RNA";
		case intSBOTermForSIMPLE_MOLECULE :
			return "SIMPLE_MOLECULE";
		case intSBOTermForUNKNOWN :
			return "UNKNOWN";
		case intSBOTermForGENERIC :
		case intSBOTermForPROTEIN :
		case intSBOTermForION_CHANNEL :
		case intSBOTermForTRUNCATED :
		case intSBOTermForRECEPTOR :
		default:
				return "PROTEIN";
		}
	}

	/**
	 * SBO term to CD reaction.
	 *
	 * @param sboterm the sboterm
	 * @return String
	 * TODO
	 */
	public static String SBOTermToCDReaction(int sboterm) {
		switch(sboterm){
		case intSBOTermForKNOWN_TRANSITION_OMITTED :
			return "KNOWN_TRANSITION_OMITTED";
		case intSBOTermForINHIBITION : // = intSBOTermForTRANSLATIONAL_INHIBITION = intSBOTermForTRANSCRIPTIONAL_INHIBITION = intSBOTermForUNKNOWN_INHIBITION
			return "INHIBITION";
		case intSBOTermForTRANSCRIPTION :
			return "TRANSCRIPTION";
		case intSBOTermForTRANSLATION :
			return "TRANSLATION";
		case intSBOTermForTRANSPORT :
			return "TRANSPORT";
		case intSBOTermForHETERODIMER_ASSOCIATION :
			return "HETERODIMER_ASSOCIATION";
		case intSBOTermForDISSOCIATION :
			return "DISSOCIATION";
		case intSBOTermForTRUNCATION :
			return "TRUNCATION";
		case intSBOTermForUNKNOWN_TRANSITION :
			return "UNKNOWN_TRANSITION";
		case intSBOTermForSTATE_TRANSITION :
			default:
			return "STATE_TRANSITION";
		}
	}

	/**
	 * SBO term to CD modifier.
	 *
	 * @param sboterm the sboterm
	 * @return the string
	 */
	public static String SBOTermToCDModifier(int sboterm){
		switch(sboterm){
		case intSBOTermForINHIBITION : // = intSBOTermForTRANSLATIONAL_INHIBITION = intSBOTermForTRANSCRIPTIONAL_INHIBITION = intSBOTermForUNKNOWN_INHIBITION
			return "INHIBITION";
		case intSBOTermForTRANSCRIPTIONAL_ACTIVATION : //  = intSBOTermForTRANSLATIONAL_ACTIVATION
			return "TRANSCRIPTIONAL_ACTIVATION";
		case intSBOTermForTrigger:
			return "TRIGGER";
		case intSBOTermForPHYSICAL_STIMULATION :
			return "PHYSICAL_STIMULATION";
		case intSBOTermForHETERODIMER_ASSOCIATION :
			return "HETERODIMER_ASSOCIATION";
		case intSBOTermForMODULATION :
			return "MODULATION";
		case intSBOTermForCATALYSIS : // = intSBOTermForUNKNOWN_CATALYSIS
			default:
			return "CATALYSIS";
		}
	}
	
	/**
	 * Sets the maths.
	 *
	 * @param d1 the d 1
	 * @param d2 the d 2
	 * @return the SBML document
	 */
	public static SBMLDocument setMaths(SBMLDocument d1, SBMLDocument d2) {
		TreeNode node2 = d2.getRoot();
		setMaths(d1, node2);
		return d1;
	}

	/**
	 * Sets the maths.
	 *
	 * @param d1 the d 1
	 * @param node2 the node 2
	 */
	public static void setMaths(SBMLDocument d1, TreeNode node2){
		for(int i = 0; i< node2.getChildCount(); i++){
			TreeNode node = node2.getChildAt(i);
			if(node instanceof AbstractMathContainer){
				setMathObject(d1, (AbstractMathContainer) node);
			}
			setMaths(d1, node);
		}
	}

	/**
	 * Sets the math object.
	 *
	 * @param d the d
	 * @param math the math
	 */
	public static void setMathObject(SBMLDocument d, AbstractMathContainer math){
		
		if(math instanceof FunctionDefinition){
			d.getModel().addFunctionDefinition((FunctionDefinition) math.clone());
		} else if(math instanceof KineticLaw){
			KineticLaw kl = (KineticLaw) math;
			d.getModel().getReaction(kl.getParent().getId()).setKineticLaw((KineticLaw) math.clone());
		} else if(math instanceof EventAssignment){
			EventAssignment ea = (EventAssignment) math;
			Event e = (Event) ea.getParentSBMLObject();
			d.getModel().getEvent(e.getId()).getListOfEventAssignments().append((EventAssignment) math.clone());
		} else if(math instanceof InitialAssignment){
			d.getModel().addInitialAssignment((InitialAssignment) math.clone());
		} else if(math instanceof Rule){
			d.getModel().addRule((Rule) math.clone());
		} else if(math instanceof Priority){
			Priority p = (Priority) math;
			Event e = p.getParent();
			d.getModel().getEvent(e.getId()).setPriority((Priority) math.clone());
		} else if(math instanceof StoichiometryMath){
			
		} else if(math instanceof Trigger){
			Trigger t = (Trigger) math;
			Event e = t.getParent();
			d.getModel().getEvent(e.getId()).setTrigger((Trigger) math.clone());
		} else if(math instanceof Constraint){
			d.getModel().addConstraint((Constraint) math.clone());
		} else if(math instanceof Delay){
			Delay delay = (Delay) math;
			Event e = delay.getParent();
			d.getModel().getEvent(e.getId()).setDelay((Delay) math.clone());
		} else {
			System.err.println("Unknown Math Class");
		}
	}

}