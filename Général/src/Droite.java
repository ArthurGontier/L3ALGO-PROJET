
public class Droite {
	
	private Point A;
	private Point B;
	private double a;
	private double b;
	private double c;
	
	// a*x+b*y+c = 0
	// c = -a*x - b*y 
	public Droite(Point X, Point Y){
		this.A = X;
		this.B = Y;
		this.a = calcA();
		this.b = calcB();
		this.c = calcC();
	}
	
	public void affiDroitePoints(){
		A.affiPoint();
		B.affiPoint();
	}
	
	public void affiDroiteEqua(){
		//System.out.println(a +"* x + " + b +" * y + " + c + " = 0");
		System.out.println("y = "+ - (a/b)+" x + "+ -(c/b) );
	}
	
	
	private double calcA(){
		return -(B.getY() - A.getY());
	}
	
	private double calcB(){
		return B.getX() - A.getX();
	}  
	private double calcC(){
		//return (-a*A.getX()-b*A.getY());
		return (-a*B.getX()-b*B.getY());
	}
	

	//////////////////////////GETTER
	public double geta(){
		return this.a;
	}
	public double getb(){
		return this.b;
	}
	public double getc(){
		return this.c;
	}
	public Point getPtI() {
		return A;
	}
	public Point getPtII() {
		return B;
	}
	
	
	
	
	
	
	

}
