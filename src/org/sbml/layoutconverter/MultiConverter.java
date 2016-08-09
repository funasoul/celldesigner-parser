package org.sbml.layoutconverter;

import org.sbml.jsbml.Model;
import org.sbml.jsbml.ext.multi.MultiConstants;
import org.sbml.wrapper.ModelWrapper;

/**
 * @author Kaito Ii
 *
 * Date Created: Aug 7, 2016
 */

public class MultiConverter {

	private Model model;
	
	private ModelWrapper mWrapper;
	
	public MultiConverter(Model model, ModelWrapper mWrapper){
		this.model = model;
		this.mWrapper = mWrapper;
		
		model.getSBMLDocument().setPackageRequired(MultiConstants.shortLabel, false);
	}
	
	public void convert(){
		
	}
}
