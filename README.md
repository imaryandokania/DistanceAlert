# DistanceAlert [![Bintray](https://img.shields.io/twitter/url?label=Follow&style=social&url=https%3A%2F%2Ftwitter.com%2FAryanDokania)](https://bintray.com/blocke/releases/scalajack) [![Bintray](https://img.shields.io/github/followers/imaryandokania?style=social)](https://bintray.com/blocke/releases/scalajack) [![Bintray](https://img.shields.io/github/license/imaryandokania/VITgram)](https://bintray.com/blocke/releases/scalajack)
---


This app uses bluetooth technology to alert about social distancing norms.Many applications are available on Play Store that uses internet conectivity to send gps coordinates and accordingly notify, but what if internet is not available so for that  RSSI is used , and using formula 

<a href="https://www.codecogs.com/eqnedit.php?latex=accuracy=10^{(a-69)/(10*2)}" target="_blank"><img src="https://latex.codecogs.com/gif.latex?accuracy=10^{(a-69)/(10*2)}" title="accuracy=10^{(a-69)/(10*2)}" /></a>
 
  dB is converted
  
  The limit is accuracy<0.9 , if the accuracy value is in this bound then a alert message will be pushed.
  
  ## Scanning for Bluetooth Range Frequency
  
  <img src="https://github.com/imaryandokania/DistanceAlert2/blob/master/IMG_5483.PNG" width="300" height="500">
  
  ------
  
  ## PUSH Notification
  
   <img src=" https://github.com/imaryandokania/DistanceAlert2/blob/master/IMG_5484.jpg" width="300" height="500">
  
  -------
  Due to bluetooth frequency strength loss .. it may give wrong alert (but that is occassionally)
