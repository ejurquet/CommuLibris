package fr.n7.commulibris;

import javax.persistence.*;

/**
 * Classe décriavtn un message envoyé sur une conversation.
 * @author Elio Saboureau
 * @version 1.0
 */
@Entity
public class Message {

    @Id
    @GeneratedValue
    private int id; // Identifiant généré auto.

    private String texte; // Texte du message

    @ManyToOne
    private Utilisateur auteur; // Auteur du message (unidirectionnelle)

    /**
     * Constructeur vide de la classe.
     */
    public Message() {}

    /**
     * Obtenir l'identifiant du message.
     * @return identifiant
     */
    public int getId() {
        return id;
    }

    /**
     * Modifier l'identifiant du message.
     * @param id identifiant
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtenir le texte du message.
     * @return texte
     */
    public String getTexte() {
        return texte;
    }

    /**
     * Modifier le texte du message.
     * @param texte texte
     */
    public void setTexte(String texte) {
        this.texte = texte;
    }

    /**
     * Obtenir l'auteur du message.
     * @return utilisateur
     */
    public Utilisateur getAuteur() {
        return auteur;
    }

    /**
     * Modifier l'auteur du message.
     * @param auteur utilisateur
     */
    public void setAuteur(Utilisateur auteur) {
        this.auteur = auteur;
    }

}
