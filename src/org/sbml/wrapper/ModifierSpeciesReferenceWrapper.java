/*
 * 
 */
package org.sbml.wrapper;

import java.awt.geom.Point2D;
import java.util.List;

import org.sbml._2001.ns.celldesigner.Modification;
import org.sbml._2001.ns.celldesigner.SpeciesReferenceAnnotationType;
import org.sbml._2001.ns.celldesigner.SpeciesReferenceAnnotationType.Extension;
import org.sbml.layoutconverter.LayoutUtil;
import org.sbml.sbml.level2.version4.ModifierSpeciesReference;

// TODO: Auto-generated Javadoc
/**
 * The Class ModifierSpeciesReferenceWrapper.
 *
 * @author Kaito Ii
 * 
 * Date Created: May 24, 2016
 */

public class ModifierSpeciesReferenceWrapper extends ModifierSpeciesReference{

  /** The s ref. */
  private	ModifierSpeciesReference sRef;
  
  /** The model wrapper. */
  private	ModelWrapper modelWrapper;
  
  /** The species wrapper. */
  private	SpeciesWrapper speciesWrapper;
  
  /** The modification. */
  private	Modification modification;
  
  /** The reaction wrapper. */
  private	ReactionWrapper reactionWrapper;
  
  /** The type. */
  private	String type;
  
  /** The alias. */
  private	String alias;
  
  /** The edit point list. */
  private	List<Point2D.Double> editPointList;
  
  /** The target line index. */
  private	Point2D.Double targetLineIndex;

	/**
	 * Instantiates a new modifier species reference wrapper.
	 *
	 * @param sRef the s ref
	 * @param modelWrapper the model wrapper
	 * @param reactionWrapper the reaction wrapper
	 */
	public ModifierSpeciesReferenceWrapper(ModifierSpeciesReference sRef, ModelWrapper modelWrapper, ReactionWrapper reactionWrapper){
		 this.sRef =  sRef;
		 this.modelWrapper = modelWrapper;
		 this.reactionWrapper = reactionWrapper;
		 this.metaid = sRef.getMetaid();
		 this.notes = sRef.getNotes();
		 this.species = sRef.getSpecies();
		 this.speciesWrapper = modelWrapper.getSpeciesWrapperById(sRef.getSpecies());
		 	
		 this.annotation = sRef.getAnnotation();
		 
		 if(annotation != null)
			 setAnnotation();
		 else
			 initAnnotation();	 
	}

	/**
	 * Sets the annotation.
	 */
	void setAnnotation(){
		 this.alias = annotation.getExtension().getAlias();
		 
		 this.modification = reactionWrapper.getModificationByModifierId(species);
		 this.type = modification.getType();	
		 
		 if(modification.getEditPoints() != null)
			 this.editPointList = LayoutUtil.createEditPointsAsList(modification.getEditPoints());
		 if(modification.getTargetLineIndex() != null)
			setTargetLineIndex(modification.getTargetLineIndex());
		 
	}
	
	/**
	 * Inits the annotation.
	 */
	void initAnnotation(){
		this.annotation = new SpeciesReferenceAnnotationType();
		sRef.setAnnotation(annotation);
		annotation.setExtension(new Extension());
		
		this.alias = new String();
		annotation.getExtension().setAlias(alias);
		
		setModification(new Modification());
		setType(new String());
	}
	
	/**
	 * Gets the alias.
	 *
	 * @return String
	 * TODO
	 */
	public String getAlias() {
        return alias;
    }

	/**
	 * Sets the alias.
	 *
	 * @param value void
	 * TODO
	 */
	public void setAlias(String value) {
        annotation.getExtension().setAlias(value);
        this.alias = value;
    }

	/**
	 * Gets the aliased.
	 *
	 * @return SpeciesWrapper
	 * TODO
	 */
	public SpeciesWrapper getAliased(){
		return speciesWrapper;
	}

	/**
	 * Sets the modification.
	 *
	 * @param value the new modification
	 * @return Modification
	 * TODO
	 */
	public void setModification(Modification value){
		modification = value;
	}

	/**
	 * Gets the modification.
	 *
	 * @return Modification
	 * TODO
	 */
	public Modification getModification(){
		return modification;
	}

	/**
	 * Sets the type.
	 *
	 * @param type void
	 * TODO
	 */
	public void setType(String type){
		this.type = type;
		modification.setType(type);
	}

	/**
	 * Gets the type.
	 *
	 * @return String
	 * TODO
	 */
	public String getType(){
		return type;
	}

	/**
	 * Sets the target line index.
	 *
	 * @param index void
	 * TODO
	 */
	public void setTargetLineIndex(String index){
		 String[] points = index.split(",",0);
		 targetLineIndex = new Point2D.Double();
		 targetLineIndex.x = Double.valueOf(points[0]);
		 targetLineIndex.y = Double.valueOf(points[1]);
	}

	/**
	 * Gets the target line index.
	 *
	 * @return Point2D.Double
	 * TODO
	 */
	public Point2D.Double getTargetLineIndex(){
		return targetLineIndex;
	}

	/**
	 * Sets the edits the points.
	 *
	 * @param editPoints void
	 * TODO
	 */
	public void setEditPoints(List<Point2D.Double> editPoints){
		this.editPointList = editPoints;
	}

	/**
	 * Gets the edits the points.
	 *
	 * @return List<Point2D.Double>
	 * TODO
	 */
	public List<Point2D.Double> getEditPoints(){
		return editPointList;
	}
}
