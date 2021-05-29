package fr.n7.commulibris;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
     * Constructeur de la classe.
     */
    public Controler() {
        super();
        this.bnf = new Bnf();
        this.actions = new HashMap<>();
        this.actions.put(ACTION_GET_LIVRES, actionGetLivres);
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

}
