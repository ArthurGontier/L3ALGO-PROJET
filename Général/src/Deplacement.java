import java.util.ArrayList;

public class Deplacement {

	public static void CalculPointAtterrissageBalle(Point A, Point B) {
		// Cette méthode prend deux points en entrée et retourne un point en sortie 
		// Ce point est initialement calculé théoriquement
		// PUIS, on lui affecte des valeurs choisie aléatoirement pour simuler l'effet du vent sur la trajectoire théorique de la balle
		System.out.println("Données Initiales \n Point Départ : " + A.affiPoint() + "\n Point d'arrivée : " + B.affiPoint());
		// Distance euclidienne : \sqrt{(X_b-X_a)^{2} + (Y_b-Y_a)^{2}}
		//  x = X_A = A.getX();
		//  z = X_B = B.getX();
		//  y = Y_A = A.getY();
		//  t = Y_B = B.getY();
		double x = A.getX();
		double y = A.getY();
		double z = B.getX();
		double t = B.getY();
		//System.out.println(x + " - " +y + " || " + z + " - " + t );		
		double X = Math.pow(x-z, 2);
		double Y = Math.pow(z-t, 2);
		//System.out.println(X + " - " +Y);
		double D = Math.sqrt(X+Y);
		System.out.println("Distance théorique = " + D );
		System.out.println("----------------------------");
		//double novD = D + D*0.4;
		// Math.cos nécessite des radians et non des degrés. => On doit recourir à la fonction Math.toRadians(X)
		
		System.out.println("----------------------------");
		double weD = windEffect();
		double weA = windEffect();
		
		System.out.println("Effet du vent - Distance = " + weD + " % " );
		System.out.println("Effet du vent - Angle (deg) = " + weA +" (rad) "+ Math.toRadians(weA) );
		
		double novD = D + (D * weD)/100;
		System.out.println("Distance après effet du vent = " + novD );
		
		
		
	}
	
	private static int windEffect() {
		//  random() * (max - (min) + 1)) + min ;
		int T[] = {-40,-10,-9,-8,-7,-6,-5,-4,-3,-2,-1,0,1,2,3,4,5,6,7,8,9,10,40};
		/*for (int i = 0; i < T.length; i++) {
			System.out.println(T[i]);
		}*/
		
		/*for (int i = 0; i < T.length; i++) {
			int k = (int) (Math.random()*(T.length));
			System.out.println(k + " : " + T[k]);
		}*/
		int k = (int) (Math.random()*(T.length));
		return T[k];
	}
	
	public static Point CalculPointDepartBalle(QuadTree T,Point depart, Point atterri){//les conditions de la tape depuis le sable et de la tape depuis le green a moin d'un mètre doivent êtrent gérés au lancé
		Point res = new Point(1000,1000);
		if(atterri.getX() < T.getRacine().getCarre().getEns().get(0).getX()
			||atterri.getX() > T.getRacine().getCarre().getEns().get(1).getX()
			||atterri.getY() < T.getRacine().getCarre().getEns().get(0).getY()
			||atterri.getY() > T.getRacine().getCarre().getEns().get(3).getY()){//cas en dehors des limites
			res = depart;
		}
		else{
			Noeud feuille = T.RecherchePointQT(T.getRacine(),atterri);
			Polygone triangle = QuadTree.RecherhePointTriangle(feuille,atterri); 
			if(triangle.getCouleur() == 'S'){//cas tombé dans les sapins (hors jeu)
				res = depart;
			}
			else if(triangle.getCouleur() == 'B'){//cas tombé dans l'eau
				Point P1 = Geom.segIntersection(depart, atterri, triangle.getEns().get(0), triangle.getEns().get(1));
				Point P2 = Geom.segIntersection(depart, atterri, triangle.getEns().get(1), triangle.getEns().get(2));
				Point P3 = Geom.segIntersection(depart, atterri, triangle.getEns().get(2), triangle.getEns().get(0));
				if(P1.getX()*P1.getX()+P1.getY()*P1.getY() > P2.getX()*P2.getX()+P2.getY()*P2.getY()){
					P1 = P2;
				}
				else if(P1.getX()*P1.getX()+P1.getY()*P1.getY() > P3.getX()*P3.getX()+P3.getY()*P3.getY()){
					P1 = P3;
				}
				Point ptrecursif = new Point(-(P1.getX() - depart.getX())/100000, -(P1.getY() - depart.getY())/100000);
				res = CalculPointDepartBalle(T,depart, ptrecursif);
			}
		}
		return res;
	}
}
