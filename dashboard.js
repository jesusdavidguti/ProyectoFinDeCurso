
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

  // Divisas
  var ctx = document.getElementById('divisasChart')
  var divisasChart = new Chart(ctx, {
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
  var ctx = document.getElementById('valoresChart')
  var valoresChart = new Chart(ctx, {
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
  obtenerDatosChart(function(result){  

    addDatosDivisa("libr", result, divisasChart);

      obtenerDatosChart(function(result){  

        addDatosDivisa("eur", result, divisasChart);

          obtenerDatosChart(function(result){  

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

})()

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
  xhr = new XMLHttpRequest();
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
  xhr3 = new XMLHttpRequest();
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
// Llamada a la API para el chart
//************************************************/
function obtenerDatosChart(callback, parUrl){
  xhr2 = new XMLHttpRequest();
  let url = dominioLocal + parUrl;
  xhr2.open("GET", url, true);
  xhr2.setRequestHeader("Content-type","application/json");
  xhr2.onreadystatechange = function () {
      
      if (xhr2.readyState == 4 && xhr2.status == 200){

          let jsonDivisa = JSON.parse(xhr2.responseText);
          let valor;
          let array_valor = new Array();
          for (valor of jsonDivisa){
              array_valor.push(valor.cotizacionUSdolar); 
          }

          return callback (array_valor);        
      }
  }
  xhr2.send(); 
}

//************************************************/
// Color en función de divisa
//************************************************/
function colorDivisa(paramDivisa) {

  let color = "";

  switch (paramDivisa) {
    case "eur":
        color = azul;
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
