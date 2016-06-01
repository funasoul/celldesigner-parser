package org.sbml.layoutconverter;

import org.sbml.jsbml.SBMLDocument;

/**
 * @author Kaito Ii
 *
 * Date Created: May 26, 2016
 */

public class ModelUpgrader {
	
	public static SBMLDocument upgrade(SBMLDocument document){
		document.setLevelAndVersion(3, 1);
		
		return document;
	}
	
	public static void main(String[] args){
		
	}
}
