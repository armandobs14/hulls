
import algorithms.ConcaveHullBuilder;
import algorithms.FastConvexHull;
import br.com.nees.theadcaller.ThreadNotifier;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.CoordinateList;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.LineString;
import com.vividsolutions.jts.geom.Point;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import joint.codegen.locality.NeighborhoodImpl;
import joint.codegen.locality.SectorImpl;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author armando
 */
public class KaoThread extends ThreadNotifier {

    private NeighborhoodImpl ni;

    public KaoThread(int id) {
        super(id);
    }

    public void setNeighborhood(NeighborhoodImpl n) {
        this.ni = n;
    }

    @Override
    public void doRun() throws Exception {
        System.out.println(this.threadId);
        String polygon = this.convexHull(ni.getLocalityHasLocation());
        System.out.println(polygon);
        this.notifyFinish();
    }

    private String convexHull(Set sectors) {
        GeometryFactory gf = new GeometryFactory();
        ArrayList<Point> points = new CoordinateList();
        for (Iterator it = sectors.iterator(); it.hasNext();) {
            SectorImpl sector = (SectorImpl) it.next();
            String poly = sector.getLocalityHasPolygon();
            String[] res = poly.split("(\\s)");
            for (int i = 0; i < res.length; i += 2) {
//                points.add(new Coordinate(Double.parseDouble(res[i]), Double.parseDouble(res[i + 1]), 0), false);
                points.add(gf.createPoint(new Coordinate(Double.parseDouble(res[i]), Double.parseDouble(res[i + 1]))));
            }
        }
        Point[] pointArray = new Point[points.size()];
        points.toArray(pointArray);
        List<LineString> edges = ConcaveHullBuilder.getConcaveHull(pointArray, 50d);
        StringBuilder builder = new StringBuilder();
        System.out.println("-------------------------------");
        for (LineString edge : edges) {
            builder.append("[");
            builder.append(edge.getCoordinateN(0).y);
            builder.append(",");
            builder.append(edge.getCoordinateN(0).x);
            builder.append("]");
            builder.append(",");
            builder.append("[");
            builder.append(edge.getCoordinateN(1).y);
            builder.append(",");
            builder.append(edge.getCoordinateN(1).x);
            builder.append("]");

            builder.append(",");
        }
        System.out.println(builder.toString());
        System.out.println("-------------------------------");
//        ArrayList<Point> convexHullPolygon = convexHull.execute(points);
//        System.out.println(convexHullPolygon.size());
//        StringBuilder returnBuilder = new StringBuilder();
//        
//        for (Point p : convexHullPolygon) {
//            returnBuilder.append(" ");
//            returnBuilder.append(p.getCoordinate().y);
//            returnBuilder.append(" ");
//            returnBuilder.append(p.getCoordinate().x);
//        }
//        
//        
//        System.out.println(returnBuilder.toString());

        System.out.println("----------------------------");

        //CHB.demo();
//            ArrayList<String> points = 
        return null;
    }

}
