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

import java.awt.geom.Point2D;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLStreamException;

import org.sbml._2001.ns.celldesigner.Modification;
import org.sbml.jsbml.Compartment;
import org.sbml.jsbml.JSBML;
import org.sbml.jsbml.ListOf;
import org.sbml.jsbml.ModifierSpeciesReference;
import org.sbml.jsbml.NamedSBase;
import org.sbml.jsbml.Reaction;
import org.sbml.jsbml.SBMLDocument;
import org.sbml.jsbml.SBMLException;
import org.sbml.jsbml.SBMLReader;
import org.sbml.jsbml.SBase;
import org.sbml.jsbml.SimpleSpeciesReference;
import org.sbml.jsbml.Species;
import org.sbml.jsbml.SpeciesReference;
import org.sbml.jsbml.ext.layout.CompartmentGlyph;
import org.sbml.jsbml.ext.layout.CubicBezier;
import org.sbml.jsbml.ext.layout.CurveSegment;
import org.sbml.jsbml.ext.layout.Layout;
import org.sbml.jsbml.ext.layout.LayoutModelPlugin;
import org.sbml.jsbml.ext.layout.Point;
import org.sbml.jsbml.ext.layout.ReactionGlyph;
import org.sbml.jsbml.ext.layout.SpeciesGlyph;
import org.sbml.jsbml.ext.layout.SpeciesReferenceGlyph;
import org.sbml.jsbml.ext.layout.SpeciesReferenceRole;
import org.sbml.jsbml.ext.layout.TextGlyph;
import org.sbml.wrapper.CompartmentAliasWrapper;
import org.sbml.wrapper.ModifierSpeciesReferenceWrapper;
import org.sbml.wrapper.ObjectFactory;
import org.sbml.wrapper.ReactionWrapper;
import org.sbml.wrapper.SpeciesAliasWrapper;
import org.sbml.wrapper.SpeciesReferenceWrapper;
import org.sbml.wrapper.SpeciesWrapper;

// TODO: Auto-generated Javadoc
/**
 * The Class Layout2CDConverter.
 *
 * @author Kaito Ii
 *         Date Created: Jun 26, 2016
 */
public class Layout2CDConverter extends BaseLayoutConverter {

  /** The downgrade document. */
  private SBMLDocument downgrade_document;


  /**
   * Instantiates a new layout 2 CD converter.
   *
   * @param file
   *        the file
   * @throws XMLStreamException
   *         the XML stream exception
   * @throws IOException
   *         Signals that an I/O exception has occurred.
   * @throws JAXBException
   *         the JAXB exception
   */
  public Layout2CDConverter(File file) throws XMLStreamException, IOException,
    JAXBException {
    super(file);
    init();
  }


  /**
   * Instantiates a new layout 2 CD converter.
   *
   * @param file
   *        the file
   * @param outputFileName
   *        the output file name
   * @throws XMLStreamException
   *         the XML stream exception
   * @throws IOException
   *         Signals that an I/O exception has occurred.
   * @throws JAXBException
   *         the JAXB exception
   */
  public Layout2CDConverter(File file, String outputFileName)
    throws XMLStreamException, IOException, JAXBException {
    super(file, outputFileName);
    init();
  }


  /**
   * Instantiates a new layout 2 CD converter.
   *
   * @param file
   *        the file
   * @param defaultCompartment
   *        the default compartment
   * @param outputFileName
   *        the output file name
   * @throws XMLStreamException
   *         the XML stream exception
   * @throws IOException
   *         Signals that an I/O exception has occurred.
   * @throws SBMLException
   *         the SBML exception
   * @throws JAXBException
   *         the JAXB exception
   */
  public Layout2CDConverter(File file, boolean defaultCompartment,
    String outputFileName) throws XMLStreamException, IOException,
    SBMLException, JAXBException {
    super(file, defaultCompartment, outputFileName);
    init();
  }


  /**
   * Instantiates a new layout 2 CD converter.
   *
   * @param file
   *        the file
   * @param defaultCompartment
   *        the default compartment
   * @throws XMLStreamException
   *         the XML stream exception
   * @throws IOException
   *         Signals that an I/O exception has occurred.
   * @throws SBMLException
   *         the SBML exception
   * @throws JAXBException
   *         the JAXB exception
   */
  public Layout2CDConverter(File file, boolean defaultCompartment)
    throws XMLStreamException, IOException, SBMLException, JAXBException {
    super(file, defaultCompartment);
    init();
  }


  /**
   * @param file
   * @param options
   * @throws IOException
   * @throws XMLStreamException
   * @throws JAXBException
   * @throws SBMLException
   */
  public Layout2CDConverter(File file, ApplicationOption options)
    throws XMLStreamException, IOException, SBMLException, JAXBException {
    super(file, options);
    init();
  }


  /**
   * Inits the.
   *
   * @throws IOException
   *         Signals that an I/O exception has occurred.
   * @throws SBMLException
   *         the SBML exception
   * @throws JAXBException
   *         the JAXB exception
   * @throws XMLStreamException
   *         the XML stream exception
   */
  public void init() throws IOException, SBMLException, JAXBException,
    XMLStreamException {
    if (!SBMLUtil.isSetLayoutNameSpace(document))
      throw new IOException("Missing Layout Namespace");
    downgrade_document = SBMLLevelandVersionHandler.downgrade(document.clone());
    mWrapper = ObjectFactory.unmarshalSBMLFromString(JSBML.writeSBMLToString(downgrade_document));
    model = document.getModel();
  }


  /*
   * (non-Javadoc)
   * @see org.sbml.layoutconverter.abstractLayoutConverter#convert()
   */
  @Override
  public void convert() {
    LayoutModelPlugin mplugin = (LayoutModelPlugin) (model.getPlugin("layout"));
    layout = mplugin.getLayout(0);
    convertModelToCD(layout);
    convertCompartmentsToCD(layout.getListOfCompartmentGlyphs());
    convertSpeciesToCD(layout.getListOfSpeciesGlyphs());
    convertReactionsToCD(layout.getListOfReactionGlyphs());
    convertTextToCD(layout.getListOfTextGlyphs());
    try {
      String s = ObjectFactory.generateXMLString(mWrapper);
      document = SBMLReader.read(s);
      document = SBMLUtil.setMaths(document, downgrade_document);
    } catch (JAXBException e) {
      e.printStackTrace();
    } catch (XMLStreamException e) {
      e.printStackTrace();
    }
  }


  /**
   * Convert model to CD.
   *
   * @param layout
   *        the layout
   */
  public void convertModelToCD(Layout layout) {
    mWrapper.setDimension(layout.getDimensions().getWidth(),
      layout.getDimensions().getHeight());
    mWrapper.setModelVersion(SBMLUtil.DEFALUT_CELLDESIGNER_MODEL_VERSION);
  }


  /**
   * Convert compartments to CD.
   *
   * @param cgList
   *        the cg list
   */
  public void convertCompartmentsToCD(List<CompartmentGlyph> cgList) {
    for (CompartmentGlyph cg : cgList) {
      Compartment c = (Compartment) cg.getCompartmentInstance();
      if (!c.getId().equals("default")
        || (c.getId().equals("default") && convertDefaultCompartment))
        mWrapper.createCompartmentAliasWrapper(cg);
    }
  }


  /**
   * Convert species to CD.
   *
   * @param sgList
   *        the sg list
   */
  public void convertSpeciesToCD(List<SpeciesGlyph> sgList) {
    HashMap<String, Integer> hashSpeciesCounter = new HashMap<String, Integer>();
    for (SpeciesGlyph sg : sgList) {
      if (sg.isSetSpecies()) {
        Species s = (Species) sg.getSpeciesInstance();
        SpeciesAliasWrapper saw = mWrapper.createSpeciesAliasWrapper(sg);
        String cid = s.getCompartment();
        String sid = s.getId() + "alias";
        if (hashSpeciesCounter.isEmpty()
          || !hashSpeciesCounter.containsKey(sid)) {
          hashSpeciesCounter.put(sid, 1);
        } else {
          hashSpeciesCounter.put(sid, hashSpeciesCounter.get(sid) + 1);
        }
        sid = sid + hashSpeciesCounter.get(sid);
        saw.setId(sid);
        if (!cid.equals("default")) {
          saw.setCompartmentAlias(cid + "alias");
          saw.setCompartmentAliasWrapper(cid + "alias");
        }
        int sboterm = SBMLUtil.intSBOTermForDEFAULT_SPECIES;
        if (sg.isSetSBOTerm()) {
          sboterm = s.getSBOTerm();
        } else if (s.isSetSBOTerm()) {
          sboterm = s.getSBOTerm();
        }
        mWrapper.createSpeciesObjectFromSBOTerm(sg, sboterm);
        String clazz = SBMLUtil.SBOTermToCDSpecies(sboterm);
        SpeciesWrapper sw = mWrapper.getSpeciesWrapperById(s.getId());
        sw.setClazz(clazz);
        if (clazz.equals("PROTEIN")) {
          sw.getSpeciesIdentity().setProteinReference(
            mWrapper.getProteinBySpeciesId(s.getId()).getId());
        } else if (clazz.equals("GENE")) {
          sw.getSpeciesIdentity().setGeneReference(
            mWrapper.getGeneBySpeciesId(s.getId()).getId());
        } else if (clazz.equals("RNA")) {
          sw.getSpeciesIdentity().setRnaReference(
            mWrapper.getRNABySpeciesId(s.getId()).getId());
        } else if (clazz.equals("ANTISENSE_RNA")) {
          sw.getSpeciesIdentity().setAntisensernaReference(
            mWrapper.getAntisenseRNABySpeciesId(s.getId()).getId());
        } else {
          sw.getSpeciesIdentity().setName(sw.getId());
        }
        // CompartmentGlyph cg =
        // getCompartmentGlyphByCompartmentId(s.getCompartment());
        // if(cg != null)
        // sw.setPositionToCompartment(LayoutUtil.getPositionToCompartment(sg,
        // cg));
        // else
        sw.setPositionToCompartment("inside");
      } else {
        // species glyph with no reference
      }
    }
  }


  /**
   * Gets the compartment glyph by compartment id.
   *
   * @param id
   *        the id
   * @return the compartment glyph by compartment id
   */
  public CompartmentGlyph getCompartmentGlyphByCompartmentId(String id) {
    for (CompartmentGlyph cg : layout.getListOfCompartmentGlyphs()) {
      if (cg.getCompartmentInstance().getId().equals(id))
        return cg;
    }
    return null;
  }


  /**
   * Convert reactions to CD.
   *
   * @param rgList
   *        the rg list
   */
  public void convertReactionsToCD(List<ReactionGlyph> rgList) {
    for (ReactionGlyph rg : rgList) {
      Reaction r = (Reaction) rg.getReactionInstance();
      ListOf<SpeciesReference> reactantList = r.getListOfReactants();
      ListOf<SpeciesReference> productList = r.getListOfProducts();
      ListOf<ModifierSpeciesReference> modifierList = r.getListOfModifiers();
      ReactionWrapper rw = mWrapper.getReactionWrapperById(r.getId());
      // if the reaction has only reactant or product rw is null
      if (rw == null) {
        continue;
      }
      int sboterm = SBMLUtil.intSBOTermForDEFAULT_REACTION;
      if (rg.isSetSBOTerm()) {
        sboterm = rg.getSBOTerm();
      } else if (r.isSetSBOTerm()) {
        sboterm = r.getSBOTerm();
      }
      rw.setReactionType(SBMLUtil.SBOTermToCDReaction(sboterm));
      for (SpeciesReferenceGlyph srg : rg.getListOfSpeciesReferenceGlyphs()) {
        NamedSBase sbase = srg.getReferenceInstance();
        String id;
        if (sbase instanceof SimpleSpeciesReference) {
          id = ((SimpleSpeciesReference) sbase).getSpecies();
        } else {
          id = sbase.getId();
        }
        Point point = LayoutUtil.getPointFromGlyph(srg);
        SpeciesAliasWrapper saw = mWrapper.getSpeciesAliasWrapperByPosition(
          point.getX(), point.getY());
        if (srg.getSpeciesReferenceRole() == SpeciesReferenceRole.SUBSTRATE
          || srg.getSpeciesReferenceRole() == SpeciesReferenceRole.SIDESUBSTRATE) {
          SpeciesReferenceWrapper srw = rw.getReactantWrapperById(id);
          srw.setAlias(saw.getId());
        } else if (srg.getSpeciesReferenceRole() == SpeciesReferenceRole.PRODUCT
          || srg.getSpeciesReferenceRole() == SpeciesReferenceRole.SIDEPRODUCT) {
          SpeciesReferenceWrapper srw = rw.getProductWrapperById(id);
          srw.setAlias(saw.getId());
        } else if (srg.getSpeciesReferenceRole() == SpeciesReferenceRole.ACTIVATOR
          || srg.getSpeciesReferenceRole() == SpeciesReferenceRole.INHIBITOR
          || srg.getSpeciesReferenceRole() == SpeciesReferenceRole.MODIFIER) {
          ModifierSpeciesReferenceWrapper msrw = rw.getModifierWrapperById(id);
          msrw.setAlias(saw.getId());
          SpeciesWrapper sw = saw.getSpeciesWrapperAliased();
          sw.createCatalyzedReaction(rg.getReference());
          Modification modification = rw.getModificationByModifierId(sw.getId());
          modification.setAliases(msrw.getAlias());
          modification.setType(SBMLUtil.SBOTermToCDModifier(sbase.getSBOTerm()));
        } else {
          // undefined role
        }
      }
      if (reactantList.size() == 1 && productList.size() == 1) {
        SpeciesReference reactant = reactantList.get(0);
        SpeciesReference product = productList.get(0);
        rw.createBaseReactant(rw.getReactantWrapperById(reactant.getSpecies()));
        rw.createBaseProduct(rw.getProductWrapperById(product.getSpecies()));
        SpeciesReferenceGlyph reactantGlyph = getSpeciesReferenceGlyphByReferenceId(
          rg.getId(), reactant.getSpecies());
        SpeciesReferenceGlyph productGlyph = getSpeciesReferenceGlyphByReferenceId(
          rg.getId(), product.getSpecies());
        List<Point2D.Double> editPointVertices = convertSRGlyphToPointList(
          reactantGlyph, productGlyph);
        if (!editPointVertices.isEmpty()) {
          editPointVertices = LayoutUtil.convertEditPointsToProportion(
            LayoutUtil.getCenterPositionOfGlyph(reactantGlyph),
            LayoutUtil.getCenterPositionOfGlyph(productGlyph),
            editPointVertices);
          rw.createEditPointList(LayoutUtil.editPointListToStringList(editPointVertices));
          rw.setTShapeIndex(reactantGlyph.getCurve().getListOfCurveSegments()
                                         .getChildCount() - 1);
        }
      } else if (reactantList.size() == 2 && productList.size() == 1
        && sboterm == SBMLUtil.intSBOTermForHETERODIMER_ASSOCIATION) {
        SpeciesReference reactant1 = reactantList.get(0);
        SpeciesReference reactant2 = reactantList.get(1);
        SpeciesReference product = productList.get(0);
        rw.createBaseReactant(rw.getReactantWrapperById(reactant1.getSpecies()));
        rw.createBaseReactant(rw.getReactantWrapperById(reactant2.getSpecies()));
        rw.createBaseProduct(rw.getProductWrapperById(product.getSpecies()));
        SpeciesReferenceGlyph reactantGlyph1 = getSpeciesReferenceGlyphByReferenceId(
          rg.getId(), reactant1.getSpecies());
        SpeciesReferenceGlyph reactantGlyph2 = getSpeciesReferenceGlyphByReferenceId(
          rg.getId(), reactant2.getSpecies());
        SpeciesReferenceGlyph productGlyph = getSpeciesReferenceGlyphByReferenceId(
          rg.getId(), product.getSpecies());
        List<Point2D.Double> editPointVertices = convertSRGlyphToPointList(
          reactantGlyph1, reactantGlyph2, productGlyph, r.getSBOTerm());
        editPointVertices = LayoutUtil.convertEditPointsToProportion(
          LayoutUtil.getCenterPositionOfGlyph(reactantGlyph1),
          LayoutUtil.getCenterPositionOfGlyph(reactantGlyph2),
          LayoutUtil.getCenterPositionOfGlyph(productGlyph), editPointVertices);
        rw.createEditPointList(LayoutUtil.editPointListToStringList(editPointVertices));
        int num0 = reactantGlyph1.getCurve().getListOfCurveSegments()
                                 .getChildCount() - 1;
        int num1 = reactantGlyph2.getCurve().getListOfCurveSegments()
                                 .getChildCount() - 1;
        int num2 = productGlyph.getCurve().getListOfCurveSegments()
                               .getChildCount() - 1;
        int tshapeindex = num0 + num1;
        rw.setNum(num0, num1, num2, tshapeindex);
      } else if (reactantList.size() == 1
        && productList.size() == 2
        && (sboterm == SBMLUtil.intSBOTermForDISSOCIATION || sboterm == SBMLUtil.intSBOTermForTRUNCATION)) {
        SpeciesReference reactant = reactantList.get(0);
        SpeciesReference product1 = productList.get(0);
        SpeciesReference product2 = productList.get(1);
        rw.createBaseReactant(rw.getReactantWrapperById(reactant.getSpecies()));
        rw.createBaseProduct(rw.getProductWrapperById(product1.getSpecies()));
        rw.createBaseProduct(rw.getProductWrapperById(product2.getSpecies()));
        SpeciesReferenceGlyph reactantGlyph = getSpeciesReferenceGlyphByReferenceId(
          rg.getId(), reactant.getSpecies());
        SpeciesReferenceGlyph productGlyph1 = getSpeciesReferenceGlyphByReferenceId(
          rg.getId(), product1.getSpecies());
        SpeciesReferenceGlyph productGlyph2 = getSpeciesReferenceGlyphByReferenceId(
          rg.getId(), product2.getSpecies());
        List<Point2D.Double> editPointVertices = convertSRGlyphToPointList(
          reactantGlyph, productGlyph1, productGlyph2, r.getSBOTerm());
        editPointVertices = LayoutUtil.convertEditPointsToProportion(
          LayoutUtil.getCenterPositionOfGlyph(reactantGlyph),
          LayoutUtil.getCenterPositionOfGlyph(productGlyph1),
          LayoutUtil.getCenterPositionOfGlyph(productGlyph2), editPointVertices);
        rw.createEditPointList(LayoutUtil.editPointListToStringList(editPointVertices));
        int num0 = reactantGlyph.getCurve().getListOfCurveSegments()
                                .getChildCount() - 1;
        int num1 = productGlyph1.getCurve().getListOfCurveSegments()
                                .getChildCount() - 1;
        int num2 = productGlyph2.getCurve().getListOfCurveSegments()
                                .getChildCount() - 1;
        int tshapeindex = num0;
        rw.setNum(num0, num1, num2, tshapeindex);
      } else {
        reactantList = reorderSpeciesReferencesList(reactantList);
        productList = reorderSpeciesReferencesList(productList);
        SpeciesReference basereactant = reactantList.get(0);
        SpeciesReference baseproduct = productList.get(0);
        rw.createBaseReactant(rw.getReactantWrapperById(basereactant.getSpecies()));
        rw.createBaseProduct(rw.getProductWrapperById(baseproduct.getSpecies()));
        SpeciesReferenceGlyph reactantGlyph = getSpeciesReferenceGlyphByReferenceId(
          rg.getId(), basereactant.getSpecies());
        SpeciesReferenceGlyph productGlyph = getSpeciesReferenceGlyphByReferenceId(
          rg.getId(), baseproduct.getSpecies());
        List<Point2D.Double> editPointVertices = convertSRGlyphToPointList(
          reactantGlyph, productGlyph);
        if (!editPointVertices.isEmpty()) {
          editPointVertices = LayoutUtil.convertEditPointsToProportion(
            LayoutUtil.getCenterPositionOfGlyph(reactantGlyph),
            LayoutUtil.getCenterPositionOfGlyph(productGlyph),
            editPointVertices);
          rw.createEditPointList(LayoutUtil.editPointListToStringList(editPointVertices));
          rw.setTShapeIndex(reactantGlyph.getCurve().getListOfCurveSegments()
                                         .getChildCount() - 1);
        }
        if (reactantList.size() > 1) {
          rw.createReactantLinks();
          for (int i = 1; i < reactantList.size(); i++) {
            SpeciesReferenceGlyph srg = getSpeciesReferenceGlyphByReferenceId(
              rg.getId(), reactantList.get(i).getSpecies());
            Point point = LayoutUtil.getPointFromGlyph(srg);
            SpeciesAliasWrapper saw = mWrapper.getSpeciesAliasWrapperByPosition(
              point.getX(), point.getY());
            rw.createReactantLink(saw);
            rw.setReactantLinkLineType(saw.getSpecies(),
              srg.getCurve().getListOfCurveSegments().getLast().getType());
          }
        }
        if (productList.size() > 1) {
          rw.createProductLinks();
          for (int i = 1; i < productList.size(); i++) {
            SpeciesReferenceGlyph srg = getSpeciesReferenceGlyphByReferenceId(
              rg.getId(), productList.get(i).getSpecies());
            Point point = LayoutUtil.getPointFromGlyph(srg);
            SpeciesAliasWrapper saw = mWrapper.getSpeciesAliasWrapperByPosition(
              point.getX(), point.getY());
            rw.createProductLink(saw);
            rw.setProductLinkLineType(saw.getSpecies(),
              srg.getCurve().getListOfCurveSegments().get(0).getType());
          }
        }
      }
      for (ModifierSpeciesReference msr : modifierList) {
        SpeciesReferenceGlyph modifierGlyph = getSpeciesReferenceGlyphByReferenceId(
          rg.getId(), msr.getSpecies());
        List<Point2D.Double> editPointVertices = convertSRGlyphToPointList(modifierGlyph);
        if (!editPointVertices.isEmpty()) {
          SpeciesReference reactant = reactantList.get(0);
          SpeciesReferenceGlyph reactantGlyph = getSpeciesReferenceGlyphByReferenceId(
            rg.getId(), reactant.getSpecies());
          editPointVertices = LayoutUtil.convertEditPointsToProportion(
            modifierGlyph.getCurve().getListOfCurveSegments().get(0).getStart(),
            reactantGlyph.getCurve().getListOfCurveSegments().getLast()
                         .getEnd(), editPointVertices);
          ModifierSpeciesReferenceWrapper mw = rw.getModifierWrapperById(msr.getSpecies());
          mw.setEditPointList(LayoutUtil.editPointListToStringList(editPointVertices));
        }
      }
    }
  }


  /**
   * Convert SR glyph to point list.
   *
   * @param modifier
   *        the modifier
   * @return the list
   */
  private List<Point2D.Double> convertSRGlyphToPointList(
    SpeciesReferenceGlyph modifier) {
    List<Point2D.Double> pointList = new ArrayList<Point2D.Double>();
    ListOf<CurveSegment> locs = modifier.getCurve().getListOfCurveSegments();
    for (int i = 0; i < locs.size(); i++) {
      CurveSegment cs = locs.get(i);
      pointList.addAll(createPointListFromCurveSegment(cs));
    }
    pointList.remove(pointList.size() - 1);
    return pointList;
  }


  /**
   * Convert SR glyph to point list.
   *
   * @param reactant
   *        the reactant
   * @param product
   *        the product
   * @return the list
   */
  private List<Point2D.Double> convertSRGlyphToPointList(
    SpeciesReferenceGlyph reactant, SpeciesReferenceGlyph product) {
    List<Point2D.Double> pointList = new ArrayList<Point2D.Double>();
    ListOf<CurveSegment> locs = reactant.getCurve().getListOfCurveSegments();
    for (int i = 0; i < locs.size(); i++) {
      CurveSegment cs = locs.get(i);
      pointList.addAll(createPointListFromCurveSegment(cs));
    }
    pointList.remove(pointList.size() - 1);
    locs = product.getCurve().getListOfCurveSegments();
    for (int i = 0; i < locs.size(); i++) {
      CurveSegment cs = locs.get(i);
      pointList.addAll(createPointListFromCurveSegment(cs));
    }
    pointList.remove(pointList.size() - 1);
    return pointList;
  }


  /**
   * Convert SR glyph to point list.
   *
   * @param reactant
   *        the reactant
   * @param reference
   *        the reference
   * @param product
   *        the product
   * @param sboterm
   *        the sboterm
   * @return the list
   */
  private List<Point2D.Double> convertSRGlyphToPointList(
    SpeciesReferenceGlyph reactant, SpeciesReferenceGlyph reference,
    SpeciesReferenceGlyph product, int sboterm) {
    List<Point2D.Double> pointList = new ArrayList<Point2D.Double>();
    ListOf<CurveSegment> locs = reactant.getCurve().getListOfCurveSegments();
    for (int i = 0; i < locs.size(); i++) {
      CurveSegment cs = locs.get(i);
      pointList.addAll(createPointListFromCurveSegment(cs));
    }
    if (sboterm == SBMLUtil.intSBOTermForHETERODIMER_ASSOCIATION) {
      pointList.remove(pointList.size() - 1);
    }
    locs = reference.getCurve().getListOfCurveSegments();
    for (int i = 0; i < locs.size(); i++) {
      CurveSegment cs = locs.get(i);
      pointList.addAll(createPointListFromCurveSegment(cs));
    }
    if (sboterm == SBMLUtil.intSBOTermForDISSOCIATION
      || sboterm == SBMLUtil.intSBOTermForTRUNCATED) {
      pointList.remove(pointList.size() - 1);
    }
    locs = product.getCurve().getListOfCurveSegments();
    for (int i = 0; i < locs.size(); i++) {
      CurveSegment cs = locs.get(i);
      pointList.addAll(createPointListFromCurveSegment(cs));
    }
    pointList.remove(pointList.size() - 1);
    return pointList;
  }


  /**
   * Creates the point list from curve segment.
   *
   * @param cs
   *        the cs
   * @return the list
   */
  private List<Point2D.Double> createPointListFromCurveSegment(CurveSegment cs) {
    List<Point2D.Double> pointList = new ArrayList<Point2D.Double>();
    if (cs.isCubicBezier()) {
      CubicBezier bezier = (CubicBezier) cs;
      Point2D.Double basePoint = new Point2D.Double(bezier.getBasePoint1()
                                                          .getX(),
        bezier.getBasePoint1().getY());
      pointList.add(basePoint);
      if (bezier.getBasePoint1().getX() != bezier.getBasePoint2().getX()
        || bezier.getBasePoint1().getY() != bezier.getBasePoint2().getY()) {
        basePoint = new Point2D.Double(bezier.getBasePoint2().getX(),
          bezier.getBasePoint2().getY());
        pointList.add(basePoint);
      }
    }
    Point2D.Double point = new Point2D.Double(cs.getEnd().getX(), cs.getEnd()
                                                                    .getY());
    pointList.add(point);
    return pointList;
  }


  /**
   * Reorder species references list.
   *
   * @param srList
   *        the sr list
   * @return the list
   */
  private ListOf<SpeciesReference> reorderSpeciesReferencesList(
    ListOf<SpeciesReference> srList) {
    SpeciesReference sr = srList.get(0);
    SpeciesWrapper sw = mWrapper.getSpeciesWrapperById(sr.getSpecies());
    if (sw.getClazz().equals("PROTEIN") || sw.getClazz().equals("GENE")
      || sw.getClazz().equals("RNA") || sw.getClazz().equals("ANTISENSE_RNA")) {
      return srList;
    }
    for (int i = 1; i < srList.size(); i++) {
      sr = srList.get(i);
      sw = mWrapper.getSpeciesWrapperById(sr.getSpecies());
      if (sw.getClazz().equals("PROTEIN") || sw.getClazz().equals("GENE")
        || sw.getClazz().equals("RNA") || sw.getClazz().equals("ANTISENSE_RNA")) {
        Collections.swap(srList, 0, i);
      }
    }
    return srList;
  }


  /**
   * Gets the species reference glyph by id.
   *
   * @param rgid
   *        the rgid
   * @param id
   *        the id
   * @return the species reference glyph by id
   */
  private SpeciesReferenceGlyph getSpeciesReferenceGlyphByReferenceId(
    String rgid, String id) {
    ReactionGlyph rg = layout.getReactionGlyph(rgid);
    for (SpeciesReferenceGlyph sg : rg.getListOfSpeciesReferenceGlyphs()) {
      if (sg.getReference().equals(id)
        || sg.getSpeciesGlyphInstance().getReference().equals(id))
        return sg;
    }
    return null;
  }


  /**
   * Convert text to CD.
   *
   * @param tgList
   *        the tg list
   */
  public void convertTextToCD(List<TextGlyph> tgList) {
    for (TextGlyph tg : tgList) {
      SBase reference = tg.getReferenceInstance();
      if (reference instanceof CompartmentGlyph) {
        Compartment c = (Compartment) ((CompartmentGlyph) reference).getCompartmentInstance();
        CompartmentAliasWrapper caw = mWrapper.getCompartmentAliasWrapperByCompartmentId(c.getId());
        Point namePoint = tg.getBoundingBox().getPosition();
        caw.setNameX(namePoint.getX());
        caw.setNameY(namePoint.getY());
      }
    }
  }
}
