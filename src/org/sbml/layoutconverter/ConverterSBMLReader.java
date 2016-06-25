/*
 * 
 */
package org.sbml.layoutconverter;

import java.io.File;
import java.io.IOException;

import javax.xml.stream.XMLStreamException;

import org.sbml.jsbml.SBMLDocument;
import org.sbml.jsbml.SBMLReader;

// TODO: Auto-generated Javadoc
/**
 * The Class ConverterSBMLReader.
 *
 * @author Kaito Ii
 * 
 * Date Created: May 28, 2016
 */

public class ConverterSBMLReader {

	/**
	 * Read.
	 *
	 * @param file the file
	 * @return the SBML document
	 * @throws XMLStreamException the XML stream exception
	 * @throws IOException SBMLDocument
	 * TODO
	 */
	public static SBMLDocument read(File file) throws XMLStreamException, IOException{
		SBMLDocument document = SBMLReader.read(file);
		
		return document;
	}
}
