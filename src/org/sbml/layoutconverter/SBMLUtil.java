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

import java.io.File;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Logger;

import javax.swing.tree.TreeNode;
import javax.xml.stream.XMLStreamException;

import org.sbml.jsbml.AbstractMathContainer;
import org.sbml.jsbml.Compartment;
import org.sbml.jsbml.Constraint;
import org.sbml.jsbml.Delay;
import org.sbml.jsbml.Event;
import org.sbml.jsbml.EventAssignment;
import org.sbml.jsbml.FunctionDefinition;
import org.sbml.jsbml.InitialAssignment;
import org.sbml.jsbml.KineticLaw;
import org.sbml.jsbml.ListOf;
import org.sbml.jsbml.Model;
import org.sbml.jsbml.ModifierSpeciesReference;
import org.sbml.jsbml.Parameter;
import org.sbml.jsbml.Priority;
import org.sbml.jsbml.Reaction;
import org.sbml.jsbml.Rule;
import org.sbml.jsbml.SBMLDocument;
import org.sbml.jsbml.SBMLReader;
import org.sbml.jsbml.SBO;
import org.sbml.jsbml.SBase;
import org.sbml.jsbml.SimpleSpeciesReference;
import org.sbml.jsbml.Species;
import org.sbml.jsbml.SpeciesReference;
import org.sbml.jsbml.StoichiometryMath;
import org.sbml.jsbml.Trigger;
import org.sbml.jsbml.ext.fbc.FBCConstants;
import org.sbml.jsbml.ext.layout.LayoutConstants;
import org.sbml.jsbml.util.filters.SpeciesReferenceFilter;

// TODO: Auto-generated Javadoc
/**
 * The Class SBMLUtil.
 *
 * @author Kaito Ii
 *         Date Created: Jun 24, 2016
 */
@SuppressWarnings("deprecation")
public class SBMLUtil {

  /**
   * A {@link Logger} for this class.
   */
  private static final Logger logger = Logger.getLogger(SBMLUtil.class.getName());

  /** The defaultsbmllevel. */
  public static final int DEFAULT_SBML_LEVEL = 3;

  /** The defaultsbmlversion. */
  public static final int DEFAULT_SBML_VERSION = 1;

  /** The defaultCellDesignersbmllevel. */
  public static final int DEFAULT_CELLDESIGNER_SBML_LEVEL = 2;

  /** The defaultCellDesignersbmlversion. */
  public static final int DEFAULT_CELLDESIGNER_SBML_VERSION = 4;

  /** The Constant DEFALUT_CELLDESIGNER_MODEL_VERSION. */
  public static final int DEFALUT_CELLDESIGNER_MODEL_VERSION = 4;

  /** The Constant cellDesignerNS. */
  final static String cellDesignerNS = "http://www.sbml.org/2001/ns/celldesigner";

  /** The Constant intSBOTermForModel. */
  public static final int intSBOTermForModel = 231; // occurring entity representation

  /** The Constant intSBOTermForFunctionDefinition. */
  public static final int intSBOTermForFunctionDefinition = 64; // mathematical expression

  /** The Constant intSBOTermForCompartmentType. */
  public static final int intSBOTermForCompartmentType = 240; // material entity

  /** The Constant intSBOTermForSpeciesType. */
  public static final int intSBOTermForSpeciesType = 240; // material entity

  /** The Constant intSBOTermForCompartment. */
  // TODO: why not 290: physical compartment?
  public static final int intSBOTermForCompartment = 240; // material entity

  /** The Constant intSBOTermForSpecies. */
  public static final int intSBOTermForSpecies = 240; // material entity

  /** The Constant intSBOTermForReaction. */
  public static final int intSBOTermForReaction = 231; // occurring entity representation

  /** The Constant intSBOTermForParameter. */
  public static final int intSBOTermForParameter = 2; // quantitative systems description parameter

  /** The Constant intSBOTermForSpeciesReference. */
  public static final int intSBOTermForSpeciesReference = 3; // participant role

  /** The Constant intSBOTermForModifierSpeciesReference. */
  public static final int intSBOTermForModifierSpeciesReference = 3; // participant role

  /** The Constant intSBOTermForKineticLaw. */
  public static final int intSBOTermForKineticLaw = 1; // rate law

  /** The Constant intSBOTermForInitialAssignment. */
  public static final int intSBOTermForInitialAssignment = 64; // mathematical expression

  /** The Constant intSBOTermForAlgebraicRule. */
  public static final int intSBOTermForAlgebraicRule = 64; // mathematical expression

  /** The Constant intSBOTermForAssignmentRule. */
  public static final int intSBOTermForAssignmentRule = 64; // mathematical expression

  /** The Constant intSBOTermForRateRule. */
  public static final int intSBOTermForRateRule = 64; // mathematical expression

  /** The Constant intSBOTermForConstraint. */
  public static final int intSBOTermForConstraint = 64; // mathematical expression

  /** The Constant intSBOTermForEvent. */
  public static final int intSBOTermForEvent = 231; // occurring entity representation

  /** The Constant intSBOTermForTrigger. */
  public static final int intSBOTermForTrigger = 64; // mathematical expression

  /** The Constant intSBOTermForDelay. */
  public static final int intSBOTermForDelay = 64; // mathematical expression

  /** The Constant intSBOTermForEventAssignment. */
  public static final int intSBOTermForEventAssignment = 64; // mathematical expression

  /** The Constant intSBOTermForANTISENSE_RNA. */
  //  Species Aliases
  public static final int intSBOTermForANTISENSE_RNA = SBO.getAntisenseRNA();

  /** The Constant intSBOTermForCOMPLEX. */
  public static final int intSBOTermForCOMPLEX = SBO.getComplex();

  /** The Constant intSBOTermForDEGRADED. */
  public static final int intSBOTermForDEGRADED = SBO.getEmptySet();

  /** The Constant intSBOTermForDRUG. */
  public static final int intSBOTermForDRUG = SBO.getDrug();

  /** The Constant intSBOTermForGENE. */
  public static final int intSBOTermForGENE = SBO.getGene();

  /** The Constant intSBOTermForGENERIC. */
  public static final int intSBOTermForGENERIC = SBO.getGeneric();

  /** The Constant intSBOTermForION. */
  public static final int intSBOTermForION = SBO.getIon();

  /** The Constant intSBOTermForPHENOTYPE. */
  public static final int intSBOTermForPHENOTYPE = SBO.getPhenotype();

  /** The Constant intSBOTermForPROTEIN. */
  public static final int intSBOTermForPROTEIN = SBO.getProtein();

  /** The Constant intSBOTermForRECEPTOR. */
  public static final int intSBOTermForRECEPTOR = SBO.getReceptor();

  /** The Constant intSBOTermForRNA. */
  public static final int intSBOTermForRNA = SBO.getRNA();

  /** The Constant intSBOTermForION_CHANNEL. */
  public static final int intSBOTermForION_CHANNEL = SBO.getIonChannel();

  /** The Constant intSBOTermForSIMPLE_MOLECULE. */
  public static final int intSBOTermForSIMPLE_MOLECULE = SBO.getSimpleMolecule();

  /** The Constant intSBOTermForTRUNCATED. */
  public static final int intSBOTermForTRUNCATED = SBO.getTruncated();

  /** The Constant intSBOTermForUNKNOWN. */
  public static final int intSBOTermForUNKNOWN = SBO.getUnknownMolecule();

  /** The Constant intSBOTermForKNOWN_TRANSITION_OMITTED. */
  // Reaction Aliases
  public static final int intSBOTermForKNOWN_TRANSITION_OMITTED = SBO.getTransitionOmitted();

  /** The Constant intSBOTermForSTATE_TRANSITION. */
  public static final int intSBOTermForSTATE_TRANSITION = SBO.getStateTransition();

  /** The Constant intSBOTermForTRANSCRIPTION. */
  public static final int intSBOTermForTRANSCRIPTION = SBO.getTranscription();

  /** The Constant intSBOTermForTRANSLATION. */
  public static final int intSBOTermForTRANSLATION = SBO.getTranslation();

  /** The Constant intSBOTermForTRANSPORT. */
  public static final int intSBOTermForTRANSPORT = SBO.getTransport();

  /** The Constant intSBOTermForHETERODIMER_ASSOCIATION. */
  public static final int intSBOTermForHETERODIMER_ASSOCIATION = 177; // non-covalent binding

  /** The Constant intSBOTermForDISSOCIATION. */
  public static final int intSBOTermForDISSOCIATION = 180; // dissociation

  /** The Constant intSBOTermForTRUNCATION. */
  public static final int intSBOTermForTRUNCATION = 178; // Cleavage

  /** The Constant intSBOTermForUNKNOWN_TRANSITION. */
  public static final int intSBOTermForUNKNOWN_TRANSITION = SBO.getUnknownTransition();

  /** The Constant intSBOTermForReactant. */
  public static final int intSBOTermForReactant = SBO.getReactant();

  /** The Constant intSBOTermForProduct. */
  public static final int intSBOTermForProduct = SBO.getProduct();

  /** The Constant intSBOTermForModifier. */
  public static final int intSBOTermForModifier = SBO.getModifier();

  /** The Constant intSBOTermForCATALYSIS. */
  // Modifier Aliases
  public static final int intSBOTermForCATALYSIS = SBO.getCatalysis();

  /** The Constant intSBOTermForINHIBITION. */
  public static final int intSBOTermForINHIBITION = SBO.getInhibition();

  /** The Constant intSBOTermForMODULATION. */
  public static final int intSBOTermForMODULATION = SBO.getModulation();

  /** The Constant intSBOTermForPHYSICAL_STIMULATION. */
  public static final int intSBOTermForPHYSICAL_STIMULATION = SBO.getStimulator();

  /** The Constant intSBOTermForTRANSCRIPTIONAL_ACTIVATION. */
  public static final int intSBOTermForTRANSCRIPTIONAL_ACTIVATION = SBO.getTranscriptionalActivation();

  /** The Constant intSBOTermForTRANSCRIPTIONAL_INHIBITION. */
  public static final int intSBOTermForTRANSCRIPTIONAL_INHIBITION = SBO.getTranscriptionalInhibitor();

  /** The Constant intSBOTermForTRANSLATIONAL_ACTIVATION. */
  public static final int intSBOTermForTRANSLATIONAL_ACTIVATION = SBO.getTranslationalActivation();

  /** The Constant intSBOTermForTRANSLATIONAL_INHIBITION. */
  public static final int intSBOTermForTRANSLATIONAL_INHIBITION = SBO.getTranslationalInhibitor();

  /** The Constant intSBOTermForTRIGGER. */
  public static final int intSBOTermForTRIGGER = SBO.getTrigger();

  /** The Constant intSBOTermForUNKNOWN_CATALYSIS. */
  public static final int intSBOTermForUNKNOWN_CATALYSIS = 13; // catalyst

  /** The Constant intSBOTermForUNKNOWN_INHIBITION. */
  public static final int intSBOTermForUNKNOWN_INHIBITION = 20; // inhibitor

  /** The Constant intSBOTermForDEFAULT_SPECIES. */
  public static final int intSBOTermForDEFAULT_SPECIES = intSBOTermForPROTEIN;

  /** The Constant intSBOTermForDEFAULT_REACTION. */
  public static final int intSBOTermForDEFAULT_REACTION = intSBOTermForSTATE_TRANSITION;

  /**
   * Checks if is sets the cell designer name space.
   *
   * @param document the document
   * @return boolean
   * TODO
   */
  public static boolean isSetCellDesignerNameSpace(SBMLDocument document) {
    Map<String, String> nsMap = document.getDeclaredNamespaces();
    return nsMap.containsValue(cellDesignerNS);
  }

  /**
   * Checks if is sets the cell designer name space.
   *
   * @param file the file
   * @return true, if is sets the cell designer name space
   * @throws XMLStreamException the XML stream exception
   * @throws IOException Signals that an I/O exception has occurred.
   */
  public static boolean isSetCellDesignerNameSpace(File file) throws XMLStreamException, IOException{
    return isSetCellDesignerNameSpace(SBMLReader.read(file));
  }

  /**
   * Checks if is sets the cell designer name space.
   *
   * @param document the document
   * @return boolean
   * TODO
   */
  public static boolean isSetLayoutNameSpace(SBMLDocument document) {
    Map<String, String> nsMap = document.getDeclaredNamespaces();
    return nsMap.containsValue(LayoutConstants.namespaceURI_L3V1V1);
  }

  /**
   * Adds the extension package layout.
   *
   * @param document the document
   * @return the SBML document
   */
  public static SBMLDocument addExtensionPackageLayout(SBMLDocument document) {
    document.enablePackage(LayoutConstants.getNamespaceURI(SBMLUtil.DEFAULT_SBML_LEVEL, SBMLUtil.DEFAULT_SBML_VERSION), true);
    document.setPackageRequired(LayoutConstants.getNamespaceURI(SBMLUtil.DEFAULT_SBML_LEVEL, SBMLUtil.DEFAULT_SBML_VERSION), true);

    return document;
  }

  /**
   * Adds the extension package fbc.
   *
   * @param document the document
   * @return the SBML document
   */
  public static SBMLDocument addExtensionPackageFBC(SBMLDocument document) {
    document.enablePackage(FBCConstants.getNamespaceURI(SBMLUtil.DEFAULT_SBML_LEVEL, SBMLUtil.DEFAULT_SBML_VERSION), true);
    document.setPackageRequired(FBCConstants.getNamespaceURI(SBMLUtil.DEFAULT_SBML_LEVEL, SBMLUtil.DEFAULT_SBML_VERSION), false);

    return document;
  }

  /**
   * Adds the extension package fbc.
   *
   * @param model the model
   * @return the SBML document
   */
  public static Model addExtensionPackageFBC(Model model) {
    return addExtensionPackageFBC(model.getSBMLDocument()).getModel();
  }

  /**
   * Checks if is level and version match with CD.
   *
   * @param file the file
   * @return true, if is level and version match with CD
   * @throws XMLStreamException the XML stream exception
   * @throws IOException Signals that an I/O exception has occurred.
   */
  public static boolean isLevelAndVersionMatchWithCD(File file) throws XMLStreamException, IOException{
    return isLevelAndVersionMatchWithCD(SBMLReader.read(file));
  }

  /**
   * Checks if is level and version match with CD.
   *
   * @param document the document
   * @return true, if is level and version match with CD
   */
  public static boolean isLevelAndVersionMatchWithCD(SBMLDocument document) {
    return (document.getLevel() == DEFAULT_CELLDESIGNER_SBML_LEVEL) && (document.getVersion() == DEFAULT_CELLDESIGNER_SBML_VERSION);
  }

  /**
   * Checks if is sets the SBO term.
   *
   * @param document the document
   * @return boolean
   * TODO
   */
  public static boolean isSetSBOTerm(SBMLDocument document) {
    boolean species = checkSBOTermFromList(document.getModel().getListOfSpecies());
    boolean compartment = checkSBOTermFromList(document.getModel().getListOfCompartments());
    boolean reaction = checkSBOTermFromList(document.getModel().getListOfReactions());
    ListOf<Reaction> lor = document.getModel().getListOfReactions();
    boolean reactant = true;
    boolean product = true;
    boolean modifier = true;

    for (Reaction r: lor) {
      if (!checkSBOTermFromList(r.getListOfReactants())) {
        reactant = false;
      }
      if (!checkSBOTermFromList(r.getListOfProducts())) {
        product = false;
      }
      if (!checkSBOTermFromList(r.getListOfModifiers())) {
        modifier = false;
      }
    }

    return species && compartment && reaction && reactant && product && modifier;
  }

  /**
   * Check SBO term from list.
   *
   * @param list the list
   * @return boolean
   * TODO
   */
  public static boolean checkSBOTermFromList(ListOf<?> list) {
    @SuppressWarnings("unchecked")
    Iterator<SBase> iterator = (Iterator<SBase>) list.iterator();

    while(iterator.hasNext()) {
      if (!iterator.next().isSetSBOTerm()) {
        return false;
      }
    }

    return true;
  }

  /**
   * Adds a counter to the id if there is a clash and checks how much to increase
   * this counter in order to avoid the clash.
   * 
   * @param model
   * @param id
   * @return
   */
  public static String createId(Model model, String id) {
    String tmpId = id;
    int i = 1;
    while (model.findNamedSBase(tmpId) != null) {
      tmpId = id + "_" + (i++);
    }
    return tmpId;
  }

  /**
   * Adds the default SBO term.
   *
   * @param document the document
   * @return the SBML document
   */
  public static SBMLDocument addDefaultSBOTerm(SBMLDocument document) {
    TreeNode node = document.getRoot();
    addDefaultSBOTerm(node);

    return document;
  }

  /**
   * Adds the default SBO term.
   *
   * @param node the node
   */
  public static void addDefaultSBOTerm(TreeNode node) {
    for (int i = 0; i < node.getChildCount(); i++) {
      addDefaultSBOTerm(node.getChildAt(i));
    }

    if (!(node instanceof SBase) ) {
      return ;
    }

    SBase sbase = (SBase) node;
    if (sbase.isSetSBOTerm()) {
      return;
    }

    if (sbase instanceof Model) {
      sbase.setSBOTerm(intSBOTermForModel);
    } else if (sbase instanceof Species) {
      sbase.setSBOTerm(intSBOTermForGENERIC);
    } else if (sbase instanceof Compartment) {
      sbase.setSBOTerm(intSBOTermForCompartment);
    } else if (sbase instanceof Parameter) {
      sbase.setSBOTerm(intSBOTermForParameter);
    } else if (sbase instanceof Rule) {
      sbase.setSBOTerm(intSBOTermForRateRule);
    } else if (sbase instanceof Compartment) {
      sbase.setSBOTerm(intSBOTermForCompartment);
    } else if (sbase instanceof Reaction) {
      sbase.setSBOTerm(intSBOTermForReaction);
    } else if (sbase instanceof SpeciesReference) {
      Reaction r = (Reaction) ((SpeciesReference) sbase).getParentSBMLObject().getParentSBMLObject();
      if (r.getListOfReactants().contains(sbase)) {
        sbase.setSBOTerm(intSBOTermForReactant);
      } else {
        sbase.setSBOTerm(intSBOTermForProduct);
      }
    } else if (sbase instanceof ModifierSpeciesReference) {
      sbase.setSBOTerm(intSBOTermForModifierSpeciesReference);
    } else if (sbase instanceof KineticLaw) {
      sbase.setSBOTerm(intSBOTermForKineticLaw);
    }
  }

  /**
   * Contains referencing species.
   *
   * @param list the list
   * @param id the id
   * @return the reference to the given species identifier.
   */
  public static <T extends SimpleSpeciesReference> T findReferenceBySpeciesId(ListOf<T> list, String id) {
    SpeciesReferenceFilter srf = new SpeciesReferenceFilter(id);
    srf.setFilterForSpecies(true);
    return list.firstHit(srf);
  }

  /**
   * Creates the output file name.
   *
   * @param file the file
   * @return the string
   */
  public static String createOutputFileName(File file) {
    String path = file.getPath();
    return path.substring(0, path.indexOf(".")) + "_converted.xml";
  }

  /**
   * SBO term to string.
   *
   * @param sboTerm the sbo term
   * @return String
   * TODO
   */
  public static String SBOTermToString(int sboTerm) {
    if (SBO.isChildOf(sboTerm, intSBOTermForANTISENSE_RNA)) {
      return "ANTISENSE_RNA";
    } else if (SBO.isChildOf(sboTerm, intSBOTermForCOMPLEX)) {
      return "COMPLEX";
    } else if (SBO.isChildOf(sboTerm, intSBOTermForDEGRADED)) {
      return "DEGRADED";
    } else if (SBO.isChildOf(sboTerm, intSBOTermForDRUG)) {
      return "DRUG";
    } else if (SBO.isChildOf(sboTerm, intSBOTermForGENE)) {
      return "GENE";
    } else if (SBO.isChildOf(sboTerm, intSBOTermForGENERIC)) {
      return "GENERIC";
    } else if (SBO.isChildOf(sboTerm, intSBOTermForION)) {
      return "ION";
    } else if (SBO.isChildOf(sboTerm, intSBOTermForPHENOTYPE)) {
      return "PHENOTYPE";
    } else if (SBO.isChildOf(sboTerm, intSBOTermForPROTEIN)) {
      return "PROTEIN";
    } else if (SBO.isChildOf(sboTerm, intSBOTermForRECEPTOR)) {
      return "RECEPTOR";
    } else if (SBO.isChildOf(sboTerm, intSBOTermForRNA)) {
      return "RNA";
    } else if (SBO.isChildOf(sboTerm, intSBOTermForION_CHANNEL)) {
      return "ION_CHANNEL";
    } else if (SBO.isChildOf(sboTerm, intSBOTermForSIMPLE_MOLECULE)) {
      return "SIMPLE_MOLECULE";
    } else if (SBO.isChildOf(sboTerm, intSBOTermForTRUNCATED)) {
      return "TRUNCATED";
    } else if (SBO.isChildOf(sboTerm, intSBOTermForUNKNOWN)) {
      return "UNKNOWN";
    } else if (SBO.isChildOf(sboTerm, intSBOTermForKNOWN_TRANSITION_OMITTED)) {
      return "KNOWN_TRANSITION_OMITTED";
    } else if (SBO.isChildOf(sboTerm, intSBOTermForSTATE_TRANSITION)) {
      return "STATE_TRANSITION";
    } else if (SBO.isChildOf(sboTerm, intSBOTermForTRANSCRIPTION)) {
      return "TRANSCRIPTION";
    } else if (SBO.isChildOf(sboTerm, intSBOTermForTRANSLATION)) {
      return "TRANSLATION";
    } else if (SBO.isChildOf(sboTerm, intSBOTermForTRANSPORT)) {
      return "TRANSPORT";
    } else if (SBO.isChildOf(sboTerm, intSBOTermForHETERODIMER_ASSOCIATION)) {
      return "HETERODIMER_ASSOCIATION";
    } else if (SBO.isChildOf(sboTerm, intSBOTermForDISSOCIATION)) {
      return "DISSOCIATION";
    } else if (SBO.isChildOf(sboTerm, intSBOTermForTRUNCATION)) {
      return "TRUNCATION";
    } else if (SBO.isChildOf(sboTerm, intSBOTermForUNKNOWN_TRANSITION)) {
      return "UNKNOWN_TRANSITION";
    } else if (SBO.isChildOf(sboTerm, intSBOTermForModifier)) {
      // = intSBOTermForMODULATION
      return "Modifier";
    } else if (SBO.isChildOf(sboTerm, intSBOTermForCATALYSIS)) {
      // = intSBOTermForUNKNOWN_CATALYSIS
      return "CATALYSIS";
    } else if (SBO.isChildOf(sboTerm, intSBOTermForINHIBITION)) {
      // = intSBOTermForTRANSLATIONAL_INHIBITION = intSBOTermForTRANSCRIPTIONAL_INHIBITION = intSBOTermForUNKNOWN_INHIBITION
      return "INHIBITION";
    } else if (SBO.isChildOf(sboTerm, intSBOTermForPHYSICAL_STIMULATION)) {
      return "PHYSICAL_STIMULATION";
    } else if (SBO.isChildOf(sboTerm, intSBOTermForTRANSCRIPTIONAL_ACTIVATION)) {
      //  = intSBOTermForTRANSLATIONAL_ACTIVATION
      return "TRANSCRIPTIONAL_ACTIVATION";
    } else if (SBO.isChildOf(sboTerm, intSBOTermForTRIGGER)) {
      return "TRIGGER";
    } else {
      return "";
    }
  }

  /**
   * SBO term to CD species.
   *
   * @param sboTerm the sbo term
   * @return the string
   */
  public static String SBOTermToCDSpecies(int sboTerm) {
    if (SBO.isChildOf(sboTerm, intSBOTermForANTISENSE_RNA)) {
      return "ANTISENSE_RNA";
    } else if (SBO.isChildOf(sboTerm, intSBOTermForCOMPLEX)) {
      return "COMPLEX";
    } else if (SBO.isChildOf(sboTerm, intSBOTermForDEGRADED)) {
      return "DEGRADED";
    } else if (SBO.isChildOf(sboTerm, intSBOTermForDRUG)) {
      return "DRUG";
    } else if (SBO.isChildOf(sboTerm, intSBOTermForGENE)) {
      return "GENE";
    } else if (SBO.isChildOf(sboTerm, intSBOTermForION)) {
      return "ION";
    } else if (SBO.isChildOf(sboTerm, intSBOTermForPHENOTYPE)) {
      return "PHENOTYPE";
    } else if (SBO.isChildOf(sboTerm, intSBOTermForRNA)) {
      return "RNA";
    } else if (SBO.isChildOf(sboTerm, intSBOTermForSIMPLE_MOLECULE)) {
      return "SIMPLE_MOLECULE";
    } else if (SBO.isChildOf(sboTerm, intSBOTermForUNKNOWN)) {
      return "UNKNOWN";
    } else {
      if (SBO.isChildOf(sboTerm, intSBOTermForGENERIC)) {
      } else if (SBO.isChildOf(sboTerm, intSBOTermForPROTEIN)) {
      } else if (SBO.isChildOf(sboTerm, intSBOTermForION_CHANNEL)) {
      } else if (SBO.isChildOf(sboTerm, intSBOTermForTRUNCATED)) {
      } else if (SBO.isChildOf(sboTerm, intSBOTermForRECEPTOR)) {
      }
      return "PROTEIN";
    }
  }


  /**
   * SBO term to CD reaction.
   *
   * @param sboTerm the SBO term
   * @return String
   * TODO
   */
  public static String SBOTermToCDReaction(int sboTerm) {
    if (SBO.isChildOf(sboTerm, intSBOTermForKNOWN_TRANSITION_OMITTED)) {
      return "KNOWN_TRANSITION_OMITTED";
    } else if (SBO.isChildOf(sboTerm, intSBOTermForINHIBITION)) {
      // = intSBOTermForTRANSLATIONAL_INHIBITION = intSBOTermForTRANSCRIPTIONAL_INHIBITION = intSBOTermForUNKNOWN_INHIBITION
      return "INHIBITION";
    } else if (SBO.isChildOf(sboTerm, intSBOTermForTRANSCRIPTION)) {
      return "TRANSCRIPTION";
    } else if (SBO.isChildOf(sboTerm, intSBOTermForTRANSLATION)) {
      return "TRANSLATION";
    } else if (SBO.isChildOf(sboTerm, intSBOTermForTRANSPORT)) {
      return "TRANSPORT";
    } else if (SBO.isChildOf(sboTerm, intSBOTermForHETERODIMER_ASSOCIATION)) {
      return "HETERODIMER_ASSOCIATION";
    } else if (SBO.isChildOf(sboTerm, intSBOTermForDISSOCIATION)) {
      return "DISSOCIATION";
    } else if (SBO.isChildOf(sboTerm, intSBOTermForTRUNCATION)) {
      return "TRUNCATION";
    } else if (SBO.isChildOf(sboTerm, intSBOTermForUNKNOWN_TRANSITION)) {
      return "UNKNOWN_TRANSITION";
    } else {
      if (SBO.isChildOf(sboTerm, intSBOTermForSTATE_TRANSITION)) {
      }
      return "STATE_TRANSITION";
    }
  }

  /**
   * SBO term to CD modifier.
   *
   * @param sboterm the sboTerm
   * @return the string
   */
  public static String SBOTermToCDModifier(int sboTerm) {
    if (SBO.isChildOf(sboTerm, intSBOTermForINHIBITION)) {
      // = intSBOTermForTRANSLATIONAL_INHIBITION = intSBOTermForTRANSCRIPTIONAL_INHIBITION = intSBOTermForUNKNOWN_INHIBITION
      return "INHIBITION";
    } else if (SBO.isChildOf(sboTerm, intSBOTermForTRANSCRIPTIONAL_ACTIVATION)) {
      //  = intSBOTermForTRANSLATIONAL_ACTIVATION
      return "TRANSCRIPTIONAL_ACTIVATION";
    } else if (SBO.isChildOf(sboTerm, intSBOTermForTrigger)) {
      return "TRIGGER";
    } else if (SBO.isChildOf(sboTerm, intSBOTermForPHYSICAL_STIMULATION)) {
      return "PHYSICAL_STIMULATION";
    } else if (SBO.isChildOf(sboTerm, intSBOTermForHETERODIMER_ASSOCIATION)) {
      return "HETERODIMER_ASSOCIATION";
    } else if (SBO.isChildOf(sboTerm, intSBOTermForMODULATION)) {
      return "MODULATION";
    } else {
      if (SBO.isChildOf(sboTerm, intSBOTermForCATALYSIS)) {
        // = intSBOTermForUNKNOWN_CATALYSIS
      }
      return "CATALYSIS";
    }
  }

  /**
   * Sets the maths.
   *
   * @param d1 the d 1
   * @param d2 the d 2
   * @return the SBML document
   */
  public static SBMLDocument setMaths(SBMLDocument d1, SBMLDocument d2) {
    TreeNode node2 = d2.getRoot();
    setMaths(d1, node2);
    return d1;
  }

  /**
   * Sets the maths.
   *
   * @param d1 the d 1
   * @param node2 the node 2
   */
  public static void setMaths(SBMLDocument d1, TreeNode node2) {
    for (int i = 0; i< node2.getChildCount(); i++) {
      TreeNode node = node2.getChildAt(i);
      if (node instanceof AbstractMathContainer) {
        setMathObject(d1, (AbstractMathContainer) node);
      }
      setMaths(d1, node);
    }
  }

  /**
   * Sets the math object.
   *
   * @param d the d
   * @param math the math
   */
  public static void setMathObject(SBMLDocument d, AbstractMathContainer math) {

    if (math instanceof FunctionDefinition) {
      d.getModel().addFunctionDefinition((FunctionDefinition) math.clone());
    } else if (math instanceof KineticLaw) {
      KineticLaw kl = (KineticLaw) math;
      d.getModel().getReaction(kl.getParent().getId()).setKineticLaw((KineticLaw) math.clone());
    } else if (math instanceof EventAssignment) {
      EventAssignment ea = (EventAssignment) math;
      Event e = (Event) ea.getParentSBMLObject();
      d.getModel().getEvent(e.getId()).getListOfEventAssignments().append((EventAssignment) math.clone());
    } else if (math instanceof InitialAssignment) {
      d.getModel().addInitialAssignment((InitialAssignment) math.clone());
    } else if (math instanceof Rule) {
      d.getModel().addRule((Rule) math.clone());
    } else if (math instanceof Priority) {
      Priority p = (Priority) math;
      Event e = p.getParent();
      d.getModel().getEvent(e.getId()).setPriority((Priority) math.clone());
    } else if (math instanceof StoichiometryMath) {

    } else if (math instanceof Trigger) {
      Trigger t = (Trigger) math;
      Event e = t.getParent();
      d.getModel().getEvent(e.getId()).setTrigger((Trigger) math.clone());
    } else if (math instanceof Constraint) {
      d.getModel().addConstraint((Constraint) math.clone());
    } else if (math instanceof Delay) {
      Delay delay = (Delay) math;
      Event e = delay.getParent();
      d.getModel().getEvent(e.getId()).setDelay((Delay) math.clone());
    } else {
      System.err.println("Unknown Math Class");
    }
  }

  /**
   * This method ensures the integrity of the SBML data structure by removing
   * empty instances of {@link ListOf}.
   * 
   * @param sbase
   */
  public static void removeEmptyLists(SBase sbase) {
    if ((sbase instanceof ListOf<?>) && ((ListOf<?>) sbase).isEmpty()) {
      logger.info(MessageFormat.format("Removing empty {0} from SBML document.", sbase.getElementName()));
      sbase.removeFromParent();
    } else {
      for (int i = 0; i < sbase.getChildCount(); i++) {
        TreeNode node = sbase.getChildAt(i);
        if (node instanceof SBase) {
          removeEmptyLists((SBase) node);
        }
      }
    }
  }

}
