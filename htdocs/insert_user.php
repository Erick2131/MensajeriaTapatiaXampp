<?php
// Conexión a la base de datos
$servername = "localhost";
$username = "root"; // Nombre de usuario de la base de datos
$password = ""; // Contraseña de la base de datos
$dbname = "mensajeriatapatia"; // Nombre de la base de datos

// Crear conexión
$conn = new mysqli($servername, $username, $password, $dbname);

// Verificar la conexión
if ($conn->connect_error) {
    die("Conexión fallida: " . $conn->connect_error);
}

// Obtener los datos enviados por POST
$name = $_POST["name"];
$age = $_POST["age"];
$email = $_POST["email"];
$password = $_POST["password"];
$isAdmin = $_POST["isAdmin"];

// Query de inserción
$sql = "INSERT INTO usuario (nombre, edad, correo, contrasena, isAdmin) VALUES ('$name', '$age', '$email', '$password', '$isAdmin')";

if ($conn->query($sql) === TRUE) {
    // Éxito: se insertó el usuario correctamente
    echo "1";
} else {
    // Error: no se pudo insertar el usuario
    echo "Error: " . $sql . "<br>" . $conn->error;
}

// Cerrar conexión
$conn->close();
?>
