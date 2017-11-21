// Un noeud peut être : 
//		Interne et contenir 4 Noeuds fils
// 		Externe et contenir 1 Point


public class Noeud {
	// Noeud parents
	private Noeud parent;
	// Noeuds fils;
	// [NO][NE]
	// [SO][SE]
	private Noeud NO;
	private Noeud NE;
	private Noeud SO;
	private Noeud SE;
	// Dimension d'un noeud - Car un noeud correspond à une région
	// On travaille sur des régions carrées donc on suppose que L = H
	// Largeur
	private double L;
	// Hauteur
	private double H;
	// Coordonnées du point de découpe
	// []|[]
	// ----- x
	// []|[]
	//   y 
	private double X;
	private double Y;
	// Contenu du noeud 
	private Point P;
	
	
	// Constructeur de noeud : 
	
	public Noeud (double x,double y, double l, double h, Noeud N){
		// X et Y sont les coordonnées bas gauche du carré 
		// ______
		//|	     |
		//|._____|
		//les coordonnées du . sont X et Y
		this.X = x;
		this.Y = y;
		// La largeur et la hauteur du quadtree sont pour simplifier L-X et H-Y
		this.L = l;
		this.H = H;
		this.parent = N;
	}
	// Les getters et les setters
	// de X - Coordonnées de découpe 
	public double getX(){
		return this.X;
	}
	public void setX(double x){
		this.X = x;
	}
	// de Y - Coordonnées de découpe
	public double getY(){
		return this.Y;
	}
	public void setY(double y){
		this.Y = y;
	}
	// de L - la largeur d'un coté de région (région = noeud) 
	public double getL(){
		return this.L;
	}
	public void setL(double l){
		this.L = l;
	}
	// de H - la hauteur d'un coté de région (region = noeud)
	public double getH(){
		return this.H;
	}
	public void setH(double h){
		this.L = h;
	}
	// le père du Noeud;
	public Noeud getParent(){
		return this.parent;
	}
	public void setParent(Noeud pere){
		this.parent = pere;
	}
	// le point possiblement contenu dans un noeud;
	public Point getPoint(){
		return this.P;
	}
	public void setPoint(Point pt){
		this.P = pt;
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
