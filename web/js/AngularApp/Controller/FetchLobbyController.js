var source = new EventSource("api/lobby");
FetchLobbyRoomModule.controller( 'FetchLobbyRoomController', function ( $scope, $filter,FetchLobbyRoomService) {
    $scope.rooms = [];
        source.onmessage = function(event) {
        console.log("got message");
        var $messages = $("#messages");
        var t = JSON.parse(event.data);
        console.log(t);
        $scope.rooms = t;
        console.log("test my git");
        }
    
        $scope.createNewGame = function(){
        console.log("tst");
        FetchLobbyRoomService.createNewGame().then(function(response){
        console.log(response);
        }); 
    };
});