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

import org.sbml._2001.ns.celldesigner.SpeciesReferenceAnnotationType;


// TODO: Auto-generated Javadoc
/**
 * Redefined speciesReference.
 * 
 * <p>Java class for SpeciesReference complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SpeciesReference">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.sbml.org/sbml/level2/version4}SpeciesReference">
 *       &lt;redefine>
 *         &lt;complexType name="SpeciesReference">
 *           &lt;complexContent>
 *             &lt;extension base="{http://www.sbml.org/sbml/level2/version4}SimpleSpeciesReference">
 *               &lt;sequence>
 *                 &lt;element name="stoichiometryMath" type="{http://www.sbml.org/sbml/level2/version4}StoichiometryMath" minOccurs="0"/>
 *               &lt;/sequence>
 *               &lt;attribute name="stoichiometry" type="{http://www.w3.org/2001/XMLSchema}double" default="1" />
 *             &lt;/extension>
 *           &lt;/complexContent>
 *         &lt;/complexType>
 *       &lt;/redefine>
 *       &lt;sequence>
 *         &lt;element name="annotation" type="{http://www.sbml.org/2001/ns/celldesigner}speciesReferenceAnnotationType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SpeciesReference", propOrder = {
    "annotation"
})
public class SpeciesReference
    extends OriginalSpeciesReference
{

    /** The annotation. */
    protected SpeciesReferenceAnnotationType annotation;

    /**
     * Gets the value of the annotation property.
     * 
     * @return
     *     possible object is
     *     {@link SpeciesReferenceAnnotationType }
     *     
     */
    public SpeciesReferenceAnnotationType getAnnotation() {
        return annotation;
    }

    /**
     * Sets the value of the annotation property.
     * 
     * @param value
     *     allowed object is
     *     {@link SpeciesReferenceAnnotationType }
     *     
     */
    public void setAnnotation(SpeciesReferenceAnnotationType value) {
        this.annotation = value;
    }

}
