package fr.n7.commulibris;

import org.apache.jena.query.ParameterizedSparqlString;
import org.apache.jena.query.Query;

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
        ParameterizedSparqlString queryContent = new ParameterizedSparqlString();

        queryContent.append("SELECT ?uri ?oeuvre");
        queryContent.append("WHERE {");
        queryContent.append("?manifestation bnf-onto:ouvrageJeunesse \"true\"^^xsd:boolean ;");
        queryContent.append("rdarelationships:workManifested ?oeuvre ;");
        queryContent.append("rdfs:seeAlso ?uri.");
        queryContent.append("}");

        Query query = queryContent.asQuery();
        query.addGraphURI("https://data.bnf.fr/");
    }

    /**
     * Rechercher un livre par son nom
     * @param nom nom
     */
    public void searchForNom(String nom) {
        ParameterizedSparqlString p = new ParameterizedSparqlString();
    }

    public static void main(String[] args) {
        Bnf b = new Bnf();
        b.searchForAuteur("lol");
    }

}
