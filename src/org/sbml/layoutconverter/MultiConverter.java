package org.sbml.layoutconverter;

import org.sbml.jsbml.Model;
import org.sbml.jsbml.ext.multi.MultiConstants;
import org.sbml.wrapper.ModelWrapper;

// TODO: Auto-generated Javadoc
/**
 * The Class MultiConverter.
 *
 * @author Kaito Ii
 * 
 * Date Created: Aug 7, 2016
 */

public class MultiConverter {

	/** The model. */
	private Model model;
	
	/** The m wrapper. */
	private ModelWrapper mWrapper;
	
	/**
	 * Instantiates a new multi converter.
	 *
	 * @param model the model
	 * @param mWrapper the m wrapper
	 */
	public MultiConverter(Model model, ModelWrapper mWrapper){
		this.model = model;
		this.mWrapper = mWrapper;
		
		model.getSBMLDocument().setPackageRequired(MultiConstants.shortLabel, false);
	}
	
	/**
	 * Convert.
	 */
	public void convert(){
		
	}
}
