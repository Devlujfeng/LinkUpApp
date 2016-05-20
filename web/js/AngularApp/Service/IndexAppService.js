IndexAppModule.service("IndexAppService", function ($http) {
    var serviceAddress = 'api/';
    this.login = function (email,passwd) {
                console.log(email + "----" + passwd)
                var request = $http.get(serviceAddress+'user/login',{
            params: {
                     email: email,
                     passwd: passwd
            }
        });
        return request;
    };
    
    this.createNewUser = function(userObj){
                       var request = $http.get(serviceAddress+'user/register',{
            params: {
                     accountName: userObj.accountName,
                     email: userObj.email,
                     passwd: userObj.passwd
            }
        });
        return request; 
    };
});
