package com.tc.labt.sgabs.cokpit.commons;

public class MessageError {
	
	public static final String TITLE_ERROR = "Cokpitv4: ";

    public static final String NULL_OBJECT = "Cannot create object null";

    public static final String ALREADY_EXIST = "Object already exists";

    public static final String NOT_EXIST = "Object does not exist";

    public static final String DATABASE_NOT_EXIST = "Database not configured";

    public static final String SERVOR_ERROR = "Echec de l'opération!";

    public static Exception build(String e){
        return new Exception(e);
    }
}
