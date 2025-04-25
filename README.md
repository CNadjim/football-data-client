# Football Data Client

A Java client library for accessing football data through a REST API. This client provides a simple and intuitive
interface to retrieve information about football areas, teams, matches, players, and competitions.

## Features

- Access to football data through a clean API
- Support for multiple endpoints:
    - Areas (countries/regions)
    - Teams
    - Matches
    - Players/Persons
    - Competitions
- Token-based authentication
- Customizable HTTP connector
- Built with Java 21

## Requirements

- Java 21 or higher
- Maven 3.6 or higher

## Installation

Add the following dependency to your Maven project:

```xml

<dependency>
    <groupId>io.github.cnadjim</groupId>
    <artifactId>football-data-client</artifactId>
    <version>1.0</version>
</dependency>
```

## Usage

### Creating a client

```java
// Create a client with default settings
FootballDataClient client = new FootballDataClient.FootballDataClientBuilder()
                .addToken("your-api-token")
                .build();

// Or with a custom HTTP connector
FootballDataClient client = new FootballDataClient.FootballDataClientBuilder()
        .addToken("your-api-token")
        .httpConnector(customHttpConnector)
        .build();
```

### Retrieving data

```java
// Get all areas
Collection<Area> areas = client.area().findAll();

// Get a specific area by ID
Optional<Area> england = client.area().findByAreaId(2072);

// Get team information
Optional<Team> team = client.team().findByTeamId(66);

// Get match information
Optional<Match> match = client.match().findByMatchId(327856);

// Get competition information
Optional<Competition> competition = client.competition().findByCompetitionId(2021);
```

## Dependencies

- Jackson (JSON processing)
- Spring Web
- JUnit, AssertJ, and Mockito (for testing)

