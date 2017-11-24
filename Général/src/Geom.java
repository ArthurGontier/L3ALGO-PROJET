import java.util.ArrayList;


public class Geom {
	
	public static Point intersection(Droite D1, Droite D2) {
		Point intersec;
		if(D1.geta() * D2.getb() - D1.getb() * D2.geta()==0){
			//System.out.println(">> Parallèles");
			intersec = new Point(1000,1000);
		}
		else{
			//System.out.println(">> Secantes");
			// CF : Prise de notes dans le MD20
			// Calcul de la coordonnées x :
			double x = ((D1.getb()*D2.getc()-D1.getc()*D2.getb())/(D1.geta()*D2.getb() - D1.getb()*D2.geta()));
			double y = ((D1.getc()*D2.geta()-D1.geta()*D2.getc())/(D1.geta()*D2.getb() - D1.getb()*D2.geta()));
			intersec = new Point(x,y);
		}
		return intersec;
	}
	
	
	
	
	
	public static Point segIntersection(Point A, Point B, Point C, Point D) {
		// L'idée ici est d'utiliser un point comme un vecteur
		// Le dénom est nul, alors les droites sont parallèles
		// Si 0<m<1 et 0<k<1, alors l'intersection se fait dans les segments
		// Sinon; elles s'intersectent ailleurs.
				// Il est possible que l'intersection se fasse sur une des bornes du segment
				// Il est possible que l'intersection se fasse hors du segment
		
		Point intersec;
		// [AB] - I le vecteur associé à [AB]	
		Point I = new Point (B.getX()-A.getX(),B.getY()-A.getY());		
		// [CD] - J le vecteur associé à [CD]	
		Point J = new Point (D.getX() - C.getX(), D.getY() - C.getY());
		//System.out.println("I : [ " + I.getX() + " ; " + I.getY() + " ]"); 
		//System.out.println("J : [ " + J.getX() + " ; " + J.getY() + " ]"); 
		
		double denom = I.getX() * J.getY() - I.getY() * J.getX();
		//System.out.println("Dénom : "+denom);
		if(denom == 0) {
			System.out.println("> Les droites sont parallèles");  
		    intersec = new Point(1000,1000); 
		    //intersec.affiPointSyso();
		}
		else {
			double m = (I.getX() * A.getY() - I.getX() * C.getY() - I.getY() * A.getX() + I.getY() * C.getX())/(denom);
			double k = (J.getX() * A.getY() - J.getX() * C.getY()- J.getY() * A.getX() + J.getY() * C.getX())/(denom);
		    int m0 = Double.compare(m, 0);
		    int m1 = Double.compare(1, m);
		    int k0 = Double.compare(k, 0);
		    int k1 = Double.compare(1, k);
		    //System.out.println("m = "+ m + " | 0<m : "+ m0+ " - m<1 : "+ m1); 
		    //System.out.println("k = "+ k + " | 0<k : "+ k0+ " - k<1 : "+ k1); 
		    if (m0==1 && m1==1 && k0==1 && k1==1) {
		    		System.out.println("> Intersection dans segment");
		    		//System.out.println("m = "+ m + " /  k =" + k );
		    		//double X = A.getX() + k * I.getX();
		    		//double Y = A.getY() + k * I.getY();
		    		//  OU
		    		double X = C.getX() + m * J.getX();
		    		double Y = C.getY() + m * J.getY();
		    		intersec = new Point (X,Y);
		    		//intersec.affiPointSyso();
		    }
		    else {
		    		//On veut pas des éléments hors du segment donc on retourne le point error (1000,1000)
		    		Droite AB = new Droite(A,B);
		    		Droite CD = new Droite(C,D);
		    		Point X = intersection(AB,CD);
		    		System.out.println(">> "+uneBorne(X,A,B,C,D));
		    		if (uneBorne(X,A,B,C,D)) {
		    			System.out.println("> Intersection sur bornes");
		    			intersec = X;
		    		}
		    		else {
		    			System.out.println("> Intersection hors segment");
		    			intersec = new Point (1000,1000);
		    		}
		    		//intersec.affiPointSyso();
		    }
		}
		return intersec;
	}
	
	private static boolean uneBorne(Point X, Point A, Point B, Point C, Point D) {
		boolean rep = false;
		//A.affiPointSyso(); System.out.println(X.comparA(A));
		//B.affiPointSyso(); System.out.println(X.comparA(B));
		//C.affiPointSyso(); System.out.println(X.comparA(C));
		//D.affiPointSyso(); System.out.println(X.comparA(D));
		if (X.comparA(A) || X.comparA(B) || X.comparA(C) || X.comparA(D) ) {
			rep = true;
		}
		return rep;
	}
	public static ArrayList<Point> clipping(Polygone C, Polygone T) {
		// On définit une arraylist qui va récupérer les points d'intersection :
		ArrayList<Point> res = new ArrayList<Point>();		
		int Csize = C.getEns().size();
		int Tsize = T.getEns().size();
		for (int j = 0; j < Csize;j++) {
			for (int i = 0; i < Tsize;i++) {
					Point tmp = segIntersection(C.getEns().get(j%Csize),C.getEns().get((j+1)%Csize),T.getEns().get(i%Tsize),T.getEns().get((i+1)%Tsize));
					// On test si le point que l'on a calculé n'est pas le point erreur;
					// On a juste à tester la valeur de la première coordonnée;
					if (tmp.getX()!=1000) {
						res.add(tmp);
					}
			}
		}		
		// Affichage du résultat 
		return res;
	}	
}
