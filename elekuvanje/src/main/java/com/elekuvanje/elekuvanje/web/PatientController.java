package com.elekuvanje.elekuvanje.web;

import com.elekuvanje.elekuvanje.model.Termin;
import com.elekuvanje.elekuvanje.model.User;
import com.elekuvanje.elekuvanje.repository.UserRepository;
import com.elekuvanje.elekuvanje.service.TerminService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/patient")
public class PatientController {

private final TerminService terminService;
private final UserRepository userRepository;
    public PatientController(TerminService terminService,UserRepository userRepository) {
        this.terminService=terminService;
        this.userRepository=userRepository;
    }

    @GetMapping(value = "/login")
    public String getLoginPage(Model model) {
        model.addAttribute("bodyContent","login");
        return "najava-pacient";
    }
    @GetMapping(value="/logout")
    public String logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:/patient/login";
    }
    @GetMapping(value="/register")
    public String register(Model model){
        return "registracija";
    }



    @GetMapping(value="/termini")

    public String getTerminiPage(@RequestParam(required = false) String error, Model model){
        if(error != null && !error.isEmpty()){
            model.addAttribute("hasError",true);
            model.addAttribute("error",error);
        }
        UserDetails userDetails =
                (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Termin> terminList=this.terminService.findBySetForPatientId(userRepository.findByUsername(userDetails.getUsername()).get().getId());
        //List<Termin> terminList=this.terminService.listAll();
        model.addAttribute("terminList",terminList);
        return "listTerminiPacient";
    }

    @GetMapping(value="/termin/deleteTermin")
    public String deleteTermin(@RequestParam(required = true) Long terminID){
        UserDetails userDetails =
                (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Termin> termins=this.terminService.
                findBySetForPatientId(this.userRepository.findByUsername(userDetails.getUsername()).get().getId()); // check if Termin belongs to patient
        if(termins.isEmpty()){
            //termin doesn't belong to patient
        }else{
            this.terminService.deleteById(terminID);

        }
        return "redirect:/patient/termini";

    }

}
