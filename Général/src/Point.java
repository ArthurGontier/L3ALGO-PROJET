
public class Point {
	// On définit le point erreur (parallèle ou sécante hors du plan) par (1000,1000) 
	
	private double x;
	private double y;
	
	public Point(double abs, double ord){
		x = abs;
		y = ord;
	}
	
	public void affiPointSyso(){	
		System.out.println("( " + x + " ; " + y + " )");
	}
	
	
	public String affiPoint(){
		return("( " + this.x + " ; " + this.y + " )");
	}
	
	public double getX(){
		return x;
	}
	public double getY(){
		return y;
	}
	
	public boolean comparA(Point X) {
		boolean rep = false;
		if (this.getX() == X.getX() && this.getY() == X.getY()) {
			rep = true;
		}
		return rep;
	}
	

}
