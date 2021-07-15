package controllers;

import dao.ContaBancariaDAO;

public class ContaBancariaService {
	
	ContaBancariaDAO contaBancariaDAO;
	public ContaBancariaService() {
		this.contaBancariaDAO = ContaBancariaDAO.getInstance();
	}

}
