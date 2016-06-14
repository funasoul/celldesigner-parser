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
			this.name = reaction.getName();
			this.notes = reaction.getNotes();
			this.reversible = reaction.isReversible();
			
			this.annotation = reaction.getAnnotation();
			this.editPoints = annotation.getExtension().getEditPoints();
			this.editPointList = LayoutUtil.createEditPointsAsList(editPoints.getValue());
			
			if(annotation.getExtension().getListOfModification() != null && annotation.getExtension().getListOfModification().getModification() != null)
				this.modificationList = annotation.getExtension().getListOfModification().getModification();
		
			reactantWrapperList = createReactantWrapperList(listOfReactants.getSpeciesReference());
			productWrapperList = createProductWrapperList(listOfProducts.getSpeciesReference());
			
			if(listOfModifiers != null)
				modifierWrapperList = createModifierWrapperList(listOfModifiers.getModifierSpeciesReference());
			else 
				isSetModifiers = false;
				
		}
		
	   public String getName() {
           return annotation.getExtension().getName();
       }
	   
       public void setName(String value) {
           annotation.getExtension().setName(value);
       }
       
       /**
        * 
        * @return
        * String
        * TODO
        */
       public String getReactionType() {
           return annotation.getExtension().getReactionType();
       }
       
       /**
        * 
        * @param value
        * void
        * TODO
        */
       public void setReactionType(String value) {
           annotation.getExtension().setReactionType(value);
       }

       /**
        * 
        * @return
        * BaseReactants
        * TODO
        */
       public List<BaseReactant> getBaseReactants() {
    	   List<BaseReactant> brList = annotation.getExtension().getBaseReactants().getBaseReactant(); 
           for(BaseReactant br: brList)
        	   if(br.getLinkAnchor() == null || br.getLinkAnchor().getPosition() == null){
        		   LinkAnchor anchor = new LinkAnchor();
        		   anchor.setPosition("INACTIVE");
        		   br.setLinkAnchor(anchor);
        	   }
        		   
    	   return brList;
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
    	   List<BaseProduct> bpList = annotation.getExtension().getBaseProducts().getBaseProduct(); 
           for(BaseProduct bp: bpList)
        	   if(bp.getLinkAnchor() == null || bp.getLinkAnchor().getPosition() == null){
        		   LinkAnchor anchor = new LinkAnchor();
        		   anchor.setPosition("INACTIVE");
        		   bp.setLinkAnchor(anchor);
        	   }
        		   
    	   return bpList;
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
           return annotation.getExtension().getListOfReactantLinks().getReactantLink();
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
    	   annotation.getExtension().getListOfReactantLinks().getReactantLink().add(link);
       }

       /**
        * 
        * @param link
        * void
        * TODO
        */
       public void removeReactantLink(ReactantLink link){
    	   annotation.getExtension().getListOfReactantLinks().getReactantLink().remove(link);
       }
       
       /**
        * 
        * @return
        * ListOfProductLinks
        * TODO
        */
       public List<ProductLink> getListOfProductLinks() {
           return annotation.getExtension().getListOfProductLinks().getProductLink();
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
    	   annotation.getExtension().getListOfProductLinks().getProductLink().add(link);
       }

       /**
        * 
        * @param link
        * void
        * TODO
        */
       public void removeProductLink(ProductLink link){
    	   annotation.getExtension().getListOfProductLinks().getProductLink().remove(link);
       }

       /**
        * 
        * @return
        * ConnectScheme
        * TODO
        */
       public ConnectScheme getConnectScheme() {
           return annotation.getExtension().getConnectScheme();
       }

       /**
        * 
        * @param value
        * void
        * TODO
        */
       public void setConnectScheme(ConnectScheme value) {
    	   annotation.getExtension().setConnectScheme(value);
       }

       /**
        * 
        * @return
        * Offset
        * TODO
        */
       public Offset getOffset() {
    	   return annotation.getExtension().getOffset();
       }

       /**
        * 
        * @param value
        * void
        * TODO
        */
       public void setOffset(Offset value) {
    	   annotation.getExtension().setOffset(value);
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
    	   annotation.getExtension().getListOfModification().getModification().add(modification);
       }

       /**
        * 
        * @param modification
        * void
        * TODO
        */
       public void removeModification(Modification modification){
    	   annotation.getExtension().getListOfModification().getModification().remove(modification);
       }
       
       
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
    	   String index = annotation.getExtension().getConnectScheme().getRectangleIndex();
    	   if(index == null)
    		   return 0;
    	   
    	   return Integer.valueOf(index); 
       }
       
       /**
        * 
        * @param index
        * void
        * TODO
        */
       public void setRectangleIndex(int index){
    	   annotation.getExtension().getConnectScheme().setRectangleIndex(String.valueOf(index));
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
       public LinkTarget getLinkTargetByModifierId(String id){
    	   Modification m = getModificationByModifierId(id);
    	   List<LinkTarget> ltList = m.getLinkTarget();
    	   for(LinkTarget lt: ltList){
    		   if(lt.getSpecies().equals(id))
    			   return lt;
    	   }
    	   
    	   return null;
       }
}
