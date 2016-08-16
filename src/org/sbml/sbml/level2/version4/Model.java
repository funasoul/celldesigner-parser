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
//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.05.30 at 07:47:34 PM JST 
//


package org.sbml.sbml.level2.version4;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import org.sbml._2001.ns.celldesigner.ModelAnnotationType;


// TODO: Auto-generated Javadoc
/**
 * Redefined model.
 * 
 * <p>Java class for Model complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Model">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.sbml.org/sbml/level2/version4}Model">
 *       &lt;redefine>
 *         &lt;complexType name="Model">
 *           &lt;complexContent>
 *             &lt;extension base="{http://www.sbml.org/sbml/level2/version4}SBase">
 *               &lt;sequence>
 *                 &lt;element name="listOfFunctionDefinitions" minOccurs="0">
 *                   &lt;complexType>
 *                     &lt;complexContent>
 *                       &lt;extension base="{http://www.sbml.org/sbml/level2/version4}SBase">
 *                         &lt;sequence>
 *                           &lt;element name="functionDefinition" type="{http://www.sbml.org/sbml/level2/version4}FunctionDefinition" maxOccurs="unbounded"/>
 *                         &lt;/sequence>
 *                       &lt;/extension>
 *                     &lt;/complexContent>
 *                   &lt;/complexType>
 *                 &lt;/element>
 *                 &lt;element name="listOfUnitDefinitions" minOccurs="0">
 *                   &lt;complexType>
 *                     &lt;complexContent>
 *                       &lt;extension base="{http://www.sbml.org/sbml/level2/version4}SBase">
 *                         &lt;sequence>
 *                           &lt;element name="unitDefinition" type="{http://www.sbml.org/sbml/level2/version4}UnitDefinition" maxOccurs="unbounded"/>
 *                         &lt;/sequence>
 *                       &lt;/extension>
 *                     &lt;/complexContent>
 *                   &lt;/complexType>
 *                 &lt;/element>
 *                 &lt;element name="listOfCompartments" minOccurs="0">
 *                   &lt;complexType>
 *                     &lt;complexContent>
 *                       &lt;extension base="{http://www.sbml.org/sbml/level2/version4}SBase">
 *                         &lt;sequence>
 *                           &lt;element name="compartment" type="{http://www.sbml.org/sbml/level2/version4}Compartment" maxOccurs="unbounded"/>
 *                         &lt;/sequence>
 *                       &lt;/extension>
 *                     &lt;/complexContent>
 *                   &lt;/complexType>
 *                 &lt;/element>
 *                 &lt;element name="listOfSpecies" minOccurs="0">
 *                   &lt;complexType>
 *                     &lt;complexContent>
 *                       &lt;extension base="{http://www.sbml.org/sbml/level2/version4}SBase">
 *                         &lt;sequence>
 *                           &lt;element name="species" type="{http://www.sbml.org/sbml/level2/version4}Species" maxOccurs="unbounded"/>
 *                         &lt;/sequence>
 *                       &lt;/extension>
 *                     &lt;/complexContent>
 *                   &lt;/complexType>
 *                 &lt;/element>
 *                 &lt;element name="listOfParameters" minOccurs="0">
 *                   &lt;complexType>
 *                     &lt;complexContent>
 *                       &lt;extension base="{http://www.sbml.org/sbml/level2/version4}SBase">
 *                         &lt;sequence>
 *                           &lt;element name="parameter" type="{http://www.sbml.org/sbml/level2/version4}Parameter" maxOccurs="unbounded"/>
 *                         &lt;/sequence>
 *                       &lt;/extension>
 *                     &lt;/complexContent>
 *                   &lt;/complexType>
 *                 &lt;/element>
 *                 &lt;element name="listOfRules" minOccurs="0">
 *                   &lt;complexType>
 *                     &lt;complexContent>
 *                       &lt;extension base="{http://www.sbml.org/sbml/level2/version4}SBase">
 *                         &lt;choice maxOccurs="unbounded">
 *                           &lt;element name="algebraicRule" type="{http://www.sbml.org/sbml/level2/version4}AlgebraicRule" minOccurs="0"/>
 *                           &lt;element name="assignmentRule" type="{http://www.sbml.org/sbml/level2/version4}AssignmentRule" minOccurs="0"/>
 *                           &lt;element name="rateRule" type="{http://www.sbml.org/sbml/level2/version4}RateRule" minOccurs="0"/>
 *                         &lt;/choice>
 *                       &lt;/extension>
 *                     &lt;/complexContent>
 *                   &lt;/complexType>
 *                 &lt;/element>
 *                 &lt;element name="listOfReactions" minOccurs="0">
 *                   &lt;complexType>
 *                     &lt;complexContent>
 *                       &lt;extension base="{http://www.sbml.org/sbml/level2/version4}SBase">
 *                         &lt;sequence>
 *                           &lt;element name="reaction" type="{http://www.sbml.org/sbml/level2/version4}Reaction" maxOccurs="unbounded"/>
 *                         &lt;/sequence>
 *                       &lt;/extension>
 *                     &lt;/complexContent>
 *                   &lt;/complexType>
 *                 &lt;/element>
 *                 &lt;element name="listOfEvents" minOccurs="0">
 *                   &lt;complexType>
 *                     &lt;complexContent>
 *                       &lt;extension base="{http://www.sbml.org/sbml/level2/version4}SBase">
 *                         &lt;sequence>
 *                           &lt;element name="event" type="{http://www.sbml.org/sbml/level2/version4}Event" maxOccurs="unbounded"/>
 *                         &lt;/sequence>
 *                       &lt;/extension>
 *                     &lt;/complexContent>
 *                   &lt;/complexType>
 *                 &lt;/element>
 *               &lt;/sequence>
 *               &lt;attribute name="id" type="{http://www.sbml.org/sbml/level2/version4}SId" />
 *               &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
 *             &lt;/extension>
 *           &lt;/complexContent>
 *         &lt;/complexType>
 *       &lt;/redefine>
 *       &lt;sequence>
 *         &lt;element name="annotation" type="{http://www.sbml.org/2001/ns/celldesigner}modelAnnotationType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Model", propOrder = {
    "annotation"
})
public class Model
    extends OriginalModel
{

    /** The annotation. */
    protected ModelAnnotationType annotation;

    /**
     * Gets the value of the annotation property.
     * 
     * @return
     *     possible object is
     *     {@link ModelAnnotationType }
     *     
     */
    public ModelAnnotationType getAnnotation() {
        return annotation;
    }

    /**
     * Sets the value of the annotation property.
     * 
     * @param value
     *     allowed object is
     *     {@link ModelAnnotationType }
     *     
     */
    public void setAnnotation(ModelAnnotationType value) {
        this.annotation = value;
    }

}
