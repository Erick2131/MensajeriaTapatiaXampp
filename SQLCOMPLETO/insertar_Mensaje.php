<?php

// Verificar si se reciben los datos esperados
if (isset($_POST['idDestinatario']) && isset($_POST['idMensajero']) && isset($_POST['contenido']) && isset($_POST['tipo']) && isset($_POST['fecha'])) {
    
    // Obtener los datos del mensaje
    $idDestinatario = $_POST['idDestinatario'];
    $idMensajero = $_POST['idMensajero'];
    $contenido = $_POST['contenido'];
    $tipo = $_POST['tipo'];
    $fecha = $_POST['fecha'];
    
    // Conectarse a la base de datos (ajusta estos valores según tu configuración)
    $servername = "localhost";
    $username = "root";
    $password = "";
    $dbname = "mensajeriatapatia";
    
    // Crear conexión
    $conn = new mysqli($servername, $username, $password, $dbname);
    
    // Verificar la conexión
    if ($conn->connect_error) {
        die("Conexión fallida: " . $conn->connect_error);
    } 
    
    // Escapar caracteres especiales en los datos para prevenir inyección SQL
    $idDestinatario = $conn->real_escape_string($idDestinatario);
    $idMensajero = $conn->real_escape_string($idMensajero);
    $contenido = $conn->real_escape_string($contenido);
    $tipo = $conn->real_escape_string($tipo);
    $fecha = $conn->real_escape_string($fecha);

    // Preparar la consulta SQL para insertar el mensaje
    $sql = "INSERT INTO mensaje (destinatario, mensajero, contenido, tipo, fecha) VALUES ('$idDestinatario', '$idMensajero', '$contenido', '$tipo', '$fecha')";
    
    // Ejecutar la consulta y verificar si fue exitosa
    if ($conn->query($sql) === TRUE) {
        echo "Mensaje insertado correctamente";
    } else {
        echo "Error al insertar el mensaje: " . $conn->error;
    }
    
    // Cerrar la conexión
    $conn->close();
    
} else {
    echo "Error: Faltan datos del mensaje";
}
?>

