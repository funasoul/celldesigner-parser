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
		this(file, SBMLUtil.isSetCellDesignerNameSpace(file), SBMLUtil.createOutputFileName(file));
	}

	/**
	 * Instantiates a new layout converter.
	 *
	 * @param file
	 *            the file
	 * @param isCD2Layout
	 *            the is CD 2 layout
	 * @throws JAXBException
	 *             the JAXB exception
	 * @throws XMLStreamException
	 *             the XML stream exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public LayoutConverter(File file, boolean isCD2Layout) throws JAXBException, XMLStreamException, IOException {
		if(isCD2Layout)
			converter = new CD2LayoutConverter(file);
		else
			converter = new Layout2CDConverter(file);
	}

	/**
	 * Instantiates a new layout converter.
	 *
	 * @param file the file
	 * @param isCD2Layout the is CD 2 layout
	 * @param outputFileName the output file name
	 * @throws JAXBException the JAXB exception
	 * @throws XMLStreamException the XML stream exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public LayoutConverter(File file, boolean isCD2Layout, String outputFileName) throws JAXBException, XMLStreamException, IOException {
		if(isCD2Layout)
			converter = new CD2LayoutConverter(file, outputFileName);
		else
			converter = new Layout2CDConverter(file, outputFileName);
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
		this(file, SBMLUtil.isSetCellDesignerNameSpace(file), outputFileName);
		
	}

	/**
	 * Convert.
	 */
	public void convert(){
		converter.convert();
	}
	
	/**
	 * void TODO.
	 */
	public void validate() {
		converter.validate();
	}

	/**
	 * void TODO.
	 */
	public void print() {
		converter.print();
	}

	/**
	 * void TODO.
	 */
	public void save() {
		converter.save();
	}

	/**
	 * The main method.
	 *
	 * @param args
	 *            the arguments
	 */
	public static void main(String[] args) {
		LayoutConverter converter;
		try {
			if(args.length >= 2)
				converter = new LayoutConverter(new File(args[0]), args[1]);
			else if(args.length == 1)
				converter = new LayoutConverter(new File(args[0]));
			else
				//converter = new LayoutConverter(new File("sample/sample.xml"));
				converter = new LayoutConverter(new File("sample/layout_example1_L3.xml"));
				//converter = new LayoutConverter(new File("sample/sample2.xml"));
		} catch (JAXBException e) {
			System.err.println("Error unmarshaling XML");
			e.printStackTrace();
			return;
		} catch (XMLStreamException | IOException e) {
			System.err.println("Error reading SBML model");
			e.printStackTrace();
			return;
		}

		converter.convert();
		converter.save();
		converter.print();
		converter.validate();
	}
}
