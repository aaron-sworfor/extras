<?php

include 'conexion_examen.php';

$pdo=new Conexion();

if ($_SERVER['REQUEST_METHOD']=='DELETE'){
	$sql="DELETE FROM productos_inventario WHERE id=:id";
	$stmt=$pdo->prepare($sql);
	$stmt->bindValue(':id',$_GET['id']);
	$stmt->execute();
	$sql="DELETE FROM registro_producto WHERE id=:id";
	$stmt=$pdo->prepare($sql);
	$stmt->bindValue(':id',$_GET['id']);
	$stmt->execute();
	header("HTTP/1.1 200 OK");
	exit;
	}else {
	header("HTTP/1.1 400 Bad REQUEST_METHOD");}
?>