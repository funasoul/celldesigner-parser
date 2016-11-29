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

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.Option;

// TODO: Auto-generated Javadoc
/**
 * The Class ApplicationOption.
 *
 * @author Kaito Ii
 *         Date Created: Aug 11, 2016
 */
public class ApplicationOption {

  /** The help. */
  @Option(name = "-h", aliases = {"--help"}, required = false, usage = "help")
  private boolean help = false;
  /** The CD 2 layout. */
  @Option(name = "-c", aliases = {"--CD2Layout"}, required = false, usage = "convert CD to layout")
  private Boolean CD2Layout;
  /** The Layout 2 CD. */
  @Option(name = "-l", aliases = {"--Layout2CD"}, required = false, usage = "convert layout to CD")
  private Boolean Layout2CD;
  /** The default compartment. */
  @Option(name = "-d", aliases = {"--withDefaultCompartment","--defaultCompartment"}, required = false, usage = "convert with default compartment")
  private Boolean defaultCompartment;
  /** The convert 2 FBC. */
  @Option(name = "-f", aliases = {"--fbc"}, required = false, usage = "add FBC packages")
  private boolean convert2FBC;
  /** The input. */
  @Argument(index = 0, metaVar = "input files", required = true, usage = "input file")
  private String  input;
  /** The output. */
  @Argument(index = 1, metaVar = "output files", required = false, usage = "output file")
  private String  output;


  /**
   * Checks if is help.
   *
   * @return the help
   */
  public boolean isHelp() {
    return help;
  }


  /**
   * Checks if is CD 2 layout.
   *
   * @return the cD2Layout
   */
  public boolean isCD2Layout() {
    return CD2Layout;
  }


  /**
   * Checks if is sets the CD 2 layout.
   *
   * @return true, if is sets the CD 2 layout
   */
  public boolean issetCD2Layout() {
    if (CD2Layout == null)
      return false;
    return true;
  }


  /**
   * Checks if is layout 2 CD.
   *
   * @return the layout2CD
   */
  public boolean isLayout2CD() {
    return Layout2CD;
  }


  /**
   * Checks if is sets the layout 2 CD.
   *
   * @return true, if is sets the layout 2 CD
   */
  public boolean issetLayout2CD() {
    if (Layout2CD == null)
      return false;
    return true;
  }


  /**
   * Checks if is sets the conversion direction.
   *
   * @return true, if is sets the conversion direction
   */
  public boolean isSetConversionDirection() {
    if (issetLayout2CD() || issetCD2Layout())
      return true;
    return false;
  }


  /**
   * Checks if is 2 CD 2 layout.
   *
   * @return true, if is 2 CD 2 layout
   */
  public boolean is2CD2Layout() {
    if (issetCD2Layout())
      return CD2Layout;
    else
      return Layout2CD;
  }


  /**
   * Checks if is default compartment.
   *
   * @return the defaultCompartmen
   */
  public boolean isDefaultCompartment() {
    if (defaultCompartment == null)
      return true;
    return defaultCompartment;
  }


  /**
   * Gets the input.
   *
   * @return the input
   */
  public String getInput() {
    return input;
  }


  /**
   * Checks if is sets the output.
   *
   * @return true, if is sets the output
   */
  public boolean isSetOutput() {
    if (output == null)
      return false;
    return true;
  }


  /**
   * Gets the output.
   *
   * @return the output
   */
  public String getOutput() {
    if (output == null)
      return "";
    return output;
  }


  /**
   * Gets the convert 2 FBC.
   *
   * @return the convert 2 FBC
   */
  public boolean isConvert2FBC() {
    return convert2FBC;
  }
}
