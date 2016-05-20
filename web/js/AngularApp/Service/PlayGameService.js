IndexAppModule.service("PlayGameService", function ($http) {
    var serviceAddress = 'api/';
    
    this.initGame = function () {
       var myParam = location.search.split('id=')[1];
       var request = $http.get('initGame',{
            params: {id: myParam}
        })
//       var myParam = location.search.split('id=')[1];
//       var request= $http({
//        url:'initGame', 
//        method: "GET",
//        params: {id: myParam}
//         });
        return request;
    };
    
    this.sendMessage = function(username,message,chatRoomId){
        var request = $http.get('newMessage',{
            params: {username: username,
                     message: message,
                     chatRoomId:chatRoomId
            }
        });
        return request;
    };
    
    this.sendMyCards = function (AcardID,BcardID,chatRoomId){
        var request = $http.get('playGame',{
            params: {AcardID: AcardID,
                     BcardID: BcardID,
                     RoomID:chatRoomId
            }
        });
        return request;
    };
    
    this.updateRoomPlayers = function(roomNo){
        console.log(roomNo);
        var request = $http.get('joinRoom',{
            params: {roomNo: roomNo
            }
        });
        return request;
    };
    this.getRoomPlayersByFresh = function(roomNo){
      var request = $http.get('api/room/'+ roomNo + '/getRoomPlayersByFresh',{
      });
      return request;
    };
    
    this.getMyInfo = function(){
       var request = $http.get('api/user/getMyInfo');
       return request;
       };
    this.onExit = function(){
        $http.post(serviceAddress + 'user/logout');
    };
    
    this.getFriendList = function(){
            var request = $http.get(serviceAddress + 'user/getfriends');
    return request;
    };
});