import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.awt.*;
import javax.swing.*;


public class golfMain extends JPanel {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		// Ligne 
		ArrayList<Polygone> terrainNonTriangulé = new ArrayList<Polygone>();

		//LECTURE DE DONNEE
		/*
		Scanner sc = new Scanner(System.in);

		System.out.println("Veuillez saisir un t" + "\n" 
				+ "(i.e. le nombre max de triangles dans une feuille du quadtree :");
		int t = Integer.parseInt(sc.nextLine());	
		System.out.println("Vous avez choisi : " + t + " triangles par feuilles." );
		
		*/
		
		//LECTURE DE FICHIER
		/*
		//File f = new File("DescriptionFIgureGolf2.txt");

		File f = new File("DescriptionFIgureGolf2TrianglesTest.txt");

		
		try {
			BufferedReader br = new BufferedReader(new FileReader(f));
			String line = br.readLine();
			//la première ligne nous donne le nombre de molynomes
			int init = Integer.parseInt(line);
			//pour chaque polynome, on découpe les coordonnées avec split et substring puis on créé chaque points pour les mettre dans un polygone que l'on met enfin dans le terrain non triangulé 
			for (int i = 0; i < init;i++){
				ArrayList<Point> A = new ArrayList<Point>();
				line = br.readLine();
				String[] P = line.split(",");
				for(int j = 0; j<P.length-1; j=j+2){
					double x = Double.parseDouble(P[j].substring(1,P[j].length()));
					double y = Double.parseDouble(P[j+1].substring(0,P[j+1].length()-1));
					Point p = new Point(x,y);
					A.add(p);
				}
				
				char couleur = P[P.length-1].charAt(0);
				Polygone poly = new Polygone(A,couleur);
				//poly.polyAff();
				terrainNonTriangulé.add(poly);
			}
			//// Le tracé regroupe toutes les infos et est sité dans les dernières lignes 
			// > l1 : le nombre de tracés 
			// > l2 et + : description d'un tracé :(surface k=1),(surface k=2),(surface k=3),(surface green),(pt de départ),(pt d'arrivee),(par)
			br.close();		
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		*/
		//pgmTest.testPosiTri();
		
		
		
		
		ArrayList<Polygone> terrainTriangulé = new ArrayList<Polygone>();
		terrainTriangulé = Geom.TriangulationTerrain(terrainNonTriangulé);
		
		
		Point BorneSO = new Point(0,0);
		Point BorneSE = new Point(10,0);
		Point BorneNE = new Point(10,10);
		Point BorneNO = new Point(0,10);  
		
		
		
		
		ArrayList<Point> BornesTerrain = new ArrayList();
		BornesTerrain.add(BorneSO);
		BornesTerrain.add(BorneSE);
		BornesTerrain.add(BorneNE);
		BornesTerrain.add(BorneNO);
		 	
		int t = 9;
		
		QuadTree Terrain = new QuadTree(BornesTerrain, terrainTriangulé);
		QuadTree.ConstructionQT(Terrain.getRacine(),t,terrainTriangulé,"RAC");
		//pgmTest.testQT(Terrain);
		
		
		
		
		// Fonction déplacement.
		// On prend un point de départ;
		// On donne un point d'arrivée - Théorique 
		// La fonction nous retourne un point qui à partir du point de départ et du point théorique a subit l'influence du vent;
		
		// Test Basique
		// Départ à (0,0)
		// Arrivée à (10,10)
		
		Point Dep = new Point (0,0);
		Point Arr = new Point (10,10);
		
		Deplacement.CalculPointAtterrissageBalle(Dep,Arr);
		
		
		Point Atteri = Deplacement.CalculPointDepartBalle(Terrain,Dep, Arr);
		
		
		
		
		
	}
	
	
	
	

	
	

}
