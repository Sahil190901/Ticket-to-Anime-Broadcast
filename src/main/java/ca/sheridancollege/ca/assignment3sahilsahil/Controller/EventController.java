package ca.sheridancollege.ca.assignment3sahilsahil.Controller;


import ca.sheridancollege.ca.assignment3sahilsahil.model.Ticket;
import ca.sheridancollege.ca.assignment3sahilsahil.repository.TicketRepository;
import ca.sheridancollege.ca.assignment3sahilsahil.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@AllArgsConstructor
public class EventController {

    @Autowired
    @Lazy
    private UserRepository da;

    private TicketRepository ticketRepo;

    private UserRepository UserRepo;



    @GetMapping("/")
    public String goRoot() {
        return "Root";
    }

    @GetMapping("/check")
    public String gocheck() {
        return "check";
    }

    @GetMapping("/login")
    public String goLogin() {
        return "login";
    }


    @GetMapping("/view1")
    public String viewAllTickets(Model model){
                model.addAttribute("tickets", ticketRepo.getTicketsAll());
        return "viewPage.html";

    }
    @GetMapping("/view")
    public String viewTickets(Model model ,Authentication authentication) {

//        double totalSum = 0;
//        ArrayList<Ticket> tickets= new ArrayList<>();
//        for (Ticket ticket : tickets) {
//            totalSum += ticket.getPrice();
//        }
        model.addAttribute("tickets", ticketRepo.getTickets(authentication) );
//        model.addAttribute("ticket", ticketRepo.getTicketsAll());
//        model.addAttribute("totalSum", totalSum);
//        model.addAttribute("tickets", tickets);

        return "viewPage.html";
    }

    @GetMapping("/AccessDenied")
    public String goDenied() {
        return "AccessDenied";
    }

    @GetMapping("/add")


    public  String goAdd(Model model) {

        model.addAttribute("ticket", new Ticket());
        model.addAttribute("usernames", UserRepo.finduserAll());

        return "addTicket";
    }

//    @GetMapping("/viewtable")
//    public String seetable( Model model){
//
//        return "viewPage";
//
//    }

    @PostMapping("/add")
    public String processAdd(@ModelAttribute Ticket ticket, Model model) {

        ticketRepo.addTicket(ticket);
        return "redirect:/view1";

    }

    @GetMapping("/register")
    public String goRegistration() {
        return "RegisterPage";
    }

    @GetMapping("/edit/{id}")
    public String goEdit(@PathVariable int id, Model model) {
        Ticket d = ticketRepo.getTicketById(id);

        model.addAttribute("ticket", d);
        return "editTicket";
    }

    @GetMapping("/delete/{id}")
    public String deletePage(@PathVariable int id, Model model) {

        ticketRepo.deleteTicket(id);
//        model.addAttribute("tickets", ticketRepo.getTickets());

        return "redirect:/view1";
    }


    @PostMapping("/editTicket")
    public String processEdit(@ModelAttribute Ticket ticket, Model model) {

        ticketRepo.editTicket(ticket);
        return "redirect:/view1";


    }

    @PostMapping("/register")
    public String doRegistration(@RequestParam String name,
                                 @RequestParam String password) {
        da.addUser(name, password );
        long userId = da.findUserAccount(name).getUserid();
        da.addRole(userId, 2);
        da.addRole(userId, 2);

        return "redirect:/";
    }
//
//
}
