package org.sbml.wrapper;

import java.util.ArrayList;
import java.util.List;

import org.sbml._2001.ns.celldesigner.BaseProduct;
import org.sbml._2001.ns.celldesigner.BaseProducts;
import org.sbml._2001.ns.celldesigner.BaseReactant;
import org.sbml._2001.ns.celldesigner.BaseReactants;
import org.sbml._2001.ns.celldesigner.ConnectScheme;
import org.sbml._2001.ns.celldesigner.EditPoints;
import org.sbml._2001.ns.celldesigner.Line;
import org.sbml._2001.ns.celldesigner.ListOfModification;
import org.sbml._2001.ns.celldesigner.ListOfProductLinks;
import org.sbml._2001.ns.celldesigner.ListOfReactantLinks;
import org.sbml._2001.ns.celldesigner.Modification;
import org.sbml._2001.ns.celldesigner.Offset;
import org.sbml._2001.ns.celldesigner.ProductLink;
import org.sbml._2001.ns.celldesigner.ReactantLink;
import org.sbml.sbml.level2.version4.ModifierSpeciesReference;
import org.sbml.sbml.level2.version4.Reaction;
import org.sbml.sbml.level2.version4.SpeciesReference;

/**
 * @author Kaito Ii
 *
 * Date Created: May 24, 2016
 */

/**
 * @author
 */
public class ReactionWrapper extends Reaction{
				
		Reaction reaction;
		ModelWrapper modelWrapper;
		List<SpeciesReferenceWrapper> reactantWrapperList;
		List<SpeciesReferenceWrapper> productWrapperList;
		List<ModifierSpeciesReferenceWrapper> modifierWrapperList;
		
		public ReactionWrapper(Reaction reaction, ModelWrapper modelWrapper){
			this.reaction = reaction;
			this.modelWrapper = modelWrapper;
			this.annotation = reaction.getAnnotation();
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
			
			reactantWrapperList = createReactantWrapperList(listOfReactants.getSpeciesReference());
			productWrapperList = createProductWrapperList(listOfProducts.getSpeciesReference());
			//modifierWrapperList = createModifierWrapperList(listOfModifiers.getModifierSpeciesReference());					
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
       public String getReaction() {
           return annotation.getExtension().getReaction();
       }
       
       /**
        * 
        * @param value
        * void
        * TODO
        */
       public void setReaction(String value) {
           annotation.getExtension().setReaction(value);
       }

       /**
        * 
        * @return
        * BaseReactants
        * TODO
        */
       public List<BaseReactant> getBaseReactants() {
           return annotation.getExtension().getBaseReactants().getBaseReactant();
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
           return annotation.getExtension().getBaseProducts().getBaseProduct();
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
           return annotation.getExtension().getListOfModification().getModification();
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
       
       public List<SpeciesReferenceWrapper> createReactantWrapperList(List<SpeciesReference> srList){
    	   List<SpeciesReferenceWrapper> srwList = new ArrayList<SpeciesReferenceWrapper>(srList.size());
    	   for(SpeciesReference sr : srList)
    		   srwList.add(new SpeciesReferenceWrapper(sr, modelWrapper));
    		   
    	   return srwList;
       }
       
       public List<SpeciesReferenceWrapper> createProductWrapperList(List<SpeciesReference> srList){
    	   List<SpeciesReferenceWrapper> srwList = new ArrayList<SpeciesReferenceWrapper>(srList.size());
    	   for(SpeciesReference sr : srList)
    		   srwList.add(new SpeciesReferenceWrapper(sr, modelWrapper));
    		   
    	   return srwList;
       }
       
       public List<ModifierSpeciesReferenceWrapper> createModifierWrapperList(List<ModifierSpeciesReference> msrList){
    	   List<ModifierSpeciesReferenceWrapper> msrwList = new ArrayList<ModifierSpeciesReferenceWrapper>(msrList.size());
    	   for(ModifierSpeciesReference msr : msrList)
    		   msrwList.add(new ModifierSpeciesReferenceWrapper(msr, modelWrapper));
    		   
    	   return msrwList;
       }
}
