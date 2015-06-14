<?php
//Incluindo o arquivo que conecta com o MySQL
require_once("conectar.php");

$message = "";

if(isset($_POST['titulo']) && isset($_POST['texto'])) {
	$titulo = $_POST['titulo'];
	$texto = $_POST['texto'];
	$sql = "insert into noticia(titulo, texto) values('$titulo', '$texto')";
	$result = mysql_query($sql);
	if(isset($result)) {
		$message = "Noticia inserida com sucesso!";
	}else{
		$message = "Erro ao inserir a noticia!";
	}
}else{
	$message = "Forneça corretamente os campos!";
}

?>
{
	"mensagem": "<?php echo $message ?>"
}