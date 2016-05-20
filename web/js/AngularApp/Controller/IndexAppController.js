IndexAppModule.controller( 'IndexAppController', function ( $scope,IndexAppService) {
        $scope.email = "";
        $scope.password = "";
        $scope.status='';
        $scope.register = {
            accountName : '',
            email : '',
            passwd : ''
        };
        $scope.RegStatus='';
        $scope.login = function(){
            IndexAppService.login($scope.email,$scope.password).then(function(response){
            if(response.data.status){
            $scope.status = response.data.status;
            $scope.focus = false;
            $scope.password = "";
            }
            else{
            location.href = "lobby.html";  
            }
            }); 
        };
        
        $scope.createNewUser = function(){
          IndexAppService.createNewUser($scope.register).then(function(response){
              if(response.data.status != "success"){
                console.log(response.data.status); 
                $scope.RegStatus=response.data.status;
                $scope.Regfocus = false;
                $scope.register.passwd = '';
              }else{
                alert("Account created.");
                location.href = "lobby.html";  
              }
          });
        };
});
