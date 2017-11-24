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
	
	
	public QuadTree(ArrayList<Point> Pts,ArrayList<Polygone> ensT){
		this.racine = new Noeud(Pts,ensT);
	}
	
	public void ConstructionQT(Noeud N,int t,ArrayList<Polygone> ensT){
		//si le noeud intersecte t triangles : on met les triangles dans la feuille

		int cpt = 0;
		//parcourt des triangle pour voir combien intersectent le Noeud
		for(int i = 0; i<ensT.size();i++){
			if(Geom.clipping(N.getCarre(), ensT.get(i)).size() == 0){
				cpt++;
			}
		}
		//sinon on créer 4 fils et on appelle la construction récurcivement
	}
	
	public Noeud getRacine(){
		return this.racine;
	}
	
	
	
}

