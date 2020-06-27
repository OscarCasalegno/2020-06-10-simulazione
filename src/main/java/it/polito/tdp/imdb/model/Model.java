package it.polito.tdp.imdb.model;

import java.util.List;

import it.polito.tdp.imdb.db.ImdbDAO;

public class Model {

	public List<String> getGeneri() {

		return ImdbDAO.getGeneri();
	}

}
