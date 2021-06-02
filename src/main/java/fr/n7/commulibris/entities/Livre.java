package fr.n7.commulibris.entities;

import javax.persistence.*;
import java.util.List;

/**
 * Classe décrivant un livre possédé par un utilisateur.
 * @author Elio Saboureau
 * @version 1.0
 */
@Entity
public class Livre {

    @Id
    @GeneratedValue
    private int id; // Identifiant du livre généré automatiquement

    private String nom; // Nom du livre
    private String auteur; // Auteur du livre
    private String imageUrl; // Url de l'image
    private String description; // Description du livre

    @ElementCollection
    private List<String> genres; // Liste des genres du livre

    @ManyToOne
    private Utilisateur proprietaire; // Propriétaire du livre (bidirectionnelle)

    /**
     * Constructeur vide de la classe.
     */
    public Livre() {}

    /**
     * Obtenir l'identifiant du livre.
     * @return identifiant
     */
    public int getId() {
        return id;
    }

    /**
     * Modifier l'identifiant du livre.
     * @param id nouvel identifiant
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtenir le nom du livre.
     * @return nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * Modifier le nom du livre
     * @param nom nom
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Obtenir l'auteur du livre.
     * @return auteur
     */
    public String getAuteur() {
        return auteur;
    }

    /**
     * Modifier l'auteur du livre.
     * @param auteur auteur
     */
    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    /**
     * Obtenir les genres du livre.
     * @return liste des genres
     */
    public List<String> getGenres() {
        return genres;
    }

    /**
     * Modifier les genres du livre.
     * @param genres liste des genres
     */
    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    /**
     * Obtenir le propriétaire du livre.
     * @return utilisateur
     */
    public Utilisateur getProprietaire() {
        return this.proprietaire;
    }

    /**
     * Modifier le propriétaire du livre.
     * @param proprietaire utilisateur
     */
    public void setProprietaire(Utilisateur proprietaire) {
        this.proprietaire = proprietaire;
    }

    /**
     * Obtenir le lien de l'image.
     * @return url de l'image
     */
    public String getImageUrl() {
        return imageUrl;
    }

    /**
     * Modifier l'url de l'image.
     * @param imageUrl url
     */
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    /**
     * Obtenir la description du livre.
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Modifier la description du livre.
     * @param description description
     */
    public void setDescription(String description) {
        this.description = description;
    }

}
