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

import org.hibernate.cfg.Configuration;
import org.hibernate.SessionFactory;
import org.scp.app.datahandling.ObjectBuilder;
import java.io.Serializable;

public abstract class Logic implements Serializable
{

       private static final long serialVersionUID = 1L;

       private static SessionFactory sf = null; private static ObjectBuilder ob = null;

       protected Logic () {

           if ( sf == null ) {

               try{

                         System.setProperty( "hibernate.dialect.storage_engine", "innodb" );
                         sf = new Configuration().configure().buildSessionFactory();

                  } catch ( Throwable ex ) {

                         System.out.println( "Failed to create sessionFactory object." + ex );
                         ex.printStackTrace();

                  }

           }
           if ( ob == null ) {

                 try{

                         ob = ObjectBuilder.getObjectBuilder();

                 } catch ( Throwable ex ) {

                         System.out.println( "Cannot Instantiate Singleton Class ObjectBuilder." + ex );
                         ex.printStackTrace();

                 }

           }

       }
       
       protected static void resetSf () {

             sf.close();
             try{

                         sf = new Configuration().configure().buildSessionFactory();

                } catch ( Throwable ex ) {

                              System.out.println( "Failed to create sessionFactory object." + ex );
                              ex.printStackTrace();

                }

       }
       
       protected static void resetOb () {

             ObjectBuilder.flushObjectBuilder();
             try{

                         ob = ObjectBuilder.getObjectBuilder();

                } catch ( Throwable ex ) {

                         System.out.println( "Cannot Instantiate Singleton Class ObjectBuilder" + ex );
                         ex.printStackTrace();

                }

       }

       protected static void stopSf () { sf.close(); }

       protected static SessionFactory getSf () { return ( sf ); }

       protected static ObjectBuilder getOb () { return ( ob ); }

       protected static void stopOb () { ObjectBuilder.flushObjectBuilder(); }

}