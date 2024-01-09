package vue.admin.tournois.creation;

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

import modele.Arbitre;

import vue.common.CustomColor;
import vue.common.CustomComboBox;
import vue.common.MaFont;


public class PopupArbitres extends JFrame {

	private CustomComboBox<Arbitre> c;

	public interface ActionHandler {
		void handleAction();
	}

	public PopupArbitres(String title, List<Arbitre> equipes, ActionHandler actionHandler) {
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

	private JPanel createPanel(ActionHandler actionHandler, List<Arbitre> equipes) {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.setBackground(new Color(15, 3, 25));

		JLabel label = new JLabel("Saisir l'équipe :");
		label.setForeground(Color.white);
		label.setHorizontalAlignment(SwingConstants.CENTER); // Centre le texte
		label.setFont(MaFont.getFontTitre3()); // Agrandir la police
		panel.add(label, BorderLayout.NORTH);


		DefaultComboBoxModel<Arbitre> model = new DefaultComboBoxModel<Arbitre>();
		equipes.forEach(model::addElement);

		c = new CustomComboBox<Arbitre>(model);
		c.setRenderer(new ListCellRenderer<Arbitre>() {

			@Override
			public Component getListCellRendererComponent(JList<? extends Arbitre> list, Arbitre value, int index,
					boolean isSelected, boolean cellHasFocus) {
				if (value != null) {
				return new JLabel(value.getNom() + " " + value.getPrenom());
				}
				return new JLabel("");
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

	public Arbitre getSaisie() {
		return (Arbitre) c.getSelectedItem();
	}
}
