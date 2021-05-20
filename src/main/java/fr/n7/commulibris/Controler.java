package fr.n7.commulibris;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

/**
 * Classe controlant la communication avec notre page web.
 * @author Elio Saboureau
 * @version 1.0
 */
@WebServlet("/controler")
public class Controler extends HttpServlet {

    @EJB
    private Facade f;

    /**
     * Constructeur de la classe.
     */
    public Controler() {
        super();
    }

}
