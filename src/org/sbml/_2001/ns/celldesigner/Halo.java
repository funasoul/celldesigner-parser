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

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

// TODO: Auto-generated Javadoc
/**
 * Halo around a block(protein).
 * <p>
 * Java class for halo complex type.
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="halo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="width" use="required">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}short">
 *             &lt;minInclusive value="0"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="height" use="required">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}short">
 *             &lt;minInclusive value="0"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="x" use="required">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}short">
 *             &lt;minInclusive value="0"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="y" use="required">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}short">
 *             &lt;minInclusive value="0"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "halo")
public class Halo {

  /** The width. */
  @XmlAttribute(name = "width", required = true)
  protected short width;
  /** The height. */
  @XmlAttribute(name = "height", required = true)
  protected short height;
  /** The x. */
  @XmlAttribute(name = "x", required = true)
  protected short x;
  /** The y. */
  @XmlAttribute(name = "y", required = true)
  protected short y;


  /**
   * Gets the value of the width property.
   *
   * @return the width
   */
  public short getWidth() {
    return width;
  }


  /**
   * Sets the value of the width property.
   *
   * @param value
   *        the new width
   */
  public void setWidth(short value) {
    this.width = value;
  }


  /**
   * Gets the value of the height property.
   *
   * @return the height
   */
  public short getHeight() {
    return height;
  }


  /**
   * Sets the value of the height property.
   *
   * @param value
   *        the new height
   */
  public void setHeight(short value) {
    this.height = value;
  }


  /**
   * Gets the value of the x property.
   *
   * @return the x
   */
  public short getX() {
    return x;
  }


  /**
   * Sets the value of the x property.
   *
   * @param value
   *        the new x
   */
  public void setX(short value) {
    this.x = value;
  }


  /**
   * Gets the value of the y property.
   *
   * @return the y
   */
  public short getY() {
    return y;
  }


  /**
   * Sets the value of the y property.
   *
   * @param value
   *        the new y
   */
  public void setY(short value) {
    this.y = value;
  }
}
