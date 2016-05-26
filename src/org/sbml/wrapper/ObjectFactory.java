package org.sbml.wrapper;

import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.JAXBIntrospector;
import javax.xml.bind.Unmarshaller;

import org.sbml.sbml.level2.version4.Model;
import org.sbml.sbml.level2.version4.Sbml;

/**
 * @author Kaito Ii
 *
 * Date Created: May 24, 2016
 */

public class ObjectFactory {
	static Model model;
	static ModelWrapper modelWrapper;
	
	public static ModelWrapper unmarshalSBML(String file) throws JAXBException{	
		return unmarshalSBML(new File(file));
	}
	
	public static ModelWrapper unmarshalSBML(File file) throws JAXBException{
		Sbml sbml = getSbml(file);
		
		model = sbml.getModel();
		modelWrapper = createModelWrapper(model);
		return modelWrapper;
	}
	
	public static Sbml getSbml(File file) throws JAXBException{
		JAXBContext context;
		Sbml sbml = null;
		context = JAXBContext.newInstance(Sbml.class);
		Unmarshaller unmarshaller = context.createUnmarshaller();
		Object schemaObject = JAXBIntrospector.getValue(unmarshaller.unmarshal(file));
		sbml = (Sbml) schemaObject;

	   return sbml;
	}
	
	public static ModelWrapper createModelWrapper(Model model){
		return new ModelWrapper(model);	
	}
}
