package ca.sheridancollege.ca.assignment3sahilsahil.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
//import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import ca.sheridancollege.ca.assignment3sahilsahil.beans.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class UserRepository {

    private NamedParameterJdbcTemplate jdbc;
    private UserRepository userRepo;

    public UserRepository(NamedParameterJdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }


    public  User findUserAccount(String userName) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        String query = "SELECT * FROM sec_user WHERE userName=:name";
        parameters.addValue("name", userName);
        ArrayList<User> users = (ArrayList<User>) jdbc.query(query,
                parameters,
                new BeanPropertyRowMapper<User>(User.class));

        return (users.size() > 0) ? users.get(0) : null;

    }

        public ArrayList<User> finduserAll(){
            MapSqlParameterSource parameters =new MapSqlParameterSource();
        String query = "SELECT userName FROM SEC_USER";
            ArrayList<User> users = (ArrayList<User>) jdbc.query(query, parameters , new BeanPropertyRowMapper<User>(User.class))
                    ;
            return users;
        }


//        public   ArrayList<User> getUsers(){
//        MapSqlParameterSource parameters =new MapSqlParameterSource();
//        String query = "SELECT * FROM SEC_USER";
//        ArrayList<User> users = (ArrayList<User>) jdbc.query(query, parameters , new BeanPropertyRowMapper<User>(User.class))
//                ;
//        return users;
//
//    }
//    public ca.sheridancollegelnusa.securityexercise.beans.User getUserByUsername(String username){
//        MapSqlParameterSource parameters =new MapSqlParameterSource();
//        String query = "SELECT * FROM SEC_USER WHERE username=:woof";
//        parameters.addValue("woof" , username);
//
//        ArrayList<User> users = (ArrayList<User>) jdbc.query(query, parameters , new BeanPropertyRowMapper<User>(User.class))
//                ;
//        if(users.size()>0)
//            return users.get(0);
//        else
//            return null;
//
//    }


    public void addRole(long userId, long roleId) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        String query = "insert into user_role (userId, roleId)"
                + "values (:userId, :roleId);";
        parameters.addValue("userId", userId);
        parameters.addValue("roleId", roleId);
        jdbc.update(query, parameters);
    }
////
    public void addUser(String userName, String password) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        String query = "insert into SEC_User "
                + "(userName, encryptedPassword, ENABLED)"
                + " values (:userName, :encryptedPassword, 1)";
        BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
        parameters.addValue("userName", userName);
        parameters.addValue("encryptedPassword",
                passwordEncoder.encode(password));
        jdbc.update(query, parameters);
    }
    public List<String> getRolesById(long userId){
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        String query = "SELECT user_role.userId, sec_role.roleName "
                + "FROM user_role, sec_role WHERE "
                + "user_role.roleId=sec_role.roleId and userId=:id";
        parameters.addValue("id", userId);
        ArrayList<String> roles = new ArrayList<String>();
        List<Map<String,Object>> rows= jdbc.queryForList(query, parameters);
        for (Map<String,Object> row : rows) {
            roles.add((String)row.get("roleName"));
        }
        return roles;
    }
//    public List<String> getRolesById(long userId){
//        MapSqlParameterSource parameters =new MapSqlParameterSource();
//        String query="SELECT user_role.userId , sec_role.roleName FROM user_role, sec_role WHERE user_role.roleId=sec_role.roleId and userId=3";
//        parameters.addValue("woof" , userId);
//        ArrayList<String> roles = new ArrayList<String>();
//        List<Map<String , Object>> rows = jdbc.queryForList(query , parameters);
//        for (Map<String , Object> row: rows) {
//            roles.add((String) row.get("roleName"));
//        }
//        return  roles;
//    }


//    public  void addNewUser(String username , String password , PasswordEncoder passwordEncoder){
//        MapSqlParameterSource parameters =new MapSqlParameterSource();
//        String query = "insert into  SEC_USER (username , encrypted Password , ENABLED) values (:uname , :ep , 1)";
//        parameters.addValue("uname", username);
//        parameters.addValue("ep" ,passwordEncoder.encode(password));
//        jdbc.update(query , parameters);}

//

//    @Bean
//    public BCryptPasswordEncoder passwordEncoder(){
//        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//        return passwordEncoder;
//
//    }
}

