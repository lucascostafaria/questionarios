package br.com.softbox.questionarios.exception;

public class BusinessException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public BusinessException(Throwable t) {
		super(t);
	}

	public BusinessException(String message, Throwable t) {
		super(message, t);
	}

}
