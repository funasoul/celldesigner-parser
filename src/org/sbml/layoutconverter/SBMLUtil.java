package org.sbml.layoutconverter;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import javax.swing.tree.TreeNode;
import javax.xml.stream.XMLStreamException;

import org.sbml.jsbml.ListOf;
import org.sbml.jsbml.Reaction;
import org.sbml.jsbml.SBMLDocument;
import org.sbml.jsbml.SBMLReader;
import org.sbml.jsbml.SBase;
import org.sbml.jsbml.ext.layout.LayoutConstants;

// TODO: Auto-generated Javadoc
/**
 * The Class SBMLUtil.
 *
 * @author Kaito Ii
 * 
 * Date Created: Jun 24, 2016
 */

public class SBMLUtil {

	/** The defaultsbmllevel. */
	public static int DEFAULT_SBML_LEVEL = 3;
	
	/** The defaultsbmlversion. */
	public static int DEFAULT_SBML_VERSION = 1;
	
	/** The defaultCellDesignersbmllevel. */
	public static int DEFAULT_CELLDESIGNER_SBML_LEVEL = 2;
	
	/** The defaultCellDesignersbmlversion. */
	public static int DEFAULT_CELLDESIGNER_SBML_VERSION = 4;
	
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
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args){
		try {
			SBMLDocument d = SBMLReader.read(new File("sample/layout_example1_L3.xml"));
			Map<String, String> nsMap = d.getDeclaredNamespaces();
			for(java.util.Map.Entry<String, String> e: nsMap.entrySet())
				System.out.println(e.toString());
			System.out.println(LayoutConstants.namespaceURI_L3V1V1);
		} catch (XMLStreamException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * Checks if is set SBO term.
	 *
	 * @param node the node
	 */
	public static void isSetSBOTerm(TreeNode node){		
		for(int i = 0; i < node.getChildCount(); i++){
			TreeNode n = node.getChildAt(i);
			if(n instanceof SBase && !((SBase)n ).isSetSBOTerm()){
				System.out.println(n.getClass());
				SBMLLevelandVersionHandler.isSetSBOTerm = false;
			}
			isSetSBOTerm(n);	
		}		
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
	 * Creates the output file name.
	 *
	 * @param file the file
	 * @return the string
	 */
	public static String createOutputFileName(File file) {
		String path = file.getPath();
		return path.substring(0, path.indexOf(".")) + "_converted.xml";		
	}
}
