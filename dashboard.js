
var valoresChart;
// Dominios de llamada a la API
var dominioLocal = "http://localhost:8080/";
// Arrays
var arrDias = ['Lunes','Martes','Miércoles','Jueves','Viernes','Sábado','Domingo'];
var arrMeses = ['Enero','Febrero','Marzo','Abril','Mayo','Junio',
                'Julio','Agosto','Septiembre','Octubre','Noviembre','Diciembre'];
var arrValoresPintados = [];
// Objetos
var objetoUrl = new montaUrl();

/* globals Chart:false, feather:false */
(function () {
  'use strict'

  feather.replace()
  // Chart Divisas
  var ctxDivisas = document.getElementById('divisasChart')
  var divisasChart = new Chart(ctxDivisas, {
    type: 'line',
    data: {
 /*      labels: [
         'Lunes',
         'Martes',
         'Miércoles',
         'Jueves',
         'Viernes',
         'Sábado',
         'Domingo'
      ] */
/*       ,
      datasets: [{
        data: [],
        lineTension: 0,
        backgroundColor: 'transparent',
        borderColor: '#007bff',
        borderWidth: 4,
        pointBackgroundColor: '#007bff'
      }] */
    },
    options: {    
      scales: {
        yAxes: [{
          ticks: {
            min: 0,
            max: 3,
            stepSize: 0.2,
            display: true,
            beginAtZero: true,            
          }
        }]
      },
      legend: {
        display: true
      }
    }
  })

  // Chart valores
  var ctxValores = document.getElementById('valoresChart')
  var valoresChart = new Chart(ctxValores, {
    type: 'line',
    data: {      
     },
    options: {    
      scales: {
        yAxes: [{
          ticks: {
            min: 0,
            max: 3,
            stepSize: 0.2,
            display: true,
            beginAtZero: true,            
          }
        }]
      },
      legend: {
        display: true
      }
    }
  })

  // Pintamos tres divisas en la semana actual
  obtenerDatosChartDivisa(function(result){  

    addDatosDivisa("libr", result, divisasChart);

      obtenerDatosChartDivisa(function(result){  

        addDatosDivisa("eur", result, divisasChart);

          obtenerDatosChartDivisa(function(result){  

            addDatosDivisa("suco", result, divisasChart);
          },objetoUrl.getDivisashistBetweenFecs("suco",fechaHasta(3),fechaHasta(0)));          
    
      },objetoUrl.getDivisashistBetweenFecs("eur",fechaHasta(3),fechaHasta(0)));          

  },objetoUrl.getDivisashistBetweenFecs("libr",fechaHasta(3),fechaHasta(0)));            
  
  // Pintamos los valores mejores y peores
  obtenerDatosMaxMin(function(resultMin){    

    pintaDatosMin(resultMin);

    obtenerDatosMaxMin(function(resultMax){    

      pintaDatosMax(resultMax);
    },objetoUrl.getValoreshistTopValor(fechaHasta(3),fechaHasta(0)));    
 
  },objetoUrl.getValoreshistLowValor(fechaHasta(3),fechaHasta(0)));    

  // Cargamos los valores en la dropdown
  cargarValoresDropdown();

  setChartValores(valoresChart);
})()

//************************************************/
// Carga de valores en dropdown
//************************************************/
function cargarValoresDropdown(){
  let dropdownValores = document.getElementById("selectValor");
  // Llamada a la api
  obtenerDatosValores(function(resultValores){    
    let jsonValor = resultValores;
    let valor;
    for (valor of jsonValor){

        let opt = document.createElement('option');
        opt.value = valor.idVAlor;
        opt.innerHTML = valor.nombre;
        dropdownValores.appendChild(opt);
    }
  },objetoUrl.getValores());  
}

//************************************************/
// Evaluamos el valor
//************************************************/
function cambiaValor(){
  //let myselect = document.getElementById("selectValor");
  //alert(myselect.options[myselect.selectedIndex].value);
  procesoRecuperaValor();
}

//************************************************/
// Evaluamos la periocidad
//************************************************/
function cambiaPeriodicidad(){
  //let myselect = document.getElementById("selectPeriodicidad");
  //alert(myselect.options[myselect.selectedIndex].value);
  procesoRecuperaValor();  
}

//************************************************/
// Recuperamos los datos del valor en función
// de su id y de la period. (mensual, anual, etc)
//************************************************/
function procesoRecuperaValor(){

  let selectValor = document.getElementById("selectValor");
  let selectPeriodo = document.getElementById("selectPeriodicidad");
  let idValor = selectValor.options[selectValor.selectedIndex].value;
  let idPeriodo = selectPeriodo.options[selectPeriodo.selectedIndex].value;
  let idValorText = selectValor.options[selectValor.selectedIndex].innerText;
  let idPeriodoText = selectPeriodo.options[selectPeriodo.selectedIndex].innerText;

  if (idValorText != "Seleccione valor" && idPeriodoText != "Seleccione periodicidad"){

    // Comprobamos si ya lo hemos pintado antes.
    if (!arrValoresPintados.includes(idValor)){

      arrValoresPintados.push(idValor);      
      // Datos históricos del valor
      obtenerDatosHistChartValor(function(result){  
        // Datos del valor
        obtenerDatosValores(function(resultValor){    
          let jsonValor = resultValor;
          
          addDatosValor(jsonValor, result, valoresChart);    

        },objetoUrl.getValor(idValor));  
      
      },objetoUrl.getValoreshistBetweenFecs(idValor,fechaHasta(3),fechaHasta(0)));
    }
  }
}

//************************************************/
// Actualizamos datos de MEJOR cotización
//************************************************/
function pintaDatosMax(paramDatosMax){
  let nombre;

  for (let i=0; i<5; i++){
    nombre = JSON.stringify(paramDatosMax[i].valorNombre);
    nombre = nombre.replace(/['"]+/g, '');    
    document.getElementById("tablaValores").rows[i+1].cells[1].innerText = nombre;
    document.getElementById("tablaValores").rows[i+1].cells[2].innerText = JSON.stringify(paramDatosMax[i].cotizacionUSdolarHoy);
    document.getElementById("tablaValores").rows[i+1].cells[3].innerText = JSON.stringify(paramDatosMax[i].diferenciaCotizacion);
  }
}

//************************************************/
// Actualizamos datos de PEOR cotización
//************************************************/
function pintaDatosMin(paramDatosMin){
  let nombre;

  for (let i=0; i<5; i++){
    nombre = JSON.stringify(paramDatosMin[i].valorNombre);
    nombre = nombre.replace(/['"]+/g, '');
    document.getElementById("tablaValores").rows[i+1].cells[4].innerText = nombre;
    document.getElementById("tablaValores").rows[i+1].cells[5].innerText = JSON.stringify(paramDatosMin[i].cotizacionUSdolarHoy);
    document.getElementById("tablaValores").rows[i+1].cells[6].innerText = JSON.stringify(paramDatosMin[i].diferenciaCotizacion);
  }
}

//************************************************/
// Añadimos y actualizamos datos al chart valores
//************************************************/
function addDatosValor(paramValor, paramData, paramChart){

  paramChart.data.labels=arrDias;

  paramChart.data.datasets.push({      
      label: paramValor.nombre,
      lineTension: 0,
      backgroundColor: 'transparent',
      borderColor: colorDivisa(paramValor.divisa.codDivisa),
      borderWidth: 4,
      pointBackgroundColor: '#007bff',
      data: paramData
    });

    paramChart.update();
}

//************************************************/
// Añadimos y actualizamos datos al chart divisas
//************************************************/
function addDatosDivisa(paramLabel, paramData, paramChart){

    paramChart.data.labels=arrDias;

    obtenerNombreDivisa(function(nombreDiv){    

      paramChart.data.datasets.push({      
        label: nombreDiv,
        lineTension: 0,
        backgroundColor: 'transparent',
        borderColor: colorDivisa(paramLabel),
        borderWidth: 4,
        pointBackgroundColor: '#007bff',
        data: paramData
      });
  
      paramChart.update();
    },paramLabel);
}

//************************************************/
// Limpiamos el chart
//************************************************/
function removeData(chart) {
  chart.data.labels.pop();
  chart.data.datasets.forEach((dataset) => {
      dataset.data.pop();
  });
  chart.update();
}

//************************************************/
// Llamada a la API para mejores/peores valores
//************************************************/
function obtenerDatosMaxMin(callback, parUrl){
  let xhr = new XMLHttpRequest();
  //let urlMaxMin = dominioLocal + parUrl;
  let urlMaxMin = parUrl;  
  xhr.open("GET", urlMaxMin, true);
  xhr.setRequestHeader("Content-type","application/json");
  xhr.onreadystatechange = function () {
      if (xhr.readyState == 4 && xhr.status == 200){
          let jsonDivisa = JSON.parse(xhr.responseText);          
          return callback (jsonDivisa);        
      }
  }
  xhr.send(); 
}

//************************************************/
// Llamada a la API para obtener nombre divisa
//************************************************/
function obtenerNombreDivisa(callback, parDivisaNombre){
  let xhr3 = new XMLHttpRequest();
  let url = dominioLocal + "apiDivisas/divisas/" + parDivisaNombre;
  xhr3.open("GET", url, true);
  xhr3.setRequestHeader("Content-type","application/json");
  xhr3.onreadystatechange = function () {
      
      if (xhr3.readyState == 4 && xhr3.status == 200){

          let jsonDivisa = JSON.parse(xhr3.responseText);
          return callback (jsonDivisa.nombre);        
      }
  }
  xhr3.send(); 
}

//************************************************/
// Llamada a la API para obtener valor por id
//************************************************/
function obtenerValor(callback, parIdValor){
  let xhr6 = new XMLHttpRequest();
  let url = dominioLocal + "apiValores/valores/" + parIdValor;
  xhr6.open("GET", url, true);
  xhr6.setRequestHeader("Content-type","application/json");
  xhr6.onreadystatechange = function () {
      
      if (xhr6.readyState == 4 && xhr6.status == 200){

          let jsonValor = JSON.parse(xhr6.responseText);
          return callback (jsonValor);        
      }
  }
  xhr6.send();
}

//************************************************/
// Llamada a la API para el chart de divisas
//************************************************/
function obtenerDatosChartDivisa(callback, parUrl){
  let xhr2 = new XMLHttpRequest();
  //let url = dominioLocal + parUrl;
  let url = parUrl;  
  xhr2.open("GET", url, true);
  xhr2.setRequestHeader("Content-type","application/json");
  xhr2.onreadystatechange = function () {
      
      if (xhr2.readyState == 4 && xhr2.status == 200){

          let jsonDivisa = JSON.parse(xhr2.responseText);
          let divisa;
          let array_divisa = new Array();
          for (divisa of jsonDivisa){
              array_divisa.push(divisa.cotizacionUSdolar); 
          }
          return callback (array_divisa);        
      }
  }
  xhr2.send(); 
}

//************************************************/
// Llamada a la API para el chart de valores hist.
//************************************************/
function obtenerDatosHistChartValor(callback, parUrl){
  let xhr = new XMLHttpRequest();
  //let url = dominioLocal + parUrl;
  let url = parUrl;  
  xhr.open("GET", url, true);
  xhr.setRequestHeader("Content-type","application/json");
  xhr.onreadystatechange = function () {
      
      if (xhr.readyState == 4 && xhr.status == 200){

          let jsonValorHist = JSON.parse(xhr.responseText);
          let valorHist;
          let array_valorHist = new Array();
          for (valorHist of jsonValorHist){
              array_valorHist.push(valorHist.cotizacionUSdolar); 
          }
          return callback (array_valorHist);        
      }
  }
  xhr.send(); 
}

//************************************************/
// Llamada a la API para obtener valores
//************************************************/
function obtenerDatosValores(callback, parUrl){
  let xhr4 = new XMLHttpRequest();
  //let url = dominioLocal + parUrl;
  let url = parUrl;  
  xhr4.open("GET", url, true);
  xhr4.setRequestHeader("Content-type","application/json");
  xhr4.onreadystatechange = function () {
      
      if (xhr4.readyState == 4 && xhr4.status == 200){

          let jsonValor = JSON.parse(xhr4.responseText);
          return callback (jsonValor);        
      }
  }
  xhr4.send(); 
}

//************************************************/
// Color en función de divisa
//************************************************/
function colorDivisa(paramDivisa) {
  // Colores
  let rojo = '#F70958';
  let azul = '#098BF7';
  let azulfuerte = '#172ED3';
  let amarillo = '#D7CC1C';
  let negro = '#000000';
  let color = "";

  switch (paramDivisa) {
    case "eur":
        color=azul;
        break;
    case "usdo":
        color=rojo;
        break;    
    case "libr":
        color=rojo;
        break;    
    case "yen":
        color=amarillo;
        break;    
    case "bado":
        color=rojo;
        break;    
    case "neru":
        color=rojo;
        break;    
    case "rubl":
        color=azulfuerte;
        break;    
    case "sufr":
        color=rojo;
        break;    
    case "suco":
        color=amarillo;
        break;    
    default:
        color=negro;        
        break;
  }
  return color;
}

//************************************************/
// Devuelve la fecha "hasta" en función de la 
// periodicidad o la fecha de hoy.
//************************************************/

function fechaHasta(paramPeriodicidad){

  let dateHoy = new Date();
  let fechaCalculada = new Date();

  let fechaHoy = dateHoy;

  switch (paramPeriodicidad) {
      // 1 día
      case 1:
          fechaCalculada.setDate(fechaCalculada.getDate() - 1);
          break;  
      // 5 días
      case 2:
          fechaCalculada.setDate(fechaCalculada.getDate() - 5);
          break;    
      // 1 semana
      case 3:
          fechaCalculada.setDate(fechaCalculada.getDate() - 7);
          break;
      // Mensual
      case 4:
          fechaCalculada.setMonth(fechaCalculada.getMonth() - 1);
          break;
      // Semestral
      case 5:
          fechaCalculada.setMonth(fechaCalculada.getMonth() - 6);
          break;
      // Anual
      case 6:
          fechaCalculada.setMonth(fechaCalculada.getMonth() - 12);
          break;
      // Hoy
      default:
          fechaCalculada = fechaHoy
          //console.log("fechaCalculada default:"+fechaCalculada);          
          break;
    }
    return fechaddMMyyyy(fechaCalculada);
}    

//************************************************/
// Cambia el formato fecha a ddMMyyyy
//************************************************/
function fechaddMMyyyy(paramDate){

  let dia = (paramDate.getDate() > 9 ? paramDate.getDate() : "0"+paramDate.getDate());
  let mes = ((paramDate.getMonth()+1) > 9 ? (paramDate.getMonth()+1) : "0"+(paramDate.getMonth()+1));
  let ann = paramDate.getFullYear();

  return (dia + mes + ann);
}

//************************************************/
// Hacemos que el chart sea global
//************************************************/
function setChartValores(paramValoresChart){

  valoresChart = paramValoresChart;
}

