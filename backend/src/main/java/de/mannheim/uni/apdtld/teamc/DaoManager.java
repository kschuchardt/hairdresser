package de.mannheim.uni.apdtld.teamc;

import de.mannheim.uni.apdtld.teamc.dao.InMemoryCustomerDAO;
import de.mannheim.uni.apdtld.teamc.dao.InMemoryEmployeeDAO;

public class DaoManager {

	private static DaoManager singleton;
	private static InMemoryCustomerDAO custDAO;
	private static InMemoryEmployeeDAO empDAO;

	public enum DAOS {
		CUSTOMER, EMPLOYEE
	}

	public static synchronized DaoManager getInstance() {
		if (singleton == null) {
			singleton = new DaoManager();
		}
		return singleton;
	}

	public InMemoryEmployeeDAO getEmployeeDao() {

		if (empDAO == null) {
			empDAO = new InMemoryEmployeeDAO();
		}
		return empDAO;
	}

}
