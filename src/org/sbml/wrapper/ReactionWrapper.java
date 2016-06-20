package org.sbml.wrapper;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import org.sbml._2001.ns.celldesigner.BaseProduct;
import org.sbml._2001.ns.celldesigner.BaseProducts;
import org.sbml._2001.ns.celldesigner.BaseReactant;
import org.sbml._2001.ns.celldesigner.BaseReactants;
import org.sbml._2001.ns.celldesigner.ConnectScheme;
import org.sbml._2001.ns.celldesigner.EditPoints;
import org.sbml._2001.ns.celldesigner.Line;
import org.sbml._2001.ns.celldesigner.LinkAnchor;
import org.sbml._2001.ns.celldesigner.LinkTarget;
import org.sbml._2001.ns.celldesigner.ListOfModification;
import org.sbml._2001.ns.celldesigner.ListOfProductLinks;
import org.sbml._2001.ns.celldesigner.ListOfReactantLinks;
import org.sbml._2001.ns.celldesigner.Modification;
import org.sbml._2001.ns.celldesigner.Offset;
import org.sbml._2001.ns.celldesigner.ProductLink;
import org.sbml._2001.ns.celldesigner.ReactantLink;
import org.sbml.layoutconverter.LayoutUtil;
import org.sbml.sbml.level2.version4.ModifierSpeciesReference;
import org.sbml.sbml.level2.version4.Reaction;
import org.sbml.sbml.level2.version4.SpeciesReference;

/**
 * @author Kaito Ii
 *
 * Date Created: May 24, 2016
 */

public class ReactionWrapper extends Reaction{
				
		Reaction reaction;
		ModelWrapper modelWrapper;
		List<SpeciesReferenceWrapper> reactantWrapperList;
		List<SpeciesReferenceWrapper> productWrapperList;
		List<ModifierSpeciesReferenceWrapper> modifierWrapperList;
		boolean isSetModifiers = true;
		List<Point2D.Double> editPointList;
		EditPoints editPoints;
		List<Modification> modificationList;
		String type;
		List<BaseReactant> baseReactants;
		List<BaseProduct> baseProducts;
		List<ReactantLink> reactantLinks;
		List<ProductLink> productLinks;
		ConnectScheme connectScheme;
		Offset offset;
		int rectangleIndex;
		
		public ReactionWrapper(Reaction reaction, ModelWrapper modelWrapper){
			this.reaction = reaction;
			this.modelWrapper = modelWrapper;
			this.fast = reaction.isFast();
			this.id = reaction.getId();
			this.kineticLaw = reaction.getKineticLaw();
			this.listOfModifiers = reaction.getListOfModifiers();
			this.listOfProducts = reaction.getListOfProducts();
			this.listOfReactants = reaction.getListOfReactants();
			this.metaid = reaction.getMetaid();
			this.name = reaction.getName();		//same as annotation.getExtension().getName()?
			this.notes = reaction.getNotes();
			this.reversible = reaction.isReversible();

			
			this.annotation = reaction.getAnnotation();
			this.type  = annotation.getExtension().getReactionType();
			
			this.editPoints = annotation.getExtension().getEditPoints();
			if(editPoints != null)
				this.editPointList = LayoutUtil.createEditPointsAsList(editPoints.getValue());
			else
				this.editPointList = new ArrayList<Point2D.Double>();
			
			if(annotation.getExtension().getListOfModification() != null && annotation.getExtension().getListOfModification().getModification() != null)
				this.modificationList = annotation.getExtension().getListOfModification().getModification();
		
			reactantWrapperList = createReactantWrapperList(listOfReactants.getSpeciesReference());
			productWrapperList = createProductWrapperList(listOfProducts.getSpeciesReference());
			
			if(listOfModifiers != null)
				modifierWrapperList = createModifierWrapperList(listOfModifiers.getModifierSpeciesReference());
			else 
				isSetModifiers = false;
			
			if(annotation.getExtension().getBaseReactants() != null) {
				baseReactants = annotation.getExtension().getBaseReactants().getBaseReactant();
				for (BaseReactant br : baseReactants) {
					if (br.getLinkAnchor() == null || br.getLinkAnchor().getPosition() == null) {
						LinkAnchor anchor = new LinkAnchor();
						anchor.setPosition("INACTIVE");
						br.setLinkAnchor(anchor);
					}
				}
			}
			
			if(annotation.getExtension().getBaseProducts() != null) {	
				baseProducts = annotation.getExtension().getBaseProducts().getBaseProduct(); 
				for(BaseProduct bp: baseProducts){
					if(bp.getLinkAnchor() == null || bp.getLinkAnchor().getPosition() == null){
						LinkAnchor anchor = new LinkAnchor();
						anchor.setPosition("INACTIVE");
						bp.setLinkAnchor(anchor);
					}
				}
			}
			
			if(annotation.getExtension().getListOfReactantLinks() != null)
				reactantLinks = annotation.getExtension().getListOfReactantLinks().getReactantLink();
		
			if(annotation.getExtension().getListOfProductLinks() != null)
				productLinks = annotation.getExtension().getListOfProductLinks().getProductLink();
			
			connectScheme = annotation.getExtension().getConnectScheme();
			offset = annotation.getExtension().getOffset();
		
			if(connectScheme != null && connectScheme.getRectangleIndex() != null)
				rectangleIndex = Integer.valueOf(annotation.getExtension().getConnectScheme().getRectangleIndex());

		}
		
	   public String getName() {
           return name;
       }
	   
       public void setName(String name) {
           annotation.getExtension().setName(name);
           this.name = name;
       }
       
       /**
        * 
        * @return
        * String
        * TODO
        */
       public String getReactionType() {
           return type;
       }
       
       /**
        * 
        * @param type
        * void
        * TODO
        */
       public void setReactionType(String type) {
           annotation.getExtension().setReactionType(type);
           this.type = type;
       }

       /**
        * 
        * @return
        * BaseReactants
        * TODO
        */
       public List<BaseReactant> getBaseReactants() {	   
    	   return baseReactants;
       }

       /**
        * 
        * @param value
        * void
        * TODO
        */
       public void setBaseReactants(BaseReactants value) {
           annotation.getExtension().setBaseReactants(value);
       }

       /**
        * 
        * @return
        * BaseProducts
        * TODO
        */
       public List<BaseProduct> getBaseProducts() {
    	   return baseProducts;
       }

       /**
        * 
        * @param value
        * void
        * TODO
        */
       public void setBaseProducts(BaseProducts value) {
    	   annotation.getExtension().setBaseProducts(value);
       }

       /**
        * 
        * @return
        * ListOfReactantLinks
        * TODO
        */
       public List<ReactantLink> getListOfReactantLinks() {
           return reactantLinks;
       }

       /**
        * 
        * @param value
        * void
        * TODO
        */
       public void setListOfReactantLinks(ListOfReactantLinks value) {
    	   annotation.getExtension().setListOfReactantLinks(value);
       }

       /**
        * 
        * @param link
        * void
        * TODO
        */
       public void addReactantLink(ReactantLink link){
    	  reactantLinks.add(link);
       }

       /**
        * 
        * @param link
        * void
        * TODO
        */
       public void removeReactantLink(ReactantLink link){
    	  reactantLinks.remove(link);
       }
       
       /**
        * 
        * @return
        * ListOfProductLinks
        * TODO
        */
       public List<ProductLink> getListOfProductLinks() {
           return productLinks;
       }

       /**
        * 
        * @param value
        * void
        * TODO
        */
       public void setListOfProductLinks(ListOfProductLinks value) {
    	   annotation.getExtension().setListOfProductLinks(value);
       }
       
       /**
        * 
        * @param link
        * void
        * TODO
        */
       public void addProductLink(ProductLink link){
    	   productLinks.add(link);
       }

       /**
        * 
        * @param link
        * void
        * TODO
        */
       public void removeProductLink(ProductLink link){
    	   productLinks.remove(link);
       }

       /**
        * 
        * @return
        * ConnectScheme
        * TODO
        */
       public ConnectScheme getConnectScheme() {
           return connectScheme;
       }

       /**
        * 
        * @param value
        * void
        * TODO
        */
       public void setConnectScheme(ConnectScheme connectScheme) {
    	   annotation.getExtension().setConnectScheme(connectScheme);
    	   this.connectScheme = connectScheme;
       }

       /**
        * 
        * @return
        * Offset
        * TODO
        */
       public Offset getOffset() {
    	   return offset;
       }

       /**
        * 
        * @param offset
        * void
        * TODO
        */
       public void setOffset(Offset offset) {
    	   annotation.getExtension().setOffset(offset);
    	   this.offset = offset;
       }

       /**
        * 
        * @return
        * boolean
        * TODO
        */
       public boolean isSetEditPoints(){
    	   if(editPoints == null)
    		   return false;
    	   
    	   return true;
       }  
       
       /**
        * 
        * @return
        * EditPoints
        * TODO
        */
       public EditPoints getEditPoints() {
    	   return annotation.getExtension().getEditPoints();
       }

       /**
        * 
        * @param value
        * void
        * TODO
        */
       public void setEditPoints(EditPoints value) {
    	   annotation.getExtension().setEditPoints(value);
       }

       /**
        * 
        * @return
        * List<Point2D>
        * TODO
        */
       public List<Point2D.Double> getEditPointsAsList(){
    	   return editPointList;
       }
       
       /**
        * 
        * @return
        * Line
        * TODO
        */
       public Line getLine() {
           return annotation.getExtension().getLine();
       }

       /**
        * 
        * @param value
        * void
        * TODO
        */
       public void setLine(Line value) {
    	   annotation.getExtension().setLine(value);
       }

       /**
        * 
        * @return
        * ListOfModification
        * TODO
        */
       public List<Modification> getListOfModification() {
           return modificationList;
       }

       /**
        * 
        * @param value
        * void
        * TODO
        */
       public void setListOfModification(ListOfModification value) {
    	   annotation.getExtension().setListOfModification(value);
       }

       /**
        * 
        * @param modification
        * void
        * TODO
        */
       public void addModification(Modification modification){
    	  modificationList.add(modification);
       }

       /**
        * 
        * @param modification
        * void
        * TODO
        */
       public void removeModification(Modification modification){
    	   modificationList.remove(modification);
       }
       
       /**
        * 
        * @param id
        * @return
        * Modification
        * TODO
        */
       public Modification getModificationByModifierId(String id){
    	   for(Modification m : modificationList){
    		   if(m.getModifiers().equals(id))
    			   return m;
    	   }
    	   
    	   return null;
       }
       
       /**
        * 
        * @param srList
        * @return
        * List<SpeciesReferenceWrapper>
        * TODO
        */
       public List<SpeciesReferenceWrapper> createReactantWrapperList(List<SpeciesReference> srList){
    	   List<SpeciesReferenceWrapper> srwList = new ArrayList<SpeciesReferenceWrapper>(srList.size());
    	   for(SpeciesReference sr : srList)
    		   srwList.add(new SpeciesReferenceWrapper(sr, modelWrapper));
    		   
    	   return srwList;
       }

       /**
        * 
        * @return
        * List<SpeciesReferenceWrapper>
        * TODO
        */
       public List<SpeciesReferenceWrapper> getListOfReactantWrapper(){
    	   return reactantWrapperList;
       }
       
       /**
        * 
        * @param srList
        * @return
        * List<SpeciesReferenceWrapper>
        * TODO
        */
       public List<SpeciesReferenceWrapper> createProductWrapperList(List<SpeciesReference> srList){
    	   List<SpeciesReferenceWrapper> srwList = new ArrayList<SpeciesReferenceWrapper>(srList.size());
    	   for(SpeciesReference sr : srList)
    		   srwList.add(new SpeciesReferenceWrapper(sr, modelWrapper));
    		   
    	   return srwList;
       }
       
       /**
        * 
        * @return
        * int
        * TODO
        */
       public int getRectangleIndex(){
    	   return rectangleIndex;
       }
       
       /**
        * 
        * @param index
        * void
        * TODO
        */
       public void setRectangleIndex(int index){
    	   annotation.getExtension().getConnectScheme().setRectangleIndex(String.valueOf(index));
    	   this.rectangleIndex = index;
       }
       
       /**
        * 
        * @return
        * List<SpeciesReferenceWrapper>
        * TODO
        */
       public List<SpeciesReferenceWrapper> getListOfProductWrapper(){
    	   return productWrapperList;
       }
       
       /**
        * 
        * @param msrList
        * @return
        * List<ModifierSpeciesReferenceWrapper>
        * TODO
        */
       public List<ModifierSpeciesReferenceWrapper> createModifierWrapperList(List<ModifierSpeciesReference> msrList){
    	   List<ModifierSpeciesReferenceWrapper> msrwList = new ArrayList<ModifierSpeciesReferenceWrapper>(msrList.size());
    	   for(ModifierSpeciesReference msr : msrList)
    		   msrwList.add(new ModifierSpeciesReferenceWrapper(msr, modelWrapper, this));
    		   
    	   return msrwList;
       }
       
       /**
        * 
        * @return
        * List<SpeciesReferenceWrapper>
        * TODO
        */
       public List<ModifierSpeciesReferenceWrapper> getListOfModifierWrapper(){
    	   return modifierWrapperList;
       }
       
       /**
        * 
        * @return
        * boolean
        * TODO
        */
       public boolean isSetModifier(){
    	   return isSetModifiers;
       }
       
       /**
        * 
        * @param id
        * @return
        * String
        * TODO
        */
       public String getModifierTypeByModifierId(String id){
    	   List<Modification> mList = getListOfModification();
    	   
    	   for(Modification m : mList)
    		   if(m.getModifiers().equals(id))
    			   return m.getType();
    	   
    	   return "";
       }
       
       /**
        * 
        * @param id
        * @return
        * LinkTarget
        * TODO
        */
       public LinkTarget getLinkTargetByModifier(Modification modification){
    	   Modification m = getModificationByModifierId(modification.getModifiers());
    	   List<LinkTarget> ltList = m.getLinkTarget();
    	   for(LinkTarget lt: ltList){
    		   if(lt.getSpecies().equals(id))
    			   return lt;
    	   }
    	   
    	   // link target missing = positioned at inactive
    	   LinkTarget lt = new LinkTarget();
    	   lt.setSpecies(m.getModifiers());
    	   lt.setAlias(m.getAliases());
    	   LinkAnchor la = new LinkAnchor();
    	   la.setPosition("INACTIVE");
    	   lt.setLinkAnchor(la);
    	   
    	   return lt;
       }
}
