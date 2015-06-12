var app = angular.module('plunker', []);

var result = "";

app.controller('generateCtrl', function($scope, anaLexicoService, $log) {
  
  $scope.input = "clazz teste { variavel i = 1 while ( i < 10 ) { Console.log i=i+1 } }";
  $scope.tokens = [];

	$scope.change = function () {
	  $scope.result = "";
		$scope.tokens = anaLexicoService.gramatica($scope.input);
		$scope.classePrincipal($scope.tokens);
	}
	
	$scope.classePrincipal = function() {
	  for (var ii = 0; ii < $scope.tokens.length; ii++) {
			if (($scope.tokens.indexOf("while") == -1) && ($scope.tokens.indexOf("variavel") == -1)) {
				$scope.gerarVariavel0();
			} else {
				if (($scope.tokens.indexOf("if") == -1) && ($scope.tokens.indexOf("variavel") == -1)) {
					$scope.gerarVariavel1();
				} else {
					if (($scope.tokens.indexOf("while") == -1) && ($scope.tokens.indexOf("Console.read") == -1)) {
						$scope.gerarScanner0();
					} else {
						if (($scope.tokens.indexOf("if") == -1) && ($scope.tokens.indexOf("Console.read") == -1)) {
							$scope.gerarScanner1();
						}
					}
				}
			}
	  }
	}
	
	$scope.gerarVariavel0 = function() {
	  var l = $scope.tokens;
		var bytes = [];
		
		var op;
		var temp;
		var temp1;
		var i = 0;
		while (!l.length == 0) {
		  
			op = l.splice(0, 1);
			
			switch (op[0].valor) {
			
			case "clazz":
				bytes.push("LSP"); // Entrada no código
				bytes.push("00");
				bytes.push("64"); // Tamanho do bytecode
				bytes.push("JMP"); // Saltar para a linha 8
				bytes.push("00");
				bytes.push("08");
				bytes.push("00");
				bytes.push("00");
				l.splice(0, 1);
				l.splice(0, 1);
				break;

			case "variavel":
				l.splice(0, 1);
				l.splice(0, 1);
				temp = l.splice(0, 1);
				
				bytes.push("LDI"); // Carregar uma variável
				bytes.push("00");
				bytes.push("0"+temp[0].valor);
				bytes.push("STO"); // 
				bytes.push("00");
				bytes.push("06");
				break;	
			
			case "while":
				l.splice(0, 1);
				l.splice(0, 1);
				temp = l.splice(0, 1);
				temp1 = l.splice(0, 1);
				
				bytes.push("LOD"); // Começo do while
				bytes.push("00");
				bytes.push("06");
				bytes.push("LDI"); // Atribuir uma variável
				bytes.push("00");
				bytes.push(temp1[0].valor);
				if (temp[0].valor == "<") {
					bytes.push("LT");
				} else {
					if (temp[0].valor == ">") {
						bytes.push("GT");
					}
				}
				bytes.push("JF"); // Jump false
				bytes.push("00");
				bytes.push("40");
				break;	
				
			case "Console.log":
				bytes.push("LOD"); // Carrega um método
				bytes.push("00");
				bytes.push("06");
				bytes.push("OUT");
				break;
				
			case "i=i+1":	
					bytes.push("LOD"); // Começo do while
					bytes.push("00");
					bytes.push("06");
					bytes.push("ADI"); // Adicionar um valor
					bytes.push("00");
					bytes.push("01");
					bytes.push("STO");
					bytes.push("00");
					bytes.push("06");
				break;
				
			case "}":
				temp = l.splice(0, 1);
				bytes.push("JMP");
				bytes.push("00");
				bytes.push("14");
				bytes.push("STOP");
				
				break;	
				
			default:
				break;
			}
		}
		
		for (var ii = 0; ii <= bytes.length; ii++) {
			$scope.result += bytes.splice(0, 1) + " ";
		}
	}
	
	$scope.gerarVariavel1 = function() {
	  var l = $scope.tokens;
		var bytes = [];
		
		var op;
		var temp;
		var temp1;
		var i = 0;
		while (!l.length == 0) {
			op = l.splice(0, 1);
			
			switch (op[0].valor) {
			
			case "clazz":
				bytes.push("LSP");
				bytes.push("00");
				bytes.push("64");
				bytes.push("JMP");
				bytes.push("00");
				bytes.push("08");
				bytes.push("00");
				bytes.push("00");
				l.splice(0, 1);
				l.splice(0, 1);
				break;

			case "variavel":
				l.splice(0, 1);
				l.splice(0, 1);
				temp = l.splice(0, 1);
				
				bytes.push("LDI");
				bytes.push("00");
				bytes.push("0"+temp[0].valor);
				bytes.push("STO");
				bytes.push("00");
				bytes.push("06");
				break;	
			
			case "if":
				l.splice(0, 1);
				l.splice(0, 1);
				temp = l.splice(0, 1);
				temp1 = l.splice(0, 1);
				
				bytes.push("LOD");
				bytes.push("00");
				bytes.push("06");
				bytes.push("LDI");
				bytes.push("00");
				bytes.push(temp1[0].valor);
				if (temp[0].valor == "<") {
					bytes.push("LT");
				} else {
					if (temp[0].valor == ">") {
						bytes.push("GT");
					}
				}
				bytes.push("JF");
				bytes.push("00");
				bytes.push("37");
				break;	
				
			case "Console.log":
				bytes.push("LOD");
				bytes.push("00");
				bytes.push("06");
				bytes.push("OUT");
				break;
				
			case "i=i+1":	
				bytes.push("LOD");
				bytes.push("00");
				bytes.push("06");
				bytes.push("ADI");
				bytes.push("00");
				bytes.push("01");
				bytes.push("STO");
				bytes.push("00");
				bytes.push("06");
				break;
				
			case "}":
				temp = l.splice(0, 1);
				
				bytes.push("STOP");
				break;	
				
			default:
				break;
			}
		}
		
		for (var ii = 0; ii <= bytes.length; ii++) {
			$scope.result += bytes.splice(0, 1) + " ";
		}
	}
	
	$scope.gerarScanner0 = function() {
		var l = $scope.tokens;
		var bytes = [];
		
		var op;
		var temp;
		var temp1;
		var i = 0;
		while (!l.length == 0) {
			op = l.splice(0, 1);
			
			switch (op[0].valor) {
			
			case "clazz":
				bytes.push("LSP");
				bytes.push("00");
				bytes.push("64");
				bytes.push("JMP");
				bytes.push("00");
				bytes.push("08");
				bytes.push("00");
				bytes.push("00");
				l.splice(0, 1);
				l.splice(0, 1);
				break;

			case "scanner":
				bytes.push("IN");
				bytes.push("STO");
				bytes.push("00");
				bytes.push("06");
				break;	
			
			case "while":
				l.splice(0, 1);
				l.splice(0, 1);
				temp = l.splice(0, 1);
				temp1 = l.splice(0, 1);
				
				bytes.push("LOD");
				bytes.push("00");
				bytes.push("06");
				bytes.push("LDI");
				bytes.push("00");
				bytes.push(temp1[0].valor);
				if (temp[0].valor == "<") {
					bytes.push("LT");
				} else {
					if (temp[0].valor == ">") {
						bytes.push("GT");
					}
				}
				bytes.push("JF");
				bytes.push("00");
				bytes.push("38");
				break;	
				
			case "Console.log":
				bytes.push("LOD");
				bytes.push("00");
				bytes.push("06");
				bytes.push("OUT");
				break;
				
			case "i=i+1":	
				bytes.push("LOD");
				bytes.push("00");
				bytes.push("06");
				bytes.push("ADI");
				bytes.push("00");
				bytes.push("01");
				bytes.push("STO");
				bytes.push("00");
				bytes.push("06");
				break;
				
			case "}":
				temp = l.splice(0, 1);
				
				bytes.push("JMP");
				bytes.push("00");
				bytes.push("12");
				bytes.push("STOP");
				break;	
				
			default:
				break;
			}
		}
		
		for (var ii = 0; ii <= bytes.length; ii++) {
			$scope.result += bytes[ii] + " ";
			bytes.splice(0, 1);
		}
	}
	
	$scope.gerarScanner1 = function() {
	  var l = $scope.tokens;
		var bytes = [];
		
		var op;
		var temp;
		var temp1;
		var i = 0;
		while (!l.length == 0) {
			op = l.splice(0, 1);
			
			switch (op[0].valor) {
			
			case "clazz":
				bytes.push("LSP");
				bytes.push("00");
				bytes.push("64");
				bytes.push("JMP");
				bytes.push("00");
				bytes.push("08");
				bytes.push("00");
				bytes.push("00");
				l.splice(0, 1);
				l.splice(0, 1);
				break;

			case "Console.read":
				bytes.push("IN");
				bytes.push("STO");
				bytes.push("00");
				bytes.push("06");
				break;	
			
			case "if":
				l.splice(0, 1);
				l.splice(0, 1);
				temp = l.remove(0);
				temp1 = l.splice(0, 1);
				
				bytes.push("LOD");
				bytes.push("00");
				bytes.push("06");
				bytes.push("LDI");
				bytes.push("00");
				bytes.push(temp1[0].valor);
				if (temp[0].valor == "<") {
					bytes.push("LT");
				} else {
					if (temp[0].valor == ">") {
						bytes.push("GT");
					}
				}
				bytes.push("JF");
				bytes.push("00");
				bytes.push("35");
				break;	
				
			case "Console.log":
				bytes.push("LOD");
				bytes.push("00");
				bytes.push("06");
				bytes.push("OUT");
				break;
				
			case "i=i+1":	
				bytes.push("LOD");
				bytes.push("00");
				bytes.push("06");
				bytes.push("ADI");
				bytes.push("00");
				bytes.push("01");
				bytes.push("STO");
				bytes.push("00");
				bytes.push("06");
				break;
				
			case "}":
				temp = l.splice(0, 1);
				
				bytes.push("STOP");
				break;	
				
			default:
				break;
			}
		}
		
		for (var ii = 0; ii <= bytes.length; ii++) {
			$scope.result += bytes[ii] + " ";
			bytes.splice(0, 1);
		}
	}
});