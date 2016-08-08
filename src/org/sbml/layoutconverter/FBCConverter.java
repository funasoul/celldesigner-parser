package org.sbml.layoutconverter;

import java.io.File;
import java.io.IOException;

import javax.xml.stream.XMLStreamException;

import org.sbml.jsbml.Model;
import org.sbml.jsbml.SBMLDocument;
import org.sbml.jsbml.SBMLReader;
import org.sbml.jsbml.ext.fbc.FBCModelPlugin;
import org.sbml.wrapper.ModelWrapper;

/**
 * @author Kaito Ii
 *
 * Date Created: Aug 6, 2016
 */

public class fbcConverter {

	private SBMLDocument document;
	
	private Model model;
	
	private ModelWrapper mWrapper;
	
	public fbcConverter(SBMLDocument document, ModelWrapper mWrapper) throws IOException, XMLStreamException{
		this.document = document;
		this.model = document.getModel();
		this.mWrapper = mWrapper;
		

		FBCModelPlugin fbcPlugin = (FBCModelPlugin) model.getPlugin("fbc");
		fbcPlugin.create
	}
}
