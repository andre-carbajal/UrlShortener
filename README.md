# UrlShortener

## Overview

UrlShortener is a web application that allows users to shorten long URLs into more manageable, short URLs. The
application provides both a REST API and a web-based UI for managing URLs.

[Roadmap Project Detail](https://roadmap.sh/projects/url-shortening-service)
[Roadmap Project Solution](https://roadmap.sh/projects/url-shortening-service/solutions?u=66a1204323c186c28d4f18e8)

## Features

- Shorten long URLs
- Custom URL codes
- Redirect to original URLs
- View all shortened URLs
- Authentication for URL creation

## Technologies Used

- Java 21
- Spring Boot
- Maven
- MySql
- Thymeleaf
- Tailwind CSS

## Getting Started

### Installation

1. Clone the repository

```sh
git clone https://github.com/andre-carbajal/UrlShortener.git
cd UrlShortener
```

2. Create a MySQL database

```sql
CREATE DATABASE url;
```

3. Build the project

```sh
mvn clean install
```

4. Run the application

```sh
mvn spring-boot:run
```

### Usage

#### REST API

- **Get all URLs**: `GET /api/urls`
- **Update URL**: `PUT /api/urls/{id}`
- **Delete URL**: `DELETE /api/urls/{id}`

#### Web UI

- Access the web UI at `http://localhost:8080/`
- Shorten a URL using the form on the homepage
- View all shortened URLs on the homepage
- Redirect to the original URL by clicking on the shortened URL

## Project Structure

- `src/main/java/net/andrecarbajal/urlshortener/controller`: Contains the controllers for handling web requests.
- `src/main/java/net/andrecarbajal/urlshortener/domain/url`: Contains the domain models and services for URL management.
- `src/main/resources/templates`: Contains the Thymeleaf templates for the web UI.
