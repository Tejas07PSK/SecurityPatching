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

package org.scp.app.datahandling;

import org.scp.app.pojos.ApprovalPending;
import org.scp.app.pojos.HostDetails;
import org.scp.app.pojos.UserDetails;
import java.util.List;
import org.json.simple.JSONObject;

public class ObjectBuilder 
{

           private static ObjectBuilder obj = null; private Object oj = null;
           private final JSONObject jsobj;

           private ObjectBuilder () { oj = new Object (); jsobj = new JSONObject (); }

           public static ObjectBuilder getObjectBuilder () {

                   if ( obj == null ) { obj = new ObjectBuilder (); }
                   return ( obj );

           }

           public Object setNgetObject ( String cls ) {

                    switch ( cls ) {

                            case "UserDetails" : oj = new UserDetails (); break;
                            case "ApprovalPending" : oj = new ApprovalPending (); break;
                            case "HostDetails" : oj = new HostDetails (); break;
                            default : oj = null; break;

                    }
                    return ( oj );

           }

           public String createJsonResponse ( Object O, int cse, String ... arr ) {

                    jsobj.clear();
                    switch ( arr[0] ) {

                        case "UserDetails" : switch ( arr[1] ) {

                                                case "Insert" : if ( cse == 0 ) {

                                                                       jsobj.put( "STATUS", "Insertion Successful!!" );
                                                                       jsobj.put( "STATUS_CODE", "1" );
                                                                       JSONObject temp = new JSONObject ();
                                                                       temp.put( "USER_ID", ((UserDetails)O).getUsr_id() );
                                                                       temp.put( "ROLE", ((UserDetails)O).getRole() );
                                                                       temp.put( "FIRST_NAME", ((UserDetails)O).getFirstname() );
                                                                       temp.put( "LAST_NAME", ((UserDetails)O).getLastname() );
                                                                       temp.put( "EMAIL_ID", ((UserDetails)O).getEmail_id() );
                                                                       temp.put( "MOB_NO", ((UserDetails)O).getMob_no() );
                                                                       temp.put( "PASSWORD", ((UserDetails)O).getPass() );
                                                                       jsobj.put( "RESPONSE", temp );

                                                                }
                                                                if ( cse == -2 ) {

                                                                       jsobj.put( "STATUS", "Insertion Failed!!" );
                                                                       jsobj.put( "STATUS_CODE", "-1" );
                                                                       jsobj.put( "RESPONSE", "Email Already Registered!!" );

                                                                }
                                                                if ( cse == -3 ) {

                                                                       jsobj.put( "STATUS", "Insertion Failed!!" );
                                                                       jsobj.put( "STATUS_CODE", "-1" );
                                                                       jsobj.put( "RESPONSE", "Password Already Exists!!" );

                                                                }
                                                                if ( cse == -4 ) {

                                                                       jsobj.put( "STATUS", "Insertion Failed!!" );
                                                                       jsobj.put( "STATUS_CODE", "-1" );
                                                                       jsobj.put( "RESPONSE", "Phone Number Already Registered!!" );

                                                                }
                                                                if ( cse == -5 ) {

                                                                       jsobj.put( "STATUS", "Insertion Failed!!" );
                                                                       jsobj.put( "STATUS_CODE", "-1" );
                                                                       jsobj.put( "RESPONSE", "Invalid Secure Key For Manager Registration!!" );

                                                                }
                                                                if ( cse == -1 ) {

                                                                       jsobj.put( "STATUS", "Insertion Failed!!" );
                                                                       jsobj.put( "STATUS_CODE", "-1" );
                                                                       jsobj.put( "RESPONSE", "Internal Hibernate Exception!!" );

                                                                }
                                                                break;
                                                case "Retrieve" : if ( cse == 0 ) {

                                                                       jsobj.put( "STATUS", "Retrieval Successful!!" );
                                                                       jsobj.put( "STATUS_CODE", "1" );
                                                                       JSONObject temp = new JSONObject ();
                                                                       temp.put( "USER_SLNO", String.valueOf(((UserDetails)(((List <Object>)(O)).get( 0 ))).getUsr_slno()) );
                                                                       temp.put( "USER_ID", ((UserDetails)(((List <Object>)(O)).get( 0 ))).getUsr_id() );
                                                                       temp.put( "USER_ROLE", ((UserDetails)(((List <Object>)(O)).get( 0 ))).getRole() );
                                                                       temp.put( "FIRST_NAME", ((UserDetails)(((List <Object>)(O)).get( 0 ))).getFirstname() );
                                                                       temp.put( "LAST_NAME", ((UserDetails)(((List <Object>)(O)).get( 0 ))).getLastname() );
                                                                       temp.put( "EMAIL_ID", ((UserDetails)(((List <Object>)(O)).get( 0 ))).getEmail_id() );
                                                                       temp.put( "MOB_NO", ((UserDetails)(((List <Object>)(O)).get( 0 ))).getMob_no() );
                                                                       temp.put( "PASSWORD", ((UserDetails)(((List <Object>)(O)).get( 0 ))).getPass() );
                                                                       temp.put( "LIST", (String)(((List <Object>)(O)).get( 1 )) );
                                                                       temp.put( "PEND_STAT", (String)(((List <Object>)(O)).get( 2 )) );
                                                                       jsobj.put( "RESPONSE", temp );

                                                                  }
                                                                  if ( cse == -2 ) {

                                                                       jsobj.put( "STATUS", "Retrieval Failed!!" );
                                                                       jsobj.put( "STATUS_CODE", "-1" );
                                                                       jsobj.put( "RESPONSE", "Incorrect UserId Or Password!!" );

                                                                  }
                                                                  if ( cse == -1 ) {

                                                                       jsobj.put( "STATUS", "Retrieval Failed!!" );
                                                                       jsobj.put( "STATUS_CODE", "-1" );
                                                                       jsobj.put( "RESPONSE", "Internal Hibernate Exception!!" );

                                                                  }
                                                                  break;
                                                default : jsobj.put( "STATUS", "ERROR!!" );
                                                          jsobj.put( "STATUS_CODE", "-1" );
                                                          jsobj.put( "RESPONSE", "Unknown ERROR!!" );
                                                          break;

                                             }
                                             break;
                        case "ApprovalPending" : switch ( arr[1] ) {

                                                        case "Delete" : if ( cse == 0 ) {

                                                                                jsobj.put( "STATUS", "Removal Successful!!" );
                                                                                jsobj.put( "STATUS_CODE", "1" );
                                                                                jsobj.put( "RESPONSE", "User approved!!" );

                                                                        }
                                                                        if ( cse == -1 ) {

                                                                                jsobj.put( "STATUS", "Removal Failed!!" );
                                                                                jsobj.put( "STATUS_CODE", "-1" );
                                                                                jsobj.put( "RESPONSE", "Internal Hibernate Exception!!" );

                                                                        }
                                                                        break;
                                                        default : jsobj.put( "STATUS", "ERROR!!" );
                                                                  jsobj.put( "STATUS_CODE", "-1" );
                                                                  jsobj.put( "RESPONSE", "Unknown ERROR!!" );
                                                                  break;

                                                  }
                                                  break;
                        case "HostDetails" : switch ( arr[1] ) {

                                                        case "Insert" : if ( cse == 0 ) {

                                                                                jsobj.put( "STATUS", "Insertion Successful!!" );
                                                                                jsobj.put( "STATUS_CODE", "1" );
                                                                                JSONObject temp = new JSONObject ();
                                                                                temp.put( "HOSTNAME", ((HostDetails)O).getH_name() );
                                                                                temp.put( "PATCHNAME", ((HostDetails)O).getH_patch() );
                                                                                temp.put( "HOST_TYPE", ((HostDetails)O).getH_type() );
                                                                                temp.put( "HOST_LOC", ((HostDetails)O).getH_loc() );
                                                                                jsobj.put( "RESPONSE", temp );

                                                                        }
                                                                        if ( cse == -2 ) {

                                                                                jsobj.put( "STATUS", "Insertion Failed!!" );
                                                                                jsobj.put( "STATUS_CODE", "-1" );
                                                                                jsobj.put( "RESPONSE", "Hostname already exists!!" );

                                                                        }
                                                                        if ( cse == -1 ) {

                                                                                jsobj.put( "STATUS", "Insertion Failed!!" );
                                                                                jsobj.put( "STATUS_CODE", "-1" );
                                                                                jsobj.put( "RESPONSE", "Internal Hibernate Exception!!" );

                                                                        }
                                                                        break;
                                                        case "Delete" : if ( cse == 0 ) {

                                                                                jsobj.put( "STATUS", "Removal Successful!!" );
                                                                                jsobj.put( "STATUS_CODE", "1" );
                                                                                jsobj.put( "RESPONSE", "Host Removed!!" );

                                                                        }
                                                                        if ( cse == -1 ) {

                                                                                jsobj.put( "STATUS", "Removal Failed!!" );
                                                                                jsobj.put( "STATUS_CODE", "-1" );
                                                                                jsobj.put( "RESPONSE", "Internal Hibernate Exception!!" );

                                                                        }
                                                                        break;
                                                        default : jsobj.put( "STATUS", "ERROR!!" );
                                                                  jsobj.put( "STATUS_CODE", "-1" );
                                                                  jsobj.put( "RESPONSE", "Unknown ERROR!!" );
                                                                  break;

                                             }
                                             break;
                        default : jsobj.put( "STATUS", "ERROR!!" );
                                  jsobj.put( "STATUS_CODE", "-1" );
                                  jsobj.put( "RESPONSE", "Unknown ERROR!!" );
                                  break;

                    }
                    return ( jsobj.toJSONString() );

           }

           public static void flushObjectBuilder () { obj = null; }

}