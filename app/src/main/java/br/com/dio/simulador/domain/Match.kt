package br.com.dio.simulador.domain

data class Match(

    @SerializedName()

    val description: String,
    val place: String,
    val homeTeam: Team,
    val awayTeam: Team
)