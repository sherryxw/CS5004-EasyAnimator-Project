ReadMe

# CS5004-Assignment-10-Easy-Animation-Part-2
# Created by: Xue Wu, Yuanyuan Zhou  8/7/2020

===============UPDATE FOR A10==========================================================

for A10, we made several changes of our model, and add view, controller and main.
1. We modified all the toString methods in IShape & IAnimation. (Delete all the sentence related to time). Since we need to convert the tick to second when output a file, so in order to handle this in textual view more flexible, we've deleted those part in the toString func, and append those time information in our textual view. Same as the modelState in the modelImpl class. 

2. Add a tweenHelper in IAnimation interface. In order to calculate the value/position/color at current frame.

3. Add more param to Move/ChangeColor/Scale constructor, add fromX, fromY, fromW, fromH, fromRGB depends on different needs. In order to more easily append motion while reading from files. 

4. Add getLength, getAnimationBound, setAnimationBound, getAllShapesAtFrame in IModel interface. 
4.1 getLength: get the largest ending time of the shape, so we can know when to stop the timer in controller.
4.2 getAnimationBound: get the canvas size from input file
4.3 setAnimationBound: set up the canvas before drawing.
4.4 getAllShapesAtFrame: get all the visible shapes at current frame (will first perform animation at current frame, then get the shapes).

5. View (AnimationView & TextualView, ViewFactory, IView, IDrawingPanel, DrawingPanel)
5.1 IView (interface)
This is the view interface, it contains getViewType, draw, renderText, refresh.
5.2 ViewFactory
Create animation view or textual view based on the given view type.
5.3 Animation view
Set up the panel, scrollPane, in the draw function, panel will call drawShapes to draw current shape on the screen.
5.4 Textual View 
If the given out is empty, default System.out, else, output a file in txt format.

6. Controller (IController, AnimationController, TextController)
6.1 IController (interface)
It contains a method : animation to perform an animation using the provided model. 
6.2 Animation Controller
Add all the shapes at current frame into the animation view, stop when current frame is equal to the shape's longest disappearance time.

7. EasyAnimator (Main)
Get information from command-line arguments, get the filename, the view type, speed and output.
Create the model with the given file.
Create the view with the given model, viewType, out, animationBound information, speed.
Create the controller based on different view type. 
Then, do controller.animate to start the animation.



==============END OF UPDATE FOR A10======================================================

# CS5004-Assignment-9-Easy-Animation
# Created by: Xue Wu, Yuanyuan Zhou  7/30/2020

Purpose of the assignment:  Design a model to represent an animation.

Structure of our model:  

***IShape (Interface)
Contains 7 gets functions to get the attributes and 6 set functions to set to new attributes.

**AbstractShape(implement IShape) Include all common codes.

*Rectangle 
*Oval 


***IAnimations (Interface) 
Contains the Animations functions. Includes getName, getType, getStartTime, getEndTime, performAction. 

**AbstractAnimation(implement IAnimations) Includes all the common codes 

*Move
*Scale
*Color 
For each extended class, they have a private method: canAction, this is use to determine whether is can perform animation or not. It would return true if can perform, false otherwise. 


***IModel (Interface) 
Represents an animation model. It contains 4 gets functions and action functions: addShape, deleteShape, changeColor, scalePerform, move and modelState.

**ModelImpl(implement IModel)
For each model, there are three Map/List to store information: 
1. shapeList: Using HashMap to store shape name & IShape. This shapeList is use for determine whether we setup conflict name between two shapes, and use for find the correct shape and its relative information for future use. 
2. animationList: Using HashMap to store shape name & its animations. This list is use for quick find each shape’s animations, determine whether there is a conflict/overlap between two animation request. 
3. sortedAnimationList: Using arrayList to store all the animations sorted by their startTime. Since we need to perform those animations based on time in the future, so its a good idea to sort them in order by their startTime.

There are 4 private functions that helps to organize code:
1. checkShapeExist: check shape exist or not before making any changes to the model 
2. checkTimeOverlap: check time overlap every time when try to change color/move/scale. Need to check whether there is a time conflict between the new animation and those existed animations.
3. addAnimationInSortedList: add new animation to sorted list. Sort by each animation start time. 
4. updateAllRelatedList: update all related lists every time when add a new animation.


											                   

																   



				 

				