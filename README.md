# DistanceAlert [![Bintray](https://img.shields.io/twitter/url?label=Follow&style=social&url=https%3A%2F%2Ftwitter.com%2FAryanDokania)](https://bintray.com/blocke/releases/scalajack) [![Bintray](https://img.shields.io/github/followers/imaryandokania?style=social)](https://bintray.com/blocke/releases/scalajack) [![Bintray](https://img.shields.io/github/license/imaryandokania/VITgram)](https://bintray.com/blocke/releases/scalajack)
---

This app uses bluetooth technology to alert about social distancing norms. RSSI is used , using formula 

<a href="https://www.codecogs.com/eqnedit.php?latex=accuracy=10^{(a-69)/(10*2)}" target="_blank"><img src="https://latex.codecogs.com/gif.latex?accuracy=10^{(a-69)/(10*2)}" title="accuracy=10^{(a-69)/(10*2)}" /></a>
 
  dB is converted
  
  The limit is accuracy<0.9 , if the accuracy value is in this bound then a alert message will be pushed.
  
  -------
  Due to bluetooth frequency strength loss .. it may give wrong alert (but that is occassionally)
