/*
 * 
 */
package org.sbml.layoutconverter;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.sbml.jsbml.ext.layout.CompartmentGlyph;
import org.sbml.jsbml.ext.layout.CubicBezier;
import org.sbml.jsbml.ext.layout.Dimensions;
import org.sbml.jsbml.ext.layout.GraphicalObject;
import org.sbml.jsbml.ext.layout.LineSegment;
import org.sbml.jsbml.ext.layout.Point;
import org.sbml.jsbml.ext.layout.SpeciesGlyph;

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
	 * @param sg the sg
	 * @return Point
	 * TODO
	 */
	public static Point getCenterOfGlyph(SpeciesGlyph sg){
		Dimensions dimension = sg.getBoundingBox().getDimensions();
		Point point = sg.getBoundingBox().getPosition().clone();
		point.setX(point.getX() + dimension.getWidth() / 2);
		point.setY(point.getY() + dimension.getHeight() / 2);
		
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
		Point pOrigin = getCenterOfGlyph(origin);
		Point pA1 = getCenterOfGlyph(axis1);
		Point pA2  = getCenterOfGlyph(axis2);
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
		Point point = origin.clone();

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
	 * Gets the distance from edit point.
	 *
	 * @param p1 the p 1
	 * @param p2 the p 2
	 * @param percentage the percentage
	 * @return Point
	 * TODO
	 */
	public static Point getDistanceFromEditPoint(Point p1, Point p2, double percentage){
		Point point = new Point(SBMLUtil.DEFAULT_SBML_LEVEL, SBMLUtil.DEFAULT_SBML_VERSION);
		point.setX(getLength(p1, p2, percentage));
		point.setY(getLength(p1, p2, percentage));
		point.setZ(0);
		
		return point;
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
		return Math.hypot(p1.getX()-p2.getX(), p1.getY()-p2.getY()) * proportion;
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
		Point point = new Point();
		point.setLevel(SBMLUtil.DEFAULT_SBML_LEVEL);
		point.setVersion(SBMLUtil.DEFAULT_SBML_VERSION);
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
	 * @param sg the sg
	 * @param direction the direction
	 * @return Point
	 * TODO
	 */
	public static Point createAdjustedPoint(SpeciesGlyph sg, String direction){
		Point p = sg.getBoundingBox().getPosition();
		Dimensions d = sg.getBoundingBox().getDimensions();
		
		return createAdjustedPoint(p.getX(), p.getY(), d.getWidth(), d.getHeight(), direction);
	}
	
	/**
	 * Adjust point.
	 *
	 * @param point the point
	 * @param direction the direction
	 * @return Point
	 * TODO
	 */
	public static Point adjustPoint(Point point, String direction){
		if(direction.equals("INACTIVE")){
			//point.setX(point.getX() + saw.getW() /2);
		}
		
		if(direction.contains("W")){
			point.setX(point.getX() - 10);
		} else if(direction.contains("E")){
			point.setX(point.getX() + 10);
		}
		
		if(direction.contains("N")){
			point.setY(point.getY() - 10);
		} else if(direction.contains("S")){
			point.setY(point.getY() + 10);
		}
		
		return point;
	}

	/**
	 * Creates the list of bezier.
	 *
	 * @param startPoint the start point
	 * @param endPoint the end point
	 * @param basePoint the base point
	 * @return List<LineSegment>
	 * TODO
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
			Collections.reverse(subList);																			//since editpoint lists the cordinates starting from the editpoint
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
		
		if(type.equals("DISSOCIATION") || type.equals("TRUNCATION")){
			LineSegment l1 = lineList.get(num0 - tshapeIndex);
			LineSegment l2 = new LineSegment(SBMLUtil.DEFAULT_SBML_LEVEL, SBMLUtil.DEFAULT_SBML_VERSION);
			Point p =  createCenterPoint(l1.getStart(), l1.getEnd());
			l2.setEnd(l1.getEnd().clone());
			l1.setEnd(p.clone());
			l2.setStart(p.clone());
			lineList.add(num0 - tshapeIndex + 1, l2);
		} else {
			LineSegment l1 = lineList.get(num0 + 1 + num1 + 1 + tshapeIndex);
			LineSegment l2 = new LineSegment(SBMLUtil.DEFAULT_SBML_LEVEL, SBMLUtil.DEFAULT_SBML_VERSION);
			Point p =  createCenterPoint(l1.getStart(), l1.getEnd());
			l2.setEnd(l1.getEnd().clone());
			l1.setEnd(p.clone());
			l2.setStart(p.clone());
			lineList.add(num0 + 1 + num1 + 1 + tshapeIndex + 1, l2);
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
	 * create line segments with 2 vectors .
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
	 * Gets the position to compartment.
	 *
	 * @param sg the sg
	 * @param cg the cg
	 * @return the position to compartment
	 */
	//TODO
	public static String getPositionToCompartment(SpeciesGlyph sg, CompartmentGlyph cg){
//		Point sPoint = sg.getBoundingBox().getPosition();
//		Dimensions sDim = sg.getBoundingBox().getDimensions();
//
//		Point cPoint = cg.getBoundingBox().getPosition();
//		Dimensions cDim = cg.getBoundingBox().getDimensions();

		
		
		return "inside";
	}
}