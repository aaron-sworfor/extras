<?php

include 'conexion_examen.php';

$pdo = new Conexion();

if ($_SERVER['REQUEST_METHOD'] == 'GET') {
   
        if (isset($_GET['numfactura'])) {
        $sql = $pdo->prepare("SELECT * FROM actualizacion_inventario WHERE numfactura=:id");
        $sql->bindValue(':id', $_GET['numfactura']);
        $sql->execute();
        $sql->setFetchMode(PDO::FETCH_ASSOC);
        header("HTTP/1.1 200 OK");
        echo json_encode($sql->fetchAll());
        exit;
    } else {
        $sql = $pdo->prepare("SELECT * FROM actualizacion_inventario");
        $sql->execute();
        $sql->setFetchMode(PDO::FETCH_ASSOC);
        header("HTTP/1.1 200 OK");
        echo json_encode($sql->fetchAll());
        exit;
    }
} else {
    header("HTTP/1.1 400 Bad Request");
}
?>