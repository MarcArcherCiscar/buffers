package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import model.*;
import view.*;

public class GestionEventos {

	private GestionDatos model;
	private LaunchView view;
	private Libreria lib;
	private ActionListener actionListener_comparar, actionListener_buscar,actionListener_LibrosInterfaz,actionListener_enviar;
	private ActionListener actionListener_recoger;
	private ActionListener actionListener_recogerTodos;

	public GestionEventos(GestionDatos model, LaunchView view, Libreria l) {
		this.model = model;
		this.view = view;
		this.lib = l;
	}

	public void contol() {
		actionListener_comparar = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				call_compararContenido();
			}
		};
		
		
		view.getComparar().addActionListener(actionListener_comparar);

		actionListener_buscar = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				call_buscarPalabra();
			}
		};
		
		
		view.getBuscar().addActionListener(actionListener_buscar);
		actionListener_LibrosInterfaz=new ActionListener() {

			public void actionPerformed(ActionEvent actionEvent) {
				call_Libreria();
			}
		};
		view.getBtnLibros().addActionListener(actionListener_LibrosInterfaz);
		actionListener_enviar=new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				call_Enviar();
			}

		};
		lib.getBtnEnviar().addActionListener(actionListener_enviar);

		actionListener_recoger=new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				call_Recoger();
			}

		};
		lib.getBtnIDR().addActionListener(actionListener_recoger);
		actionListener_recogerTodos=new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				call_RecogerTodos();
			}

		};
		lib.getBtnIDR().addActionListener(actionListener_recogerTodos);


	}

	private void call_compararContenido() {
		try {
			if (model.compararContenido(view.getFichero1().getText(), view.getFichero2().getText())) {
				view.getTextArea().setText("Los archivos son iguales");
			}else {
				view.getTextArea().setText("Los archivos son diferentes");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			view.showError("ERROR!");
		}
	}

	private void call_buscarPalabra() {
		try {
			int result=model.buscarPalabra(view.getFichero1().getText(), view.getPalabra().getText(),view.getPrimera().isSelected());
			if(result!=-1) {
				if(view.getPrimera().isSelected()) {
					view.getTextArea().setText("La palabra "+view.getPalabra().getText()+" aparece por primera vez en la linea "+result );
				}else {
					view.getTextArea().setText("La palabra "+view.getPalabra().getText()+" aparece por ultima vez en la linea "+result );
				}
			}else {
				view.getTextArea().setText("No existe la palabra "+view.getPalabra().getText()+" en "+view.getFichero1().getText());
			}
		} catch (IOException e) {
			view.showError("ERROR!");
		}


	}
	private void call_Libreria() {
		lib.setVisible(true);
	}
	private void call_Enviar() {
		if(model.enviar(lib)) {
			lib.getTextResult().setText("Libro guardado correctamente");
		}else {
			lib.getTextResult().setText("Error, no se ha guardado correctamente el libro");
		} 
	}
	private void call_Recoger() {
		try {
			if(model.recuperar_libro(lib.getTextIDR().getText())!=null) {
				Libro l=model.recuperar_libro(lib.getTextIDR().getText());
				lib.getTextResult().setText(view.getTextArea().getText() + "ID:" + l.getId() + "\n" + "Titulo:"+ l.getTitulo() + "\n" + "Autor:"+ l.getAutor()+ "\n" + "Año:"+ l.getAutor()+ "\n" + "Editorial:"+ l.getEditor() + "\n" + "Paginas:"+ l.getNum_paginas());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void call_RecogerTodos() {


	}

}
