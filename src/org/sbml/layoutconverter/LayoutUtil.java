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
/*
 * 
 */
package org.sbml.layoutconverter;

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.sbml.jsbml.ext.layout.CompartmentGlyph;
import org.sbml.jsbml.ext.layout.CubicBezier;
import org.sbml.jsbml.ext.layout.CurveSegment;
import org.sbml.jsbml.ext.layout.Dimensions;
import org.sbml.jsbml.ext.layout.GraphicalObject;
import org.sbml.jsbml.ext.layout.LineSegment;
import org.sbml.jsbml.ext.layout.Point;
import org.sbml.jsbml.ext.layout.SpeciesGlyph;
import org.sbml.jsbml.ext.layout.SpeciesReferenceGlyph;

// TODO: Auto-generated Javadoc
/**
 * The Class LayoutUtil.
 *
 * @author Kaito Ii
 * 
 * Date Created: Jun 7, 2016
 */

public class LayoutUtil {

	/**
	 * Gets the center of glyph.
	 *
	 * @param go the go
	 * @return Point
	 * TODO
	 */
	public static Point getCenterPositionOfGlyph(GraphicalObject go){
		Point point;
		if(go.isSetBoundingBox()){
			Dimensions dimension = go.getBoundingBox().getDimensions();
			point = go.getBoundingBox().getPosition().clone();
			point.setX(point.getX() + dimension.getWidth() / 2);
			point.setY(point.getY() + dimension.getHeight() / 2);
		} else {
			Dimensions dimension = ((SpeciesReferenceGlyph)go).getSpeciesGlyphInstance().getBoundingBox().getDimensions();
			point = ((SpeciesReferenceGlyph)go).getSpeciesGlyphInstance().getBoundingBox().getPosition().clone();
			point.setX(point.getX() + dimension.getWidth() / 2);
			point.setY(point.getY() + dimension.getHeight() / 2);
		}
		
		return point;
	}
	
	/**
	 * Gets the edits the point position.
	 *
	 * @param origin the origin
	 * @param axis1 the axis 1
	 * @param axis2 the axis 2
	 * @param editPoint the edit point
	 * @return Point
	 * TODO
	 */
	public static Point getEditPointPosition(SpeciesGlyph origin, SpeciesGlyph axis1, SpeciesGlyph axis2, Point2D.Double editPoint){
		Point pOrigin = getCenterPositionOfGlyph(origin);
		Point pA1 = getCenterPositionOfGlyph(axis1);
		Point pA2  = getCenterPositionOfGlyph(axis2);
		Point point = getEditPointPosition(pOrigin, pA1, pA2, editPoint);	
		
		return point;
	}
	
	/**
	 * Gets the edits the point position.
	 *
	 * @param origin the origin
	 * @param axis1 the axis 1
	 * @param axis2 the axis 2
	 * @param editPoint the edit point
	 * @return Point
	 * TODO
	 */
	public static Point getEditPointPosition(Point origin, Point axis1, Point axis2, Point2D.Double editPoint){
		Point point = new Point(SBMLUtil.DEFAULT_SBML_LEVEL, SBMLUtil.DEFAULT_SBML_VERSION);

		double x = (axis1.getX() - origin.getX()) * editPoint.getX();
		x += (axis2.getX() - origin.getX()) * editPoint.getY();
		double y = (axis1.getY() - origin.getY()) * editPoint.getX();
		y += (axis2.getY() - origin.getY()) * editPoint.getY();

		point.setX(origin.getX() + x);
		point.setY(origin.getY() + y);		
		point.setZ(0);
		return point;
	}

	/**
	 * Gets the length.
	 *
	 * @param p1 the p 1
	 * @param p2 the p 2
	 * @return the length
	 */
	public static double getLength(Point p1, Point p2){
		return Math.hypot(p1.getX() - p2.getX(), p1.getY() - p2.getY());
	}
	
	/**
	 * Gets the length.
	 *
	 * @param p1 the p 1
	 * @param p2 the p 2
	 * @param proportion the proportion
	 * @return double
	 * TODO
	 */
	public static double getLength(Point p1, Point p2, double proportion){
		return getLength(p1, p2) * proportion;
	}
	
	/**
	 * Dot product.
	 *
	 * @param startPoint the start point
	 * @param endPoint1 the end point 1
	 * @param endPoint2 the end point 2
	 * @return the double
	 */
	public static double dotProduct(Point startPoint, Point endPoint1, Point endPoint2){
		Point vec1 = new Point(SBMLUtil.DEFAULT_SBML_LEVEL, SBMLUtil.DEFAULT_SBML_VERSION);
		vec1.setX(endPoint1.getX() - startPoint.getX());
		vec1.setY(endPoint1.getY() - startPoint.getY());

		Point vec2 = new Point(SBMLUtil.DEFAULT_SBML_LEVEL, SBMLUtil.DEFAULT_SBML_VERSION);
		vec2.setX(endPoint2.getX() - startPoint.getX());
		vec2.setY(endPoint2.getY() - startPoint.getY());

		return vec1.getX() * vec2.getX() + vec1.getY() * vec2.getY();
	}
	
	/**
	 * Creates the center point.
	 *
	 * @param point1 the point 1
	 * @param point2 the point 2
	 * @return Point
	 * TODO
	 */
	public static Point createCenterPoint(Point point1, Point point2){
		return new Point((point1.getX() + point2.getX())/2, (point1.getY() + point2.getY())/2, 0);
	}
	
	/**
	 * returns the adjusted coordinates of a species according to the direction .
	 *
	 * @param x the x
	 * @param y the y
	 * @param width the width
	 * @param height the height
	 * @param direction the direction
	 * @return Point
	 * TODO
	 */
	public static Point createAdjustedPoint(double x, double y, double width, double height, String direction){
		Point point = new Point(SBMLUtil.DEFAULT_SBML_LEVEL, SBMLUtil.DEFAULT_SBML_VERSION);
		point.setZ(0);
		
		if(direction.equals("NW")){
			point.setX(x);
			point.setY(y);
			
		} else if(direction.equals("NNW")){
			point.setX(x + width / 4);
			point.setY(y);
			
		} else if(direction.equals("N")){
			point.setX(x + width / 2);
			point.setY(y);
		} else if(direction.equals("NNE")){
			point.setX(x + width * 3 / 4);
			point.setY(y);
			
		} else if(direction.equals("NE")){
			point.setX(x + width);
			point.setY(y);
			
		} else if(direction.equals("ENE")){
			point.setX(x + width);
			point.setY(y + height / 4);
			
		} else if(direction.equals("E")){
			point.setX(x + width);
			point.setY(y + height / 2);
			
		} else if(direction.equals("ESE")){
			point.setX(x + width);
			point.setY(y + height * 3 / 4);
			
		} else if(direction.equals("SE")){
			point.setX(x + width);
			point.setY(y + height);
			
		} else if(direction.equals("SSE")){
			point.setX(x + width * 3 / 4);
			point.setY(y + height);
			
		} else if(direction.equals("S")){
			point.setX(x + width / 2);
			point.setY(y + height);
			
		} else if(direction.equals("SSW")){
			point.setX(x + width / 4);
			point.setY(y + height);
			
		} else if(direction.equals("SW")){
			point.setX(x);
			point.setY(y + height);
			
		} else if(direction.equals("WSW")){
			point.setX(x);
			point.setY(y + height * 3 / 4);
			
		} else if(direction.equals("W")){
			point.setX(x);
			point.setY(y + height / 2);
			
		} else if(direction.equals("WNW")){
			point.setX(x);
			point.setY(y + height / 4);
			
		} else { //INACTIVE
			point.setX(x + width / 2);
			point.setY(y + height / 2);
		}
		
		return point;
	}
	
	/**
	 * Creates the adjusted point.
	 *
	 * @param go the go
	 * @param direction the direction
	 * @return Point
	 * TODO
	 */
	public static Point createAdjustedPoint(GraphicalObject go, String direction){
		Point p = go.getBoundingBox().getPosition();
		Dimensions d = go.getBoundingBox().getDimensions();
		
		return createAdjustedPoint(p.getX(), p.getY(), d.getWidth(), d.getHeight(), direction);
	}
	
	/**
	 * Creates the list of bezier.
	 *
	 * @param startPoint the start point
	 * @param endPoint the end point
	 * @param basePoint the base point
	 * @return List<LineSegment>
	 * 
	 */
	public static List<LineSegment> createListOfBezier(Point startPoint, Point endPoint, Point basePoint){
		List<LineSegment> lsList = new ArrayList<LineSegment>();
		CubicBezier bezier = new CubicBezier(SBMLUtil.DEFAULT_SBML_LEVEL, SBMLUtil.DEFAULT_SBML_VERSION);
		bezier.setStart(startPoint.clone());
		bezier.setEnd(endPoint.clone());
		
		bezier.setBasePoint1(calculateBasepointQuadraticToCubic(startPoint, basePoint));
		bezier.setBasePoint2(calculateBasepointQuadraticToCubic(endPoint, basePoint));
		lsList.add(bezier);
		
		return lsList;
	}
	
	/**
	 * Calculate basepoint quadratic to cubic.
	 * CP0 = QP0
	 * CP3 = QP2
	 * CP1 = QP0 + 2/3 *(QP1-QP0)
	 * CP2 = QP2 + 2/3 *(QP1-QP2)
	 *
	 * @param startPoint the start point
	 * @param quadBasePoint the quad base point
	 * @return the point
	 */
	public static Point calculateBasepointQuadraticToCubic(Point startPoint, Point quadBasePoint){
		Point point = new Point(SBMLUtil.DEFAULT_SBML_LEVEL, SBMLUtil.DEFAULT_SBML_VERSION);
		
		point.setX(startPoint.getX() + 2d/3d * (quadBasePoint.getX() - startPoint.getX()));
		point.setY(startPoint.getY() + 2d/3d * (quadBasePoint.getY() - startPoint.getY()));
		
		return point;
	}
	
	/**
	 * Creates the list of line segment.
	 *
	 * @param startPoint the start point
	 * @param endPoint1 the end point 1
	 * @param endPoint2 the end point 2
	 * @param speciesPoint1 the species point 1
	 * @param speciesPoint2 the species point 2
	 * @param speciesPoint3 the species point 3
	 * @param editPointList the edit point list
	 * @param num0 the num 0
	 * @param num1 the num 1
	 * @param num2 the num 2
	 * @param type the type
	 * @param tshapeIndex the tshape index
	 * @return List<LineSegment>
	 * TODO
	 */
	public static List<LineSegment> createListOfLineSegment(Point startPoint, Point endPoint1, Point endPoint2,  Point speciesPoint1, Point speciesPoint2, Point speciesPoint3, List<Point2D.Double> editPointList, int num0, int num1, int num2, String type, int tshapeIndex){
		List<LineSegment> lineList = new ArrayList<LineSegment>();
		Point editPoint = getEditPointPosition(speciesPoint1, speciesPoint2, speciesPoint3, editPointList.get(editPointList.size() - 1));
		
		if(num0 > 0){
			List<Point2D.Double> subList = editPointList.subList(0, num0);
			Collections.reverse(subList);	//since editpoint lists the coordinates starting from the editpoint
			List<LineSegment> subLineList = createListOfLineSegment(startPoint, editPoint, editPoint, startPoint, subList);
			lineList.addAll(lineList.size(), subLineList);
		} else {
			LineSegment ls = new LineSegment(SBMLUtil.DEFAULT_SBML_LEVEL, SBMLUtil.DEFAULT_SBML_VERSION);
			ls.setStart(startPoint);
			ls.setEnd(editPoint);
			lineList.add(ls);
		}
		
		if(num1 > 0){
			List<Point2D.Double> subList = editPointList.subList(num0, num0 + num1);
			List<LineSegment> subLineList;
			if(type.equals("DISSOCIATION") || type.equals("TRUNCATION")){
				subLineList = createListOfLineSegment(editPoint, endPoint1, editPoint, endPoint1, subList);
			} else {
				Collections.reverse(subList);
				subLineList = createListOfLineSegment(endPoint1, editPoint, editPoint, endPoint1, subList);
			}
			lineList.addAll(lineList.size(), subLineList);
		} else {
			LineSegment ls = new LineSegment(SBMLUtil.DEFAULT_SBML_LEVEL, SBMLUtil.DEFAULT_SBML_VERSION);
			if(type.equals("DISSOCIATION") || type.equals("TRUNCATION")){
				ls.setStart(editPoint.clone());
				ls.setEnd(endPoint1);
			} else {
				ls.setStart(endPoint1);
				ls.setEnd(editPoint.clone());
			}
			lineList.add(ls);
		}
		
		if(num2 > 0){
			List<Point2D.Double> subList = editPointList.subList(num0 + num1, num0 + num1 + num2);
			List<LineSegment> subLineList = createListOfLineSegment(editPoint, endPoint2, editPoint, endPoint2, subList);
			lineList.addAll(lineList.size(), subLineList);
		} else {
			LineSegment ls = new LineSegment(SBMLUtil.DEFAULT_SBML_LEVEL, SBMLUtil.DEFAULT_SBML_VERSION);
			ls.setStart(editPoint.clone());
			ls.setEnd(endPoint2);
			lineList.add(ls);
		}
		
		return lineList;
	}
	
	/**
	 * create line segments with one to one reaction.
	 *
	 * @param startPoint the start point
	 * @param endPoint the end point
	 * @param reactantPoint the reactant point
	 * @param productPoint the product point
	 * @param editPointList the edit point list
	 * @param rectangleIndex the rectangle index
	 * @return List<LineSegment>
	 * TODO
	 */
	public static List<LineSegment> createListOfLineSegment(Point startPoint, Point endPoint, Point reactantPoint, Point productPoint, List<Point2D.Double> editPointList, int rectangleIndex){
		List<LineSegment> lineList = createListOfLineSegment(startPoint, endPoint, reactantPoint, productPoint, editPointList);
		
		LineSegment l1 = lineList.get(rectangleIndex);
		LineSegment l2 = new LineSegment(SBMLUtil.DEFAULT_SBML_LEVEL, SBMLUtil.DEFAULT_SBML_VERSION);
		Point p =  createCenterPoint(l1.getStart(), l1.getEnd());
		l2.setEnd(l1.getEnd().clone());
		l1.setEnd(p.clone());
		l2.setStart(p.clone());
		lineList.add(rectangleIndex + 1, l2);
	
		return lineList;
	}
	
	/**
	 * Creates the list of line segment.
	 * assumes vector1 is the start point and vector2 is the end point
	 * @param vector1 the vector 1
	 * @param vector2 the vector 2
	 * @param editPointList the edit point list
	 * @return the list
	 */
	public static List<LineSegment> createListOfLineSegment(Point vector1, Point vector2, List<Point2D.Double> editPointList){
		return createListOfLineSegment(vector1, vector2, vector1, vector2, editPointList);
	}
	
	/**
	 * create line segments with 2 vectors.
	 *
	 * @param startPoint the start point
	 * @param endPoint the end point
	 * @param vector1 the vector 1
	 * @param vector2 the vector 2
	 * @param editPointList the edit point list
	 * @return List<LineSegment>
	 * TODO
	 */
	public static List<LineSegment> createListOfLineSegment(Point startPoint, Point endPoint, Point vector1, Point vector2, List<Point2D.Double> editPointList){
		List<LineSegment> lineList = new ArrayList<LineSegment>();
		Point perpPoint = createPerpendicularPoint(vector1, vector2);
		LineSegment line = new LineSegment(SBMLUtil.DEFAULT_SBML_LEVEL, SBMLUtil.DEFAULT_SBML_VERSION);
		line.setStart(startPoint.clone());
		for(int i = 0; i < editPointList.size(); i++){
			Point point = getEditPointPosition(vector1, vector2, perpPoint, editPointList.get(i));
			line.setEnd(point.clone());
			lineList.add(line);
			line = new LineSegment(SBMLUtil.DEFAULT_SBML_LEVEL, SBMLUtil.DEFAULT_SBML_VERSION);
			line.setStart(point.clone());
		}
			
		line.setEnd(endPoint.clone());
		lineList.add(line);
		
		return lineList;
	}
	
	/**
	 * Creates the perpendicular point.
	 *
	 * @param start the start
	 * @param end the end
	 * @return Point
	 * TODO
	 */
	public static Point createPerpendicularPoint(Point start, Point end){
		Point point = start.clone();
		point.setX(start.getX() + (end.getY() - start.getY()) * -1);
		point.setY(start.getY() + end.getX() - start.getX());
		
		return point;
	}
    
	/**
	 * Creates the edit points as list.
	 *
	 * @param editPoints the edit points
	 * @return List<Point>
	 * TODO
	 */
    public static List<Point2D.Double> createEditPointsAsList(List<String> editPoints){
 	   List<Point2D.Double> list = new ArrayList<Point2D.Double>();

 	   if(editPoints == null)
 		  return list;
 	   
 	   for(String s : editPoints){
 		   String[] points = s.split(",",0);
 		   Point2D.Double point = new Point2D.Double();
 		   point.x = Double.valueOf(points[0]);
 		   point.y = Double.valueOf(points[1]);
 		   list.add(point);
 	   }
 	    	   
 	   return list;
    }

    /**
     * Edits the point list to string.
     *
     * @param editPointList the edit point list
     * @return the string
     */
    public static List<String> editPointListToStringList(List<Point2D.Double> editPointList){
    	List<String> strList = new ArrayList<String>();
    	for(Point2D.Double point : editPointList){
    		String s = point.x + "," + point.y;
    		strList.add(s);
    	}
    	return strList;
    }
    
	/**
	 * Creates the center point.
	 *
	 * @param x the x
	 * @param y the y
	 * @param width the width
	 * @param height the height
	 * @return Point
	 * TODO
	 */
	public static Point createCenterPoint(double x, double y, double width, double height) {
		Point point = new Point();
		point.setX(x + width / 2);
		point.setY(y + height / 2);
		
		return point;
	}
		
	/**
	 * Convert edit points to proportion.
	 *
	 * @param startPoint the start point
	 * @param vector1 the vector 1
	 * @param vector2 the vector 2
	 * @param editPoints the edit points
	 * @return the list
	 */
	public static List<Point2D.Double> convertEditPointsToProportion(Point startPoint, Point vector1, Point vector2, List<Point2D.Double> editPoints){
		List<Point2D.Double> editPointList = new ArrayList<Point2D.Double>();
		
		for(Point2D.Double point : editPoints){
			Point2D.Double editPoint = new Point2D.Double();
			
			Point origin = new Point(SBMLUtil.DEFAULT_SBML_LEVEL, SBMLUtil.DEFAULT_SBML_VERSION);
			origin.setX(0);
			origin.setY(0);
			
			Point vec1 = new Point(SBMLUtil.DEFAULT_SBML_LEVEL, SBMLUtil.DEFAULT_SBML_VERSION);
			vec1.setX(vector1.getX() - startPoint.getX());
			vec1.setY(vector1.getY() - startPoint.getY());
			
			Point vec2 = new Point(SBMLUtil.DEFAULT_SBML_LEVEL, SBMLUtil.DEFAULT_SBML_VERSION);
			vec2.setX(vector2.getX() - startPoint.getX());
			vec2.setY(vector2.getY() - startPoint.getY());
			
			Point edit = new Point(SBMLUtil.DEFAULT_SBML_LEVEL, SBMLUtil.DEFAULT_SBML_VERSION);
			edit.setX(point.x - startPoint.getX());
			edit.setY(point.y - startPoint.getY());

			double slope1 = vec1.getY() / vec1.getX();
			double b = edit.getY()- slope1 * edit.getX();
			
			double slope2 = vec2.getY() / vec2.getX();
			
			Point intersect = new Point(SBMLUtil.DEFAULT_SBML_LEVEL, SBMLUtil.DEFAULT_SBML_VERSION);
			intersect.setX(b / (slope2 - slope1));
			intersect.setY(slope2 * intersect.getX());			
	
			editPoint.x = getLength(edit, intersect) / getLength(origin, vec1);
			editPoint.y = getLength(origin, intersect) / getLength(origin, vec2);
			
			editPointList.add(editPoint);
		}
			
		return editPointList;
	}
	
	/**
	 * Convert edit points to proportion.
	 *
	 * @param startPoint the start point
	 * @param endPoint the end point
	 * @param editPoints the edit points
	 * @return the list
	 */
	public static List<Point2D.Double> convertEditPointsToProportion(Point startPoint, Point endPoint, List<Point2D.Double> editPoints){
		List<Point2D.Double> editPointList = new ArrayList<Point2D.Double>();
		Point perpPoint = createPerpendicularPoint(startPoint, endPoint);
		
		for(Point2D.Double point : editPoints){
			Point2D.Double editPoint = new Point2D.Double();
			double length1 = getOrthogonalProjectionLengthFromEditPoint(startPoint, endPoint, point);
			editPoint.x = length1 / getLength(startPoint, endPoint);
			
			double length2 = getOrthogonalProjectionLengthFromEditPoint(startPoint, perpPoint, point);
			editPoint.y = length2 / getLength(startPoint, perpPoint);
			
			editPointList.add(editPoint);
		}
			return editPointList;
	}
	
	/**
	 * Gets the orthogonal projection length from edit point.
	 *
	 * @param startPoint the start point
	 * @param endPoint the end point
	 * @param editPoint the edit point
	 * @return the orthogonal projection length from edit point
	 */
	public static double getOrthogonalProjectionLengthFromEditPoint(Point startPoint, Point endPoint, Point2D.Double editPoint){
		Point edit = new Point(SBMLUtil.DEFAULT_SBML_LEVEL, SBMLUtil.DEFAULT_SBML_VERSION);
		edit.setX(editPoint.getX());
		edit.setY(editPoint.getY());
		
		return getOrthogonalProjectionLengthFromEditPoint(startPoint, endPoint, edit);
	}
	
	/**
	 * Gets the orthogonal projection length from edit point.
	 *
	 * @param startPoint the start point
	 * @param endPoint the end point
	 * @param editPoint the edit point
	 * @return the orthogonal projection length from edit point
	 */
	public static double getOrthogonalProjectionLengthFromEditPoint(Point startPoint, Point endPoint, Point editPoint){
		double radian = angleOf2Vector(startPoint, endPoint, editPoint);
		return getOrthogonalProjection(startPoint, editPoint, radian);
	}
	
	/**
	 * Angle of 2 vector.
	 *
	 * @param startPoint the start point
	 * @param endPoint1 the end point 1
	 * @param endPoint2 the end point 2
	 * @return the double
	 */
	public static double angleOf2Vector(Point startPoint, Point endPoint1, Point endPoint2){
		double length1 = getLength(startPoint, endPoint1);
		double length2 = getLength(startPoint, endPoint2);	
		double cos = dotProduct(startPoint, endPoint1, endPoint2) / (length1 * length2);
		
		return Math.acos(cos);
	}
	
	/**
	 * Gets the orthogonal projection.
	 *
	 * @param startPoint the start point
	 * @param endPoint the end point
	 * @param radian the radian
	 * @return the orthogonal projection
	 */
	public static double getOrthogonalProjection(Point startPoint, Point endPoint, double radian){
		return getLength(startPoint, endPoint) * Math.cos(radian);
	}
	
	/**
	 * Creates the center point.
	 *
	 * @param go the go
	 * @return the point
	 */
	public static Point createCenterPoint(GraphicalObject go) {
		Point point = new Point();
		Point p = go.getBoundingBox().getPosition();
		Dimensions d = go.getBoundingBox().getDimensions();
		
		point.setX(p.getX() + d.getWidth() / 2);
		point.setY(p.getY() + d.getHeight() / 2);
		
		return point;
	}
	
	/**
	 * Creates the link point.
	 *
	 * @param nearestPoint the nearest point
	 * @param centerPoint the center point
	 * @return the point
	 */
	public static Point createLinkPoint(Point nearestPoint, Point centerPoint){
		Point p = new Point(SBMLUtil.DEFAULT_SBML_LEVEL, SBMLUtil.DEFAULT_SBML_VERSION);
		double proportion = 3/4d; 
		
		p.setX(nearestPoint.getX() + (centerPoint.getX() - nearestPoint.getX()) * proportion);
		p.setY(nearestPoint.getY() + (centerPoint.getY() - nearestPoint.getY()) * proportion);

		return p;
	}
	
	/**
	 * Gets the position to compartment.
	 *
	 * @param sg the sg
	 * @param cg the cg
	 * @return the position to compartment
	 */
	//TODO determine the species position depending on how each glyphs are layed out
	public static String getPositionToCompartment(SpeciesGlyph sg, CompartmentGlyph cg){
		return "inside";
	}
	
    /**
     * Convert line type to CD string.
     *
     * @param type the type
     * @return the string
     */
    public static String convertLineTypeToCDString(String type){
    	if(type.equals("CUBIC_BEZIER")){
 		   return "Curve";
 	   } else if(type.equals("LINE_SEGMENT")){
 		   return "Straight";
 	   } else {
 		   return "";
 	   }	   
    }

    /**
     * Adjust overlapping end point.
     *
     * @param cs the cs
     * @param go the go
     * @return the point
     */
    public static Point adjustOverlappingEndPoint(CurveSegment cs, GraphicalObject go){
    	Line2D.Double reactionLine = new Line2D.Double(cs.getStart().getX(), cs.getStart().getY(), cs.getEnd().getX(), cs.getEnd().getY());
    	Rectangle2D.Double glyph = new Rectangle2D.Double(go.getBoundingBox().getPosition().getX(), go.getBoundingBox().getPosition().getY(), go.getBoundingBox().getDimensions().getWidth(), go.getBoundingBox().getDimensions().getHeight());
    	
    	if(reactionLine.intersectsLine(glyph.getMinX(), glyph.getMinY(), glyph.getMaxX(), glyph.getMinY())){ //up
    		return getIntersectingPoint(cs.getStart(), cs.getEnd(), new Point(glyph.getMinX(), glyph.getMinY()), new Point(glyph.getMaxX(), glyph.getMinY()));
    	
    	} else if(reactionLine.intersectsLine(glyph.getMaxX(), glyph.getMinY(), glyph.getMaxX(), glyph.getMaxY())){ //right
    		return getIntersectingPoint(cs.getStart(), cs.getEnd(), new Point(glyph.getMaxX(), glyph.getMinY()), new Point( glyph.getMaxX(), glyph.getMaxY()));
    	
    	} else if(reactionLine.intersectsLine(glyph.getMinX(), glyph.getMaxY(), glyph.getMaxX(), glyph.getMaxY())){ //down
    		return getIntersectingPoint(cs.getStart(), cs.getEnd(), new Point(glyph.getMinX(), glyph.getMaxY()), new Point(glyph.getMaxX(), glyph.getMaxY()));
    	
    	} else if(reactionLine.intersectsLine(glyph.getMinX(), glyph.getMinY(), glyph.getMinX(), glyph.getMaxY())){ //left
    		return getIntersectingPoint(cs.getStart(), cs.getEnd(), new Point(glyph.getMinX(), glyph.getMinY()), new Point(glyph.getMinX(), glyph.getMaxY()));
    	
    	} else { // no intersection
    		return cs.getEnd();
    	}
    }
    
    /**
     * Gets the intersecting point.
     *
     * @param p1Start the p 1 start
     * @param p1End the p 1 end
     * @param p2Start the p 2 start
     * @param p2End the p 2 end
     * @return the intersecting point
     */
    public static Point getIntersectingPoint(Point p1Start, Point p1End, Point p2Start, Point p2End){
    	Point point = new Point(SBMLUtil.DEFAULT_SBML_LEVEL, SBMLUtil.DEFAULT_SBML_VERSION);
    	double area1 = crossProduct(p2Start, p2Start, p2End, p1Start) / 2;
    	double area2 = crossProduct(p2Start, p1End, p2End, p2Start) / 2;
    	point.setX(p1Start.getX() + (p1End.getX() - p1Start.getX()) * area1 / (area1 + area2));
    	point.setY(p1Start.getY() + (p1End.getY() - p1Start.getY()) * area1 / (area1 + area2));
    	point.setZ(0d);
    	
    	return point;
    }

    /**
     * Cross product.
     *
     * @param startPoint1 the start point 1
     * @param startPoint2 the start point 2
     * @param endPoint1 the end point 1
     * @param endPoint2 the end point 2
     * @return the double
     */
    public static double crossProduct(Point startPoint1, Point startPoint2, Point endPoint1, Point endPoint2){
    	return  (endPoint1.getX() - startPoint1.getX()) * (endPoint2.getY() - startPoint2.getY()) - (endPoint1.getY() - startPoint1.getY()) * (endPoint2.getX() - startPoint2.getX());
    }
}