/*
 * BETA CLAVIER
 * .
 */

/**
 *
 * @author iks-d
 */
import java.awt.*;
import java.awt.event.*;
import static java.lang.Math.abs;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import org.jfugue.player.Player;
import org.jfugue.theory.Chord;
import org.jfugue.theory.Note;
/**
 *
 * @author Mihary
 */
/** Classe clavier*/
public class Clavier implements ActionListener, ItemListener{
    String bla[] = new String[50]; //Utilisé pour la génération d'une note aléatoire
    public String[] notejouee = new String[50]; //Utilisé pour stocker les notes jouées au clavier
    /** La frame*/
    public JFrame frame;
    /** Nombre de notes*/
    public static final int NUM_KEYS = 7;
    /** Nombre d'octaves*/
    public static final int NUM_OCTAVES = 3;
    /** Notes romandes*/
    private String[] notes1 = {"Do","Re","Mi","Fa","Sol","La","Si"};
    /** Notes saxones*/
    private String[] notes = {"C","D","E","F","G","A","B"};
    /** Dièses*/
    private String[] diese = {"Do#","Re#","Fa#","Sol#","La#"};
    /** Sharps*/
    private String[] sharps = {"C#","D#","F#","G#","A#"};
    /** Octaves du clavier*/
    private String[] octave = {"4","5","6"};
    /** Player du jfugue*/
    private Player player = new Player();
    /** Label utilisés dans la fenêtre*/
    public JLabel titleLabel, messageLabel, appreciationLabel;
    /** Combo pour le choix d'exercice */
    public JComboBox combo;
    /** Conteneur principal*/
    public Container mainPanel;
    /** Couleur utilisée en background*/
    public Color couleur;
    /** Panel contenant le combo*/
    public JPanel panelCombo;
     /** Panel contenant le combo*/
    public JPanel panelRadio;
    /** Panel du piano*/
    public JPanel panelPiano;
    /** Radio pour le choix d'exercice*/
    public ButtonGroup radio;
    /** Radio majeur*/
    public JRadioButton rMajeur;
    /** Radio majeur*/
    public JRadioButton rMineur;
    /** Simulateur*/
    public Clavier()
    {
        /** Interface graphique*/
        frame = new JFrame("Simulateur v 1.0"); //Titre de la frame
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        /** Panel principal*/
        Color loko = Color.yellow; //loko est la couleur du fond
        mainPanel = frame.getContentPane();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		mainPanel.setForeground(Color.WHITE);
		mainPanel.setBackground(loko);
        mainPanel.add(Box.createRigidArea(new Dimension(0,10)));
        
         /** Title label*/
                titleLabel = new JLabel(" Sur quelle tonalité voulez-vous exercer?  ");
		titleLabel.setFont(new Font("Verdana", Font.BOLD, 26));
		titleLabel.setForeground(Color.WHITE);
		titleLabel.setBackground(Color.BLACK);
		mainPanel.add(titleLabel);
        mainPanel.add(Box.createRigidArea(new Dimension(150,20)));
        
        /** Appel de l'interface du piano*/
        JLayeredPane pianoKeyPanel = makeKeys();  
        mainPanel.add(pianoKeyPanel);
      
        /** Appreciation Label*/
        appreciationLabel = new JLabel("Appreciation");
        appreciationLabel.setVisible(false);
        appreciationLabel.setFont(new Font("Arial", Font.BOLD, 30));
        mainPanel.add(appreciationLabel);
        
        /** Choix exercice */
        String[] choix = {"majeur","mineur"};
        panelCombo = new JPanel();
        panelCombo.setSize(new Dimension(50, 100));
        panelCombo.setBackground(loko);
        panelCombo.setVisible(false);
        combo = new JComboBox(choix);
        panelCombo.add(combo);
        combo.addActionListener(this);
        combo.addItemListener(this);
        //combo.setSize(50, 100);
        mainPanel.add(panelCombo);
        
         /** Choix exercice radio*/
        panelRadio = new JPanel();
        panelRadio.setSize(50, 100);
        panelRadio.setBackground(loko);
        radio = new ButtonGroup();
        rMajeur = new JRadioButton("MAJEUR");
        rMajeur.setBackground(loko);
        radio.add(rMajeur);
        panelRadio.add(rMajeur);
        mainPanel.add(panelRadio);
        rMajeur.addActionListener(this);
        
        rMineur = new JRadioButton("MINEUR");
        rMineur.setBackground(loko);
        radio.add(rMineur);
        panelRadio.add(rMineur);
        mainPanel.add(panelRadio);
        //mainPanel.add(rMineur);
        rMineur.addActionListener(this);
        
        /** Ajout du label Message */
        
        messageLabel = new JLabel("Les notes que vous allez jouer se trouveront ici");
        messageLabel.setVisible(false);
        mainPanel.add(messageLabel);
        /** Bouton vers menu principal*/
        JButton menuButton = new JButton ("Menu Principal");
        //JPanel panelButton = new JPanel();
        menuButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        mainPanel.add(menuButton);
        mainPanel.add(Box.createRigidArea(new Dimension(150 , 20)));
        
        /** Affichage de la frame*/
        frame.setVisible(true);
        frame.setSize(900, 520);
        frame.setResizable(false);
        
    }
    
    public void Colorier(Color couleur)
    {
        this.couleur = couleur;
        mainPanel.setBackground(couleur);
        panelCombo.setBackground(couleur);
        panelRadio.setBackground(couleur);
        rMineur.setBackground(couleur);
        rMajeur.setBackground(couleur);
        
    }
    public void itemStateChanged (ItemEvent e)
    {
        System.out.print("item combo : ");
        Object valeur = combo.getSelectedItem();
        System.out.println((String) valeur);
    }

    public JLayeredPane makeKeys(){
		// Initialisation
                System.out.println("on est dans makeKeys()");
		String name = "";
                String name1 = "";
		int x = 55;
		int y = 0;
		
		// Creation layerPane
		/*JLayeredPane*/ keyBoard = new JLayeredPane();
		keyBoard.setPreferredSize(new Dimension(900,162));
		keyBoard.add(Box.createRigidArea(new Dimension(x, 0)));

        // Ajout des notes blanches
        for(int i=0; i< NUM_OCTAVES; i++){
        	for(int j=0; j<NUM_KEYS; j++){
        		
                        JButton jb = new JButton();
                        jb.setBackground(Color.WHITE);
        		name1 = notes1[j]+octave[i];
                        name = notes[j]+octave[i];
        		jb.setName(name1);
        		jb.setActionCommand(name);
        		jb.addActionListener(this);
        		jb.setBounds(x,y,35,162);
        		keyBoard.add(jb,new Integer(1));
        		keyBoard.add(Box.createRigidArea(new Dimension(2, 0)));
        		x += 37;
        	}
        }
        
        // Reinitialisation
        x = 0;
        
        // Ajout des touches noires
        for(int i=0; i< NUM_OCTAVES; i++){
        	
    		
    		// Création des 5 "keys"
    		
    		
                JButton jb0 = new JButton();
                jb0.setBackground(Color.black);
                name1 = diese[0]+octave[i];
    		name = sharps[0]+octave[i];
    		jb0.setName(name);
    		jb0.setActionCommand(name);
    		jb0.addActionListener(this);
    		
    		
                JButton jb1 = new JButton();
                jb1.setBackground(Color.black);
    		name1 = diese[1]+octave[i];
                name = sharps[1]+octave[i];
    		jb1.setName(name);
    		jb1.setActionCommand(name);
    		jb1.addActionListener(this);
    		
    		
    		JButton jb2 = new JButton();
                jb2.setBackground(Color.black);
                name1 = diese[0]+octave[i];
                name = sharps[2]+octave[i];
    		jb2.setName(name);
    		jb2.setActionCommand(name);
    		jb2.addActionListener(this);
    		
    		
    		JButton jb3 = new JButton();
                jb3.setBackground(Color.black);
                name = sharps[3]+octave[i];
    		jb3.setName(name);
    		jb3.setActionCommand(name);
    		jb3.addActionListener(this);
    		
    		
    		JButton jb4 = new JButton();
                jb4.setBackground(Color.black);
                name = sharps[4]+octave[i];
    		jb4.setName(name);
    		jb4.setActionCommand(name);
    		jb4.addActionListener(this);
    		
    		// Placement des 5 touches 
    		jb0.setBounds(77+(260*i),y,25,95);
    		keyBoard.add(jb0,new Integer(2));
    		
    		jb1.setBounds(115+(260*i),y,25,95);
    		keyBoard.add(jb1,new Integer(2));
    		
    		jb2.setBounds(188+(260*i),y,25,95);
    		keyBoard.add(jb2,new Integer(2));
        	
    		jb3.setBounds(226+(260*i),y,25,95);
    		keyBoard.add(jb3,new Integer(2));
   
    		jb4.setBounds(264+(260*i),y,25,95);
    		keyBoard.add(jb4,new Integer(2));
                
                
        }
        return keyBoard;
    }
    /*class EcouteClic extends MouseAdapter
            {
        private int o;
        private final JPanel panelPiano;
                public EcouteClic(JPanel panelPiano)
                {
                    this.panelPiano = panelPiano;
                }
                public void mouseClicked(MouseEvent e)
                {
                    o++;
                    System.out.println("Tu as clickééééééééééééééééé " + o  + " fois");
                }
            }
    */
    /** Gestion des evenements */
    public void actionPerformed(ActionEvent e) {
        
        if (e.getSource() == rMajeur)
        {
            System.out.println("NICE TENA TSARA");
            Generation();
            Majeur();
            Nettoyer();
            bCompteur = true;
        }
        if (e.getSource() == rMineur)
        {
            System.out.println("AOKA FA RAITRA");
            Generation();
            Mineur();
            Nettoyer();
        }
        /** Choix dans le combobox  */
        /*if (e.getSource() == combo)
        {
                System.out.print("Action combo : ");
                Object valeur = combo.getSelectedItem();
                System.out.println((String) valeur);
                String choix = (String) valeur;
                System.out.println("le choix est :"+choix);
                //Appel de la génération de note aléatoire
                Generation();
            /** Dans le cas ou on a choisi majeur*/
        /*    if(choix == "majeur")
            {
                Majeur();
                Nettoyer();
            }
            else
            {
                Mineur();
                Nettoyer();
            }
        }
        */
        /** Pour les touches du simulateur */
        else{
        JButton jb = null;
        String note = "";
        
        Object obj = e.getSource();
        jb = (JButton)obj;
        jb.setBackground(Color.red);//colorie chaque touche jouée en rouge
        note = jb.getActionCommand();
        player.play(note);
        //notejouee avec l'indice
        notejouee[indNJ] = (String)note;
        System.out.println("la valeur dans note jouee[" + indNJ + "] = "+(String) notejouee[indNJ]);
        indNJ++;
        nbNotes++;
        //System.out.println(notejouee);
        recupNote = (String)note.substring(0,1);//récupération de la note
        System.out.println("Longueur de recupnote : "  +  recupNote.length());
        recupOctave = (String)note.substring(1, 2);//récupération de l'octave
       //laNoteEnFr = "Do"+recupOctave;
         //   System.out.println("la note en français : " + laNoteEnFr);
        
        NoteFrancaise();
        Comparaison();
        
        
        
        System.out.println("on a récupéré la note= " + recupNote);
        System.out.println("on a récupéré l'octave = " + recupOctave);
        //System.out.println("la note en français : " + laNoteEnFr);
        messageLabel.setText("vous avez appuyé sur : "+laNoteEnFr);
        touchePressee = true;
        //messageLabel.setText("vous avez appuyé sur : "+note);
        System.out.println("nombre de touche appuyees : "+nbNotes);
        if (nbNotes == 3)
            {
           //fd System.out.println(nbNotes);
            System.out.println(notejouee[0] + " " + notejouee[1] + " " + notejouee[2] );
            System.out.println(note);
            }
        }
        
    }
    
    /** On utilise cette methode pour comparer la note à trouver à celle jouée par l'apprenant*/
    public void Comparaison()
    {
        String noteTempo ;
        noteTempo = laNoteEnFr; //ici on prend la note jouée par l'apprenant
        System.out.println("La valeur de bMajeur dans comparaison: " + bMajeur);
        System.out.println("==>la valeur de noteTempo avant le test = " + noteTempo);
        System.out.println("la longueur de noteTempo est : " + noteTempo.length());
        System.out.println("==>la valeur de debutFr avant le test = " + debutFr);
        System.out.println("la longueur de debutFr est : " + debutFr.length());
        if (bMajeur == true)//S'il a choisi de faire de l'exercice avec la tonalité majeure
        {//if majeur
            if (noteTempo.length()==3)
            {    
                if (noteTempo.equals(debutFr.substring(0,3)) )

                {
                    /*System.out.println("la note dans debutFr : "+debutFr);
                    System.out.println("la note dans laNoteEnFr : "+laNoteEnFr);
                    System.out.println("la note dans noteTempo : "+noteTempo);
                    */System.out.println("YOOOOOOOOOOOOOLO");
                    
                    appreciationLabel.setText(" Bien joué ");
                    appreciationLabel.setVisible(true);
                    appreciationLabel.setForeground(Color.GREEN);
                    //On ajoutera 1 point
                }

                else
                {
                    System.out.println("Désolé c'est faux ");
                    /*System.out.println("la note dans debutFr : "+debutFr);
                    System.out.println("la note dans laNoteEnFr : "+laNoteEnFr);
                    System.out.println("la note dans noteTempo : "+noteTempo);
                    */System.out.println("NOOOOOOOOOOOOOB");
                    appreciationLabel.setText("vous avez appuyé sur : "+laNoteEnFr );
                    appreciationLabel.setVisible(true);
                    appreciationLabel.setForeground(Color.RED);
                    //On donnera la correction
                    //leContenu[17].setBackground(Color.BLUE);
                    //System.out.println("le nom de l'element dans leContenu : " + leContenu[17].getName());
                    //System.out.println("la longueur du nom de l'element dans leContenu : " + leContenu[17].getName().length());
                    for (int nn = 15 ; nn < 36; nn++)
                    {
                        if (leContenu[nn].getName().equals(debutFr.substring(0,3)))
                                {
                                    leContenu[nn].setBackground(Color.MAGENTA);
                                }
                        else if (leContenu[nn].getName().equals(debutFr.substring(0,4)))
                                {
                                    System.out.println("on a réussi YATTA");
                                    leContenu[nn].setBackground(Color.MAGENTA);
                                }
                    }
                    
                }
            }
            else if (noteTempo.length()==4)//Dans le cas ou on appuie sur une touche Sol
            {    
                if (noteTempo.equals(debutFr.substring(0,4)) ) //Si on a trouvé le bon

                {
                    /*System.out.println("la note dans debutFr : "+debutFr);
                    System.out.println("la note dans laNoteEnFr : "+laNoteEnFr);
                    System.out.println("la note dans noteTempo : "+noteTempo);
                    */System.out.println("YOOOOOOOOOOOOOLO");
                    appreciationLabel.setText(" Bien joué ");
                    appreciationLabel.setVisible(true);
                    appreciationLabel.setForeground(Color.GREEN);
                    //On ajoutera 1 point
                }

                else //Si on a raté
                {
                    System.out.println("Désolé c'est faux ");
                    
                    System.out.println("******************NOOOOOOOOOOOOOB********************");
                    appreciationLabel.setText("vous avez appuyé sur : "+laNoteEnFr);
                    appreciationLabel.setVisible(true);
                    appreciationLabel.setForeground(Color.RED);
                    for (int nn = 15 ; nn < 36; nn++)
                    {
                        if (leContenu[nn].getName().equals(debutFr.substring(0,3)))
                                {
                                    leContenu[nn].setBackground(Color.MAGENTA);
                                }
                        else if (leContenu[nn].getName().equals(debutFr.substring(0,4)))
                                {
                                    System.out.println("on a réussi YATTA");
                                    leContenu[nn].setBackground(Color.MAGENTA);
                                }
                    }
                }
            }
            
        }
        if (bMineur == true)//S'il a choisi de faire de l'exercice avec la tonalité mineure
        {
            if (noteTempo.length()==3)
            {    
                if (noteTempo.equals(debutFr.substring(0,3)) )

                {
                    
                    System.out.println("YOOOOOOOOOOOOOLO");
         
                    appreciationLabel.setText(" Bien joué ");
                    appreciationLabel.setVisible(true);
                    appreciationLabel.setForeground(Color.GREEN);
                    //On ajoutera 1 point
                }

                else
                {
                    System.out.println("Désolé c'est faux ");
                    
                    System.out.println("NOOOOOOOOOOOOOB");
                    appreciationLabel.setText("Oups !vous avez appuyé sur : "+laNoteEnFr);
                    appreciationLabel.setVisible(true);
                    appreciationLabel.setForeground(Color.RED);
                    //On donne ici la correction
                    for (int nn = 15 ; nn < 36; nn++)
                    {
                        if (leContenu[nn].getName().equals(debutFr.substring(0,3)))
                                {
                                    leContenu[nn].setBackground(Color.MAGENTA);
                                }
                        else if (leContenu[nn].getName().equals(debutFr.substring(0,4)))
                                {
                                    System.out.println("on a réussi YATTA");
                                    leContenu[nn].setBackground(Color.MAGENTA);
                                }
                    }
                }
            }
            else if (noteTempo.length()==4)//Dans le cas ou on appuie sur une touche Sol
            {    
                if (noteTempo.equals(debutFr.substring(0,4)) ) //Si on a trouvé le bon

                {
                    System.out.println("YOOOOOOOOOOOOOLO");
                    appreciationLabel.setText(" Bien joué ");
                    appreciationLabel.setVisible(true);
                    appreciationLabel.setForeground(Color.GREEN);
                    //On ajoutera 1 point
                }

                else //Si on a raté
                {
                    System.out.println("Désolé c'est faux ");
                    
                    System.out.println("******************NOOOOOOOOOOOOOB********************");
                    appreciationLabel.setText("oups !vous avez appuyé sur : "+laNoteEnFr);
                    appreciationLabel.setVisible(true);
                    appreciationLabel.setForeground(Color.RED);
                    for (int nn = 15 ; nn < 36; nn++)
                    {
                        if (leContenu[nn].getName().equals(debutFr.substring(0,3)))
                                {
                                    leContenu[nn].setBackground(Color.MAGENTA);
                                }
                        else if (leContenu[nn].getName().equals(debutFr.substring(0,4)))
                                {
                                    System.out.println("on a réussi YATTA");
                                    leContenu[nn].setBackground(Color.MAGENTA);
                                }
                    }
                }
            }
            
        }
        
        debutFr = "";//Pour éviter de prendre en compte la pression d'autres touche du clavier
        noteTempo = "";
        //Mettre un booléen pour enclencher l'exercice suivante
    }
    public void NoteFrancaise()
    {
        
        if (recupNote.equals("C"))
        {
            laNoteEnFr = "Do"+recupOctave;
            System.out.println("la note en français : " + laNoteEnFr);
        }
        else if (recupNote.equals("D"))
        {
            laNoteEnFr = "Re"+recupOctave;
            System.out.println("la note en français : " + laNoteEnFr);
        }
        else if (recupNote.equals("E"))
        {
            laNoteEnFr = "Mi"+recupOctave;
            System.out.println("la note en français : " + laNoteEnFr);
        }
        else if (recupNote.equals("F"))
        {
            laNoteEnFr = "Fa"+recupOctave;
            System.out.println("la note en français : " + laNoteEnFr);
        }
        else if (recupNote.equals("G"))
        {
            laNoteEnFr = "Sol"+recupOctave;
            System.out.println("la note en français : " + laNoteEnFr);
        }
        else if (recupNote.equals("A"))
        {
            laNoteEnFr = "La"+recupOctave;
            System.out.println("la note en français : " + laNoteEnFr);
        }
        else if (recupNote.equals("B"))
        {
            laNoteEnFr = "Si"+recupOctave;
            System.out.println("la note en français : " + laNoteEnFr);
        }
        
         
        else{
            System.out.println("On est sorti de la boucleeeeeee");
        }
            
    }
    public void Majeur()
    {
        
        System.out.println("Vous avez choisi majeur");
        bMajeur = true;
        bMineur = false;
        //String choisi = choix;
        String message = "Trouvez la note jouée: " + debutFr + " avec la tonalité majeur";
        titleLabel.setText(message);
        Chord accord = new Chord(debut);
        Note[] naoty = accord.getNotes();
        System.out.print(naoty[0]+" ");
        System.out.print(naoty[1]+" ");
        System.out.print(naoty[2] + "\n") ;
        player.delayPlay(100, accord+ "QQQQQQ");
        String comMajStr = Integer.toString(compteurMajeur);
        String naoty0 = naoty[compteurMajeur].toString();
        String naoty1 = naoty[1].toString();
        String naoty2 = naoty[2].toString();
        String noteJouee0 = notejouee[0];
        System.out.println("la note jouee dans dans notejouee0 " + noteJouee0);
        System.out.println("la valeur de naoty0 est :" +naoty0);
        
        
        //leContenu[4].setBackground(Color.PINK);
    }
    
    public void Mineur()
    {
         String message = "Trouvez la note jouée : " + debutFr+" avec la tonalité mineur";
                bMineur = true;
                bMajeur = false;
                //JOptionPane.showMessageDialog(null, message);
                titleLabel.setText(message);
                //frame.repaint();
                System.out.println("Vous avez choisi mineur");
                //player.play("CminQQQQ");
                Chord mineur = new Chord(debut+"min"); 
                Note[] minora = mineur.getNotes();
                System.out.print(minora[0]+" ");
                System.out.print(minora[1]+" ");
                System.out.print(minora[2]);
                player.delayPlay(100, mineur+"QQQQQQ");
                //player.delayPlay(20, minora[0] + "QQQQ");
                //player.delayPlay(20, minora[1] + "QQQQ");
                //player.delayPlay(20, minora[2] + "QQQQ");
                //player.play(minora[0] + "QQQ");
                //player.play(minora[1] + "QQQ");
                //player.play(minora[2] + "QQQ");
    }
    public void Generation()
    {
        /** Generation d'une note aléatoire*/
                Random random = new Random();
                int n, k, m;
                n = random.nextInt();
                k = abs(n);
                m = k%17;

                System.out.println(n);
                System.out.println(k);
                System.out.println(m);
                String notesFr [] = {"Do", "Re", "Mi", "Fa", "Sol", "La", "Si"};
                String notes [] = {"C", "D", "E", "F", "G", "A", "B"};
                String[] octave = {"4","5","6"};
                String nameFr = "";
                String name = "";
                String noteFr = "";
                String note= "";
                String bla[] = new String[50];
                String blaFr[] = new String[50];
                int g = 0;
                //Création de la liste des notes jouables sur le clavier
                    for(int i=0; i< NUM_OCTAVES; i++){
                            for(int j=0; j<NUM_KEYS; j++){
                            nameFr = notesFr[j]+octave[i]+" ";
                            name = notes[j]+octave[i]+" ";
                            noteFr += nameFr+" ";
                            note += name+" ";
                            blaFr[g] = nameFr;
                            bla[g] = name;
                            g++;
                            }
                        }
                     debut = bla[m].substring(0,2);
                     debutFr = blaFr[m].substring(0,4);
                    System.out.println(debut);
                    System.out.println("blaFR "+debutFr);
    }
    /** Cette methode permet de recolorier les touches du clavier*/
    public void Nettoyer()
    { 
        appreciationLabel.setText("");
        appreciationLabel.setVisible(false);
        
        System.out.println("on procède au nettoyage");
        //On stocke les composants de la JLayeredPane keyBoard dans un tableau 
        //leContenu de type Component
        leContenu = keyBoard.getComponents();
        
        /* Pour vérifier les éléments dans leContenu
        for (int y =0 ; y<leContenu.length; y++)
        {
            System.out.println("ce qu'il y a dans leContenu " + y + "  : " + leContenu[y]);
        }
        */
        //System.out.println(leContenu.length);
        
        //on recolorie les touches noires
        for (int nd = 0; nd < 15 ; nd++)
        {
            leContenu[nd].setBackground(Color.BLACK);
        }
        //on recolorie les touches blanches
        for (int nn = 15 ; nn < 36; nn++)
        {
            leContenu[nn].setBackground(Color.WHITE);
        }
        //on réinitialise notejoee
        /*for (int nj = 0; nj<notejouee.length; nj++)
        {
            notejouee[nj] = "";
        }
        */
        //on réinitialise nbnote
        nbNotes = 0;
        
        //on réinitialise l'indice du tableau
        indNJ = 0;
        
        //touche pressée
        touchePressee = false;
        
    }
    public void Compteur()
    {
       if(bCompteur == true)
       {
           System.out.println("On teste dans le compteur");
       }
        
    }
    /** indNJ = indice des notes jouées, nbNotes = nombre total de touche jouees*/
    public Component[] leContenu;
    public JLayeredPane keyBoard;
    public int compteurMajeur;
    public int indNJ, nbNotes;
    public String noteParlApprenant, debut, debutFr, laNoteEnFr, recupNote, recupOctave;
    public boolean touchePressee, bMajeur, bMineur, bCompteur;
     /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Clavier cl = new Clavier();
        String sexe = "";
        if (sexe == "masculin")
        cl.Colorier(Color.blue);
        else
            cl.Colorier(Color.lightGray);
        
       
       // cl.ComboEssai();
        
    }
}
    
