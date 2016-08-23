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
/*
 * 
 */
package org.sbml.layoutconverter;

import org.sbml.jsbml.SBMLDocument;

// TODO: Auto-generated Javadoc
/**
 * The Class ModelUpgrader.
 *
 * @author Kaito Ii
 * 
 * Date Created: May 26, 2016
 */

public class SBMLLevelandVersionHandler {

	/**
	 * Upgrade.
	 *
	 * @param document the document
	 * @return SBMLDocument
	 * TODO
	 */
	public static SBMLDocument upgrade(SBMLDocument document){
		if(document.getLevel() != SBMLUtil.DEFAULT_SBML_LEVEL || document.getVersion() != SBMLUtil.DEFAULT_SBML_VERSION )
			document.setLevelAndVersion(SBMLUtil.DEFAULT_SBML_LEVEL, SBMLUtil.DEFAULT_SBML_VERSION);
		
		document = SBMLModelCompleter.autoCompleteRequiredAttributes(document);
		
		return document;
	}

	/**
	 * Downgrade.
	 *
	 * @param document the document
	 * @return the SBML document
	 */
	public static SBMLDocument downgrade(SBMLDocument document){
		if(document.getLevel() > SBMLUtil.DEFAULT_CELLDESIGNER_SBML_LEVEL || document.getVersion() != SBMLUtil.DEFAULT_CELLDESIGNER_SBML_VERSION)
			document.setLevelAndVersion(SBMLUtil.DEFAULT_CELLDESIGNER_SBML_LEVEL, SBMLUtil.DEFAULT_CELLDESIGNER_SBML_VERSION);
		
		document = SBMLModelCompleter.autoCompleteRequiredAttributes(document);

		return document;
	}

}
