package org.sbml.layoutconverter;

import java.io.File;
import java.io.IOException;
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
import org.sbml._2001.ns.celldesigner.Info;
import org.sbml._2001.ns.celldesigner.LineDirection;
import org.sbml._2001.ns.celldesigner.LinkAnchor;
import org.sbml._2001.ns.celldesigner.Modification;
import org.sbml._2001.ns.celldesigner.UsualView;
import org.sbml.jsbml.Model;
import org.sbml.jsbml.SBMLDocument;
import org.sbml.jsbml.SBMLException;
import org.sbml.jsbml.SBMLReader;
import org.sbml.jsbml.SBMLWriter;
import org.sbml.jsbml.ext.SBasePlugin;
import org.sbml.jsbml.ext.layout.BoundingBox;
import org.sbml.jsbml.ext.layout.CompartmentGlyph;
import org.sbml.jsbml.ext.layout.Dimensions;
import org.sbml.jsbml.ext.layout.Layout;
import org.sbml.jsbml.ext.layout.LayoutConstants;
import org.sbml.jsbml.ext.layout.LayoutModelPlugin;
import org.sbml.jsbml.ext.layout.Point;
import org.sbml.jsbml.ext.layout.TextGlyph;
import org.sbml.wrapper.CompartmentWrapper;
import org.sbml.wrapper.ModelWrapper;
import org.sbml.wrapper.ObjectFactory;
import org.sbml.wrapper.ReactionWrapper;
import org.sbml.wrapper.SpeciesAliasWrapper;
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
		
//		System.out.println(mWrapper.getId());
//		rwList = mWrapper.getListOfReactionWrapper();
//		swList = mWrapper.getListOfSpeciesWrapper();
//		cwList  = mWrapper.getListOfCompartmentWrapper();
//		sawList  = mWrapper.getListOfSpeciesAliasWrapper();
//		
//		for(ReactionWrapper rw : rwList){
//			System.out.println(rw.getId());
//			System.out.println("reaction : " +  rw.getReaction());
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
		} catch (SBMLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XMLStreamException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void convert(){
		convertModel();
		convertCompartments(mWrapper.getListOfCompartmentAliases());
	}
	
	public void convertCompartments(List<CompartmentAlias> caList){
		for(CompartmentAlias ca : caList){
			CompartmentGlyph cg = layout.createCompartmentGlyph("CompartmentGlyph_" + ca.getId());
			ca.setCompartment(ca.getCompartment());
			Bounds bound = ca.getBounds();
			BoundingBox bb = cg.createBoundingBox();
			Dimensions dimension = bb.createDimensions();
			dimension.setWidth(bound.getW().doubleValue());
			dimension.setHeight(bound.getW().doubleValue());
			dimension.setDepth(1d);
			bb.setDimensions(dimension);
			Point point = bb.createPosition();
			point.setX(bound.getX().doubleValue());
			point.setY(bound.getY().doubleValue());
			point.setZ(0d);
			bb.setPosition(point);
			
			
			TextGlyph tg = layout.createTextGlyph("TextGlyph_" + ca.getId());
			tg.setOriginOfText(ca.getId());
			tg.setGraphicalObject(cg);
			BoundingBox bb2 = tg.createBoundingBox();;
			Dimensions dimension2 = bb2.createDimensions();
			dimension2.setWidth(ca.getId().length() * 3);
			dimension2.setHeight(10);
			dimension.setDepth(1d);
			Point point2 = bb.createPosition();
			point2.setX(ca.getNamePoint().getX().doubleValue());
			point2.setY(ca.getNamePoint().getX().doubleValue());
			point2.setZ(0d);
		}
	}
	
	public void convertModel(){
		layout.setId("Layout_" + model.getId());
		layout.createDimensions(mWrapper.getSizeX(), mWrapper.getSizeX(), 0);
		
	}
	
	public void convertReactions(){
		
	}
	
	public void convertSpecies(){
		
	}
	
	
	
	
	public static void main(String[] args){
		LayoutConverter converter;
		try {
			converter = new LayoutConverter(new File("sample/ex3.xml"));
		} catch (JAXBException e) {
			System.err.println("Error unmarshaling XML");
			e.printStackTrace();
			return;
		} catch (XMLStreamException | IOException e) {
			System.err.println("Error reading SBML model");
			e.printStackTrace();
			return;
		}
		
		converter.convert();
		converter.print();

	}
}
