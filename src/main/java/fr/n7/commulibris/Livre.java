package fr.n7.commulibris;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Classe décrivant un livre possédé par un utilisateur.
 * @author Elio Saboureau
 * @version 1.0
 */
@Entity
public class Livre {

    @Id
    private int id; // Identifiant du livre

    private String nom;
    private String auteur;
    private String note;

    @ElementCollection
    private List<String> genres;

    public Livre() {}

    public Livre(int id, String nom, String auteur, String genre) {
        this.id = id;
        this.nom = nom;
        this.auteur = auteur;
        this.genres = new LinkedList<>();
    }

}
