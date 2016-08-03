package org.sbml.layoutconverter;

import java.io.File;
import java.io.IOException;

import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLStreamException;

// TODO: Auto-generated Javadoc
/**
 * The Class Application.
 *
 * @author Kaito Ii
 * 
 * Date Created: Jul 7, 2016
 */

public class Application {

	/**
	 * Instantiates a new application.
	 *
	 * @param args the args
	 */
	public Application(String[] args){
		LayoutConverter converter;
		String filepath = "";
		String outputpath = "";
		Boolean isCD2Layout = null;
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
				Application.printUsage();
				return ;
			}
		}
		System.out.println(filepath);
		System.out.println(outputpath);
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
	
	/**
	 * Prints the usage.
	 */
	public static void printUsage(){
		System.err.println("Illegal option");
		System.err.println("");
	}
	
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args){
		new Application(args);
	}
}
