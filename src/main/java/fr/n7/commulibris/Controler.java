package fr.n7.commulibris;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Classe contrôlant la communication avec notre page web.
 * @author Elio Saboureau
 * @version 1.0
 */
@WebServlet("/controler")
public class Controler extends HttpServlet {

    // Descripteurs
    private final static String ACTION_PARAMETER = "action";
    private final static String ACTION_GET_LIVRES = "getLivres";
    private final static String ACTION_GET_LIVRE = "getLivre";
    private final static String ACTION_GET_LIVRES_NOM = "getLivresById";
    private final static String ACTION_GET_LIVRES_AUTEUR = "getLivresByAuteur";
    private final static String ACTION_ADD_LIVRE = "addLivre";

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
     * Obtenir tous les livres de la base de donnée.
     */
    private final Action actionGetLivres = (req, rep) -> {
        // Obtenir la liste des livres
        List<Livre> livres = this.f.getAllLivres();

        // Configurer la réponse, ajout de l'attribut
        req.setAttribute("livres", livres);

        // Envoyer la réponse
        RequestDispatcher rd = req.getRequestDispatcher("page.jsp"); // Redirection vers cette page
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

        // Configuration de la réponse
        req.setAttribute("livre", livre);

        // Envoyer la réponse
        RequestDispatcher rd = req.getRequestDispatcher("page.jsp"); // Redirection vers cette page
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
        int proprietaire = Integer.parseInt(req.getParameter("proprietaire"));
        String auteur = req.getParameter("auteur");
        String nom = req.getParameter("nom");
        List<String> genres = Arrays.asList(req.getParameterValues("genres"));

        // Ajout à la BDD
        this.f.addLivre(proprietaire, auteur, nom, genres);
        // TODO : un peu de vérification et renvoi vers une page d'erreur

        // Envoyer la réponse
        RequestDispatcher rd = req.getRequestDispatcher("success.html"); // Redirection vers cette page
        rd.forward(req, rep);
    };

    private final Action actionGetLivresByAuteur = (req, rep) -> {
        // Récupération des informations de la requête
        String auteur = req.getParameter("auteur");

        // Obtenir la liste des livres
        List<Livre> livres = this.f.getLivresByAuteur(auteur);

        // Configurer la réponse, ajout de l'attribut
        req.setAttribute("livres", livres);

        // Envoyer la réponse
        RequestDispatcher rd = req.getRequestDispatcher("page.jsp"); // Redirection vers cette page
        rd.forward(req, rep);
    };

    private final Action actionGetLivresByNom = (req, rep) -> {
        // Récupération des informations de la requête
        String nom = req.getParameter("nom");

        // Obtenir la liste des livres
        List<Livre> livres = this.f.getLivresByNom(nom);

        // Configurer la réponse, ajout de l'attribut
        req.setAttribute("livres", livres);

        // Envoyer la réponse
        RequestDispatcher rd = req.getRequestDispatcher("page.jsp"); // Redirection vers cette page
        rd.forward(req, rep);
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
        this.actions.put(ACTION_GET_LIVRES_NOM, actionGetLivresByNom);
        this.actions.put(ACTION_GET_LIVRES_AUTEUR, actionGetLivresByAuteur);
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

            // On récupère l'action et on l'exécute
            Action action = this.actions.get(actionName);

            if (action != null) {
                action.execute(req, rep);
            } else {
                System.out.println("L'action demandée n'existe pas");
                // TODO : renvoyer vers une page d'erreur (à définir)
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
