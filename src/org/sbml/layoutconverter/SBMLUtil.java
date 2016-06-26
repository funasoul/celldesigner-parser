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
