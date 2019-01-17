/*
 * AjaxDemo.java
 *
 * Created on 20 de febrero de 2006, 01:44 PM
 */

package com.cazucito.demo.ajax;

import java.io.*;
import java.net.*;
import javax.servlet.*;
import javax.servlet.http.*;
/**
 *
 * @author pedro.cz@cazucito.com
 * @version
 */
public class AjaxDemo extends HttpServlet {
    /**
     * Salida formateada como texto/xml, la salida debe ser un documento XML bien
     * formado.
     * @param request Objeto que modela la petición
     * @param response Objeto que modela la respuesta
     **/
    protected void processRequestXml(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        StringBuffer sb = new StringBuffer();
        sb.append("<?xml version=\"1.0\" ?>");
        sb.append("<mensaje>");
        sb.append("<tipo>"+request.getParameter("tipo")+"</tipo>");
        sb.append("<param>"+request.getParameter("param")+"</param>");
        sb.append("</mensaje>");
        out.println(sb.toString());
        //System.out.println(sb.toString());
        out.close();
    }
    /**
     * Salida formateada como texto/plano, noten que se puede enviar etiquetas
     * de HTML, para asi darle formato al texto de salida
     * @param request Objeto que modela la petición
     * @param response Objeto que modela la respuesta
     **/
    protected void processRequestTxt(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        StringBuffer sb = new StringBuffer();
        sb.append("<strong>El formato de salida solicitado es: ");
        sb.append("<em>[tipo: "+request.getParameter("tipo")+" / ");
        sb.append("param: "+request.getParameter("param")+"]</em></strong>");               
        out.println(sb.toString());
        out.close();
        //System.out.println(sb.toString());         
    }
    /**
     * Salida formateada como texto/plano, noten que se puede enviar etiquetas
     * de HTML, para asi darle formato al texto de salida
     **/
    protected void processRequestError(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();
        StringBuffer sb = new StringBuffer();
        sb.append("<font color='red'>El formato de salida solicitado es incorrecto: ");
        sb.append("[tipo: "+request.getParameter("tipo")+"]</font>");
        out.println(sb.toString());
        out.close();
    }
    /** Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String tipoSalida=request.getParameter("tipo");
        if(tipoSalida!=null) {
            if(tipoSalida.equalsIgnoreCase("xml")) {
                processRequestXml(request, response);
            }else if(tipoSalida.equalsIgnoreCase("txt")) {
                processRequestTxt(request, response);
            } else {
                processRequestError(request, response);
            }
        } else {
            processRequestError(request, response);
        }
    }
    
    /** Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        doGet(request, response);
    }
    
}
