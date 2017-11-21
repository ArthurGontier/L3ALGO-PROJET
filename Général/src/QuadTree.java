import java.util.ArrayList;

public class QuadTree {
	
	private Noeud racine;
	//private int cpt = 0;
	
	// Constructeur de QuadTree - On initialise le noeud de la racine;
	// Le noeud racine du quadtree et nécessairement un noeud interne. il possède donc
	// 		4 fils 
	// 		0 point
	// On passe en paramètre du quadtree les dimensions initiale de notre quadtree
	// Nous ça sera 10x10;
	
	
	public QuadTree(double minX,double minY, double maxX, double maxY){
		// Largeur de la surface initiale 
		double L = maxX - minX;
		// Hauteur de la surface initiale 
		double H = maxY - minY;
		// C'est un point interne donc il ne possède que des fils; pas de point.
		this.racine = new Noeud(minX,minY,L,H,null);
	}
	
	public Noeud getRacine(){
		return this.racine;
	}
	
	
}
