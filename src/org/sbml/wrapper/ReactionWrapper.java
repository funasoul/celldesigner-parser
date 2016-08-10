/*
 * 
 */
package org.sbml.wrapper;

import java.awt.geom.Point2D;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.sbml._2001.ns.celldesigner.BaseProduct;
import org.sbml._2001.ns.celldesigner.BaseProducts;
import org.sbml._2001.ns.celldesigner.BaseReactant;
import org.sbml._2001.ns.celldesigner.BaseReactants;
import org.sbml._2001.ns.celldesigner.ConnectScheme;
import org.sbml._2001.ns.celldesigner.EditPoints;
import org.sbml._2001.ns.celldesigner.Line;
import org.sbml._2001.ns.celldesigner.LineType2;
import org.sbml._2001.ns.celldesigner.LinkAnchor;
import org.sbml._2001.ns.celldesigner.LinkTarget;
import org.sbml._2001.ns.celldesigner.ListOfModification;
import org.sbml._2001.ns.celldesigner.ListOfProductLinks;
import org.sbml._2001.ns.celldesigner.ListOfReactantLinks;
import org.sbml._2001.ns.celldesigner.Modification;
import org.sbml._2001.ns.celldesigner.Offset;
import org.sbml._2001.ns.celldesigner.ProductLink;
import org.sbml._2001.ns.celldesigner.ReactantLink;
import org.sbml._2001.ns.celldesigner.ReactionAnnotationType;
import org.sbml._2001.ns.celldesigner.ReactionAnnotationType.Extension;
import org.sbml.jsbml.ext.layout.CurveSegment.Type;
import org.sbml.layoutconverter.LayoutUtil;
import org.sbml.sbml.level2.version4.ModifierSpeciesReference;
import org.sbml.sbml.level2.version4.Reaction;
import org.sbml.sbml.level2.version4.SpeciesReference;

// TODO: Auto-generated Javadoc
/**
 * The Class ReactionWrapper.
 *
 * @author Kaito Ii
 * 
 * Date Created: May 24, 2016
 */

public class ReactionWrapper extends Reaction{

  /** The reaction. */
  private Reaction reaction;
  
  /** The model wrapper. */
  private ModelWrapper modelWrapper;
  
  /** The reactant wrapper list. */
  private List<SpeciesReferenceWrapper> reactantWrapperList;
  
  /** The product wrapper list. */
  private List<SpeciesReferenceWrapper> productWrapperList;
  
  /** The modifier wrapper list. */
  private List<ModifierSpeciesReferenceWrapper> modifierWrapperList;
  
  /** The is set modifiers. */
  private boolean isSetModifiers = true;
  
  /** The edit point list. */
  private List<Point2D.Double> editPointList;
  
  /** The edit points. */
  private EditPoints editPoints;
  
  /** The modification list. */
  private List<Modification> modificationList;
  
  /** The type. */
  private String type;
  
  /** The base reactants. */
  private List<BaseReactant> baseReactants;
  
  /** The base products. */
  private List<BaseProduct> baseProducts;
  
  /** The reactant links. */
  private List<ReactantLink> reactantLinks;
  
  /** The product links. */
  private List<ProductLink> productLinks;
  
  /** The connect scheme. */
  private ConnectScheme connectScheme;
  
  /** The offset. */
  private Offset offset;
  
  /** The rectangle index. */
  private int rectangleIndex;

	/**
	 * Instantiates a new reaction wrapper.
	 *
	 * @param reaction the reaction
	 * @param modelWrapper the model wrapper
	 */
	public ReactionWrapper(Reaction reaction, ModelWrapper modelWrapper) {
		this.reaction = reaction;
		this.modelWrapper = modelWrapper;
		this.fast = reaction.isFast();
		this.id = reaction.getId();
		this.kineticLaw = reaction.getKineticLaw();
		this.listOfModifiers = reaction.getListOfModifiers();
		this.listOfProducts = reaction.getListOfProducts();
		this.listOfReactants = reaction.getListOfReactants();
		this.metaid = reaction.getMetaid();
		this.name = reaction.getName(); // same as annotation.getExtension().getName()?
		this.notes = reaction.getNotes();
		this.reversible = reaction.isReversible();	
		
		reactantWrapperList = createReactantWrapperList(listOfReactants.getSpeciesReference());
		productWrapperList = createProductWrapperList(listOfProducts.getSpeciesReference());
		
		this.annotation = reaction.getAnnotation();
		if(annotation != null)
			setAnnotations();
		else
			initAnnotations();
		
	}

	/**
	 * Sets the annotations.
	 */
	void setAnnotations(){
		this.type = annotation.getExtension().getReactionType();

		this.editPoints = annotation.getExtension().getEditPoints();
		if (editPoints != null)
			this.editPointList = LayoutUtil.createEditPointsAsList(editPoints.getValue());
		else
			this.editPointList = new ArrayList<Point2D.Double>();

		if (annotation.getExtension().getListOfModification() != null)
			this.modificationList = annotation.getExtension().getListOfModification().getModification();

		if (annotation.getExtension().getBaseReactants() != null) {
			baseReactants = annotation.getExtension().getBaseReactants().getBaseReactant();
			for (BaseReactant br : baseReactants) {
				if (br.getLinkAnchor() == null || br.getLinkAnchor().getPosition() == null) {
					LinkAnchor anchor = new LinkAnchor();
					anchor.setPosition("INACTIVE");
					br.setLinkAnchor(anchor);
				}
			}
		}

		if (annotation.getExtension().getBaseProducts() != null) {
			baseProducts = annotation.getExtension().getBaseProducts().getBaseProduct();
			for (BaseProduct bp : baseProducts) {
				if (bp.getLinkAnchor() == null || bp.getLinkAnchor().getPosition() == null) {
					LinkAnchor anchor = new LinkAnchor();
					anchor.setPosition("INACTIVE");
					bp.setLinkAnchor(anchor);
				}
			}
		}

		if (annotation.getExtension().getListOfReactantLinks() != null)
			reactantLinks = annotation.getExtension().getListOfReactantLinks().getReactantLink();
		else
			reactantLinks = new ArrayList<ReactantLink>();

		if (annotation.getExtension().getListOfProductLinks() != null)
			productLinks = annotation.getExtension().getListOfProductLinks().getProductLink();
		else
			productLinks = new ArrayList<ProductLink>();

		connectScheme = annotation.getExtension().getConnectScheme();
		offset = annotation.getExtension().getOffset();

		if (connectScheme != null && connectScheme.getRectangleIndex() != null)
			rectangleIndex = Integer.valueOf(annotation.getExtension().getConnectScheme().getRectangleIndex());
		
		if (listOfModifiers != null)
			modifierWrapperList = createModifierWrapperList(listOfModifiers.getModifierSpeciesReference());
		else
			isSetModifiers = false;
	}
	
	/**
	 * Inits the annotations.
	 */
	void initAnnotations(){
		annotation = new ReactionAnnotationType();
		reaction.setAnnotation(annotation);
		annotation.setExtension(new Extension());

		setReactionType(new String());
		
		this.editPointList = new ArrayList<Point2D.Double>();
	
		if (listOfModifiers != null)
			modifierWrapperList = createModifierWrapperList(listOfModifiers.getModifierSpeciesReference());
		else
			isSetModifiers = false;
		
		if (listOfModifiers != null){
			setListOfModification(new ListOfModification());
			this.modificationList = annotation.getExtension().getListOfModification().getModification();
			for(ModifierSpeciesReferenceWrapper msrw: modifierWrapperList){
				Modification m = new Modification();
				m.setAliases(msrw.getAlias());
				m.setModifiers(msrw.getSpecies());
				Line line = new Line();
				line.setWidth(new BigDecimal(1.0d));
				line.setColor("ff000000");
				m.setLine(line);
				modificationList.add(m);
				msrw.setModification(m);
			}
		}
		
		setBaseReactants(new BaseReactants());
		baseReactants = annotation.getExtension().getBaseReactants().getBaseReactant();
		
		setBaseProducts(new BaseProducts());
		baseProducts = annotation.getExtension().getBaseProducts().getBaseProduct();
		
		this.connectScheme = new ConnectScheme();
		connectScheme.setConnectPolicy("direct");
		setConnectScheme(new ConnectScheme());
		setOffset(new Offset());

		Line line = new Line();
		line.setWidth(new BigDecimal(1.0d));
		line.setColor("ff000000");
		setLine(line);
		
		this.reaction.setKineticLaw(null); // reset the kinetic law later
		
	}
	
	/**
	 * Creates the edit point list.
	 *
	 * @param editPointList the edit point list
	 * @return the list
	 */
	public List<String> createEditPointList(List<String> editPointList){
		editPoints = new EditPoints();
		setEditPoints(editPoints);
		editPoints.getValue().addAll(editPointList);
		
		return editPoints.getValue();
	}
	
	/**
	 * Creates the reactant link.
	 *
	 * @return the list
	 */
	public List<ReactantLink> createReactantLinks(){
		if(annotation.getExtension().getListOfReactantLinks() == null )
			setListOfReactantLinks(new ListOfReactantLinks());
		reactantLinks = annotation.getExtension().getListOfReactantLinks().getReactantLink();
	
		return reactantLinks;
	}
	
	/**
	 * Creates the product link.
	 *
	 * @return the list
	 */
	public List<ProductLink> createProductLinks(){
		if(annotation.getExtension().getListOfProductLinks() == null )
			setListOfProductLinks(new ListOfProductLinks());
		productLinks = annotation.getExtension().getListOfProductLinks().getProductLink();

		return productLinks;
	}
	
	   /* (non-Javadoc)
	    * @see org.sbml.sbml.level2.version4.OriginalReaction#getName()
   	 	*/
   	public String getName() {
           return name;
       }

       /* (non-Javadoc)
        * @see org.sbml.sbml.level2.version4.OriginalReaction#setName(java.lang.String)
        */
       public void setName(String name) {
           annotation.getExtension().setName(name);
           this.name = name;
       }

       /**
        * Gets the reaction type.
        *
        * @return String
        * TODO
        */
       public String getReactionType() {
           return type;
       }

       /**
        * Sets the reaction type.
        *
        * @param type void
        * TODO
        */
       public void setReactionType(String type) {
           annotation.getExtension().setReactionType(type);
           this.type = type;
       }

       /**
        * Gets the base reactants.
        *
        * @return BaseReactants
        * TODO
        */
       public List<BaseReactant> getBaseReactants() {
    	   return baseReactants;
       }

       /**
        * Sets the base reactants.
        *
        * @param value void
        * TODO
        */
       public void setBaseReactants(BaseReactants value) {
           annotation.getExtension().setBaseReactants(value);
       }

       /**
        * Gets the base products.
        *
        * @return BaseProducts
        * TODO
        */
       public List<BaseProduct> getBaseProducts() {
    	   return baseProducts;
       }

       /**
        * Sets the base products.
        *
        * @param value void
        * TODO
        */
       public void setBaseProducts(BaseProducts value) {
    	   annotation.getExtension().setBaseProducts(value);
       }

       /**
        * Gets the list of reactant links.
        *
        * @return ListOfReactantLinks
        * TODO
        */
       public List<ReactantLink> getListOfReactantLinks() {
           return reactantLinks;
       }

       /**
        * Sets the list of reactant links.
        *
        * @param value void
        * TODO
        */
       public void setListOfReactantLinks(ListOfReactantLinks value) {
    	   annotation.getExtension().setListOfReactantLinks(value);
       }

       /**
        * Adds the reactant link.
        *
        * @param link void
        * TODO
        */
       public void addReactantLink(ReactantLink link){
    	  reactantLinks.add(link);
       }

       /**
        * Removes the reactant link.
        *
        * @param link void
        * TODO
        */
       public void removeReactantLink(ReactantLink link){
    	  reactantLinks.remove(link);
       }

       /**
        * Creates the reactant link.
        *
        * @param saw the saw
        */
       public void createReactantLink(SpeciesAliasWrapper saw){
    	   ReactantLink link = new ReactantLink();
    	   link.setReactant(saw.getSpecies());
    	   link.setAlias(saw.getId());
    	   LinkAnchor anchor = new LinkAnchor();
    	   anchor.setPosition("INACTIVE");
    	   link.setLinkAnchor(anchor);
    	   
    	   LineType2 line = new LineType2();
    	   line.setWidth(new BigDecimal(1.0d));
    	   line.setColor("ff000000");
    	   link.setLine(line);
    	   
    	   reactantLinks.add(link);
       }
       
       /**
        * Sets the reactant link line type.
        *
        * @param id the id
        * @param type the type
        */
       public void setReactantLinkLineType(String id, Type type){
    	   for(ReactantLink link : reactantLinks){
    		   if(link.getReactant().equals(id))
    			   link.getLine().setType(LayoutUtil.convertLineTypeToCDString(type.name()));
    	   }
       }
      
       /**
        * Gets the list of product links.
        *
        * @return ListOfProductLinks
        * TODO
        */
       public List<ProductLink> getListOfProductLinks() {
           return productLinks;
       }

       /**
        * Sets the list of product links.
        *
        * @param value void
        * TODO
        */
       public void setListOfProductLinks(ListOfProductLinks value) {
    	   annotation.getExtension().setListOfProductLinks(value);
       }

       /**
        * Adds the product link.
        *
        * @param link void
        * TODO
        */
       public void addProductLink(ProductLink link){
    	   productLinks.add(link);
       }

       /**
        * Removes the product link.
        *
        * @param link void
        * TODO
        */
       public void removeProductLink(ProductLink link){
    	   productLinks.remove(link);
       }

       /**
        * Creates the product link.
        *
        * @param saw the saw
        */
       public void createProductLink(SpeciesAliasWrapper saw){
    	   ProductLink link = new ProductLink();
    	   link.setProduct(saw.getSpecies());
    	   link.setAlias(saw.getId());
    	   LinkAnchor anchor = new LinkAnchor();
    	   anchor.setPosition("INACTIVE");
    	   link.setLinkAnchor(anchor);
    	   
    	   LineType2 line = new LineType2();
    	   line.setWidth(new BigDecimal(1.0d));
    	   line.setColor("ff000000");
    	   link.setLine(line);
    	   productLinks.add(link);
       }
       
       /**
        * Sets the product link line type.
        *
        * @param id the id
        * @param type the type
        */
       public void setProductLinkLineType(String id, Type type){
    	   for(ProductLink link : productLinks){
    		   if(link.getProduct().equals(id))
    			   link.getLine().setType(LayoutUtil.convertLineTypeToCDString(type.name()));
    	   }
       }
       
       /**
        * Gets the connect scheme.
        *
        * @return ConnectScheme
        * TODO
        */
       public ConnectScheme getConnectScheme() {
           return connectScheme;
       }

       /**
        * Sets the connect scheme.
        *
        * @param connectScheme the new connect scheme
        */
       public void setConnectScheme(ConnectScheme connectScheme) {
    	   annotation.getExtension().setConnectScheme(connectScheme);
    	   this.connectScheme = connectScheme;
       }

       /**
        * Gets the offset.
        *
        * @return Offset
        * TODO
        */
       public Offset getOffset() {
    	   return offset;
       }

       /**
        * Sets the offset.
        *
        * @param offset void
        * TODO
        */
       public void setOffset(Offset offset) {
    	   annotation.getExtension().setOffset(offset);
    	   this.offset = offset;
       }

       /**
        * Checks if is sets the edit points.
        *
        * @return boolean
        * TODO
        */
       public boolean isSetEditPoints(){
    	   if(editPoints == null)
    		   return false;

    	   return true;
       }

       /**
        * Gets the edits the points.
        *
        * @return EditPoints
        * TODO
        */
       public EditPoints getEditPoints() {
    	   return annotation.getExtension().getEditPoints();
       }

       /**
        * Sets the edits the points.
        *
        * @param value void
        * TODO
        */
       public void setEditPoints(EditPoints value) {
    	   annotation.getExtension().setEditPoints(value);
       }

       /**
        * Gets the edits the points as list.
        *
        * @return List<Point2D>
        * TODO
        */
       public List<Point2D.Double> getEditPointsAsList(){
    	   return editPointList;
       }

       /**
        * Sets the num.
        *
        * @param num0 the num 0
        * @param num1 the num 1
        * @param num2 the num 2
        */
       public void setNum(int num0, int num1, int num2){
    	   setNum(num0, num1, num2, 0);
       }
       
       /**
        * Sets the num.
        *
        * @param num0 the num 0
        * @param num1 the num 1
        * @param num2 the num 2
        * @param tshapeIndex the tshape index
        */
       public void setNum(int num0, int num1, int num2, int tshapeIndex){
    	   editPoints.setNum0((short)num0);
    	   editPoints.setNum1((short)num1);
    	   editPoints.setNum2((short)num2);
    	   editPoints.setTShapeIndex((short)tshapeIndex);
       }
       
       /**
        * Sets the t shape index.
        *
        * @param index the new t shape index
        */
       public void setTShapeIndex(int index){
    	   editPoints.setTShapeIndex((short) index);
       }
       
       /**
        * Gets the line.
        *
        * @return Line
        * TODO
        */
       public Line getLine() {
           return annotation.getExtension().getLine();
       }

       /**
        * Sets the line.
        *
        * @param value void
        * TODO
        */
       public void setLine(Line value) {
    	   annotation.getExtension().setLine(value);
       }

       /**
        * Gets the list of modification.
        *
        * @return ListOfModification
        * TODO
        */
       public List<Modification> getListOfModification() {
           return modificationList;
       }

       /**
        * Sets the list of modification.
        *
        * @param value void
        * TODO
        */
       public void setListOfModification(ListOfModification value) {
    	   annotation.getExtension().setListOfModification(value);
       }

       /**
        * Adds the modification.
        *
        * @param modification void
        * TODO
        */
       public void addModification(Modification modification){
    	  modificationList.add(modification);
       }

       /**
        * Removes the modification.
        *
        * @param modification void
        * TODO
        */
       public void removeModification(Modification modification){
    	   modificationList.remove(modification);
       }

       /**
        * Gets the modification by modifier id.
        *
        * @param id the id
        * @return Modification
        * TODO
        */
       public Modification getModificationByModifierId(String id){
    	   if(modificationList == null ) return null;
    	   
    	   for(Modification m : modificationList){
    		   if(m.getModifiers().equals(id))
    			   return m;
    	   }

    	   return null;
       }

       /**
        * Creates the reactant wrapper list.
        *
        * @param srList the sr list
        * @return List<SpeciesReferenceWrapper>
        * TODO
        */
       public List<SpeciesReferenceWrapper> createReactantWrapperList(List<SpeciesReference> srList){
    	   List<SpeciesReferenceWrapper> srwList = new ArrayList<SpeciesReferenceWrapper>(srList.size());
    	   for(SpeciesReference sr : srList)
    		   srwList.add(new SpeciesReferenceWrapper(sr, modelWrapper));

    	   return srwList;
       }

       /**
        * Gets the list of reactant wrapper.
        *
        * @return List<SpeciesReferenceWrapper>
        * TODO
        */
       public List<SpeciesReferenceWrapper> getListOfReactantWrapper(){
    	   return reactantWrapperList;
       }

       /**
        * Gets the reactant wrapper by id.
        *
        * @param id the id
        * @return the reactant wrapper by id
        */
       public SpeciesReferenceWrapper getReactantWrapperById(String id){
    	   for(SpeciesReferenceWrapper srw : reactantWrapperList)
    		  if(srw.getSpecies().equals(id))
    			  return srw;
    	   
    		   return null;
       }
       
       /**
        * Creates the product wrapper list.
        *
        * @param srList the sr list
        * @return List<SpeciesReferenceWrapper>
        * TODO
        */
       public List<SpeciesReferenceWrapper> createProductWrapperList(List<SpeciesReference> srList){
    	   List<SpeciesReferenceWrapper> srwList = new ArrayList<SpeciesReferenceWrapper>(srList.size());
    	   for(SpeciesReference sr : srList)
    		   srwList.add(new SpeciesReferenceWrapper(sr, modelWrapper));

    	   return srwList;
       }

       /**
        * Gets the product wrapper by id.
        *
        * @param id the id
        * @return the product wrapper by id
        */
       public SpeciesReferenceWrapper getProductWrapperById(String id){
    	   for(SpeciesReferenceWrapper srw : productWrapperList)
    		  if(srw.getSpecies().equals(id))
    			  return srw;
    	   
    		   return null;
       }

       /**
        * Gets the list of product wrapper.
        *
        * @return List<SpeciesReferenceWrapper>
        * TODO
        */
       public List<SpeciesReferenceWrapper> getListOfProductWrapper(){
    	   return productWrapperList;
       }

       /**
        * Creates the modifier wrapper list.
        *
        * @param msrList the msr list
        * @return List<ModifierSpeciesReferenceWrapper>
        * TODO
        */
       public List<ModifierSpeciesReferenceWrapper> createModifierWrapperList(List<ModifierSpeciesReference> msrList){
    	   List<ModifierSpeciesReferenceWrapper> msrwList = new ArrayList<ModifierSpeciesReferenceWrapper>(msrList.size());
    	   for(ModifierSpeciesReference msr : msrList)
    		   msrwList.add(new ModifierSpeciesReferenceWrapper(msr, modelWrapper, this));

    	   return msrwList;
       }

       /**
        * Gets the list of modifier wrapper.
        *
        * @return List<SpeciesReferenceWrapper>
        * TODO
        */
       public List<ModifierSpeciesReferenceWrapper> getListOfModifierWrapper(){
    	   return modifierWrapperList;
       }

       /**
        * Gets the modifiert wrapper by id.
        *
        * @param id the id
        * @return the modifier wrapper by id
        */
       public ModifierSpeciesReferenceWrapper getModifierWrapperById(String id){
    	   for(ModifierSpeciesReferenceWrapper msrw : modifierWrapperList)
    		  if(msrw.getSpecies().equals(id))
    			  return msrw;
    	   
    		   return null;
       }
       
       /**
        * Checks if is sets the modifier.
        *
        * @return boolean
        * TODO
        */
       public boolean isSetModifier(){
    	   return isSetModifiers;
       }

       /**
        * Gets the modifier type by modifier id.
        *
        * @param id the id
        * @return String
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
        * Gets the link target by modifier.
        *
        * @param modification the modification
        * @return LinkTarget
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

       /**
        * Gets the rectangle index.
        *
        * @return int
        * TODO
        */
       public int getRectangleIndex(){
    	   return rectangleIndex;
       }

       /**
        * Sets the rectangle index.
        *
        * @param index void
        * TODO
        */
       public void setRectangleIndex(int index){
    	   annotation.getExtension().getConnectScheme().setRectangleIndex(String.valueOf(index));
    	   this.rectangleIndex = index;
       }

	/**
	 * Creates the base reactant.
	 *
	 * @param srw the srw
	 */
	public void createBaseReactant(SpeciesReferenceWrapper srw) {
		BaseReactant br = new BaseReactant();
		br.setSpecies(srw.getSpecies());
		br.setAlias(srw.getAlias());
		LinkAnchor anchor = new LinkAnchor();
		anchor.setPosition("INACTIVE");
		br.setLinkAnchor(anchor);
		baseReactants.add(br);
	}

	/**
	 * Creates the base product.
	 *
	 * @param srw the srw
	 */
	public void createBaseProduct(SpeciesReferenceWrapper srw) {
		BaseProduct bp = new BaseProduct();
		bp.setSpecies(srw.getSpecies());
		bp.setAlias(srw.getAlias());
		LinkAnchor anchor = new LinkAnchor();
		anchor.setPosition("INACTIVE");
		bp.setLinkAnchor(anchor);
		baseProducts.add(bp);
	}

}
