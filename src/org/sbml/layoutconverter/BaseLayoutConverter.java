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
import java.util.List;

import javax.xml.stream.XMLStreamException;

import org.sbml.jsbml.Model;
import org.sbml.jsbml.SBMLDocument;
import org.sbml.jsbml.SBMLError;
import org.sbml.jsbml.SBMLErrorLog;
import org.sbml.jsbml.SBMLException;
import org.sbml.jsbml.SBMLReader;
import org.sbml.jsbml.TidySBMLWriter;
import org.sbml.jsbml.ext.layout.Layout;
import org.sbml.wrapper.ModelWrapper;

// TODO: Auto-generated Javadoc
/**
 * The Class abstractLayoutConverter.
 *
 * @author Kaito Ii
 *         Date Created: Jun 26, 2016
 */
public abstract class BaseLayoutConverter {

  /** The document. */
  protected SBMLDocument        document;
  /** The model. */
  protected Model               model;
  /** The output file name. */
  protected String              outputFileName;
  /** The m wrapper. */
  protected ModelWrapper        mWrapper;
  /** The layout. */
  protected Layout              layout;
  /** The convert default compartment. */
  protected boolean             convertDefaultCompartment = false;
  /** The Constant DEFAULT_SPECIES_WIDTH. */
  protected static final double DEFAULT_SPECIES_WIDTH     = 80.0;
  /** The Constant DEFAULT_SPECIES_HEIGHT. */
  protected static final double DEFAULT_SPECIES_HEIGHT    = 40.0;
  /** The Constant DEFAULT_SPECIES_DEPTH. */
  protected static final double DEFAULT_SPECIES_DEPTH     = 1.0;
  /** The convert 2 FBC. */
  protected boolean             convert2FBC               = false;
  /** The convert 2 multi. */
  protected boolean             convert2Multi             = false;


  /**
   * Instantiates a new base layout converter.
   *
   * @param file
   *        the file
   * @throws XMLStreamException
   *         the XML stream exception
   * @throws IOException
   *         Signals that an I/O exception has occurred.
   */
  public BaseLayoutConverter(File file) throws XMLStreamException, IOException {
    document = SBMLReader.read(file);
    outputFileName = SBMLUtil.createOutputFileName(file);
  }


  /**
   * Instantiates a new base layout converter.
   *
   * @param file
   *        the file
   * @param outputFileName
   *        the output file name
   * @throws XMLStreamException
   *         the XML stream exception
   * @throws IOException
   *         Signals that an I/O exception has occurred.
   */
  public BaseLayoutConverter(File file, String outputFileName)
      throws XMLStreamException, IOException {
    this(file);
    this.outputFileName = outputFileName;
  }


  /**
   * Instantiates a new base layout converter.
   *
   * @param file
   *        the file
   * @param defaultCompartment
   *        the default compartment
   * @throws XMLStreamException
   *         the XML stream exception
   * @throws IOException
   *         Signals that an I/O exception has occurred.
   */
  public BaseLayoutConverter(File file, boolean defaultCompartment)
      throws XMLStreamException, IOException {
    this(file);
    convertDefaultCompartment = defaultCompartment;
  }


  /**
   * Instantiates a new base layout converter.
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
   */
  public BaseLayoutConverter(File file, boolean defaultCompartment,
    String outputFileName) throws XMLStreamException, IOException {
    this(file, outputFileName);
    convertDefaultCompartment = defaultCompartment;
  }


  /**
   * Instantiates a new base layout converter.
   *
   * @param file
   *        the file
   * @param options
   *        the options
   * @throws XMLStreamException
   *         the XML stream exception
   * @throws IOException
   *         Signals that an I/O exception has occurred.
   */
  public BaseLayoutConverter(File file, ApplicationOption options)
      throws XMLStreamException, IOException {
    this(file);
    outputFileName = options.isSetOutput() ? options.getOutput()
      : SBMLUtil.createOutputFileName(file);
    convertDefaultCompartment = options.isDefaultCompartment();
  }


  /**
   * Gets the document.
   *
   * @return the document
   */
  public SBMLDocument getDocument() {
    return document;
  }


  /**
   * Sets the document.
   *
   * @param document
   *        the document to set
   */
  public void setDocument(SBMLDocument document) {
    this.document = document;
  }


  /**
   * Gets the model.
   *
   * @return the model
   */
  public Model getModel() {
    return model;
  }


  /**
   * Sets the model.
   *
   * @param model
   *        the model to set
   */
  public void setModel(Model model) {
    this.model = model;
  }


  /**
   * Convert.
   */
  public abstract void convert();


  /**
   * Save.
   */
  public void save() {
    try {
      TidySBMLWriter.write(document, new File(outputFileName), ' ', (short) 2);
    } catch (SBMLException | XMLStreamException | IOException e) {
      e.printStackTrace();
    }
  }


  /**
   * Validate.
   */
  public void validate() {
    document.checkConsistencyOffline();
    SBMLErrorLog errorLog = document.getListOfErrors();
    List<SBMLError> errorList = errorLog.getValidationErrors();
    for (SBMLError e : errorList) {
      System.out.println(e.getLine() + " " + e.getMessage());
    }
  }


  /**
   * Print
   */
  public void print() {
    try {
      String docStr = new TidySBMLWriter().writeSBMLToString(document);
      System.out.println(docStr);
    } catch (SBMLException e) {
      e.printStackTrace();
    } catch (XMLStreamException e) {
      e.printStackTrace();
    }
  }


  /**
   * Checks if is convert default compartment.
   *
   * @return the convertDefaultCompartment
   */
  public boolean isConvertDefaultCompartment() {
    return convertDefaultCompartment;
  }


  /**
   * Sets the convert default compartment.
   *
   * @param convertDefaultCompartment
   *        the convertDefaultCompartment to set
   */
  public void setConvertDefaultCompartment(boolean convertDefaultCompartment) {
    this.convertDefaultCompartment = convertDefaultCompartment;
  }


  /**
   * Checks if is convert 2 FBC.
   *
   * @return the convert2FBC
   */
  public boolean isConvert2FBC() {
    return convert2FBC;
  }


  /**
   * Sets the convert 2 FBC.
   *
   * @param convert2fbc
   *        the convert2FBC to set
   */
  public void setConvert2FBC(boolean convert2fbc) {
    convert2FBC = convert2fbc;
  }


  /**
   * Checks if is convert 2 multi.
   *
   * @return the convert2Multi
   */
  public boolean isConvert2Multi() {
    return convert2Multi;
  }


  /**
   * Sets the convert 2 multi.
   *
   * @param convert2Multi
   *        the convert2Multi to set
   */
  public void setConvert2Multi(boolean convert2Multi) {
    this.convert2Multi = convert2Multi;
  }
}
