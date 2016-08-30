/*******************************************************************************
 * Copyright 2016 Kaito Ii
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
/*
 * 
 */
package org.sbml.wrapper;

import java.io.File;
import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.JAXBIntrospector;
import javax.xml.bind.Marshaller;
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
	private static Model model;
	
	/** The model wrapper. */
	private static ModelWrapper modelWrapper;
	
	/** The context. */
	private static JAXBContext context;
	
	/** The sbml. */
	private static Sbml sbml;
	
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
		sbml = getSbml(file);
		
		model = sbml.getModel();
		modelWrapper = createModelWrapper(model, sbml);
		
		return modelWrapper;
	}
	
	/**
	 * Unmarshal SBML from string.
	 *
	 * @param xml the xml
	 * @return the model wrapper
	 * @throws JAXBException the JAXB exception
	 */
	public static ModelWrapper unmarshalSBMLFromString(String xml) throws JAXBException{
		sbml = getSbml(xml);
		
		model = sbml.getModel();
		modelWrapper = createModelWrapper(model, sbml);
		
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
		context = JAXBContext.newInstance(Sbml.class);
		Unmarshaller unmarshaller = context.createUnmarshaller();
		Object schemaObject = JAXBIntrospector.getValue(unmarshaller.unmarshal(file));
		sbml = (Sbml) schemaObject;
	
		return sbml;
	}

	/**
	 * Gets the sbml.
	 *
	 * @param xml the xml
	 * @return the sbml
	 * @throws JAXBException the JAXB exception
	 */
	public static Sbml getSbml(String xml) throws JAXBException{
		StringReader reader = new StringReader(xml);
		context = JAXBContext.newInstance(Sbml.class);
		Unmarshaller unmarshaller = context.createUnmarshaller();
		Object schemaObject = JAXBIntrospector.getValue(unmarshaller.unmarshal(reader));
		sbml = (Sbml) schemaObject;
	
		return sbml;
	}
	
	/**
	 * Creates a new Object object.
	 *
	 * @param model the model
	 * @param sbml the sbml
	 * @return the model wrapper
	 */
	public static ModelWrapper createModelWrapper(Model model, Sbml sbml){
		return new ModelWrapper(model, sbml);	
	}
	
	/**
	 * Save model.
	 *
	 * @param modelWrapper the model wrapper
	 * @return the file
	 * @throws JAXBException the JAXB exception
	 */
	public static File saveModel(ModelWrapper modelWrapper) throws JAXBException{
		return saveModel(modelWrapper, modelWrapper.getId() + ".xml");
	}
	
	/**
	 * Save model.
	 *
	 * @param modelWrapper the model wrapper
	 * @param fileName the file name
	 * @return the file
	 * @throws JAXBException the JAXB exception
	 */
	public static File saveModel(ModelWrapper modelWrapper, String fileName) throws JAXBException{
		File file = new File(fileName);
		Marshaller marshaller = context.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		marshaller.marshal(modelWrapper.getSbml(), file);

		return file;
	}
	
	/**
	 * Generate XML string.
	 *
	 * @param modelWrapper the model wrapper
	 * @return the string
	 * @throws JAXBException the JAXB exception
	 */
	public static String generateXMLString(ModelWrapper modelWrapper) throws JAXBException{
		StringWriter sw = new StringWriter();
		context = JAXBContext.newInstance(Sbml.class);
		Marshaller marshaller = context.createMarshaller();
		marshaller.marshal(modelWrapper.getSbml(), sw);
		
		return sw.toString();
	}
}
