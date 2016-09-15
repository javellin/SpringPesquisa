app.controller("adminCtrl", ["$scope", "$http", function ($scope, $http) {

    $scope.usuarioAtual = {};
    $scope.tiposUsuario = [];
    $scope.usuario = {usuario: "", email: "", senha: "", tipoUsuario: null};

    $scope.buscarUsuarioAtual = function () {
        $http.get("/servicos/usuario/buscarUsuarioAtual", {}).success(function (response) {
            $scope.usuarioAtual = response;
        });
    }
    $scope.buscarUsuarioAtual();

    $scope.buscarTiposUsuario = function () {
        $http.get("/servicos/usuario/buscarTiposUsuario", {}).success(function (response) {
            $scope.tiposUsuario = response;
        });
    }
    $scope.buscarTiposUsuario();

    $scope.criarUsuario = function () {
        if (validar()) {
            $http.post("/servicos/usuario/criarUsuario", $scope.usuario, {}).success(function () {
                $scope.resetarMensagens();
                $scope.mensagemSucesso.push("Usuário criado com sucesso.");
                $scope.usuario.usuario = "";
                $scope.usuario.email = "";
                $scope.usuario.senha = "";
            }).error(function (data) {
                $scope.resetarMensagens();
                $scope.mensagemErro.push(data.message);
            });
        }
    }

    function validar() {
        $scope.mensagemErro = [];
        validarDadosGerais();
        if ($scope.mensagemErro.length > 0) {
            $scope.mensagemSucesso = [];
            return false;
            scroll(0, 0);
        } else {
            $scope.mensagemErro = [];
            return true;
            scroll(0, 0);
        }
    }

    function validarDadosGerais() {
        if ($scope.usuario.usuario == null || $scope.usuario.usuario == undefined || $scope.usuario.usuario == "") {
            $scope.mensagemErro.push("O campo usuário é de preenchimento obrigatório.")
        }
        if ($scope.usuario.email == null || $scope.usuario.email == undefined || $scope.usuario.email == "") {
            $scope.mensagemErro.push("O campo email é de preenchimento obrigatório.")
        }
        if ($scope.usuario.senha == null || $scope.usuario.senha == undefined || $scope.usuario.senha == "") {
            $scope.mensagemErro.push("O campo senha é de preenchimento obrigatório.")
        }
        if ($scope.usuario.tipoUsuario == null || $scope.usuario.tipoUsuario == undefined || $scope.usuario.tipoUsuario == "") {
            $scope.mensagemErro.push("O campo tipo de usuário é de preenchimento obrigatório.")
        }
    }

    $scope.resetarMensagens = function () {
        $scope.mensagemErro = [];
        $scope.mensagemSucesso = [];
        scroll(0, 0);
    }

}])
;