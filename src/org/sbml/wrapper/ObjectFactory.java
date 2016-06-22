package org.sbml.wrapper;

import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.JAXBIntrospector;
import javax.xml.bind.Unmarshaller;

import org.sbml.sbml.level2.version4.Model;
import org.sbml.sbml.level2.version4.Sbml;

// TODO: Auto-generated Javadoc
/**
 * A factory for creating Object objects.
 *
 * @author Kaito Ii
 * 
 * Date Created: May 24, 2016
 */

public class ObjectFactory {
	
	/** The model. */
	static Model model;
	
	/** The model wrapper. */
	static ModelWrapper modelWrapper;
	
	/**
	 * Unmarshal SBML.
	 *
	 * @param file the file
	 * @return the model wrapper
	 * @throws JAXBException the JAXB exception
	 */
	public static ModelWrapper unmarshalSBML(String file) throws JAXBException{	
		return unmarshalSBML(new File(file));
	}
	
	/**
	 * Unmarshal SBML.
	 *
	 * @param file the file
	 * @return the model wrapper
	 * @throws JAXBException the JAXB exception
	 */
	public static ModelWrapper unmarshalSBML(File file) throws JAXBException{
		Sbml sbml = getSbml(file);
		
		model = sbml.getModel();
		modelWrapper = createModelWrapper(model);
		return modelWrapper;
	}
	
	/**
	 * Gets the sbml.
	 *
	 * @param file the file
	 * @return the sbml
	 * @throws JAXBException the JAXB exception
	 */
	public static Sbml getSbml(File file) throws JAXBException{
		JAXBContext context;
		Sbml sbml = null;
		context = JAXBContext.newInstance(Sbml.class);
		Unmarshaller unmarshaller = context.createUnmarshaller();
		Object schemaObject = JAXBIntrospector.getValue(unmarshaller.unmarshal(file));
		sbml = (Sbml) schemaObject;

	   return sbml;
	}
	
	/**
	 * Creates a new Object object.
	 *
	 * @param model the model
	 * @return the model wrapper
	 */
	public static ModelWrapper createModelWrapper(Model model){
		return new ModelWrapper(model);	
	}
}
