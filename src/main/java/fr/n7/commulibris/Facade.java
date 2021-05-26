package fr.n7.commulibris;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Classe gérant la création/destruction des entités de notre base de données.
 * @author Elio Saboureau
 * @version 1.0
 */
@Singleton
public class Facade {

    @PersistenceContext
    private EntityManager em; // Gestionnaire de la base de donnée

    /**
     * Constructeur de la classe.
     */
    public Facade() {}

    /**
     * Retrouver un utilisateur depuis son idntifiant.
     * @param id idnetifiant
     * @return utilisateur
     */
    private Utilisateur getUtilisateurById(int id) {
        return em.find(Utilisateur.class, id); // TODO : cas d'erreur?
    }

    /**
     * Retrouver une conversation depuis son idnetifiant.
     * @param id identifiant
     * @return conversation
     */
    private Conversation getConversationById(int id) {
        return em.find(Conversation.class, id);
    }

    public void createUtilisateur(String pseudonyme, String mdp) {
        Utilisateur u = new Utilisateur(); // Création de l'objet
        u.setPseudonyme(pseudonyme);
        u.setMdp(mdp);

        this.em.persist(u); // Persistence
    }

    /**
     * Créer un livre.
     * @param proprietaire identiifiant du propriétaire
     * @param nom nom
     * @param auteur auteur
     * @param genres liste des genres
     */
    public void addLivre(int proprietaire, String nom, String auteur, List<String> genres) {
        Livre l = new Livre(); // Création de l'objet
        l.setNom(nom);
        l.setAuteur(auteur);
        l.setGenres(genres);
        l.setProprietaire(this.getUtilisateurById(proprietaire));

        this.em.persist(l); // Persistence
    }

    public void addAvis(int source, int cible, int note, String texte) {
        Avis a = new Avis(); // Création de l'objet
        a.setSource(this.getUtilisateurById(source));
        a.setCible(this.getUtilisateurById(cible));
        a.setNote(note);
        a.setTexte(texte);

        this.em.persist(a); // Persistence
    }

    public void addConversation(int[] participants, String nom) {
        Conversation c = new Conversation(); // Création de l'objet

        // Récupération des participants
        List<Utilisateur> p = new ArrayList<>(participants.length);

        for (int id : participants) {
            p.add(this.getUtilisateurById(id));
        }

        c.setNom(nom);
        c.setParticipants(p);

        this.em.persist(c); // Persistence
    }

    public void addMessage(int conversation, int auteur, String texte) {
        Message m = new Message(); // Création de l'objet
        m.setAuteur(this.getUtilisateurById(auteur));
        m.setTexte(texte);

        Conversation c = this.getConversationById(conversation); // Ajout à la conversation
        c.addMessage(m);

        this.em.persist(m); // Persistence
    }

}
