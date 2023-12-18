package controlleur;

import vue.BoutonNavBar;
import vue.Page;
import vue.admin.VueAdmin;
import vue.admin.accueil.VueAccueil;
import vue.admin.arbitres.VueArbitres;
import vue.admin.equipes.VueAdminEquipes;
import vue.admin.main.BoutonMenu;
import vue.admin.main.MenuNavBar;
import vue.admin.tournois.VueAdminTournois;
import vue.common.JFramePopup;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Objects;

enum ETAT {
	ACCUEIL, ARBITRES, EQUIPES, TOURNOIS
}

public class AdminControlleur implements ActionListener, MouseListener {
	private final VueAdmin vue;
	private ETAT etat;
	MenuNavBar navbar;

	public AdminControlleur(MenuNavBar navbar, VueAdmin vue) {
		this.navbar = navbar;
		this.etat = ETAT.ACCUEIL;
		this.vue = vue;
		vue.addPage(new VueAdminEquipes(), Page.EQUIPES);
		vue.addPage(new VueAccueil(), Page.ACCUEIL_ADMIN);
		vue.addPage(new VueArbitres(), Page.ARBITRES);
		vue.addPage(new VueAdminTournois(), Page.TOURNOIS);
		vue.setPage(Page.ACCUEIL_ADMIN);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() instanceof BoutonMenu) {
			navbar.selectionner((BoutonMenu) e.getSource());
		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() instanceof BoutonMenu) {
			navbar.selectionner((BoutonMenu) e.getSource());
			BoutonMenu boutonSelection = (BoutonMenu) e.getSource();
			if (Objects.equals(boutonSelection.getText(), BoutonNavBar.ARBITRES.getNom()) && etat != ETAT.ARBITRES) {
				this.etat = ETAT.ARBITRES;
				this.vue.setPage(Page.ARBITRES);
			} else if (Objects.equals(boutonSelection.getText(), BoutonNavBar.DECONNEXION.getNom())) {
				new JFramePopup("Déconnexion", "Etes vous sur de vous déconnecter ?", () -> VueObserver.getInstance().notifyVue(Page.LOGIN));

			} else if ((Objects.equals(boutonSelection.getText(), BoutonNavBar.ACCUEIL.getNom())) && etat != ETAT.ACCUEIL) {
				this.etat = ETAT.ACCUEIL;
				this.vue.setPage(Page.ACCUEIL_ADMIN);
			} else if (Objects.equals(boutonSelection.getText(), BoutonNavBar.EQUIPES.getNom())) {
				this.etat = ETAT.EQUIPES;
				this.vue.setPage(Page.EQUIPES);
			} else if (Objects.equals(boutonSelection.getText(), BoutonNavBar.TOURNOIS.getNom())) {
				this.etat = ETAT.TOURNOIS;
				this.vue.setPage(Page.TOURNOIS);
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		if (e.getSource() instanceof BoutonMenu) {
			((BoutonMenu) e.getSource()).survoller();
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if (e.getSource() instanceof BoutonMenu) {
			((BoutonMenu) e.getSource()).finSurvoller();
		}
	}
}
