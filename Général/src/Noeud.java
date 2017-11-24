// Un noeud peut être : 
//		Interne et contenir 4 Noeuds fils
// 		Externe et contenir 1 Point
import java.util.ArrayList;

public class Noeud {
	// Noeud parents
	//private Noeud parent;
	// Noeuds fils;
	// [NO][NE]
	// [SO][SE]
	private Noeud NO;
	private Noeud NE;
	private Noeud SO;
	private Noeud SE;

	Polygone carre;
	
	ArrayList<Polygone> EnsTriangle = new ArrayList<Polygone>();
	
	// Constructeur de noeud : 
	
	public Noeud (ArrayList<Point> ensP,ArrayList<Polygone> EnsT){
		this.carre = new Polygone(ensP);
		this.EnsTriangle = EnsT;
	}

	public Polygone getCarre(){
		return this.carre;
	}
	
	// les subdivisions : dans l'ordre de traitement.
	// # NO 
	public Noeud getNO(){
		return this.NO;
	}
	public void setNO(Noeud n){
		this.NO = n;
	}
	// # NE 
	public Noeud getNE(){
		return this.NE;
	}
	public void setNE(Noeud n){
		this.NE = n;
	}
	// # SE 
	public Noeud getSE(){
		return this.SE;
	}
	public void setSE(Noeud n){
		this.SE = n;
	}
	// # SO 
	public Noeud getSO(){
		return this.SO;
	}
	public void setSO(Noeud n){
		this.SO = n;
	}


}
