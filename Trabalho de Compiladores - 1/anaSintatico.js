
var result = "";

var app = angular.module('plunker', []);

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
						$scope.result = "Gramática inválida!1";
					}
				} else {
					$scope.result = "Gramática inválida!2";
				}
			} else {
				$scope.result = "Gramática inválida!3";
			}
	}
	
	$scope.varDeclaracao = function() {
		$scope.identificador();
	}

	$scope.identificador = function() {
		if ($scope.tokens[0].clazz == "i") {
      $scope.tokens.splice(0, 1);
		} else {
			$scope.result = "Gramática inválida!5";
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
							$scope.result = "Gramática inválida!6";
						}
					} else {
						$scope.result = "Gramática inválida!7";
					}
				} else {
					$scope.result = "Gramática inválida!8";
				}
			} else {
				$scope.result = "Gramática inválida!9";
			}
		} else {
			$scope.result = "Gramática inválida!10";
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
						$scope.result = "Gramática inválida!12";
					}
				} else {
					$scope.result = "Gramática inválida!13";
				}
			} else {
				$scope.result = "Gramática inválida!14";
			}
		} else {
			$scope.result = "Gramática inválida!15";
		}
	}
	
	$scope.leitura = function() {
		if ($scope.tokens[0].valor == "scanner") {
		  $scope.tokens.splice(0, 1);
			$scope.identificador();
		} else {
			$scope.result = "Gramática inválida!16";
		}
	}

	$scope.impressao = function() {
		if ($scope.tokens[0].valor == "print") {
		  $scope.tokens.splice(0, 1);
			$scope.identificador();
		} else {
			$scope.result = "Gramática inválida!17";
		}
	}

	$scope.expressao = function() {
		$scope.identificador();
		if ($scope.tokens[0].clazz == "o") {
		  $scope.tokens.splice(0, 1);
			$scope.identificador();
		} else {
			$scope.result = "Gramática inválida!18";
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