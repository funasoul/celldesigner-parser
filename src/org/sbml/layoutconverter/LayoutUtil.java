package org.sbml.layoutconverter;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import org.sbml.jsbml.ext.layout.Dimensions;
import org.sbml.jsbml.ext.layout.LineSegment;
import org.sbml.jsbml.ext.layout.Point;
import org.sbml.jsbml.ext.layout.SpeciesGlyph;
import org.sbml.wrapper.CompartmentAliasWrapper;
import org.sbml.wrapper.CompartmentWrapper;

/**
 * @author Kaito Ii
 *
 * Date Created: Jun 7, 2016
 */

public class LayoutUtil {

	public static int DEFAULTSBMLLEVEL = 3;
	public static int DEFAULTSBMLVERSION = 1;
	
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
	 * @param origin
	 * @param axis1
	 * @param axis2
	 * @param editPoint
	 * @return
	 * Point
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
	 * 
	 * @param origin
	 * @param axis1
	 * @param axis2
	 * @param editPoint
	 * @return
	 * Point
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
		Point point = new Point(DEFAULTSBMLLEVEL, DEFAULTSBMLVERSION);
		point.setX(getLength(p1, p2, percentage));
		point.setY(getLength(p1, p2, percentage));
		point.setZ(0);
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
		return Math.hypot(p1.getX()-p2.getX(), p1.getY()-p2.getY()) * proportion;
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
		point.setLevel(DEFAULTSBMLLEVEL);
		point.setVersion(DEFAULTSBMLVERSION);
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
	 * 
	 * @param point
	 * @param direction
	 * @return
	 * Point
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
	 * 
	 * @param startPoint
	 * @param endPoint
	 * @param editPointList
	 * @param rectangleIndex
	 * @return
	 * List<LineSegment>
	 * TODO
	 */
	public static List<LineSegment> createListOfLineSegment(Point startPoint, Point endPoint, List<Point2D.Double> editPointList, int rectangleIndex){
		List<LineSegment> lineList = new ArrayList<LineSegment>();
		
		Point perpPoint = createPerpendicularPoint(startPoint, endPoint);
		LineSegment line = new LineSegment(DEFAULTSBMLLEVEL, DEFAULTSBMLVERSION);
		line.setStart(startPoint);
		for(int i = 0; i < editPointList.size(); i++){
			Point point = getEditPointPosition(startPoint, endPoint, perpPoint, editPointList.get(i));
			line.setEnd(point.clone());
			lineList.add(line);
			line = new LineSegment(DEFAULTSBMLLEVEL, DEFAULTSBMLVERSION);
			line.setStart(point.clone());
		}
			
		line.setEnd(endPoint);
		lineList.add(line);

		LineSegment l1 = lineList.get(rectangleIndex);
		LineSegment l2 = new LineSegment(DEFAULTSBMLLEVEL, DEFAULTSBMLVERSION);
		Point p =  createCenterPoint(l1.getStart(), l1.getEnd());
		l2.setEnd(l1.getEnd().clone());
		l1.setEnd(p.clone());
		l2.setStart(p.clone());
		lineList.add(rectangleIndex + 1, l2);
	
		return lineList;
	}
	
	
	public static Point createPerpendicularPoint(Point start, Point end){
		Point point = start.clone();
		point.setX(start.getX() + (end.getY() - start.getY()) * -1);
		point.setY(start.getY() + end.getX() - start.getX());
		
		return point;
	}
	
    
	/**
	 * 
	 * @param editPoints
	 * @return
	 * List<Point>
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
}
