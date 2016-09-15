app.controller("cadastroCtrl", ["$scope", "$http", function ($scope, $http) {
    $scope.usuario = {
        usuario: "",
        email: "",
        senha: ""
    }
    $scope.senhaRepetida;

    function validar() {
        $scope.mensagemErro = [];

        validarDadosGerais();

        if ($scope.mensagemErro.length > 0) {
            $scope.mensagemSucesso = [];
            scroll(0, 0);
            return false;
        } else {
            $scope.mensagemErro = [];
            return true;
        }
    }

    function validarDadosGerais() {
        if ($scope.usuario.usuario == null || $scope.usuario.usuario == "" || $scope.usuario.usuario == undefined) {
            $scope.mensagemErro.push("O campo usuário é de preenchimento obrigatório.");
        }
        if ($scope.usuario.email == null || $scope.usuario.email == "" || $scope.usuario.email == undefined) {
            $scope.mensagemErro.push("O campo email é de preenchimento obrigatório.");
        }
        if ($scope.usuario.senha == null || $scope.usuario.senha == "" || $scope.usuario.senha == undefined) {
            $scope.mensagemErro.push("O campo senha é de preenchimento obrigatório.");
        }
        if ($scope.senhaRepetida == null || $scope.senhaRepetida == "" || $scope.senhaRepetida == undefined) {
            $scope.mensagemErro.push("O campo de repetição de senha é de preenchimento obrigatório.");
        }
    }

    $scope.registrar = function () {
        if (validar()) {
            if ($scope.senhaRepetida == $scope.usuario.senha) {
                $http.post("/servicos/usuario/registrar", $scope.usuario, {}).success(function () {
                    $scope.resetarMensagens();
                    $scope.mensagemSucesso.push("Cadastrado com sucesso.");
                }).error(function (data) {
                    $scope.resetarMensagens();
                    $scope.mensagemErro.push(data.message);
                });
            } else {
                $scope.resetarMensagens();
                $scope.mensagemErro.push("As senhas não conferem.");
            }
        }
    }

    $scope.resetarMensagens = function () {
        $scope.mensagemErro = [];
        $scope.mensagemSucesso = [];
        scroll(0, 0);
    }

}]);