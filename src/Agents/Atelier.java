package Agents;


import Objects.*;
import Objects.Produits;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.lang.acl.UnreadableException;
import jade.util.leap.Iterator;

import javax.swing.*;

import GuiAgent.ControllerAgentGui;

import java.awt.*;
import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class Atelier extends Agent {
	private int numero ;
    static private List<Produits> produitList  ; 
    private List<String> produits = new ArrayList<>();
    
    private Vector agents = new Vector();
    //transient protected ControllerAgentGui myGui;
    

    Produits p ;//=  new Produits();
    
    public Atelier(int numero  , List<Produits> lp) {
    	this.numero = numero;
    	//this.produitList = new ArrayList<>();
    	this.produitList = lp;	
    }

    public void setProduitList(List<Produits> produitList) {
		this.produitList = produitList;
	}
    public List<Produits> getProduitList() {
		return produitList;
	}
    
    ArrayList<String> TypeBois = new ArrayList();
    
    public ArrayList<String> getTypeBois() {
		return TypeBois;
	}
    
   /* Produits p1 = new Produits("Table", 8, 4, 5,TypeBois.get(0)) ;
    Produits p2 = new Produits("Chaise", 7, 4, 2,TypeBois.get(0)) ;
    Produits p3 = new Produits("Buffet", 10, 4, 6,TypeBois.get(0)) ;

   
    /////////////////////////
    Produits p4 = new Produits("Lit", 11, 2, 7,TypeBois.get(1)) ;
    Produits p5 = new Produits("Chevet", 5, 2, 1,TypeBois.get(1)) ;
    Produits p6 = new Produits("Armoire", 8, 2, 8,TypeBois.get(1)) ;


   ////////////////////////////////////////
    Produits p7 = new Produits("Banquette", 12, 3, 6,TypeBois.get(2)) ;
    Produits p8 = new Produits("Fauteuil", 0, 3, 3,TypeBois.get(2)) ;
    Produits p9 = new Produits("Etag�re", 6, 3, 8,TypeBois.get(2)) ;
*/
    
    Produits p1 = new Produits("Table", 8, 4, 5) ;
    Produits p2 = new Produits("Chaise", 7, 4, 2) ;
    Produits p3 = new Produits("Buffet", 10, 4, 6) ;

   
    /////////////////////////
    Produits p4 = new Produits("Lit", 11, 2, 7) ;
    Produits p5 = new Produits("Chevet", 5, 2, 1) ;
    Produits p6 = new Produits("Armoire", 8, 2, 8) ;


   ////////////////////////////////////////
    Produits p7 = new Produits("Banquette", 12, 3, 6) ;
    Produits p8 = new Produits("Fauteuil", 0, 3, 3) ;
    Produits p9 = new Produits("Etag�re", 6, 3, 8) ;
    public Atelier() {
    	
       	TypeBois.add("Ch�ne");
    	TypeBois.add("Merisier");
    	TypeBois.add("Noyer");
    	
    	produitList = new ArrayList<>();
    	gui = new myframeGUI();
    	produitList.add(p1);
    	produitList.add(p2);
    	produitList.add(p3);
    	produitList.add(p4);
    	produitList.add(p5);
    	produitList.add(p6);
    	produitList.add(p7);
    	produitList.add(p8);
    	produitList.add(p9);
    }
    
    
    private myframeGUI gui;
    
    public myframeGUI getGui() {
		return gui;
	}
    
    
    
    @Override
    protected void setup() {
    	//System.out.println("Aaa");
    	//System.out.println(p.getListProduit().get(0));
        
        gui.setVisible(true);
        /*
        for(java.util.Iterator<Produits> it=produitList.iterator(); it.hasNext();) {
        	p=it.next();
        	String[] liste = {p.getNom(),Integer.toString(p.getstock()),Integer.toString(p.getfornisseur())};
        	//System.out.println(it.next().getNom());
        	gui.setRows(liste);
        }
        */
        
        
       /* String[] list1 = {p1.getNom(),Integer.toString(p1.getstock()),Integer.toString(p1.getfornisseur())};
         gui.setRows(list1);
         String[] list2 = {p2.getNom(),Integer.toString(p2.getstock()),Integer.toString(p2.getfornisseur())};
         gui.setRows(list2);
         */
        
		addBehaviour(new CyclicBehaviour() {
			@Override
			public void action() {
				// receive du demande
				//p.setStock(p.getstock()+1);
				ACLMessage msg1 = receive();
				ACLMessage msg2 = receive();
				if((msg1 != null) && (msg2 != null)) {
										
					JOptionPane.showMessageDialog(null, "message1 --"+msg1.getContent()
					                                   +"message2 --"+msg2.getContent());
					String[] liste = {msg1.getContent(),msg2.getContent()};
					gui.setRowsDemande(liste);
					System.out.println("****Atelier1  receive msg1****"+msg1.getContent());
					System.out.println("****Atelier1  receive msg2****"+msg2.getContent());
					
					//doWait(500);
					init(msg1.getContent(),msg2.getContent());
					
					
					/*
					 for(java.util.Iterator<Produits> it=produitList.iterator(); it.hasNext();) {
				        	p=it.next();
				        	p.setStock(50);
				        	String[] liste1 = {p.getNom(),Integer.toString(p.getstock()),Integer.toString(p.getfornisseur())};
				        	//System.out.println(it.next().getNom());
				        	gui.setRows(liste1);
				        }
					 */
					

				}
				else block();
			}
		});
		
		/*addBehaviour(new CyclicBehaviour() {
			
			@Override
			public void action() {
				// TODO Auto-generated method stub
				ACLMessage msg1 = receive();
				ACLMessage msg2 = receive();
		        for(java.util.Iterator<Produits> it=produitList.iterator(); it.hasNext();) {
		        	p=it.next();
		        	if(p.getNom()==msg1.getContent()) {
		        		if(Integer.parseInt(msg2.getContent())<=p.getstock()) {
		        			p.setStock(p.getstock()-(Integer.parseInt(msg2.getContent())));
		        			System.out.println("****p.getNom()  ****"+p.getNom());
		        			System.out.println("****msg1.getContent() ****"+msg1.getContent());
		        			
			        		//String[] liste1 = {p.getNom(),Integer.toString(p.getstock()),Integer.toString(p.getfornisseur())};
				        	//gui.setRows(liste1);
		        		}
		        	}
		        	else {
		        		String[] liste1 = {p.getNom(),Integer.toString(p.getstock()),Integer.toString(p.getfornisseur())};
			        	gui.setRows(liste1);
		        	}			        	
		        	
		        }
				
			}
		});*/
        
        
    }
    
    void init(String pr,String qt) { 
    	

    	/*
    	if (gui.getTableModel().getRowCount() > 0) {
    	    for (int i = gui.getTableModel().getRowCount() - 1; i > -1; i--) {
    	    	gui.getTableModel().removeRow(i);
    	    }
    	}
    	*/
        for(java.util.Iterator<Produits> it=produitList.iterator(); it.hasNext();) {
        	p=it.next();
        	//p.setStock(500);
        	if(p.getNom().equals(pr)) {
        		if((Integer.parseInt(qt))<=(p.getstock())) {
        			p.setStock(p.getstock()-(Integer.parseInt(qt)));
        			//System.out.println("****p.getNom()  ****"+p.getNom());
        			//System.out.println("****msg1.getContent() ****"+pr);
	        		String[] liste1 = {p.getNom(),Integer.toString(p.getstock()),Integer.toString(p.getfornisseur())};
		        	gui.setRows(liste1);
			        //agents.add("updateList");
			        //myGui.updateList(agents);
        		}
        	}
        	else {
        		String[] liste1 = {p.getNom(),Integer.toString(p.getstock()),Integer.toString(p.getfornisseur())};
	        	gui.setRows(liste1);
        	}			        	        	
        }
    }
        
        


}
