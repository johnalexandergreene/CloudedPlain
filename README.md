Clouded Plain

IN A NUTSHELL
It's a stage (plain) where cell-pattern generators (clouds) dance
The stage handles cloud mixing by summing cloud-presence at each cell.
And then we render that summed value to a color or strobe

We have a graphic renderer. It converts the plain into an image by using cell values as indices in a color array. simple

We have a sound renderer. It converts the plain to a sound, interpreting shapes and color as pitch and volume and waveform and such.

Our graphic animation rate is 60fps at the moment. We might change it later (120fps?) but probably not.

Our sound sample rate is some multiple of 60 in the vicinity of 44.1khz. 
Something with lots of factors for each frame-sound-sample-duration. Maybe 43200 (60*720. 720=1*2*3*4*5*6)

So when we render graphics we render a frame, increment time, render another frame and so on.

And when we render sound we generate a 1/60th of a second sound in a sound array.
  that would be like 

----------------------
So the subsystems are :

  plain
  cloud
  graphic renderer
  sound renderer
  cloud generator
    every tick of the clock (every frame that is. every 1/60 sec) we query the cloud generator and maybe it generates some clouds. 
    It's a conditional thing, based upon time, present cloud population and etc. The clouds enter the stage, do their dance, and exit.


-----------------------


Framework for doing a certain kind of av composition.

Clouds drifting in layers over plain

Clouds are cell-manifesting agents
Plain is field of cells.


Like Blanketflower but better. Suaver design. Faster. Has sound. Smoother.

Cloud

  void setPlain(plain)

  manifest
    Derive t from plain
    Add values for all cells in plain where cloud is present. 
    Usually that value is 1. Sometimes 2 if we want to illustrate area-overlap or a secon-level effect or something.

  boolean isFinished
    Used to tell the system to discard this cloud because it's done


-----

clouds are time-parametric cell pattern generators

plain is an array of cells

clouds are graphically mixed by summing manifestations in cells and then using that value to get a color from a palette-array

sound is derived by examining plain. Get all the colored shapes. Translate colors into tones.
ex : shape color = waveform. area = frequency. Closeness to field center = volume. Gangliness = vibrato


---------------
It's a tiled-image and sculpted-tone audio-video mixer-generator

The plain is a field of tiles

The clouds are tile-value expressors
They are instantiated, do their dance, then are discarded

The cloud expresses a pattern of tile values, to be summed for each tile. 
That value means the tile's color.
The audio is derived from the various shapes formed by colored masses of tiles.
(or however we want to do it)

-----------------

We have 2 rates to consider
frame rate and audio sample rate
both will be derived from a fundamental tick. Tick frequency will be some really high audio sample rate I guess.


-------------------
try different resolutions for the plain. Fine and coarse.

---------------------

can clouds interact?
spawn more clouds?

---------------------

we use 60 frames per second

-------------------
graphical movement always occurs at 1 tick of movement at a time
either every frame, or every 3rd frame, or whatever. Depending on speed.
ie : object.x = foo, foo+1, foo+2 etc.

IE 1 square tile at a time

--------------------------------
If we use a 60 ticks per frame (ie 60 fps and tickrate are the same and soundsamplerate can be some multiple of that.)
then we get a nice set of usable movement speeds (they are the factors of 60)
1 (ie 1 action per tick)
1/2 (1 action ever other tick)
1/3 (1 action every 3 ticks)
1/4 ...
1/5
1/6 
1/12
etc








    
