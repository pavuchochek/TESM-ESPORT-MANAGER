import dao.Connexion;
import dao.DBGeneration;
import dao.DBSuppression;
import dao.DaoAppartenance;
import dao.DaoEquipe;
import dao.DaoInscription;
import dao.DaoJoueur;
import dao.DaoMatche;
import dao.DaoNiveau;
import dao.DaoPartie;
import dao.DaoPoule;
import dao.DaoSaison;
import dao.DaoTournoi;
import modele.Appartenance;
import modele.Categorie;
import modele.CompteArbitre;
import modele.CustomDate;
import modele.Equipe;
import modele.Inscription;
import modele.Joueur;
import modele.Matche;
import modele.Niveau;
import modele.Partie;
import modele.Pays;
import modele.Poule;
import modele.Saison;
import modele.Tournoi;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class ESporterManagerInitBDD {
	public static void main(String[] args) throws Exception {

		Connexion c = Connexion.getConnexion();
		try {
			DBSuppression.main(c);
		} catch (SQLException e) {
			System.out.println(e.toString());
		}

		try {
			DBGeneration.main(c);
		} catch (SQLException e) {
			System.out.println(e.toString());
		}

		DaoTournoi daoTournoi = new DaoTournoi(c);
		DaoNiveau daoNiveau = new DaoNiveau(c);
		DaoPoule daoPoule = new DaoPoule(c);
		DaoEquipe daoEquipe = new DaoEquipe(c);
		DaoAppartenance daoAppartenance = new DaoAppartenance(c);
		DaoMatche daoMatche = new DaoMatche(c);
		DaoPartie daoPartie = new DaoPartie(c);
		DaoSaison daoSaison = new DaoSaison(c);
		DaoInscription daoInscription = new DaoInscription(c);
		try {
			daoNiveau.add(Niveau.LOCAL);
			daoNiveau.add(Niveau.INTERNATIONAL);
			daoNiveau.add(Niveau.REGIONAL);
			daoNiveau.add(Niveau.INTERNATIONAL_CLASSE);
			daoNiveau.add(Niveau.NATIONAL);
		} catch (SQLException e) {
			System.out.println(e.toString());
		}
		Saison saison = new Saison(2024);
		try {
			daoSaison.add(saison);
		} catch (SQLException e) {
			System.out.println(e.toString());
		}
		CustomDate debutTournoi = new CustomDate(2024, 12, 1);
		CustomDate fin = new CustomDate(2024, 12, 30);
		Tournoi tournoi = new Tournoi(saison, "RLCS", debutTournoi, fin, Niveau.LOCAL, new CompteArbitre("arbitre", "rlcs"));
		try {
			daoTournoi.add(tournoi);
		} catch (SQLException e) {
			System.out.println(e.toString());
		}
		/*
		Optional<Tournoi> tournoiOptional = daoTournoi.getTournoiActuel();

		if (tournoiOptional.isPresent()) {
			tournoi = tournoiOptional.get();
		}*/

		Poule poule = new Poule(tournoi, 'A');

		CustomDate debut = new CustomDate(2024, 12, 5);
		CustomDate debut1 = new CustomDate(2024, 12, 7);

		Equipe equipe = new Equipe("terros", Pays.FRANCE);
		Equipe equipe1 = new Equipe("lion-rouge", Pays.FRANCE);
		Equipe equipe2 = new Equipe("shark", Pays.FRANCE);
		Equipe equipe3 = new Equipe("goule", Pays.FRANCE);
		Equipe equipe4 = new Equipe("dragon", Pays.FRANCE);
		Equipe equipe5 = new Equipe("aigle", Pays.FRANCE);
		Equipe equipe6 = new Equipe("bear", Pays.FRANCE);
		Equipe equipe7 = new Equipe("chevarcher", Pays.FRANCE);
		Equipe equipe8 = new Equipe("loup", Pays.FRANCE);
		Equipe equipe9 = new Equipe("mort", Pays.FRANCE);
		Inscription inscription = new Inscription(saison, equipe);
		Inscription inscription1 = new Inscription(saison, equipe1);
		Inscription inscription2 = new Inscription(saison, equipe2);
		Inscription inscription3 = new Inscription(saison, equipe3);
		Inscription inscription4 = new Inscription(saison, equipe4);
		Inscription inscription5 = new Inscription(saison, equipe5);
		Inscription inscription6 = new Inscription(saison, equipe6);
		Inscription inscription7 = new Inscription(saison, equipe7);
		Inscription inscription8 = new Inscription(saison, equipe8);
		Inscription inscription9 = new Inscription(saison, equipe9);

		try {

			daoEquipe.add(equipe);
			daoEquipe.add(equipe1);
			daoEquipe.add(equipe2);
			daoEquipe.add(equipe3);
			daoEquipe.add(equipe4);
			daoEquipe.add(equipe5);
			daoEquipe.add(equipe6);
			daoEquipe.add(equipe7);
			daoEquipe.add(equipe8);
			daoEquipe.add(equipe9);
			daoInscription.add(inscription);
			daoInscription.add(inscription1);
			daoInscription.add(inscription2);
			daoInscription.add(inscription3);
			daoInscription.add(inscription4);
			daoInscription.add(inscription5);
			daoInscription.add(inscription6);
			daoInscription.add(inscription7);
			daoInscription.add(inscription8);
			daoInscription.add(inscription9);
		} catch (SQLException e) {
			System.out.println(e.toString());
		}

		initEquipe(equipe);
		initEquipe(equipe1);
		initEquipe(equipe2);
		initEquipe(equipe3);
		initEquipe(equipe4);
		initEquipe(equipe5);
		initEquipe(equipe6);
		initEquipe(equipe7);
		initEquipe(equipe8);
		initEquipe(equipe9);

		Appartenance appartenance = new Appartenance(equipe, poule);
		Appartenance appartenance1 = new Appartenance(equipe1, poule);
		Appartenance appartenance2 = new Appartenance(equipe2, poule);
		Appartenance appartenance3 = new Appartenance(equipe3, poule);
		Appartenance appartenance4 = new Appartenance(equipe4, poule);
		Appartenance appartenance5 = new Appartenance(equipe5, poule);
		Appartenance appartenance6 = new Appartenance(equipe6, poule);
		Appartenance appartenance7 = new Appartenance(equipe7, poule);

		try {
			daoPoule.add(poule);
		} catch (SQLException e) {
			System.out.println(e.toString());
		}
		try {
			daoAppartenance.add(appartenance);
			daoAppartenance.add(appartenance1);
			daoAppartenance.add(appartenance2);
			daoAppartenance.add(appartenance3);
			daoAppartenance.add(appartenance4);
			daoAppartenance.add(appartenance5);
			daoAppartenance.add(appartenance6);
			daoAppartenance.add(appartenance7);
		} catch (SQLException e) {
			System.out.println(e.toString());
		}


		Matche matche = new Matche(1, debut, Categorie.POULE, equipe, equipe1, tournoi);
		Matche matche1 = new Matche(1, debut1, Categorie.POULE, equipe2, equipe3, tournoi);
		Partie partie1 = new Partie(matche, 1);
		Partie partie2 = new Partie(matche1, 1);
		try {
			daoMatche.add(matche);
			daoPartie.add(partie1);
			daoMatche.add(matche1);
			daoPartie.add(partie2);
		} catch (SQLException e) {
			System.out.println(e.toString());
		}


	}

	private static String randomUsername(String name) {
		List<String> characters = Arrays.asList(name.split(""));
		Collections.shuffle(characters);
		StringBuilder afterShuffle = new StringBuilder();
		for (String character : characters) {
			afterShuffle.append(character);
		}
		return afterShuffle.toString();
	}

	private static void initEquipe(Equipe equipe) {
		Connexion c = Connexion.getConnexion();
		DaoJoueur daoJoueur = new DaoJoueur(c);
		String default_username = "patata1234";
		for (int i = 0; i < 5; i++) {
			default_username = randomUsername(default_username);
			try {
				Joueur j = new Joueur(default_username, equipe);
				daoJoueur.add(j);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
