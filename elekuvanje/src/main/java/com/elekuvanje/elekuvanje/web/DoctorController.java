package com.elekuvanje.elekuvanje.web;

import com.elekuvanje.elekuvanje.model.Termin;
import com.elekuvanje.elekuvanje.repository.UserRepository;
import com.elekuvanje.elekuvanje.service.TerminService;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

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
    @GetMapping(value = "/login")
    public RedirectView getLoginPage(Model model) {
        //model.addAttribute("bodyContent","login");
        //return "najava-doktor";
        return new RedirectView("/doctor/termini");
    }



    @GetMapping(value="/termini")

    public String getTerminiPage(@RequestParam(required = false) String error, Model model){
        if(error != null && !error.isEmpty()){
            model.addAttribute("hasError",true);
            model.addAttribute("error",error);
        }

        List<Termin> terminList=this.terminService.listAll();
        model.addAttribute("terminList",terminList);
        return "listTermini";
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




