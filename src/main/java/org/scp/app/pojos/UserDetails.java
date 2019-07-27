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

package org.scp.app.pojos;

import java.io.Serializable;
import java.util.Random;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@ NamedQueries ({

        @ NamedQuery ( name = "findIfEmailExists", query = "select ud.email_id from UserDetails ud where ud.email_id=:ent_eml" ),
        @ NamedQuery ( name = "findIfPasswordExists", query = "select ud.pass from UserDetails ud where ud.pass=:ent_pass" ),
        @ NamedQuery ( name = "findIfPhoneNoExists", query = "select ud.mob_no from UserDetails ud where ud.mob_no=:ent_mobno" ),
        @ NamedQuery ( name = "selecusrfromusrid", query = "from UserDetails ud where ud.usr_id=:ent_uid" )

    })  

@ Entity
@ Table ( name = "USER_DETAILS" )
public class UserDetails implements Serializable
{

        private static final long serialVersionUID = 1L;

        @ Id
        @ GeneratedValue ( strategy = GenerationType.SEQUENCE )
        @ Column ( name = "USER_SLNO", unique = true, nullable = false, columnDefinition = "bigint(20)" )
        private long usr_slno;

        @ Column ( name = "USER_ID", unique = true, nullable = false )
        private String usr_id;

        @ Column ( name = "ROLE", nullable = false )
        private String role;

        @ Column ( name = "FIRST_NAME", length = 100, nullable = false )
        private String firstname;

        @ Column ( name = "LAST_NAME", length = 100, nullable = false )
        private String lastname;

        @ Column ( name = "EMAIL_ID", length = 100, unique = true, nullable = false )
        private String email_id;

        @ Column ( name = "MOB_NO", unique = true, nullable = false, columnDefinition = "char(20)" )
        private String mob_no;

        @ Column ( name = "PASSWORD", length = 12, unique = true, nullable = false )
        private String pass;

        public UserDetails () {

            usr_slno = 0L; usr_id = ""; role = "";
	        firstname = ""; lastname = ""; pass = "";
	        email_id = ""; mob_no = "";

	    }

	    public Long getUsr_slno () { return ( usr_slno ); }

        public String getUsr_id () { return ( usr_id ); }

        public String getRole () { return ( role ); }

        public String getFirstname () { return ( firstname ); }

        public String getLastname () { return ( lastname ); }

        public String getPass () { return ( pass ); }

        public String getEmail_id () { return ( email_id ); }

        public String getMob_no () { return ( mob_no ); }

	    public void setUsr_slno ( Long usr_slno ) { this.usr_slno = usr_slno; }

        public void setUsr_id ( String pre ) { this.usr_id = pre + UserDetails.generateUser_ID(); }

        public void setRole ( String role ) { this.role = role; }

	    public void setFirstname ( String firstname ) { this.firstname = firstname; }

	    public void setLastname ( String lastname ) { this.lastname = lastname; }

        public void setPass ( String password ) { this.pass = password; }

	    public void setEmail_id ( String email_id ) { this.email_id = email_id; }

	    public void setMob_no ( String mob_no ) { this.mob_no = mob_no; }

	    private static int generateUser_ID () { return ( ( new Random () ).nextInt( 50000 ) ); }

}