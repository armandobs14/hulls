
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import joint.codegen.locality.NeighborhoodImpl;
import joint.codegen.locality.SectorImpl;
import wwwc.nees.joint.module.kao.AbstractKAO;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author armando
 */
public class Kao extends AbstractKAO {

    private Set<URI> contexts;

    public <T extends Object> Kao(Class<T> classe) {
        super(classe);
        contexts = new HashSet<>();
    }

    public Iterator getNeighborhoods(int offset, int limit) {
        String query = "prefix loc: <http://nees.com.br/linkn/onto/locality/>\n"
                + "select ?neigh\n"
                + "from <http://nees.com.br/linkn/data/brazil/address2/>\n"
                + "where{\n"
                + "?neigh a loc:Neighborhood; loc:hasLocation ?sector.\n"
                + "?sector a loc:Sector.\n"
                + "}"
                + "offset " + offset + "\n"
                + "limit " + limit + "\n";
        //return this.executeSPARQLqueryResultList(query);
        System.out.println(query);
        return this.executeQueryAsIterator(query);
    }

    public NeighborhoodImpl getNeighborhood(int offset) {
        String query = "prefix loc: <http://nees.com.br/linkn/onto/locality/>\n"
                + "select ?neigh\n"
                + "from <http://nees.com.br/linkn/data/brazil/address2/>\n"
                + "where{\n"
                + "?neigh a loc:Neighborhood; loc:hasLocation ?sector.\n"
                + "?sector a loc:Sector.\n"
                + "}"
                + "offset " + offset + "\n"
                + "limit 1\n";
        //return this.executeSPARQLqueryResultLsist(query);
        return (NeighborhoodImpl) this.executeSPARQLquerySingleResult(query);
    }

    public List<SectorImpl> getSectorsByNeighborhood(NeighborhoodImpl ni) throws URISyntaxException {
        String query = "prefix loc: <http://nees.com.br/linkn/onto/locality/>\n"
                + "select distinct ?sector\n"
                + "from <http://nees.com.br/linkn/data/brazil/address2/>\n"
                + "where{\n"
                + ni.getURI()+" loc:hasLocation ?sector.\n"
                + "?sector a loc:Sector.\n"
                + "}";
        return this.executeSPARQLqueryResultList(query,new URI("http://nees.com.br/linkn/data/brazil/address2/"));
    }
}
