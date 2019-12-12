package eu.monoxial.password_manager;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * La classe Coffre est composé d'{@link eu.monoxial.password_manager.Element},
 * c'est avec elle qu'on interagit pour gérer les identifiants.
 *
 * @author Monoxial
 */
public class Coffre implements Serializable {

    /**
     * Le runtime de sérialisation associe à chaque classe sérialisable un
     * numéro de version, qui est utilisé pendant la désérialisation pour
     * vérifier que l'émetteur et le récepteur d'un objet sérialisé ont des
     * classes chargées pour cet objet qui sont bien compatibles entre elles.
     */
    private static final long serialVersionUID = -6813005583631907045L;

    /**
     * Tableau dynamique d'{@link eu.monoxial.password_manager.Element}.
     */
    private final ArrayList<Element> listeElements = new ArrayList<>();

    /**
     * Correspond au nom du Coffre, servira nottament à nommer le fichier lors
     * de la sauvegarde
     */
    private String nom;

    /**
     * Constructeur de Coffre
     *
     * @param _nom Un nom à donner au coffre
     */
    public Coffre(String _nom) {
        this.nom = _nom;
    }

    /**
     * Permet d'accèder à la liste des
     * {@link eu.monoxial.password_manager.Element}
     *
     * @return Tableau contenant les
     * {@link eu.monoxial.password_manager.Element}
     */
    public ArrayList<Element> getListeElement() {
        return listeElements;
    }

    /**
     * Permet d'obtenir un {@link eu.monoxial.password_manager.Element} à la
     * position donnée
     *
     * @param at Position de l'élément
     * @return L'element demandé
     */
    public Element getElement(int at) {
        return listeElements.get(at);
    }

    /**
     * Permet d'ajouter un {@link eu.monoxial.password_manager.Element} à la fin
     * du tableau
     *
     * @param nouvelElement Un élement à ajouter au coffre
     */
    public void ajouter(Element nouvelElement) {
        listeElements.add(nouvelElement);
    }

    /**
     * Permet de supprimer un {@link eu.monoxial.password_manager.Element} à la
     * position donnée
     *
     * @param at Position de l'élément à supprimer
     */
    public void supprimerAt(int at) {
        listeElements.remove(at);
    }

    /**
     * Permet d'obtenir le nom du coffre
     *
     * @return Le nom du coffre
     */
    public String getNomCoffre() {
        return nom;
    }

    /**
     * Permet de modifier le nom du coffre
     *
     * @param nouveauNom Le nom à appliquer au coffre
     */
    public void setNomCoffre(String nouveauNom) {
        this.nom = nouveauNom;
    }
}
