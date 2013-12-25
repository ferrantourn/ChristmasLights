package AccesoDatos; 
//estos valores dentro de esta clase son accesibles solo a los miembros de AccesoDatos.
//para hacer esto posible, se quita el alcance de la clase, y queda como "internal" (ni private, ni public):
class AccesoBD {
    
    static String URL_CONEXION = "jdbc:mysql://localhost:3306/2014worldcup";
    static String NOMBRE_USUARIO_BASE_DATOS = "root";
    //static String CONTRASENA_BASE_DATOS = "1qaz2WSX"; //yanick
    static String contrasena_BD = "awaken1"; //fito
    
}
