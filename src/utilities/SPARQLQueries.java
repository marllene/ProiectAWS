package utilities;

import com.hp.hpl.jena.query.*;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.sparql.engine.http.QueryEngineHTTP;
import parse.Config;

/**
 * Created by marlen on 18.05.2015.
 */
public class SPARQLQueries {

    public static Resource getJudetResursa(String judet){
        String sparqlQuery = String.format("" +
                "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> \n" +
                "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> \n" +
                "SELECT ?s WHERE {\n" +
                "  ?s rdfs:label $label .\n" +
                "  ?s rdf:type <http://dbpedia.org/class/yago/CountiesOfRomania> .\n" +
                "  filter(lcase(str($label)) = \"%s\")\n" +
                "}",judet.toLowerCase());

        Query query = QueryFactory.create(sparqlQuery, Syntax.syntaxARQ) ;
        QuerySolutionMap querySolutionMap = new QuerySolutionMap();
        ParameterizedSparqlString parameterizedSparqlString = new ParameterizedSparqlString(query.toString(), querySolutionMap);
        QueryEngineHTTP httpQuery = new QueryEngineHTTP(Config.SPARQL_ENDPOINT,parameterizedSparqlString.asQuery());
        ResultSet results = httpQuery.execSelect();
        if(results.hasNext()) {
            QuerySolution solution = results.next();
            Resource expression = solution.get("s").asResource();
            return expression;
        }
        return null;
    }
}
