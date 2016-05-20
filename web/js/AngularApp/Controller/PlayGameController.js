var source2;
var sourcePlayGame;
var sourceGetRoomPlayers;
IndexAppModule.controller('PlayGameController', function ( $scope, $filter,PlayGameService) {
        $scope.Values = [];
        $scope.roomNumber;
        $scope.userName;
        $scope.message;
        $scope.ACardID;
        $scope.BCardID;
        $scope.classVar = "imgresize imgclick";
        $scope.messages = [];
        $scope.roomPlayers = [];
        $scope.imgIdentifier = "http://www.gravatar.com/avatar/";
    
        
        $scope.updateRoomPlayers = function(){
            PlayGameService.updateRoomPlayers($scope.roomNumber).then(function(response){

            });
        };
        
        $scope.getMyInfo = function(){
          PlayGameService.getMyInfo().then(function(response){
              console.log(response.data);
              $scope.userName = response.data.userName;
          });
        };
        $scope.getMyInfo();
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
               var t = location.search.split("=");
                $scope.roomNumber = t[2];
                
                // sse for sending message
                source2 = new EventSource("api/"+$scope.roomNumber);
                $(source2).on($scope.roomNumber,function(event){
                var chat = JSON.parse(event.originalEvent.data);
                $scope.$apply(function(){
                   $scope.messages.push(chat); 
                });
                console.log($scope.messages);
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
                $scope.$apply(function () {
                $scope.Values = value;
                });
                }); 
                //SSE for update roomplayers
            sourceGetRoomPlayers = new EventSource("api/room/"+$scope.roomNumber);
            $(sourceGetRoomPlayers).on($scope.roomNumber+"players",function(event){
            var chat = JSON.parse(event.originalEvent.data);
            $scope.$apply(function () {
            $scope.roomPlayers = chat;
            });
        }); 
            });
        
        };
        $scope.initGame();
        $scope.updateSelection = function(form){
        if(form.value != "0"){
            if($scope.ACardID && $scope.BCardID){
                if(form.$index == $scope.ACardID){
                    $scope.ACardID = null;
                    $scope.removeCSS(form);
                }
                else if(form.$index == $scope.BCardID){
                    $scope.BCardID = null;
                    $scope.removeCSS(form);
                }
                else{
                    console.log("No Way");
                }
            }
            else if($scope.ACardID == null && $scope.BCardID){
                if(form.$index == $scope.BCardID){
                $scope.BCardID = null;
                $scope.removeCSS(form);
                }
                else{
                $scope.ACardID = form.$index;
                $scope.renderCSS(form);
                //
                PlayGameService.sendMyCards($scope.ACardID,$scope.BCardID,$scope.roomNumber).then(function(response){
                var img = document.getElementById($scope.ACardID);
                img.style.border = null; 
                var img = document.getElementById($scope.BCardID);
                img.style.border = null; 
                $scope.ACardID = null;
                $scope.BCardID = null;
                });
                }
            }
            else if($scope.BCardID == null && $scope.ACardID){
                if(form.$index == $scope.ACardID){
                $scope.ACardID = null;
                $scope.removeCSS(form);
                }
                else{
                $scope.BCardID = form.$index;
                $scope.renderCSS(form);
                //
                PlayGameService.sendMyCards($scope.ACardID,$scope.BCardID,$scope.roomNumber).then(function(response){
                var img = document.getElementById($scope.ACardID);
                img.style.border = null; 
                var img = document.getElementById($scope.BCardID);
                img.style.border = null; 
                $scope.ACardID = null;
                $scope.BCardID = null;
                });
                }
            }
            else{
                $scope.ACardID = form.$index;
                $scope.renderCSS(form);
            }
        }
        };
        
        $scope.sendMessage = function(){
            console.log($scope.userName + "---" + $scope.message);
            PlayGameService.sendMessage($scope.userName,$scope.message,$scope.roomNumber ).then(function(response){
               $scope.message = ''; 
            });
        };
                
        $scope.renderCSS = function(form){
            console.log(form);
            console.log(form.$index);
            var img = document.getElementById(form.$index);
            img.style.border = '2px solid #FBFF00'; 
            img.classList.add("imgclickAfter");
            
            
        };
        $scope.removeCSS = function(form){
            var img = document.getElementById(form.$index);
            img.style.border = '2px solid black'; 
        };
        
        $scope.updateRoomPlayers = function(){
            var t = location.search.split("=");
            PlayGameService.updateRoomPlayers(t[2]).then(function(response){

            });
        };
       $scope.updateRoomPlayers();
       $scope.getRoomPlayersByFresh = function(){
           var t = location.search.split("=");
           PlayGameService.getRoomPlayersByFresh(t[2]).then(function(resopnse){
               console.log(resopnse.data);
               $scope.roomPlayers = resopnse.data;
           });
       };
       $scope.getRoomPlayersByFresh();
        $scope.onExit = function() {
         PlayGameService.onExit();
            location = 'index.html';
       };
       
        $scope.getAllFriends = function(){
           PlayGameService.getFriendList().then(function(response){
            if(response.data.status == "Please login first"){
                $("#alert").click();
                var num = 0;
                spanobj = document.getElementById('spanid');
                        var i = 2   ;
                     setInterval(function () {
                         num = --i;
                         if (num <= 0) {
                             clearInterval();
                             location = 'index.html';
                         }
                         spanobj.innerHTML = num;
                     }, 1000);
                }
           });
        };
        $scope.getAllFriends();
        $scope.backToIndex = function(){
            location = 'index.html';
        };
        
        $scope.backToLobby = function(){
            location = 'lobby.html';
        };
}); 
