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
import javax.persistence.FetchType;
import javax.persistence.Table;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.OneToOne;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@ NamedQueries ({

        @ NamedQuery ( name = "checkifuserispending", query = "from ApprovalPending ap where ap.ud.usr_id=:ent_uid" )

})

@ Entity
@ Table ( name = "USER_REGISTRATION_PENDING_APPROVAL" )
public class ApprovalPending implements Serializable
{

        private static final long serialVersionUID = 1L;

        @ Id
        @ GeneratedValue ( strategy = GenerationType.SEQUENCE )
        @ Column ( name = "PENDING_SLNO", unique = true, nullable = false, columnDefinition = "bigint(20)" )
        private long pn_slno;

        @ OneToOne ( fetch = FetchType.LAZY )
        @ JoinColumns ( {

                @ JoinColumn ( name = "PUSER_ID", nullable = false, unique = true, referencedColumnName = "USER_ID" ),
                @ JoinColumn ( name = "PUSER_FIRSTNAME", nullable = false, referencedColumnName = "FIRST_NAME" ),
                @ JoinColumn ( name = "PUSER_LASTNAME", nullable = false, referencedColumnName = "LAST_NAME" ),
                @ JoinColumn ( name = "PUSER_EMAIL_ID", nullable = false, referencedColumnName = "EMAIL_ID" )

        } )
        private UserDetails ud;

        public ApprovalPending () { pn_slno = 0L; ud = new UserDetails (); }

        public long getPn_slno () { return ( pn_slno ); }

        public void setPn_slno ( long pn_slno ) { this.pn_slno = pn_slno; }

        public UserDetails getUd () { return ( ud ); }

        public void setUd ( UserDetails ud ) { this.ud = ud; }

}
