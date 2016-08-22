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

import java.io.File;
import java.io.IOException;

import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLStreamException;

// TODO: Auto-generated Javadoc
/**
 * The Class LayoutConverter.
 *
 * @author Kaito Ii
 * 
 *         Date Created: May 26, 2016
 */

public class LayoutConverter {

	/** The converter. */
	private BaseLayoutConverter converter;

	/**
	 * Instantiates a new layout converter.
	 *
	 * @param file
	 *            the file
	 * @throws JAXBException
	 *             the JAXB exception
	 * @throws XMLStreamException
	 *             the XML stream exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public LayoutConverter(File file) throws JAXBException, XMLStreamException, IOException {
		this(file, false, SBMLUtil.isSetCellDesignerNameSpace(file), SBMLUtil.createOutputFileName(file));
	}

	/**
	 * Instantiates a new layout converter.
	 *
	 * @param file            the file
	 * @param defaultCompartment the default compartment
	 * @param isCD2Layout            the is CD 2 layout
	 * @throws JAXBException             the JAXB exception
	 * @throws XMLStreamException             the XML stream exception
	 * @throws IOException             Signals that an I/O exception has occurred.
	 */
	public LayoutConverter(File file, boolean defaultCompartment, boolean isCD2Layout) throws JAXBException, XMLStreamException, IOException {
		if(isCD2Layout)
			converter = new CD2LayoutConverter(file, defaultCompartment);
		else
			converter = new Layout2CDConverter(file, defaultCompartment);
	}

	/**
	 * Instantiates a new layout converter.
	 *
	 * @param file the file
	 * @param defaultCompartment the default compartment
	 * @param isCD2Layout the is CD 2 layout
	 * @param outputFileName the output file name
	 * @throws JAXBException the JAXB exception
	 * @throws XMLStreamException the XML stream exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public LayoutConverter(File file, boolean defaultCompartment, boolean isCD2Layout, String outputFileName) throws JAXBException, XMLStreamException, IOException {
		if(isCD2Layout)
			converter = new CD2LayoutConverter(file, defaultCompartment, outputFileName);
		else
			converter = new Layout2CDConverter(file, defaultCompartment, outputFileName);
	}
	
	/**
	 * Instantiates a new layout converter.
	 *
	 * @param file the file
	 * @param outputFileName the output file name
	 * @throws JAXBException the JAXB exception
	 * @throws XMLStreamException the XML stream exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public LayoutConverter(File file, String outputFileName) throws JAXBException, XMLStreamException, IOException {
		this(file, false, SBMLUtil.isSetCellDesignerNameSpace(file), outputFileName);	
	}

	/**
	 * Instantiates a new layout converter.
	 *
	 * @param file the file
	 * @param defaultCompartment the default compartment
	 * @throws JAXBException the JAXB exception
	 * @throws XMLStreamException the XML stream exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public LayoutConverter(File file, Boolean defaultCompartment) throws JAXBException, XMLStreamException, IOException {
		this(file, defaultCompartment, SBMLUtil.isSetCellDesignerNameSpace(file), SBMLUtil.createOutputFileName(file));
	}

	/**
	 * Instantiates a new layout converter.
	 *
	 * @param file the file
	 * @param defaultCompartment the default compartment
	 * @param outputpath the outputpath
	 * @throws JAXBException the JAXB exception
	 * @throws XMLStreamException the XML stream exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public LayoutConverter(File file, Boolean defaultCompartment, String outputpath) throws JAXBException, XMLStreamException, IOException {
		this(file, defaultCompartment, SBMLUtil.isSetCellDesignerNameSpace(file), outputpath);
	}

	/**
	 * Instantiates a new layout converter.
	 *
	 * @param file the file
	 * @param options the options
	 * @throws XMLStreamException the XML stream exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws JAXBException the JAXB exception
	 */
	public LayoutConverter(File file, ApplicationOption options) throws XMLStreamException, IOException, JAXBException{
		if((options.isSetConversionDirection() && options.issetCD2Layout() && options.isCD2Layout()) ||
				(!options.isSetConversionDirection() && SBMLUtil.isLevelAndVersionMatchWithCD(file)))
			converter = new CD2LayoutConverter(file, options);
		else
			converter = new Layout2CDConverter(file, options);
	}
	
	/**
	 * Convert.
	 */
	public void convert(){
		converter.convert();
	}
	
	/**
	 * void.
	 */
	public void validate() {
		converter.validate();
	}

	/**
	 * void.
	 */
	public void print() {
		converter.print();
	}

	/**
	 * void.
	 */
	public void save() {
		converter.save();
	}
}
