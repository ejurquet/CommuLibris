package fr.n7.commulibris;

import fr.n7.commulibris.entities.Conversation;
import fr.n7.commulibris.entities.Livre;
import fr.n7.commulibris.entities.Utilisateur;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.*;

/**
 * Classe contrôlant la communication avec notre page web.
 * @author Elio Saboureau
 * @version 1.0
 */
@WebServlet("/controler")
public class Controler extends HttpServlet {

    // Descripteurs
    private final static String ACTION_PARAMETER = "action";
    private final static String ACTION_ACCESS_ACCUEIL = "accessAccueil";
    private final static String ACTION_CREATE_UTILISATEUR = "createUtilisateur";
    private final static String ACTION_AUTHENTICATE_UTILISATEUR = "authenticateUtilisateur";
    private final static String ACTION_LOGOUT_UTILISATEUR = "logoutUtilisateur";
    private final static String ACTION_GET_LIVRES = "getLivres";
    private final static String ACTION_GET_LIVRE = "getLivre";
    private final static String ACTION_GET_LIVRES_BY = "getLivresBy";
    private final static String ACTION_ADD_LIVRE = "addLivre";
    private final static String ACTION_ACCESS_PROFIL = "accessProfil";
    private final static String ACTION_ACCESS_OTHER_PROFIL = "accessOtherProfil";
    private final static String ACTION_REQUEST_ADD_AVIS = "requestAddAvis";
    private final static String ACTION_ADD_AVIS = "addAvis";
    private final static String ACTION_REQUEST_START_CONV = "requestStartConv";
    private final static String ACTION_START_CONV = "startConv";
    private final static String ACTION_ACCESS_CONV = "accessConv";
    private final static String ACTION_ADD_MSG = "addMessage";

    /**
     * Communication avec nos entités.
     */
    @EJB
    private Facade f;

    /**
     * Communication avec l'API de la BNF.
     */
    private Bnf bnf;

    /**
     * Table de reconnaissance des actions.
     */
    private final Map<String, Action> actions;

    /**
     * Interface décrivant une action.
     */
    interface Action {

        /**
         * Exécuter l'action.
         * @param req requête
         * @param rep réponse
         */
        void execute(HttpServletRequest req, HttpServletResponse rep) throws ServletException, IOException;

    }

    /**
     * Accéder à la page d'accueil.
     */
    private final Action actionAccessAccueil = (req, rep) -> {
        List<Livre> livres = this.f.getLatestLivres();

        // Configuration et envoi de la réponse
        req.setAttribute("livres", livres);
        RequestDispatcher rd = req.getRequestDispatcher("index.jsp"); // Redirection vers cette page
        rd.forward(req, rep);
    };

    /**
     * Créer un utilisateur.
     * pseudonyme : pseudonyme
     * mdp : mdp
     */
    private final Action actionCreateUtilisateur = (req, rep) -> {
        // Récupération des informations de la requête
        String pseudonyme = req.getParameter("pseudonyme");
        String mdp = req.getParameter("mdp");
        boolean valid = !pseudonyme.isEmpty() && !mdp.isEmpty();

        if (valid) {
            // Création de l'utilisateur
            valid = this.f.createUtilisateur(pseudonyme, mdp);

            // Envoyer la réponse
            if (valid) {
                // Afficher un message d'erreur
                successMessage(req, rep, "Votre compte a bien été créé.");
            }

        }

        if (!valid) {
            // Afficher un message d'erreur
            errorMessage(req, rep, "Votre compte n'a pas pu être créé.");
        }

    };

    /**
     * Authentifier un utilisateur.
     * pseudonyme : pseudonyme
     * mdp : mdp
     */
    private final Action actionAuthenticateUtilisateur = (req, rep) -> {
        // Récupération des informations de la requête
        String pseudonyme = req.getParameter("pseudonyme");
        String mdp = req.getParameter("mdp");

        // Vérifier l'authentification
        Utilisateur utilisateur = this.f.authenticateUtilisateur(pseudonyme, mdp);

        // Envoyer la réponse
        if (utilisateur != null) {
            // Ajouter un cookie relatif à l'authentificationZ
            HttpSession session = req.getSession();
            session.setAttribute("utilisateur", utilisateur);

            // Afficher un message de succès
            successMessage(req, rep, "Vous avez été authentifié.");
        } else {
            // Afficher un message d'erreur
            errorMessage(req, rep, "Authentification incorrecte. Aucun compte n'existe avec ces identifiants.");
        }
    };

    /**
     * Déconnecter un utilisateur.
     */
    private final Action actionLogoutUtilisateur = (req, rep) -> {
        HttpSession session = req.getSession(false);
        if (session != null) {
            session.removeAttribute("utilisateur");
        }
        rep.sendRedirect("");
    };

    /**
     * Obtenir tous les livres de la base de donnée.
     */
    private final Action actionGetLivres = (req, rep) -> {
        // Obtenir la liste des livres
        List<Livre> livres = this.f.getAllLivres();

        // Configurer la réponse, ajout de l'attribut
        req.setAttribute("livres", livres);

        // Envoyer la réponse
        RequestDispatcher rd = req.getRequestDispatcher("book_list.jsp"); // Redirection vers cette page
        rd.forward(req, rep);
    };

    /**
     * Obtenir un livre.
     * livreId : identifiant du livre
     */
    private final Action actionGetLivre = (req, rep) -> {
        // Récupération des informations de la requête
        int id = Integer.parseInt(req.getParameter("livreId"));

        // Récupération du livre
        Livre livre = this.f.getLivreById(id);

        // Configuration et envoi de la réponse
        req.setAttribute("livre", livre);
        RequestDispatcher rd = req.getRequestDispatcher("book.jsp"); // Redirection vers cette page
        rd.forward(req, rep);
    };

    /**
     * Ajouter un livre.
     * proprietaire : identifiant BDD du proprietaire
     * auteur : auteur
     * nom : nom
     * genres : genres
     */
    private final Action actionAddLivre = (req, rep) -> {
        // Récupération de l'utilisateur
        Utilisateur utilisateur = isLogged(req);
        boolean valid = utilisateur != null;

        if (valid) {
            // Récupération des informations de la requête
            int proprietaire = utilisateur.getId();
            String auteur = req.getParameter("auteur");
            String nom = req.getParameter("nom");
            String imageUrl = req.getParameter("image_url");
            String description = req.getParameter("desc");
            List<String> genres = new LinkedList<>();

            valid = !auteur.isEmpty() && !nom.isEmpty() && !imageUrl.isEmpty() && !description.isEmpty();

            if (valid) {
                this.f.addLivre(proprietaire, auteur, nom, imageUrl, description, genres);

                // Envoyer la réponse
                successMessage(req, rep, "Le livre a bien été ajouté.");
            }

        }

        if (!valid) {
            errorMessage(req, rep, "Impossible d'ajouter le livre.");
        }
    };

    /**
     * Obtenir la liste des livres par leur auteur.
     * auteur : auteur
     */
    private final Action actionGetLivresBy = (req, rep) -> {
        // Récupération des informations de la requête
        String terme = req.getParameter("terme");

        // Obtenir la liste des livres
        List<Livre> livresAuteur = this.f.getLivresByAuteur(terme);
        List<Livre> livresNom = this.f.getLivresByNom(terme);
        livresAuteur.addAll(livresNom);

        // Configurer la réponse, ajout de l'attribut
        req.setAttribute("livres", livresAuteur);

        // Envoyer la réponse
        RequestDispatcher rd = req.getRequestDispatcher("book_list.jsp"); // Redirection vers cette page
        rd.forward(req, rep);
    };

    /**
     * Accéder à son profil.
     * Nécessite d'être connecté.
     */
    private final Action actionAccesProfil = (req, rep) -> {
        // Récupération de l'utilisateur
        Utilisateur utilisateur = isLogged(req);
        boolean valid = utilisateur != null;

        if (valid) {
            // Récupération de l'utilisateur
            int id = utilisateur.getId();
            Utilisateur u = this.f.getUtilisateurById(id);

            valid = u != null;

            if (valid) {
                // Réponse à la requête
                req.setAttribute("utilisateur", u);
                RequestDispatcher rd = req.getRequestDispatcher("user_profil.jsp");
                rd.forward(req, rep);
            }
        }

        if (!valid) {
            errorMessage(req, rep, "Vous n'êtes pas connecté.");
        }
    };

    /**
     * Accèder au profil d'une autre personne.
     * cible : cible
     */
    private final Action actionAccessOtherProfil = (req, rep) -> {
        // Récupération du paramètre et initialisations
        int cible = Integer.parseInt(req.getParameter("cible"));
        Utilisateur u = this.f.getUtilisateurById(cible);
        boolean valid = u != null;

        if (valid) {
            // Envoyer la réponse
            req.setAttribute("utilisateur", u);
            RequestDispatcher rd = req.getRequestDispatcher("other_profil.jsp");
            rd.forward(req, rep);
        }

        if (!valid) {
            errorMessage(req, rep, "Ce profil n'existe pas !.");
        }
    };

    /**
     * Ouvrir la page pour ajouter un avis.
     * cible : identifiant de la cible
     */
    private final Action actionRequestAddAvis = (req, rep) -> {
        // Récupération de l'utilisateur
        int cible = Integer.parseInt(req.getParameter("cible"));
        Utilisateur utilisateur = this.f.getUtilisateurById(cible);
        boolean valid = utilisateur != null;

        if (valid) {
            req.setAttribute("cible", utilisateur);
            RequestDispatcher rd = req.getRequestDispatcher("user_review.jsp");
            rd.forward(req, rep);
        }

        if (!valid) {
            errorMessage(req, rep, "Impossible d'accèder à la page pour ajouter un avis.");
        }
    };

    /**
     * Ajouter un avis.
     * cible : identifiant de la cible
     * note : note
     * desc : description
     */
    private final Action actionAddAvis = (req, rep) -> {
        // Récupération de l'utilisateur
        Utilisateur utilisateur = isLogged(req);
        boolean valid = utilisateur != null;

        if (valid) {
            // Récupération des paramètres
            int source = utilisateur.getId();
            int cible = Integer.parseInt(req.getParameter("cible"));
            int note = Integer.parseInt(req.getParameter("note"));
            String desc = req.getParameter("desc"); // Autorisé vide.
            desc = desc.isEmpty() ? "Aucune explication." : desc;

            // Ajout à la BDD
            this.f.addAvis(source, cible, note, desc);

            successMessage(req, rep, "Avis ajouté.");
        }

        if (!valid) {
            errorMessage(req, rep, "Impossible d'ajouter cet avis.");
        }
    };

    /**
     * Demander à démarrer une conversation.
     * cible : cible
     */
    private final Action actionRequestStartConversation = (req, rep) -> {
        // Récupération de l'utilisateur
        int cible = Integer.parseInt(req.getParameter("cible"));
        Utilisateur utilisateur = this.f.getUtilisateurById(cible);
        boolean valid = utilisateur != null;

        if (valid) {
            req.setAttribute("cible", utilisateur);
            RequestDispatcher rd = req.getRequestDispatcher("user_start_conv.jsp");
            rd.forward(req, rep);
        }

        if (!valid) {
            errorMessage(req, rep, "Impossible d'accèder à la page pour démarrer une conversation.");
        }
    };

    /**
     * Démarrer une conversation.
     * nom : nom
     * cible : identifiant de la cible
     */
    private final Action actionStartConversation = (req, rep) -> {
        // Récupération de l'utilisateur
        Utilisateur utilisateur = isLogged(req);
        boolean valid = utilisateur != null;

        if (valid) {
            // Récupération des paramètres
            int[] participants = new int[] {utilisateur.getId(), Integer.parseInt(req.getParameter("cible"))};
            String nom = req.getParameter("nom");

            valid = nom != null;

            if (valid) {
                // Ajout à la BDD
                this.f.addConversation(participants, nom);
                successMessage(req, rep, "Conversation démarrée accédez-y via votre profil.");
            }
        }

        if (!valid) {
            errorMessage(req, rep, "Impossible de démarrer une conversation.");
        }
    };

    /**
     * Accéder à une conversation.
     * cible : identifiant conversation
     */
    private final Action actionAccessConversation = (req, rep) -> {
        // Récupération de l'utilisateur
        Utilisateur utilisateur = isLogged(req);
        boolean valid = utilisateur != null;

        if (valid) {
            // Récupération des paramètres
            int id = Integer.parseInt(req.getParameter("cible"));
            Conversation c = this.f.getConversationById(id);
            valid = c != null; // && c.getParticipants().contains(utilisateur);

            if (valid) {
                req.setAttribute("conv", c);
                RequestDispatcher rd = req.getRequestDispatcher("conversation.jsp");
                rd.forward(req, rep);
            }
        }

        if (!valid) {
            errorMessage(req, rep, "Impossible d'accéder à cette conversation.");
        }
    };

    /**
     * Ajouter un message à une conversation.
     * cible : identifiant de la conversation
     * desc : texte du message
     */
    private final Action actionAddMessage = (req, rep) -> {
        // Récupération de l'utilisateur
        Utilisateur utilisateur = isLogged(req);
        boolean valid = utilisateur != null;

        if (valid) {
            // Récupération des paramètres
            int id = Integer.parseInt(req.getParameter("cible"));
            String texte = req.getParameter("desc");
            Conversation c = this.f.getConversationById(id);

            valid = c != null; // && c.getParticipants().contains(utilisateur);

            if (valid) {
                this.f.addMessage(id, utilisateur.getId(), texte);
                req.setAttribute("conv", c);
                RequestDispatcher rd = req.getRequestDispatcher("conversation.jsp");
                rd.forward(req, rep);
            }
        }

        if (!valid) {
            errorMessage(req, rep, "Impossible d'accéder à cette conversation.");
        }
    };

    /**
     * Constructeur de la classe.
     */
    public Controler() {
        super();
        this.bnf = new Bnf();
        this.actions = new HashMap<>();
        this.actions.put("", actionAccessAccueil); // Redirection par défaut
        this.actions.put(ACTION_ACCESS_ACCUEIL, actionAccessAccueil);
        this.actions.put(ACTION_GET_LIVRES, actionGetLivres);
        this.actions.put(ACTION_GET_LIVRE, actionGetLivre);
        this.actions.put(ACTION_ADD_LIVRE, actionAddLivre);
        this.actions.put(ACTION_GET_LIVRES_BY, actionGetLivresBy);
        this.actions.put(ACTION_CREATE_UTILISATEUR, actionCreateUtilisateur);
        this.actions.put(ACTION_AUTHENTICATE_UTILISATEUR, actionAuthenticateUtilisateur);
        this.actions.put(ACTION_LOGOUT_UTILISATEUR, actionLogoutUtilisateur);
        this.actions.put(ACTION_ACCESS_PROFIL, actionAccesProfil);
        this.actions.put(ACTION_ACCESS_OTHER_PROFIL, actionAccessOtherProfil);
        this.actions.put(ACTION_REQUEST_ADD_AVIS, actionRequestAddAvis);
        this.actions.put(ACTION_ADD_AVIS, actionAddAvis);
        this.actions.put(ACTION_REQUEST_START_CONV, actionRequestStartConversation);
        this.actions.put(ACTION_START_CONV, actionStartConversation);
        this.actions.put(ACTION_ACCESS_CONV, actionAccessConversation);
        this.actions.put(ACTION_ADD_MSG, actionAddMessage);
    }

    /**
     * Afficher un message d'erreur.
     * @param req requête
     * @param rep réponse
     * @param message message
     */
    private static void errorMessage(HttpServletRequest req, HttpServletResponse rep, String message)
            throws ServletException, IOException {
        req.setAttribute("erreur", message);
        RequestDispatcher rd = req.getRequestDispatcher("error.jsp");
        rd.forward(req, rep);
    }

    /**
     * Afficher un message de succès.
     * @param req requête
     * @param rep réponse
     * @param message message
     */
    private static void successMessage(HttpServletRequest req, HttpServletResponse rep, String message)
            throws ServletException, IOException {
        req.setAttribute("success", message);
        RequestDispatcher rd = req.getRequestDispatcher("success.jsp");
        rd.forward(req, rep);
    }

    /**
     * Retrouver un cookie dans un tableau.
     * @param cookies tableau de cookies
     * @param key clef
     * @return option cookie
     */
    private static Optional<String> getCookie(Cookie[] cookies, String key) {
        return Arrays.stream(cookies)
                .filter(c -> key.equals(c.getName()))
                .map(Cookie::getValue)
                .findAny();
    }

    /**
     * Vérifie si un utilisateur est connecté
     * @param req requête
     * @return Utilisateur utilisateur
     */
    private static Utilisateur isLogged(HttpServletRequest req) {
        HttpSession session = req.getSession(false);
        Utilisateur utilisateur = null;
        if (session != null) {
            utilisateur = (Utilisateur) session.getAttribute("utilisateur");
        }
        return utilisateur;
    }

    /**
     * Gérer les requêtes HTTP GET.
     * @param req requête
     * @param rep réponse
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse rep) {
        try {

            // On suppose l'existence d'un paramètre action
            // qui décrit la requête faite au serveur
            String actionName = req.getParameter(ACTION_PARAMETER);
            actionName = actionName != null ? actionName : "";

            // On récupère l'action et on l'exécute
            Action action = this.actions.get(actionName);

            System.out.println("Action " + actionName + " appelée");

            if (action != null) {
                action.execute(req, rep);
            } else {
                // Redirection vers la page d'erreur
                errorMessage(req, rep, "La page à laquelle vous tentez d'accéder n'existe pas.");
            }

        } catch (Exception e) {
            // Dernière tentative d'afficher une erreur
            try {
                errorMessage(req, rep, "Quelque chose s'est mal passé.");
            } catch (ServletException | IOException servletException) {
                servletException.printStackTrace();
            }

            // Affichage de l'erreur de notre côté
            System.out.println("Une erreur est survenue lors de la récupération d'une requête HTTP");
            e.printStackTrace();
        }
    }

}
