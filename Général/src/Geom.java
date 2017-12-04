import java.util.ArrayList;


public class Geom {
	
	public static Point intersection(Droite D1, Droite D2) {
		Point intersec;
		/*
		double Y = (D2.geta()*D1.getc()-D2.getc())/(D2.getb()-D1.geta()*D2.getb());
		double X = -D1.geta()-D1.getb()*Y;
		intersec = new Point(X,Y);
		*/
		
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
	
	public static int position(Droite AB, Point M) {
		// Entier de la réponse
		int rep = 0;
		double eval = AB.geta() * M.getX() + AB.getb() * M.getY() + AB.getc();
		double coefDir = -(AB.geta()/AB.getb());
		//System.out.println(coefDir);
		if (eval == 0){
			//System.out.println("Points sur la droite. ");
			rep = 0;
		}
		else {
			// Calcul du coefficient directeur pour savoir quels sont les test à effectuer.
			// En effet, cela diffère si une droite est croissante (coefDir > 0) du cas ou une droite est décroissante (coefDir < )
			if (coefDir > 0) {
				// CAS D'UNE DROITE CROISSANTE
				//System.out.print("Points hors de la droite : ");
				if(eval < 0){
					//System.out.println("Le point est au dessus de la droite.");
					rep = 1;
				}
				else {
					//System.out.println("Le point est en dessous de la droite.");
					rep = -1;
				}	
			}
			else {
				// CAS D'UNE DROITE DECROISSANTE
				if(eval > 0){
					//System.out.println("Le point est au dessus de la droite.");
					rep = 1;
				}
				else {
					//System.out.println("Le point est en dessous de la droite.");
					rep = -1;
				}
			}
		}
		return rep;
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
			//System.out.println("> Les droites sont parallèles");  
		    intersec = new Point(1000,1000); 
		    //intersec.affiPointSyso();
		}
		else {
			/*
			Droite AB = new Droite (A,B);
			Droite CD = new Droite(C,D);
			
    			intersec = intersection(AB,CD);

    			if (!((intersec.getX() >= A.getX() && intersec.getX()<= B.getX())||(intersec.getX() <= A.getX() && intersec.getX() >= B.getX()))){
    				intersec = new Point (1000,1000);
    			}*/
			
			
			double m = (I.getX() * A.getY() - I.getX() * C.getY() - I.getY() * A.getX() + I.getY() * C.getX())/(denom);
			double k = (J.getX() * A.getY() - J.getX() * C.getY()- J.getY() * A.getX() + J.getY() * C.getX())/(denom);
		    int m0 = Double.compare(m, 0);
		    int m1 = Double.compare(1, m);
		    int k0 = Double.compare(k, 0);
		    int k1 = Double.compare(1, k);
		    //System.out.println("m = "+ m + " | 0<m : "+ m0+ " - m<1 : "+ m1); 
		    //System.out.println("k = "+ k + " | 0<k : "+ k0+ " - k<1 : "+ k1); 
		    if (m0==1 && m1==1 && k0==1 && k1==1) {
		    		//System.out.println("> Intersection dans segment");
		    		//System.out.println("m = "+ m + " /  k =" + k );
		    		//double X = A.getX() + k * I.getX();
		    		//double Y = A.getY() + k * I.getY();
		    		//  OU
		    		double X = C.getX() + m * J.getX();
		    		double Y = C.getY() + m * J.getY();
		    		intersec = new Point (X,Y);
		    		//intersec.affiPointSyso();
		    }
		    else{
		    		intersec = new Point(1000,1000);
		    	}
		    
			
		   /* else {
		    		//On veut pas des éléments hors du segment donc on retourne le point error (1000,1000)
		    		Droite AB = new Droite(A,B);
		    		Droite CD = new Droite(C,D);
		    		Point X = intersection(AB,CD);
		    		System.out.println(">> "+uneBorne(X,A,B,C,D));
		    		if (uneBorne(X,A,B,C,D)) {
		    			System.out.println("> Intersection sur bornes");
		    			X.affiPointSyso();
		    			intersec = X;
		    		}
		    		else {
		    			System.out.println("> Intersection hors segment");
		    			intersec = new Point (1000,1000);
		    		}
		    		//intersec.affiPointSyso();
		    }*/
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
					//System.out.println("---");
					if (tmp.getX()!=1000) {
						res.add(tmp);
						//tmp.affiPointSyso();

					}
			}
		}
		//si leurs cotés ne s'intersectent pas, le seul moyen qu'ils se touchent, c'est qu'il soient imbriqués l'un dans l'autre.
		//donc on test les 2 cas ou les points du carré sonts dans le triangle ou les points du triangle sonts dans le carré:
		
		for (int i = 0; i < Csize;i++) {//parcourt des points du CARRE pour voir si ils sonts dans le triangle
			if(1 == posTriangle(T.getEns().get(0),T.getEns().get(1),T.getEns().get(2),C.getEns().get(i))){
				res.add(T.getEns().get(0));
    			//System.out.println("> Cas carré dans le triangle");

				//on ajoute un noeud juste pour signifier que il y a une intersection, a voir lequel
			}
		}
		for (int i = 0; i < Tsize;i++) {//parcourt des points du TRIANGLE pour voir si ils sonts dans le carré
			//regarde si les points du triangles sont entre les coordonées X et Y du carré
			if(T.getEns().get((i)).getX() >= C.getEns().get((0)).getX() 
					&& T.getEns().get((i)).getX() <= C.getEns().get((1)).getX() 
					&& T.getEns().get((i)).getY() >= C.getEns().get((0)).getY() 
					&& T.getEns().get((i)).getY() <= C.getEns().get((3)).getY()){
				//on ajoute un noeud juste pour signifier que il y a une intersection, a voir lequel
				res.add(C.getEns().get(0));
    			//System.out.println("> Cas triangle dans le carré");

			}
		}
		//System.out.println("taille des intersections : "+res.size());
		// Affichage du résultat 
		return res;
	}
	
	
	
	
	
	public static int posTriangle(Point A, Point B, Point C, Point X) {
		// On suppose le triangle ABC - Intersection de 3 demi plans
		// Pour savoir si X se trouve dans le demi plan, il faut alors 3 conditions sur X
		// Demi plan AB contenant C (X doit être du signe de C)
		// Demi plan AC contenant B (X doit être du signe de B)
		// Demi plan BC contenant A (X doit être du signe de A)
		int res;
		
		// ATTENTION AB BC CA - Ordre Trigo 
		Droite AB = new Droite (A,B);
		//int m = position(AB, C);
		//int n = position(AB, X);
		//System.out.println("> Demi plan AB | C = " + m + " , X = "+ n);
		boolean pC_AB = egalPosi(position(AB, C),position(AB, X));
		//System.out.println("> Demi plan AB | C : "+ pC_AB);
		//AB.affiDroiteEqua();
		
		Droite BC = new Droite (B,C);
		//int o = position(BC, A);
		//int p = position(BC, X);
		//System.out.println("> Demi plan BC | A = " + o + " , X = "+ p);
		boolean pA_BC = egalPosi(position(BC, A),position(BC, X));
		//System.out.println("> Demi plan BC | B : "+ pA_BC);
		//BC.affiDroiteEqua();
		
		Droite CA = new Droite (C,A);
		//int q = position(CA, B);
		//int r = position(CA, X);
		//System.out.println("> Demi plan CA | B = " + q + " , X = "+ r);
		boolean pB_CA = egalPosi(position(CA, B),position(CA, X));
		//System.out.println("> Demi plan CA | B : "+pB_CA);
		//CA.affiDroiteEqua();
		
		// Intérieur ou exterieur : ??
		//System.out.println(pC_AB + " - " + pA_BC + " - " + pB_CA);
		if (pC_AB && pA_BC && pB_CA) {
			// Interieur simple :
			//System.out.println("Intérieur");
			res = 1;
		}
		else {
			// Confondu avec sommet : 
			if (
					pC_AB && !pA_BC && !pB_CA ||
					!pC_AB && pA_BC && !pB_CA ||
					!pC_AB && !pA_BC && pB_CA
			) {
				//System.out.println("Confondu avec sommet");
				res = 0;
			}
			else {
				// Confondu sur droite
				//System.out.println(position(AB,X));
				//System.out.println(position(BC,X));
				//System.out.println(position(CA,X));
				
				if (
					position(AB,X) == 0 ||
					position(BC,X) == 0 ||
					position(CA,X) == 0
					) {
					//System.out.println("Confondu sur droite");
					res = 0;
				}
				else {
					// Exterieur simple
					//System.out.println("Exterieur");
					res = - 1;
				}
			}
		}
		//System.out.println("--");
		return res;
		
		
		// Analyse des exemples : 
		// Interieur : 3 true; 
		// Exterieur : 2 true + 1 false
		// Confondu avec sommet : 2 false + 1 true 
		// A voir pour sur droite : Si l'une des valeurs de position est nulle. 
		
	}
	
	private static boolean egalPosi (int m, int n) {
		return (m == n);
	}
	
	public static ArrayList<Polygone> TriangulationTerrain(ArrayList<Polygone> P){
		ArrayList<Polygone> T = new ArrayList(); 
		//pour chaque polygone
			//on le copi => Q
			//on prend trois points consécutifs A,B,C
			//tant que(il reste plus de trois points dans le polygone){
				//on cherche si le segment AC est entièrement à l'intèrieur du polygone
					//pour ça trois points :
					//1) Si un autre point du polygone est a l'intèrieur du triangle ABC ou sur le segment AC, FALSE
					//2) Créer un segment AD avec D a l'extèrieur du terrain de jeu
					//3) Si le nombre d'intersection propres entre le segment ouvert ]AB[ et le polygone (tout entier) est impair TRUE, sinon FALSE
				//si oui 
					//on ajoute le triangle dans T
					//et on suprime le point B du polygone
				//sinon
					//on décale d'un cran.Mod(Q) le point A
				//finsi
			//fin tant que
			//on ajoute le dernier triangle
		//fin pour chaque polynome
		System.out.println("Triangulation ok");
		return P;
	}
	
	public static void affEns(ArrayList<Point> X) {
		for (int i = 0; i < X.size();i++) {
			X.get(i).affiPointSyso();
		}
	}
	
	
}
