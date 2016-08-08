# CellDesigner-Parser
CellDesigner-Parser will parse CellDesigner annotations from SBML. The parser API will automatically generated from schema (*.xsd) files by using JAXB.

## Schema files
- CellDesigner.xsd (base XML schema file which imports SBML L2v4, MathML, CellDesigner-Extensions. This schema file is distributed with [CellDesigner](http://celldesigner.org))
- sbml-level-2-v4-wo-annotation.xsd (XML schema for SBML L2v4 taken from http://sbml.org/Documents/Specifications/All_Releases_and_Versions_of_SBML_Level_2 . We have removed the definition of```<annotation/>``` from the schema to avoid a conflict with the definition in sbmlCellDesignerExtension_v4_2.xsd )
- sbml-mathml.xsd (XML schema for a subset of MatML taken from http://sbml.org/Documents/Specifications/All_Releases_and_Versions_of_SBML_Level_2 .)
- sbmlCellDesignerExtension_v4_2.xsd (XML schema for CellDesigner-Extensions which will be described under SBML's ```<annotation/>```. It uses its own namespace ```http://www.sbml.org/2001/ns/celldesigner```. This schema file is distributed with [CellDesigner](http://celldesigner.org))

## How to generate the parser API
Just execute ```build.sh``` from the commandline, then it will generates the Java code under ```src``` directory.
The generated code is also included in this git repository.
```sh
% sh build.sh
parsing a schema...
compiling a schema...
org/sbml/sbml/level2/version4/AlgebraicRule.java
org/sbml/sbml/level2/version4/AssignmentRule.java
org/sbml/sbml/level2/version4/Compartment.java
org/sbml/sbml/level2/version4/Event.java
...
```

## How to use the parser API
### Requirements
We have tested our API on Java 8 (1.8.0_40). To use this API, it is recommended to use Maven so that you can easily install dependent libraries.

### Download and setup
#### Clone this project
```sh
cd ~/git/
git clone https://github.com/funasoul/celldesigner-parser.git
```
#### Launch Eclipse and import as Maven project.
1. [File] -> [Import] -> [Maven] -> [Existing Maven Project] -> [Next]
2. Navigate to ```~/git/celldesigner-parser``` -> [Next]
3. Select ```/pom.xml``` which you will see in the dialog.
4. Press [Next], then Eclipse will create a new project.
That's it!

### Example code
The sample code is placed under ```src/sample``` directory,  but just for a quick look, here is an example code which will read an SBML file (w/ CellDesigner-Extensions and obtain CellDesigner specific information from its annotation).
```java
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
```

### How to use CellDesigner-Parser

#### Create runnable
    mvn install


#### How to convert file
    java -jar layoutconverter-with-dependencies.jar /path/to/input.xml /path/to/output.xml

## So, what's next?
- Decide what to do with current implementation of [JSBML](https://github.com/sbmlteam/jsbml).
