package org.sbml.layoutconverter;

import java.awt.geom.Point2D;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLStreamException;

import org.sbml._2001.ns.celldesigner.BaseProduct;
import org.sbml._2001.ns.celldesigner.BaseReactant;
import org.sbml._2001.ns.celldesigner.LinkTarget;
import org.sbml._2001.ns.celldesigner.Modification;
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
import org.sbml.wrapper.SpeciesWrapper;

/**
 * @author Kaito Ii
 *
 * Date Created: May 26, 2016
 */

public class LayoutConverter {
	
	
	ModelWrapper mWrapper;
	List<ReactionWrapper> rwList;
	List<SpeciesWrapper> swList;
	List<CompartmentWrapper> cwList;
	List<SpeciesAliasWrapper> sawList;

	SBMLDocument document;
	Model model;
	Layout layout;
	
	static final double DEFAULT_SPECIES_WIDTH = 80.0;
	static final double DEFAULT_SPECIES_HEIGHT = 40.0;
	static final double DEFAULT_SPECIES_DEPTH = 1.0;
	
	/**
	 * 
	 * @param file
	 * @throws JAXBException
	 * @throws XMLStreamException
	 * @throws IOException
	 */
	public LayoutConverter(File file) throws JAXBException, XMLStreamException, IOException{
		mWrapper = ObjectFactory.unmarshalSBML(file);	
		document = ConverterSBMLReader.read(file);
		model = document.getModel();
		LayoutModelPlugin mplugin = (LayoutModelPlugin)(model.getPlugin("layout"));
		layout = mplugin.createLayout();
	}
		
	/**
	 * 
	 * 
	 * void
	 * TODO
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
	 * 
	 * 
	 * void
	 * TODO
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
	 * 
	 * @param caList
	 * void
	 * TODO
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
			tg.setOriginOfText(caw.getId());
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
	
	public void convertModelToLayout(ModelWrapper mWrapper){
		layout.setId("Layout_" + model.getId());
		layout.createDimensions(mWrapper.getW(), mWrapper.getH(), 1d);
		
	}
	
	public void convertReactionsToLayout(List<ReactionWrapper> rList){
		for(ReactionWrapper rw: rList){
			ReactionGlyph rg = layout.createReactionGlyph("ReactionGlyph_" + rw.getId());
			rg.setReference(rw.getId());
			ListOf<SpeciesReferenceGlyph> srgList = createSpeciesReferenceGlyph(rg, rw);
			List<BaseReactant> brsList = rw.getBaseReactants();
			List<BaseProduct> prsList = rw.getBaseProducts();
			List<Point2D.Double> editPointList = rw.getEditPointsAsList();
			int rectangleIndex = rw.getRectangleIndex();
			rg.createBoundingBox();
			List<LineSegment> lsList = null;
			
			if(brsList.size() == 1 && prsList.size() == 1){
				BaseReactant br = brsList.get(0);
				BaseProduct bp = prsList.get(0);
				
				SpeciesAliasWrapper reactantsaw = mWrapper.getSpeciesAliasWrapperById(br.getAlias());
				SpeciesAliasWrapper productsaw = mWrapper.getSpeciesAliasWrapperById(bp.getAlias());
				
				Point reactantPoint  = LayoutUtil.createAdjustedPoint(reactantsaw.getX(), reactantsaw.getY(), reactantsaw.getW(), reactantsaw.getH(), br.getLinkAnchor().getPosition()); 
				Point productPoint = LayoutUtil.createAdjustedPoint(productsaw.getX(), productsaw.getY(), productsaw.getW(), productsaw.getH(), bp.getLinkAnchor().getPosition());
				lsList = LayoutUtil.createListOfLineSegment(reactantPoint, productPoint, editPointList, rectangleIndex);
				
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
				
			} else if(rw.getReactionType().equals("HETERODIMER_ASSOCIATION")){ 	// two to one
				List<Point2D.Double> editPoint = rw.getEditPointsAsList();
				BaseReactant br1 = brsList.get(0);
				SpeciesGlyph sg1 = layout.getSpeciesGlyph("SpeciesGlyph_" + br1.getAlias());
				SpeciesAliasWrapper reactantsaw1 = mWrapper.getSpeciesAliasWrapperById(br1.getAlias());
				SpeciesReferenceGlyph srg1 = srgList.get("SpeciesReferenceGlyph_" + rg.getReaction() + "_" + reactantsaw1.getId());
				Point reactantPoint1 = LayoutUtil.createAdjustedPoint(reactantsaw1.getX(), reactantsaw1.getY(), reactantsaw1.getW(), reactantsaw1.getH(), br1.getLinkAnchor().getPosition());
				LineSegment r1segment = srg1.createCurve().createLineSegment();
				
				
				BaseReactant br2 = brsList.get(1);
				SpeciesGlyph sg2 = layout.getSpeciesGlyph("SpeciesGlyph_" + br2.getAlias());
				SpeciesAliasWrapper reactantsaw2 = mWrapper.getSpeciesAliasWrapperById(br2.getAlias());
				SpeciesReferenceGlyph srg2 = srgList.get("SpeciesReferenceGlyph_" + rg.getReaction() + "_" + reactantsaw2.getId());
				Point reactantPoint2 = LayoutUtil.createAdjustedPoint(reactantsaw2.getX(), reactantsaw2.getY(), reactantsaw2.getW(), reactantsaw2.getH(), br1.getLinkAnchor().getPosition());
				LineSegment r2segment = srg2.createCurve().createLineSegment();
				
				
				BaseProduct bp1 = prsList.get(0);
				SpeciesGlyph sg3 = layout.getSpeciesGlyph("SpeciesGlyph_" + bp1.getAlias());
				SpeciesAliasWrapper productsaw = mWrapper.getSpeciesAliasWrapperById(bp1.getAlias());
				SpeciesReferenceGlyph srg3;
				Point productPoint;
				if(productsaw != null){
					// not complex species
					srg3 = srgList.get("SpeciesReferenceGlyph_" + rg.getReaction() + "_" + productsaw.getId());				
					productPoint = LayoutUtil.createAdjustedPoint(productsaw.getX(), productsaw.getY(), productsaw.getW(), productsaw.getH(), bp1.getLinkAnchor().getPosition());
				}  else { 
					//product is complex species
					ComplexSpeciesAliasWrapper csaw = mWrapper.getComplexAliasWrapperById(bp1.getAlias());
					srg3 =  srgList.get("SpeciesReferenceGlyph_" + rg.getReaction() + "_" + csaw.getId());
					productPoint = LayoutUtil.createAdjustedPoint(csaw.getX(), csaw.getY(), csaw.getW(), csaw.getH(), bp1.getLinkAnchor().getPosition());
				}
				LineSegment psegment = srg3.createCurve().createLineSegment();
				
				
				Point centerPoint = LayoutUtil.getEditPointPosition(sg1, sg2, sg3, editPoint.get(0));
				r1segment.setStart(reactantPoint1);
				r2segment.setStart(reactantPoint2);
				psegment.setEnd(productPoint);
				psegment.setStart(centerPoint);
				r1segment.setEnd(centerPoint.clone());
				r2segment.setEnd(centerPoint.clone());
								
			} else if(rw.getReactionType().equals("DISSOCIATION") || rw.getReactionType().equals("TRUNCATION")){ 	//one to two 	
				List<Point2D.Double> editPoint = rw.getEditPointsAsList();
				BaseReactant br1 = brsList.get(0);
				SpeciesGlyph sg1 = layout.getSpeciesGlyph("SpeciesGlyph_" + br1.getAlias());
				SpeciesAliasWrapper reactantsaw = mWrapper.getSpeciesAliasWrapperById(br1.getAlias());
				
				SpeciesReferenceGlyph srg1;
				Point reactantPoint;
				if(reactantsaw != null){
					// not complex species
					srg1 = srgList.get("SpeciesReferenceGlyph_" + rg.getReaction() + "_" + reactantsaw.getId());
					reactantPoint = LayoutUtil.createAdjustedPoint(reactantsaw.getX(), reactantsaw.getY(), reactantsaw.getW(), reactantsaw.getH(), br1.getLinkAnchor().getPosition());
				}  else { 
					//product is complex species
					ComplexSpeciesAliasWrapper csaw = mWrapper.getComplexAliasWrapperById(br1.getAlias());
					srg1 =  srgList.get("SpeciesReferenceGlyph_" + rg.getReaction() + "_" + csaw.getId());
					reactantPoint = LayoutUtil.createAdjustedPoint(csaw.getX(), csaw.getY(), csaw.getW(), csaw.getH(), br1.getLinkAnchor().getPosition());
				}
				LineSegment r1segment = srg1.createCurve().createLineSegment();
				
				BaseProduct bp1 = prsList.get(0);
				SpeciesAliasWrapper productsaw1 = mWrapper.getSpeciesAliasWrapperById(bp1.getAlias());
				SpeciesGlyph sg2 = layout.getSpeciesGlyph("SpeciesGlyph_" + bp1.getAlias());
				SpeciesReferenceGlyph srg2 = srgList.get("SpeciesReferenceGlyph_" + rg.getReaction() + "_" + productsaw1.getId());
				LineSegment p1segment = srg2.createCurve().createLineSegment();
				Point productPoint1 = LayoutUtil.createAdjustedPoint(productsaw1.getX(), productsaw1.getY(), productsaw1.getW(), productsaw1.getH(), bp1.getLinkAnchor().getPosition());
				
				BaseProduct bp2 = prsList.get(1);				
				SpeciesAliasWrapper productsaw2 = mWrapper.getSpeciesAliasWrapperById(bp2.getAlias());
				SpeciesGlyph sg3 = layout.getSpeciesGlyph("SpeciesGlyph_" + bp2.getAlias());
				SpeciesReferenceGlyph srg3 = srgList.get("SpeciesReferenceGlyph_" + rg.getReaction() + "_" + productsaw2.getId());
				LineSegment p2segment = srg3.createCurve().createLineSegment();
				
				Point productPoint2 = LayoutUtil.createAdjustedPoint(productsaw2.getX(), productsaw2.getY(), productsaw2.getW(), productsaw2.getH(), bp2.getLinkAnchor().getPosition());
				Point centerPoint = LayoutUtil.getEditPointPosition(sg1, sg2, sg3, editPoint.get(0));
				r1segment.setStart(reactantPoint);
				r1segment.setEnd(centerPoint.clone());
				p1segment.setStart(centerPoint.clone());
				p1segment.setEnd(productPoint1);
				p2segment.setStart(centerPoint.clone());
				p2segment.setEnd(productPoint2);			
			}

			
			if(rw.isSetModifier()){
				List<ModifierSpeciesReferenceWrapper> msrwList = rw.getListOfModifierWrapper();
				for(ModifierSpeciesReferenceWrapper msrw : msrwList){
					Modification m = rw.getModificationByModifierId(msrw.getSpecies());
					editPointList = msrw.getEditPoints();
					SpeciesReferenceGlyph srg = rg.getSpeciesReferenceGlyph("ModifierSpeciesReferenceGlyph_" + rg.getReaction() + "_" + msrw.getAlias());
					SpeciesGlyph sg = layout.getSpeciesGlyph("SpeciesGlyph_" + msrw.getAlias());
					SpeciesAliasWrapper modifiersaw = mWrapper.getSpeciesAliasWrapperById(msrw.getAlias());
					Point modifier = sg.getBoundingBox().getPosition();
					LinkTarget lt = rw.getLinkTargetByModifier(m);
					
					modifier = LayoutUtil.createAdjustedPoint(modifiersaw.getX(), modifiersaw.getY(), modifiersaw.getW(), modifiersaw.getH(), lt.getLinkAnchor().getPosition());
					Point reaction = lsList.get(rectangleIndex).getEnd().clone();
					lsList = LayoutUtil.createListOfLineSegment(modifier, reaction, editPointList, rectangleIndex);
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
	 * 
	 * @param rg
	 * @param rw
	 * @return
	 * ListOf<SpeciesReferenceGlyph>
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
	 * 
	 * @param saList
	 * void
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
	 * 
	 * @param csaList
	 * void
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
	 * 
	 * 
	 * void
	 * TODO
	 */
	public void validate(){
		document.checkConsistency();
		SBMLErrorLog errorLog = document.getListOfErrors();
		List<SBMLError> errorList = errorLog.getValidationErrors();
		for(SBMLError e: errorList){
			System.out.println(e.getLine() + " " + e.getMessage());
		}
	}
	
	public static void main(String[] args){
		LayoutConverter converter;
		try {
			//converter = new LayoutConverter(new File("sample/phosphorylation.xml"));		
			converter = new LayoutConverter(new File("sample/complex.xml"));		
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
