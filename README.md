# handwriting-image-translator
An image analysis tool that recognises and tranlsates handwritten text from an image. It uses Google Cloud services.

### How to use
After launching the app, select an image file. After the analysis, you should specify:
* The language of the handwritten text
* The language that you want to translate to

### Results
As seen in the image below, the original handwritten text is shown in the middle and the translation is shown in red colour.

![alt text][results-image]

The log for the execution is as follows:
```
	 Original text:	hur mar d u
Predicted language:	sv
	 True language:	sv
	Corrected text:	null
	  Translate to:	en
	   Translation:	how did you
```
According to the log, the language prediction was correct and no spelling correction was required.
[results-image]: https://github.com/yannismarkou/handwriting-image-translator/blob/master/results.png "Results"