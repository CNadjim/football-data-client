# Football Data Client

A Java client library for accessing football data through a Football Data REST API (https://docs.football-data.org/general/v4/resources.html)

## Features

- Support for multiple endpoints:
    - Areas
    - Teams
    - Matches
    - Persons
    - Competitions

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
FootballDataClient client = FootballDataClient.builder().addToken("your-api-token").build();
```

### Retrieving data

```java
// Get all areas
Collection<Area> areas = client.area().findAll();

// Find a specific area by ID
Optional<Area> area = client.area().findByAreaId(2081);

// Find team information
Optional<Team> team = client.team().findByTeamId(66);

// Find match information
Optional<Match> match = client.match().findByMatchId(327856);

// Get all Competitions
Collection<Competition> competitions = client.competition().findAll();

// Find competition information
Optional<Competition> competition = client.competition().findByCompetitionId(2021);

// Get all matches for a specific competition
Collection<Match> matches = client.competition().findMatchesByCompetitionId(2021);

// Get all teams for a specific competition
Collection<Team> teams = client.competition().findTeamsByCompetitionId(2021);

// Get all standings for a specific competition
Collection<Standing> standings = client.competition().findStandingsByCompetitionId(2021);

```
