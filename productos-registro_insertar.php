<?php

include 'conexion_examen.php';

$pdo=new Conexion();

if($_SERVER['REQUEST_METHOD']=='POST')
{
	$sql="INSERT INTO registro_producto (id, nombre, marca, presentacion, precio) VALUES (:id1, :nom, :mar, :des, :cos)";
	$stmt=$pdo->prepare($sql);	
	$stmt->bindValue(':id1',$_POST['id']);
	$stmt->bindValue(':nom',$_POST['nombre']);
	$stmt->bindValue(':mar',$_POST['marca']);
	$stmt->bindValue(':des',$_POST['presentacion']);
	$stmt->bindValue(':cos',$_POST['precio']);	
	$stmt->execute();
	$idPost=$pdo->lastInsertId();
	
	$sql="INSERT INTO productos_inventario (id,cantidad) VALUES (:id1,0)";
	$stmt=$pdo->prepare($sql);	
	$stmt->bindValue(':id1',$_POST['id']);
	$stmt->execute();
	$idPost=$pdo->lastInsertId();
	
	if($idPost)
	{
		header ("http/1.1 200 OK");
		echo json_encode($idPost);
		exit;
	}
} else {
	header("HTTP/1.1 400 Bad REQUEST_METHOD");
}
?>