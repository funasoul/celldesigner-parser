package org.sbml.layoutconverter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.sbml.jsbml.ext.layout.Dimensions;
import org.sbml.jsbml.ext.layout.LineSegment;
import org.sbml.jsbml.ext.layout.Point;
import org.sbml.jsbml.ext.layout.SpeciesGlyph;

/**
 * @author Kaito Ii
 *
 * Date Created: Jun 7, 2016
 */

public class LayoutUtil {

	/**
	 * 
	 * @param sg
	 * @return
	 * Point
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
	 * 
	 * @param p1
	 * @param p2
	 * @param editPoint
	 * @return
	 * Point
	 * TODO
	 */
	public static Point getAnchorPoint(Point p1, Point p2, Point editPoint){
		Point point = new Point();
		point.setX(p1.getX() + getLength(p1, p2, editPoint.getX()));
		point.setY(p1.getY() + getLength(p1, p2, editPoint.getY()));
		return point;
	}
	
	/**
	 * 
	 * @param p1
	 * @param p2
	 * @param editPoint
	 * @return
	 * Point
	 * TODO
	 */
	public static Point getDistanceFromEditPoint(Point p1, Point p2, double percentage){
		Point point = new Point();
		point.setX(getLength(p1, p2, percentage));
		point.setY(getLength(p1, p2, percentage));
		
		return point;
	}
	
	/**
	 * 
	 * @param d1
	 * @param d2
	 * @param d3
	 * @return
	 * double
	 * TODO
	 */
	public static double getLength(Point p1, Point p2, double proportion){
		return  Math.hypot(p1.getX()-p2.getX(), p1.getY()-p2.getY()) * proportion;
	}
	
	
	/**
	 * 
	 * @param point1
	 * @param point2
	 * @return
	 * Point
	 * TODO
	 */
	public static Point createCenterPoint(Point point1, Point point2){
		return new Point((point1.getX() + point2.getX())/2, (point1.getY() + point2.getY())/2);
	}
	
	/**
	 * returns the adjusted coordinates of a species according to the direction 
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param direction
	 * @return
	 * Point
	 * TODO
	 */
	public static Point createAdjustedPoint(double x, double y, double width, double height, String direction){
		Point point = new Point();
		
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
	 * 
	 * @param origin
	 * @param axis1
	 * @param axis2
	 * @param editPoint
	 * @return
	 * Point
	 * TODO
	 */
	public static Point getEditPointPosition(SpeciesGlyph origin, SpeciesGlyph axis1, SpeciesGlyph axis2, Point editPoint){
		Point pOrigin = getCenterOfGlyph(origin);
		Point pA1 = getCenterOfGlyph(axis1);
		Point pA2  = getCenterOfGlyph(axis2);
		Point point = pOrigin.clone();

		Point displacement1 = getDistanceFromEditPoint(pOrigin, pA1, editPoint.getX());
		Point displacement2 = getDistanceFromEditPoint(pOrigin, pA2, editPoint.getY());
		
		point.setX(point.getX() + displacement1.getX() + displacement2.getX());
		point.setY(point.getY() + displacement1.getY() + displacement2.getY());		
		
		return point;
	}

	/**
	 * 
	 * @param reactantPoint
	 * @param productPoint
	 * @param editPointList
	 * @param rectangleIndex
	 * @return
	 * List<LineSegment>
	 * TODO
	 */
	public static List<LineSegment> createListOfLineSegment(Point reactantPoint, Point productPoint, List<Point> editPointList, int rectangleIndex){
		List<LineSegment> lineList = new ArrayList<LineSegment>();

		for(Point p : editPointList)
			System.out.println("point " + p.getX() + " "  + p.getY());
		
		LineSegment line = new LineSegment();
		line.setStart(reactantPoint);
		for(int i = 0; i < editPointList.size(); i++){
			Point point = getAnchorPoint(reactantPoint, productPoint, editPointList.get(i));
			line.setEnd(point.clone());
			lineList.add(line);
			line = new LineSegment();
			line.setStart(point.clone());
		}
			
		line.setEnd(productPoint);
		lineList.add(line);

		LineSegment l1 = lineList.get(rectangleIndex);
		LineSegment l2 = new LineSegment();
		Point p =  createCenterPoint(l1.getStart(), l1.getEnd());
		l2.setEnd(l1.getEnd().clone());
		l1.setEnd(p.clone());
		l2.setStart(p.clone());
		lineList.add(rectangleIndex + 1, l2);
	
		return lineList;
	}
}
