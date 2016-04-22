var source2;
var sourcePlayGame;
PlayGameModule.controller('PlayGameController', function ( $scope, $filter,PlayGameService) {
        $scope.Values = [];
        $scope.roomNumber;
        $scope.userName;
        $scope.message;
        $scope.ACardID;
        $scope.BCardID;
    

        
        $scope.initGame = function(){
            PlayGameService.initGame().then(function(response){
                console.log(response.data);
                var value = [];
                for( i = 0; i < response.data.length; i++){
                var t = response.data[i]["value"].toString();
                value.push(t);
                }
                $scope.Values = value;
                console.log($scope.Values); 
               //update Room Number
                $scope.roomNumber = location.search.substring(4);
                
                // sse for sending message
                source2 = new EventSource("api/"+$scope.roomNumber);
                $(source2).on($scope.roomNumber,function(event){
                var chat = JSON.parse(event.originalEvent.data);
                console.log("got message");
                //console.log($("#chattitle").val()+"|"+$("#chattitle").text());
                var $messages2 = $("#messages2");
                console.log(">> msg = " + JSON.stringify(chat));
                $messages2.text(chat.name + ": " + chat.message + "\n" 
                + $messages2.text());
                });  
                
                // sse for sending cards
                sourcePlayGame = new EventSource("api/game/"+$scope.roomNumber);
                
                $(sourcePlayGame).on($scope.roomNumber+"Rd",function(event){
                var chat = JSON.parse(event.originalEvent.data);
                console.log(chat);
                var value = [];
                for( i = 0; i < chat.length; i++){
                var t = chat[i]["value"].toString();
                value.push(t);
                }
                console.log(value);
                $scope.Values = value;
                }); 
            });
        }
        $scope.initGame();
        $scope.updateSelection = function(form){
            if($scope.ACardID && $scope.BCardID){
                if(form.$id-5 == $scope.ACardID){
                    $scope.ACardID = null;
                    $scope.removeCSS(form);
                }
                else if(form.$id-5 == $scope.BCardID){
                    $scope.BCardID = null;
                    $scope.removeCSS(form);
                }
                else{
                    console.log("No Way");
                }
            }
            else if($scope.ACardID == null && $scope.BCardID){
                if(form.$id-5 == $scope.BCardID){
                $scope.BCardID = null;
                $scope.removeCSS(form);
                }
                else{
                $scope.ACardID = form.$id-5;
                $scope.renderCSS(form);
                //
                PlayGameService.sendMyCards($scope.ACardID,$scope.BCardID,$scope.roomNumber).then(function(response){

                });
                }
            }
            else if($scope.BCardID == null && $scope.ACardID){
                if(form.$id-5 == $scope.ACardID){
                $scope.ACardID = null;
                $scope.removeCSS(form);
                }
                else{
                $scope.BCardID = form.$id-5;
                $scope.renderCSS(form);
                //
                PlayGameService.sendMyCards($scope.ACardID,$scope.BCardID,$scope.roomNumber).then(function(response){

                });
                }
            }
            else{
                $scope.ACardID = form.$id-5;
                $scope.renderCSS(form);
            }
        };
        
        $scope.sendMessage = function(){
            console.log($scope.userName + "---" + $scope.message);
            PlayGameService.sendMessage($scope.userName,$scope.message,$scope.roomNumber ).then(function(response){

                });
        };
                
        $scope.renderCSS = function(form){
            console.log(form.$id -5);
            var img = document.getElementById(form.$id-5);
            img.style.border = '2px solid red'; 
        };
        $scope.removeCSS = function(form){
            var img = document.getElementById(form.$id-5);
            img.style.border = '0px solid red'; 
        };
});
