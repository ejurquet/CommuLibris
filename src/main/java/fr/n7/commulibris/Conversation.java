package fr.n7.commulibris;

import javax.persistence.*;
import java.util.List;

/**
 * Classe permettant de réunir tous les messages échangés entre deux utilisateurs.
 * @author Elio Saboureau
 * @version 1.0
 */
@Entity
public class Conversation {

    @Id
    @GeneratedValue
    private int id; // Identifiant généré auto.

    private String nom; // Nom de la conversation

    @ManyToMany
    private List<Utilisateur> participants; // Liste des utilisateurs participant (bidirectionnelle)

    @OneToMany
    private List<Message> messages; // Liste des messages

    /**
     * Constructeur vide de la classe.
     */
    public Conversation() {}

    /**
     * Obtenir l'identifiant de la conversation.
     * @return identifiant
     */
    public int getId() {
        return id;
    }

    /**
     * Modifier l'identifiant de la conversation.d
     * @param id identifiant
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtenir le nom de la conversation.
     * @return nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * Modifier le nom de la conversation;
     * @param nom nom
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Obtenir les participants.
     * @return liste des participants
     */
    public List<Utilisateur> getParticipants() {
        return participants;
    }

    /**
     * Modifier la liste des participants.
     * @param participants liste des participants
     */
    public void setParticipants(List<Utilisateur> participants) {
        this.participants = participants;
    }

    /**
     * Obtenir les messages.
     * @return liste des messages
     */
    public List<Message> getMessages() {
        return messages;
    }

    /**
     * Modifier les messages.
     * @param messages liste des messages
     */
    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    /**
     * Ajouter un message.
     * @param message message
     */
    public void addMessage(Message message) {
        List<Message> messages = this.getMessages(); // Ajout par les getter/setter pour assurer la mise à jour
        messages.add(message);
        this.setMessages(messages);
    }

}
