/*
 * 
 */
package org.sbml.layoutconverter;

import java.util.Iterator;

import org.sbml.jsbml.ListOf;
import org.sbml.jsbml.Reaction;
import org.sbml.jsbml.SBMLDocument;
import org.sbml.jsbml.SBase;

// TODO: Auto-generated Javadoc
/**
 * The Class ModelUpgrader.
 *
 * @author Kaito Ii
 * 
 * Date Created: May 26, 2016
 */

public class ModelUpgrader {
	
	/**
	 * Upgrade.
	 *
	 * @param document the document
	 * @return SBMLDocument
	 * TODO
	 */
	public static SBMLDocument upgrade(SBMLDocument document){
		document.setLevelAndVersion(3, 1);
		if(!isSetSBOTerm(document))
			System.err.println("Missing sboterms");
		
		return document;
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
