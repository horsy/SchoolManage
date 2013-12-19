package com.school.service;

public class StartThread {

	private Initialization initialization;

	public void setInitialization(Initialization initialization) {
		this.initialization = initialization;
		this.initialization.setDaemon(true);
		this.initialization.start();
	}
}
