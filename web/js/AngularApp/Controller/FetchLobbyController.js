var source = new EventSource("api/lobby");

FetchLobbyRoomModule.controller( 'FetchLobbyRoomController', function ( $scope, $filter,FetchLobbyRoomService) {
    $scope.rooms = [];
        var goodtemplate = Handlebars.compile($( "#gameListTemplate" ).html()); 
        source.onmessage = function(event) {
        console.log("got message");
        var t = JSON.parse(event.data);
        console.log(t);
        var data = {
                     gameId: t.GameId
        };
        $("#gameId").append(goodtemplate(data));    
        console.log("test my git");
        }
        $scope.createNewGame = function(){
            FetchLobbyRoomService.createNewGame().then(function(response){
            console.log(response);
            }); 
        };
        $scope.getAllRooms = function(){
            console.log("test");
            FetchLobbyRoomService.getAllRooms().then(function(response){
                console.log(response);
                var roomId =[];
                for( i = 0; i < response.data.length; i++){
                var t = response.data[i]["rooms"]
                roomId.push(t);
                }
                $scope.rooms = roomId;
                console.log($scope.rooms);
            });
        }
        $scope.getAllRooms();
});

