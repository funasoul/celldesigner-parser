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
package org.sbml.extconverter;

import org.sbml.jsbml.Model;
import org.sbml.wrapper.ModelWrapper;

// TODO: Auto-generated Javadoc
/**
 * The Class EXTConverter.
 *
 * @author Kaito Ii
 *         Date Created: Aug 22, 2016
 */
public abstract class EXTConverter {

  /** The model. */
  protected Model        model;
  /** The m wrapper. */
  protected ModelWrapper mWrapper;


  /**
   * Instantiates a new EXT converter.
   *
   * @param model
   *        the model
   * @param mWrapper
   *        the m wrapper
   */
  public EXTConverter(Model model, ModelWrapper mWrapper) {
    this.model = model;
    this.mWrapper = mWrapper;
  }


  /**
   * Convert.
   */
  public abstract void convert();
}
