package vue.admin;

import controlleur.BoutonMenuControlleur;
import controlleur.VueObserver;
import vue.Page;
import vue.admin.main.Main;
import vue.admin.main.MenuNavBar;
import vue.common.TitleBar;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class VueAdmin extends JPanel {

	private Main main;
	private MenuNavBar navbar;
	private BoutonMenuControlleur controlleurBoutons;


	public VueAdmin() {
		setLayout(new BorderLayout());
		navbar = new MenuNavBar();
		main = new Main(navbar);
		controlleurBoutons = new BoutonMenuControlleur(navbar, this);
		navbar.addButtonControlleur(controlleurBoutons);
		main.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
		main.setOpaque(false);
		add(main, BorderLayout.CENTER);
		setPage(Page.ACCUEIL_ADMIN.getNom());
	}


	/**
	 * Change la page de contenue du main
	 *
	 * @param identifiant l'identifiant
	 */
	public void setPage(String identifiant) {
		main.setPage(identifiant);
		TitleBar.getInstance().setTitle(identifiant);
	}

	public void addPage(JComponent c, String id) {
		main.addPage(c, id);
	}


	public void attachObserver(VueObserver obs) {
		this.controlleurBoutons.attach(obs);
	}


}
