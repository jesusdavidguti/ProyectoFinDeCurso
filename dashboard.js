/* globals Chart:false, feather:false */

(function () {
  'use strict'

  feather.replace()

  // Graphs
  var ctx = document.getElementById('myChart')
  // eslint-disable-next-line no-unused-vars
  var myChart = new Chart(ctx, {
    type: 'line',
    data: {
      labels: [
        'Lunes',
        'Martes',
        'Miércoles',
        'Jueves',
        'Viernes',
        'Sábado',
        'Domingo'
      ],
      datasets: [{
        data: 
        [],
        lineTension: 0,
        backgroundColor: 'transparent',
        borderColor: '#007bff',
        borderWidth: 4,
        pointBackgroundColor: '#007bff'
      }]
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

  
  obtenerDatosChart(function(result){    
    addDatos("Libra", result, myChart);
  },"http://localhost:8080/apiDivisasHist/divisashistBetweenFecs/libr/07032021/13032021");
  
  // obtenerDatosChart(function(result){    
  //    addDatos("Euro", result, myChart);
  // },"http://localhost:8080/apiDivisasHist/divisashistBetweenFecs/eur/07032021/13032021");

  obtenerDatosMaxMin(function(result){    

    //pintaDatosMin(result);

 },"http://localhost:8080/apiValoresHist/valoreshistTopLowValor/1/10032021/11032021");

 obtenerDatosMaxMin(function(result){    

    pintaDatosMax(result);

},"http://localhost:8080/apiValoresHist/valoreshistTopLowValor/2/10032021/11032021");


})()

//************************************************/
// Añadimos los datos de MEJOR cotización
//************************************************/
function pintaDatosMax(paramDatos){

  for (let i=0; i<5; i++){
    document.getElementById("tablaValores").rows[i+1].cells[1].innerText = JSON.stringify(paramDatos[i].valorNombre);    
    document.getElementById("tablaValores").rows[i+1].cells[2].innerText = JSON.stringify(paramDatos[i].cotizacionUSdolarHoy);
    document.getElementById("tablaValores").rows[i+1].cells[3].innerText = JSON.stringify(paramDatos[i].diferenciaCotizacion);    
  }
}

//************************************************/
// Añadimos los datos de PEOR cotización
//************************************************/
function pintaDatosMin(paramDatos){

  for (let i=0; i<5; i++){
    document.getElementById("tablaValores").rows[i+1].cells[4].innerText = JSON.stringify(paramDatos[i].valorNombre);    
    document.getElementById("tablaValores").rows[i+1].cells[5].innerText = JSON.stringify(paramDatos[i].cotizacionUSdolarHoy);
    document.getElementById("tablaValores").rows[i+1].cells[6].innerText = JSON.stringify(paramDatos[i].diferenciaCotizacion);    
  }
}

//************************************************/
// Añadimos y actualizamos datos al chart
//************************************************/
function addDatos(paramDivisa, paramData, paramChart){
  //console.log("param: "+paramIndex);
  //paramChart.data.datasets[paramIndex].data = paramChart.data.datasets[paramIndex].data.concat(paramData);

  paramChart.data.datasets.push({
    label: paramDivisa,
    lineTension: 0,
    backgroundColor: 'transparent',
    borderColor: '#007bff',
    borderWidth: 4,
    pointBackgroundColor: '#007bff',
    data: paramData
  });

  paramChart.update();
}

//************************************************/
// Llamada a la API para los máx. y mín.
//************************************************/
function obtenerDatosMaxMin(callback, parUrl){
  xhr = new XMLHttpRequest();
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
// Llamada a la API para el chart
//************************************************/
function obtenerDatosChart(callback, parUrl){
  xhr = new XMLHttpRequest();
  let url = parUrl;
  xhr.open("GET", url, true);
  xhr.setRequestHeader("Content-type","application/json");
  xhr.onreadystatechange = function () {
      if (xhr.readyState == 4 && xhr.status == 200){

          let jsonDivisa = JSON.parse(xhr.responseText);
          let valor;
          let array_valor = new Array();
          for (valor of jsonDivisa){
              array_valor.push(valor.cotizacionUSdolar); 
          }          
          return callback (array_valor);        
      }
  }
  xhr.send(); 
}