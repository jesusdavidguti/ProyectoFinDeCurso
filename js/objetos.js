//************************************************/
// Objeto Url para montar la cadena 
// url de las APIS
//************************************************/

class montaUrl{

    // Constructor
    constructor() {
        this.rutaLocal = "http://localhost:8080/";
        this.ruta = this.rutaLocal;
        //console.log("this.ruta:"+this.ruta);
    }

    // Métodos SET

    setRutaLocal() {
      this.ruta = this.rutaLocal;
    }

    setRutaGlobal() {
      this.ruta = this.rutaGlobal;
    }

    // Divisas

    getDivisashistBetweenFecs (paramIdDivisa,paramFecDesde, paramFecHasta){    
        return (this.ruta+"apiDivisasHist/divisashistBetweenFecs/"+paramIdDivisa+"/"
                                                                  +paramFecDesde+"/"
                                                                  +paramFecHasta);
    }

    // Valores 

    getValores(){
        return (this.ruta+"apiValores/valores");
    }

    getValor(paramIdValor){
        return (this.ruta+"apiValores/valores/"+paramIdValor);
    }

    getValoreshistBetweenFecs (paramIdValor,paramFecDesde, paramFecHasta){    
        return (this.ruta+"apiValoresHist/valoreshistBetweenFecs/"+paramIdValor+"/"
                                                                  +paramFecDesde+"/"
                                                                  +paramFecHasta);
    }

    getValoreshistTopValor(paramFecDesde, paramFecHasta){
        return (this.ruta+"apiValoresHist/valoreshistTopLowValor/2/"+paramFecDesde+"/"
                                                                    +paramFecHasta);
    }

    getValoreshistLowValor(paramFecDesde, paramFecHasta){
        return (this.ruta+"apiValoresHist/valoreshistTopLowValor/1/"+paramFecDesde+"/"
                                                                    +paramFecHasta);
    }


}
