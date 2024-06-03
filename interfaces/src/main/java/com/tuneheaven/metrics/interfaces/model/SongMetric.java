package com.tuneheaven.metrics.interfaces.model;

public class SongMetric {

    private final String songName;
    private final String songId;
    private final String authorName;
    private final String authorId;
    private final String userId;
    private final Integer score;
    private final String genre;

    public SongMetric(String songName,
                      String songId,
                      String authorName,
                      String authorId,
                      String userId,
                      Integer score,
                      String genre) {
        this.songName = songName;
        this.songId = songId;
        this.authorName = authorName;
        this.authorId = authorId;
        this.userId = userId;
        this.score = score;
        this.genre = genre;
    }

    public String getSongName() {
        return songName;
    }

    public String getSongId() {
        return songId;
    }

    public String getAuthorName() {
        return authorName;
    }

    public String getAuthorId() {
        return authorId;
    }

    public String getUserId() {
        return userId;
    }

    public Integer getScore() {
        return score;
    }

    public String getGenre() {
        return genre;
    }
}
