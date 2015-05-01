
import algorithms.FastConvexHull;
import br.com.nees.theadcaller.ThreadManager;
import br.com.nees.theadcaller.ThreadNotifier;
import java.util.Iterator;
import joint.codegen.locality.NeighborhoodImpl;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author armando
 */
public class Main {

    public static void main(String[] args) {
        Kao kao = new Kao(NeighborhoodImpl.class);
        Kao.limit = 10;
        
        int pages = kao.getNeighborhoodGroups();
        for (int i = 0; i < pages; i++) {
            Iterator<NeighborhoodImpl> neighborhoods = kao.getNeighborhoods(i);
            while (neighborhoods.hasNext()) {
                NeighborhoodImpl ni = neighborhoods.next();
                System.out.println(ni);
            }
        }
//        ThreadManager manager = ThreadManager.getInstance();
//
//        ThreadNotifier thread = new KaoThread(1);
//
//        thread.addListener(manager);
//
//        thread.start();
//        ConcaveHullBuilder builder = new ConcaveHullBuilder();
//        builder.demo();

    }
}
