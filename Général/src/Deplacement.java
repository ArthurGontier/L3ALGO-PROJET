import java.util.ArrayList;

public class Deplacement {

	public static void trajecBall(Point A, Point B) {
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
	
	

}
