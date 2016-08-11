package org.sbml.layoutconverter;

import java.io.File;
import java.io.IOException;

import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLStreamException;

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

// TODO: Auto-generated Javadoc
/**
 * The Class Application.
 *
 * @author Kaito Ii
 * 
 * Date Created: Jul 7, 2016
 */

public class Application {

	LayoutConverter converter;
	
	/**
	 * Instantiates a new application.
	 *
	 * @param args the args
	 */
	public Application(String[] args){
		ApplicationOption option = new ApplicationOption();
		CmdLineParser parser = new CmdLineParser(option);
		
		try {
			parser.parseArgument(args);
		} catch (CmdLineException e1) {
			System.out.println(e1.getMessage());
			parser.printUsage(System.err);
			System.exit(1);
		}
		
		String filepath = option.getInput();
		String outputpath = option.getOutput();
		Boolean defaultCompartment = option.isDefaultCompartment();
		Boolean isCD2Layout;
		if(option.issetCD2Layout()) 
			isCD2Layout = option.isCD2Layout();
		else if (option.issetLayout2CD()) 
			isCD2Layout =  !option.isLayout2CD();
		else 
			isCD2Layout = null;

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
			return;
		} catch (XMLStreamException | IOException e) {
			System.err.println("Error reading SBML model");
			return;
		} 
		
		converter.convert();
		converter.save();
		converter.print();
		converter.validate();
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
