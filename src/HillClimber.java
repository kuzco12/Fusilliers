import java.util.Random;


public class HillClimber {
	
	//D�claration de l'automate et des r�gles
	int n = 5 + 1;
	int [] rules = new int[n * n * n];
	Initialization init;
	Automata automate;
	
	public HillClimber()
	{
		//Initialisation de l'automate et des r�gles
		Random generator = new Random();
		init = new Initialization();
		automate = new Automata(20);

		try {

			for(int i=0;i<216;i++)
			{		
				rules[i] = generator.nextInt(4);
			} 
		}		
		catch (Exception e){
			System.out.println(e.toString());
		}
	}
	
	public void iterer(int nb_iterations, int [] regles)
	{
		//Nouvelle Random Seed
		Random generator = new Random();
		
		//Tableau de r�gles qui contiendra celles de la solution pr�c�dent si le voisin est moins bon
		int [] anciennes_regles;
		
		//Indice de la r�gle � changer
		int regle_a_changer = 0;
		int fit = 0;
		
		
		for (int i = 1; i > 0; i=1) {
			
			//On r�initialise les r�gles pour garder celles de Initialization
			init.init(regles);
			//On stocke les r�gles pour pouvoir les recopier en cas de moins bonne performance
			anciennes_regles = copier_tableau(regles);
			//On choisit une r�gle au hasard � changer
			regle_a_changer = generator.nextInt(regles.length);
			//On lui affecte une valeur al�atoire
			regles[regle_a_changer] = generator.nextInt(4);
			
			if(automate.f(regles, 20) >= fit)
			{
				if(automate.f(regles, 20) > fit) System.out.println("Nouvelle performance : " + fit);
				fit = automate.f(regles, 20);
			}
			else
			{
				regles = copier_tableau(anciennes_regles);
				//System.out.println("BAD : " + automate.f(regles, 20));
			}
		}
		System.out.println("Fin");
		
	}
	
	//Fonction qui permet de recopier un tableau en entr�e
	public int[] copier_tableau(int[] tableau_a_copier)
	{
		int[] result = new int[tableau_a_copier.length];
		
		for (int i = 0; i < tableau_a_copier.length; i++) {
			result[i] = tableau_a_copier[i];	
		}
		
		return result;
	}

}
