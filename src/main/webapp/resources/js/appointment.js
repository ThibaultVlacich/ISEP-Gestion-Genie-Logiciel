var d = 0;
var s;
var r;
var myVar = setInterval(myTimer, 1000);
function myTimer() {
   d = d + 1;
   if(d>59){
     s = 'Timer : ' + Math.floor(d/60) + ' m ' + (d%60) + ' s';
     r = Math.floor(d/60);
   }
   else{
     s = 'Timer : ' + d + ' s';
     r = 0;
   }
   $timer = document.getElementById("timer");
   $pTimer = document.getElementById("pTimer");
   $pTimer.innerHTML = s;
   $timer.value = r;

}