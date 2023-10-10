package ca.sheridancollege.ca.assignment3sahilsahil.repository;

import ca.sheridancollege.ca.assignment3sahilsahil.model.Ticket;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class TicketRepository {

    private NamedParameterJdbcTemplate jdbc;

    public TicketRepository(NamedParameterJdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }
    public ArrayList<Ticket> getTickets(Authentication authentication){
        MapSqlParameterSource parameters =new MapSqlParameterSource();
        String username = authentication.getName();
//        double totalSum = 0;
//        ArrayList<Ticket> tickets= new ArrayList<>();
//        for (Ticket ticket : tickets) {
//            totalSum += ticket.getPrice();
//        }
        ArrayList<String> roles = new ArrayList<String>();
        for (GrantedAuthority ga: authentication.getAuthorities()) {
            roles.add(ga.getAuthority());
        }
        String query = "SELECT * FROM ticket where name= :username ";
        parameters.addValue("username" , username) ;
        parameters.addValue("roles" , roles) ;
        ArrayList<Ticket> tickets = (ArrayList<Ticket>) jdbc.query(query, parameters , new BeanPropertyRowMapper<Ticket>(Ticket.class))
                ;
        return tickets;

    }

    public ArrayList<Ticket> getTicketsAll(){
        MapSqlParameterSource parameters =new MapSqlParameterSource();

        String query = "SELECT * FROM ticket  ";

        ArrayList<Ticket> tickets= (ArrayList<Ticket>) jdbc.query(query, parameters , new BeanPropertyRowMapper<Ticket>(Ticket.class))
                ;
        return tickets;

    }
    public Ticket getTicketById(int id){
        MapSqlParameterSource parameters =new MapSqlParameterSource();
        String query = "SELECT * FROM ticket where id= :id";
        parameters.addValue("id" , id) ;
        ArrayList<Ticket> tickets = (ArrayList<Ticket>) jdbc.query(query, parameters , new BeanPropertyRowMapper<Ticket>(Ticket.class))
                ;
        if(tickets.size()>0){
            return tickets.get(0);
        }else{
            return null;
        }
    }

    public void deleteTicket(int id){
        MapSqlParameterSource parameters =new MapSqlParameterSource();
        String delete="DELETE FROM ticket WHERE id=:Woof";
        parameters.addValue("Woof" ,     id);
        jdbc.update(delete, parameters);
    }

    public void addTicket(Ticket ticket){
        MapSqlParameterSource parameters =new MapSqlParameterSource();
        String query = "INSERT INTO ticket"
                +"(name, venue, price, seatNumber, date, time) "
                +"VALUES"
                +"(:na , :ve ,:pr, :sn ,:da ,:ti )";
        parameters.addValue("na" , ticket.getName());
        parameters.addValue("ve" , ticket.getVenue());
        parameters.addValue("pr" , ticket.getPrice());
        parameters.addValue("sn" , ticket.getSeatNumber());
        parameters.addValue("da" , ticket.getDate());
        parameters.addValue("ti" , ticket.getTime());
        jdbc.update(query , parameters);

    }

    public void editTicket(Ticket ticket){
        MapSqlParameterSource parameters =new MapSqlParameterSource();
        String query=" UPDATE ticket SET name=:na , venue=:ve ,price=:pr , " +
                "seatNumber=:sn , date=:da , time=:ti WHERE id=:id";
        parameters.addValue("id" , ticket.getId());
        parameters.addValue("na" , ticket.getName());
        parameters.addValue("ve" , ticket.getVenue());
        parameters.addValue("pr" , ticket.getPrice());
        parameters.addValue("sn" , ticket.getSeatNumber());
        parameters.addValue("da" , ticket.getDate());
        parameters.addValue("ti" , ticket.getTime());
        parameters.addValue("woof" ,  ticket.getId()) ;
        jdbc.update(query , parameters);

    }
}
