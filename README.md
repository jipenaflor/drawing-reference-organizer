# Drawing Reference Organizer
As an artist, I am not always capable of coming up with my original ideas 
for my artworks. Most of the time, I need a lot of references, may it be a 
photograph, a screenshot, or an artwork from another artist, to create my 
own. However, managing tons of references sometimes tends to get cumbersome. 
I also sometimes find saving them on my device impractical as they tend to 
consume some space. And sometimes, I find it better to just search them up 
on Google while I draw. This is the motivation behind this project: 

A RESTful CRUD API for managing drawing references using Spring Boot, Spring 
Security, JWT, JPA, and PostgreSQL

The application defines the following CRUD APIs:

## Auth
| Method | Url                    | Description | Request Body          |
|--------|------------------------|-------------|-----------------------|
| POST   | /api/auth/register     | Sign up     | name, email, password |
| POST   | /api/auth/authenticate | Login       | email, password       |

## Users
| Method | Url             | Description    | Request Body |
|--------|-----------------|----------------|--------------|
| GET    | /api/users/     | Get all users  |              |
| GET    | /api/users/{id} | Get user by id |              |

## Subjects
| Method | Url                                                     | Description                       | Request Body |
|--------|---------------------------------------------------------|-----------------------------------|--------------|
| GET    | /api/subjects/                                          | Get all subjects                  |              |
| GET    | /api/subjects/{id}                                      | Get subject by id                 |              |
| POST   | /api/subjects                                           | Add subject                       | title        |
| PUT    | /api/subjects/{id}                                      | Update title of a subject         | title        |
| DELETE | /api/subjects/{id}                                      | Delete subject by id              |              |
| POST   | /api/subjects/{subjectId}/<br/>references/{referenceId} | Add a reference to a subject      |              |
| DELETE | /api/subjects/{subjectId}/<br/>references/{referenceId} | Remove a reference from a subject |              |

## References
| Method | Url                  | Description              | Request Body     |
|--------|----------------------|--------------------------|------------------|
| GET    | /api/references/     | Get all references       |                  |
| GET    | /api/references/{id} | Get reference by id      |                  |
| POST   | /api/references      | Add reference            | description, url |
| PUT    | /api/references/{id} | Update info of reference | description, url |
| DELETE | /api/references/{id} | Delete reference by id   |                  |

