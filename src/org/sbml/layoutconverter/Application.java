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
import java.util.logging.Logger;

import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLStreamException;

import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;

// TODO: Auto-generated Javadoc
/**
 * The Class Application.
 *
 * @author Kaito Ii
 *         Date Created: Jul 7, 2016
 */
public class Application {

  /**
   * A {@link Logger} for this class.
   */
  private static final Logger logger = Logger.getLogger(Application.class.getName());

  /** The converter. */
  private LayoutConverter converter;

  /**
   * Instantiates a new application.
   *
   * @param args the args
   */
  public Application(String[] args) {
    ApplicationOption option = new ApplicationOption();
    CmdLineParser parser = new CmdLineParser(option);

    try {
      parser.parseArgument(args);
    } catch (CmdLineException e1) {
      logger.severe(e1.getMessage());
      parser.printUsage(System.err);
      System.exit(1);
    }

    if (option.isHelp()) {
      parser.printUsage(System.err);
      System.exit(1);
    }

    String filepath = option.getInput();
    String outputpath = option.getOutput();

    System.out.println(filepath);
    System.out.println(outputpath);
    long time = System.currentTimeMillis();
    try {
      converter = new LayoutConverter(new File(filepath), option);
    } catch (JAXBException e) {
      System.err.println("Error unmarshaling XML");
      System.err.println(e.getLocalizedMessage());
      return;
    } catch (XMLStreamException | IOException e) {
      System.err.println("Error reading SBML model");
      System.err.println(e.getLocalizedMessage());
      return;
    }

    converter.convert();
    logger.info(MessageFormat.format(
      "Time lapsed for conversion: {0,number} s",
      (System.currentTimeMillis() - time)/1e3d));
    converter.save();
    // This causes problems for very large models:
    //converter.print();
    converter.validate();
  }

  /**
   * The main method.
   *
   * @param args
   *        the arguments
   */
  public static void main(String[] args) {
    new Application(args);
  }

}
