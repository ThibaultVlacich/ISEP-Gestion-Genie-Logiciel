var d = 0;
var s;
var myVar = setInterval(myTimer, 1000);
function myTimer() {
   d = d + 1;
   if(d>59){
     s = 'Timer : ' + Math.floor(d/60) + ' m ' + (d%60) + ' s';
   }
   else{
     s = 'Timer : ' + d + ' s';
   }
   $timer = document.getElementById("timer");
   $pTimer = document.getElementById("pTimer");
   $pTimer.innerHTML = s;
   $timer.value = d;

}