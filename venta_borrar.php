<?php

include 'conexion_examen.php';

$pdo=new Conexion();

if ($_SERVER['REQUEST_METHOD']=='DELETE'){
		$sql="DELETE FROM ventas_productos WHERE id_venta=:id";
		$stmt=$pdo->prepare($sql);
	$stmt->bindValue(':id',$_GET['id_venta']);
	$stmt->execute();
	header("HTTP/1.1 200 OK");
	exit;
	}
	header("HTTP/1.1 400 Bad REQUEST_METHOD");
?>