package com.example.springboot.api;

public class NotFoundException extends ApiException {
    private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	private int code;
    public NotFoundException (int code, String msg) {
        super(code, msg);
        this.code = code;
    }
}
