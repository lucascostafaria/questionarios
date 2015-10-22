package br.com.softbox.questionarios.exception;

public class PersistenceException extends Exception {
	private static final long serialVersionUID = 1L;

	public PersistenceException(Throwable t) {
		super(t);
	}

	public PersistenceException(String message, Throwable t) {
		super(message, t);
	}
}
