/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Compte;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author AnaSs
 */
@Stateless
public class CompteFacade extends AbstractFacade<Compte> {

    @PersistenceContext(unitName = "CompteOperation_JeePU")
    private EntityManager em;

    @EJB
    private OperationFacade operationfacade;

    public int save(Compte compte) {
        Compte loadcompte = find(compte.getId());
        if (loadcompte != null) {
            return -1;
        } else if (compte.getSolde() < 100) {
            return -2;
        } else {
            create(compte);
            return 1;
        }

    }

    public int debiter(Compte compte, double montant) {
        Compte loadcompte = find(compte.getId());
        if (loadcompte == null) {
            return -1;
        } else {
            compte.setSolde(compte.getSolde() + montant);
            edit(compte);
            operationfacade.createOperationBanquiareDebit(compte, montant);
            return 1;
        }

        
    }

    public int crediter(String id, double montant) {
        Compte compte = find(id);
        if (compte == null) {
            return -1;
        } else {
            double nvSolde = compte.getSolde() - montant;
            if (nvSolde < 0) {
                return -2;
            } else {
                compte.setSolde(nvSolde);
                edit(compte);
                return 2;
            }
        }
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CompteFacade() {
        super(Compte.class);
    }

}
