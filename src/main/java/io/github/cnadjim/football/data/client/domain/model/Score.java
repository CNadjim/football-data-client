package io.github.cnadjim.football.data.client.domain.model;


public record Score(String duration,
                    ScoreWinner winner,
                    ScoreResult fullTime,
                    ScoreResult halfTime) {
}
