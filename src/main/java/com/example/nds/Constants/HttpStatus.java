package com.example.nds.Constants;

	public enum HttpStatus {
	    // Success
	    OK(200, "OK"),
	    CREATED(201, "Created"),
	    ACCEPTED(202, "Accepted"),
	    
	    // Redirection
	    MOVED_PERMANENTLY(301, "Moved Permanently"),
	    FOUND(302, "Found"),
	    SEE_OTHER(303, "See Other"),
	    
	    // Client Error
	    BAD_REQUEST(400, "Bad Request"),
	    UNAUTHORIZED(401, "Unauthorized"),
	    FORBIDDEN(403, "Forbidden"),
	    NOT_FOUND(404, "Not Found"),
	    METHOD_NOT_ALLOWED(405, "Method Not Allowed"),
	    
	    // Server Error
	    INTERNAL_SERVER_ERROR(500, "Internal Server Error"),
	    BAD_GATEWAY(502, "Bad Gateway"),
	    SERVICE_UNAVAILABLE(503, "Service Unavailable"),
	    GATEWAY_TIMEOUT(504, "Gateway Timeout");
	    
	    private final int code;
	    private final String message;
	    
	    HttpStatus(int code, String message) {
	        this.code = code;
	        this.message = message;
	    }
	    
	    public int getCode() {
	        return code;
	    }
	    
	    public String getMessage() {
	        return message;
	    }
	}