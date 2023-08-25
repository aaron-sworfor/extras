<?php

include 'conexion_examen.php';

$pdo=new Conexion();
if($_SERVER['REQUEST_METHOD']=='PUT')
{
	$sql="UPDATE registro_producto SET nombre=:nom, marca=:des, presentacion=:pre, precio=:exi WHERE  id=:id";
	$stmt=$pdo->prepare($sql);	
	$stmt->bindValue(':id',$_GET['id']);
	$stmt->bindValue(':nom',$_GET['nombre']);
	$stmt->bindValue(':des',$_GET['marca']);
	$stmt->bindValue(':pre',$_GET['presentacion']);
	$stmt->bindValue(':exi',$_GET['precio']);
	$stmt->execute();
	header ("http/1.1 200 OK");
	exit;

}
header("HTTP/1.1 400 Bad REQUEST_METHOD");
?>
