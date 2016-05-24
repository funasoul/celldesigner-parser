package org.sbml.wrapper;

import org.sbml._2001.ns.celldesigner.CompartmentAnnotationType;
import org.sbml.sbml.level2.version4.Compartment;

/**
 * @author Kaito Ii
 *
 * Date Created: May 24, 2016
 */

public class CompartmentWrapper extends Compartment {
	
	private Compartment compartment;
	private CompartmentAnnotationType annotation;
	
	
	public CompartmentWrapper(Compartment c){
		this.compartment = c;
		annotation = c.getAnnotation();
	}
	
	public String getName(){
		return annotation.getExtension().getName();
	}
	
	
	public void setName(String value){
		annotation.getExtension().setName(value);;
	}
}
