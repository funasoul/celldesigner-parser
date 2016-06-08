package org.sbml.layoutconverter;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLStreamException;

import org.sbml._2001.ns.celldesigner.BaseProduct;
import org.sbml._2001.ns.celldesigner.BaseReactant;
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
			//SBMLWriter.write(document, System.out, ' ', (short) 2);
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
	
	public void convertToLayout(){
		convertModelToLayout(mWrapper);
		convertCompartmentsToLayout(mWrapper.getListOfCompartmentAliasWrapper());
		convertSpeciesAliasToLayout(mWrapper.getListOfSpeciesAliasWrapper());
		convertReactionsToLayout(mWrapper.getListOfReactionWrapper());
	
	}
	
	/**
	 * 
	 * @param caList
	 * void
	 * TODO
	 */
	public void convertCompartmentsToLayout(List<CompartmentAliasWrapper> cawList){
		cawList = reorderCompartmentAccordingToPosition(cawList);
		
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
			bb.setPosition(point);
			
			TextGlyph tg = layout.createTextGlyph("TextGlyph_" + caw.getId());
			tg.setOriginOfText(caw.getId());
			tg.setGraphicalObject(cg);
			BoundingBox bb2 = tg.createBoundingBox();;
			Dimensions dimension2 = bb2.createDimensions();
			dimension2.setWidth(caw.getId().length() * 3);
			dimension2.setHeight(10);
			dimension.setDepth(1d);
			Point point2 = bb2.createPosition();
			point2.setX(caw.getNamePoint().getX().doubleValue());
			point2.setY(caw.getNamePoint().getY().doubleValue());
			point2.setZ(0d);
		}
	}
	
	/**
	 * 
	 * @param cawList
	 * @return
	 * List<CompartmentAliasWrapper>
	 * TODO
	 */
	public List<CompartmentAliasWrapper> reorderCompartmentAccordingToPosition(List<CompartmentAliasWrapper> cawList){
		for(int i = 1; i < cawList.size(); i++){
			CompartmentAliasWrapper c = cawList.get(i);
			if(c.getCompartmentWrapperAliased().isSetOutside()){
				CompartmentWrapper cw = c.getCompartmentWrapperAliased().getOutsideInstance();
				CompartmentAliasWrapper outside = cw.getAliasWrapper();
				int outIndex = cawList.indexOf(outside);
				if(outIndex > i){
					cawList.remove(c);
					cawList.add(outIndex , c);
				}
			}
		}
		return cawList;
	}
	
	public void convertModelToLayout(ModelWrapper mWrapper){
		layout.setId("Layout_" + model.getId());
		layout.createDimensions(mWrapper.getSizeX(), mWrapper.getSizeX(), 0);
		
	}
	
	public void convertReactionsToLayout(List<ReactionWrapper> rList){
		for(ReactionWrapper rw: rList){
			ReactionGlyph rg = layout.createReactionGlyph("ReactionGlyph_" + rw.getId());
			rg.setReference(rw.getId());
			rg.setSBOTerm(model.getReaction(rw.getId()).getSBOTerm());
			ListOf<SpeciesReferenceGlyph> srgList = createSpeciesReferenceGlyph(rg, rw);
			List<BaseReactant> brsList = rw.getBaseReactants();
			List<BaseProduct> prsList = rw.getBaseProducts();
			List<Point> editPointList = rw.getEditPointsAsList();
			int rectangleIndex = rw.getRectangleIndex();
			
			if(brsList.size() == 1 && prsList.size() == 1){
				BaseReactant br = brsList.get(0);
				BaseProduct bp = prsList.get(0);
				
				SpeciesAliasWrapper reactantsaw = mWrapper.getSpeciesAliasWrapperById(br.getAlias());
				SpeciesAliasWrapper productsaw = mWrapper.getSpeciesAliasWrapperById(bp.getAlias());
				
				Point reactantPoint;
				Point productPoint;
				if(br.getLinkAnchor() == null){
					reactantPoint = LayoutUtil.createAdjustedPoint(reactantsaw.getX(), reactantsaw.getY(), reactantsaw.getW(), reactantsaw.getH(), "INACTIVE");
				} else {
					reactantPoint = LayoutUtil.createAdjustedPoint(reactantsaw.getX(), reactantsaw.getY(), reactantsaw.getW(), reactantsaw.getH(), br.getLinkAnchor().getPosition());
				}
				if(bp.getLinkAnchor() == null){
					productPoint = LayoutUtil.createAdjustedPoint(productsaw.getX(), productsaw.getY(), productsaw.getW(), productsaw.getH(), "INACTIVE");
				} else{
					productPoint = LayoutUtil.createAdjustedPoint(productsaw.getX(), productsaw.getY(), productsaw.getW(), productsaw.getH(), bp.getLinkAnchor().getPosition());
				}
				List<LineSegment> lsList = LayoutUtil.createListOfLineSegment(reactantPoint, productPoint, editPointList, rectangleIndex);
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
				List<Point> editPoint = rw.getEditPointsAsList();
				BaseReactant br1 = brsList.get(0);
				BaseReactant br2 = brsList.get(1);
				BaseProduct bp1 = prsList.get(0);
				SpeciesAliasWrapper reactantsaw1 = mWrapper.getSpeciesAliasWrapperById(br1.getAlias());
				SpeciesAliasWrapper reactantsaw2 = mWrapper.getSpeciesAliasWrapperById(br2.getAlias());
				SpeciesAliasWrapper productsaw = mWrapper.getSpeciesAliasWrapperById(bp1.getAlias());
				SpeciesGlyph sg1 = layout.getSpeciesGlyph("SpeciesGlyph_" + br1.getAlias());
				SpeciesGlyph sg2 = layout.getSpeciesGlyph("SpeciesGlyph_" + br2.getAlias());
				SpeciesGlyph sg3 = layout.getSpeciesGlyph("SpeciesGlyph_" + bp1.getAlias());
				SpeciesReferenceGlyph srg1 = srgList.get("SpeciesReferenceGlyph_" + rg.getReaction() + "_" + reactantsaw1.getId());
				SpeciesReferenceGlyph srg2 = srgList.get("SpeciesReferenceGlyph_" + rg.getReaction() + "_" + reactantsaw2.getId());
				SpeciesReferenceGlyph srg3 = srgList.get("SpeciesReferenceGlyph_" + rg.getReaction() + "_" + productsaw.getId());

				
				LineSegment r1segment = srg1.createCurve().createLineSegment();
				LineSegment r2segment = srg2.createCurve().createLineSegment();
				LineSegment psegment = srg3.createCurve().createLineSegment();
				Point reactantPoint1 = LayoutUtil.createAdjustedPoint(reactantsaw1.getX(), reactantsaw1.getY(), reactantsaw1.getW(), reactantsaw1.getH(), br1.getLinkAnchor().getPosition());
				Point reactantPoint2 = LayoutUtil.createAdjustedPoint(reactantsaw2.getX(), reactantsaw2.getY(), reactantsaw2.getW(), reactantsaw2.getH(), br1.getLinkAnchor().getPosition());
				Point productPoint = LayoutUtil.createAdjustedPoint(productsaw.getX(), productsaw.getY(), productsaw.getW(), productsaw.getH(), bp1.getLinkAnchor().getPosition());
				Point centerPoint = LayoutUtil.getEditPointPosition(sg1, sg2, sg3, editPoint.get(0));
				r1segment.setStart(reactantPoint1);
				r2segment.setStart(reactantPoint2);
				psegment.setEnd(productPoint);
				psegment.setStart(centerPoint);
				r1segment.setEnd(centerPoint.clone());
				r2segment.setEnd(centerPoint.clone());
								
			} else if(rw.getReactionType().equals("DISSOCIATION") || rw.getReactionType().equals("TRUNCATION")){ 	//one to two 	
				List<Point> editPoint = rw.getEditPointsAsList();
				BaseReactant br1 = brsList.get(0);
				BaseProduct bp1 = prsList.get(0);
				BaseProduct bp2 = prsList.get(1);				
				SpeciesAliasWrapper reactantsaw1 = mWrapper.getSpeciesAliasWrapperById(br1.getAlias());
				SpeciesAliasWrapper productsaw1 = mWrapper.getSpeciesAliasWrapperById(bp1.getAlias());
				SpeciesAliasWrapper productsaw2 = mWrapper.getSpeciesAliasWrapperById(bp2.getAlias());
				SpeciesGlyph sg1 = layout.getSpeciesGlyph("SpeciesGlyph_" + br1.getAlias());
				SpeciesGlyph sg2 = layout.getSpeciesGlyph("SpeciesGlyph_" + bp1.getAlias());
				SpeciesGlyph sg3 = layout.getSpeciesGlyph("SpeciesGlyph_" + bp2.getAlias());
				SpeciesReferenceGlyph srg1 = srgList.get("SpeciesReferenceGlyph_" + rg.getReaction() + "_" + reactantsaw1.getId());
				SpeciesReferenceGlyph srg2 = srgList.get("SpeciesReferenceGlyph_" + rg.getReaction() + "_" + productsaw1.getId());
				SpeciesReferenceGlyph srg3 = srgList.get("SpeciesReferenceGlyph_" + rg.getReaction() + "_" + productsaw2.getId());

				LineSegment r1segment = srg1.createCurve().createLineSegment();
				LineSegment p1segment = srg2.createCurve().createLineSegment();
				LineSegment p2segment = srg3.createCurve().createLineSegment();
				Point reactantPoint1 = LayoutUtil.createAdjustedPoint(reactantsaw1.getX(), reactantsaw1.getY(), reactantsaw1.getW(), reactantsaw1.getH(), br1.getLinkAnchor().getPosition());
				Point productPoint1 = LayoutUtil.createAdjustedPoint(productsaw1.getX(), productsaw1.getY(), productsaw1.getW(), productsaw1.getH(), bp1.getLinkAnchor().getPosition());
				Point productPoint2 = LayoutUtil.createAdjustedPoint(productsaw2.getX(), productsaw2.getY(), productsaw2.getW(), productsaw2.getH(), bp2.getLinkAnchor().getPosition());
				Point centerPoint = LayoutUtil.getEditPointPosition(sg1, sg2, sg3, editPoint.get(0));
				r1segment.setStart(reactantPoint1);
				r1segment.setEnd(centerPoint.clone());
				p1segment.setStart(centerPoint.clone());
				p1segment.setEnd(productPoint1);
				p2segment.setStart(centerPoint.clone());
				p2segment.setEnd(productPoint2);			
			}

			
			if(rw.isSetModifier()){
				List<ModifierSpeciesReferenceWrapper> msrwList = rw.getListOfModifierWrapper();
				for(ModifierSpeciesReferenceWrapper msrw : msrwList){
					SpeciesReferenceGlyph srg = rg.getSpeciesReferenceGlyph("ModifierSpeciesReferenceGlyph_" + rg.getReaction() + "_" + msrw.getAlias());
					
				}
			}
			
			
			//create textglyph near the first base reactant 
			BaseReactant br = brsList.get(0);
			SpeciesAliasWrapper saw = mWrapper.getSpeciesAliasWrapperById(br.getAlias());
			Point point = LayoutUtil.createAdjustedPoint(saw.getX(), saw.getY(), saw.getW(), saw.getH(), br.getLinkAnchor().getPosition());
			if(br.getLinkAnchor().getPosition().equals("INACTIVE")){
				point.setX(point.getX() + saw.getW() /2);
			}
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
			srg.setSpeciesGlyph("speciesGlyph_" + srw.getAlias());
		}

		List<SpeciesReferenceWrapper> productList = rw.getListOfProductWrapper();
		for(SpeciesReferenceWrapper srw : productList){
			SpeciesReferenceGlyph srg = rg.createSpeciesReferenceGlyph("SpeciesReferenceGlyph_" + rg.getReaction() + "_" + srw.getAlias());
			srg.setSpeciesReference(srw.getSpecies());
			srg.setRole(SpeciesReferenceRole.PRODUCT);
			srg.setSpeciesGlyph("speciesGlyph_" + srw.getAlias());
		}

		if (rw.isSetModifier()) {
			List<ModifierSpeciesReferenceWrapper> modifierList = rw.getListOfModifierWrapper();
			for (ModifierSpeciesReferenceWrapper msrw : modifierList) {
				SpeciesReferenceGlyph srg = rg.createSpeciesReferenceGlyph("ModifierSpeciesReferenceGlyph_" + rg.getReaction() + "_" + msrw.getAlias());
				srg.setSpeciesReference(msrw.getSpecies());
				srg.setSpeciesGlyph("speciesGlyph_" + msrw.getAlias());
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
			sg.setSBOTerm(model.getSpecies(sg.getReference()).getSBOTerm());
			
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
			
			TextGlyph tg = layout.createTextGlyph("TextGlyph_" + saw.getSpeciesWrapperAliased().getId());
			tg.setOriginOfText(saw.getSpeciesWrapperAliased().getId());
			tg.setGraphicalObject(sg);
			BoundingBox bb2 = tg.createBoundingBox();;
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
	
	public void validate(){
			document.checkConsistency();
			SBMLErrorLog errorLog = document.getListOfErrors();
			List<SBMLError> errorList = errorLog.getValidationErrors();
			for(SBMLError e: errorList)
				System.out.println(e.getMessage());
	}
	
	public static void main(String[] args){
		LayoutConverter converter;
		try {
			converter = new LayoutConverter(new File("sample/anchor5.xml"));
			
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
