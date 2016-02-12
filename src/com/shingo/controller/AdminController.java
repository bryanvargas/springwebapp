package com.shingo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.shingo.pojo.Admin;
import com.shingo.service.AdminService;

@Controller
public class AdminController {

	@Autowired
	private AdminService adminService;

	@RequestMapping("/admin")
	public String showSave(Model model, @ModelAttribute("resultado") String resultado) {
		// es la clave que le paso a admin.jsp
		List<Admin> admins = adminService.findAll();
		model.addAttribute("admin", new Admin());
		model.addAttribute("resultado", resultado);
		model.addAttribute("admins", admins);
		return "admin";
	}

	// @ModelAttribute nos va a leer un atributo del modelo con la clave
	// Redirect es una interfaz de spring
	// @RequestParam es cualquier parametro que venga en el ambito request,
	// ya sea un ipervinculo o ya sea en un formulario
	@RequestMapping(value = "/admin/save", method = RequestMethod.POST)
	public String handleAdmin(@ModelAttribute("admin") Admin adminForm, Model model, RedirectAttributes ra) {
		// @RequestParam("estado") String estado){

		adminService.saveOrUpdate(adminForm);
		ra.addFlashAttribute("resultado", "Cambios realizados con exito");

		// accion=activar?id=1 - esto es un requestparam
		// model.addAttribute("adminForm", adminForm);
		// un FashAtribute es un atributo del modelo que persiste en varias
		// peticiones aunque
		// se redirija a otro controlador a diferencia de addAtribute

		// System.out.println(adminForm);
		// System.out.println("Request Param: " + estado);
		// resultado sera un atributo del modelo que persistira aunque
		// la peticion se redirija a otra
		// return "index";
		return "redirect:/admin";
	}

	@RequestMapping(value = "/admin/{idAd}/update")
	public String showUpdate(Model model, @PathVariable("idAd") int id) {
		Admin admin = adminService.findById(id);
		model.addAttribute("admin", admin);
		return "admin";
	}

	@RequestMapping(value = "/admin/{idAd}/delete")
	public String delete(@PathVariable("idAd") int id, RedirectAttributes ra) {
		adminService.delete(id);
		ra.addFlashAttribute("resultado", "Cambios realizados con exito");
		return "redirect:/admin";
	}

}
