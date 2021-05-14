
// Dominios de llamada a la API
var dominioLocal = "http://localhost:8080/";
// Colores
var rojo = '#F70958';
var azul = '#098BF7';
var azulfuerte = '#172ED3';
var amarillo = '#D7CC1C';
var negro = '#000000';
// Arrays
var arrDias = ['Lunes','Martes','Miércoles','Jueves','Viernes','Sábado','Domingo'];
var arrMeses = ['Enero','Febrero','Marzo','Abril','Mayo','Junio',
                'Julio','Agosto','Septiembre','Octubre','Noviembre','Diciembre'];

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

  // Valores
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

  // Pintamos tres divisas
  obtenerDatosChartDivisa(function(result){  

    addDatosDivisa("libr", result, divisasChart);

      obtenerDatosChartDivisa(function(result){  

        addDatosDivisa("eur", result, divisasChart);

          obtenerDatosChartDivisa(function(result){  

            addDatosDivisa("suco", result, divisasChart);
          },"apiDivisasHist/divisashistBetweenFecs/suco/07032021/13032021");

      },"apiDivisasHist/divisashistBetweenFecs/eur/07032021/13032021");

  },"apiDivisasHist/divisashistBetweenFecs/libr/07032021/13032021");
  
  // Pintamos los valores mejores y peores
  obtenerDatosMaxMin(function(resultMin){    

    pintaDatosMin(resultMin);

    obtenerDatosMaxMin(function(resultMax){    

      pintaDatosMax(resultMax);
    },"apiValoresHist/valoreshistTopLowValor/2/10032021/11032021");
 
  },"apiValoresHist/valoreshistTopLowValor/1/10032021/11032021");

  // Cargamos los valores en la dropdown

  cargarValoresDropdown();

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
        //console.log("Valor: "+valor.nombre + " " + valor.idVAlor);
        let opt = document.createElement('option');
        opt.value = valor.idVAlor;
        opt.innerHTML = valor.nombre;
        dropdownValores.appendChild(opt);
    }
  },"apiValores/valores");
}

//************************************************/
// Evaluamos la fecha
//************************************************/
function cambiaFecha(){
  let miFecha = document.getElementById("fechaValor");
  alert(miFecha.value);
}

//************************************************/
// Evaluamos el valor
//************************************************/
function cambiaValor(){
  let myselect = document.getElementById("selectValor");
  alert(myselect.options[myselect.selectedIndex].value);
}

//************************************************/
// Evaluamos la periocidad
//************************************************/
function cambiaPeriodicidad(){
  let myselect = document.getElementById("selectPeriodicidad");
  alert(myselect.options[myselect.selectedIndex].value);
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
  let urlMaxMin = dominioLocal + parUrl;
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
// Llamada a la API para el chart de divisas
//************************************************/
function obtenerDatosChartDivisa(callback, parUrl){
  let xhr2 = new XMLHttpRequest();
  let url = dominioLocal + parUrl;
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
// Llamada a la API para obtener valores
//************************************************/
function obtenerDatosValores(callback, parUrl){
  let xhr4 = new XMLHttpRequest();
  let url = dominioLocal + parUrl;
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
        color=rojo;
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
