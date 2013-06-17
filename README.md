README
========

Description
-----------

This Music Shop is developed as a student's project in the module __Web Engineering 2__ at the _[Baden-Wuerttemberg Cooperative State University Stuttgart](http://www.dbhw-stuttgart.de)_.

### Contact

__Moritz Rupp__: [moritz.rupp@gmail.com](moritz.rupp@gmail.com)  
__Julian Schobert__: [julian.schobert@gmail.com](julian.schobert@gmail.com)

---
To do's
--------

* Calculation of the duration of a medium (Mo)
* ~~After insertig a medium, an album of a media type the object in the session has to be deleted (Mo)~~
* ~~After clicking on back buttons objects in the session have to be deleted (Mo)~~
* The navigation of <code>AllAlbums</code> has to be removed and the back button has to link to <code>AllMedia</code> (Jule)
* Back navigation with GET-Parameter (Jule) *In my opinion it should work, but i cant test it, cause of the error below*
* ~~You cannot buy an album from the detail page (Jule)~~
* WIN only "Unexpected internal error near index 1" - Error after trying to upload a coverpic. Error occurs in line 201 ="split = (delFile.toString()).split("\\");"
	(Occurs while creating a new album or a new MediaType)
* Make sure that there is no album without a medium. Page flow: album -> new medium -> album (Mo)

#####Completely finished pages:

 - album_processing.jsp
 - type_processing.jsp
 - medium_processing.jsp
 - print.jsp
 
#####Pages could be deleted:
 - DevEnvTest.jsp
 - index.jsp

#####Pages missing:
 -/-
 
#####Other items missing:
 -/-
 
#####Incorrect navigation:
 - Back navigation from album details -> medium details doesn't work correctly. Navigates back to <code>AllMedia</code> instead of <code>AllAlbums</code>
 