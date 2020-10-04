**1) Explain the Object Model, and why it is relevant for modern web development**<br>
The object model is about grouping functionality and data into objects, for manipulation by code. It is relevant in web based development due to heavy usage of DOM (Document Object Model) manipulation, as well as JSON (Javascript Object Notation). And example would be finding a div element by its ID, and attaching an event listener.
<br><br>
**2)Explain Javascript events and event bubbling**<br>
An event in javascript is when the user or the browser does something, like clicking, loading the page, or mousing over an element. Event bubbling means, that when an event happens on an element, the handlers for that event on all the ancestor elements are also run. For example: clicking on a p tag within a div with an onclick handler will activate said handler. The ancestor elements can always access information about the target of the event via the event object.
<br><br>
**3) Elaborate on how JSON or XML support communication between subsystems, even when the subsystems are implemented on different platforms**<br>
JSON and XML can facilitate communication between different types of systems because they are widely used standards for data transfer that are supported in many languages and frameworks, either natively or via extensions, like with Gson in Java.
<br><br>
**4)Explain AJAX and how it has changed the way modern webapps are created**<br>
AJAX (Asynchronous Javascript And XML) has enabled data in web pages to be served asynchronously via JSON or XML, such that the server does not need to build an entirely new page everytime something changes. It can instead just send the new data and have the client update the page with javascript.
<br><br>
**5) Explain the Same Origin Policy (for AJAX), and different ways to work around it**<br>
The Same Origin Policy allows scripts to only access data from other pages if both pages have the same origin. One way of working around this restriction is Cross-Origin Resource Sharing, which uses HTTP headers to allow access to certain resources from a different origin. Another way is to run a proxy for an external resource on the same origin.
