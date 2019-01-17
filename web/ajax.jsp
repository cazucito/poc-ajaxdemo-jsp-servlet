<script type="text/javascript" language="javascript">
<!--
    // Referencia a la petición
    var http_request = false;
    // Obtención y ejecución del request
    // esta es la función que se ejecuta al hacer clic en el enlace
    function makeRequest(_url) {
      if (window.XMLHttpRequest) { // Mozilla, Safari,, NOT MS IExplorer...
        http_request = new XMLHttpRequest();
        if (http_request.overrideMimeType) {
          http_request.overrideMimeType('text/xml');         
        }
      } else if (window.ActiveXObject) { // IE
        try {
          http_request = new ActiveXObject("Msxml2.XMLHTTP");
        } catch (e) {
          try {
            http_request = new ActiveXObject("Microsoft.XMLHTTP");
          } catch (e) {}
        }
      }
      // Si no se puedo instanciar
      if (!http_request) {
        alert('No se pudo crear una instancia de XMLHTTP');
        return false;
      }
      // se llama a la funcion que gestionará el resultado
      http_request.onreadystatechange = demo;
      // se abre el procesador
      http_request.open('GET', _url, true);
      //
      http_request.send(null);
    }
    /**
    * En este punto procesamos la información obtenida.
    **/
    function demo() {
      // obtenemos la referencia del widget para escribir en el
      infoAsincrona=document.getElementById('info');      
      if (http_request.readyState == 4) { // Control de estados del proceso
        if (http_request.status == 200) { // Estado exitoso
          //En este punto debemos obtener la respuesta del Servlet pero como un 
          // XML, para lo que utilizaremos las funciones del DOM
          var xmldoc = http_request.responseXML;
          var mensaje = xmldoc.getElementsByTagName('mensaje').item(0);
          var tipo = mensaje.getElementsByTagName('tipo').item(0);
          var param = mensaje.getElementsByTagName('param').item(0);          
          salida="El formato de salida solicitado es:";
          salida+="[tipo:"+tipo.firstChild.data+"/ param:"+param.firstChild.data+"]";
          // utilizamos la propiedad innerHTML para poner la respuesta del Servlet
          infoAsincrona.innerHTML=salida;
        } else { // Error en el proceso
          msjError='Problema con la petición.';
          alert(msjError);
          infoAsincrona.innerHTML=msjError;
        }
      }
    }
//-->
</script>
<html>
    <head><title>Demostración de AJAX y Servlet/JSP</title></head>
    <body>
        <h1>AJAX y Java (JSP/Servlet)</h1>
        Ejecución del Servlet solicitando una salida tipo:
        [<a href="javascript:makeRequest('ajax_demo?tipo=xml&param=mi_parametro')">XML</a>]     

        <div id="info" style="position:absolute; top:100px; left:20px; width:450px; heigth=300px; background-color:#fafafa;"></div>
        <br/><br/><br/><br/><br/><br/><hr/>[<a href="index.jsp" >inicio</a>]
    </body>
</html>