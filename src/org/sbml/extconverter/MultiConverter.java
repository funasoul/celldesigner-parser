/*******************************************************************************
 * Copyright 2016 Kaito Ii
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
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
 * The Class MultiConverter.
 *
 * @author Kaito Ii
 * 
 * Date Created: Aug 7, 2016
 */
//TODO convert complex species and included species from CellDesigner Annootation to SBML Multi package
public class MultiConverter extends EXTConverter{
	
	/**
	 * Instantiates a new multi converter.
	 *
	 * @param model the model
	 * @param mWrapper the m wrapper
	 */
	public MultiConverter(Model model, ModelWrapper mWrapper){
		super(model, mWrapper);
	}
	
	/**
	 * Convert.
	 */
	public void convert(){
		
	}
}
