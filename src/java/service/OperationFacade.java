/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Operation;
import bean.Compte;
import java.util.Date;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author AnaSs
 */
@Stateless
public class OperationFacade extends AbstractFacade<Operation> {

    @PersistenceContext(unitName = "CompteOperation_JeePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public void createOperationBanquiareDebit(Compte compte, double montant) {
        createOperationBanquiare(compte, montant, 1);
    }
    
    public void createOperationBanquiareCredit(Compte compte, double montant,int type) {
        createOperationBanquiare(compte, montant, 2);
    }
    public void createOperationBanquiare(Compte compte, double montant,int type) {
        Operation operation = new Operation();
        operation.setMontant(montant);
        operation.setDateOperationbanquaire(new Date());
        operation.setType(type);
        operation.setCompte(compte);
        create(operation);
    }

    public OperationFacade() {
        super(Operation.class);
    }

}
