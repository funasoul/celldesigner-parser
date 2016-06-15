package org.sbml.wrapper;

import java.awt.geom.Point2D;
import java.util.List;

import org.sbml._2001.ns.celldesigner.Modification;
import org.sbml.layoutconverter.LayoutUtil;
import org.sbml.sbml.level2.version4.ModifierSpeciesReference;

/**
 * @author Kaito Ii
 *
 * Date Created: May 24, 2016
 */

public class ModifierSpeciesReferenceWrapper extends ModifierSpeciesReference{
	
	ModifierSpeciesReference sRef;
	ModelWrapper modelWrapper;
	SpeciesWrapper speciesWrapper;
	Modification modification;
	ReactionWrapper reactionWrapper;
	String type;
	String alias;
	List<Point2D.Double> editPointList;
	Point2D.Double targetLineIndex;
	
	/**
	 * 
	 * @param sRef
	 * @param modelWrapper
	 * @param reactionWrapper
	 */
	public ModifierSpeciesReferenceWrapper(ModifierSpeciesReference sRef, ModelWrapper modelWrapper, ReactionWrapper reactionWrapper){
		 this.sRef =  sRef;
		 this.modelWrapper = modelWrapper;
		 this.reactionWrapper = reactionWrapper;
		 this.annotation = sRef.getAnnotation();
		 this.alias = annotation.getExtension().getAlias();
		 this.metaid = sRef.getMetaid();
		 this.notes = sRef.getNotes();
		 this.species = sRef.getSpecies();
		 this.speciesWrapper = modelWrapper.getSpeciesWrapperById(sRef.getSpecies());
		 this.modification = reactionWrapper.getModificationByModifierId(species);
	
		 this.type = modification.getType();
		 if(modification.getEditPoints() != null)
			 this.editPointList = LayoutUtil.createEditPointsAsList(modification.getEditPoints());
		 if(modification.getTargetLineIndex() != null)
			setTargetLineIndex(modification.getTargetLineIndex());
	}
	
	/**
	 * 
	 * @return
	 * String
	 * TODO
	 */
	public String getAlias() {
        return alias;
    }
	
	/**
	 * 
	 * @param value
	 * void
	 * TODO
	 */
	public void setAlias(String value) {
        annotation.getExtension().setAlias(value);
        this.alias = value;
    }
	
	/**
	 * 
	 * @return
	 * SpeciesWrapper
	 * TODO
	 */
	public SpeciesWrapper getAliased(){
		return speciesWrapper;
	}
	
	/**
	 * 
	 * @return
	 * Modification
	 * TODO
	 */
	public void setModification(Modification value){
		modification = value;
	}
	
	/**
	 * 
	 * @return
	 * Modification
	 * TODO
	 */
	public Modification getModification(){
		return modification;
	}
	
	/**
	 * 
	 * @param type
	 * void
	 * TODO
	 */
	public void setType(String type){
		this.type = type;
	}
	
	/**
	 * 
	 * @return
	 * String
	 * TODO
	 */
	public String getType(){
		return type;
	}
	
	/**
	 * 
	 * @param index
	 * void
	 * TODO
	 */
	public void setTargetLineIndex(String index){
		 String[] points = index.split(",",0);
		 targetLineIndex = new Point2D.Double();
		 targetLineIndex.x = Double.valueOf(points[0]);
		 targetLineIndex.y = Double.valueOf(points[1]);
	}
	
	/**
	 * 
	 * @return
	 * Point2D.Double
	 * TODO
	 */
	public Point2D.Double getTargetLineIndex(){
		return targetLineIndex;
	}
	
	/**
	 * 
	 * @param editPoints
	 * void
	 * TODO
	 */
	public void setEditPoints(List<Point2D.Double> editPoints){
		this.editPointList = editPoints;
	}
	
	/**
	 * 
	 * @return
	 * List<Point2D.Double>
	 * TODO
	 */
	public List<Point2D.Double> getEditPoints(){
		return editPointList;
	}
}
