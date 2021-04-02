package com.proyectofincurso.appValores.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

// Indiciamos que es un controlador rest así como la raíz de la URL que usaremos (http://localhost:8080/api/)
@RestController
@RequestMapping("/apiMercados")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.PUT,RequestMethod.POST}) // Evita problmas COR

public class MercadoController {

	//Inyectamos el servicio para poder hacer uso de el	
	@Autowired
	private PersonaService personaService;
		
	@Bean 
	public PersonaService personaService() { 
		return new PersonaServiceImpl(); 
	}
	 	
	// PETICONES GET
			
	@GetMapping("/info")
	public Map<String, String> info() {
	    HashMap<String, String> map = new HashMap<>();
	    map.put("Servicio", "personaCRUD");
	    map.put("Versión", "1.0");
	    map.put("Descripción", "CRUD para el mantenimiento de la entidad persona.");
	    map.put("Observaciones", "Tutorial para gestión de una sola tabla.");	    
	    return map;
	}
	
	@GetMapping("/personas")
    public List<Persona> findAll(){

        return personaService.findAll();
    }
	
	@GetMapping("/personas/{personaId}")
    public Persona getUser(@PathVariable int personaId){
        Persona persona = personaService.findById(personaId);

        if(persona == null) {
            throw new RuntimeException("Persona desconocida -"+personaId);
        }

        return persona;
    }

	 // ...aquí podríamos ampliar nuestra funcionalidad añadiendo búsquedas por nombre, fecha de nacimiento, etc.
	 
	 
	// PETICONES POST	 
	
	 @PostMapping("/personas")
	 public Persona addPersona(@RequestBody Persona persona) {
		 
	        persona.setId_persona(0);

	        personaService.save(persona);

	        return persona;	 
	 }
	 
	// PETICONES PUT
	 
	 @PutMapping("/personas")
	 public Persona updatePersona(@RequestBody Persona persona) {

	        personaService.save(persona);

	        return persona;
	    }
	 
	// PETICONES DELETE
	 
	 @DeleteMapping("personas/{personaId}")
	 public String deletePersona(@PathVariable int personaId) {

	        Persona persona = personaService.findById(personaId);

	        if(persona == null) {
	            throw new RuntimeException("Persona desconocida id:"+personaId);
	        }

	        personaService.deleteById(personaId);

	        return "Persona borrada con id - "+personaId;
	    }	 
	 
	 // ...inicialmente borramos por id, pero podríamos borrar también por apellido, fecha de nacimiento, etc.
}
