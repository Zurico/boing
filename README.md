# Boing

Hi there!, this is a little toy app for android devices I coded to have some fun with my little younger brother and ... to exercise my legs too ;) Take a look at the video below for a demo and start making boings, boings, boings ....

[![Boing demo](/data/portrait.png)](https://www.youtube.com/watch?v=3FTt_JJkiBg "Boing demo")

If you liked it, you may download it from [here](https://github.com/Tsur/boing/releases/tag/1.0.0), or compile from sources with Android Studio.

# How does it work ?

Detecting jumps with a smartphone accelerometer is not a so easy task. Data coming from the sensor contains too much noise and requires to filter it out properly to remove that noise and the effects of gravity on it. In order to get a higher accuracy, it was collected different accelerometer measures from different physical exercises as walking, jumping high, jumping low, running, and from different people (actually just me and my brother hehe) to find a common pattern.

The collected data can be found in the data folder and visualized in the boing [Jupyter](http://jupyter.org/) notebook in same folder. This is not accurate at all, and both the filtering technics and the collected data are not very exhaustive, but plays pretty well for most of cases and as an experiment is a funny app to have some good time with friend and at the same time, for regularly exercising :)

![Boing notebook](/data/notebook1.png)

# Contribute

If you liked it, please feel free to improve and PR-it.

# License

[GNU](https://www.gnu.org/licenses/gpl-3.0.en.html) - Made with :heart: & Boings by Zuri Pabon
