package com.auditorio.auditorio.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import com.auditorio.auditorio.annotation.Publico;
import com.auditorio.auditorio.model.Usuario;
import com.auditorio.auditorio.repository.UsuarioRepository;



@Controller
public class AdmController {
	
	@Autowired
	private UsuarioRepository repository;
	
	@Publico
	@RequestMapping("pagLogin")
	public String paglogin() {
		return "adm/paglogin";
	}
	
	@Publico
	@RequestMapping("loginAdm")
	public String loginAdm(String senha, String nif, RedirectAttributes attr,  HttpSession session) {
		
		String admNif = "123";
		String admSenha = "admin";
		
		if(senha.equals(admSenha) && nif.equals(admNif)) {
			session.setAttribute("admLogado", admSenha+admNif);
			return "redirect:cadastroUsuario";
		}
		
		attr.addFlashAttribute("mensagemErro", "Login e/ou senha inválido");
		return "redirect:pagLogin";
	}
	
	
	
	@RequestMapping("listarUsuarios/{page}")
	public String listaUsuarios(Model model, @PathVariable("page") int page) {
		
		
		PageRequest pageable = PageRequest.of(page-1, 10, Sort.by(Sort.Direction.ASC, "nome"));
		
		Page<Usuario> pagina = repository.findAll(pageable);
		
		int totalPages = pagina.getTotalPages();
		
		List<Integer> pageNumbers = new ArrayList<Integer>();
		
		for(int i = 0; i < totalPages; i++) {
			pageNumbers.add(i+1);
		}
		
		model.addAttribute("usuarios", pagina.getContent());
		model.addAttribute("paginaAtual", page);
		model.addAttribute("totalPaginas", totalPages);
		model.addAttribute("numPaginas", pageNumbers);
		
		
		return "usuario/listaUsuarios";
	}


}
