package it.polito.tdp.imdb.model;

import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.imdb.db.ImdbDAO;

public class Model {
	private Graph<Actor, DefaultWeightedEdge> grafo;

	public List<String> getGeneri() {

		return ImdbDAO.getGeneri();
	}

	public void creaGrafo(String genere) {

		this.grafo = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);

		List<Actor> attori = ImdbDAO.getAttoriPerGenere(genere);

		Graphs.addAllVertices(this.grafo, attori);

	}

}
