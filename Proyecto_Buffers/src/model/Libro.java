package model;
import java.io.Serializable;

import view.Libreria;

public class Libro implements Serializable{

	private String id;
	private String titulo;
	private String autor;
	private int año;
	private String editor;
	private int num_paginas;
	
	public Libro() {
		// TODO Auto-generated constructor stub
	}
	public Libro(String id,String titulo,String autor,int año,String editor,int num_paginas) {
		this.id = id;
		this.titulo = titulo;
		this.autor = autor;
		this.año=año;
		this.editor = editor;
		this.num_paginas=num_paginas;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getAutor() {
		return autor;
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}
	public int getAño() {
		return año;
	}
	public void setAño(int año) {
		this.año = año;
	}
	public String getEditor() {
		return editor;
	}
	public void setEditor(String editor) {
		this.editor = editor;
	}
	public int getNum_paginas() {
		return num_paginas;
	}
	public void setNum_paginas(int num_paginas) {
		this.num_paginas = num_paginas;
	}
	public void print() {
		Libreria lib=new Libreria();
	}




}
