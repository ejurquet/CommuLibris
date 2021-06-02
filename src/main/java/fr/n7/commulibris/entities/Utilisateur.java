package fr.n7.commulibris.entities;

import javax.persistence.*;
import java.util.List;

/**
 * Classe décrivant un utilisateur du site internet.
 * @author Elio Saboureau
 * @version 1.0
 */
@Entity
public class Utilisateur {

    @Id
    @GeneratedValue
    private int id; // Identifiant de l'utilisateur généré automatiquement

    private String pseudonyme; // Pseudonyme de l'utilisateur
    private String mdp; // Mot de passe de l'utilisateur

    @OneToMany(mappedBy="proprietaire", fetch=FetchType.EAGER)
    private List<Livre> livres; // Livres de l'utilisateur (bidirectionnelle)

    @OneToMany(mappedBy="source")
    private List<Avis> avisDonnes; // Avis donnés (bidirectionnelle)

    @OneToMany(mappedBy="cible")
    private List<Avis> avisRecu; // Avis reçus (bidirectionnelle)

    @ManyToMany(mappedBy="participants")
    private List<Conversation> conversations; // Conversations (bidirectionnelle)

    /**
     * Constructeur vide de la classe.
     */
    public Utilisateur() {}

    /**
     * Obtenir l'identifiant de l'utilisateur.
     * @return identifiant
     */
    public int getId() {
        return id;
    }

    /**
     * Modifier l'identifiant de l'utilisateur.
     * @param id identifiant
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtenir le pseudonyme de l'utilisateur.
     * @return pseudonyme
     */
    public String getPseudonyme() {
        return pseudonyme;
    }

    /**
     * Modifier le pseudonyme de l'utilisateur.
     * @param pseudonyme pseudonyme
     */
    public void setPseudonyme(String pseudonyme) {
        this.pseudonyme = pseudonyme;
    }

    /**
     * Obtenir le mot de passe de l'utilisateur.
     * @return mot de passe
     */
    public String getMdp() {
        return mdp;
    }

    /**
     * Modifier le mot de passe de l'utilisateur.
     * @param mdp mot de passe
     */
    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    /**
     * Obtenir les livres de l'utilisateur.
     * @return liste des livres
     */
    public List<Livre> getLivres() {
        return livres;
    }

    /**
     * Modifier les livre de l'utilisateur.
     * @param livres liste des livres
     */
    public void setLivres(List<Livre> livres) {
        this.livres = livres;
    }

    /**
     * Obtenir les avis donnés de l'utilisateur.
     * @return liste des avis donnés
     */
    public List<Avis> getAvisDonnes() {
        return avisDonnes;
    }

    /**
     * Modifier les avis donnés de l'utilisateur.
     * @param avisDonnes liste des avis donnés
     */
    public void setAvisDonnes(List<Avis> avisDonnes) {
        this.avisDonnes = avisDonnes;
    }

    /**
     * Obtenir les avis reçus de l'utilisateur.
     * @return liste des avis reçus
     */
    public List<Avis> getAvisRecu() {
        return avisRecu;
    }

    /**
     * Modifier les avis reçus de l'utilisateur.
     * @param avisRecu liste des avis reçus
     */
    public void setAvisRecu(List<Avis> avisRecu) {
        this.avisRecu = avisRecu;
    }

    /**
     * Obtenir les conversations de l'utilisateur.
     * @return liste des conversations
     */
    public List<Conversation> getConversations() {
        return conversations;
    }

    /**
     * Modifier les conversations de l'utilisateur.
     * @param conversations liste des conversations
     */
    public void setConversations(List<Conversation> conversations) {
        this.conversations = conversations;
    }

}
