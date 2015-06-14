<?php
//Incluindo o arquivo que conecta com o MySQL
require_once("conectar.php");

$message = "";

if(isset($_POST['titulo']) && isset($_POST['texto']) && isset($_POST['idNoticia'])) {
	$titulo = $_POST['titulo'];
	$texto = $_POST['texto'];
	$idNoticia = $_POST['idNoticia'];
	$sql = "update noticia set titulo='$titulo', texto='$texto' where idNoticia=$idNoticia";
	$result = mysql_query($sql);
	if(isset($result)) {
		$message = "Noticia alterada com sucesso!";
	}else{
		$message = "Erro ao alterar a noticia!";
	}
}else{
	$message = "Forneça corretamente os campos!";
}

?>
{
	"mensagem": "<?php echo $message ?>"
}
