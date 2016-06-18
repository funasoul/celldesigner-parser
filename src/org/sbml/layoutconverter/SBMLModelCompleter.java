package org.sbml.layoutconverter;

import javax.swing.tree.TreeNode;

import org.sbml.jsbml.Compartment;
import org.sbml.jsbml.Event;
import org.sbml.jsbml.KineticLaw;
import org.sbml.jsbml.ListOf;
import org.sbml.jsbml.Model;
import org.sbml.jsbml.ModifierSpeciesReference;
import org.sbml.jsbml.Parameter;
import org.sbml.jsbml.Reaction;
import org.sbml.jsbml.SBMLDocument;
import org.sbml.jsbml.Species;
import org.sbml.jsbml.SpeciesReference;
import org.sbml.jsbml.Unit;
import org.sbml.jsbml.Unit.Kind;
import org.sbml.jsbml.UnitDefinition;

/**
 * @author Kaito Ii
 *
 * Date Created: Jun 9, 2016
 */

public class SBMLModelCompleter {
	
	static SBMLDocument document;
	static Model model;
	static final int level = 3;
	static final int version = 1;
	
	/**
	 * 
	 * @param doc
	 * @return
	 * SBMLDocument
	 * TODO
	 */
	public static SBMLDocument autoCompleteRequiredAttributes(SBMLDocument doc){
		document = doc;
		model = document.getModel();
		completeUnitDefinitions(model.getListOfUnitDefinitions());
		completeCompartments(model.getListOfCompartments());
		completeReactions(model.getListOfReactions());
		completeSpecies(model.getListOfSpecies());
		
		return document;
	}
	
	public static void autocomplete(TreeNode node){
		System.out.println(node.getClass());
		
		for(int i = 0; i < node.getChildCount(); i++){
			TreeNode n = node.getChildAt(i);
			     
			if(n instanceof Model){
				Model model = (Model) n;
				model.initDefaults(level, version);
			} else if(n instanceof Compartment){
				Compartment compartment = (Compartment) n;
				compartment.initDefaults(level, version);
			} else if(n instanceof Parameter){
				Parameter parameter = (Parameter) n;
				parameter.initDefaults(level, version);
			} else if(n instanceof Species){
				Species species = (Species) n;
				species.initDefaults(level, version);
			}  else if(n instanceof Unit){
				Unit unit = (Unit) n;
				unit.initDefaults(level, version);
			} else if(n instanceof Reaction){
				Reaction reaction = (Reaction) n;
				reaction.initDefaults(level, version);
			} else if(n instanceof Event){
				Event event = (Event) n;
				event.initDefaults(level, version);
			} else if(n instanceof KineticLaw){
				KineticLaw kineticLaw = (KineticLaw) n;
				kineticLaw.initDefaults();
			} 
			
			
			
			autocomplete(n);
		}
	}
	
	
	/**
	 * 
	 * @param unitDefinitions
	 * @return
	 * ListOf<UnitDefinition>
	 * TODO
	 */
	public static ListOf<UnitDefinition> completeUnitDefinitions(ListOf<UnitDefinition> unitDefinitions){
		for(UnitDefinition ud : unitDefinitions){
				ListOf<Unit> units = ud.getListOfUnits();
			for(Unit u: units){
				if(!u.isSetExponent())
					u.setExponent(1.0);;
				if(!u.isSetMultiplier())
					u.setMultiplier(1.0);
				if(!u.isSetKind())
					u.setKind(Kind.INVALID);
				if(!u.isSetScale())
					u.setScale(0);
			
			}
		}
		
		return unitDefinitions;
	}
	
	/**
	 * 
	 * @param compartments
	 * @return
	 * ListOf<Compartment>
	 * TODO
	 */
	public static ListOf<Compartment> completeCompartments(ListOf<Compartment> compartments){
		int i = 0;
		for(Compartment c: compartments){
			if(!c.isSetId())
				c.setId(c.getClass().getSimpleName() + i++);
			if(!c.isSetConstant())
				c.setConstant(true);
		}
		
		return compartments;
	}
	
	/**
	 * 
	 * @param species
	 * @return
	 * ListOf<Species>
	 * TODO
	 */
	public static ListOf<Species> completeSpecies(ListOf<Species> species){
		int i = 0;
		for(Species s: species){
			if(!s.isSetId())
				s.setId(s.getClass().getSimpleName() + i++);
			if(!s.isSetCompartment()){}
				s.setCompartment(model.getCompartment(0));
			if(!s.isSetHasOnlySubstanceUnits())
				s.setHasOnlySubstanceUnits(true);
			if(!s.isSetBoundaryCondition())
				s.setBoundaryCondition(true);
			if(!s.isSetConstant())
				s.setConstant(true);
		}
		
		return species;
	}
	
	/**
	 * 
	 * @param reactions
	 * @return
	 * ListOf<Reaction>
	 * TODO
	 */
	public static ListOf<Reaction> completeReactions(ListOf<Reaction> reactions){
		int i = 0;
		for(Reaction r: reactions){
			if(!r.isSetId())
				r.setId(r.getClass().getSimpleName() + i++);
			if(!r.isSetFast())
				r.setFast(true);
			if(!r.isSetReversible())
				r.setReversible(true);
			completeSpeciesReference(r.getListOfReactants());
			completeSpeciesReference(r.getListOfProducts());
			completeModifierSpeciesReference(r.getListOfModifiers());		
		}
		
		return reactions;
	}
	
	/**
	 * 
	 * @param speciesReference
	 * @return
	 * ListOf<SpeciesReference>
	 * TODO
	 */
	public static ListOf<SpeciesReference> completeSpeciesReference(ListOf<SpeciesReference> speciesReference){
		for(SpeciesReference sr: speciesReference){
			if(!sr.isSetSpecies())
				sr.setSpecies(model.getSpecies(0));
			if(!sr.isSetConstant())
				sr.setConstant(true);	
		}
		
		return speciesReference;
	}
	
	/**
	 * 
	 * @param modifierSpeciesReference
	 * @return
	 * ListOf<ModifierSpeciesReference>
	 * TODO
	 */
	public static ListOf<ModifierSpeciesReference> completeModifierSpeciesReference(ListOf<ModifierSpeciesReference> modifierSpeciesReference){
		for(ModifierSpeciesReference sr: modifierSpeciesReference){
			if(!sr.isSetSpecies())
				sr.setSpecies(model.getSpecies(0));
		}	
		
		return modifierSpeciesReference;
	}
}
