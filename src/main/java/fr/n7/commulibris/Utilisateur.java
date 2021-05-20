package fr.n7.commulibris;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Classe décrivant un utilisateur du site internet.
 * @author Elio Saboureau
 * @version 1.0
 */
@Entity
public class Utilisateur {

    @Id
    private int id; // Identifiant de l'utilisateur

    public Utilisateur() {

    }

}
