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

@ Entity
@ Table ( name = "REMOTE_HOST_DETAILS" )
public class HostDetails implements Serializable
{

        private static final long serialVersionUID = 1L;

        @ Id
        @ GeneratedValue ( strategy = GenerationType.SEQUENCE )
        @ Column ( name = "HOST_SLNO", unique = true, nullable = false, columnDefinition = "bigint(20)" )
        private long h_slno;

        @ Column ( name = "HOSTNAME", unique = true, nullable = false )
        private String h_name;

        @ Column ( name = "TYPE", length = 5, nullable = false )
        private String h_type;

        @ Column ( name = "HOST_LOC", length = 5, nullable = false )
        private String h_loc;

        @ Column ( name = "HOST_PATCH", length = 100, nullable = false )
        private String h_patch;

        public long getH_slno () { return ( h_slno ); }

        public void setH_slno ( long h_slno ) { this.h_slno = h_slno; }

        public String getH_name () { return ( h_name ); }

        public void setH_name ( String h_name ) { this.h_name = h_name; }

        public String getH_type () { return ( h_type ); }

        public void setH_type ( String h_type ) { this.h_type = h_type; }

        public String getH_loc () { return ( h_loc ); }

        public void setH_loc ( String h_loc ) { this.h_loc = h_loc; }

        public String getH_patch () { return ( h_patch ); }

        public void setH_patch ( String h_patch ) { this.h_patch = h_patch; }

}
