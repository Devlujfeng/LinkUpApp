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
            url ="room.html?id="+response.data;
            location.href = url;
            }); 
        };
        $scope.getAllRooms = function(){
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
        $scope.joinGame = function(event){
            console.log(event.room);
            url ="room.html?id=" + event.room;
            location.href = url;
        }
});
        function joinGame(event){
            console.log(event.value);
           // alert("tset");
            url ="room.html?id=" + event.value;
            location.href = url;
        };