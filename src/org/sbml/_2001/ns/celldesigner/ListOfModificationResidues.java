//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.05.24 at 05:21:09 PM JST 
//


package org.sbml._2001.ns.celldesigner;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * List of modification residues.
 * 
 * <p>Java class for listOfModificationResidues complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="listOfModificationResidues">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="modificationResidue" type="{http://www.sbml.org/2001/ns/celldesigner}modificationResidue" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "listOfModificationResidues", propOrder = {
    "modificationResidue"
})
public class ListOfModificationResidues {

    @XmlElement(required = true)
    protected List<ModificationResidue> modificationResidue;

    /**
     * Gets the value of the modificationResidue property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the modificationResidue property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getModificationResidue().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ModificationResidue }
     * 
     * 
     */
    public List<ModificationResidue> getModificationResidue() {
        if (modificationResidue == null) {
            modificationResidue = new ArrayList<ModificationResidue>();
        }
        return this.modificationResidue;
    }

}
