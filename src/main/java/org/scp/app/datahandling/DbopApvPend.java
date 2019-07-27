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

import org.scp.app.pojos.UserDetails;
import org.scp.app.pojos.ApprovalPending;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.query.Query;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;

public final class DbopApvPend extends Logic implements Serializable
{

        private static final long serialVersionUID = 1L;

        private static SessionFactory sf; private static ObjectBuilder obb;

        public DbopApvPend () { super (); sf = Logic.getSf(); obb = Logic.getOb(); }

        protected synchronized int insert ( String uid ) {

                Object O = null; UserDetails ud; Session s = null; int chk = 0;
                try {

                            s = sf.openSession(); s.beginTransaction();
                            ud = (UserDetails)(s.getNamedQuery( "selecusrfromusrid" ).setParameter( "ent_uid", uid ).getSingleResult());
                            O = DbopApvPend.assignFields( ud );
                            s.saveOrUpdate( O );
                            s.getTransaction().commit();

                } catch ( Exception e ) {

                            chk = -1;
                            System.out.println( "HibernateException Occured!!" + e );
                            e.printStackTrace();

                }
                finally { if ( s != null ) { s.clear(); s.close(); } }
                return ( chk );

        }

        protected synchronized String retrieveAll () {

            List <Object []> lst = new ArrayList <> (); UserDetails ud; Session s = null; String str = "[]";
            JSONArray ja = new JSONArray ();
            try {

                    s = sf.openSession(); s.beginTransaction();
                    Query qry = s.createQuery( "select ap.pn_slno, ap.ud.usr_id, ap.ud.firstname, ap.ud.lastname from ApprovalPending ap" );
                    lst = qry.getResultList();
                    if ( !(lst.isEmpty()) ) {

                            for (Object [] arr : lst) {

                                    JSONObject tmp = new JSONObject ();
                                    tmp.put( "PUSER_SLNO", String.valueOf(arr[0]) );
                                    tmp.put( "PUSER_ID", (String)arr[1] );
                                    tmp.put( "PUSER_FNAME", (String)arr[2] );
                                    tmp.put( "PUSER_LNAME", (String)arr[3] );
                                    ja.add( tmp );

                            }
                            str = ja.toJSONString();

                    }
                    s.getTransaction().commit();

            } catch ( Exception e ) {

                    str = null;
                    System.out.println( "HibernateException Occured!!" + e );
                    e.printStackTrace();

            }
            finally { if ( s != null ) { s.clear(); s.close(); } }
            return ( str );

        }

        public synchronized String remove ( long pslno ) {

                Object O = null; Session s = null; int chk = 0;
                try {

                        s = sf.openSession(); s.beginTransaction();
                        Query qry = s.createQuery( "delete from ApprovalPending ap where ap.pn_slno=:pslno" );
                        qry.setParameter( "pslno", pslno );
                        qry.executeUpdate();
                        s.getTransaction().commit();

                } catch ( Exception e ) {

                        chk = -1;
                        System.out.println( "HibernateException Occured!!" + e );
                        e.printStackTrace();

                }
                finally { if ( s != null ) { s.clear(); s.close(); } }
                return ( obb.createJsonResponse( O, chk, "ApprovalPending", "Delete" ) );

        }

        private synchronized static Object assignFields ( UserDetails ref_oj ) {

                ApprovalPending ap = (ApprovalPending)(obb.setNgetObject( "ApprovalPending" ));
                ap.setUd( ref_oj );
                return ( ap );

        }

}
