package org.sbml.layoutconverter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLStreamException;

import org.sbml._2001.ns.celldesigner.BaseProduct;
import org.sbml._2001.ns.celldesigner.BaseReactant;
import org.sbml._2001.ns.celldesigner.Bounds;
import org.sbml._2001.ns.celldesigner.BriefView;
import org.sbml._2001.ns.celldesigner.CompartmentAlias;
import org.sbml._2001.ns.celldesigner.ConnectScheme;
import org.sbml._2001.ns.celldesigner.EditPoints;
import org.sbml._2001.ns.celldesigner.Info;
import org.sbml._2001.ns.celldesigner.LineDirection;
import org.sbml._2001.ns.celldesigner.LinkAnchor;
import org.sbml._2001.ns.celldesigner.Modification;
import org.sbml._2001.ns.celldesigner.UsualView;
import org.sbml.jsbml.ListOf;
import org.sbml.jsbml.Model;
import org.sbml.jsbml.SBMLDocument;
import org.sbml.jsbml.SBMLException;
import org.sbml.jsbml.SBMLReader;
import org.sbml.jsbml.SBMLWriter;
import org.sbml.jsbml.ext.SBasePlugin;
import org.sbml.jsbml.ext.layout.BoundingBox;
import org.sbml.jsbml.ext.layout.CompartmentGlyph;
import org.sbml.jsbml.ext.layout.Curve;
import org.sbml.jsbml.ext.layout.Dimensions;
import org.sbml.jsbml.ext.layout.GraphicalObject;
import org.sbml.jsbml.ext.layout.Layout;
import org.sbml.jsbml.ext.layout.LayoutConstants;
import org.sbml.jsbml.ext.layout.LayoutModelPlugin;
import org.sbml.jsbml.ext.layout.LineSegment;
import org.sbml.jsbml.ext.layout.Point;
import org.sbml.jsbml.ext.layout.ReactionGlyph;
import org.sbml.jsbml.ext.layout.SpeciesGlyph;
import org.sbml.jsbml.ext.layout.SpeciesReferenceGlyph;
import org.sbml.jsbml.ext.layout.SpeciesReferenceRole;
import org.sbml.jsbml.ext.layout.TextGlyph;
import org.sbml.sbml.level2.version4.SpeciesReference;
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
		
		
	public void print(){
//		
//		System.out.println(mWrapper.getId());
//		rwList = mWrapper.getListOfReactionWrapper();
//		swList = mWrapper.getListOfSpeciesWrapper();
//		cwList  = mWrapper.getListOfCompartmentWrapper();
//		sawList  = mWrapper.getListOfSpeciesAliasWrapper();
//		
//		for(ReactionWrapper rw : rwList){
//			System.out.println(rw.getId());
//			System.out.println("reaction : " +  rw.getReactionType());
//			System.out.println("name " + rw.getName());
//			List<BaseReactant> rl = rw.getBaseReactants();
//			for(BaseReactant br : rl){
//				System.out.println("species : " + br.getSpecies());
//				System.out.println("alias : " + br.getAlias());
//				System.out.println("link anchor position : " + br.getLinkAnchor().getPosition());
//			}
//			
//			List<BaseProduct> pl = rw.getBaseProducts();
//			for(BaseProduct pr : pl){
//				System.out.println("species : " + pr.getSpecies());
//				System.out.println("alias : " + pr.getAlias());
//				System.out.println("link anchor position : " + pr.getLinkAnchor().getPosition());
//			}
//
//			List<SpeciesReferenceWrapper> rList = rw.getListOfReactantWrapper();			
//			for(SpeciesReferenceWrapper srw : rList){
//				System.out.println(srw.getAliased().getId());
//			}
//
//			List<SpeciesReferenceWrapper> pList = rw.getListOfProductWrapper();			
//			for(SpeciesReferenceWrapper srw : pList){
//				System.out.println(srw.getAliased().getId());
//			}
//
//			if(rw.isSetModifier()){
//				List<ModifierSpeciesReferenceWrapper> msrwList = rw.getListOfModifierWrapper();			
//					for(ModifierSpeciesReferenceWrapper msrw : msrwList)
//						System.out.println(msrw.getAliased().getId());
//			}
//			
//			ConnectScheme cs = rw.getConnectScheme();
//			System.out.println("connect policy : " + cs.getConnectPolicy());
//			System.out.println("rectangle index : " + cs.getRectangleIndex());
//			
//			List<LineDirection> ld = cs.getListOfLineDirection().getLineDirection();
//			for(LineDirection l : ld){
//				System.out.println("value : " + l.getValue());
//				System.out.println("index : " + l.getIndex());
//				System.out.println("arm : " + l.getArm());
//			}
//			
//			System.out.println("line color : " + rw.getLine().getColor());
//			System.out.println("width : " + rw.getLine().getWidth());
//			
//		}
//		
//		for(SpeciesWrapper sw : swList){
//			System.out.println(sw.getId());
//			System.out.println("position to compartment : " + sw.getPositionToCompartment());
//			System.out.println("species identity : " + sw.getSpeciesIdentity().getClazz());
//			System.out.println("protein reference : " + sw.getSpeciesIdentity().getProteinReference());
//			System.out.println("antisenseRNA reference : " + sw.getSpeciesIdentity().getAntisensernaReference());
//			System.out.println("gene reference : " + sw.getSpeciesIdentity().getGeneReference());
//			System.out.println("rna reference : " + sw.getSpeciesIdentity().getRnaReference());
//			System.out.println("state : " + sw.getSpeciesIdentity().getState());
//		}
//		
//		for(CompartmentWrapper cw : cwList){
//			System.out.println(cw.getId());
//			if(cw.isSetName())
//				System.out.println("Name : " + cw.getName());
//		}
//		
//		for(SpeciesAliasWrapper saw : sawList){
//			System.out.println(saw.getId());
//			System.out.println("activity : " + saw.getActivity());
//			System.out.println("Bounds : " + " x = "  + saw.getBounds().getX() + " y = "  + saw.getBounds().getY() + " w = "  + saw.getBounds().getW() + " h = "  + saw.getBounds().getH());
//			System.out.println("view : " + saw.getView().getState());
//		
//			UsualView uv = saw.getUsualView();
//			System.out.println("usual view");
//			System.out.println("inner position : " + uv.getInnerPosition().getX() + ", " + uv.getInnerPosition().getY());
//			System.out.println("single line : " + uv.getSingleLine().getWidth());
//			System.out.println("paint : " + "color " + uv.getPaint().getColor() + " scheme " + uv.getPaint().getScheme());
//
//			BriefView bv = saw.getBriefView();
//			System.out.println("usual view");
//			System.out.println("inner position : " + bv.getInnerPosition().getX() + ", " + bv.getInnerPosition().getY());
//			System.out.println("single line : " + bv.getSingleLine().getWidth());
//			System.out.println("paint : " + "color " + bv.getPaint().getColor() + " scheme " + uv.getPaint().getScheme());
//
//			Info info = saw.getInfo();
//			System.out.println("label : " + info.getLabel());
//			System.out.println("prefix : " + info.getPrefix());
//			System.out.println("state : " + info.getState());
//			System.out.println("angle : " + info.getAngle());
//		}
		
		try {
	    	SBMLWriter.write(document, System.out, ' ', (short) 2);
	    	SBMLWriter.write(document, new File("CD_" + model.getId() + ".xml"), ' ', (short) 2);
		} catch (SBMLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XMLStreamException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
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
		for(CompartmentAliasWrapper caw : cawList){
			CompartmentGlyph cg = layout.createCompartmentGlyph("CompartmentGlyph_" + caw.getId());
			cg.setCompartment(caw.getCompartment());
			cg.setSBOTerm(model.getCompartment(caw.getCompartment()).getSBOTerm());
			BoundingBox bb = cg.createBoundingBox();
			Dimensions dimension = bb.createDimensions();
			dimension.setWidth(caw.getW());
			dimension.setHeight(caw.getH());
			dimension.setDepth(1d);
			bb.setDimensions(dimension);
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
			
			// one to one
			if(brsList.size() == 1 && prsList.size() == 1 && editPointList.isEmpty()){			
				BaseReactant br = brsList.get(0);
				BaseProduct bp = prsList.get(0);
				
				SpeciesAliasWrapper reactantsaw = mWrapper.getSpeciesAliasWrapperById(br.getAlias());
				SpeciesAliasWrapper productsaw = mWrapper.getSpeciesAliasWrapperById(bp.getAlias());
				
				LineSegment rsegment = new LineSegment();
				LineSegment psegment = new LineSegment();
				Point reactantPoint = createAdjustedPoint(reactantsaw.getX(), reactantsaw.getY(), reactantsaw.getW(), reactantsaw.getH(), br.getLinkAnchor().getPosition());
				Point productPoint = createAdjustedPoint(productsaw.getX(), productsaw.getY(), productsaw.getW(), productsaw.getH(), bp.getLinkAnchor().getPosition());
				Point centerPoint = getCenter(reactantPoint, productPoint);
				rsegment.setStart(reactantPoint);
				psegment.setStart(centerPoint);
				psegment.setEnd(productPoint);
				rsegment.setEnd(centerPoint.clone());
				
				SpeciesReferenceGlyph srg = srgList.get("SpeciesReferenceGlyph_" + rg.getReaction() + "_" + reactantsaw.getId());
				Curve curve = srg.createCurve();
				curve.addCurveSegment(rsegment);

				srg = srgList.get("SpeciesReferenceGlyph_" + rg.getReaction() + "_" + productsaw.getId());
				curve = srg.createCurve();
				curve.addCurveSegment(psegment);
				
			} else if(brsList.size() == 1 && prsList.size() == 1 && !editPointList.isEmpty()){			
				BaseReactant br = brsList.get(0);
				BaseProduct bp = prsList.get(0);
				
				SpeciesAliasWrapper reactantsaw = mWrapper.getSpeciesAliasWrapperById(br.getAlias());
				SpeciesAliasWrapper productsaw = mWrapper.getSpeciesAliasWrapperById(bp.getAlias());
				
				LineSegment rsegment = new LineSegment();
				LineSegment psegment = new LineSegment();
				Point reactantPoint = createAdjustedPoint(reactantsaw.getX(), reactantsaw.getY(), reactantsaw.getW(), reactantsaw.getH(), br.getLinkAnchor().getPosition());
				Point productPoint = createAdjustedPoint(productsaw.getX(), productsaw.getY(), productsaw.getW(), productsaw.getH(), bp.getLinkAnchor().getPosition());
				Point centerPoint = getCenter(rsegment.getStart(), psegment.getEnd());
				rsegment.setStart(reactantPoint);
				psegment.setEnd(productPoint);
				psegment.setStart(centerPoint);
				rsegment.setEnd(centerPoint.clone());
				
				SpeciesReferenceGlyph srg = srgList.get("SpeciesReferenceGlyph_" + rg.getReaction() + "_" + reactantsaw.getId());
				Curve curve = srg.createCurve();
				curve.addCurveSegment(rsegment);

				srg = srgList.get("SpeciesReferenceGlyph_" + rg.getReaction() + "_" + productsaw.getId());
				curve = srg.createCurve();
				curve.addCurveSegment(psegment);

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
				Point reactantPoint1 = createAdjustedPoint(reactantsaw1.getX(), reactantsaw1.getY(), reactantsaw1.getW(), reactantsaw1.getH(), br1.getLinkAnchor().getPosition());
				Point reactantPoint2 = createAdjustedPoint(reactantsaw2.getX(), reactantsaw2.getY(), reactantsaw2.getW(), reactantsaw2.getH(), br1.getLinkAnchor().getPosition());
				Point productPoint = createAdjustedPoint(productsaw.getX(), productsaw.getY(), productsaw.getW(), productsaw.getH(), bp1.getLinkAnchor().getPosition());
				Point centerPoint = getEditPointPosition(sg1, sg2, sg3, editPoint.get(0));
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
				SpeciesAliasWrapper productsaw = mWrapper.getSpeciesAliasWrapperById(bp1.getAlias());
				SpeciesAliasWrapper productsaw2 = mWrapper.getSpeciesAliasWrapperById(bp2.getAlias());
				SpeciesGlyph sg1 = layout.getSpeciesGlyph("SpeciesGlyph_" + br1.getAlias());
				SpeciesGlyph sg2 = layout.getSpeciesGlyph("SpeciesGlyph_" + bp1.getAlias());
				SpeciesGlyph sg3 = layout.getSpeciesGlyph("SpeciesGlyph_" + bp2.getAlias());
				SpeciesReferenceGlyph srg1 = srgList.get("SpeciesReferenceGlyph_" + rg.getReaction() + "_" + reactantsaw1.getId());
				SpeciesReferenceGlyph srg2 = srgList.get("SpeciesReferenceGlyph_" + rg.getReaction() + "_" + productsaw.getId());
				SpeciesReferenceGlyph srg3 = srgList.get("SpeciesReferenceGlyph_" + rg.getReaction() + "_" + productsaw2.getId());
				
				LineSegment r1segment = srg1.createCurve().createLineSegment();
				LineSegment p1segment = srg2.createCurve().createLineSegment();
				LineSegment p2segment = srg3.createCurve().createLineSegment();
				Point reactantPoint1 = createAdjustedPoint(reactantsaw1.getX(), reactantsaw1.getY(), reactantsaw1.getW(), reactantsaw1.getH(), br1.getLinkAnchor().getPosition());
				Point productPoint1 = createAdjustedPoint(productsaw.getX(), productsaw.getY(), productsaw.getW(), productsaw.getH(), bp1.getLinkAnchor().getPosition());
				Point productPoint2 = createAdjustedPoint(productsaw2.getX(), productsaw2.getY(), productsaw2.getW(), productsaw2.getH(), bp2.getLinkAnchor().getPosition());
				Point centerPoint = getEditPointPosition(sg1, sg2, sg3, editPoint.get(0));
				r1segment.setStart(reactantPoint1);
				r1segment.setEnd(centerPoint.clone());
				p1segment.setStart(centerPoint.clone());
				p1segment.setEnd(productPoint1);
				p2segment.setStart(centerPoint.clone());
				p2segment.setEnd(productPoint2);
				
			}
			
		}
	}
	
	
	/**
	 * 
	 * @param origin
	 * @param axis1
	 * @param axis2
	 * @param editPoint
	 * @return
	 * Point
	 * TODO
	 */
	public Point getEditPointPosition(SpeciesGlyph origin, SpeciesGlyph axis1, SpeciesGlyph axis2, Point editPoint){
		Point pOrigin = getCenterOfGlyph(origin);
		Point pA1 = getCenterOfGlyph(axis1);
		Point pA2  = getCenterOfGlyph(axis2);
		Point point = pOrigin.clone();
		
		Point displacement1 = getDistanceFromEditPoint(pOrigin, pA1, editPoint.getX());
		Point displacement2 = getDistanceFromEditPoint(pOrigin, pA2, editPoint.getY());
		
		point.setX(point.getX() + displacement1.getX() + displacement2.getX());
		point.setY(point.getY() + displacement1.getY() + displacement2.getY());		
		
		return point;
	}
	
	/**
	 * 
	 * @param sg
	 * @return
	 * Point
	 * TODO
	 */
	public Point getCenterOfGlyph(SpeciesGlyph sg){
		Dimensions dimension = sg.getBoundingBox().getDimensions();
		Point point = sg.getBoundingBox().getPosition().clone();
		point.setX(point.getX() + dimension.getWidth() / 2);
		point.setY(point.getY() + dimension.getHeight() / 2);
		
		return point;
	}

	/**
	 * 
	 * @param p1
	 * @param p2
	 * @param editPoint
	 * @return
	 * Point
	 * TODO
	 */
	public Point getDistanceFromEditPoint(Point p1, Point p2, double percentage){
		Point point = new Point();
		point.setX(getLength(p1.getX(), p2.getX(), percentage));
		point.setY(getLength(p1.getY(), p2.getY(), percentage));
		
		return point;
	}
	
	/**
	 * 
	 * @param d1
	 * @param d2
	 * @param d3
	 * @return
	 * double
	 * TODO
	 */
	public double getLength(double d1, double d2, double d3){
		return (d2 - d1) * d3;
	}
	
	/**
	 * 
	 * @param point1
	 * @param point2
	 * @return
	 * Point
	 * TODO
	 */
	public Point getCenter(Point point1, Point point2){
		return new Point((point1.getX() + point2.getX())/2, (point1.getY() + point2.getY())/2);
	}
	
	/**
	 * returns the adjusted coordinates of a species according to the direction 
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param direction
	 * @return
	 * Point
	 * TODO
	 */
	public Point createAdjustedPoint(double x, double y, double width, double height, String direction){
		Point point = new Point();
		
		if(direction.equals("NW")){
			point.setX(x);
			point.setY(y);
			
		} else if(direction.equals("NNW")){
			point.setX(x + width / 4);
			point.setY(y);
			
		} else if(direction.equals("N")){
			point.setX(x + width / 2);
			point.setY(y);
		} else if(direction.equals("NNE")){
			point.setX(x + width * 3 / 4);
			point.setY(y);
			
		} else if(direction.equals("NE")){
			point.setX(x + width);
			point.setY(y);
			
		} else if(direction.equals("ENE")){
			point.setX(x + width);
			point.setY(y + height / 4);
			
		} else if(direction.equals("E")){
			point.setX(x + width);
			point.setY(y + height / 2);
			
		} else if(direction.equals("ESE")){
			point.setX(x + width);
			point.setY(y + height * 3 / 4);
			
		} else if(direction.equals("SE")){
			point.setX(x + width);
			point.setY(y + height);
			
		} else if(direction.equals("SSE")){
			point.setX(x + width * 3 / 4);
			point.setY(y + height);
			
		} else if(direction.equals("S")){
			point.setX(x + width / 2);
			point.setY(y + height);
			
		} else if(direction.equals("SSW")){
			point.setX(x + width / 4);
			point.setY(y + height);
			
		} else if(direction.equals("SW")){
			point.setX(x);
			point.setY(y + height);
			
		} else if(direction.equals("WSW")){
			point.setX(x);
			point.setY(y + height * 3 / 4);
			
		} else if(direction.equals("W")){
			point.setX(x);
			point.setY(y + height / 2);
			
		} else if(direction.equals("WNW")){
			point.setX(x);
			point.setY(y + height / 4);
			
		} else{
			System.err.println("Illegal anchortype");
		}
		
		return point;
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
		}
		
		List<SpeciesReferenceWrapper> productList = rw.getListOfProductWrapper();
		for(SpeciesReferenceWrapper srw : productList){
			SpeciesReferenceGlyph srg = rg.createSpeciesReferenceGlyph("SpeciesReferenceGlyph_" + rg.getReaction() + "_" + srw.getAlias());
			srg.setSpeciesReference(srw.getSpecies());
			srg.setRole(SpeciesReferenceRole.PRODUCT);
		}

		if (rw.isSetModifier()) {
			List<ModifierSpeciesReferenceWrapper> modifierList = rw.getListOfModifierWrapper();
			for (ModifierSpeciesReferenceWrapper msrw : modifierList) {
				SpeciesReferenceGlyph srg = rg.createSpeciesReferenceGlyph("ModifierSpeciesReferenceGlyph_" + rg.getReaction() + "_" + msrw.getAlias());
				srg.setSpeciesReference(msrw.getSpecies());
				srg.setRole(SpeciesReferenceRole.MODIFIER);
				
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
			tg.setOriginOfText( saw.getSpeciesWrapperAliased().getId());
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
	
	
	public static void main(String[] args){
		LayoutConverter converter;
		try {
			converter = new LayoutConverter(new File("sample/truncation.xml"));
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

	}
}
