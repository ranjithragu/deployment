angular.module('myApp')
.service("AjaxService", ["$log", "$http", '$location', "$window","$q","detailService",
  function ($log, $http, $location, $window,$q,detailService) {

    var self  =this;
    self.baseUrl = "http://localhost:8690";

    self.signIn = function (user) {

      return $http({

        method: 'POST',
        url: self.baseUrl + '/user/createuser',
        headers: {
          'Content-Type': 'application/json'
        },
        data: angular.toJson(user)
      }).then(function (data, status) {
        return data;
      }, function (error) {
        $log.log("Error while loading urls!");
        return 0;
      });

    };
    self.login = function (user) {
      return $http({
       method: 'POST',
       url: self.baseUrl + '/user/login',
       headers: {
        'Content-Type': 'application/json'
      },
      data: angular.toJson(user),
    }).then(function (data, status) {
     return data;
   }, function (error) {
     $log.log("Error while loading urls!");
     return error;
   });
  };

  self.allQuestions = function () {
    return $http({
      method: 'GET',
      url: self.baseUrl + '/details/listByUserId',
    }).then(function (data) {
      return data;
    }, function (error) {
      $log.log("Error while loading urls!");
      return error;
    });
  };
  self.scoreUpdate = function (id,score) {
  	console.log("score===========>",score);
  	console.log("id=============>",id);
    self.totalScore = detailService.scoreInformation;
    self.totalQuestions = detailService.totalOfQuestions;
    self.total = self.totalScore/self.totalQuestions;
    self.percentage = self.total*100;
    var Indata = {'product': score, 'id': id };
    
    console.log("Indata=============>",angular.toJson(Indata));
    return $http({
      method: 'PUT',
      url: self.baseUrl + '/user/updateScore?userId='+id+'&score='+self.percentage,
    })
    .then(function (data) {});
  };

  self.detailedUser = function (id) {
    return $http({
      method: 'GET',
      url: self.baseUrl + '/user/userlist?userId='+id,
    }).then(function (data) {
      return data;
    }, function (error) {
      $log.log("Error while loading urls!");
      return error;
    });
  };

  self.allQuestionsToAdmin = function () {
    return $http({
      method: 'GET',
      url: self.baseUrl + '/details/listAll',
    }).then(function (data) {
      return data;
    }, function (error) {
      $log.log("Error while loading urls!");
      return error;
    });
  };

  self.allUsersToAdmin = function () {
    return $http({
      method: 'GET',
      url: self.baseUrl + '/user/listAll',
    }).then(function (data) {
      return data;
    }, function (error) {
      $log.log("Error while loading urls!");
      return error;
    });
  };
  
  self.userByFilter = function (total) {
	    return $http({
	      method: 'GET',
	      url: self.baseUrl + '/user/userlistByFilter?college='+total.college+'&department='+total.department,
	    }).then(function (data) {
	      return data;
	    }, function (error) {
	      $log.log("Error while loading urls!");
	      return error;
	    });
	  };

}]);
