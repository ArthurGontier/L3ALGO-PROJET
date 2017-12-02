import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class golfMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		// Création des points manuelle;
		// Ligne 


		//lecture du fichier
		File f = new File("DescriptionFIgureGolf2.txt");

		try {
			ArrayList<Polygone> terrainNonTriangulé = new ArrayList<Polygone>();
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
				Polygone poly = new Polygone(A);poly.polyAff();
				terrainNonTriangulé.add(poly);
			}
			/*while ((line = br.readLine()) != null){//affichage brut test
				System.out.println(line);
			}*/
			br.close();		
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
