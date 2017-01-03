/*******************************************************************************
 * Copyright 2016 Kaito Ii
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package org.sbml.layoutconverter;

import java.awt.Color;
import java.awt.geom.Point2D;
import java.io.File;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLStreamException;

import org.sbml._2001.ns.celldesigner.BaseProduct;
import org.sbml._2001.ns.celldesigner.BaseReactant;
import org.sbml._2001.ns.celldesigner.LinkTarget;
import org.sbml._2001.ns.celldesigner.Modification;
import org.sbml._2001.ns.celldesigner.ProductLink;
import org.sbml._2001.ns.celldesigner.ReactantLink;
import org.sbml.extconverter.FBCConverter;
import org.sbml.extconverter.MultiConverter;
import org.sbml.jsbml.ListOf;
import org.sbml.jsbml.Reaction;
import org.sbml.jsbml.SimpleSpeciesReference;
import org.sbml.jsbml.ext.layout.BoundingBox;
import org.sbml.jsbml.ext.layout.CompartmentGlyph;
import org.sbml.jsbml.ext.layout.Curve;
import org.sbml.jsbml.ext.layout.CurveSegment;
import org.sbml.jsbml.ext.layout.Dimensions;
import org.sbml.jsbml.ext.layout.LayoutConstants;
import org.sbml.jsbml.ext.layout.LayoutModelPlugin;
import org.sbml.jsbml.ext.layout.LineSegment;
import org.sbml.jsbml.ext.layout.Point;
import org.sbml.jsbml.ext.layout.ReactionGlyph;
import org.sbml.jsbml.ext.layout.SpeciesGlyph;
import org.sbml.jsbml.ext.layout.SpeciesReferenceGlyph;
import org.sbml.jsbml.ext.layout.SpeciesReferenceRole;
import org.sbml.jsbml.ext.layout.TextGlyph;
import org.sbml.jsbml.ext.render.ColorDefinition;
import org.sbml.jsbml.ext.render.ListOfLocalRenderInformation;
import org.sbml.jsbml.ext.render.LocalRenderInformation;
import org.sbml.jsbml.ext.render.LocalStyle;
import org.sbml.jsbml.ext.render.RenderConstants;
import org.sbml.jsbml.ext.render.RenderGraphicalObjectPlugin;
import org.sbml.jsbml.ext.render.RenderGroup;
import org.sbml.jsbml.ext.render.RenderLayoutPlugin;
import org.sbml.jsbml.util.StringTools;
import org.sbml.wrapper.CompartmentAliasWrapper;
import org.sbml.wrapper.CompartmentWrapper;
import org.sbml.wrapper.ComplexSpeciesAliasWrapper;
import org.sbml.wrapper.ModelWrapper;
import org.sbml.wrapper.ObjectFactory;
import org.sbml.wrapper.ReactionWrapper;
import org.sbml.wrapper.SpeciesAliasWrapper;

// TODO: Auto-generated Javadoc
/**
 * The Class CD2LayoutConverter.
 *
 * @author Kaito Ii
 *         Date Created: Jun 26, 2016
 */
public class CD2LayoutConverter extends BaseLayoutConverter {

  /**
   * A {@link Logger} for this class.
   */
  private static final transient Logger logger = Logger.getLogger(CD2LayoutConverter.class.getName());

  /**
   * Instantiates a new CD 2 layout converter.
   *
   * @param file the file
   * @throws XMLStreamException the XML stream exception
   * @throws IOException Signals that an I/O exception has occurred.
   * @throws JAXBException the JAXB exception
   */
  public CD2LayoutConverter(File file) throws XMLStreamException, IOException, JAXBException {
    super(file);
    init(file);
  }

  /**
   * Instantiates a new CD 2 layout converter.
   *
   * @param file the file
   * @param outputFileName the output file name
   * @throws XMLStreamException the XML stream exception
   * @throws IOException Signals that an I/O exception has occurred.
   * @throws JAXBException the JAXB exception
   */
  public CD2LayoutConverter(File file, String outputFileName) throws XMLStreamException, IOException, JAXBException {
    super(file, outputFileName);
    init(file);
  }


  /**
   * Instantiates a new CD 2 layout converter.
   *
   * @param file the file
   * @param defaultCompartment the default compartment
   * @param outputFileName the output file name
   * @throws XMLStreamException the XML stream exception
   * @throws IOException Signals that an I/O exception has occurred.
   * @throws JAXBException the JAXB exception
   */
  public CD2LayoutConverter(File file, boolean defaultCompartment, String outputFileName) throws XMLStreamException, IOException, JAXBException {
    super(file, defaultCompartment, outputFileName);
    init(file);
  }

  /**
   * Instantiates a new CD 2 layout converter.
   *
   * @param file the file
   * @param defaultCompartment the default compartment
   * @throws XMLStreamException the XML stream exception
   * @throws IOException Signals that an I/O exception has occurred.
   * @throws JAXBException the JAXB exception
   */
  public CD2LayoutConverter(File file, boolean defaultCompartment) throws XMLStreamException, IOException, JAXBException {
    super(file, defaultCompartment);
    init(file);
  }

  /**
   * Instantiates a new CD 2 layout converter.
   *
   * @param file the file
   * @param options the options
   * @throws XMLStreamException the XML stream exception
   * @throws IOException Signals that an I/O exception has occurred.
   * @throws JAXBException the JAXB exception
   */
  public CD2LayoutConverter(File file, ApplicationOption options) throws XMLStreamException, IOException, JAXBException {
    super(file, options);
    convert2FBC = options.isConvert2FBC();
    init(file);
  }

  /**
   * Inits the.
   *
   * @param file the file
   * @throws IOException Signals that an I/O exception has occurred.
   * @throws JAXBException the JAXB exception
   * @throws XMLStreamException the XML stream exception
   */
  public void init(File file) throws IOException, JAXBException, XMLStreamException{
    if (!SBMLUtil.isSetCellDesignerNameSpace(document)) {
      throw new IOException("Missing CellDesigner Namespace");
    }
    if (!SBMLUtil.isLevelAndVersionMatchWithCD(file)) {
      throw new IOException(MessageFormat.format(
        "Level and Version does not match the CellDesigner defaults Level {0,number,integer} Version {1,number,integer}.",
        SBMLUtil.DEFAULT_CELLDESIGNER_SBML_LEVEL,
        SBMLUtil.DEFAULT_CELLDESIGNER_SBML_VERSION));
    }

    document = SBMLLevelandVersionHandler.upgrade(document);

    mWrapper = ObjectFactory.unmarshalSBML(file);
    model = document.getModel();
    LayoutModelPlugin mplugin = (LayoutModelPlugin) model.getPlugin(LayoutConstants.shortLabel);
    layout = mplugin.createLayout();
  }

  /**
   * Convert.
   */
  /* (non-Javadoc)
   * @see org.sbml.layoutconverter.abstractLayoutConverter#convert()
   */
  @Override
  public void convert() {
    convertModelToLayout(mWrapper);
    convertCompartmentsToLayout(mWrapper.getListOfCompartmentAliasWrapper());
    convertComplexAliasToLayout(mWrapper.getListOfComplexSpeciesAliasWrapper());
    convertSpeciesAliasToLayout(mWrapper.getListOfSpeciesAliasWrapper());
    convertReactionsToLayout(mWrapper.getListOfReactionWrapper());

    if (convert2FBC) {
      FBCConverter fbcConverter = new FBCConverter(model, mWrapper);
      fbcConverter.convert();
    }

    if (convert2Multi) {
      MultiConverter multiConverter = new MultiConverter(model, mWrapper);
      multiConverter.convert();
    }

    SBMLUtil.removeEmptyLists(document);
  }

  /**
   * Convert model to layout.
   *
   * @param mWrapper
   *            void TODO
   */
  public void convertModelToLayout(ModelWrapper mWrapper) {
    layout.setId("Layout_" + model.getId());
    layout.createDimensions(mWrapper.getW(), mWrapper.getH(), 1d);

    // Render information
    RenderLayoutPlugin lrp = (RenderLayoutPlugin) layout.getPlugin(RenderConstants.shortLabel);
    ListOfLocalRenderInformation llri = lrp.getListOfLocalRenderInformation();
    llri.setVersionMajor(1);
    llri.setVersionMinor(0);
    LocalRenderInformation lri = lrp.createLocalRenderInformation("celldesigner");
    lri.setProgramName("CellDesigner");
    lri.setProgramVersion(StringTools.toString(mWrapper.getModelVersion()));
  }

  /**
   * Convert compartments to layout.
   *
   * @param cawList
   *            the caw list
   */
  public void convertCompartmentsToLayout(List<CompartmentAliasWrapper> cawList) {
    if (convertDefaultCompartment) {
      convertDefaultCompartment();
    }

    cawList = ModelWrapper.reorderCompartmentAccordingToPosition(cawList);

    for (CompartmentAliasWrapper caw : cawList) {
      configureCompartmentGlyph(
        layout.createCompartmentGlyph("CompartmentGlyph_" + caw.getId()),
        caw.getCompartment(), caw.getX(), caw.getY(), caw.getH(), caw.getW(),
        caw.getId(), caw.getNameX(), caw.getNameY(), caw.getId().length() * 3d);
    }
  }

  /**
   * 
   * @param cg
   * @param compartment
   * @param x
   * @param y
   * @param h
   * @param w
   * @param labelId
   * @param labelX
   * @param labelY
   * @param labelW
   */
  private void configureCompartmentGlyph(CompartmentGlyph cg,
    String compartment, double x, double y, double h, double w, String labelId,
    double labelX, double labelY, double labelW) {
    cg.setCompartment(compartment);
    BoundingBox bb = cg.createBoundingBox();
    Dimensions dimension = bb.createDimensions();
    dimension.setWidth(w);
    dimension.setHeight(h);
    dimension.setDepth(1d);
    Point point = bb.createPosition();
    point.setX(x);
    point.setY(y);
    point.setZ(0d);

    configureTextGlyph(layout.createTextGlyph("TextGlyph_" + labelId),
      compartment, cg.getId(), labelX, labelY, 10d, labelW);
  }

  /**
   * Convert default compartment.
   */
  public void convertDefaultCompartment() {
    CompartmentWrapper cw = mWrapper.getCompartmentWrapperById("default");
    if (cw != null) {
      String text = cw.getName();
      if ((text == null) || (text.length() == 0)) {
        text = cw.getId();
      }
      configureCompartmentGlyph(
        layout.createCompartmentGlyph("CompartmentGlyph_" + cw.getId()),
        cw.getId(), 0, 0, mWrapper.getH(), mWrapper.getW(), cw.getId(),
        mWrapper.getW() / 2d - text.length() * 3d / 2d,
        mWrapper.getH() - 10d, text.length() * 3d);
    }
  }

  /**
   * Convert reactions to layout.
   *
   * @param rList
   *            void TODO
   */
  public void convertReactionsToLayout(List<ReactionWrapper> rList) {
    for (ReactionWrapper rw : rList) {
      convertReactionToLayout(rw);
    }
  }

  /**
   * @param rw
   */
  private void convertReactionToLayout(ReactionWrapper rw) {
    ReactionGlyph rg = layout.createReactionGlyph("ReactionGlyph_" + rw.getId());
    rg.setReference(rw.getId());
    ListOf<SpeciesReferenceGlyph> srgList = createSpeciesReferenceGlyph(rg, rw);
    List<BaseReactant> brsList = rw.getBaseReactants();
    List<BaseProduct> prsList = rw.getBaseProducts();
    List<Point2D.Double> editPointList = rw.getEditPointsAsList();
    int rectangleIndex = rw.getRectangleIndex();
    List<LineSegment> lsList = null;
    Point centerPoint = new Point();

    if ((brsList.size() == 1) && (prsList.size() == 1)) {
      BaseReactant br = brsList.get(0);
      BaseProduct bp = prsList.get(0);
      SpeciesReferenceGlyph srg1 = srgList.get("srGlyphReactant_" + rg.getReaction() + "_" + br.getSpecies());
      SpeciesReferenceGlyph srg2 = srgList.get("srGlyphProduct_" + rg.getReaction() + "_" + bp.getSpecies());

      Point startPoint = LayoutUtil.createAdjustedPoint(srg1, br.getLinkAnchor().getPosition());
      Point endPoint = LayoutUtil.createAdjustedPoint(srg2, bp.getLinkAnchor().getPosition());
      Point reactantPoint = LayoutUtil.createCenterPoint(srg1);
      Point productPoint = LayoutUtil.createCenterPoint(srg2);
      lsList = LayoutUtil.createListOfLineSegment(startPoint,
        endPoint, reactantPoint, productPoint, editPointList, rectangleIndex);

      Curve curve = srg1.createCurve();
      for (int i = 0; i <= rectangleIndex; i++) {
        curve.addCurveSegment(lsList.get(i));
      }

      centerPoint = curve.getCurveSegment(rectangleIndex).getEnd().clone();

      curve = srg2.createCurve();
      for (int i = rectangleIndex + 1; i < lsList.size(); i++) {
        curve.addCurveSegment(lsList.get(i));
      }

    } else if (rw.getReactionType().equals("HETERODIMER_ASSOCIATION")) {
      BaseReactant br1 = brsList.get(0);
      SpeciesReferenceGlyph srg1 = srgList.get("srGlyphReactant_" + rg.getReaction() + "_" + br1.getSpecies());
      SpeciesGlyph sg1 = srg1.getSpeciesGlyphInstance();
      Point startPoint = LayoutUtil.createAdjustedPoint(sg1, br1.getLinkAnchor().getPosition());

      BaseReactant br2 = brsList.get(1);
      SpeciesReferenceGlyph srg2 = srgList.get("srGlyphReactant_" + rg.getReaction() + "_" + br2.getSpecies());
      SpeciesGlyph sg2 = srg2.getSpeciesGlyphInstance();
      Point endPoint1 = LayoutUtil.createAdjustedPoint(sg2, br2.getLinkAnchor().getPosition());

      BaseProduct bp1 = prsList.get(0);
      SpeciesReferenceGlyph srg3 = srgList.get("srGlyphProduct_" + rg.getReaction() + "_" + bp1.getSpecies());
      SpeciesGlyph sg3 = srg3.getSpeciesGlyphInstance();
      Point endPoint2 = LayoutUtil.createAdjustedPoint(sg3, bp1.getLinkAnchor().getPosition());

      Point reactantPoint1 = LayoutUtil.createCenterPoint(sg1);
      Point reactantPoint2 = LayoutUtil.createCenterPoint(sg2);
      Point productPoint1 = LayoutUtil.createCenterPoint(sg3);
      int num0 = rw.getEditPoints().getNum0();
      int num1 = rw.getEditPoints().getNum1();
      int num2 = rw.getEditPoints().getNum2();

      lsList = LayoutUtil.createListOfLineSegment(startPoint,
        endPoint1, endPoint2, reactantPoint1, reactantPoint2,
        productPoint1, editPointList, num0, num1, num2, rw.getReactionType(), rw.getEditPoints().getTShapeIndex());
      int reactant1 = num0;
      int reactant2 = num0 + num1 + 1;

      Curve curve = srg1.createCurve();
      for (int i = 0; i <= reactant1; i++) {
        curve.addCurveSegment(lsList.get(i));
      }

      curve = srg2.createCurve();
      for (int i = reactant1 + 1; i <= reactant2; i++) {
        curve.addCurveSegment(lsList.get(i));
      }

      curve = srg3.createCurve();
      for (int i = reactant2 + 1; i < lsList.size(); i++) {
        curve.addCurveSegment(lsList.get(i));
      }

      int tshapeIndex = rw.getEditPoints().getTShapeIndex();
      LineSegment ls = (LineSegment) curve.getCurveSegment(tshapeIndex);
      centerPoint = ls.getStart().clone();

    } else if (rw.getReactionType().equals("DISSOCIATION")
        || rw.getReactionType().equals("TRUNCATION")) {
      BaseReactant br1 = brsList.get(0);
      SpeciesReferenceGlyph srg1 = srgList.get("srGlyphReactant_" + rg.getReaction() + "_" + br1.getSpecies());
      Point startPoint = LayoutUtil.createAdjustedPoint(srg1, br1.getLinkAnchor().getPosition());

      BaseProduct bp1 = prsList.get(0);
      SpeciesReferenceGlyph srg2 = srgList.get("srGlyphProduct_" + rg.getReaction() + "_" + bp1.getSpecies());
      Point endPoint1 = LayoutUtil.createAdjustedPoint(srg2, bp1.getLinkAnchor().getPosition());

      BaseProduct bp2 = prsList.get(1);
      SpeciesReferenceGlyph srg3 = srgList.get("srGlyphProduct_" + rg.getReaction() + "_" + bp2.getSpecies());
      Point endPoint2 = LayoutUtil.createAdjustedPoint(srg3, bp2.getLinkAnchor().getPosition());

      Point reactantPoint = LayoutUtil.createCenterPoint(srg1);
      Point productPoint1 = LayoutUtil.createCenterPoint(srg2);
      Point productPoint2 = LayoutUtil.createCenterPoint(srg3);

      int num0 = rw.getEditPoints().getNum0();
      int num1 = rw.getEditPoints().getNum1();
      int num2 = rw.getEditPoints().getNum2();

      lsList = LayoutUtil.createListOfLineSegment(startPoint,
        endPoint1, endPoint2, reactantPoint, productPoint1,
        productPoint2, editPointList, num0, num1, num2, rw.getReactionType(), rw.getEditPoints().getTShapeIndex());
      int reactant1 = num0 ;
      int reactant2 = num0 + num1 + 1;

      Curve curve = srg1.createCurve();
      for (int i = 0; i <= reactant1; i++) {
        curve.addCurveSegment(lsList.get(i));
      }

      curve = srg2.createCurve();
      for (int i = reactant1 + 1; i <= reactant2; i++) {
        curve.addCurveSegment(lsList.get(i));
      }

      curve = srg3.createCurve();
      for (int i = reactant2 + 1; i < lsList.size(); i++) {
        curve.addCurveSegment(lsList.get(i));
      }

      curve = srg1.getCurve();
      int tshapeIndex = rw.getEditPoints().getTShapeIndex();
      LineSegment ls = (LineSegment) curve.getCurveSegment(curve.getCurveSegmentCount() - tshapeIndex - 1);
      centerPoint = ls.getEnd().clone();
    }

    if (rw.isSetModifier()) {
      for (Modification m : rw.getListOfModification()) {
        if (m.getType().contains("BOOLEAN")) {
          continue;
        }
        editPointList = LayoutUtil.createEditPointsAsList(m.getEditPoints());

        SpeciesReferenceGlyph srg = rg.getSpeciesReferenceGlyph("srGlyphModifier_" + rg.getReaction() + "_" + m.getModifiers());
        SpeciesGlyph sg = srg.getSpeciesGlyphInstance();
        Point modifierPoint = LayoutUtil.createCenterPoint(sg);
        LinkTarget lt = rw.getLinkTargetByModifier(m);
        Point startPoint = LayoutUtil.createAdjustedPoint(sg, lt.getLinkAnchor().getPosition());
        List<LineSegment> lsList2 = LayoutUtil.createListOfLineSegment(startPoint, centerPoint,
          modifierPoint, centerPoint, editPointList);
        createCurve(srg, lsList2);
      }
    }

    List<ReactantLink> rlList = rw.getListOfReactantLinks();
    List<ProductLink> plList = rw.getListOfProductLinks();

    for (ReactantLink link : rlList) {
      String lineType = link.getLine().getType();
      SpeciesReferenceGlyph srg = srgList.get("srGlyphReactant_" + rg.getReaction() + "_" + link.getReactant());
      SpeciesGlyph sg = srg.getSpeciesGlyphInstance();
      String position = link.getLinkAnchor() != null ? link.getLinkAnchor().getPosition() : "INACTIVE" ;
      Point startPoint = LayoutUtil.createAdjustedPoint(sg, position);
      Point endPoint = centerPoint;

      editPointList = new ArrayList<Point2D.Double>();
      List<LineSegment> lsList2;

      if (lineType.equals("Curve")) {
        lsList2 = LayoutUtil.createListOfBezier(startPoint, endPoint, lsList.get(rectangleIndex).getStart());

      } else if (rw.getReactionType().equals("HETERODIMER_ASSOCIATION")) {
        int num0 = rw.getEditPoints().getNum0();
        int num1 = rw.getEditPoints().getNum1();
        int tshapeIndex = rw.getEditPoints().getTShapeIndex();

        Point linkPoint = LayoutUtil.createLinkPoint(lsList.get(num0 + 1 + num1 + 1 + tshapeIndex).getStart(), endPoint);
        lsList2 = LayoutUtil.createListOfLineSegment(startPoint, linkPoint, editPointList);

      } else if (rw.getReactionType().equals("DISSOCIATION") || rw.getReactionType().equals("TRUNCATION")) {
        int num0 = rw.getEditPoints().getNum0();
        int tshapeIndex = rw.getEditPoints().getTShapeIndex();
        Point linkPoint = LayoutUtil.createLinkPoint(lsList.get(num0 - tshapeIndex).getStart(), endPoint);
        lsList2 = LayoutUtil.createListOfLineSegment(startPoint, linkPoint, editPointList);

      } else {
        Point linkPoint = LayoutUtil.createLinkPoint(lsList.get(rectangleIndex).getStart(), endPoint);
        lsList2 = LayoutUtil.createListOfLineSegment(startPoint, linkPoint, editPointList);
      }
      createCurve(srg, lsList2);
    }

    for (ProductLink link : plList) {
      String lineType = link.getLine().getType();
      SpeciesReferenceGlyph srg = srgList.get("srGlyphProduct_" + rg.getReaction() + "_" + link.getProduct());
      SpeciesGlyph sg = srg.getSpeciesGlyphInstance();
      String position = link.getLinkAnchor() != null ? link.getLinkAnchor().getPosition() : "INACTIVE" ;
      Point endPoint = LayoutUtil.createAdjustedPoint(sg, position);
      Point startPoint = centerPoint;
      editPointList = new ArrayList<Point2D.Double>();
      List<LineSegment> lsList2;

      if (lineType.equals("Curve")) {
        lsList2 = LayoutUtil.createListOfBezier(startPoint, endPoint, lsList.get(rectangleIndex + 1).getEnd());

      } else if (rw.getReactionType().equals("HETERODIMER_ASSOCIATION")) {
        int num0 = rw.getEditPoints().getNum0();
        int num1 = rw.getEditPoints().getNum1();
        int tshapeIndex = rw.getEditPoints().getTShapeIndex();

        Point linkPoint = LayoutUtil.createLinkPoint(lsList.get(num0 + 1 + num1 + 1 + tshapeIndex).getEnd(), startPoint);
        lsList2 = LayoutUtil.createListOfLineSegment(linkPoint, endPoint, editPointList);

      } else if (rw.getReactionType().equals("DISSOCIATION") || rw.getReactionType().equals("TRUNCATION")) {
        int num0 = rw.getEditPoints().getNum0();
        int tshapeIndex = rw.getEditPoints().getTShapeIndex();

        Point linkPoint = LayoutUtil.createLinkPoint(lsList.get(num0 - tshapeIndex).getEnd(), startPoint);
        lsList2 = LayoutUtil.createListOfLineSegment(linkPoint, endPoint, editPointList);

      } else {
        Point linkPoint = LayoutUtil.createLinkPoint(lsList.get(rectangleIndex + 1).getEnd(), startPoint);
        lsList2 = LayoutUtil.createListOfLineSegment(linkPoint, endPoint, editPointList);
      }

      createCurve(srg, lsList2);
    }

    configureTextGlyph(layout.createTextGlyph("TextGlyph_" + rw.getId()),
      rw.getId(), rg.getId(), centerPoint.getX(), centerPoint.getY(), 10d,
      (rg.isSetName() ? rg.getName().length() : rg.getId().length()) * 3d);

    BoundingBox bbox = rg.createBoundingBox();
    // TODO: make the size of the reaction node somehow variable!
    Dimensions dim = bbox.createDimensions(8d, 8d, 1d);
    bbox.createPosition(
      centerPoint.getX() - dim.getWidth() / 2d,
      centerPoint.getY() - dim.getHeight() / 2d, 0d);

    for (SpeciesReferenceGlyph srg : srgList) {

      createSpeciesReferenceGlyphBbox(rg, srg);

      if (srg.isSetSpeciesReferenceRole()) {

        if ((srg.getRole() == SpeciesReferenceRole.SIDEPRODUCT) || (srg.getSpeciesReferenceRole() == SpeciesReferenceRole.PRODUCT)) {
          if (srg.isSetCurve()) {
            Curve curve = srg.getCurve();
            if (curve.isSetListOfCurveSegments()) {
              CurveSegment cs = curve.getListOfCurveSegments().getLast();
              cs.setEnd(LayoutUtil.adjustOverlappingEndPoint(cs, srg));
            } else {
              logger.warning(MessageFormat.format(
                "No curveSegment defined for speciesReferenceGlyph ''{0}'', seems to be an error in the model.",
                srg.getId()));
            }
          } else {
            logger.fine(MessageFormat.format(
              "No curve defined for speciesReferenceGlyph ''{0}'', nothing to do.",
              srg.getId()));
          }
        }
      }
    }
  }

  /**
   * The species reference glyph should not have the bbox of the speciesGlyph it
   * references, but the box containing the arrow from/to the reaction glyph. We
   * therefore will now find the bounding box between srg and rg.
   * 
   * @param rg
   * @param srg
   */
  private void createSpeciesReferenceGlyphBbox(ReactionGlyph rg,
    SpeciesReferenceGlyph srg) {
    SpeciesGlyph sg = srg.getSpeciesGlyphInstance(); // cannot be null! (otherwise there is an error elsewhere during the conversion)
    BoundingBox sgBbox = sg.getBoundingBox();

    Point sgPos = sgBbox.getPosition();
    Point rgPos = rg.getBoundingBox().getPosition();
    Dimensions sgDim = sgBbox.getDimensions(), rgDim = rg.getBoundingBox().getDimensions();
    double sgWhalf = sgDim.getWidth() / 2d;
    double rgWhalf = rgDim.getWidth() / 2d;
    double sgHhalf = sgDim.getHeight() / 2d;
    double rgHhalf = rgDim.getHeight() / 2d;
    double minX = Math.min(sgPos.x() + sgWhalf, rgPos.x() + rgWhalf);
    double maxX = Math.max(sgPos.x() + sgWhalf, rgPos.x() + rgWhalf);
    double minY = Math.min(sgPos.y() + sgHhalf, rgPos.y() + rgHhalf);
    double maxY = Math.max(sgPos.x() + sgHhalf, rgPos.y() + rgHhalf);
    // Now we get a bounding box with the upper left corner being the center of
    // either the reaction glyph or the species glyph and then going to the
    // center of the other node.
    BoundingBox bbox = srg.getBoundingBox();
    bbox.getPosition().setX(minX);
    bbox.getPosition().setY(minY);
    bbox.getDimensions().setWidth(maxX - minX);
    bbox.getDimensions().setHeight(maxY - minY);
  }

  /**
   * @param srg
   * @param lsl
   */
  public void createCurve(SpeciesReferenceGlyph srg, List<LineSegment> lsl) {
    Curve curve = srg.createCurve();
    for (LineSegment ls : lsl) {
      curve.addCurveSegment(ls);
    }
  }

  /**
   * Creates the species reference glyph.
   *
   * @param rg the rg
   * @param rw the rw
   * @return the list of
   */
  public ListOf<SpeciesReferenceGlyph> createSpeciesReferenceGlyph(ReactionGlyph rg, ReactionWrapper rw) {
    Reaction reaction = (Reaction) rg.getReactionInstance();
    ListOf<SpeciesReferenceGlyph> srgList = rg.getListOfSpeciesReferenceGlyphs();
    List<BaseReactant> baseReactantList = rw.getBaseReactants();
    List<BaseProduct> baseProductList = rw.getBaseProducts();
    List<ReactantLink> reactantLinkList = rw.getListOfReactantLinks();
    List<ProductLink> productLinkList = rw.getListOfProductLinks();
    List<Modification> modificationList = rw.getListOfModification();

    for (BaseReactant br : baseReactantList) {
      createSpeciesReferenceGlyph(
        "srGlyphReactant_" + rg.getReaction() + "_" + br.getSpecies(),
        rg, reaction.getListOfReactants(), br.getSpecies(),
        SpeciesGlyph.class.getSimpleName(), br.getAlias(),
        SpeciesReferenceRole.SUBSTRATE);
    }

    for (ReactantLink link : reactantLinkList) {
      createSpeciesReferenceGlyph(
        SBMLUtil.createId(model, "srGlyphReactant_" + rg.getReaction() + "_" + link.getReactant()),
        rg, reaction.getListOfReactants(), link.getReactant(),
        SpeciesGlyph.class.getSimpleName(), link.getAlias(),
        SpeciesReferenceRole.SUBSTRATE);
    }

    for (BaseProduct bp : baseProductList) {
      createSpeciesReferenceGlyph(
        SBMLUtil.createId(model, "srGlyphProduct_" + rg.getReaction() + "_" + bp.getSpecies()),
        rg, reaction.getListOfProducts(), bp.getSpecies(),
        SpeciesGlyph.class.getSimpleName(), bp.getAlias(),
        SpeciesReferenceRole.PRODUCT);
    }

    for (ProductLink link : productLinkList) {
      createSpeciesReferenceGlyph(
        SBMLUtil.createId(model, "srGlyphProduct_" + rg.getReaction() + "_" + link.getProduct()),
        rg, reaction.getListOfProducts(), link.getProduct(),
        SpeciesGlyph.class.getSimpleName(), link.getAlias(),
        SpeciesReferenceRole.PRODUCT);
    }

    if (rw.isSetModifier()) {
      for (Modification m : modificationList) {
        if (m.getType().contains("BOOLEAN")) {
          // TODO: implement support for logical rules here
          continue;
        }
        String modType = rw.getModifierTypeByModifierId(m.getModifiers());
        SpeciesReferenceRole role = SpeciesReferenceRole.MODIFIER;
        if (modType.equals("CATALYSIS") || modType.equals("UNKNOWN_CATALYSIS")) {
          role = SpeciesReferenceRole.ACTIVATOR;
        } else if (modType.equals("INHIBITION") || modType.equals("UNKNOWN_INHIBITION")) {
          role = SpeciesReferenceRole.INHIBITOR;
        }
        createSpeciesReferenceGlyph(
          SBMLUtil.createId(model, "srGlyphModifier_" + rg.getReaction() + "_" + m.getModifiers()),
          rg, reaction.getListOfModifiers(), m.getModifiers(),
          SpeciesGlyph.class.getSimpleName(), m.getAliases(), role);
      }
    }

    return srgList;
  }

  /**
   * @param id
   *        the identifier of the {@link SpeciesReferenceGlyph} to be created
   * @param rg
   *        the {@link ReactionGlyph} whose sub-element this species reference
   *        glyph will become
   * @param participants
   *        a list of reaction participants from the corresponding core reaction
   * @param species
   *        the reference to the identifier of the reaction participant from
   *        SBML core
   * @param prefix
   *        the id prefix for the reference of the glyph element
   * @param alias
   *        this is the second component to be concatenated with the prefix to
   *        give the id of the glyph element
   * @param role
   *        the role that this species reference glyph plays within this
   *        reaction glyph.
   * @return the newly creates {@link SpeciesReferenceGlyph}.
   */
  public SpeciesReferenceGlyph createSpeciesReferenceGlyph(String id, ReactionGlyph rg, ListOf<? extends SimpleSpeciesReference> participants, String species, String prefix, String alias, SpeciesReferenceRole role) {
    SpeciesReferenceGlyph srg = rg.createSpeciesReferenceGlyph(id);
    SimpleSpeciesReference ssr = SBMLUtil.findReferenceBySpeciesId(participants, species);
    if (ssr != null) {
      if (!ssr.isSetId()) {
        // set the id of each simple species reference when first needed.
        Reaction parent = (Reaction) ssr.getParent().getParent();
        String elementName = participants.getElementName();
        String lType = elementName.substring(6, elementName.length() - 1);
        ssr.setId(parent.getId() + "_" + lType + "_" + (participants.indexOf(ssr) + 1));
      }
      srg.setSpeciesReference(ssr);
    }
    srg.setRole(role);
    srg.setSBOTerm(role.toSBOterm());
    srg.setSpeciesGlyph(prefix + '_' + alias);

    // This initial bounding box is needed for building up the curve, a more precise box will be created later on.
    srg.setBoundingBox(srg.getSpeciesGlyphInstance().getBoundingBox().clone());

    return srg;
  }

  /**
   * Convert species alias to layout.
   *
   * @param saList
   *            void TODO
   */
  public void convertSpeciesAliasToLayout(List<SpeciesAliasWrapper> saList) {

    RenderLayoutPlugin lrp = (RenderLayoutPlugin) layout.getPlugin(RenderConstants.shortLabel);
    LocalRenderInformation lri = lrp.getLocalRenderInformation(0);
    Map<String, ColorDefinition> colorMap = new HashMap<>();

    for (SpeciesAliasWrapper saw : saList) {
      SpeciesGlyph sg = layout.createSpeciesGlyph("SpeciesGlyph_" + saw.getId());
      if (model.getSpecies(saw.getSpecies()) != null) {
        sg.setReference(saw.getSpecies());
      }

      // Render stuff
      String color = saw.getUsualView().getPaint().getColor();
      if (color != null) {
        color = color.substring(2);
        ColorDefinition cd;
        if (!colorMap.containsKey(color)) {
          cd = new ColorDefinition(layout.getLevel(), layout.getVersion());
          cd.setId("color_" + (lri.getListOfColorDefinitions().size() + 1));
          cd.setValue(Color.decode('#' + color));
          lri.addColorDefinition(cd);
          colorMap.put(color, cd);
        } else {
          cd = colorMap.get(color);
        }

        String id = "fill" + cd.getId();
        LocalStyle ls = lri.getListOfLocalStyles().get(id);
        if (ls == null) {
          RenderGroup g = new RenderGroup(layout.getLevel(), layout.getVersion());
          //          g.setNamespace(RenderConstants.namespaceURI);
          ls = new LocalStyle(id, layout.getLevel(), layout.getVersion(), g);
          lri.addLocalStyle(ls);
          g.setFill(cd.getId());
        }
        /*
         *  A link from the style to the element that is to be rendered can lead
         *  to extremely long list of ids and is therefore not the preferred way.
         *  Instead, we define a role id and refer from the rendered element to
         *  that style.
         */
        //ls.getIDList().add(sg.getId());
        id = "style_" + id;
        if (!ls.getRoleList().contains(id)) {
          ls.getRoleList().add(id);
        }
        RenderGraphicalObjectPlugin rgop = (RenderGraphicalObjectPlugin) sg.getPlugin(RenderConstants.shortLabel);
        rgop.setObjectRole(id);
      }
      // End render

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
      configureTextGlyph(tg, saw.getSpecies(), sg.getId(),
        saw.getX(),
        saw.getY() + saw.getH() / 5d,
        10d, saw.getId().length() * 3d,
        model.getSpecies(saw.getSpecies()) == null);
      // x = saw.getX() + saw.getW() / 2d - dimension2.getWidth() / 2d
      // y = saw.getY() + saw.getH() / 2d - dimension2.getHeight() / 2d
    }
  }

  /**
   * Convert complex alias to layout.
   *
   * @param csaList
   *            void TODO
   */
  public void convertComplexAliasToLayout(
    List<ComplexSpeciesAliasWrapper> csaList) {
    for (ComplexSpeciesAliasWrapper csaw : csaList) {
      SpeciesGlyph sg = layout.createSpeciesGlyph("SpeciesGlyph_" + csaw.getId());
      if (model.getSpecies(csaw.getSpecies()) != null) {
        sg.setReference(csaw.getSpecies());
      }

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

      String text = csaw.getSpeciesWrapper().getName();
      if (text == null) {
        text = csaw.getSpeciesWrapper().getId();
      }
      configureTextGlyph(layout.createTextGlyph("TextGlyph_" + csaw.getId()),
        csaw.getSpecies(), sg.getId(), csaw.getX(), csaw.getY() + csaw.getH() / 2d,
        10d, text.length() * 3d);
      // x = csaw.getX() + csaw.getW() / 2d - dimension2.getWidth() / 2d
      // y = csaw.getY() + csaw.getH()
    }
  }

  /**
   * 
   * @param tg
   * @param originOfText
   * @param graphicalObject
   * @param x
   * @param y
   * @param h
   * @param w
   */
  private void configureTextGlyph(TextGlyph tg, String originOfText, String graphicalObject, double x, double y, double h, double w) {
    configureTextGlyph(tg, originOfText, graphicalObject, x, y, h, w, false);
  }

  /**
   * 
   * @param tg
   * @param text either the text or the origin of text depending on whether isText is true or false, respectively.
   * @param graphicalObject
   * @param x
   * @param y
   * @param h
   * @param w
   * @param isText
   */
  private void configureTextGlyph(TextGlyph tg, String text, String graphicalObject, double x, double y, double h, double w, boolean isText) {
    if (text != null) {
      if (!isText) {
        tg.setOriginOfText(text);
      } else {
        tg.setText(text);
      }
    }
    if (graphicalObject != null) {
      tg.setGraphicalObject(graphicalObject);
    }
    tg.createBoundingBox(w, h, 1d, x, y, 0d);
  }

}
