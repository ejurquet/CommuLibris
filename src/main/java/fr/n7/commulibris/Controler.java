package fr.n7.commulibris;

import fr.n7.commulibris.entities.Livre;
import fr.n7.commulibris.entities.Utilisateur;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    private final static String ACTION_CREATE_UTILISATEUR = "createUtilisateur";
    private final static String ACTION_AUTHENTICATE_UTILISATEUR = "authenticateUtilisateur";
    private final static String ACTION_GET_LIVRES = "getLivres";
    private final static String ACTION_GET_LIVRE = "getLivre";
    private final static String ACTION_GET_LIVRES_BY = "getLivresBy";
    private final static String ACTION_ADD_LIVRE = "addLivre";
    private final static String ACTION_ACCESS_PROFIL = "accessProfil";

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
     * Créer un utilisateur.
     * pseudonyme : pseudonyme
     * mdp : mdp
     */
    private final Action actionCreateUtilisateur = (req, rep) -> {
        // Récupération des informations de la requête
        String pseudonyme = req.getParameter("pseudonyme");
        String mdp = req.getParameter("mdp");

        // Création de l'utilisateur
        boolean done = this.f.createUtilisateur(pseudonyme, mdp);

        // Envoyer la réponse
        if (done) {
            // Afficher un message d'erreur
            req.setAttribute("success", "Votre compte a bien été créé.");
            RequestDispatcher rd = req.getRequestDispatcher("success.jsp"); // Redirection vers cette page
            rd.forward(req, rep);
        } else {
            // Afficher un message d'erreur
            req.setAttribute("erreur", "Votre compte n'a pas pu être créé. Le pseudonyme est déjà utilisé.");
            RequestDispatcher rd = req.getRequestDispatcher("error.jsp");
            rd.forward(req, rep);
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
        int id = this.f.authenticateUtilisateur(pseudonyme, mdp);

        // Envoyer la réponse
        if (id >= 0) {
            // Ajouter un cookie relatif à l'authentification
            Cookie utilisateurCookie = new Cookie("utilisateur", String.valueOf(id));
            rep.addCookie(utilisateurCookie);

            // Afficher un message de succès
            req.setAttribute("success", "Vous avez été authentifié.");
            RequestDispatcher rd = req.getRequestDispatcher("success.jsp"); // Redirection vers cette page
            rd.forward(req, rep);
        } else {
            // Afficher un message d'erreur
            req.setAttribute("erreur", "Authentification incorrecte. Aucun compte n'existe avec ces identifiants.");
            RequestDispatcher rd = req.getRequestDispatcher("error.jsp");
            rd.forward(req, rep);
        }
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
        //Livre livre = this.f.getLivreById(id);
        Livre livre = new Livre();
        livre.setId(1);
        livre.setNom("Bob");
        livre.setAuteur("Le Bricoleur");

        // Configuration de la réponse
        req.setAttribute("livre", livre);

        // Envoyer la réponse
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
        // Récupération des informations de la requête
        //int proprietaire = Integer.parseInt(req.getParameter("proprietaire"));
        String auteur = req.getParameter("auteur");
        String nom = req.getParameter("nom");
        //List<String> genres = Arrays.asList(req.getParameterValues("genres"));

        // Ajout à la BDD
        //this.f.addLivre(proprietaire, auteur, nom, genres);
        // TODO : un peu de vérification et renvoi vers une page d'erreur

        // Envoyer la réponse
        RequestDispatcher rd = req.getRequestDispatcher("index.jsp"); // Redirection vers cette page
        rd.forward(req, rep);
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
        // Récupération du cookie de connexion
        Optional<String> cookie = getCookie(req.getCookies(), "utilisateur");
        boolean valid = cookie.isPresent();

        if (valid) {
            // Récupération de l'utilisateur
            int id = Integer.parseInt(cookie.get());
            Utilisateur u = this.f.getUtilisateurById(id);

            valid = u != null;

            if (valid) {
                // Réponse à la requête
                req.setAttribute("utilisateur", u);
                RequestDispatcher rd = req.getRequestDispatcher("profil.jsp");
                rd.forward(req, rep);
            }
        }

        if (!valid) {
            // Afficher un message d'erreur
            req.setAttribute("erreur", "Vous n'êtes pas connecté.");
            RequestDispatcher rd = req.getRequestDispatcher("error.jsp");
            rd.forward(req, rep);
        }
    };

    /**
     * Constructeur de la classe.
     */
    public Controler() {
        super();
        this.bnf = new Bnf();
        this.actions = new HashMap<>();
        this.actions.put(ACTION_GET_LIVRES, actionGetLivres);
        this.actions.put(ACTION_GET_LIVRE, actionGetLivre);
        this.actions.put(ACTION_ADD_LIVRE, actionAddLivre);
        this.actions.put(ACTION_GET_LIVRES_BY, actionGetLivresBy);
        this.actions.put(ACTION_CREATE_UTILISATEUR, actionCreateUtilisateur);
        this.actions.put(ACTION_AUTHENTICATE_UTILISATEUR, actionAuthenticateUtilisateur);
        this.actions.put(ACTION_ACCESS_PROFIL, actionAccesProfil);
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
            actionName = actionName != null ? actionName : "inexistante";

            // On récupère l'action et on l'exécute
            Action action = this.actions.get(actionName);

            System.out.println("Action " + actionName + " appelée");

            if (action != null) {
                action.execute(req, rep);
            } else {
                // Redirection vers la page d'erreur
                req.setAttribute("erreur", "La page à laquelle vous tentez d'accéder n'existe pas.");
                RequestDispatcher rd = req.getRequestDispatcher("error.jsp");
                rd.forward(req, rep);
            }

        } catch (Exception e) {
            System.out.println("Une erreur est survenue lors de la récupération d'une requête HTTP");
            e.printStackTrace();
        }
    }

    /*
    // doFilter
    public void doFilter(HttpServletRequest request, HttpServletResponse response, HttpFilter chain) throws IOException, ServletException {
        response.setContentType("text/html; charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        doFilter(request, response, chain);
    }*/

}
