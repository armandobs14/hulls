package algorithms;
import com.vividsolutions.jts.geom.Point;
import java.util.ArrayList;


public interface ConvexHullAlgorithm 
{
	ArrayList<Point> execute(ArrayList<Point> list);
}
