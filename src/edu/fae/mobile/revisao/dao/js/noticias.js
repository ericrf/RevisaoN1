
//Chamada quando a página de notícias é carregada
$(document).on("pagecreate", "#noticiasPage", function() {
	$.getJSON("service/noticias.php",
        function(data){
			//Renderiza o template na lista de noticias
			$( "#noticiaTemplate" ).tmpl( data.noticia ).appendTo( "#listNoticias" );
			//Força o redraw da lista
			$("#listNoticias").listview( "refresh", true );
    	}
	);	
});

//Registra o evento click de cada noticia, para abrir a página de detalhe
$(document).on("click", "#listNoticias a", function() {
	var idNoticia = $(this).attr("noticia-id");
	changePage("noticia_detalhe.html", {"idNoticia": idNoticia});
});


//Chamada quando a página de detalhe é carregada
$(document).on("pagecreate", "#noticiaDetalhePage", function() {
	var idNoticia = getParameterByName("idNoticia");
	$.getJSON("service/noticia_detalhe.php?idNoticia=" + idNoticia,
        function(data){
			$( "#noticiaDetalheTemplate" ).tmpl( data ).appendTo( "#noticiaDetalhe" );
			//Força o redraw na div #noticiaDetalhe
			$("#noticiaDetalhe" ).trigger('create');
    	}
	);	
});


//Chamada quando a página de inserção é carregada
$(document).on("pagecreate", "#noticiaInserirPage", function() {
	$("#formInserirNoticia" ).submit(function () {
		$.mobile.loading('show');
		
		$.ajax({
		  	url: "service/noticia_salvar.php",
		  	type: "POST",
		  	dataType: 'json',
			data: $("#formInserirNoticia").serialize(),
		  	success: function( response ) {
				$.mobile.loading('hide');
				alert(response.mensagem);
		  	},
			error: function( jqXHR, textStatus, errorThrown ) {
				alert('erro');
				$.mobile.loading('hide');
				console.log('Status: ' + textStatus + "\nError: " + errorThrown);
			}
		});
		
		//Evita o submit padrão do formulário
		return false; // Prevent a form submit
	});	
});

//Registra o evento click de cada noticia, para abrir a página de alteração
$(document).on("click", "#linkAlterar", function() {
	var idNoticia = $(this).attr("noticia-id");
	changePage("noticia_alterar.html", {"idNoticia": idNoticia});
});


$(document).on("pagecreate", "#noticiaAlterarPage", function() {

	var idNoticia = getParameterByName("idNoticia");
	$.getJSON("service/noticia_detalhe.php?idNoticia=" + idNoticia,
        function(data){
			$("#txtTitulo").val(data.titulo);
			$("#txtTexto").val(data.texto);
			$("#hdnIdNoticia").val(data.idNoticia);
    	}
	);	
	

	$("#frmAlterarNoticia" ).submit(function () {
		$.mobile.loading('show');
		
		$.ajax({
		  	url: "service/noticia_salvar_alterar.php",
		  	type: "POST",
		  	dataType: 'json',
			data: $("#frmAlterarNoticia").serialize(),
		  	success: function( response ) {
				$.mobile.loading('hide');
				alert(response.mensagem);
		  	},
			error: function( jqXHR, textStatus, errorThrown ) {
				alert('erro');
				$.mobile.loading('hide');
				console.log('Status: ' + textStatus + "\nError: " + errorThrown);
			}
		});
		
		//Evita o submit padrão do formulário
		return false; // Prevent a form submit
	});	


});


//Registra o evento click de cada noticia, para abrir a página de exclusão
$(document).on("click", "#linkExcluir", function() {
	var idNoticia = $(this).attr("noticia-id");
	$.getJSON("service/noticia_excluir.php?idNoticia=" + idNoticia,
        function(data){
			alert(data.mensagem);
			changePage("noticias.html", {});
    	}
	);		
});









