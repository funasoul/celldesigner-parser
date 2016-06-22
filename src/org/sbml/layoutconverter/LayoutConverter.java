/*
 * 
 */
package org.sbml.layoutconverter;

import java.awt.geom.Point2D;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLStreamException;

import org.sbml._2001.ns.celldesigner.BaseProduct;
import org.sbml._2001.ns.celldesigner.BaseReactant;
import org.sbml._2001.ns.celldesigner.LinkTarget;
import org.sbml._2001.ns.celldesigner.Modification;
import org.sbml._2001.ns.celldesigner.ProductLink;
import org.sbml._2001.ns.celldesigner.ReactantLink;
import org.sbml.jsbml.ListOf;
import org.sbml.jsbml.Model;
import org.sbml.jsbml.SBMLDocument;
import org.sbml.jsbml.SBMLError;
import org.sbml.jsbml.SBMLErrorLog;
import org.sbml.jsbml.SBMLException;
import org.sbml.jsbml.SBMLWriter;
import org.sbml.jsbml.TidySBMLWriter;
import org.sbml.jsbml.ext.layout.BoundingBox;
import org.sbml.jsbml.ext.layout.CompartmentGlyph;
import org.sbml.jsbml.ext.layout.Curve;
import org.sbml.jsbml.ext.layout.Dimensions;
import org.sbml.jsbml.ext.layout.Layout;
import org.sbml.jsbml.ext.layout.LayoutModelPlugin;
import org.sbml.jsbml.ext.layout.LineSegment;
import org.sbml.jsbml.ext.layout.Point;
import org.sbml.jsbml.ext.layout.ReactionGlyph;
import org.sbml.jsbml.ext.layout.SpeciesGlyph;
import org.sbml.jsbml.ext.layout.SpeciesReferenceGlyph;
import org.sbml.jsbml.ext.layout.SpeciesReferenceRole;
import org.sbml.jsbml.ext.layout.TextGlyph;
import org.sbml.wrapper.CompartmentAliasWrapper;
import org.sbml.wrapper.CompartmentWrapper;
import org.sbml.wrapper.ComplexSpeciesAliasWrapper;
import org.sbml.wrapper.ModelWrapper;
import org.sbml.wrapper.ModifierSpeciesReferenceWrapper;
import org.sbml.wrapper.ObjectFactory;
import org.sbml.wrapper.ReactionWrapper;
import org.sbml.wrapper.SpeciesAliasWrapper;
import org.sbml.wrapper.SpeciesReferenceWrapper;


// TODO: Auto-generated Javadoc
/**
 * The Class LayoutConverter.
 *
 * @author Kaito Ii
 * 
 * Date Created: May 26, 2016
 */

public class LayoutConverter {
	/** The m wrapper. */
	private	ModelWrapper mWrapper;
	
	/** The document. */
	private	SBMLDocument document;
	
	/** The model. */
	private	Model model;
	
	/** The layout. */
	private	Layout layout;

	/** The Constant DEFAULT_SPECIES_WIDTH. */
	static final double DEFAULT_SPECIES_WIDTH = 80.0;
	
	/** The Constant DEFAULT_SPECIES_HEIGHT. */
	static final double DEFAULT_SPECIES_HEIGHT = 40.0;
	
	/** The Constant DEFAULT_SPECIES_DEPTH. */
	static final double DEFAULT_SPECIES_DEPTH = 1.0;

	/**
	 * Instantiates a new layout converter.
	 *
	 * @param file the file
	 * @throws JAXBException the JAXB exception
	 * @throws XMLStreamException the XML stream exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public LayoutConverter(File file) throws JAXBException, XMLStreamException, IOException{
		mWrapper = ObjectFactory.unmarshalSBML(file);
		document = ConverterSBMLReader.read(file);
		model = document.getModel();
		LayoutModelPlugin mplugin = (LayoutModelPlugin)(model.getPlugin("layout"));
		layout = mplugin.createLayout();
	}

	/**
	 * void
	 * TODO.
	 */
	public void print(){
		try {
	    	SBMLWriter.write(document, new File("CD_" + model.getId() + ".xml"), ' ', (short) 2);
	    	String docStr = new TidySBMLWriter().writeSBMLToString(document);
	    	System.out.println(docStr);
		} catch (SBMLException e) {
			e.printStackTrace();
		} catch (XMLStreamException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * void
	 * TODO.
	 *
	 * @return the SBML document
	 */
	public SBMLDocument convertToLayout(){
		convertModelToLayout(mWrapper);
		convertCompartmentsToLayout(mWrapper.getListOfCompartmentAliasWrapper());
		convertComplexAliasToLayout(mWrapper.getListOfComplexSpeciesAliasWrapper());
		convertSpeciesAliasToLayout(mWrapper.getListOfSpeciesAliasWrapper());
		convertReactionsToLayout(mWrapper.getListOfReactionWrapper());

		return document;
	}

	/**
	 * Convert compartments to layout.
	 *
	 * @param cawList the caw list
	 */
	public void convertCompartmentsToLayout(List<CompartmentAliasWrapper> cawList){
		CompartmentWrapper cw = mWrapper.getCompartmentWrapperById("default");
		if(cw != null){
			CompartmentGlyph cg = layout.createCompartmentGlyph("CompartmentGlyph_" + cw.getId());
			cg.setCompartment(cw.getId());
			BoundingBox bb = cg.createBoundingBox();
			Dimensions dimension = bb.createDimensions();
			dimension.setWidth(mWrapper.getW());
			dimension.setHeight(mWrapper.getH());
			dimension.setDepth(1d);
			bb.createPosition(0, 0, 0);

			TextGlyph tg = layout.createTextGlyph("TextGlyph_" + cw.getId());
			tg.setOriginOfText(cw.getId());
			tg.setGraphicalObject(cg);
			BoundingBox bb2 = tg.createBoundingBox();;
			Dimensions dimension2 = bb2.createDimensions();
			dimension2.setWidth(cw.getId().length() * 3);
			dimension2.setHeight(10);
			dimension.setDepth(1d);
			Point point2 = bb2.createPosition();
			point2.setX(mWrapper.getW() / 2 - cw.getId().length() * 3 / 2);
			point2.setY(mWrapper.getH() - 10);
			point2.setZ(0d);

		}

		cawList = ModelWrapper.reorderCompartmentAccordingToPosition(cawList);

		for(CompartmentAliasWrapper caw : cawList){
			CompartmentGlyph cg = layout.createCompartmentGlyph("CompartmentGlyph_" + caw.getId());
			cg.setCompartment(caw.getCompartment());
			BoundingBox bb = cg.createBoundingBox();
			Dimensions dimension = bb.createDimensions();
			dimension.setWidth(caw.getW());
			dimension.setHeight(caw.getH());
			dimension.setDepth(1d);
			Point point = bb.createPosition();
			point.setX(caw.getX());
			point.setY(caw.getY());
			point.setZ(0d);

			TextGlyph tg = layout.createTextGlyph("TextGlyph_" + caw.getId());
			tg.setOriginOfText(caw.getCompartment());
			tg.setGraphicalObject(cg);
			BoundingBox bb2 = tg.createBoundingBox();;
			Dimensions dimension2 = bb2.createDimensions();
			dimension2.setWidth(caw.getId().length() * 3);
			dimension2.setHeight(10);
			dimension.setDepth(1d);
			Point point2 = bb2.createPosition();
			point2.setX(caw.getNameX());
			point2.setY(caw.getNameY());
			point2.setZ(0d);
		}
	}

	/**
	 * Convert model to layout.
	 *
	 * @param mWrapper void
	 * TODO
	 */
	public void convertModelToLayout(ModelWrapper mWrapper){
		layout.setId("Layout_" + model.getId());
		layout.createDimensions(mWrapper.getW(), mWrapper.getH(), 1d);

	}

	/**
	 * Convert reactions to layout.
	 *
	 * @param rList void
	 * TODO
	 */
	public void convertReactionsToLayout(List<ReactionWrapper> rList){
		for(ReactionWrapper rw: rList){
			ReactionGlyph rg = layout.createReactionGlyph("ReactionGlyph_" + rw.getId());
			rg.setReference(rw.getId());
			ListOf<SpeciesReferenceGlyph> srgList = createSpeciesReferenceGlyph(rg, rw);
			List<BaseReactant> brsList = rw.getBaseReactants();
			List<BaseProduct> prsList = rw.getBaseProducts();
			List<Point2D.Double> editPointList = rw.getEditPointsAsList();
			int rectangleIndex = rw.getRectangleIndex();
			BoundingBox reactionBB = rg.createBoundingBox();
			reactionBB.setDimensions(new Dimensions(0, 0, 0, LayoutUtil.DEFAULTSBMLLEVEL, LayoutUtil.DEFAULTSBMLVERSION));
			List<LineSegment> lsList = null;

			if(brsList.size() == 1 && prsList.size() == 1){
				BaseReactant br = brsList.get(0);
				BaseProduct bp = prsList.get(0);

				SpeciesAliasWrapper reactantsaw = mWrapper.getSpeciesAliasWrapperById(br.getAlias());
				SpeciesAliasWrapper productsaw = mWrapper.getSpeciesAliasWrapperById(bp.getAlias());

				Point startPoint  = LayoutUtil.createAdjustedPoint(reactantsaw.getX(), reactantsaw.getY(), reactantsaw.getW(), reactantsaw.getH(), br.getLinkAnchor().getPosition());
				Point endPoint = LayoutUtil.createAdjustedPoint(productsaw.getX(), productsaw.getY(), productsaw.getW(), productsaw.getH(), bp.getLinkAnchor().getPosition());
				Point reactantPoint = LayoutUtil.createCenterPoint(reactantsaw.getX(), reactantsaw.getY(), reactantsaw.getW(), reactantsaw.getH());
				Point productPoint = LayoutUtil.createCenterPoint(productsaw.getX(), productsaw.getY(), productsaw.getW(), productsaw.getH());
				lsList = LayoutUtil.createListOfLineSegment(startPoint, endPoint, reactantPoint, productPoint, editPointList, rectangleIndex);

				SpeciesReferenceGlyph srg = srgList.get("SpeciesReferenceGlyph_" + rg.getReaction() + "_" + reactantsaw.getId());
				Curve curve = srg.createCurve();
				for(int i = 0; i <= rectangleIndex; i++){
					curve.addCurveSegment(lsList.get(i));
				}

				srg = srgList.get("SpeciesReferenceGlyph_" + rg.getReaction() + "_" + productsaw.getId());
				curve = srg.createCurve();
				for(int i = rectangleIndex + 1; i < lsList.size(); i++){
					curve.addCurveSegment(lsList.get(i));
				}
				
				reactionBB.setPosition(new Point(curve.getCurveSegment(0).getStart()));
				
			} else if(rw.getReactionType().equals("HETERODIMER_ASSOCIATION")){ 	// two to one
				BaseReactant br1 = brsList.get(0);
				SpeciesAliasWrapper reactantsaw1 = mWrapper.getSpeciesAliasWrapperById(br1.getAlias());
				SpeciesGlyph sg1 = layout.getSpeciesGlyph("SpeciesGlyph_" + br1.getAlias());
				Point startPoint = LayoutUtil.createAdjustedPoint(reactantsaw1.getX(), reactantsaw1.getY(), reactantsaw1.getW(), reactantsaw1.getH(), br1.getLinkAnchor().getPosition());
				
				BaseReactant br2 = brsList.get(1);
				SpeciesAliasWrapper reactantsaw2 = mWrapper.getSpeciesAliasWrapperById(br2.getAlias());
				SpeciesGlyph sg2 = layout.getSpeciesGlyph("SpeciesGlyph_" + br2.getAlias());
				Point endPoint1 = LayoutUtil.createAdjustedPoint(reactantsaw2.getX(), reactantsaw2.getY(), reactantsaw2.getW(), reactantsaw2.getH(), br2.getLinkAnchor().getPosition());
				
				
				BaseProduct bp1 = prsList.get(0);
				SpeciesGlyph sg3 = layout.getSpeciesGlyph("SpeciesGlyph_" + bp1.getAlias());
				SpeciesAliasWrapper productsaw = mWrapper.getSpeciesAliasWrapperById(bp1.getAlias());
				Point endPoint2;
				if(productsaw != null){
					// not complex species
					endPoint2 = LayoutUtil.createAdjustedPoint(productsaw.getX(), productsaw.getY(), productsaw.getW(), productsaw.getH(), bp1.getLinkAnchor().getPosition());
				}  else {
					//product is complex species
					ComplexSpeciesAliasWrapper csaw = mWrapper.getComplexAliasWrapperById(bp1.getAlias());
					endPoint2 = LayoutUtil.createAdjustedPoint(csaw.getX(), csaw.getY(), csaw.getW(), csaw.getH(), bp1.getLinkAnchor().getPosition());
				}

				Point reactantPoint1 = LayoutUtil.createCenterPoint(sg1.getBoundingBox().getPosition().getX(), sg1.getBoundingBox().getPosition().getY(), sg1.getBoundingBox().getDimensions().getWidth(), sg1.getBoundingBox().getDimensions().getHeight());
				Point reactantPoint2 = LayoutUtil.createCenterPoint(sg2.getBoundingBox().getPosition().getX(), sg2.getBoundingBox().getPosition().getY(), sg2.getBoundingBox().getDimensions().getWidth(), sg2.getBoundingBox().getDimensions().getHeight());
				Point productPoint1 = LayoutUtil.createCenterPoint(sg3.getBoundingBox().getPosition().getX(), sg3.getBoundingBox().getPosition().getY(), sg3.getBoundingBox().getDimensions().getWidth(), sg3.getBoundingBox().getDimensions().getHeight());
				int num0 = rw.getEditPoints().getNum0(); 
				int num1 = rw.getEditPoints().getNum1(); 
				int num2 = rw.getEditPoints().getNum2(); 
				
			
				lsList = LayoutUtil.createListOfLineSegment(startPoint, endPoint1, endPoint2, reactantPoint1, reactantPoint2, productPoint1, editPointList, num0, num1, num2, rw.getReactionType(), rw.getEditPoints().getTShapeIndex());
				int reactant1 = num0;
				int reactant2 = num0 + num1 + 1;
			
				SpeciesReferenceGlyph srg = srgList.get("SpeciesReferenceGlyph_" + rg.getReaction() + "_" + reactantsaw1.getId());
				Curve curve = srg.createCurve();
				for(int i = 0; i <= reactant1; i++){
					curve.addCurveSegment(lsList.get(i));
				}
				
				srg = srgList.get("SpeciesReferenceGlyph_" + rg.getReaction() + "_" + reactantsaw2.getId());
				curve = srg.createCurve();
				for(int i = reactant1 + 1; i <= reactant2; i++){
					curve.addCurveSegment(lsList.get(i));
				}
				
				if(productsaw != null){
					srg = srgList.get("SpeciesReferenceGlyph_" + rg.getReaction() + "_" + productsaw.getId());
				} else {
					ComplexSpeciesAliasWrapper csaw = mWrapper.getComplexAliasWrapperById(bp1.getAlias());
					srg = srgList.get("SpeciesReferenceGlyph_" + rg.getReaction() + "_" + csaw.getId());
				}
				
				curve = srg.createCurve();
				for(int i = reactant2 + 1; i < lsList.size(); i++){
					curve.addCurveSegment(lsList.get(i));
				}
				
				int tshapeIndex = rw.getEditPoints().getTShapeIndex();
				LineSegment ls = lsList.get(num0 + 1 + num1 + 1 + tshapeIndex);
				reactionBB.setPosition(new Point(ls.getStart()));
			} else if(rw.getReactionType().equals("DISSOCIATION") || rw.getReactionType().equals("TRUNCATION")){ 	//one to two
				BaseReactant br1 = brsList.get(0);
				SpeciesGlyph sg1 = layout.getSpeciesGlyph("SpeciesGlyph_" + br1.getAlias());
				SpeciesAliasWrapper reactantsaw = mWrapper.getSpeciesAliasWrapperById(br1.getAlias());

				Point startPoint;
				if(reactantsaw != null){
					// not complex species
					startPoint = LayoutUtil.createAdjustedPoint(reactantsaw.getX(), reactantsaw.getY(), reactantsaw.getW(), reactantsaw.getH(), br1.getLinkAnchor().getPosition());
				}  else {
					//product is complex species
					ComplexSpeciesAliasWrapper csaw = mWrapper.getComplexAliasWrapperById(br1.getAlias());
					startPoint = LayoutUtil.createAdjustedPoint(csaw.getX(), csaw.getY(), csaw.getW(), csaw.getH(), br1.getLinkAnchor().getPosition());
				}
				
				BaseProduct bp1 = prsList.get(0);
				SpeciesAliasWrapper productsaw1 = mWrapper.getSpeciesAliasWrapperById(bp1.getAlias());
				SpeciesGlyph sg2 = layout.getSpeciesGlyph("SpeciesGlyph_" + bp1.getAlias());
				Point endPoint1 = LayoutUtil.createAdjustedPoint(productsaw1.getX(), productsaw1.getY(), productsaw1.getW(), productsaw1.getH(), bp1.getLinkAnchor().getPosition());

				BaseProduct bp2 = prsList.get(1);
				SpeciesAliasWrapper productsaw2 = mWrapper.getSpeciesAliasWrapperById(bp2.getAlias());
				SpeciesGlyph sg3 = layout.getSpeciesGlyph("SpeciesGlyph_" + bp2.getAlias());
				Point endPoint2 = LayoutUtil.createAdjustedPoint(productsaw2.getX(), productsaw2.getY(), productsaw2.getW(), productsaw2.getH(), bp2.getLinkAnchor().getPosition());

				Point reactantPoint =  LayoutUtil.createCenterPoint(sg1.getBoundingBox().getPosition().getX(), sg1.getBoundingBox().getPosition().getY(), sg1.getBoundingBox().getDimensions().getWidth(), sg1.getBoundingBox().getDimensions().getHeight());
				Point productPoint1 =  LayoutUtil.createCenterPoint(sg2.getBoundingBox().getPosition().getX(), sg2.getBoundingBox().getPosition().getY(), sg2.getBoundingBox().getDimensions().getWidth(), sg2.getBoundingBox().getDimensions().getHeight());
				Point productPoint2 =  LayoutUtil.createCenterPoint(sg3.getBoundingBox().getPosition().getX(), sg3.getBoundingBox().getPosition().getY(), sg3.getBoundingBox().getDimensions().getWidth(), sg3.getBoundingBox().getDimensions().getHeight());
				
				int num0 = rw.getEditPoints().getNum0(); 
				int num1 = rw.getEditPoints().getNum1(); 
				int num2 = rw.getEditPoints().getNum2(); 
				
				lsList = LayoutUtil.createListOfLineSegment(startPoint, endPoint1, endPoint2, reactantPoint, productPoint1, productPoint2, editPointList, num0, num1, num2, rw.getReactionType(), rw.getEditPoints().getTShapeIndex());
				int reactant1 = num0;
				int reactant2 = num0 + num1 + 1;
			
				SpeciesReferenceGlyph srg;
				if(reactantsaw != null){
					srg = srgList.get("SpeciesReferenceGlyph_" + rg.getReaction() + "_" + reactantsaw.getId());
				} else {
					ComplexSpeciesAliasWrapper csaw = mWrapper.getComplexAliasWrapperById(br1.getAlias());
					srg = srgList.get("SpeciesReferenceGlyph_" + rg.getReaction() + "_" + csaw.getId());
				}
				Curve curve = srg.createCurve();
				for(int i = 0; i <= reactant1; i++){
					curve.addCurveSegment(lsList.get(i));
				}

				srg = srgList.get("SpeciesReferenceGlyph_" + rg.getReaction() + "_" + productsaw1.getId());
				curve = srg.createCurve();
				for(int i = reactant1 + 1; i <= reactant2; i++){
					curve.addCurveSegment(lsList.get(i));
				}
				
				srg = srgList.get("SpeciesReferenceGlyph_" + rg.getReaction() + "_" + productsaw2.getId());
				curve = srg.createCurve();
				for(int i = reactant2 + 1; i < lsList.size(); i++){
					curve.addCurveSegment(lsList.get(i));
				}
				
				int tshapeIndex = rw.getEditPoints().getTShapeIndex();
				LineSegment ls = lsList.get(num0 - tshapeIndex + 1);
				reactionBB.setPosition(new Point(ls.getStart()));
			}

			List<ReactantLink> rlList = rw.getListOfReactantLinks();
			List<ProductLink> plList = rw.getListOfProductLinks();

			for(ReactantLink link : rlList){  //complexだと死ぬかも
				SpeciesAliasWrapper saw = mWrapper.getSpeciesAliasWrapperById(link.getAlias());
				Point startPoint  = LayoutUtil.createAdjustedPoint(saw.getX(), saw.getY(), saw.getW(), saw.getH(), link.getLinkAnchor().getPosition());
				Point endPoint = reactionBB.getPosition();
				editPointList= new ArrayList<Point2D.Double>();
				lsList = LayoutUtil.createListOfLineSegment(startPoint, endPoint, startPoint, endPoint, editPointList);

				SpeciesReferenceGlyph srg = srgList.get("SpeciesReferenceGlyph_" + rg.getReaction() + "_" + saw.getId());
				Curve curve = srg.createCurve();
				for(int i = 0; i <= rectangleIndex; i++){
					curve.addCurveSegment(lsList.get(i));
				}
			}

			for(ProductLink link : plList){
				SpeciesAliasWrapper saw = mWrapper.getSpeciesAliasWrapperById(link.getAlias());
				Point endPoint  = LayoutUtil.createAdjustedPoint(saw.getX(), saw.getY(), saw.getW(), saw.getH(), link.getLinkAnchor().getPosition());
				Point startPoint = reactionBB.getPosition();
				editPointList= new ArrayList<Point2D.Double>();
				lsList = LayoutUtil.createListOfLineSegment(startPoint, endPoint, startPoint, endPoint, editPointList);

				SpeciesReferenceGlyph srg = srgList.get("SpeciesReferenceGlyph_" + rg.getReaction() + "_" + saw.getId());
				Curve curve = srg.createCurve();
				for(int i = 0; i <= rectangleIndex; i++){
					curve.addCurveSegment(lsList.get(i));
				}
			}

			
			if(rw.isSetModifier()){
				List<ModifierSpeciesReferenceWrapper> msrwList = rw.getListOfModifierWrapper();
				for(ModifierSpeciesReferenceWrapper msrw : msrwList){
					Modification m = rw.getModificationByModifierId(msrw.getSpecies());
					editPointList = msrw.getEditPoints();
					SpeciesReferenceGlyph srg = rg.getSpeciesReferenceGlyph("ModifierSpeciesReferenceGlyph_" + rg.getReaction() + "_" + msrw.getAlias());
					SpeciesAliasWrapper modifiersaw = mWrapper.getSpeciesAliasWrapperById(msrw.getAlias());
					Point modifierPoint =  LayoutUtil.createCenterPoint(modifiersaw.getX(), modifiersaw.getY(), modifiersaw.getW(), modifiersaw.getH());
					
					LinkTarget lt = rw.getLinkTargetByModifier(m);

					Point startPoint = LayoutUtil.createAdjustedPoint(modifiersaw.getX(), modifiersaw.getY(), modifiersaw.getW(), modifiersaw.getH(), lt.getLinkAnchor().getPosition());
					Point reaction = lsList.get(rectangleIndex).getEnd().clone();
					lsList = LayoutUtil.createListOfLineSegment(startPoint, reaction, modifierPoint, reaction,editPointList, rectangleIndex);
					Curve curve = srg.createCurve();
					for(LineSegment ls : lsList){
						curve.addCurveSegment(ls);
					}
				}
			}


			//create textglyph near the first base reactant
			BaseReactant br = brsList.get(0);
			SpeciesAliasWrapper saw = mWrapper.getSpeciesAliasWrapperById(br.getAlias());
			Point point;
			if(saw != null){
				// not complex species
				point = LayoutUtil.createAdjustedPoint(saw.getX(), saw.getY(), saw.getW(), saw.getH(), br.getLinkAnchor().getPosition());
			}  else {
				//product is complex species
				ComplexSpeciesAliasWrapper csaw = mWrapper.getComplexAliasWrapperById(br.getAlias());
				point = LayoutUtil.createAdjustedPoint(csaw.getX(), csaw.getY(), csaw.getW(), csaw.getH(), br.getLinkAnchor().getPosition());
			}
			point = LayoutUtil.adjustPoint(point, br.getLinkAnchor().getPosition());

			TextGlyph tg = layout.createTextGlyph("TextGlyph_" + rw.getId());
			tg.setOriginOfText(rw.getId());
			tg.setGraphicalObject(rg);
			BoundingBox bb = tg.createBoundingBox(20,10,1d);
			bb.setPosition(point);
		}
	}

	/**
	 * Creates the species reference glyph.
	 *
	 * @param rg the rg
	 * @param rw the rw
	 * @return ListOf<SpeciesReferenceGlyph>
	 * TODO
	 */
	public ListOf<SpeciesReferenceGlyph> createSpeciesReferenceGlyph(ReactionGlyph rg, ReactionWrapper rw){
		ListOf<SpeciesReferenceGlyph> srgList = rg.getListOfSpeciesReferenceGlyphs();

		List<SpeciesReferenceWrapper> reactantList = rw.getListOfReactantWrapper();
		for(SpeciesReferenceWrapper srw : reactantList){
			SpeciesReferenceGlyph srg = rg.createSpeciesReferenceGlyph("SpeciesReferenceGlyph_" + rg.getReaction() + "_" + srw.getAlias());
			srg.setSpeciesReference(srw.getSpecies());
			srg.setRole(SpeciesReferenceRole.SUBSTRATE);
			srg.setSpeciesGlyph("SpeciesGlyph_" + srw.getAlias());
			BoundingBox bb = srg.createBoundingBox(DEFAULT_SPECIES_WIDTH,DEFAULT_SPECIES_HEIGHT, DEFAULT_SPECIES_DEPTH);
			bb.createPosition(0,0,0);
		}

		List<SpeciesReferenceWrapper> productList = rw.getListOfProductWrapper();
		for(SpeciesReferenceWrapper srw : productList){
			SpeciesReferenceGlyph srg = rg.createSpeciesReferenceGlyph("SpeciesReferenceGlyph_" + rg.getReaction() + "_" + srw.getAlias());
			srg.setSpeciesReference(srw.getSpecies());
			srg.setRole(SpeciesReferenceRole.PRODUCT);
			srg.setSpeciesGlyph("SpeciesGlyph_" + srw.getAlias());
			BoundingBox bb = srg.createBoundingBox(DEFAULT_SPECIES_WIDTH,DEFAULT_SPECIES_HEIGHT, DEFAULT_SPECIES_DEPTH);
			bb.createPosition(0,0,0);
		}

		if (rw.isSetModifier()) {
			List<ModifierSpeciesReferenceWrapper> modifierList = rw.getListOfModifierWrapper();
			for (ModifierSpeciesReferenceWrapper msrw : modifierList) {
				SpeciesReferenceGlyph srg = rg.createSpeciesReferenceGlyph("ModifierSpeciesReferenceGlyph_" + rg.getReaction() + "_" + msrw.getAlias());
				srg.setSpeciesReference(msrw.getSpecies());
				srg.setSpeciesGlyph("SpeciesGlyph_" + msrw.getAlias());
				BoundingBox bb = srg.createBoundingBox(DEFAULT_SPECIES_WIDTH,DEFAULT_SPECIES_HEIGHT, DEFAULT_SPECIES_DEPTH);
				bb.createPosition(0,0,0);
				String s = rw.getModifierTypeByModifierId(msrw.getSpecies());

				if(s.equals("CATALYSIS") || s.equals("UNKNOWN_CATALYSIS")){
					srg.setRole(SpeciesReferenceRole.ACTIVATOR);
				} else if(s.equals("INHIBITION") || s.equals("UNKNOWN_INHIBITION")){
					srg.setRole(SpeciesReferenceRole.INHIBITOR);
				} else{
					srg.setRole(SpeciesReferenceRole.MODIFIER);
				}
			}
		}

		return srgList;
	}

	/**
	 * Convert species alias to layout.
	 *
	 * @param saList void
	 * TODO
	 */
	public void convertSpeciesAliasToLayout(List<SpeciesAliasWrapper> saList){
		for(SpeciesAliasWrapper saw : saList){
			SpeciesGlyph sg = layout.createSpeciesGlyph("SpeciesGlyph_" + saw.getId());
			sg.setReference(saw.getSpecies());

			BoundingBox bb = sg.createBoundingBox();
			Dimensions dimension = bb.createDimensions();
			dimension.setWidth(saw.getW());
			dimension.setHeight(saw.getH());
			dimension.setDepth(1d);
			bb.setDimensions(dimension);
			Point point = bb.createPosition();
			point.setX(saw.getX());
			point.setY(saw.getY());
			point.setZ(0d);
			bb.setPosition(point);

			TextGlyph tg = layout.createTextGlyph("TextGlyph_" + saw.getId());
			if(!saw.isSetComplexSpeciesAlias())
				tg.setOriginOfText(saw.getSpeciesWrapperAliased().getId());
			else
				tg.setOriginOfText(saw.getSpecies());

			tg.setGraphicalObject(sg);
			BoundingBox bb2 = tg.createBoundingBox();
			Dimensions dimension2 = bb2.createDimensions();
			dimension2.setWidth(saw.getId().length() * 3);
			dimension2.setHeight(10);
			dimension.setDepth(1d);
			Point point2 = bb2.createPosition();
			point2.setX(saw.getX() + saw.getW()/2 - dimension2.getWidth()/2);
			point2.setY(saw.getY() + saw.getH()/2 - dimension2.getHeight()/2);
			point2.setZ(0d);

		}
	}

	/**
	 * Convert complex alias to layout.
	 *
	 * @param csaList void
	 * TODO
	 */
	public void convertComplexAliasToLayout(List<ComplexSpeciesAliasWrapper> csaList){
		for(ComplexSpeciesAliasWrapper csaw : csaList){
			SpeciesGlyph sg = layout.createSpeciesGlyph("SpeciesGlyph_" + csaw.getId());
			sg.setReference(csaw.getSpecies());

			BoundingBox bb = sg.createBoundingBox();
			Dimensions dimension = bb.createDimensions();
			dimension.setWidth(csaw.getW());
			dimension.setHeight(csaw.getH());
			dimension.setDepth(1d);
			bb.setDimensions(dimension);
			Point point = bb.createPosition();
			point.setX(csaw.getX());
			point.setY(csaw.getY());
			point.setZ(0d);
			bb.setPosition(point);

			TextGlyph tg = layout.createTextGlyph("TextGlyph_" + csaw.getId());
			tg.setOriginOfText(csaw.getId());
			tg.setGraphicalObject(sg);
			BoundingBox bb2 = tg.createBoundingBox();
			Dimensions dimension2 = bb2.createDimensions();
			dimension2.setWidth(csaw.getId().length() * 3);
			dimension2.setHeight(10);
			dimension.setDepth(1d);
			Point point2 = bb2.createPosition();
			point2.setX(csaw.getX() + csaw.getW()/2 - dimension2.getWidth()/2);
			point2.setY(csaw.getY() + csaw.getH()  );
			point2.setZ(0d);

		}
	}

	/**
	 * void
	 * TODO.
	 */
	public void validate(){
		document.checkConsistency();
		SBMLErrorLog errorLog = document.getListOfErrors();
		List<SBMLError> errorList = errorLog.getValidationErrors();
		for(SBMLError e: errorList){
			System.out.println(e.getLine() + " " + e.getMessage());
		}
	}

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args){
		LayoutConverter converter;
		try {
			//converter = new LayoutConverter(new File("sample/sample.xml"));
			converter = new LayoutConverter(new File("sample/link.xml"));
		} catch (JAXBException e) {
			System.err.println("Error unmarshaling XML");
			e.printStackTrace();
			return;
		} catch (XMLStreamException | IOException e) {
			System.err.println("Error reading SBML model");
			e.printStackTrace();
			return;
		}

		converter.convertToLayout();
		converter.print();
		converter.validate();
	}
}
