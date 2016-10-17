/*******************************************************************************
 * Copyright 2016 Kaito Ii, Akira Funahashi
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB)
// Reference Implementation, v2.2.8-b130911.1802
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Any modifications to this file will be lost upon recompilation of the source
// schema.
// Generated on: 2016.05.30 at 07:47:34 PM JST
//
package org.sbml._2001.ns.celldesigner;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

// TODO: Auto-generated Javadoc
/**
 * List of RNAs.
 * <p>
 * Java class for listOfRNAs complex type.
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="listOfRNAs">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="RNA" type="{http://www.sbml.org/2001/ns/celldesigner}RNA" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "listOfRNAs", propOrder = {"rna"})
public class ListOfRNAs {

  /** The rna. */
  @XmlElement(name = "RNA")
  protected List<RNA> rna;


  /**
   * Gets the value of the rna property.
   * <p>
   * This accessor method returns a reference to the live list, not a snapshot.
   * Therefore any modification you make to the returned list will be present
   * inside the JAXB object. This is why there is not a <CODE>set</CODE> method
   * for the rna property.
   * <p>
   * For example, to add a new item, do as follows:
   * 
   * <pre>
   * getRNA().add(newItem);
   * </pre>
   * <p>
   * Objects of the following type(s) are allowed in the list {@link RNA }
   *
   * @return the rna
   */
  public List<RNA> getRNA() {
    if (rna == null) {
      rna = new ArrayList<RNA>();
    }
    return this.rna;
  }
}
