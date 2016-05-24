package org.sbml.wrapper;

import org.sbml._2001.ns.celldesigner.BaseProducts;
import org.sbml._2001.ns.celldesigner.BaseReactants;
import org.sbml._2001.ns.celldesigner.ConnectScheme;
import org.sbml._2001.ns.celldesigner.EditPoints;
import org.sbml._2001.ns.celldesigner.Line;
import org.sbml._2001.ns.celldesigner.ListOfModification;
import org.sbml._2001.ns.celldesigner.ListOfProductLinks;
import org.sbml._2001.ns.celldesigner.ListOfReactantLinks;
import org.sbml._2001.ns.celldesigner.Offset;
import org.sbml._2001.ns.celldesigner.ReactionAnnotationType;
import org.sbml.sbml.level2.version4.Reaction;

/**
 * @author Kaito Ii
 *
 * Date Created: May 24, 2016
 */

/**
 * @author
 */
public class ReactionWrapper extends Reaction{
				
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
       public BaseReactants getBaseReactants() {
           return annotation.getExtension().getBaseReactants();
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
       public BaseProducts getBaseProducts() {
           return annotation.getExtension().getBaseProducts();
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
       public ListOfReactantLinks getListOfReactantLinks() {
           return annotation.getExtension().getListOfReactantLinks();
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
        * @return
        * ListOfProductLinks
        * TODO
        */
       public ListOfProductLinks getListOfProductLinks() {
           return annotation.getExtension().getListOfProductLinks();
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
       public ListOfModification getListOfModification() {
           return annotation.getExtension().getListOfModification();
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

}
