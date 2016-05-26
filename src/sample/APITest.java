package sample;

import java.io.File;
import java.math.BigDecimal;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.JAXBIntrospector;
import javax.xml.bind.Unmarshaller;

import org.sbml._2001.ns.celldesigner.SpeciesAlias;
import org.sbml.sbml.level2.version4.Sbml;
import org.sbml.sbml.level2.version4.Species;


/**
 * @author Kaito Ii
 * @autho Akira Funahashi
 *
 * Date Created: May 20, 2016
 * Date Modified: May 24, 2016
 */

public class APITest {

  /**
   * @param args
   * void
   */
  public static void main(String[] args) {
    JAXBContext context;
    Sbml sbml = null;
    try {
      context = JAXBContext.newInstance(Sbml.class);
      Unmarshaller unmarshaller = context.createUnmarshaller();
      Object schemaObject = JAXBIntrospector.getValue(unmarshaller.unmarshal(new File("sample/sample.xml")));
      sbml = (Sbml) schemaObject;
    } catch (JAXBException e) {
      e.printStackTrace();
    }

    List<SpeciesAlias> saList = sbml.getModel().getAnnotation().getExtension().getListOfSpeciesAliases().getSpeciesAlias();
    for (SpeciesAlias sa : saList) {
      String str = sa.getId() + ":" + sa.getSpecies() + ":";
      str += "(" + sa.getBounds().getX() + "," + sa.getBounds().getY() + ") [";
      str += sa.getBounds().getW() + " x " + sa.getBounds().getH() + "]";
      System.out.println(str);
    }
    
    List<Species> sList = sbml.getModel().getListOfSpecies().getSpecies();
    for(Species s : sList){
      String str = s.getAnnotation().getExtension().getPositionToCompartment();
      System.out.println(s.getId() + ":" + str);
    }
  
  }

}
