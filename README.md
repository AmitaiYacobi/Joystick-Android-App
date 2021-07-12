# Joystick Android App

### Summary
This project is a remote control joystick  which can connect to the "FlightGear" simulator and fly a plane remotely.<br/> 
The project is MVVM designed and coded in kotlin.<br/>
[Here](https://www.youtube.com/watch?v=Mf5ohSK4ZjU&t=23s) you can watch a nice video that explains about the structure of the project (int Hebrew) and also present a demo of the app.<br/>


### Settings
In order to run this app, first you should clone this repository.<br/>
Then you have to make sure you have Android Studio IDE (or SDK on intellij) installed on your machine, if you don't, you can install it from [here](https://developer.android.com/studio?gclid=CjwKCAjwzruGBhBAEiwAUqMR8L1rRVjMNRQGKBCEKqqoMIVaZWzb2HkbZrkRoLw6NXNPjQ76Kd9vvBoC2NUQAvD_BwE&gclsrc=aw.ds). Then compile it with your IDE.<br />
After that, you have to download the FlightGear simulator from [here](https://www.flightgear.org/) and install it.<br />
After installing the simulator, open it and go to the 'Setting' section. Under 'Additional Settings', type the following  command:<br/>
``--telnet=socket,in,10,127.0.0.1,6400,tcp``<br/><br/>
It should look like this:<br/><br/>
![image](https://user-images.githubusercontent.com/71650499/122672097-7a4aec80-d1d2-11eb-8afd-b89717835fb9.png)<br/>
Finally press on the 'fly' button and boot the simulator up.

### Running
After the process above, you can load the joystick-app into your android device and open it.<br/>
Now all you need to do is to type your PC's IP address and ```6400``` as a port number and that's it.<br/>
It should look like this: <br/><br/>
![image](https://user-images.githubusercontent.com/71650499/122672142-ad8d7b80-d1d2-11eb-8a41-76e1671053ec.png)<br/>

### Structue and hierarchy
![image](https://user-images.githubusercontent.com/71650499/122672015-0f99b100-d1d2-11eb-8cac-746d46269e0f.png)


### Developers:
  * Ron Eliav
  * Amitai Yacobi







