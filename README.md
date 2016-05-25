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
We have tested our API on Java 8 (1.8.0_40).

### Example code
The sample code will be committed soon, but just for a quick answer, here is an example code which will read an SBML file (w/ CellDesigner-Extensions and obtain CellDesigner specific information from its annotation).
```java
package sample;

import java.io.File;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.JAXBIntrospector;
import javax.xml.bind.Unmarshaller;
import org.sbml.sbml.level2.version4.Sbml;
import org.sbml.sbml.level2.version4.Species;

/**
 * @author Kaito Ii
 * @author Akira Funahashi
 *
 * Date Created: May 20, 2016
 * Date Modified: May 24, 2016
 */
public class APItest {
  public static void main(String[] args) {
    JAXBContext context;
    Sbml sbml = null;
    try {
      context = JAXBContext.newInstance(Sbml.class);
      Unmarshaller unmarshaller = context.createUnmarshaller();
      Object schemaObject = JAXBIntrospector.getValue(unmarshaller.unmarshal(new File("sample.xml")));
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
```

## So, what's next?
- Based on this generated parser API, we will add several convenient APIs which will directly obtain CellDesigner-Extensions from SBML objects. For example:
  * Create a Factory Class which will accept an SBML file as an input and returns an instance of ModelWrapper.
  * Create a wrapper class for SpeciesAlias
  * Add a reference to its parent Species instance for SpeciesAlias class.
  * Add a reference to its parent Model instance for each SBase class.
- Decide what to do with current implementation of [JSBML](https://github.com/sbmlteam/jsbml).
