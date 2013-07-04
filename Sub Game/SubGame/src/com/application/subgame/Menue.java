package com.application.subgame;


import java.util.ArrayList;

public class Menue {
	/*
	private Background bg;
	private GLabel overskrift,start,exit;
	private GRect peker;
	private ArrayList<GObject> menueList = new ArrayList<GObject>();
	private int p;
	private Font f = new Font("Impact",Font.BOLD, 32);
	private Font f2 = new Font("Impact",Font.BOLD, 24);
	
	
	public Menue(){
		p = 0;
		this.bg = new Background(0, 0);
		this.overskrift = new GLabel("SUUUB Game");
		overskrift.setLocation(50, 60);
		overskrift.setFont(f);
		menueList.add(overskrift);
		
		this.peker = new GRect(160,30);
		peker.setLocation(75, 95);
		peker.setFilled(true);
		peker.setFillColor(Color.GREEN);
		menueList.add(peker);
		
		this.start = new GLabel("Start Game");
		start.setLocation(80, 120);
		start.setFont(f2);
		menueList.add(start);
		
		this.exit = new GLabel("Exit");
		exit.setLocation(80, 160);
		exit.setFont(f2);
		menueList.add(exit);
		
		
	}
	
	private void repaint(){
		switch(p){
		case 0:peker.setLocation(75, 95);break;
		case 1:peker.setLocation(75,135);break;
		}
	}
	
	public void moveUp(){
		if (p >0){
			p = p -1;
		}else{
			p = 1;
		}
		repaint();
	}
	
	public void moveDown(){
		if(p < 1){
			p = p + 1;
		}else{
			p = 0;
		}
		repaint();
	}
	
	public GameStates getState(){
		switch(p){
		case 0:return GameStates.game;
		case 1:return GameStates.menue;
		}
		return null;
	}
	
	public ArrayList<GObject> getList(){
		return this.menueList;
	}
	public Background getBg(){
		return this.bg;
	}*/

}
