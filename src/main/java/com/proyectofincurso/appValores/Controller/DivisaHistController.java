package com.proyectofincurso.appValores.Controller;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

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

import com.proyectofincurso.appValores.Service.DivisaService;
import com.proyectofincurso.appValores.Service.DivisahistService;
import com.proyectofincurso.appValores.Service.DivisahistServiceImpl;
import com.proyectofincurso.appValores.entity.Divisahist;
import com.proyectofincurso.appValores.entity.DivisahistID;
import com.proyectofincurso.appValores.entity.Divisa;

//Indiciamos que es un controlador rest así como la raíz de la URL que usaremos (http://localhost:8080/api/)
@RestController
@RequestMapping("/apiDivisasHist")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.PUT,RequestMethod.POST}) // Evita problmas COR

public class DivisaHistController {

	//Inyectamos el servicio para poder hacer uso de el	
	@Autowired
	private DivisahistService divisahistService;
	@Autowired
	private DivisaService divisaService;
	
	@Bean 
	public DivisahistService divisahistService() { 
		return new DivisahistServiceImpl(); 
	}
	
	// PETICONES GET
	
	@GetMapping("/info")
	public Map<String, String> info() {
	    HashMap<String, String> map = new HashMap<>();
	    map.put("Servicio", "divisahistCRUD");
	    map.put("Versión", "1.0");
	    map.put("Descripción", "CRUD para el mantenimiento de la entidad divisahist.");
	    map.put("Observaciones", "Trabajo fin de curso DAW2.");	    
	    return map;
	}
	
	@GetMapping("/divisashist")
    public List<Divisahist> findAll(){

        return divisahistService.findAll();
    }
	
	@GetMapping("/divisashist/{divisahistId}/{fecha}")
    public Divisahist getDivisahist(@PathVariable String divisahistId, @PathVariable String fecha){
		
		try {
	        Divisahist divisahist = divisahistService.findById(divisahistId, fecha);	        
	        return divisahist;
		} catch (Exception e) {
			throw new RuntimeException("Error recuperación divisa histórica " + divisahistId + " " + fecha);
		}				
    }
	
	// PETICONES POST	 
	
	 @PostMapping("/divisashist")
	 public Divisahist addDivisahist(@RequestBody Divisahist divisahist) {
		 
	        //mercado.setCodMercado(0);

	        divisahistService.save(divisahist);

	        return divisahist;	 
	 }
	 
	 @PostMapping("/loadMes/{fecDesde}/{fecHasta}")
	 public Map<String, String> loadDivisaHistMes(@PathVariable String fecDesde, @PathVariable String fecHasta)  throws ParseException {
		 		 
		 double start = 1.5;
		 double end = 0.5;
		 double random, result;
		 
		 DateFormat sourceFormat = new SimpleDateFormat("dd/MM/yyyy");
		
		 DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
		 simbolos.setDecimalSeparator('.');
		 DecimalFormat df = new DecimalFormat("#.00",simbolos);
		 
		 Date inicio, fin;
 		 String fechaDesde = fecDesde.substring(0, 2)+"/"+fecDesde.substring(2, 4)+"/"+fecDesde.substring(4, 8);
 		 String fechaHasta = fecHasta.substring(0, 2)+"/"+fecHasta.substring(2, 4)+"/"+fecHasta.substring(4, 8);
 		  		 
		 inicio = sourceFormat.parse(fechaDesde);		 
		 fin = sourceFormat.parse(fechaHasta);
		 
		 Date fecActual = inicio;
		 
		 // Recuperamos todos los divisas
		 List<Divisa> listaDivisas;
		 listaDivisas = divisaService.findAll();
		 
		 // Recorremos todos las divisas
		 for (Divisa divisa: listaDivisas) {
			 
			 	// Para cada fecha, insertamos un registro por divisa
			    while (fecActual.before(fin)) {

			        Calendar calendar = Calendar.getInstance();
			        calendar.setTime(fecActual);
			        // Generamos un valor aleatorio
			        random = new Random().nextDouble();
			        result = start + (random * (end - start));			        
			        result = Double.valueOf(df.format(result));
			        
			        DivisahistID divisahistID = new DivisahistID(divisa,fecActual);	// Objeto clave
				 	divisahistService.save(new Divisahist(divisahistID,result));	// Objeto Valor
			        
				 	// Avanzamos un día
			        calendar.add(Calendar.DATE, 1);
			        fecActual = calendar.getTime();					        			        
			    }			    
			    fecActual = inicio;
		 }
		 
		 HashMap<String, String> map = new HashMap<>();
		 map.put("Tabla:", "valorhist");
		 map.put("Carga:", "Entre " + fechaDesde + " y " + fechaHasta);
		 
		 return map;
	 }	 
	 
	 @PostMapping("/load")
	 public Map<String, String> loadDivisahist() {
		 
		 	java.util.Date fecha = new Date();
	 		
		 	fecha.setDate(1);
		 	DivisahistID divisahistID1 = new DivisahistID(divisaService.findById("eur"),fecha);
		 	Divisahist divisahistLoad1 = new Divisahist(divisahistID1,1.19);		 			 	
	        divisahistService.save(divisahistLoad1);	      
	        
		 	fecha.setDate(2);
		 	DivisahistID divisahistID2 = new DivisahistID(divisaService.findById("eur"),fecha);
		 	Divisahist divisahistLoad2 = new Divisahist(divisahistID2,1.19);		 			 	
	        divisahistService.save(divisahistLoad2);	      

		 	fecha.setDate(3);
		 	DivisahistID divisahistID3 = new DivisahistID(divisaService.findById("eur"),fecha);
		 	Divisahist divisahistLoad3 = new Divisahist(divisahistID3,1.19);		 			 	
	        divisahistService.save(divisahistLoad3);	      

		 	fecha.setDate(4);
		 	DivisahistID divisahistID4 = new DivisahistID(divisaService.findById("eur"),fecha);
		 	Divisahist divisahistLoad4 = new Divisahist(divisahistID4,1.19);		 			 	
	        divisahistService.save(divisahistLoad4);	        
	        
		 	fecha.setDate(5);
		 	DivisahistID divisahistID5 = new DivisahistID(divisaService.findById("eur"),fecha);
		 	Divisahist divisahistLoad5 = new Divisahist(divisahistID5,1.19);		 			 	
	        divisahistService.save(divisahistLoad5);	        
	        
	        HashMap<String, String> map = new HashMap<>();
		    map.put("Tabla:", "Divisas histórico");
		    map.put("Carga:", "Finalizada");	    
		    return map;
	 }

	 
	// PETICONES PUT
	 
	 @PutMapping("/divisashist")
	 public Divisahist updateDivisahist(@RequestBody Divisahist divisahist) {

	        divisahistService.save(divisahist);

	        return divisahist;
	 }
	
	// PETICONES DELETE
	 
	 @DeleteMapping("divisashist/{divisahistId, fec}")
	 public String deleteDivisahist(@PathVariable String divisahistId, String fec) {
		 	        
        try {
	        Divisahist divisahist = divisahistService.findById(divisahistId, fec);	        
	        if(divisahist == null) {
	            throw new RuntimeException("Divisa histórica desconocida id:"+divisahistId + " fec: " + fec);
	        }
	        divisahistService.deleteById(divisahistId, fec);
	        return "Divisa histórica borrada con id: " + divisahistId + " fec: " + fec;
	        
		} catch (Exception e) {
			throw new RuntimeException("Error recuperación divisa histórica "+divisahistId +" "+fec);
		}					        
	 }		 
}
