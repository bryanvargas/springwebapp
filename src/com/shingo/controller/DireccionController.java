package com.shingo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.shingo.pojo.Admin;
import com.shingo.pojo.Direccion;
import com.shingo.service.AdminService;
import com.shingo.service.DireccionService;

@Controller
@SessionAttributes("admin") // esto preserva cualquier atributo del modelo que
							// se le pase, durante todo el tiempo que sea
							// mecesarp
// que la sesion se terminada o se le indique mediante codigo
public class DireccionController {

	@Autowired
	private AdminService adminService;

	@Autowired
	public DireccionService direccionService;

	@RequestMapping("direccion/{idAd}")
	public String getAll(Model model, 
			@ModelAttribute("resultado") String resultado,
			@PathVariable("idAd") int idAd) {
		
		Admin admin = adminService.findById(idAd);
		model.addAttribute("admin", admin);
		
		model.addAttribute("resultado", resultado);
		// coloco una instacia vacia de direccion en el jsp de direccion
		// para posteriormente postrar la vistam
		model.addAttribute("direccion", new Direccion());
		model.addAttribute("direcciones", direccionService.findAll(idAd));
		return "direccion";
	}

	//con @ModelAttribute yo puedo obtener tanto como variables de session
	//como tambien todos los dato que se ingresen en los jsp
	@RequestMapping("/direccion/save")
	public String save(Model model, RedirectAttributes ra,
			@ModelAttribute("direccion") Direccion direccion,
			@ModelAttribute("admin") Admin admin) {
		direccionService.save(admin, direccion);
		ra.addFlashAttribute("resultado", "Cambios realizados con exito");
		return "redirect:/direccion/" + admin.getIdAd();
	}
	
	@RequestMapping("/direccion/{idDir}/delete")
	public String delete(@PathVariable("idDir") int idDir,
			RedirectAttributes ra, @ModelAttribute("admin") Admin admin){
		direccionService.delete(idDir);
		ra.addFlashAttribute("resultado", "Direccion eliminada con exito");
		return "redirect:/direccion/" + admin.getIdAd();
	}
}
