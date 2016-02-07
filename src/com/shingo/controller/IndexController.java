package com.shingo.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

//Si se quiere pasar una lista de session atributos se lo pasa con llaves, ej:
//@SessionAttributes({"atributoUno, atributoDos, etc.."})
@Controller
@SessionAttributes("resultado")
public class IndexController {
	//necesitamos un metodo que acepte una peticion HTTP y nos devuelva un nombre logico
	//para que el dispatcherservlet se encargue del resto
	//Con RequestMapping va a obtener y decirle al meto que atienda la peticion 
	
	/**************************/
//	Con httpsession, uno puede hacer persistir atributos en cualquier controlador
//  O Usando una forma mas Elegante: @SessionAttributes("algun_atributo_que_se_quier_persistir")
	
//	@RequestMapping("/")
//	public String showIndex(HttpSession session) {
//		session.setAttribute("resultado", "Resultados desde Model");
//		return "index";
//	}	
	
	
	@RequestMapping("/")
	public String showIndex(Model model) {
		model.addAttribute("resultado", "Resultado desde session");
		return "index";
	}
	
	//Para limpiar un atributo de session se realiza con sessionStatus usando el metodo
	@RequestMapping("/about")
	public String showAbout(SessionStatus sessionStatus){
		sessionStatus.setComplete();
		return "about";
	}
	
//	@RequestMapping("/admin")
//	public String showAdmin(Model model){
//		model.addAttribute("mensaje", "mensaje desde el Model");
//		return "admin";
//	}
}
