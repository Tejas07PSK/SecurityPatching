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
import org.scp.app.pojos.UserDetails;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.query.Query;
import org.hibernate.SessionFactory;
import org.hibernate.Session;

public final class DbopUsrDet extends Logic implements Crud, Serializable
{

        private static final long serialVersionUID = 1L;

        private static SessionFactory sf; private static ObjectBuilder obb;

        public DbopUsrDet () { super (); sf = Logic.getSf(); obb = Logic.getOb(); }

        @ Override
        public synchronized String insert ( String ... vals ) {

                Object O = DbopUsrDet.assignFields( vals ); int chk = 0; Session s = null;
                try {

                        s = sf.openSession(); s.beginTransaction();
                        if ( (s.getNamedQuery( "findIfEmailExists" ).setParameter( "ent_eml", vals[2] ).getResultList()).isEmpty() ) {

                             if ( (s.getNamedQuery( "findIfPasswordExists" ).setParameter( "ent_pass", vals[5] ).getResultList()).isEmpty() ) {

                                   if ( (s.getNamedQuery( "findIfPhoneNoExists" ).setParameter( "ent_mobno", vals[3] ).getResultList()).isEmpty() ) {

                                       if ( (((UserDetails)(O)).getRole()).equals( "MANAGER" ) && !((vals[6]).equals( "123456" )) ) { System.out.println( "err4" ); chk = -5; }
                                       else { s.saveOrUpdate( O ); }

                                   }
                                   else { System.out.println( "err1" ); chk = -4; }

                             }
                             else { System.out.println( "err2" ); chk = -3; }

                        } else { System.out.println( "err3" ); chk = -2; }
                        s.getTransaction().commit();
                        if ( ( chk == 0 ) && ( !((((UserDetails)(O)).getRole()).equals("MANAGER")) ) ) { chk = (new DbopApvPend ()).insert( ((UserDetails)(O)).getUsr_id() ); }

                } catch ( Exception e ) {

                        chk = -1;
                        System.out.println( "HibernateException Occured!!" + e );
                        e.printStackTrace();

                }
                finally { if ( s != null ) { s.clear(); s.close(); } }
                return ( ( chk < 0 ? obb.createJsonResponse( null, chk, "UserDetails", "Insert" ) : obb.createJsonResponse( O, chk, "UserDetails", "Insert" ) ) );

        }
        
        @Override
        public synchronized String retrieve ( String ... vals )
        {

                List <Object> lst = new ArrayList <> (); int chk = 0; Session s = null; String resp = null;
                try {

                        s = sf.openSession(); s.beginTransaction();
                        Query qry = s.createQuery( "from UserDetails ud where ud.usr_id=:ent_uid and ud.pass=:ent_pass" );
                        qry.setParameter( "ent_uid", vals[0] ); qry.setParameter( "ent_pass", vals[1] );
                        lst = qry.getResultList();
                        chk = ( lst.isEmpty() ? -2 : 0 );
                        s.getTransaction().commit();
                        if ( chk == 0 ) {

                            if ( (((UserDetails)(lst.get( 0 ))).getRole()).equals( "MANAGER" ) ) {

                                    resp = (new DbopApvPend ()).retrieveAll();
                                    chk = ( resp == null ? -1 : 0 );
                                    if ( chk == 0 ) { lst.add( resp ); lst.add( "" ); }

                            } else {

                                    lst.add( "[]" );
                                    s = sf.openSession(); s.beginTransaction();
                                    if ( (s.getNamedQuery( "checkifuserispending" ).setParameter( "ent_uid", vals[0] ).getResultList()).isEmpty() ) {

                                        lst.add( "" );

                                    } else { lst.add( "1" ); }
                                    s.getTransaction().commit();

                            }

                        }

                } catch (Exception e) {

                        chk = -1;
                        System.out.println( "HibernateException Occured!!" + e );
                        e.printStackTrace();

                }
                finally { if ( s != null ) { s.clear(); s.close(); } }
                return ( ( chk < 0 ? obb.createJsonResponse( null, chk, "UserDetails", "Retrieve" ) : obb.createJsonResponse( lst, chk, "UserDetails", "Retrieve" ) ) );

        }

        private synchronized static Object assignFields ( String ... vals ) {

                  UserDetails ud = (UserDetails)(obb.setNgetObject( "UserDetails" ));
                  ud.setFirstname( vals[0] );
                  ud.setLastname( vals[1] );
                  ud.setUsr_id( (String.valueOf( vals[0].charAt( 0 ) ) + String.valueOf( vals[1].charAt( 0 ) )) );
                  ud.setEmail_id( vals[2] );
                  ud.setMob_no( vals[3] );
                  ud.setRole( vals[4] );
                  ud.setPass( vals[5] );
                  return ( ud );

        }

}
