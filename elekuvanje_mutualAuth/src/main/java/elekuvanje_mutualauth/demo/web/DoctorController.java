package elekuvanje_mutualauth.demo.web;




import elekuvanje_mutualauth.demo.exceptions.InvalidArgumentsException;
import elekuvanje_mutualauth.demo.exceptions.NoSuchUserException;
import elekuvanje_mutualauth.demo.model.Termin;
import elekuvanje_mutualauth.demo.model.User;
import elekuvanje_mutualauth.demo.repository.UserRepository;
import elekuvanje_mutualauth.demo.service.TerminService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;


import java.security.Principal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.List;
import java.util.stream.Collectors;

@Controller

@RequestMapping("/doctor")

public class DoctorController {
    private final TerminService terminService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    public DoctorController(TerminService terminService,UserRepository userRepository,PasswordEncoder passwordEncoder){
        this.terminService=terminService;
        this.userRepository=userRepository;
        this.passwordEncoder=passwordEncoder;
    }
   /* @GetMapping(value = "/login")
    public RedirectView getLoginPage(Model model) {
        //model.addAttribute("bodyContent","login");
        //return "najava-doktor";
        return new RedirectView("/doctor/termini");
    }
*/
    @GetMapping(value="/login")
    public String getLoginPage(Model model){
        return "najava-doktor";
    }

    @PostMapping(value="/login")
    public String postLoginPage(HttpServletRequest request,Model model,Principal principal){
        User user=null;
        String currentlyLoggedIn=principal.getName();
        try {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
                throw new InvalidArgumentsException();
            }

             //user = this.userRepository.findByUsernameAndPassword(username, password).orElse(null);
            user=this.userRepository.findByUsername(username).orElse(null);
            if (user != null && passwordEncoder.matches(password,user.getPassword())&& user.getRole().toString().equals("ROLE_DOCTOR")&&currentlyLoggedIn.equals(user.getUsername())) {
                request.getSession().setAttribute("doctor", user);
                return "redirect:/doctor/termini";
            }else{
                throw new NoSuchUserException();
            }
        }catch(InvalidArgumentsException invalidArgumentsException){

            return "najava-doktor";
        }catch (NoSuchUserException noSuchUserException){
            return "najava-doktor";
        }

    }


    @GetMapping(value="/termini")

    public String getTerminiPage(Model model, HttpServletRequest request,Principal principal){


        String currentlyLoggedIn=principal.getName();
        User user=(User) request.getSession().getAttribute("doctor");
        if(user == null && currentlyLoggedIn.equals(user.getUsername())){
            return "redirect:/doctor/login";
        }
        List<Termin> terminList=this.terminService.findBySetByDoctorId(userRepository.findByUsername(user.getUsername()).get().getId());
        //List<Termin> terminList=this.terminService.listAll();
        model.addAttribute("terminList",terminList);
        return "listTermini";
    }

    @GetMapping(value="/termini/add")
    public String getAddTerminiPage( Model model, HttpServletRequest request,Principal principal){


        String currentlyLoggedIn=principal.getName();
        User user=(User) request.getSession().getAttribute("doctor");
        if(user == null && currentlyLoggedIn.equals(user.getUsername())){
            return "redirect:/doctor/login";
        }
        List<User> patients=this.userRepository.findAll().stream().filter(x->x.getRole().toString().equals("ROLE_PATIENT")).collect(Collectors.toList());
        model.addAttribute("patients",patients);


        return "addTermin";
    }

    @PostMapping(value="/termini/add")
    public String postAddTermini(@RequestParam() String birthdaytime, @RequestParam() Long dropdown, HttpServletRequest request, @RequestParam() String description,Principal principal){

        String currentlyLoggedIn=principal.getName();
        User user=(User) request.getSession().getAttribute("doctor");
        if(user == null && currentlyLoggedIn.equals(user.getUsername())){
            return "redirect:/doctor/login";
        }
        User patient=this.userRepository.findById(dropdown).get();
        DateTimeFormatter formatter= DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
        LocalDateTime datum=LocalDateTime.parse(birthdaytime,formatter);
        this.terminService.createTermin(user,patient,datum,description);
        return "redirect:/doctor/termini";
    }


   @GetMapping(value="/termin/deleteTermin")
   public String deleteTermin(@RequestParam(required = true) Long terminID,HttpServletRequest request,Principal principal){
       String currentlyLoggedIn=principal.getName();
       User user=(User) request.getSession().getAttribute("doctor");
       if(user == null && currentlyLoggedIn.equals(user.getUsername())){
           return "redirect:/doctor/login";
       }
       List<Termin> termins=this.terminService.
               findBySetByDoctorId(user.getId()).stream().filter(x->x.getId()==terminID).
               collect(Collectors.toList()); // check if Termin belongs to doctor
       if(termins.isEmpty()){
            //termin doesn't belong to doctor
       }else{
           this.terminService.deleteById(terminID);

       }
       return "redirect:/doctor/termini";

   }



    @GetMapping(value="/logout")
    public String logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:/doctor/login";
    }




    }




