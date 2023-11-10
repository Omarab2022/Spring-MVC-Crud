package ma.ensa.hopital.web;


import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import ma.ensa.hopital.entities.Patient;
import ma.ensa.hopital.repository.Patientrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@AllArgsConstructor //injection via constructor

public class PatientController {

	@Autowired
	private Patientrepository patientrepository ;




	//comme je vais faire /index il va retourner la page patients.html
	@GetMapping({"/index" , "/"})
	public String index(Model model , @RequestParam(name = "page" , defaultValue = "0") int p ,
	                                  @RequestParam(name = "size" , defaultValue = "8")int s ,
	                                 @RequestParam(name = "keyword" , defaultValue = "") String kw) {

		// Créez un objet Pageable pour spécifier les paramètres de pagination
		Pageable pageable = PageRequest.of(p, s); // Exemple : Première page avec 10 éléments par page

		//la list des patients
		Page<Patient> patientList = patientrepository.findByNomContains(kw , PageRequest.of(p,s));
		model.addAttribute("ListPatients", patientList);

		//get total pages for pagination
		model.addAttribute("pages" , new int[patientList.getTotalPages()]);
		//page courrante
		model.addAttribute("currentPage" , p);

		model.addAttribute("kw" , kw);

		return "patients";
	}

	@GetMapping("/delete")
	public String delete(Long id , String keyword , int page){

		patientrepository.deleteById(id);


		return "redirect:/index?page="+page+"&keyword="+keyword;
	}

	@GetMapping("/formpatient")
	public String form( Model model){

		model.addAttribute("patient" , new Patient());


		return "formpatient";
	}

	@PostMapping("/savepatient")
	public String save(@Valid Patient patient , BindingResult bindingResult){

		if (bindingResult.hasErrors()) {
			return "formpatient" ;
		}
		patientrepository.save(patient);

		return "redirect:/index?keyword="+patient.getNom() ; //redirige vers le new user
	}

	@GetMapping("/editpatient")
	public String editpatient( Model model , @RequestParam(name="id") Long id){

		Patient patient = patientrepository.findById(id).get(); // chercher et get patient

		model.addAttribute("patient" , patient);


		return "editpatient";
	}



}
