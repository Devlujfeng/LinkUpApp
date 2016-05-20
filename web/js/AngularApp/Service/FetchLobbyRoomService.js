IndexAppModule.service("FetchLobbyRoomService", function ($http) {
    var serviceAddress = 'api/';
    this.createNewGame = function (roomName,limitation,userName) {
        var request = $http.get('newGame',{
                params: {
                    roomName:roomName,
                    limitation: limitation,
                    userName: userName
                }    
            }
        );
        return request;
    };
    //access restful
    this.getAllRooms = function () {
        var request = $http.get(serviceAddress + 'lobby/getAllRooms');
        return request;
    };
    this.getFriendList = function(){
                var request = $http.get(serviceAddress + 'user/getfriends');
        return request;
    };
    this.onExit = function(){
        $http.post(serviceAddress + 'user/logout');
    };
    this.getOnlinePlayer = function(){
        var request = $http.get('getonlineplayers');
        return request;
    };
    this.getOnlinePlayerRestful = function(){
        var request = $http.get('api/allPlayers/getOnlinePlayerRestful');
        return request;
    };
});