<?php
//Incluindo o arquivo que conecta com o MySQL
require_once("conectar.php");

$sql = "select idNoticia, titulo from noticia order by titulo";

//Executando uma consulta
$noticias = mysql_query($sql);

$arrayNoticias = array ();

while($noticia = mysql_fetch_assoc($noticias) ) {
	$arrayNoticias[] = $noticia;
}

$resultado = array("noticia" => $arrayNoticias);

//Gera o codigo json a partir do array
echo json_encode($resultado);

?>	            
