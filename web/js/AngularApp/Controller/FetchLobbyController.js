var source = new EventSource("api/lobby");
var sourceOnlinePlayer = new EventSource("api/allPlayers");

IndexAppModule.controller( 'FetchLobbyRoomController', function ($window, $scope, $filter,FetchLobbyRoomService) {
    $scope.imgIdentifier = "http://www.gravatar.com/avatar/";
    $scope.rooms = [];
    $scope.friends = [];
    $scope.onlinePlayers = [];
    $scope.roomName = '';
    $scope.limitation = '';
    $scope.userName = '';
    
        //var goodtemplate = Handlebars.compile($( "#gameListTemplate" ).html()); 
        source.onmessage = function(event) {
        console.log("got message");
        var t = JSON.parse(event.data);
        console.log(t);
        $scope.$apply(function () {
        $scope.rooms.push(t);
        });   
        console.log("test my git");
        };
        
        $(sourceOnlinePlayer).on("getOnlinePlayer",function(event) {
        console.log("got message");
        var t = JSON.parse(event.originalEvent.data);
        $scope.$apply(function () {
        $scope.onlinePlayers = t;
        }); 
        
        console.log(t);
        console.log("test my online players");
        });
        
        
        $scope.createNewGame = function(){
            if($scope.validateRoomNumbersCreated()){
            FetchLobbyRoomService.createNewGame($scope.roomName,$scope.limitation,$scope.userName).then(function(response){
                    $scope.roomName = '';
                    $scope.limitation = '';
            }); 
        }};

        
        
        $scope.getAllRooms = function(){
            FetchLobbyRoomService.getAllRooms().then(function(response){
                console.log(response.data);
                //var roomId =[];
                //for( i = 0; i < response.data.length; i++){
                $scope.rooms = response.data;
                //roomId.push(t);
                //}
                //$scope.rooms = roomId;
                console.log($scope.rooms);
            });
        };
        $scope.getAllRooms();
        $scope.validateRoomNumbersCreated = function(){
            console.log($scope.rooms.length);
            var t = 0;
            console.log($scope.userName);
            for(i=0; i < $scope.rooms.length; i++){
                if($scope.userName == $scope.rooms[i].createdBy){
                    t++;
                }
            }
            if(t >= 3){
                alert("You can not create more than 3 rooms, Please close previous rooms."); 
                $scope.roomName = '';
                $scope.limitation = '';
                return false;
            }
            else{
                return true;
            }
        };

        $scope.joinGame = function(event){
            console.log(event);
            
            url ="room.html?name=abc?id=" + event.room.GameId;
            location.href = url;
        };
        $scope.getAllFriends = function(){
         FetchLobbyRoomService.getFriendList().then(function(response){
            if(response.data.status == "Please login first"){
                $("#alert").click();
                spanobj = document.getElementById('spanid');
                i = 3;
                     setInterval(function () {
                         num = --i;
                         if (num <= 0) {
                             clearInterval();
                             location = 'index.html';
                         }
                         spanobj.innerHTML = num;
                     }, 1000);
                }else{
                  $scope.friends = response.data;
                  console.log(response.data);
                  $scope.userName = response.data[response.data.length-1].accountName;
                  
                }
           });
        };
        $scope.getAllFriends();
        
        // get online players 
        
        $scope.getOnlinePlayer = function (){  
            FetchLobbyRoomService.getOnlinePlayer().then(function(response){
            console.log(response);
            });
        };
        $scope.getOnlinePlayer();
        
        $scope.getOnlinePlayerRestful = function (){  
            FetchLobbyRoomService.getOnlinePlayerRestful().then(function(response){
            console.log(response.data);
            $scope.onlinePlayers = response.data;
            });
        };
        $scope.getOnlinePlayerRestful();
        
        $scope.backToIndex = function(){
            location = 'index.html';
        };
        $scope.onExit = function() {
         FetchLobbyRoomService.onExit();
            location = 'index.html';
        };
});
//        function joinGame(event){
//            console.log(event.id);
//            //alert("tset");
//            url ="room.html?id=" + event.id;
//            location.href = url;
//        };
//        
        
        
        
        $(document).ready(function(e) {
            var $input = $('#refresh');
            $input.val() == 'yes' ? location.reload(true) : $input.val('yes');
        });
