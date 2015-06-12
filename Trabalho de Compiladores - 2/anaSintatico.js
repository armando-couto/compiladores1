var app = angular.module('plunker', []);

var result = "";

/*

class trabalho2 { while (teste){faz_algo} }

class trabalho2 { 
  if ( variavel1 - variavel2 ) { 
     if (vari3 + vari4) { 
      executa_if_2
     }
  execute_isso 
  } 
}

*/

app.controller('anaSintaticoCtrl', function($scope, anaLexicoService, $log) {
    
  $scope.input = "class trabalho2 { if ( variavel1 - variavel2 ) { execute_isso } }";
  $scope.result = "";

	$scope.change = function () {
		$scope.tokens = anaLexicoService.gramatica($scope.input);
		$scope.classePrincipal();
	}
  
  $scope.classePrincipal = function() {
			if ($scope.tokens[0].valor == "class") {
			  $scope.tokens.splice(0, 1);
				$scope.varDeclaracao();
				if ($scope.tokens[0].valor == "{") {
				  $scope.tokens.splice(0, 1);
					$scope.comando();
					if ($scope.tokens[0].valor == "}") {
					  $scope.tokens.splice(0, 1);
						$scope.result = "Gramática sintaticamente correta!";
					} else {
						$scope.result = "Gramática inválida! saída 1";
					}
				} else {
					$scope.result = "Gramática inválida! saída 2";
				}
			} else {
				$scope.result = "Gramática inválida! saída 3";
			}
	}
	
	$scope.varDeclaracao = function() {
		$scope.identificador();
	}

	$scope.identificador = function() {
		if ($scope.tokens[0].clazz == "i") {
      $scope.tokens.splice(0, 1);
		} else {
			$scope.result = "Gramática inválida! saída 5";
		}
	}
	
	$scope.enquanto = function () {
	  $log.info("ghagjhfds");
		if ($scope.tokens[0].valor == "while") {
		  $scope.tokens.splice(0, 1);
			if ($scope.tokens[0].valor == "(") {
			  $scope.tokens.splice(0, 1);
				$scope.expressao();
				if ($scope.tokens[0].valor == ")") {
				  $scope.tokens.splice(0, 1);
					if ($scope.tokens[0].valor == "{") {
					  $scope.tokens.splice(0, 1);
						$scope.identificador();
						if ($scope.tokens[0].valor == "}") {
              $scope.tokens.splice(0, 1);
						} else {
							$scope.result = "Gramática inválida! saída 6";
						}
					} else {
						$scope.result = "Gramática inválida! saída 7";
					}
				} else {
					$scope.result = "Gramática inválida! saída 8";
				}
			} else {
				$scope.result = "Gramática inválida! saída 9";
			}
		} else {
			$scope.result = "Gramática inválida! saída 10";
		}
	}
	
	$scope.se = function() {
		if ($scope.tokens[0].valor == "if") {
		  $scope.tokens.splice(0, 1);
			if ($scope.tokens[0].valor == "(") {
			  $scope.tokens.splice(0, 1);
				$scope.expressao();
				if ($scope.tokens[0].valor == ")") {
				  $scope.tokens.splice(0, 1);
					if ($scope.tokens[0].valor == "{") {
					  $scope.tokens.splice(0, 1);
						$scope.identificador();
						if ($scope.tokens[0].valor == "}") {
              $scope.tokens.splice(0, 1);
						} else {
						}
					} else {
						$scope.result = "Gramática inválida! saída 12";
					}
				} else {
					$scope.result = "Gramática inválida! saída 13";
				}
			} else {
				$scope.result = "Gramática inválida! saída 14";
			}
		} else {
			$scope.result = "Gramática inválida! saída 15";
		}
	}
	
	$scope.leitura = function() {
		if ($scope.tokens[0].valor == "scanner") {
		  $scope.tokens.splice(0, 1);
			$scope.identificador();
		} else {
			$scope.result = "Gramática inválida! saída 16";
		}
	}

	$scope.impressao = function() {
		if ($scope.tokens[0].valor == "print") {
		  $scope.tokens.splice(0, 1);
			$scope.identificador();
		} else {
			$scope.result = "Gramática inválida! saída 17";
		}
	}

	$scope.expressao = function() {
		$scope.identificador();
		if ($scope.tokens[0].clazz == "o") {
		  $scope.tokens.splice(0, 1);
			$scope.identificador();
		} else {
			$scope.result = "Gramática inválida! saída 18";
		}
	}
	
	$scope.comando = function() {
		var op = $scope.tokens[0].valor;

		switch (op) {
		case "while":
			$scope.enquanto();
			break;

		case "if":
			$scope.se();
			break;

		case "Console.read":
			$scope.leitura();
			break;

		case "Console.log":
			$scope.impressao();
			break;

		default:
			break;
		}
	}
  
});