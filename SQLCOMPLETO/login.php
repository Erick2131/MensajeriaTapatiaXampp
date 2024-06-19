<?php

// Datos de conexión a la base de datos
$servername = "localhost";
$username = "root";
$password = "";
$database = "mensajeriatapatia";

// Crear conexión
$conn = new mysqli($servername, $username, $password, $database);

// Verificar conexión
if ($conn->connect_error) {
    die("Conexión fallida: " . $conn->connect_error);
}

// Obtener datos del POST
$correo = $_POST["correo"];
$contrasena = $_POST["contrasena"];

// Consulta SQL para buscar el usuario en la base de datos
$sql = "SELECT * FROM usuario WHERE correo='$correo' AND contrasena='$contrasena'";
$result = $conn->query($sql);

if ($result->num_rows > 0) {
    // El usuario fue encontrado en la base de datos, iniciar sesión exitosamente
    echo "success";
} else {
    // El usuario no fue encontrado en la base de datos
    echo "failure";
}

$conn->close();
?>
