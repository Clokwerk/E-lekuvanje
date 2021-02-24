package com.elekuvanje.elekuvanje.web;

import com.elekuvanje.elekuvanje.exceptions.InvalidArgumentsException;
import com.elekuvanje.elekuvanje.exceptions.NoSuchUserException;
import com.elekuvanje.elekuvanje.model.Termin;
import com.elekuvanje.elekuvanje.model.User;
import com.elekuvanje.elekuvanje.repository.UserRepository;
import com.elekuvanje.elekuvanje.service.TerminService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.List;

@Controller

@RequestMapping("/doctor")

public class DoctorController {
    private final TerminService terminService;
    private final UserRepository userRepository;
    public DoctorController(TerminService terminService,UserRepository userRepository){
        this.terminService=terminService;
        this.userRepository=userRepository;
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
    public String postLoginPage(HttpServletRequest request,Model model){
        User user=null;
        try {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
                throw new InvalidArgumentsException();
            }

             user = this.userRepository.findByUsernameAndPassword(username, password).orElse(null);
            if (user != null && user.getRole().toString().equals("ROLE_DOCTOR")) {
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

    public String getTerminiPage(@RequestParam(required = false) String error, Model model, HttpServletRequest request){
        if(error != null && !error.isEmpty()){
            model.addAttribute("hasError",true);
            model.addAttribute("error",error);
        }


        User user=(User) request.getSession().getAttribute("doctor");
        if(user == null){
            return "redirect:/doctor/login";
        }
        List<Termin> terminList=this.terminService.findBySetByDoctorId(userRepository.findByUsername(user.getUsername()).get().getId());
        //List<Termin> terminList=this.terminService.listAll();
        model.addAttribute("terminList",terminList);
        return "listTermini";
    }

    @GetMapping(value="/termini/add")
    public String getAddTerminiPage(@RequestParam(required = false) String error, Model model, HttpServletRequest request){
        if(error != null && !error.isEmpty()){
            model.addAttribute("hasError",true);
            model.addAttribute("error",error);
        }


        User user=(User) request.getSession().getAttribute("doctor");
        if(user == null){
            return "redirect:/doctor/login";
        }
        return "addTermin";
    }

    @PostMapping(value="/termini/add")
    public String postAddTermini(@RequestParam(required = false) String error, Model model, HttpServletRequest request){
        if(error != null && !error.isEmpty()){
            model.addAttribute("hasError",true);
            model.addAttribute("error",error);
        }


        User user=(User) request.getSession().getAttribute("doctor");
        if(user == null){
            return "redirect:/doctor/login";
        }
        return "addTermin";
    }

   



    @GetMapping(value="/logout")
    public String logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:/doctor/login";
    }


   /* public String getDoctorTerminPages(@RequestParam(required = false) String error, Model model, Principal principal){
        if(error != null && !error.isEmpty()){
            model.addAttribute("hasError",true);
            model.addAttribute("error",error);
        }
       // UserDetails userDetails =
               // (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        //List<Termin> terminList=this.terminService.findBySetByDoctorId(userRepository.findByUsername(userDetails.getUsername()).get().getId());
        List<Termin> terminList=this.terminService.listAll();
        model.addAttribute("terminList",terminList);
        return "listTermini";
        */

    }




