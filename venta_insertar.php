<?php

include 'conexion_examen.php';

$pdo=new Conexion();

if($_SERVER['REQUEST_METHOD']=='POST')
{
	$sql="INSERT INTO ventas_productos (id_venta, id_producto, fecha_venta, cantidad_producto, precio, nombre_producto) VALUES (:ida,:ido, :fec, :can, :prec, :nomb)";
	$stmt=$pdo->prepare($sql);	
	$stmt->bindValue(':ida',$_POST['id_venta']);
	$stmt->bindValue(':ido',$_POST['id_producto']);
	$stmt->bindValue(':fec',$_POST['fecha_venta']);
	$stmt->bindValue(':can',$_POST['cantidad_producto']);
	$stmt->bindValue(':prec',$_POST['precio']);	
	$stmt->bindValue(':nomb',$_POST['nombre_producto']);
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