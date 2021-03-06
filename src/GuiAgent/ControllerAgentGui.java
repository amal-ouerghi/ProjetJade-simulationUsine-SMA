package GuiAgent;


import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;

import Objects.Produits;

import javax.swing.border.*;

import jade.core.*;
import jade.gui.*;
import jade.util.leap.List;


public class ControllerAgentGui extends JFrame implements ActionListener {
// -----------------------------------------------------------------------

   private JList list;
   private DefaultListModel listModel;
   //private JComboBox locations;
   private JButton newCommandes, quit;
   private ControllerAgent myAgent;
   /*
   private JPanel contentPane;
   private JPanel contentPaneC;
   private JTable ProduitsTable;
   private JTable Clients;
   private String[] column = {"Nom","Stock","fournisseurs"};
   private DefaultTableModel tableModel;
   */
   public ControllerAgentGui(ControllerAgent a, Set s) {
	   
      super("Commercial");
      this.myAgent = a;
      JPanel base = new JPanel();
      base.setBorder(new EmptyBorder(15,15,15,15));
      base.setLayout(new BorderLayout(10,0));
	  getContentPane().add(base);


	  JPanel pane = new JPanel();
	  base.add(pane, BorderLayout.WEST);
      pane.setLayout(new BorderLayout(0,10));

      
      listModel = new DefaultListModel();
      list = new JList(listModel);
      
      list.setBorder(new EmptyBorder(0,0,0,0));
      list.setVisibleRowCount(12);
      list.setFixedCellHeight(16);
      list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
      pane.add(new JScrollPane(list), BorderLayout.NORTH);
/*
      JPanel p = new JPanel();
      p.setLayout(new GridLayout(1,2,5,0));
      p.add(new JLabel("Destination :"));
      locations = new JComboBox(s.toArray());
      p.add(locations);
      pane.add(p, BorderLayout.CENTER);

      
      p = new JPanel();
      p.setLayout(new GridLayout(1,3,5,0));
      p.add(move = new JButton("Move"));
      move.setToolTipText("Move agent to a new location");
      move.addActionListener(this);
      p.add(clone = new JButton("Clone"));
      clone.setToolTipText("Clone selected agent");
      clone.addActionListener(this);
      p.add(kill = new JButton("Kill"));
      kill.setToolTipText("Kill selected agent");
      kill.addActionListener(this);
      pane.add(p, BorderLayout.SOUTH);
	  move.setEnabled(false);
	  clone.setEnabled(false);
	  kill.setEnabled(false);
      list.addListSelectionListener( new ListSelectionListener() {
	  	 public void valueChanged(ListSelectionEvent e) {
	  		if (list.getSelectedIndex() == -1){
			   move.setEnabled(false);
			   clone.setEnabled(false);
			   kill.setEnabled(false);
			}
			else {
			   move.setEnabled(true);
			   clone.setEnabled(true);
			   kill.setEnabled(true);
			}
	  	 }
	  });
      
      */
      
      
      pane = new JPanel();
      pane.setBorder(new EmptyBorder(0,0,110,0));
	  base.add(pane, BorderLayout.EAST);
      pane.setLayout(new GridLayout(2,1,0,5));
      /*
      pane.add(newAgent = new JButton("New agent"));
      newAgent.setToolTipText("Create a new agent");
      newAgent.addActionListener(this);
      */
      JLabel client = new JLabel();
      client.setFont(new Font("Tahoma", Font.PLAIN, 15));
      //livraison.setBounds(250, 100, 250, 490);
      //contentPane.add(livraison);
      pane.add(client = new JLabel("Clients :"));
      pane.add(newCommandes = new JButton("new Commandes"));
      newCommandes.setToolTipText("Create a new Commandes");
      newCommandes.addActionListener(this);
      
      pane.add(quit = new JButton("Quit"));
      quit.setToolTipText("Terminate this program");
      quit.addActionListener(this);
      

      addWindowListener(new WindowAdapter() {
	     public void windowClosing(WindowEvent e) {
		    shutDown();
		 }
	  });

      setSize(300, 210);
      setResizable(false);
      pack();
   }

   public void actionPerformed(ActionEvent ae) {
// ---------------------------------------------

	   /*
      if (ae.getSource() == newAgent) {

         GuiEvent ge = new GuiEvent(this, myAgent.NEW_AGENT);
         myAgent.postGuiEvent(ge);
	  }
      else 
    	 */ 
     if (ae.getSource() == newCommandes) {

          GuiEvent ge = new GuiEvent(this, myAgent.NEW_Commande);
          myAgent.postGuiEvent(ge);
 	  }
      /*
      else if(ae.getSource() == move) {

         GuiEvent ge = new GuiEvent(this, myAgent.MOVE_AGENT);
         ge.addParameter((String)list.getSelectedValue());
         ge.addParameter((String)locations.getSelectedItem());
         myAgent.postGuiEvent(ge);
	  }
      else if (ae.getSource() == clone) {

         GuiEvent ge = new GuiEvent(this, myAgent.CLONE_AGENT);
         ge.addParameter((String)list.getSelectedValue());
         ge.addParameter((String)locations.getSelectedItem());
         myAgent.postGuiEvent(ge);
	  }
	  else if (ae.getSource() == kill) {

         GuiEvent ge = new GuiEvent(this, myAgent.KILL_AGENT);
         ge.addParameter((String)list.getSelectedValue());
         myAgent.postGuiEvent(ge);
	  }
	  */
      else if (ae.getSource() == quit) {
         shutDown();
	  }
   }

   void shutDown() {
// -----------------  Control the closing of this gui

      GuiEvent ge = new GuiEvent(this, myAgent.QUIT);
      myAgent.postGuiEvent(ge);
   }

   public void updateList(Vector v) {
// ----------------------------------

      listModel.clear();
      for (int i = 0; i < v.size(); i++){
         listModel.addElement(v.get(i));
	  }
   }
   
   public void updateList1(ArrayList<Produits> produitList , String Nom , String qt) {
// ----------------------------------

	   /*
      listModel.clear();
      for (int i = 0; i < produitList.size(); i++){
         listModel.addElement(produitList.get(i).getNom());
	  }
      */
      for(java.util.Iterator<Produits> it=produitList.iterator(); it.hasNext();) {
      	Produits p=it.next();
      	String[] l = {p.getNom(),Integer.toString(p.getstock()),Integer.toString(p.getfornisseur())};
      	if(p.getNom()==Nom) {
      		if(Integer.parseInt(qt)<=p.getstock()) {
      			p.setStock(p.getstock()-(Integer.parseInt(qt)));
      			String[] l1 = {p.getNom(),Integer.toString(p.getstock()),Integer.toString(p.getfornisseur())};
      			//System.out.println("****p.getNom()  ****"+p.getNom());
      			//System.out.println("****msg1.getContent() ****"+Nom);
      			
	        		//String[] liste1 = {p.getNom(),Integer.toString(p.getstock()),Integer.toString(p.getfornisseur())};
		        	//gui.setRows(liste1);
      			listModel.addElement(l1);
      		}
      	}
   }
   }
   
   public void updateList2(String[] l) {
// ----------------------------------

      listModel.clear();
      for (int i = 0; i < l.length; i++){
         listModel.addElement(l);
	  }
   }

}