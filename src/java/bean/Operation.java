/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

/**
 *
 * @author AnaSs
 */
@Entity
public class Operation implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private double montant;
    private int type;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date dateOperationbanquaire = new Date();
    @ManyToOne
    private Compte compte;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Date getDateOperationbanquaire() {
        return dateOperationbanquaire;
    }

    public void setDateOperationbanquaire(Date dateOperationbanquaire) {
        this.dateOperationbanquaire = dateOperationbanquaire;
    }

    public Compte getCompte() {
        if (compte == null) {
            compte = new Compte();
        }
        return compte;
    }

    public void setCompte(Compte compte) {
        this.compte = compte;
    }

    public Operation(Long id, double montant, int type) {
        this.id = id;
        this.montant = montant;
        this.type = type;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public Operation(Long id, double montant) {
        this.id = id;
        this.montant = montant;
    }

    public Operation() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Operation)) {
            return false;
        }
        Operation other = (Operation) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bean.Operation[ id=" + id + " ]";
    }

}
