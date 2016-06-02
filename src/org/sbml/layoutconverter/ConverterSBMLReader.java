package org.sbml.layoutconverter;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import javax.xml.stream.XMLStreamException;

import org.sbml.jsbml.SBMLDocument;
import org.sbml.jsbml.SBMLReader;
import org.sbml.jsbml.ext.layout.LayoutConstants;

/**
 * @author Kaito Ii
 *
 * Date Created: May 28, 2016
 */

public class ConverterSBMLReader {

	final static String cellDesignerNS = "http://www.sbml.org/2001/ns/celldesigner";
	
	
	public static SBMLDocument read(File file) throws XMLStreamException, IOException{
		SBMLDocument document = SBMLReader.read(file);
		if(document.getLevel() < 3)
			document = ModelUpgrader.upgrade(document);

		int level = document.getLevel();
		int version = document.getVersion();
		
		document.enablePackage(LayoutConstants.getNamespaceURI(level, version), true);
		document.setPackageRequired(LayoutConstants.getNamespaceURI(level, version), true);

		//		document.enablePackage(GroupsConstants.getNamespaceURI(level, version), true);
		
		
		return document;
	}
	
	public static boolean isSetCellDesignerNameSpace(SBMLDocument document){
		Map<String, String> nsMap = document.getDeclaredNamespaces();
		return nsMap.containsValue(cellDesignerNS);
	}
}
