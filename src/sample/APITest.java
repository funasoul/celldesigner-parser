package sample;

import java.util.List;

import javax.xml.bind.JAXBException;

import org.sbml._2001.ns.celldesigner.ConnectScheme;
import org.sbml._2001.ns.celldesigner.SpeciesAlias;
import org.sbml.wrapper.ModelWrapper;
import org.sbml.wrapper.ObjectFactory;
import org.sbml.wrapper.ReactionWrapper;


/**
 * @author Kaito Ii
 * @author Akira Funahashi
 *
 * Date Created: May 20, 2016
 * Date Modified: May 30, 2016
 */

public class APITest {

  /**
   * 
   * @param args
   */
  public static void main(String[] args) {
    ModelWrapper model = null;
    try {
      model = ObjectFactory.unmarshalSBML("sample/sample.xml");
    } catch (JAXBException e) {
      e.printStackTrace();
    } 
    // ListOfSpeciesAlias
    List<SpeciesAlias> saList = model.getListOfSpeciesAliases();
    for (SpeciesAlias sa : saList) {
      String str = sa.getId() + ":" + sa.getSpecies() + ":";
      str += "(" + sa.getBounds().getX() + "," + sa.getBounds().getY() + ") [";
      str += sa.getBounds().getW() + " x " + sa.getBounds().getH() + "]";
      System.out.println(str);
    }
    // ListOfReactions
    List<ReactionWrapper> rList = model.getListOfReactionWrapper();
    for(ReactionWrapper r : rList){
    	System.out.println(r.getId() + ": " + r.getReactionType());
    	ConnectScheme cs = r.getConnectScheme();
    	System.out.println("connect policy: " + cs.getConnectPolicy());
    }
    
  }

}
