package fr.n7.commulibris.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Classe permettant de décrire les avis qu'un utilisateur
 * a donné sur un autre.
 * @author Elio Saboureau
 * @version 1.0
 */
@Entity
public class Avis {

    @Id
    @GeneratedValue
    private int id; // Identifiant généré auto.

    private String texte; // Texte de l'avis
    private int note; // Note de l'avis

    @ManyToOne
    private Utilisateur cible; // Cible de l'avis (bidirectionnelle)

    @ManyToOne
    private Utilisateur source; // Source de l'avis (bidirectionnelle)

    /**
     * Constructeur vide de la classe.
     */
    public Avis() {}

    /**
     * Obtenir l'identifiant de l'avis.
     * @return identifiant
     */
    public int getId() {
        return id;
    }

    /**
     * Modifier l'identifiant de l'avis.
     * @param id identifiant
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtenir le texte de l'avis.
     * @return texte
     */
    public String getTexte() {
        return texte;
    }

    /**
     * Modifier le texte de l'avis.
     * @param texte texte
     */
    public void setTexte(String texte) {
        this.texte = texte;
    }

    /**
     * Obtenir la note de l'avis.
     * @return note
     */
    public int getNote() {
        return note;
    }

    /**
     * Modifier la note de l'avis.
     * @param note note
     */
    public void setNote(int note) {
        this.note = note;
    }

    /**
     * Obtenir la cible de l'avis.
     * @return utilisateur
     */
    public Utilisateur getCible() {
        return cible;
    }

    /**
     * Modifier la cible de l'avis.
     * @param cible utilisateur
     */
    public void setCible(Utilisateur cible) {
        this.cible = cible;
    }

    /**
     * Obtenir la source de l'avis.
     * @return utilisateur
     */
    public Utilisateur getSource() {
        return source;
    }

    /**
     * Modifier la source de l'avis.
     * @param source utilisateur
     */
    public void setSource(Utilisateur source) {
        this.source = source;
    }

}
