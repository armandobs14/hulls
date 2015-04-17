package algorithms;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class SlowConvexHull implements ConvexHullAlgorithm 
{

	@Override
	public ArrayList<Point> execute(ArrayList<Point> points) 
	{
		int n = points.size();
		
		ArrayList<LineSegment> edges = new ArrayList<LineSegment>();
		
		for (int i = 0; i < n; i++)
		{
			for (int j = 0; j < n; j++)
			{
				if (i == j) continue;
				
				boolean valid = true;
				
				for (int k = 0; k < n; k++)
				{
					if (k == i || k == j) continue;
					
					if (!rightOfLine(points.get(k), new LineSegment(points.get(i), points.get(j))))
					{
						valid = false;
						break;
					}
				}
				
				if (valid)
				{
					edges.add(new LineSegment(points.get(i), points.get(j)));
				}
			}
		}
		
		return sortedVertexList(edges);
	}
	
	private boolean rightOfLine(Point p, LineSegment line)
	{
		return (line.p2.x-line.p1.x)*(p.y-line.p1.y) - (line.p2.y-line.p1.y)*(p.x-line.p1.x) < 0;
	}
	
	private boolean leftOfLine(Point p, LineSegment line)
	{
		return (line.p2.x-line.p1.x)*(p.y-line.p1.y) - (line.p2.y-line.p1.y)*(p.x-line.p1.x) > 0;
	}
	
	private ArrayList<Point> sortedVertexList(ArrayList<LineSegment> lines)
	{
		ArrayList<LineSegment> xSorted = (ArrayList<LineSegment>) lines.clone();
		Collections.sort(xSorted, new XCompare());
		
		int n = xSorted.size();
		
		LineSegment baseLine = new LineSegment(xSorted.get(0).p1, xSorted.get(n-1).p1);
		
		ArrayList<Point> result = new ArrayList<Point>();
		
		result.add(xSorted.get(0).p1);
		
		for (int i = 1; i < n; i++)
		{
			if (leftOfLine(xSorted.get(i).p1, baseLine))
			{
				result.add(xSorted.get(i).p1);
			}
		}
		
		result.add(xSorted.get(n-1).p1);
		
		for (int i = n-2; i > 0; i--)
		{
			if (rightOfLine(xSorted.get(i).p1, baseLine))
			{
				result.add(xSorted.get(i).p1);
			}
		}
		
		return result;
	}
	
	private class LineSegment
	{
		public Point p1, p2;
		
		public LineSegment(Point p1, Point p2)
		{
			this.p1 = p1;
			this.p2 = p2;
		}
		
		public String toString()
		{
			return p1.toString() + "," + p2.toString() + "\n";
		}
	}
	
	private class XCompare implements Comparator<LineSegment>
	{
		@Override
		public int compare(LineSegment o1, LineSegment o2) 
		{
			return (new Integer(o1.p1.x)).compareTo(new Integer(o2.p1.x));
		}
	}

}
