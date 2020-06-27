package it.polito.tdp.imdb.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.imdb.db.ImdbDAO;

public class Model {
	private Graph<Actor, DefaultWeightedEdge> grafo;
	private Map<Integer, Actor> idAttori;

	public List<String> getGeneri() {

		return ImdbDAO.getGeneri();
	}

	public void creaGrafo(String genere) {

		this.grafo = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);

		List<Actor> attori = ImdbDAO.getAttoriPerGenere(genere);

		Graphs.addAllVertices(this.grafo, attori);

		this.idAttori = new HashMap<>();
		for (Actor a : attori) {
			this.idAttori.put(a.getId(), a);
		}

		List<Adiacenze> ad = ImdbDAO.getAdiacenze(idAttori, genere);

		for (Adiacenze a : ad) {
			DefaultWeightedEdge e = this.grafo.addEdge(a.getAttore1(), a.getAttore2());
			this.grafo.setEdgeWeight(e, a.getPeso());
		}
	}

}
