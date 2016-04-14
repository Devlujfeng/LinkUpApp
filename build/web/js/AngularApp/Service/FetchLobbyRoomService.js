FetchLobbyRoomModule.service("FetchLobbyRoomService", function ($http) {
    var serviceAddress = 'api/';
    this.createNewGame = function () {
        var request = $http.get('newGame');
        return request;
    };
    //access restful
    this.getAllRooms = function () {
        var request = $http.get(serviceAddress + 'lobby/getAllRooms');
        return request;
    };
    
    
});