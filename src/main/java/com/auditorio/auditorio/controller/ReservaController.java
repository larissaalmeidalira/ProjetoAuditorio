package com.auditorio.auditorio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.auditorio.auditorio.annotation.Publico;
import com.auditorio.auditorio.model.Reserva;
import com.auditorio.auditorio.repository.ReservaRepository;

@Controller
public class ReservaController {
	
	@Autowired
	private ReservaRepository repository;
	
	@Publico
	@RequestMapping("cadastroReserva")
	public String cadReserva() {
		return "reserva/reserva";
	}
	
	
	@RequestMapping(value = "salvarReserva", method = RequestMethod.POST)
	public String salvarReserva(Reserva reserva, Model model, RedirectAttributes attr,  BindingResult result) {
			
		
		
	
			repository.save(reserva);
			return "redirect:cadastroReserva";
		
		
					
		
	}
	
}
