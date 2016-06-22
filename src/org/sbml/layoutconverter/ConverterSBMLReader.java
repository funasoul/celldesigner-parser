/*
 * 
 */
package org.sbml.layoutconverter;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import javax.xml.stream.XMLStreamException;

import org.sbml.jsbml.SBMLDocument;
import org.sbml.jsbml.SBMLReader;
import org.sbml.jsbml.ext.layout.LayoutConstants;

// TODO: Auto-generated Javadoc
/**
 * The Class ConverterSBMLReader.
 *
 * @author Kaito Ii
 * 
 * Date Created: May 28, 2016
 */

public class ConverterSBMLReader {

	/** The Constant cellDesignerNS. */
	final static String cellDesignerNS = "http://www.sbml.org/2001/ns/celldesigner";
	
	/**
	 * Read.
	 *
	 * @param file the file
	 * @return the SBML document
	 * @throws XMLStreamException the XML stream exception
	 * @throws IOException SBMLDocument
	 * TODO
	 */
	public static SBMLDocument read(File file) throws XMLStreamException, IOException{
		SBMLDocument document = SBMLReader.read(file);
		if(document.getLevel() < 3)
			document = ModelUpgrader.upgrade(document);
		
		document = SBMLModelCompleter.autoCompleteRequiredAttributes(document);
		
		int level = document.getLevel();
		int version = document.getVersion();
		
		document.enablePackage(LayoutConstants.getNamespaceURI(level, version), true);
		document.setPackageRequired(LayoutConstants.getNamespaceURI(level, version), true);
		//document.enablePackage(GroupsConstants.getNamespaceURI(level, version), true);
		
		
		return document;
	}
	
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
}
