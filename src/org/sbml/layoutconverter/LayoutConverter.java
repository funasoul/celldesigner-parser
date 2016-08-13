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
		//String filepath = "EcoliCore.sbml.xml";
		//String filepath = "1752-0509-7-97-S4.xml";
		String filepath = "comp.xml";
		String outputpath = "";
		Boolean isCD2Layout = true;
		Boolean defaultCompartment = false;
		
		for(int i = 0 ; i < args.length; i++){
			if(filepath.isEmpty() && args[i].endsWith(".xml")){
				filepath = args[i];
			} else if(outputpath.isEmpty()  && args[i].endsWith(".xml")){
				outputpath = args[i];
			} else if(args[i].contains("CD2Layout")){
				isCD2Layout = true;
			} else if(args[i].contains("Layout2CD")){
				isCD2Layout = false;
			} else if(args[i].contains("defaultCompartment")){
				defaultCompartment = true;
			} else {
			//	Application.printUsage();
				return ;
			}
		}
		
		try {
			if(!filepath.isEmpty() && !outputpath.isEmpty() && isCD2Layout != null)
				converter = new LayoutConverter(new File(filepath), defaultCompartment, isCD2Layout, outputpath);
			else if(!filepath.isEmpty() && !outputpath.isEmpty())
				converter = new LayoutConverter(new File(filepath), defaultCompartment, outputpath);
			else if(!filepath.isEmpty() && isCD2Layout != null)
				converter = new LayoutConverter(new File(filepath), defaultCompartment, isCD2Layout);
			else
				converter = new LayoutConverter(new File(filepath), defaultCompartment);

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
