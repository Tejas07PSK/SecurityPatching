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

import org.scp.app.pojos.HostDetails;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.query.Query;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;

public final class DbopHostDet extends Logic implements Serializable
{

        private static final long serialVersionUID = 1L;

        private static SessionFactory sf; private static ObjectBuilder obb;

        public DbopHostDet () { super (); sf = Logic.getSf(); obb = Logic.getOb(); }

        public synchronized String insert ( String ... vals ) {

                Object O = DbopHostDet.assignFields( vals ); int chk = 0; Session s = null;
                try {

                        s = sf.openSession(); s.beginTransaction();
                        if ( (s.getNamedQuery( "checkifhostesists" ).setParameter( "hname", vals[0] ).getResultList()).isEmpty() ) {

                              s.saveOrUpdate( O );

                        } else { System.out.println( "err1" ); chk = -2; }
                        s.getTransaction().commit();

                } catch ( Exception e ) {

                    chk = -1; System.out.println( "HibernateException Occured!!" + e );
                    e.printStackTrace();

                }
                finally { if ( s != null ) { s.clear(); s.close(); } }
                return ( ( chk < 0 ? obb.createJsonResponse( null, chk, "HostDetails", "Insert" ) : obb.createJsonResponse( O, chk, "HostDetails", "Insert" ) ) );

        }

        protected synchronized String retrieveAll () {

                List <HostDetails> lst = new ArrayList <> (); Session s = null; String str = "[]";
                JSONArray ja = new JSONArray ();
                try {

                        s = sf.openSession(); s.beginTransaction();
                        Query qry = s.createQuery( "from HostDetails hd" );
                        lst = qry.getResultList();
                        if ( !(lst.isEmpty()) ) {

                                for ( HostDetails hd : lst ) {

                                        JSONObject tmp = new JSONObject ();
                                        tmp.put( "HOST_SLNO", String.valueOf( hd.getH_slno() ) );
                                        tmp.put( "HOST_NAME", hd.getH_name() );
                                        tmp.put( "HOST_PATCH", hd.getH_patch() );
                                        tmp.put( "HOST_TYP", hd.getH_type() );
                                        tmp.put( "HOST_LOC", hd.getH_loc() );
                                        ja.add( tmp );

                                }
                                str = ja.toJSONString();

                        }
                        s.getTransaction().commit();

                } catch ( Exception e ) {

                        str = null; System.out.println( "HibernateException Occured!!" + e );
                        e.printStackTrace();

                }
                finally { if ( s != null ) { s.clear(); s.close(); } }
                return ( str );

        }

        public synchronized String remove ( long hslno ) {

                Object O = null; Session s = null; int chk = 0;
                try {

                        s = sf.openSession(); s.beginTransaction();
                        Query qry = s.createQuery( "delete from HostDetails hd where hd.h_slno=:hslno" );
                        qry.setParameter( "hslno", hslno );
                        qry.executeUpdate();
                        s.getTransaction().commit();

                } catch ( Exception e ) {

                        chk = -1; System.out.println( "HibernateException Occured!!" + e );
                        e.printStackTrace();

                }
                finally { if ( s != null ) { s.clear(); s.close(); } }
                return ( obb.createJsonResponse( O, chk, "HostDetails", "Delete" ) );

        }

        private synchronized static Object assignFields ( String ... vals ) {

                HostDetails hd = (HostDetails)(obb.setNgetObject( "HostDetails" ));
                hd.setH_name( vals[0] );
                hd.setH_patch( vals[1] );
                hd.setH_type( vals[2] );
                hd.setH_loc( vals[3] );
                return ( hd );

        }

}
