package eu.monoxial.password_manager;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * La classe Element contient toutes les informations correspondant à un couple
 * identifiant et mot de passe. Plus certaines informations utiles telle qu'un
 * descriptif et la date d'expiration du dit éléments.
 *
 * @author Monoxial
 */
public class Element implements Serializable {

    /**
     * Le runtime de sérialisation associe à chaque classe sérialisable un
     * numéro de version, qui est utilisé pendant la désérialisation pour
     * vérifier que l'émetteur et le récepteur d'un objet sérialisé ont des
     * classes chargées pour cet objet qui sont bien compatibles entre elles.
     */
    private static final long serialVersionUID = 3949074508343903957L;

    /**
     * Le nom d'un Element.
     */
    private String nom;
    /**
     * L'identifiant d'un Element.
     */
    private String identifiant;
    /**
     * Le mot de passe d'un Element
     */
    private String mot_de_passe;
    /**
     * Le descriptif d'un Element
     */
    private String brief;

    /**
     * La date d'expiration d'un Element
     */
    private LocalDate expi;

    /**
     * Constructeur d'un élément
     *
     * @param _nom Un nom à donner à l'Element
     * @param _id L'identifiant de l'Element
     * @param _mdp Le mot de passe de l'Element
     * @param _brief Une description de l'identifiant
     * @param _expi Une date ou l'identifiant va expiré
     */
    Element(String _nom, String _id, String _mdp, String _brief, LocalDate _expi) {
        this.nom = _nom;
        this.identifiant = _id;
        this.mot_de_passe = _mdp;
        this.brief = _brief;
        this.expi = _expi;
    }

    /**
     * Renvoi le nom de l'Element
     *
     * @return Le nouveau nom
     */
    public String getNom() {
        return this.nom;
    }

    /**
     * Permet de modifier le nom de l'Element
     *
     * @param _nom Le nouveau nom
     */
    public void setNom(String _nom) {
        this.nom = _nom;
    }

    /**
     * Renvoi l'identifiant de l'Element
     *
     * @return L'identifiant
     */
    public String getId() {
        return identifiant;
    }

    /**
     * Permet de modifier l'identifiant de l'Element
     *
     * @param id Le nouvel identifiant
     */
    public void setId(String id) {
        this.identifiant = id;
    }

    /**
     * Renvoi le mot de passe de l'Element
     *
     * @return Le mot de passe
     */
    public String getMdp() {
        return mot_de_passe;
    }

    /**
     * Permet de modifier le mot de passe de l'Element
     *
     * @param mdp Le nouveau mot de passe
     */
    public void setMdp(String mdp) {
        this.mot_de_passe = mdp;
    }

    /**
     * Renvoi la description de l'Element
     *
     * @return La description
     */
    public String getBrief() {
        return brief;
    }

    /**
     * Permet de modifier la description de l'Element
     *
     * @param _brief La nouvelle description
     */
    public void setBrief(String _brief) {
        this.brief = _brief;
    }
    
    /**
     * Renvoi la date d'expiration de l'Element
     *
     * @return La date d'expiration 
     */
    public LocalDate getExpi() {
        return expi;
    }

    /**
     * Permet de modifier la date d'expiration de l'Element
     *
     * @param _expi La date d'expiration 
     */
    public void setExpi(LocalDate _expi) {
        this.expi = _expi;
    }

}
