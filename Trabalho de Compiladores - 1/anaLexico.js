app.service('anaLexicoService', function() {
  
  this.criaLista = function(lista) {
		var listaAux = lista.split(" ");

		var l = [];

		for (var i = 0; i < listaAux.length; i++) {
			l.push(listaAux[i]);
		}

		return l;
  }
  
  this.verificarPL = function(n) {

		var palavraReservada = [  "class", "Boolean", "Number", 
              "String", "while", "if", "Console.read", "Console.log" ];

		var b = false;

		for (var i = 0; i < palavraReservada.length; i++) {
			if (n == palavraReservada[i]) {
				b = true;
			}
		}
		return b;
	}
  
  this.verificarS = function(n) {

		var simbolo = ["{", "}", "(", ")"];

		var b = false;

		for (var i = 0; i < simbolo.length; i++) {
			if (n == simbolo[i]) {
				b = true;
			}
		}
		return b;
	}

  this.verificarO = function(n) {

		var operador = [ "+", "-", "*", "/" ];

		var b = false;

		for (var i = 0; i < operador.length; i++) {
			if (n == operador[i]) {
				b = true;
			}
		}
		return b;
	}
	
	this.verificarN = function(n) {

		var b = false;

		if (!isNaN(n[0])) {
			b = true;
		}
		return b;
	}
	
	this.gramatica = function(lista) {
		var l = this.criaLista(lista);

		var tokens = [];

		while (l.length > 0) {
			if (this.verificarPL(l[0])) {
				tokens.push({'valor': l[0], 'clazz': "p"});
				result += l.splice(0, 1) + ": Palavra reservada";
			} else {
				if (this.verificarS(l[0])) {
					tokens.push({'valor': l[0], 'clazz': "s"});
					result += l.splice(0, 1) + ": Símbolo";
				} else {
					if (this.verificarO(l[0])) {
						tokens.push({'valor': l[0], 'clazz': "o"});
						result += l.splice(0, 1) + ": Operador";
					} else {
						if (this.verificarN(l[0])) {
							tokens.push({'valor': l[0], 'clazz': "n"});
							result += l.splice(0, 1) + ": Número";
						} else {
							tokens.push({'valor': l[0], 'clazz': "i"});
							result += l.splice(0, 1) + ": Identificador";
						}
					}
				}
			}

		}

		return tokens;
	}

});