package gr.teicm.toulou.SnapChatyX.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import gr.teicm.toulou.SnapChatyX.model.DataAccessObject;
import gr.teicm.toulou.SnapChatyX.model.IUserHistory;
import gr.teicm.toulou.SnapChatyX.model.IUserHistoryDAO;

/**
 * 
 * 
 * @since Dec 3, 2015
 * 
 * @author Stamatios Tsalikis
 */
@Path("histories/user-histories")
public class UserHistoryResource {
	
	IUserHistoryDAO dao = DataAccessObject.DAO;
	
	@GET
	@Path("user-history/{username}")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.TEXT_PLAIN)
	public Response getUserHistoryByUsername(@PathParam("username") String username) {
		
		if (username.equals(null) || username.equals("")) {
			
			return Response.status(Status.BAD_REQUEST).build();
			
		}
		
		IUserHistory userHistory = dao.getUserHistoryByUsername(username);
		
		if (!userHistory.equals(null)) {
			
			Gson gson = new GsonBuilder().disableHtmlEscaping().serializeNulls().create();
			
			String userHistoryJson = gson.toJson(userHistory);
			
			return Response.ok().entity(userHistoryJson).build();
			
		} else {
			
			return Response.status(Status.NOT_FOUND).build();
			
		}
		
	}
	
}
