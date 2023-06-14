# webeC HotKitchen Project
This is a web engineering project that has been created by Alison Fersch, Daniel Nüssli and Jan Wälti as part of the
webeC module.

* **Alison Fersch** - alison.fersch@students.fhnw.ch

*  **Daniel Nüssli** - daniel.nuessli@students.fhnw.ch
  
* **Jan Wälti** - jan.waelti@students.fhnw.ch


## Description
In this project we implemented a web application for a recipe blog. The blog has recipes incl. ingredients, needed equipment and instructions. 
Registered users can leave comments on recipes and rate the recipes, while non-registered users can simply view recipes. If users want all the functionalities, they can register via the "Register" page in the header navigation. All users can view the news and about page that round out the application. Users with the role admin or author can also edit, add or delete recipes.

## Implemented features
### Basic requirements

* Domain with articles, news, recipes, ingredients, ratings, comments and users
* Interactive UI for desktop devices with navigation via a header bar
* Validated HTML
* Server and client-sided validation of user input
* Role-based security with the following roles: Admin, Author and Reader<br>Login with these default users (username - pw):<br>Admin - admin<br>Author - author<br>Reader - reader
* Testing with unit tests, integration tests and E2E tests
* Well-structured code
* About page with a description of the functionalities of the application

### Additional features
* Functionalities for non-registered users: Viewing the welcome-, news-, recipes- and about-page as well as each recipe and its comments. These users can register via the header bar to gain all functionalities of registered users of the web application.
* Functionalities for logged-in users of role "Reader": Viewing the welcome-, news-, recipes- and about-pages as well as each recipe and its comments. Users can log in via the header bar. Logged-in users can leave comments and ratings on recipes.
* Functionalities for logged-in users of role "Admin" or "Author": Viewing the welcome-, news-, recipes- and about-pages as well as each recipe and its comments. Users can log in via the header bar. Logged-in users can leave comments and ratings on recipes. Admins and Authors can additionally add, edit and delete recipes. When editing and adding recipes, they can add or remove ingredients, equipment and preparation steps, as well as change the title, short description, times, servings, diet and header image.
* Comment on recipes: Every recipe features a discussion section where logged-in users can leave comments and talk to each other.
* Rate recipes: Logged-in users can leave ratings on recipes with a number between 1 and 5. The overall rating of each recipe is displayed on the recipe page as well.

### Extensions

* Responsive design of the application to allow problem free usage on any device
* User management incl. registration with the roles admin, author and reader. The three roles have different authorities and can do different things on the web application. Any user of the web application can register, login and logout.
* REST-service: Usage of the New York Times REST-Service for the news section
* Dynamic elements with JavaScript: Back-to-top button, tooltips on the rating-stars, dynamic accordion on the about page and image upload using ajax
* File manager for uploading and downloading files (in our application used for images)


## W3 Validation
We found one error with the w3 validation:
* Error: Attribute stroke-width not allowed on element i at this point.
> Because it is an error caused by the third party dependency feather icon we can no fixit without removing the dependency.
> See [Feather](https://feathericons.com) for more information.
