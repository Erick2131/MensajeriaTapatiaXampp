<?php

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

$sql = "SELECT idMensajero, empresa FROM mensajero";
$result = $conn->query($sql);

$mensajeros = array();
if ($result->num_rows > 0) {
    while ($row = $result->fetch_assoc()) {
        $mensajeros[] = $row;
    }
}

echo json_encode($mensajeros);
$conn->close()

?>
