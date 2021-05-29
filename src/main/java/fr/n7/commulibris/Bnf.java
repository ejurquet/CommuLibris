package fr.n7.commulibris;

import org.apache.jena.query.ParameterizedSparqlString;

/**
 * Classe gérant la communication avec l'API de la BNF.
 * @author Elio Saboureau
 * @version 1.0
 */
public class Bnf {

    /**
     * Constructeur de la classe.
     */
    public Bnf() {}

    /**
     * Rechercher un livre par son auteur.
     * @param auteur auteur
     */
    public void searchForAuteur(String auteur) {
        ParameterizedSparqlString p = new ParameterizedSparqlString();
    }

    /**
     * Rechercher un livre par son nom
     * @param nom nom
     */
    public void searchForNom(String nom) {
        ParameterizedSparqlString p = new ParameterizedSparqlString();
    }

}
