package vue.admin.arbitres.creation;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.List;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.ListCellRenderer;
import javax.swing.SwingConstants;
import javax.swing.DefaultComboBoxModel;

import modele.Equipe;
import modele.Tournoi;

import javax.swing.JComboBox;

import vue.common.CustomColor;
import vue.common.MaFont;


public class PopupTournoi extends JFrame {

	private JComboBox<Tournoi> c;

	public interface ActionHandler {
		void handleAction();
	}

	public PopupTournoi(String title, List<Tournoi> equipes, ActionHandler actionHandler) {
		super(title);
		setType(Type.UTILITY);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setPreferredSize(new Dimension(400, 200));

		ImageIcon icon = new ImageIcon(("assets/logo.png"));
		setIconImage(icon.getImage());

		JPanel panel = createPanel(actionHandler, equipes);
		add(panel);

		pack();
		setLocationRelativeTo(null); // Centre la fenêtre sur l'écran
		setVisible(true);
	}

	private JPanel createPanel(ActionHandler actionHandler, List<Tournoi> equipes) {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.setBackground(new Color(15, 3, 25));

		JLabel label = new JLabel("Saisir le tournoi :");
		label.setForeground(Color.white);
		label.setHorizontalAlignment(SwingConstants.CENTER); // Centre le texte
		label.setFont(MaFont.getFontTitre3()); // Agrandir la police
		panel.add(label, BorderLayout.NORTH);


		DefaultComboBoxModel<Tournoi> model = new DefaultComboBoxModel<Tournoi>();
		equipes.forEach(model::addElement);

		c = new JComboBox<Tournoi>(model);
		c.setRenderer(new ListCellRenderer<Tournoi>() {

			@Override
			public Component getListCellRendererComponent(JList<? extends Tournoi> list, Tournoi value, int index,
					boolean isSelected, boolean cellHasFocus) {
				return new JLabel(value.getNom()+ " " + value.getSaison().getAnnee());
			}
			
		});
		panel.add(c, BorderLayout.CENTER);

		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());
		buttonPanel.setBackground(new Color(15, 3, 25));
		JButton closeButton = new JButton("Annuler");
		closeButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		closeButton.setFont(MaFont.getFontTitre4());
		closeButton.setForeground(CustomColor.BLANC);
		closeButton.setBackground(CustomColor.TRANSPARENT);
		closeButton.setOpaque(false);
		closeButton.setFocusable(false);
		closeButton.addActionListener(e -> dispose());
		buttonPanel.add(closeButton);

		JButton okButton = new JButton("Valider");
		okButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		okButton.setFont(MaFont.getFontTitre4());
		okButton.setForeground(CustomColor.BLANC);
		okButton.setBackground(CustomColor.TRANSPARENT);
		okButton.setOpaque(false);
		okButton.setFocusable(false);
		okButton.addActionListener(e -> {
			if (actionHandler != null) {
				actionHandler.handleAction();
			}
			dispose();
		});
		buttonPanel.add(okButton);

		panel.add(buttonPanel, BorderLayout.SOUTH);

		return panel;
	}

	public Tournoi getSaisie() {
		return (Tournoi) c.getSelectedItem();
	}
}
