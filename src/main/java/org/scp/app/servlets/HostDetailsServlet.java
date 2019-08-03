/*  -> Designed for testing and development purposes.
 *  -> Project to design a 'SecurityPatching' prototype.
 *  -> Development Phase -- Intermediate.
 *  -> Project Type -- Educational.
 *  -> Owner/Designer of code file :
 *             @ Name - Palash Sarkar.
 *             @ Email - palashsarkar0007@gmail.com.
 *  -> Copyright Norms - Every piece of code given below has been written by 'Palash Sarkar (Tj07)'©,
 *                       and he holds the rights to the file. Not meant to be
 *                       copied or tampered with, without prior permission from the author.
 *  -> Guide - Balaji Chinthakalaya.
 */

package org.scp.app.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.InetAddress;
import java.net.UnknownHostException;

import org.scp.app.datahandling.DbopHostDet;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

@ WebServlet ( description = "Servlet For Managing Servers", urlPatterns = { "/HostDetailsServlet" } )
public final class HostDetailsServlet extends HttpServlet
{

        private static final long serialVersionUID = 1L;

        private static  InetAddress ip ; private static  String ipadd  ;

        @ Override
        public void init () {

            try{

                    System.setProperty( "java.net.preferIPv4Stack", "true" );
                    ip = InetAddress.getLocalHost();
                    ipadd = ip.getHostAddress();

            } catch ( UnknownHostException e ) { e.printStackTrace(); }

        }

        @ Override
        protected synchronized void doPost ( HttpServletRequest request, HttpServletResponse response )throws ServletException, IOException {

                response.setContentType( "json" );
                response.setCharacterEncoding( "UTF-8" );
                response.setBufferSize( 8192 );
                PrintWriter out = response.getWriter();
                out.print( (new DbopHostDet()).insert( request.getParameter( "hname" ), request.getParameter( "pname" ), request.getParameter( "htyp" ), request.getParameter( "hloc" ) ) );
                out.flush();
                out.close();

        }

        @ Override
        protected synchronized void doDelete ( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {

                response.setContentType( "text/json" );
                response.setCharacterEncoding( "UTF-8" );
                response.setBufferSize( 8192 );
                PrintWriter out = response.getWriter();
                BufferedReader rd = new BufferedReader( new InputStreamReader( request.getInputStream() ) );
                StringBuilder req = new StringBuilder();
                String line = "";
                DbopHostDet remv = new DbopHostDet ();
                while ( ( line = rd.readLine() ) != null ) { req.append( line ); }
                rd.close(); String result = "";
                try {

                        JSONObject jo = (JSONObject)((new JSONParser ()).parse( req.toString() ));
                        result = (remv).remove( Long.parseLong( (String)(jo.get( "HOST_SLNO" )) ) );

                } catch ( ParseException pe ) { System.out.println( pe ); result = "{\"STATUS\":\"Error!!\",\"RESPONSE\":\"Json Parse Error!!\"}"; }
                out.print( result );
                out.flush();
                out.close();

        }

}
