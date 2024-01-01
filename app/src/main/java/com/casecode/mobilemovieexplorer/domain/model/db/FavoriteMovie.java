package com.casecode.mobilemovieexplorer.domain.model.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by Mahmoud Abdalhafeez on 12/27/2023
 */
@Data
@AllArgsConstructor
@Entity(tableName = "favorite_movie")
public class FavoriteMovie {
    @PrimaryKey
    @ColumnInfo(name = "id_movie")
    public int idMovie;

    @ColumnInfo(name = "backdrop_path")
    public String backdropPath;
    @ColumnInfo(name = "original_title")
    public String originalTitle;
    @ColumnInfo(name = "vote_average")
    public double voteAverage;

    @ColumnInfo(name = "runtime")
    public int runtime;

}
