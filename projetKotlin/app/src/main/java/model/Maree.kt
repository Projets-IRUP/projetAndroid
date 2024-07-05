package model

import java.time.LocalDateTime

data class Maree(
    val id_maree: Int,
    val id_port: Int,
    val date_heure: LocalDateTime,
    val hauteur: Double,
    val maree_type: Int,
    val coefficient: Int
)
