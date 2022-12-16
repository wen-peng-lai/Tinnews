package com.wen.tinnews.model;

import java.util.List;
import java.util.Objects;

public class UniNewsResponse {
    public Integer totalResults;
    public List<Article> uniArticles;
    public String code;
    public String message;
    public String status;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UniNewsResponse that = (UniNewsResponse) o;
        return Objects.equals(totalResults, that.totalResults) && Objects.equals(uniArticles, that.uniArticles) && Objects.equals(code, that.code) && Objects.equals(message, that.message) && Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(totalResults, uniArticles, code, message, status);
    }

    @Override
    public String toString() {
        return "NewsResponse{" +
                "totalResults=" + totalResults +
                ", uniArticles=" + uniArticles +
                ", code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
