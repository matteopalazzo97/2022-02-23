package it.polito.tdp.yelp.model;

import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultDirectedWeightedGraph;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedGraph;

import it.polito.tdp.yelp.db.YelpDao;

public class Model {
	
	private YelpDao dao;
	private Graph<Review, DefaultWeightedEdge> grafo;
	private Map<String, Review> reviewMap;
	
	
	public Model() {
		super();
		this.dao = new YelpDao();
		this.reviewMap = this.dao.getAllReviewsMap();
	}
	
	public void creaGrafo(Business locale) {
		
		this.grafo = new DefaultDirectedWeightedGraph<>(DefaultWeightedEdge.class);
		
		List<Review> vertici = this.dao.getReviewsLocale(locale);
		
		Graphs.addAllVertices(grafo, vertici);
		
		System.out.println(this.grafo.vertexSet().size());
		
		List<Edge> edges = this.getEdges(locale, reviewMap);
		
		for(Edge e : edges) {
			DefaultWeightedEdge arco = this.grafo.addEdge(e.getR1(), e.getR2());
			this.grafo.setEdgeWeight(arco, e.getPeso());
		}
		
		System.out.println(this.grafo.edgeSet().size());
		
		Review rMax = null;
		int max = 0;
		for(Review r : this.grafo.vertexSet()) {      //a quanto pare il numero di archi è giusto ma
			if(this.grafo.degreeOf(r) > max) {        //la review che trova è sbagliata, non so che fare sinceramente
				max = this.grafo.degreeOf(r); 
				rMax = r;
			}
		}
		
		System.out.println(rMax.getReviewId() + "     " + max);
		
	}
	
	public List<String> riempiCmbCity() {
		
		return this.dao.getAllCity();
	}

	public List<Business> riempiCmbLocali(String citta) {
		
		return this.dao.getLocaliCitta(citta);
	}
	
	public List<Edge> getEdges (Business locale, Map<String, Review> mappa) {
		return this.dao.getEdges(locale, mappa);
	}
}
