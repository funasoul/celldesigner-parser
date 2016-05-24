//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.05.24 at 05:21:09 PM JST 
//


package org.w3._1998.math.mathml;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Piecewise complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Piecewise">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.w3.org/1998/Math/MathML}MathBase">
 *       &lt;sequence>
 *         &lt;element name="piece" type="{http://www.w3.org/1998/Math/MathML}Piece" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="otherwise" type="{http://www.w3.org/1998/Math/MathML}Otherwise" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Piecewise", propOrder = {
    "piece",
    "otherwise"
})
public class Piecewise
    extends MathBase
{

    protected List<Piece> piece;
    protected Otherwise otherwise;

    /**
     * Gets the value of the piece property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the piece property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPiece().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Piece }
     * 
     * 
     */
    public List<Piece> getPiece() {
        if (piece == null) {
            piece = new ArrayList<Piece>();
        }
        return this.piece;
    }

    /**
     * Gets the value of the otherwise property.
     * 
     * @return
     *     possible object is
     *     {@link Otherwise }
     *     
     */
    public Otherwise getOtherwise() {
        return otherwise;
    }

    /**
     * Sets the value of the otherwise property.
     * 
     * @param value
     *     allowed object is
     *     {@link Otherwise }
     *     
     */
    public void setOtherwise(Otherwise value) {
        this.otherwise = value;
    }

}
