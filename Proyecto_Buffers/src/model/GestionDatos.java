package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;

import view.Libreria;

public class GestionDatos {
	public GestionDatos() {

	}

	//TODO: Implementa una función para abrir ficheros
	public FileReader abrirFichero(String fichero1) {
		FileReader f1 = null;
		try {
			f1 = new FileReader("D:\\Eclipse\\eclipse/ProyectoBuffers /"+fichero1);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return f1;
	}
	//TODO: Implementa una función para cerrar ficheros
	public void cerrarFichero(FileReader f1) {
		
		try {
			f1.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public boolean compararContenido (String fichero1, String fichero2) throws IOException{
		//TODO: Implementa la función
		FileReader fr1=new FileReader(fichero1);
		BufferedReader br1=new BufferedReader(fr1);
		FileReader fr2=new FileReader(fichero2);
		BufferedReader br2=new BufferedReader(fr2);

		String Cadena1 = br1.readLine();
		String Cadena2=br2.readLine();
		boolean a = true;

		while ((Cadena1!=null) || (Cadena2!=null) && a) {

			if (Cadena1 == null || Cadena2== null || !Cadena1.equals(Cadena2))
				a = false;

			Cadena1 = br1.readLine();
			Cadena2 = br2.readLine();
		} 
		fr1.close();
		fr2.close();
		br1.close();
		br2.close();


		return a;
	}

	public int buscarPalabra (String fichero1, String palabra, boolean primera_aparicion) throws IOException{
		//TODO: Implementa la función
		FileReader fr1=new FileReader(fichero1);
		BufferedReader br1=new BufferedReader(fr1);

		String Cadena1 = br1.readLine();
		boolean encontrada = false;
		int cont=0;
		int linea=0;
		int result;
		if(primera_aparicion) {
			while ((Cadena1!=null) && encontrada==false) {
				cont++;
				if(Cadena1.compareTo(palabra)==0) {
					encontrada=true;
					linea=cont;
				}
				Cadena1=br1.readLine();
			}
			if(linea==0) {
				result=-1;
			}else {
				result=linea;
			}

		}
		else {
			while ((Cadena1!=null)) {
				cont++;
				if(Cadena1.compareTo(palabra)==0) {
					linea=cont;
				}
				Cadena1=br1.readLine();
			}
			if(linea==0) {
				result=-1;
			}else {
				result=linea;
			}

		}

		fr1.close();
		br1.close();



		return result;
	}
	public boolean enviar(Libreria libros) {
		String id=libros.getTextID().getText();
		String titulo=libros.getTextTitulo().getText();
		String autor=libros.getTextAutor().getText();
		int año=Integer.parseInt(libros.getTextAño().getText().trim());
		String editor=libros.getTextEditor().getText();
		int num_paginas=Integer.parseInt(libros.getTextNumPg().getText().trim());

		Libro libro=new Libro(id,titulo,autor,año,editor,num_paginas);

		boolean b=true;
		ObjectOutputStream out=null;
		try {
			out=new ObjectOutputStream(new FileOutputStream(libro.getId()+".dat"));
			out.writeObject(libro);
			out.close();
		} catch (IOException e) {
			b=false;
		}
		return b;

	}
	public Libro recuperar_libro(String identificador) {
		Libro l=null;
		ObjectInputStream in=null;
		try {
			in=new ObjectInputStream(new FileInputStream(identificador+".dat"));
			l=(Libro) in.readObject();
			in.close();
		} catch (IOException e) {
			l=null;
		} catch (ClassNotFoundException e) {
			l=null;
		}
		return l;
	}
	public ArrayList<Libro> recuperar_todos1(){
		File file=new File("libros");
		ArrayList<Libro> libros =new ArrayList<Libro>();
		Iterator it=libros.iterator();
		while(it.hasNext()) {
			Libro l=(Libro) it.next();
			l.print();
		}
		return libros;

	}
	
	public void recuperar_todos() {
		ArrayList<Libro> libros=new ArrayList<Libro>();
		Iterator it=libros.iterator();
		while(it.hasNext()) {
			Libro l=(Libro) it.next();
			l.print();
		}
	}
}
