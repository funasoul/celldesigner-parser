package sample;

import java.io.File;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.JAXBIntrospector;
import javax.xml.bind.Unmarshaller;

import org.sbml.sbml.level2.version4.Sbml;
import org.sbml.sbml.level2.version4.Species;
import org.sbml.wrapper.SpeciesWrapper;

/**
 * @author Kaito Ii
 *
 * Date Created: May 24, 2016
 */

public class APITest {

	public static void main(String[] args){
		  JAXBContext context;
		    Sbml sbml = null;
		    try {
		      context = JAXBContext.newInstance(Sbml.class);
		        Unmarshaller unmarshaller = context.createUnmarshaller();
		      Object schemaObject = JAXBIntrospector.getValue(unmarshaller.unmarshal(new File("sample/ex1.xml")));
		      sbml = (Sbml) schemaObject;
		    } catch (JAXBException e) {
		      e.printStackTrace();
		    }
		    
		    List<Species> sList = sbml.getModel().getListOfSpecies().getSpecies();
		    for(Species s : sList){
		      String str = s.getAnnotation().getExtension().getPositionToCompartment();
		      
		      System.out.println(s.getId() + ":" + str);
		    }
		    
	}
}
