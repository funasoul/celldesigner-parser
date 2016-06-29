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
	
    public static final int intSBOTermForModel = 231;
    public static final int intSBOTermForFunctionDefinition = 64;
    public static final int intSBOTermForCompartmentType = 240;
    public static final int intSBOTermForSpeciesType = 240;
    public static final int intSBOTermForCompartment = 240;
    public static final int intSBOTermForSpecies = 240;
    public static final int intSBOTermForReaction = 231;
    public static final int intSBOTermForParameter = 2;
    public static final int intSBOTermForSpeciesReference = 3;
    public static final int intSBOTermForModifierSpeciesReference = 3;
    public static final int intSBOTermForKineticLaw = 1;
    public static final int intSBOTermForInitialAssignment = 64;
    public static final int intSBOTermForAlgebraicRule = 64;
    public static final int intSBOTermForAssignmentRule = 64;
    public static final int intSBOTermForRateRule = 64;
    public static final int intSBOTermForConstraint = 64;
    public static final int intSBOTermForEvent = 231;
    public static final int intSBOTermForTrigger = 64;
    public static final int intSBOTermForDelay = 64;
    public static final int intSBOTermForEventAssignment = 64;
    //  Species Aliases
    public static final int intSBOTermForANTISENSE_RNA = 317;
    public static final int intSBOTermForCOMPLEX = 253;
    public static final int intSBOTermForDEGRADED = 291;
    public static final int intSBOTermForDRUG = 298;
    public static final int intSBOTermForGENE = 243;
    public static final int intSBOTermForGENERIC = 252;
    public static final int intSBOTermForION = 327;
    public static final int intSBOTermForPHENOTYPE = 358;
    public static final int intSBOTermForPROTEIN = 297;
    public static final int intSBOTermForRECEPTOR = 244;
    public static final int intSBOTermForRNA = 250;
    public static final int intSBOTermForION_CHANNEL = 242;
    public static final int intSBOTermForSIMPLE_MOLECULE = 247;
    public static final int intSBOTermForTRUNCATED = 248;
    public static final int intSBOTermForUNKNOWN = 285;
    // Reaction Aliases
    public static final int intSBOTermForKNOWN_TRANSITION_OMITTED = 397;
    public static final int intSBOTermForSTATE_TRANSITION = 176;
    public static final int intSBOTermForTRANSCRIPTION = 183;
    public static final int intSBOTermForTRANSLATION = 184;
    public static final int intSBOTermForTRANSPORT = 185;
    public static final int intSBOTermForHETERODIMER_ASSOCIATION = 177; // non-covalent binding
    public static final int intSBOTermForDISSOCIATION = 180;
    public static final int intSBOTermForTRUNCATION = 178; // Cleavage
    public static final int intSBOTermForUNKNOWN_TRANSITION = 396;
    public static final int intSBOTermForReactant = 10;
    public static final int intSBOTermForProduct = 11;
    public static final int intSBOTermForModifier = 19;
    // Modifier Aliases
    public static final int intSBOTermForCATALYSIS = 13;
    public static final int intSBOTermForINHIBITION = 20;
    public static final int intSBOTermForMODULATION = 19;
    public static final int intSBOTermForPHYSICAL_STIMULATION = 21;
    public static final int intSBOTermForTRANSCRIPTIONAL_ACTIVATION = 459;
    public static final int intSBOTermForTRANSCRIPTIONAL_INHIBITION = 20;
    public static final int intSBOTermForTRANSLATIONAL_ACTIVATION = 459;
    public static final int intSBOTermForTRANSLATIONAL_INHIBITION = 20;
    public static final int intSBOTermForTRIGGER = 461;
    public static final int intSBOTermForUNKNOWN_CATALYSIS = 13;
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
}
