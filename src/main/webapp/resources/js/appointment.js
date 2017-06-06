var timeSpent = 0;
var timeToPrint;
var timeInMin;
var myVar = setInterval(myTimer, 1000);
function myTimer() {
   timeSpent = timeSpent + 1;
   if(timeSpent>59){
     timeToPrint = 'Timer : ' + Math.floor(timeSpent/60) + ' m ' + (timeSpent%60) + ' s';
     timeInMin = Math.floor(timeSpent/60);
   }
   else{
     timeToPrint = 'Timer : ' + timeSpent + ' s';
     timeInMin = 0;
   }
   $timer = document.getElementById("timer");
   $pTimer = document.getElementById("pTimer");
   $pTimer.innerHTML = timeToPrint;
   $timer.value = timeInMin;

}