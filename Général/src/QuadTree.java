import java.util.ArrayList;

public class QuadTree {
	
	private Noeud racine;
	//private int cpt = 0;
	
	// Constructeur de QuadTree - On initialise le noeud de la racine;
	// Le noeud racine du quadtree et nécessairement un noeud interne. il possède donc
	// 		4 fils 
	// 		0 point
	// On passe en paramètre du quadtree les dimensions initiale de notre quadtree
	// Nous ça sera 10x10;
	
	
	public QuadTree(ArrayList<Point> Pts,ArrayList<Polygone> ensT){
		this.racine = new Noeud(Pts,ensT);
	}
	
	public static void ConstructionQT(Noeud N,int t,ArrayList<Polygone> ensT,String A){
		//si le noeud intersecte t triangles : on met les triangles dans la feuille
		int cpt = 0;
		//parcourt des triangle pour voir combien intersectent le Noeud
		System.out.println(A);
		for(int i = 0; i<ensT.size();i++){
			if(Geom.clipping(N.getCarre(), ensT.get(i)).size() != 0){
				cpt++;
			}
		}
		System.out.println(cpt);
		if(cpt<=t) {
			//si on a le nombre de triangle voulu, on fais du noeud une feuille et on s'arrete.
			ArrayList<Polygone> Triangles = new ArrayList();
			System.out.println("--Art--");
			for(int i = 0; i<ensT.size();i++){
				if(Geom.clipping(N.getCarre(),ensT.get(i)).size() != 0){
					Triangles.add(ensT.get(i));
					ensT.get(i).polyAff();
				}
			}
			N.setFeuille(Triangles);
		}
		else {
			//sinon on créer 4 fils et on appelle la construction récurcivement sur chacun d'eux.
			ArrayList<Polygone> polyNull = new ArrayList(); 
			//je créé d'abord tous les points dont je vais avoir besoin, c'est plus lisible.
			Point SO = N.getCarre().getEns().get(0);
			Point SS = new Point((N.getCarre().getEns().get(1).getX()-N.getCarre().getEns().get(0).getX())/2,N.getCarre().getEns().get(0).getY());
			Point SE = N.getCarre().getEns().get(1);
			Point EE = new Point(N.getCarre().getEns().get(1).getX(),(N.getCarre().getEns().get(2).getY()-N.getCarre().getEns().get(1).getY())/2);
			Point NE = N.getCarre().getEns().get(2);
			Point NN = new Point((N.getCarre().getEns().get(1).getX()-N.getCarre().getEns().get(0).getX())/2,N.getCarre().getEns().get(3).getY());
			Point NO = N.getCarre().getEns().get(3);
			Point OO = new Point(N.getCarre().getEns().get(0).getX(),(N.getCarre().getEns().get(2).getY()-N.getCarre().getEns().get(1).getY())/2);
			Point M = new Point((N.getCarre().getEns().get(1).getX()-N.getCarre().getEns().get(0).getX())/2,(N.getCarre().getEns().get(2).getY()-N.getCarre().getEns().get(1).getY())/2);
			
			ArrayList<Point> BornesTerrain1 = new ArrayList();
			BornesTerrain1.add(OO);
			BornesTerrain1.add(M);
			BornesTerrain1.add(NN);//erreur
			BornesTerrain1.add(NO);
			//for(int l = 0;l<4;l++) {BornesTerrain1.get(l).affiPointSyso();}
			Noeud N1 = new Noeud(BornesTerrain1,polyNull);
			N.setNO(N1);//on le met comme fils de N
			ConstructionQT(N1,t,ensT,"> NO");//appel récurcif
			
			ArrayList<Point> BornesTerrain2 = new ArrayList();
			BornesTerrain2.add(M);
			BornesTerrain2.add(EE);
			BornesTerrain2.add(NE);
			BornesTerrain2.add(NN);
			//for(int l = 0;l<4;l++) {BornesTerrain2.get(l).affiPointSyso();}
			Noeud N2 = new Noeud(BornesTerrain2,polyNull);
			N.setNE(N2);//on le met comme fils de N
			ConstructionQT(N2,t,ensT,"> SO");//appel récurcif
			
			ArrayList<Point> BornesTerrain3 = new ArrayList();
			BornesTerrain3.add(SS);
			BornesTerrain3.add(SE);
			BornesTerrain3.add(EE);
			BornesTerrain3.add(M);
			//for(int l = 0;l<4;l++) {BornesTerrain3.get(l).affiPointSyso();}
			Noeud N3 = new Noeud(BornesTerrain3,polyNull);
			N.setSE(N3);//on le met comme fils de N
			ConstructionQT(N3,t,ensT,"> SE");//appel récurcif
			
			ArrayList<Point> BornesTerrain4 = new ArrayList();
			BornesTerrain4.add(SO);
			BornesTerrain4.add(SS);
			BornesTerrain4.add(M);
			BornesTerrain4.add(OO);
			//for(int l = 0;l<4;l++) {BornesTerrain4.get(l).affiPointSyso();}
			Noeud N4 = new Noeud(BornesTerrain4,polyNull);
			N.setSO(N4);//on le met comme fils de N
			ConstructionQT(N4,t,ensT,"> NE");//appel récurcif
		}
	}
	
	public static Noeud RecherchePointQT(Noeud N,Point P) {
		Noeud res = N;
		if(!N.estFeuille()) {
			if(P.getX()<=N.getSO().getCarre().getEns().get(2).getX()) {
				if(P.getY()<=N.getSO().getCarre().getEns().get(2).getY()) {
					res = RecherchePointQT(N.getSO(),P);
				}else {
					res = RecherchePointQT(N.getNO(),P);
				}
			}else {
				if(P.getY()<=N.getSO().getCarre().getEns().get(2).getY()) {
					res = RecherchePointQT(N.getSE(),P);
				}else {
					res = RecherchePointQT(N.getNE(),P);
				}
			}
		}
		return res;
	}
	
	public static Polygone RecherhePointTriangle(Noeud N,Point P) {//on sais que N est une feuille
		int i = 0;
		//System.out.println("OUOH");
		while(i<N.getEnsTri().size() && 1!=Geom.posTriangle(N.getEnsTri().get(i).getEns().get(0), N.getEnsTri().get(i).getEns().get(1), N.getEnsTri().get(i).getEns().get(2), P)) {			
			//N.getEnsTri().get(i).polyAff();
			i++;		//System.out.println("dans la boucle");
		}
		System.out.println(N.getEnsTri().size());
		return N.getEnsTri().get(i);
	}
	
	public Noeud getRacine(){
		return this.racine;
	}
	
	
	
}

