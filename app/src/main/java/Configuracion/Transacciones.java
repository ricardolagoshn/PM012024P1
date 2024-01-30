package Configuracion;

public class Transacciones
{
    // Nombre de la base datos
    public static final String DBName = "PM012024";

    // Creacion de las tablas de base de datos
    public static final String TablePersonas = "personas";

    // Creacion de los campos de base de datos
    public static final String id = "id";
    public static final String nombres = "nombres";
    public static final String apellidos = "apellidos";
    public static final  String edad = "edad";
    public static final String correo = "correo";

    // DDL Create
    public static final String CreateTablePersonas = "Create table "+ TablePersonas +" ("+
            "id INTEGER PRIMARY KEY AUTOINCREMENT, nombres TEXT, apellidos TEXT, edad INTEGER, "+
            "correo TEXT )";

    // DDL Drop
    public static final String DropTablePersonas = "DROP TABLE IF EXISTS "+ TablePersonas;

    // DML
    public static final String SelectAllPersonas = "SELECT * FROM " + TablePersonas;

}
