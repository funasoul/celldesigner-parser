//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.05.24 at 05:21:09 PM JST 
//


package org.sbml.sbml.level2.version4;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Event complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Event">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.sbml.org/sbml/level2/version4}SBase">
 *       &lt;sequence>
 *         &lt;element name="trigger" type="{http://www.sbml.org/sbml/level2/version4}MathField"/>
 *         &lt;element name="delay" type="{http://www.sbml.org/sbml/level2/version4}MathField" minOccurs="0"/>
 *         &lt;element name="listOfEventAssignments" type="{http://www.sbml.org/sbml/level2/version4}ListOfEventAssignments"/>
 *       &lt;/sequence>
 *       &lt;attribute name="id" type="{http://www.sbml.org/sbml/level2/version4}SId" />
 *       &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="timeUnits" type="{http://www.sbml.org/sbml/level2/version4}SId" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Event", propOrder = {
    "trigger",
    "delay",
    "listOfEventAssignments"
})
public class Event
    extends SBase
{

    @XmlElement(required = true)
    protected MathField trigger;
    protected MathField delay;
    @XmlElement(required = true)
    protected ListOfEventAssignments listOfEventAssignments;
    @XmlAttribute(name = "id")
    protected String id;
    @XmlAttribute(name = "name")
    protected String name;
    @XmlAttribute(name = "timeUnits")
    protected String timeUnits;

    /**
     * Gets the value of the trigger property.
     * 
     * @return
     *     possible object is
     *     {@link MathField }
     *     
     */
    public MathField getTrigger() {
        return trigger;
    }

    /**
     * Sets the value of the trigger property.
     * 
     * @param value
     *     allowed object is
     *     {@link MathField }
     *     
     */
    public void setTrigger(MathField value) {
        this.trigger = value;
    }

    /**
     * Gets the value of the delay property.
     * 
     * @return
     *     possible object is
     *     {@link MathField }
     *     
     */
    public MathField getDelay() {
        return delay;
    }

    /**
     * Sets the value of the delay property.
     * 
     * @param value
     *     allowed object is
     *     {@link MathField }
     *     
     */
    public void setDelay(MathField value) {
        this.delay = value;
    }

    /**
     * Gets the value of the listOfEventAssignments property.
     * 
     * @return
     *     possible object is
     *     {@link ListOfEventAssignments }
     *     
     */
    public ListOfEventAssignments getListOfEventAssignments() {
        return listOfEventAssignments;
    }

    /**
     * Sets the value of the listOfEventAssignments property.
     * 
     * @param value
     *     allowed object is
     *     {@link ListOfEventAssignments }
     *     
     */
    public void setListOfEventAssignments(ListOfEventAssignments value) {
        this.listOfEventAssignments = value;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the timeUnits property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTimeUnits() {
        return timeUnits;
    }

    /**
     * Sets the value of the timeUnits property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTimeUnits(String value) {
        this.timeUnits = value;
    }

}
