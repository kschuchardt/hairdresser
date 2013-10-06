package de.mannheim.uni.apdtld.teamc.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import de.mannheim.uni.apdtld.teamc.DaoManager;
import de.mannheim.uni.apdtld.teamc.dao.InMemoryEmployeeDAO;
import de.mannheim.uni.apdtld.teamc.model.Appointment;
import de.mannheim.uni.apdtld.teamc.model.Employee;
import de.mannheim.uni.apdtld.teamc.viewmodel.AppointmentOverview;


@Path("/")
public class EmployeeService {
	
	DaoManager daoMgr = DaoManager.getInstance();
	
	// Appointment Overview for all

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("employee")
	public AppointmentOverview getAppointmentOverview() {
		return new AppointmentOverview();
	}
	
	
	// get employee by ID
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("employee/{id}")
	public Employee getEmployee(@PathParam("id") int id) {
		try{
			return daoMgr.getEmployeeDao().getById(id);
		
		} catch (RuntimeException e) {
			e.printStackTrace();
			return null;
		}

	}
	
	// add employee
	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("employee/post")
	public Response addEmployee(Employee employee) {

		if (employee != null) {

			try {
				daoMgr.getEmployeeDao().addEmployee(employee);

			} catch (Exception e) {
				e.printStackTrace();
				// internal server error
				return Response.status(500).build();
			}
			// created
			return Response.status(201).build();

		} else {
			// bad request
			return Response.status(400).build();
		}
	}
	
	//delete employee
	@DELETE
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("employee/delete")
	public Response deleteEmployee(Employee employee) {
		daoMgr.getEmployeeDao().deleteEmployee(employee);
		return Response.status(200).build();
	}
	
	//delete employee by id
	@DELETE
	@Path("employee/{id}")
	public Response deleteEmployeeById(@PathParam("id") int id) {
		
		InMemoryEmployeeDAO empDao = daoMgr.getEmployeeDao();
		Employee employee = empDao.getById(id);
		try{
		} catch(RuntimeException e){
			e.printStackTrace();
			return Response.status(204).build();
		}
		if(employee != null){
			try{
				daoMgr.getEmployeeDao().deleteEmployee(employee);
			} catch(Exception e){
				e.printStackTrace();
				return Response.status(500).build();
			}
			
			return Response.status(200).build();
		}
		return null;
		
	}

}
