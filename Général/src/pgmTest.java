import java.util.ArrayList;

public class pgmTest {

	public static void testPosiTri() {
		/*
		//test triangle dans le carré
		Point A = new Point(0, 0);
		Point B = new Point(10, 0);
		Point C = new Point(10, 10);
		Point D = new Point(0, 10);
		ArrayList<Point> X = new ArrayList<Point>();

		Polygone Car = new Polygone(X, 'W');

		Polygone.ajout(X, A);
		Polygone.ajout(X, B);
		Polygone.ajout(X, C);
		Polygone.ajout(X, D);

		Point E = new Point(1, 1);
		Point F = new Point(3, 3);
		Point G = new Point(3, 1);
		*/
		/*
		//test carré dans triangle
		Point A = new Point(1, 1);
		Point B = new Point(2, 1);
		Point C = new Point(2, 2);
		Point D = new Point(1, 2);
		ArrayList<Point> X = new ArrayList<Point>();

		Polygone Car = new Polygone(X, 'W');

		Polygone.ajout(X, A);
		Polygone.ajout(X, B);
		Polygone.ajout(X, C);
		Polygone.ajout(X, D);

		Point E = new Point(0, 0);
		Point F = new Point(10, 0);
		Point G = new Point(10, 10);
		
		ArrayList<Point> Y = new ArrayList<Point>();
		Polygone Tri = new Polygone(Y, 'W');
		Polygone.ajout(Y, E);
		Polygone.ajout(Y, F);
		Polygone.ajout(Y, G);

		Car.polyAff();
		Tri.polyAff();

		ArrayList<Point> TriInCar = new ArrayList<Point>();
		TriInCar = Geom.clipping(Car, Tri);
		Geom.affEns(TriInCar);
		*/
		
		//cas intersection d'un seg sur la borne du deuxième
		
		Point A = new Point(0, 5);
		Point B = new Point(5, 5);
		Point C = new Point(5, 10);
		Point D = new Point(0, 10);
		ArrayList<Point> X = new ArrayList<Point>();

		Polygone Car = new Polygone(X, 'W');

		Polygone.ajout(X, A);
		Polygone.ajout(X, B);
		Polygone.ajout(X, C);
		Polygone.ajout(X, D);

		Point E = new Point(0, 0);
		Point F = new Point(10, 5);
		Point G = new Point(10, 10);
		
		ArrayList<Point> Y = new ArrayList<Point>();
		Polygone Tri = new Polygone(Y, 'W');
		Polygone.ajout(Y, E);
		Polygone.ajout(Y, F);
		Polygone.ajout(Y, G);

		Car.polyAff();
		Tri.polyAff();

		ArrayList<Point> TriInCar = new ArrayList<Point>();
		TriInCar = Geom.clipping(Car, Tri);
		Geom.affEns(TriInCar);System.out.println("Fin de test");

	}
	
	public static void testQT(QuadTree Terre) {
		//Polygone RecherhePointTriangle(Noeud N,Point P)
		//Noeud RecherchePointQT(Noeud N,Point P)
		Point P = new Point(9,1);
		Noeud N = QuadTree.RecherchePointQT(Terre.getRacine(), P);
		Polygone Poly = QuadTree.RecherhePointTriangle(N, P);
		Poly.polyAff();
		
	}
	
}
