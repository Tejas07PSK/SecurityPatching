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

import org.scp.app.datahandling.DbopApvPend;
import org.scp.app.datahandling.DbopUsrDet;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.scp.app.mailing.Email;
import java.io.File;

@ WebServlet ( description = "Core Servlet For Begining all Operations", urlPatterns = { "/UserDetailsServlet" } )
public final class UserDetailsServlet extends HttpServlet
{

         private static final long serialVersionUID = 1L;

         private static  InetAddress ip ; private static  String ipadd  ;

         @ Override
         public void init () {

             Email.buildEmail( (getServletContext().getRealPath("/WEB-INF")) + File.separator + "Credentials" );
             try{

	                System.setProperty( "java.net.preferIPv4Stack", "true" );
                    ip = InetAddress.getLocalHost();
	                ipadd = ip.getHostAddress();

	         } catch ( UnknownHostException e ) { e.printStackTrace(); }

	     }

         @ Override
	     protected synchronized void doPut ( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {

                    response.setContentType( "text/json" );
	                response.setCharacterEncoding( "UTF-8" );
	                response.setBufferSize( 8192 );
                    PrintWriter out = response.getWriter();
                    BufferedReader rd = new BufferedReader( new InputStreamReader( request.getInputStream() ) );
                    StringBuilder req = new StringBuilder();
                    String line = "";
                    DbopUsrDet ins = new DbopUsrDet ();
                    while ( ( line = rd.readLine() ) != null ) { req.append( line ); }
                    rd.close(); String result = "";
                    try {

                            JSONObject jo = (JSONObject)((new JSONParser ()).parse( req.toString() ));
                            result = (ins).insert( ((String)(jo.get( "USER_INPUT_FIRSTNAME" ))).toUpperCase(),
                                                   ((String)(jo.get( "USER_INPUT_LASTNAME" ))).toUpperCase(),
                                                   (String)(jo.get( "USER_INPUT_EMAILID" )),
                                                   (String)(jo.get( "USER_INPUT_MOBNO" )),
                                                   ((String)(jo.get( "USER_INPUT_ROLE" ))).toUpperCase(),
                                                   (String)(jo.get( "USER_INPUT_PASSWORD" )),
                                                   (String)(jo.get( "USER_INPUT_SECKEY" )) );
                            jo = ((JSONObject)((new JSONParser ()).parse( result )));
                            if ( (jo.get( "STATUS_CODE" )).equals( "1" ) ) {

                                jo = (JSONObject)(jo.get("RESPONSE"));
                                Email.sendUserRegEmail( 1, ((String)(jo.get( "USER_ID" ))),
                                                              ((String)(jo.get( "ROLE" ))),
                                                              (String)(jo.get( "FIRST_NAME" )),
                                                              (String)(jo.get( "LAST_NAME" )),
                                                              ((String)(jo.get( "EMAIL_ID" ))),
                                                              (String)(jo.get( "MOB_NO" )),
                                                              (String)(jo.get( "PASSWORD" )) );

                            }

                        } catch ( ParseException pe ) { System.out.println( pe ); result = "{\"STATUS\":\"Error!!\",\"RESPONSE\":\"Json Parse Error!!\"}"; }
                    out.print( result );
                    out.flush();
                    out.close();

         }

         @ Override
         protected synchronized void doPost ( HttpServletRequest request, HttpServletResponse response )throws ServletException, IOException {

                    request.setAttribute( "response_body", (new DbopUsrDet ()).retrieve( request.getParameter( "uid" ), request.getParameter( "upass" ) ) );
                    getServletContext().getRequestDispatcher( "/results.jsp" ).forward( request, response );

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
                    DbopApvPend remv = new DbopApvPend ();
                    while ( ( line = rd.readLine() ) != null ) { req.append( line ); }
                    rd.close(); String result = "";
                    try {

                            JSONObject jo = (JSONObject)((new JSONParser ()).parse( req.toString() ));
                            String to_eml = (String)(jo.get( "PEN_USR_EML" ));
                            result = (remv).remove( Long.parseLong( (String)(jo.get( "PEN_USR_SLNO" )) ) );
                            jo = ((JSONObject)((new JSONParser ()).parse( result )));
                            if ( (jo.get( "STATUS_CODE" )).equals( "1" ) ) { Email.sendUserRegEmail( 2, to_eml ); }

                    } catch ( ParseException pe ) { System.out.println( pe ); result = "{\"STATUS\":\"Error!!\",\"RESPONSE\":\"Json Parse Error!!\"}"; }
                    out.print( result );
                    out.flush();
                    out.close();

         }

}