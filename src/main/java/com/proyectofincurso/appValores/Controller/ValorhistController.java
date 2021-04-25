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

import com.proyectofincurso.appValores.Service.ValorService;
import com.proyectofincurso.appValores.Service.ValorhistService;
import com.proyectofincurso.appValores.entity.Valor;
import com.proyectofincurso.appValores.entity.Valorhist;
import com.proyectofincurso.appValores.entity.ValorhistID;

//Indiciamos que es un controlador rest así como la raíz de la URL que usaremos (http://localhost:8080/api/)
@RestController
@RequestMapping("/apiValoresHist")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.PUT,RequestMethod.POST}) // Evita problmas COR

public class ValorhistController {

	@Autowired
	private ValorhistService valorhistService;
	@Autowired
	private ValorService valorService;
	
	// PETICONES GET
	
	@GetMapping("/info")
	public Map<String, String> info() {
	    HashMap<String, String> map = new HashMap<>();
	    map.put("Servicio", "valorhistCRUD");
	    map.put("Versión", "1.0");
	    map.put("Descripción", "CRUD para el mantenimiento de la entidad valorhist.");
	    map.put("Observaciones", "Trabajo fin de curso DAW2.");	    
	    return map;
	}
	
	@GetMapping("/valoreshist")
    public List<Valorhist> findAll(){

        return valorhistService.findAll();
    }
	
	@GetMapping("/valoreshist/{valorHistId}/{fecha}")
    public Valorhist getValor(@PathVariable int valorHistId, @PathVariable String fecha){
		
        Valorhist valorhist = valorhistService.findById(valorHistId, fecha);

        if(valorhist == null) {
            throw new RuntimeException("Valor histórico desconocido -"+valorHistId);
        }

        return valorhist;
    }

	 // ...aquí podríamos ampliar nuestra funcionalidad añadiendo distintas búsquedas.
	 
	// PETICONES POST	 
	
	 @PostMapping("/valoreshist")
	 public Valorhist addValorHist(@RequestBody Valorhist valorHist) {
		 		 
        valorhistService.save(valorHist);
        
        return valorHist;	 
	 }
	 
	 @PostMapping("/loadFecdesdeFechasta/{fecDesde}/{fecHasta}")
	 public Map<String, String> loadFecdesdeFechasta(@PathVariable String fecDesde, @PathVariable String fecHasta)  throws ParseException {
		 		 
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
 		 
 		 System.out.println("fechaDede:" + fechaDesde);
 		 
		 inicio = sourceFormat.parse(fechaDesde);		 
		 fin = sourceFormat.parse(fechaHasta);
		 
		 Date actual = inicio;
		 
		 // Recuperamos todos los valores
		 List<Valor> listaValores;
		 listaValores = valorService.findAll();
		 
		 // Recorremos todos los valores
		 for (Valor valor: listaValores) {
			 
			 	// Para cada fecha, insertamos un registro por valor
			    while (actual.before(fin)) {

			        Calendar calendar = Calendar.getInstance();
			        calendar.setTime(actual);
			        // Generamos un valor aleatorio
			        random = new Random().nextDouble();
			        result = start + (random * (end - start));			        
			        result = Double.valueOf(df.format(result));
			        
			        ValorhistID valorhistID = new ValorhistID(valor,actual);	// Objeto clave
				 	valorhistService.save(new Valorhist(valorhistID,result));	// Objeto Valor
			        
				 	// Avanzamos un día
			        calendar.add(Calendar.DATE, 1);
			        actual = calendar.getTime();					        			        
			    }			    
			    actual = inicio;
		 }
		 
		 HashMap<String, String> map = new HashMap<>();
		 map.put("Tabla:", "valorhist");
		 map.put("Carga:", "Entre " + fechaDesde + " y " + fechaHasta);
		 
		 return map;
	 }
	 
	 @PostMapping("/load")
	 public Map<String, String> loadValorHist() {
		 
		 	java.util.Date fecha = new Date();
		 			 		
		 	fecha.setDate(1);
		 	ValorhistID valorhistID1 = new ValorhistID(valorService.findById(1),fecha);
		 	Valorhist valorHist1 = new Valorhist(valorhistID1,1.24); 
		 	valorhistService.save(valorHist1);
		 		 
		 	fecha.setDate(2);		 			 			 	
		 	ValorhistID valorhistID2 = new ValorhistID(valorService.findById(1),fecha);
		 	Valorhist valorHist2 = new Valorhist(valorhistID2,1.1); 
		 	valorhistService.save(valorHist2);
		 			 	
		 	fecha.setDate(3);		 			 			 	
		 	ValorhistID valorhistID3 = new ValorhistID(valorService.findById(1),fecha);
		 	Valorhist valorHist3 = new Valorhist(valorhistID3,2.1); 
		 	valorhistService.save(valorHist3);

		 	fecha.setDate(4);		 			 			 	
		 	ValorhistID valorhistID4 = new ValorhistID(valorService.findById(1),fecha);
		 	Valorhist valorHist4 = new Valorhist(valorhistID4,1.5); 
		 	valorhistService.save(valorHist4);
		 	
		 	fecha.setDate(5);		 			 			 	
		 	ValorhistID valorhistID5 = new ValorhistID(valorService.findById(1),fecha);
		 	Valorhist valorHist5 = new Valorhist(valorhistID5,1.92); 
		 	valorhistService.save(valorHist5);
			        
	        HashMap<String, String> map = new HashMap<>();
		    map.put("Tabla:", "valorhist");
		    map.put("Carga:", "Finalizada");	    
		    return map;
	 }	 
	
	// PETICONES PUT
	 
	 @PutMapping("/valoreshist")
	 public Valorhist updateValorHist(@RequestBody Valorhist valorhist) {

	        valorhistService.save(valorhist);

	        return valorhist;
	 }

	// PETICONES DELETE
	 
	 @DeleteMapping("valores/{valorHistId}/{fecha}")
	 public String deleteValorHist(@PathVariable int valorHistId, @PathVariable String fecha) {

	        Valorhist valorHist = valorhistService.findById(valorHistId, fecha);

	        if(valorHist == null) {
	            throw new RuntimeException("Valor histórico desconocido id:"+valorHistId);
	        }

	        valorhistService.deleteById(valorHistId,fecha);

	        return "Valor histórico borrado con id - "+valorHistId + " y fecha "+fecha;
	  }	 	 
}
