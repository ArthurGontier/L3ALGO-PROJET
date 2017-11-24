import java.util.ArrayList;

public class Polygone {
	private ArrayList<Point> polyg;
	
	public Polygone(ArrayList<Point> sesSommets){
		polyg = sesSommets;
	}
	
	public static void ajout(ArrayList<Point> sommets, Point P){
		boolean here = sommets.contains(P);
		if (!here && !(P.getX()==1000) && !(P.getY()==1000)){
			sommets.add(P);
		}
		else{
			System.out.println("Sommet déjà dans le polygone OU Ajout du point error");
		}
	}
	public void polyAff(){
		System.out.println("Nb de sommets " + polyg.size());
		for(int i = 0; i < polyg.size(); i++){
			polyg.get(i).affiPointSyso();
		}
	}
	public int taille() {
		return polyg.size();
	}
	public ArrayList<Point> getEns(){
		return polyg;
	}
	
	public boolean estPoly() {
		return (polyg.size() >= 3);
	}
	
	

}
