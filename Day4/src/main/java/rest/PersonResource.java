package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.PersonDTO;
import entities.Person;
import utils.EMF_Creator;
import facades.PersonFacade;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

//Todo Remove or change relevant parts before ACTUAL use
@Path("person")
public class PersonResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    
    //An alternative way to get the EntityManagerFactory, whithout having to type the details all over the code
    //EMF = EMF_Creator.createEntityManagerFactory(DbSelector.DEV, Strategy.CREATE);
    
    private static final PersonFacade FACADE =  PersonFacade.getFacadeExample(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
            
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"the API is up and running :)\"}";
    }

    @Path("all")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAll() {
        return GSON.toJson( FACADE.getAllPersons() );
    }

    @Path("id/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getByID(@PathParam("id") int id) {
        return GSON.toJson( FACADE.getPerson( id ) );
    }

    @Path("add")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addPerson(String person) {
        PersonDTO newDTO = FACADE.addPerson( GSON.fromJson(person, PersonDTO.class) );
        return Response.ok(newDTO).build();
    }

    @Path("edit/{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response editPerson(String person) {
        PersonDTO personDTO = FACADE.editPerson( GSON.fromJson( person,PersonDTO.class ) );
        return Response.ok(personDTO).build();
    }

    @Path("delete/{id}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public String deletePerson(@PathParam("id") int id) {

        return GSON.toJson( FACADE.deletePerson(id) );
    }

}
