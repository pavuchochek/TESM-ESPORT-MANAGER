package controlleur;

import dao.Connexion;
import dao.DaoEquipe;
import dao.DaoJoueur;
import dao.DaoSaison;
import modele.Pays;
import modele.Equipe;

import vue.admin.equipes.creation.VueAdminEquipesCreation;
import vue.common.JFramePopup;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class EquipeCreationControlleur implements ActionListener, ControlleurObserver, ItemListener, MouseListener {
	private final VueAdminEquipesCreation vue;
	private final DaoEquipe daoEquipe;
	private BufferedImage logo;

	public EquipeCreationControlleur(VueAdminEquipesCreation newVue) {
		this.vue = newVue;
		Connexion c = Connexion.getConnexion();
		daoEquipe = new DaoEquipe(c);
		DaoSaison daoSaison = new DaoSaison(c);
		DaoJoueur daoJoueur = new DaoJoueur(c);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		JButton bouton = (JButton) e.getSource();
		if (Objects.equals(bouton.getText(), "Ajouter")) {
			String nomEquipe = vue.getNomEquipe();
			Pays champPaysEquipe = Pays.trouverPaysParNom(vue.getChampPaysEquipe());
			if ((nomEquipe.isEmpty()) || (logo == null)) {
				new JFramePopup("Erreur", "Un des champs est vide", () -> VueObserver.getInstance().notifyVue("Equipe"));
			} else if (equipeDejaExistante(nomEquipe)) {
				new JFramePopup("Erreur", "L'equipe existe deja", () -> VueObserver.getInstance().notifyVue("Equipe"));
				this.vue.clearField();
			} else {
				Equipe equipeInserer = new Equipe(nomEquipe, champPaysEquipe);
				try {
					daoEquipe.add(equipeInserer);
					File outputfile = new File("assets/logo-equipes/" + nomEquipe + ".jpg");
					ImageIO.write(logo, "jpg", outputfile);
					new JFramePopup("Succès", "L'équipe est insérée", () -> VueObserver.getInstance().notifyVue("Equipe"));
					this.vue.clearField();

				} catch (Exception ex) {
					new JFramePopup("Erreur", "Erreur d'insertion", () -> VueObserver.getInstance().notifyVue("Equipe"));
					throw new RuntimeException(ex);
				}
			}
		}
	}

	private boolean equipeDejaExistante(String nomEquipe) {
		try {
			Equipe equipe = daoEquipe.getById(nomEquipe);
			return equipe != null;
		} catch (Exception ignored) {
			return false;
		}

	}

	@Override
	public void update() {

	}


	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.SELECTED) {
			Pays item = (Pays) e.getItem();
			vue.setDrapeau(item.getCode());
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == vue.getLabelLogo()) {
			JFileChooser chooser = new JFileChooser();
			FileNameExtensionFilter filter = new FileNameExtensionFilter(
					"JPG & GIF Images", "jpg", "gif");
			chooser.setFileFilter(filter);
			int returnVal = chooser.showOpenDialog(this.vue);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				//recuperer le fichier choisi
				File fichier = chooser.getSelectedFile();
				try {
					//le transformer en image et la resize
					BufferedImage image = ImageIO.read(fichier);
					BufferedImage image_resized = resizeImage(image, this.vue.getLabelLogo().getWidth(), this.vue.getLabelLogo().getHeight());
					//transformer en icone pour pouvoir l'afficher
					this.logo = image;
					ImageIcon imageIcon = new ImageIcon(image_resized);

					//affichage
					this.vue.getLabelLogo().setIcon(imageIcon);
					this.vue.getLabelLogo().setText("");
					//sauvegarde du logo dans le projet
				} catch (IOException ex) {
					throw new RuntimeException(ex);
				}
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight) throws IOException {
		Image resultingImage = originalImage.getScaledInstance(targetWidth, targetHeight, Image.SCALE_DEFAULT);
		BufferedImage outputImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
		outputImage.getGraphics().drawImage(resultingImage, 0, 0, null);
		return outputImage;
	}
}
