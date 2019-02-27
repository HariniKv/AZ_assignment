
var app = angular.module('guestTable', []);

app.controller('listdata', function ($scope, $http) {
	
	$scope.form = {
            guestID : "",
            guestName : "",
            hotel : "",
            reservationNumber : ""
        };
 
        //Now load the data from server
        //_refreshPageData();

$scope.guests = []; //declare an empty array

$http.get("/guest/").then(function(response){
	$scope.guests = response.data;
	console.log($scope.guests);
});

$scope.onSubmit = function() {
       $http({
        method :'POST',
        url : 'guest/',
        data : angular.toJson($scope.form),
        headers : {
            'Content-Type' : 'application/json'
        }
    }).then(_success, _error);
    
};

 //Private Methods 
//HTTP GET- get all employees collection
function _refreshPageData() {
    $http({
        method : 'GET',
        url : 'guest/'
    }).then(function successCallback(response) {
        $scope.guests = response.data;
    }, function errorCallback(response) {
        console.log(response.statusText);
    });
}
//HTTP DELETE- delete guest by Id
$scope.removeGuest = function(guestID) {
    $http({
        method : 'DELETE',
        url : 'guest/'+guestID
    }).then(_success, _error);
};
/*//In case of edit employee, populate form with guest data
$scope.editGuest = function(guest) {
    $scope.form.guestID = guest.guestID;
    $scope.form.guestName = guest.guestName;
    $scope.form.hotel = guest.hotel;
    $scope.form.reservationNumber = guest.reservationNumber;
};*/
function _success(response) {
    _refreshPageData();
    _clearForm()
}

function _error(response) {
    console.log(response.statusText);
}
//Clear the form
function _clearForm() {
    $scope.form.guestID = "";
    $scope.form.guestName = "";
    $scope.form.hotel = "";
    $scope.form.reservationNumber = "";
};


});
