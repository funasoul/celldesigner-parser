package org.sbml.layoutconverter;

import java.io.File;
import java.io.IOException;

import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLStreamException;

/**
 * @author Kaito Ii
 *
 * Date Created: Jul 7, 2016
 */

public class Application {

	public Application(String[] args){
		LayoutConverter converter;
		String filepath = "";
		String outputpath = "";
		Boolean isCD2Layout = null;
		
		for(int i = 0 ; i < args.length; i++){
			if(filepath.isEmpty() && args[i].endsWith(".xml")){
				filepath = args[i];
			} else if(outputpath.isEmpty()  && args[i].endsWith(".xml")){
				outputpath = args[i];
			} else if(args[i].contains("CD2Layout")){
				isCD2Layout = true;
			} else if(args[i].contains("Layout2CD")){
				isCD2Layout = false;
			} else{
				
			}
		}
		System.out.println(filepath);
		System.out.println(outputpath);
		try {
			if(!filepath.isEmpty() && !outputpath.isEmpty() && isCD2Layout != null)
				converter = new LayoutConverter(new File(filepath), isCD2Layout, outputpath);
			else if(!filepath.isEmpty() && !outputpath.isEmpty())
				converter = new LayoutConverter(new File(filepath), outputpath);
			else if(!filepath.isEmpty() && isCD2Layout != null)
				converter = new LayoutConverter(new File(filepath), isCD2Layout);
			else
				converter = new LayoutConverter(new File(filepath));

			//converter = new LayoutConverter(new File());
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
	
	public static void main(String[] args){
		new Application(args);
	}
}
